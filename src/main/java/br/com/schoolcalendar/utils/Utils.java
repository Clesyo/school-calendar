package br.com.schoolcalendar.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import br.com.schoolcalendar.SchoolCaplendarApiContext;
import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.models.Role;

@Component
public abstract class Utils {
	
	private static SchoolCaplendarApiContext context;

	public static List<Role> convertUserTypeRoles(UserType...types) {
		return Arrays.stream(types).map(type -> new Role(type.name())).collect(Collectors.toList());
	}
	
	public static String generateRandomString(Integer length) {
    	return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }

	public SchoolCaplendarApiContext getContext() {
		return context;
	}

	public static void setContext(SchoolCaplendarApiContext context) {
		Utils.context = context;
	}
	
	
}
