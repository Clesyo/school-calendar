package br.com.schoolcalendar.interfaces;

import br.com.schoolcalendar.forms.UserForm;
import br.com.schoolcalendar.models.User;

public interface IUserService {

	User save(UserForm form);
}
