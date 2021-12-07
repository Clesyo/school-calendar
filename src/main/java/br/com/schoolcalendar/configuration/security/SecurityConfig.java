package br.com.schoolcalendar.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import static org.springframework.http.HttpMethod.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.schoolcalendar.configuration.security.auth.AuthenticationService;
import br.com.schoolcalendar.configuration.security.jwt.JwtAuthFilter;
import br.com.schoolcalendar.configuration.security.jwt.JwtService;
import br.com.schoolcalendar.repository.UserRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserRepository userRepository;

	@Bean
	public OncePerRequestFilter authFilter() {
		return new JwtAuthFilter(jwtService, userRepository);
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
		.antMatchers(GET, "/").permitAll()
		.antMatchers("/auth").permitAll()
		.antMatchers(GET, "/student/**").hasAnyRole("ADMIN","TEACHER")
		.antMatchers(POST, "/student/**").hasAnyRole("ADMIN")
		.antMatchers("/teacher/**").hasAnyRole("ADMIN")
		.antMatchers("/institution/**").hasAnyRole("ADMIN")
		.antMatchers(POST, "/clazz/**").hasAnyRole("ADMIN")
		.antMatchers(GET, "/clazz/**").hasAnyRole("ADMIN","TEACHER")
		.antMatchers(POST, "/schedule/**").hasAnyRole("ADMIN","TEACHER")
		.antMatchers(GET, "/schedule/**").permitAll()
		.antMatchers(POST, "/notice/**").hasAnyRole("ADMIN","TEACHER")
		.anyRequest().authenticated()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		super.configure(web);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		// TODO Auto-generated method stub
		return super.userDetailsService();
	}
}
