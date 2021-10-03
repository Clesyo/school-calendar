package br.com.schoolcalendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.forms.UserForm;
import br.com.schoolcalendar.interfaces.IUserService;
import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.UserRepository;
import br.com.schoolcalendar.validator.UserValidator;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	@Override
	public User save(UserForm form) {
		User user = form.toUser();
		userValidator.validate(form, user);

		return userRepository.save(user);
	}

}
