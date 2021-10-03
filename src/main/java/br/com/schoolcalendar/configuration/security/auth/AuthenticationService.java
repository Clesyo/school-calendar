package br.com.schoolcalendar.configuration.security.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.configuration.security.jwt.JwtService;
import br.com.schoolcalendar.exception.dtos.MessageDto;
import br.com.schoolcalendar.exception.dtos.TokenDto;
import br.com.schoolcalendar.forms.CredentialForm;
import br.com.schoolcalendar.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private JwtService jwtService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Não foi encontrado usuário com email informado."));
	}

	public ResponseEntity<Object> authenticate(@Valid CredentialForm form) {

		UsernamePasswordAuthenticationToken user = form.convert();
		return authenticateUser(user);
	}

	private ResponseEntity<Object> authenticateUser(UsernamePasswordAuthenticationToken user) {
		try {
			Authentication authenticate = authManager.authenticate(user);
			String token = jwtService.generateToken(authenticate);
			return ResponseEntity.ok(new TokenDto(token, "Bearer"));

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(
					new MessageDto("Email ou senha informada estão incorretos. Verifique os dados e tente novamente."),
					HttpStatus.UNAUTHORIZED);
		}

	}

}
