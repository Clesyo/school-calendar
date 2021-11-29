package br.com.schoolcalendar.configuration.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.schoolcalendar.SchoolCalendarApiContext;
import br.com.schoolcalendar.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Autowired
	private SchoolCalendarApiContext context;

	public String generateToken(UserDetails user) {
		Long expiracao = Long.valueOf(context.getApiConfig().getJwtExpirationInMinutes());
		LocalDateTime horaExpiracao = LocalDateTime.now().plusMinutes(expiracao);
		Instant instant = horaExpiracao.atZone(ZoneId.systemDefault()).toInstant();
		Date date = Date.from(instant);

		return Jwts.builder().setIssuer("School Calendar API").setSubject(user.getUsername()).setExpiration(date)
				.setIssuedAt(new Date()).signWith(SignatureAlgorithm.HS512, context.getApiConfig().getJwtSecret())
				.compact();
	}

	private Claims obtainClaims(String token) throws ExpiredJwtException {
		return Jwts.parser().setSigningKey(context.getApiConfig().getJwtSecret()).parseClaimsJws(token).getBody();
	}

	public String obtainLoginUser(String token) throws ExpiredJwtException {
		return (String) obtainClaims(token).getSubject();
	}

	public boolean tokenValid(String token) {
		try {
			Claims claims = obtainClaims(token);
			Date dateExpiraton = claims.getExpiration();
			LocalDateTime localDateTime = dateExpiraton.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			return !LocalDateTime.now().isAfter(localDateTime);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public String generateToken(Authentication authenticate) {
		User user = (User) authenticate.getPrincipal();
		return generateToken(user);
	}
}
