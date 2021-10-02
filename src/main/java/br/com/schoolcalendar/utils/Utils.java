package br.com.schoolcalendar.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.com.schoolcalendar.SchoolCaplendarApiContext;
import br.com.schoolcalendar.enums.UserType;
import br.com.schoolcalendar.models.Role;

@Component
public abstract class Utils {

	private static SchoolCaplendarApiContext context;

	public static List<Role> convertUserTypeRoles(UserType... types) {
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

	public String validPhone(String phone) {
		return phone.replaceAll("[\\s()-]", "");
	}

	public static boolean isPhoneNumberValid(String phoneNumber) {

		String processedPhone = phoneNumber.replaceAll("[\\s()-]", "");

		// verifica se tem a qtde correta de numeros
		if (processedPhone.length() != 11)
			return false;

		// Se tiver 11 caracteres, verificar se começa com 9 o celular
		if (Integer.parseInt(processedPhone.substring(2, 3)) != 9)
			return false;

		String last8Numbers = processedPhone.substring(3, 11);

		if (last8Numbers.equals("99999999") || last8Numbers.equals("88888888") || last8Numbers.equals("77777777")
				|| last8Numbers.equals("66666666") || last8Numbers.equals("55555555") || last8Numbers.equals("44444444")
				|| last8Numbers.equals("33333333") || last8Numbers.equals("22222222")
				|| last8Numbers.equals("11111111"))
			return false;

		// DDDs validos
		List<String> dddCodes = Arrays.asList("11", "12", "13", "14", "15", "16", "17", "18", "19", "21", "22", "24",
				"27", "28", "31", "32", "33", "34", "35", "37", "38", "41", "42", "43", "44", "45", "46", "47", "48",
				"49", "51", "53", "54", "55", "61", "62", "64", "63", "65", "66", "67", "68", "69", "71", "73", "74",
				"75", "77", "79", "81", "82", "83", "84", "85", "86", "87", "88", "89", "91", "92", "93", "94", "95",
				"96", "97", "98", "99");

		// verifica se o DDD é valido
		if (!dddCodes.contains(processedPhone.substring(0, 2)))
			return false;

		// se passar por todas as validações acima, o formato está correto.
		return true;
	}
	
	public static boolean isCpfValido(String xCPF) {
        try {

            if (StringUtils.isEmpty(xCPF)){
                return false;
            }

            if (hasAllRepeatedDigits(xCPF)){
                return false;

            }

            if (xCPF.length() < 11){
                return false;
            }

            int d1, d4, xx, nCount, resto, digito1, digito2;
            String Check;
            String Separadores = "/-.";
            d1 = 0;
            d4 = 0;
            xx = 1;
            for (nCount = 0; nCount < xCPF.length() - 2; nCount++) {
                String s_aux = xCPF.substring(nCount, nCount + 1);
                if (Separadores.indexOf(s_aux) == -1) {
                    d1 = d1 + (11 - xx) * Integer.valueOf(s_aux).intValue();
                    d4 = d4 + (12 - xx) * Integer.valueOf(s_aux).intValue();
                    xx++;
                }
            }
            resto = (d1 % 11);
            if (resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }

            d4 = d4 + 2 * digito1;
            resto = (d4 % 11);
            if (resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }

            Check = String.valueOf(digito1) + String.valueOf(digito2);
            String s_aux2 = xCPF.substring(xCPF.length() - 2, xCPF.length());

            if (s_aux2.compareTo(Check) != 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
	
	private static boolean hasAllRepeatedDigits(String xcpf) {
        String cpf=xcpf.replace(".", "").replace("-", "");
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                return false;
            }
        }
        return true;
    }
	
	public static boolean isEmailValid(String email) {
        if (StringUtils.isEmpty(email)) return false;
        
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }	
}
