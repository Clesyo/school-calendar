package br.com.schoolcalendar.exception.dtos;

import br.com.schoolcalendar.models.Address;

public class AddressDto {

	private String zipeCode;
	private String street;
	private String complement;
	private Integer number;
	private String city;
	private String state;
	
	public AddressDto(Address address) {
		super();
		this.zipeCode = address.getZipCode();
		this.street = address.getStreet();
		this.complement = address.getComplement();
		this.number = address.getNumber();
		this.city = address.getCity().getName();
		this.state = address.getCity().getState().getName();
	}
	
	public static AddressDto convetTo(Address address) {
		return new AddressDto(address);
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
