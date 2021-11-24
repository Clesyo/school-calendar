package br.com.schoolcalendar.exception.dtos;

import org.springframework.data.domain.Page;

import br.com.schoolcalendar.models.Teacher;

public class TeacherDto {

	private String publicId;
	private String name;
	private String email;
	private String cpf;
	private String phone;
	private AddressDto address;

	public TeacherDto(Teacher teacher) {
		this.publicId = teacher.getPublicId();
		this.name = teacher.getName();
		this.email = teacher.getEmail();
		this.cpf = teacher.getCpf();
		this.phone = teacher.getPhone();
		this.address = AddressDto.convetTo(teacher.getAddress());
	}

	public static TeacherDto convertTo(Teacher teacher) {
		return new TeacherDto(teacher);
	}

	public static Page<TeacherDto> convertTo(Page<Teacher> teachers) {
		return teachers.map(TeacherDto::convertTo);
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

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

}