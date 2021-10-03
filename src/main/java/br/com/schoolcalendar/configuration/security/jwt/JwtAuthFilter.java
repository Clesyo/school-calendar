package br.com.schoolcalendar.configuration.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.schoolcalendar.configuration.security.auth.AuthenticationService;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;

	private AuthenticationService authenticationService;

	public JwtAuthFilter(JwtService jwtService, AuthenticationService authenticationService) {
		this.jwtService = jwtService;
		this.authenticationService = authenticationService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authorization = request.getHeader("Authorization");

		if (authorization != null && authorization.startsWith("Bearer")) {
			String token = authorization.replace("Bearer ", "");

			boolean isTokenValid = jwtService.tokenValid(token);

			if (isTokenValid) {
				String loginUser = jwtService.obtainLoginUser(token);
				UserDetails user = authenticationService.loadUserByUsername(loginUser);
				UsernamePasswordAuthenticationToken userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());

				userPasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(userPasswordAuthenticationToken);
			}
		}
		filterChain.doFilter(request, response);
	}
}
