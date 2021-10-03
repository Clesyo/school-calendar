package br.com.schoolcalendar.exception.dtos;

import java.util.List;
import java.util.stream.Collectors;

import br.com.schoolcalendar.models.User;

public class UserDto {

	private String publicId;
	private String name;
	private String email;
	private List<RoleDto> roles;

	public UserDto(String publicId, String name, String email, List<RoleDto> role) {
		this.publicId = publicId;
		this.name = name;
		this.email = email;
		this.roles = role;
	}

	public static UserDto convertToDto( User user) {
		List<RoleDto> roleDtos = user.getRoles().stream().map(RoleDto::convertToDto).collect(Collectors.toList());
		return new UserDto(user.getPublicId(), user.getName(), user.getEmail(), roleDtos);
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

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

}
