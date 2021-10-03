package br.com.schoolcalendar.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.exception.InvalidException;
import br.com.schoolcalendar.exception.InvalidUserException;
import br.com.schoolcalendar.forms.UserForm;
import br.com.schoolcalendar.models.Role;
import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.RoleRepository;
import br.com.schoolcalendar.repository.UserRepository;

@Service
public class UserValidator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public void validate(UserForm form, User user) {
		if (!form.validateEmail()) {
			throw new InvalidException("email", "O email informado é inválido.");
		}
		userRepository.findByEmail(form.getEmail()).ifPresent(userExist -> {
			throw new InvalidUserException("Já existe um usuário cadastrado com o e-mail informado.");
		});

		Role role = roleRepository.findByName(form.getRole())
				.orElseThrow(() -> new InvalidException("role", "Perfil informado é inválido"));

		user.getRoles().add(role);
	}
}
