package br.com.schoolcalendar.exception.dtos;

import br.com.schoolcalendar.models.Role;

public class RoleDto {

	private String name;

	public RoleDto(String name) {
		this.name = name;
	}

	public static RoleDto convertToDto(Role role) {
		return new RoleDto(role.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
