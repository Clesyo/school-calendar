package br.com.schoolcalendar.exception.dtos;

import br.com.schoolcalendar.models.Institution;

public class InstitutionDto {

	private String publicId;

	private String name;

	private String phone;

	private String email;

	private String inep;

	private String cnpj;

	private AddressDto address;

	public InstitutionDto(Institution institution) {
		this.publicId = institution.getPublicId();
		this.name = institution.getName();
		this.phone = institution.getPhone();
		this.email = institution.getEmail();
		this.inep = institution.getInep();
		this.cnpj = institution.getCnpj();
		this.address = AddressDto.convetTo(institution.getAddress());
	}

	public static InstitutionDto convertTo(Institution institution) {
		return new InstitutionDto(institution);
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getInep() {
		return inep;
	}

	public void setInep(String inep) {
		this.inep = inep;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
	}

}
