package br.com.schoolcalendar.exception.dtos;

import br.com.schoolcalendar.models.Student;
import br.com.schoolcalendar.utils.Utils;

public class StudentDto {

	private String publicId;
	private String email;
	private String cpf;
	private String phone;
	private String birthday;
	private String zipeCode;
	private String street;
	private String complement;
	private Integer number;
	private String city;
	private String state;

	public StudentDto(Student student) {
		this.publicId = student.getPublicId();
		this.email = student.getEmail();
		this.cpf = student.getCpf();
		this.phone = student.getPhone();
		this.birthday = Utils.dateFormatedToString(student.getBirthday());
		this.zipeCode = student.getAddress().getZipCode();
		this.street = student.getAddress().getStreet();
		this.complement  = student.getAddress().getComplement();
		this.number = student.getAddress().getNumber();
		this.city = student.getAddress().getCity().getName();
		this.state = student.getAddress().getState().getName();
	}

	public static StudentDto convertToDto(Student student) {
		return new StudentDto(student);
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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
