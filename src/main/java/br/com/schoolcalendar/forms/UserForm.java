package br.com.schoolcalendar.forms;

import javax.validation.constraints.NotBlank;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.utils.Utils;
import br.com.schoolcalendar.validator.ValidPassword;

public class UserForm {

	@NotBlank(message = "Nome não pode ser vazio.")
	private String name;
	
	@NotBlank(message = "Email não pode ser vazio.")
	private String email;
	
	@NotBlank(message = "Senha não pode ser vazio.")
	@ValidPassword
	private String password;
	
	@NotBlank(message = "Perfil não pode ser vazio.")
	private String role;
	
	public User toUser() {
		User userModel = new User();
		return merge(userModel );
	}
	
	public User merge(User userModel) {
	
		if(!Utils.isEmpty(email)) {
			userModel.setEmail(this.email);
		}
		if(!Utils.isEmpty(name)) {
			userModel.setName(name);
		}
		
		if(!Utils.isEmpty(password)) {
			userModel.setPassword(new BCryptPasswordEncoder().encode(this.password));
		}
		return userModel;
	}
	
	public boolean validateEmail() {
		return Utils.isEmailValid(this.email);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String senha) {
		this.password = senha;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
