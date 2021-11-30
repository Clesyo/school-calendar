package br.com.schoolcalendar.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.schoolcalendar.enums.AdministrativeCategory;
import br.com.schoolcalendar.enums.AdministrativeDependence;
import br.com.schoolcalendar.enums.Localization;

@Entity
public class Institution extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private String phone;

	private String email;

	private String inep;

	private String cnpj;

	@Enumerated(EnumType.STRING)
	private Localization localization;

	@Enumerated(EnumType.STRING)
	private AdministrativeCategory category;

	@Enumerated(EnumType.STRING)
	private AdministrativeDependence dependence;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

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

	public Localization getLocalization() {
		return localization;
	}

	public void setLocalization(Localization localization) {
		this.localization = localization;
	}

	public AdministrativeCategory getCategory() {
		return category;
	}

	public void setCategory(AdministrativeCategory category) {
		this.category = category;
	}

	public AdministrativeDependence getDependence() {
		return dependence;
	}

	public void setDependence(AdministrativeDependence dependence) {
		this.dependence = dependence;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
