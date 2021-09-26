package br.com.schoolcalendar.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.models.Role;

public class Utils {

	public List<Role> convertUserTypeRoles(UserType...types) {
		return Arrays.stream(types).map(type -> new Role(type.name())).collect(Collectors.toList());
	}
}
