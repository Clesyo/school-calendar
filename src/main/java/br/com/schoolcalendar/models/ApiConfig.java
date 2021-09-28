package br.com.schoolcalendar.models;

import javax.persistence.Entity;

@Entity
public class ApiConfig extends BaseEntity {

	private static final long serialVersionUID = -8632484012674266472L;

	private String jwtSecret;
	private Integer jwtExpirationInMinutes;

	public String getJwtSecret() {
		return jwtSecret;
	}

	public void setJwtSecret(String jwtSecret) {
		this.jwtSecret = jwtSecret;
	}

	public Integer getJwtExpirationInMinutes() {
		return jwtExpirationInMinutes;
	}

	public void setJwtExpirationInMinutes(Integer jwtExpirationInMinutes) {
		this.jwtExpirationInMinutes = jwtExpirationInMinutes;
	}

}
