package br.com.schoolcalendar.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.forms.TeacherForm;
import br.com.schoolcalendar.interfaces.ITeacherService;
import br.com.schoolcalendar.models.Address;
import br.com.schoolcalendar.models.City;
import br.com.schoolcalendar.models.Role;
import br.com.schoolcalendar.models.Teacher;
import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.CityRepository;
import br.com.schoolcalendar.repository.RoleRepository;
import br.com.schoolcalendar.repository.TeacherRepository;
import br.com.schoolcalendar.utils.Utils;
import br.com.schoolcalendar.validator.TeacherValidator;

@Service
public class TeacherService implements ITeacherService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherValidator teacherValidator;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public Teacher findById(Long id) {
		return teacherRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID informado."));
	}

	@Override
	public Page<Teacher> find(Optional<String> filter, Pageable pageable) {
		if (filter.isPresent())
			return teacherRepository.findBySearchQueryContains(Utils.normalizeToQuery(filter.get()).describeConstable(),
					pageable);

		return teacherRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Teacher save(TeacherForm form) {
		teacherValidator.validate(form);
		Teacher teacher = form.toTeacher();
		createUserFromTeacher(form, teacher);
		createAddressFromStudent(form, teacher);
		Optional<Teacher> teacherExist = teacherRepository.findByCpf(form.getCpf());
		if (teacherExist.isPresent()) {
			return teacherExist.get();
		}
		return teacherRepository.save(teacher);
	}

	@Override
	public Teacher findByPublicId(String publicId) {
		return teacherRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException("Professor não encontrado para publicId informado."));
	}

	@Override
	public Teacher update(Long id, TeacherForm form) {
		// TODO Auto-generated method stub
		return teacherRepository.findById(id).map(teacher -> {
			teacherRepository.findByCpfAndIdNot(form.getCpf(), id).ifPresent(st -> {
				throw new InvalidException("Já existe um professor com CPF informado.");
			});
			Teacher teacherUpdate = form.toTeacher(teacher);
			createAddressFromStudent(form, teacherUpdate);
			return teacherRepository.save(teacherUpdate);
		}).orElseThrow(() -> new EntityNotFoundException("Professor não encontrado para o ID informado."));
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
	}

	public void createUserFromTeacher(TeacherForm form, Teacher teacher) {

		Role role = roleRepository.findByName(UserType.TEACHER.name())
				.orElseThrow(() -> new EntityNotFoundException("Função não encontrada."));

		User user = new User();
		user.setEmail(form.getEmail());
		user.setName(form.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(role);
		teacher.setUser(user);
	}

	public void createAddressFromStudent(TeacherForm form, Teacher teacher) {
		City city = cityRepository.findByIbgeCode(form.getIbgeCode())
				.orElseThrow(() -> new EntityNotFoundException("Cidade não encontada para os dados informados."));

		Address address = new Address();
		if (teacher.getAddress() != null)
			address = teacher.getAddress();
		
		address.setZipCode(form.getZipCode());
		address.setStreet(form.getStreet());
		address.setNumber(form.getNumber());
		address.setComplement(form.getComplement());
		address.setDistrict(form.getDistrict());
		address.setCity(city);
		address.setState(city.getState());
		teacher.setAddress(address);
	}
}
