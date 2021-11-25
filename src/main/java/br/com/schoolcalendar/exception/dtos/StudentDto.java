package br.com.schoolcalendar.exception.dtos;

import org.springframework.data.domain.Page;

import br.com.schoolcalendar.models.Student;
import br.com.schoolcalendar.utils.Utils;

public class StudentDto {

	private String publicId;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private String birthday;
	private AddressDto address;

	public StudentDto(Student student) {
		this.publicId = student.getPublicId();
		this.name = student.getName();
		this.email = student.getEmail();
		this.cpf = student.getCpf();
		this.phone = student.getPhone();
		this.birthday = Utils.dateFormatedToString(student.getBirthday());
		this.address = AddressDto.convetTo(student.getAddress());
	}

	public static StudentDto convertToDto(Student student) {
		return new StudentDto(student);
	}
	public static Page<StudentDto> convertTo(Page<Student> students) {
		return students.map(StudentDto::convertToDto);
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

}
