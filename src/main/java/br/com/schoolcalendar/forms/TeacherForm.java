package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import br.com.schoolcalendar.models.Address;
import br.com.schoolcalendar.models.Teacher;
import br.com.schoolcalendar.utils.Utils;
import br.com.schoolcalendar.validator.ValidPassword;

public class TeacherForm {

	@NotBlank(message = "Nome não pode ser vazio.")
	private String name;

	@NotBlank(message = "CPF não pode ser vazio.")
	private String cpf;

	@NotBlank(message = "Email não pode ser vazio.")
	private String email;

	@NotBlank(message = "Telefone não pode ser vazio.")
	private String phone;

	@NotBlank(message = "Senha não pode ser vazio.")
	@ValidPassword
	private String password;

	@NotNull(message = "Número não poder vazio.")
	private Integer number;

	@NotBlank(message = "Logradouro não pode ser vazio.")
	private String street;

	private String complement;

	@NotBlank(message = "CEP não pode ser vazio.")
	private String zipCode;

	@NotBlank(message = "Bairro não pode ser vazio.")
	private String district;

	@NotNull(message = "Codigo do IBGE não pode ser vazio.")
	private Integer ibgeCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		if (StringUtils.isEmpty(cpf)) {
			return "";
		}
		return Utils.validCpf(cpf);
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		if (StringUtils.isEmpty(phone)) {
			return "";
		}
		return Utils.validPhone(phone);
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getIbgeCode() {
		return ibgeCode;
	}

	public void setIbgeCode(Integer ibgeCode) {
		this.ibgeCode = ibgeCode;
	}

	public Teacher toTeacher(Teacher...teachers) {
		Teacher teacher = new Teacher();
		Address address = new Address();
		List<Teacher> list = Arrays.asList(teachers);
		if(!list.isEmpty())
			teacher = list.get(0);
			address = list.get(0).getAddress();
		
		teacher.setName(name);
		teacher.setCpf(getCpf());
		teacher.setEmail(email);
		teacher.setPhone(getPhone());
		address.setZipCode(zipCode);
		address.setStreet(street);
		address.setNumber(number);
		address.setDistrict(district);
		address.setComplement(complement);
		teacher.setAddress(address);
		return teacher;
	}
}