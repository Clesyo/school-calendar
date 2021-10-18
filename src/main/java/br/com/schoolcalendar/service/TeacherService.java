package br.com.schoolcalendar.service;

import java.util.Arrays;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.forms.TeacherForm;
import br.com.schoolcalendar.models.Role;
import br.com.schoolcalendar.models.Teacher;
import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.RoleRepository;
import br.com.schoolcalendar.repository.TeacherRepository;

@Service
public class TeacherService {

	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	
	public Teacher findById(Long id) {
		return teacherRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com ID informado."));
	}
	public void createUserFromTeacher(TeacherForm form, Teacher teacher) {

		Role role = roleRepository.findByName(UserType.TEACHER.name())
				.orElseThrow(() -> new EntityNotFoundException("Função não encontrada."));

		User user = new User();
		user.setEmail(form.getEmail());
		user.setName(form.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
		user.setRoles(Arrays.asList(role));

		teacher.setUser(user);
	}
}
