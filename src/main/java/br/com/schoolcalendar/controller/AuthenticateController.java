package br.com.schoolcalendar.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.schoolcalendar.configuration.security.auth.AuthenticationService;
import br.com.schoolcalendar.forms.CredentialForm;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticateController {

	@Autowired
	private AuthenticationService authenticationService;
	
	public ResponseEntity<Object> auth(@RequestBody @Valid CredentialForm form, BindingResult result)
			throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {
		if(result.hasErrors()) {
			throw new MethodArgumentNotValidException(
					new MethodParameter(this.getClass().getDeclaredMethod("authenticate", CredentialForm.class, BindingResult.class), 0), 
					result
					);
		}
		return authenticationService.authenticate(form); 
	}
}
