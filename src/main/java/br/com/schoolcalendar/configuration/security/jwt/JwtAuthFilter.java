package br.com.schoolcalendar.configuration.security.jwt;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.schoolcalendar.models.User;
import br.com.schoolcalendar.repository.UserRepository;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;

	private UserRepository userRepository;

	public JwtAuthFilter(JwtService jwtService, UserRepository userRepository) {
		this.jwtService = jwtService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		boolean authenticationRequest = isPublicResource(request);
		if (!authenticationRequest) {
			validateToken(request);
		}

		filterChain.doFilter(request, response);
	}

	public void authenticate(String token,HttpServletRequest request ) {
		String login = jwtService.obtainLoginUser(token);
		User user = userRepository.findByEmail(login).get();
		UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				user, null, user.getAuthorities());
		userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
	}

	private String getToken(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");

		if (!StringUtils.hasLength(authorization) && !authorization.startsWith("Bearer")) {
			return null;
		}
		return authorization.replace("Bearer ", "");
	}

	private void validateToken(HttpServletRequest request) {
		String token = getToken(request);
		boolean valid = jwtService.tokenValid(token);
		if (valid) {
			authenticate(token, request);
		}
	}

	private boolean isPublicResource(HttpServletRequest request) {
		List<String> publicResources = Arrays.asList("auth", "token", "pr", "sn");
		String url = request.getRequestURL().toString();
		Pattern pattern = Pattern.compile("/([^/]+)/?$");
		Matcher m = pattern.matcher(url);
		if (m.find()) {
			String resource = m.group(1);
			if (publicResources.contains(resource)) {
				return true;
			}
		}
		return false;
	}
}
