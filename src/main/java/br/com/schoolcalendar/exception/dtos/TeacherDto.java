package br.com.schoolcalendar.exception.dtos;

import br.com.schoolcalendar.models.Teacher;

public class TeacherDto {

	private String publicId;
	private String email;
	private String cpf;
	private String phone;
	private String zipeCode;
	private String street;
	private String complement;
	private Integer number;
	private String city;
	private String state;

	public TeacherDto(Teacher teacher) {
		this.publicId = teacher.getPublicId();
		this.email = teacher.getEmail();
		this.cpf = teacher.getCpf();
		this.phone = teacher.getPhone();
		this.zipeCode = teacher.getAddress().getZipCode();
		this.street = teacher.getAddress().getStreet();
		this.complement = teacher.getAddress().getComplement();
		this.number = teacher.getAddress().getNumber();
		this.city = teacher.getAddress().getCity().getName();
		this.state = teacher.getAddress().getState().getName();
	}

	public static TeacherDto convertTo(Teacher teacher) {
		return new TeacherDto(teacher);
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipeCode() {
		return zipeCode;
	}

	public void setZipeCode(String zipeCode) {
		this.zipeCode = zipeCode;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
