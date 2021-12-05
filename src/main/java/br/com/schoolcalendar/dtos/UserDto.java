package br.com.schoolcalendar.dtos;

import br.com.schoolcalendar.models.User;

public class UserDto {

	private String publicId;
	private String name;
	private String email;
	private RoleDto role;

	public UserDto(String publicId, String name, String email, RoleDto role) {
		this.publicId = publicId;
		this.name = name;
		this.email = email;
		this.role = role;
	}

	public static UserDto convertToDto(User user) {
		return new UserDto(user.getPublicId(), user.getName(), user.getEmail(), RoleDto.convertToDto(user.getRole()));
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
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

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}

}
