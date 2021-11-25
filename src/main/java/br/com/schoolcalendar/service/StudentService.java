package br.com.schoolcalendar.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.forms.StudentForm;
import br.com.schoolcalendar.interfaces.IStudentService;
import br.com.schoolcalendar.models.Address;
import br.com.schoolcalendar.models.City;
import br.com.schoolcalendar.models.Role;
import br.com.schoolcalendar.models.Student;
import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.CityRepository;
import br.com.schoolcalendar.repository.RoleRepository;
import br.com.schoolcalendar.repository.StudentRepository;
import br.com.schoolcalendar.validator.StudentValidator;

@Service
public class StudentService implements IStudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentValidator studentValidator;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private CityRepository cityRepository;

	@Override
	public Page<Student> find(Optional<String> filter, Pageable pageable) {
		if(filter.isPresent())
		return studentRepository.findBySearchQueryContains(filter, pageable);
		
		return studentRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Student save(@Valid StudentForm form) {
		studentValidator.validate(form);
		Student student = form.toStudent();
		createUserFromStudent(form, student);
		createAddressFromStudent(form, student);
		Optional<Student> studentExist = studentRepository.findByCpf(form.getCpf());
		if (studentExist.isPresent()) {
			return studentExist.get();
		}
		return studentRepository.save(student);
	}

	@Override
	public Student findById(Long id) {
		return studentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado para o ID informado."));
	}

	@Override
	public Student findByPublicId(String publicId) {
		return studentRepository.findByPublicId(publicId)
				.orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado para o publicId informado."));
	}

	@Override
	public Student update(Long id, StudentForm form) {
		return studentRepository.findById(id).map(student -> {
			studentValidator.validate(form);

			studentRepository.findByCpfAndIdNot(form.getCpf(), id).ifPresent(st -> {
				throw new InvalidException("Já existe um estudante com CPF informado.");
			});

			Student studentConvert = form.toStudent(student);

			return studentRepository.save(studentConvert);
		}).orElseThrow(() -> new EntityNotFoundException("Estudante não encontrado para o ID informado."));
	}

	@Override
	public void delete(Long id) {

	}

	public void createUserFromStudent(StudentForm form, Student student) {

		Role role = roleRepository.findByName(UserType.STUDENT.name())
				.orElseThrow(() -> new EntityNotFoundException("Função não encontrada."));

		User user = new User();
		user.setEmail(form.getEmail());
		user.setName(form.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRole(role);

		student.setUser(user);
	}

	public void createAddressFromStudent(StudentForm form, Student student) {
		City city = cityRepository.findByIbgeCode(form.getIbgeCode())
				.orElseThrow(() -> new EntityNotFoundException("Cidade não encontada para os dados informados."));

		Address address = new Address();

		address.setZipCode(form.getZipCode());
		address.setStreet(form.getStreet());
		address.setNumber(form.getNumber());
		address.setComplement(form.getComplement());
		address.setDistrict(form.getDistrict());
		address.setCity(city);
		address.setState(city.getState());
		student.setAddress(address);
	}

}
