package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import br.com.schoolcalendar.enums.AdministrativeCategory;
import br.com.schoolcalendar.enums.AdministrativeDependence;
import br.com.schoolcalendar.enums.Localization;
import br.com.schoolcalendar.models.Institution;
import br.com.schoolcalendar.utils.Utils;

public class InstitutionForm {

	@NotBlank(message = "Nome não pode ser vazio.")
	private String name;

	@NotBlank(message = "Telefone não pode ser vazio.")
	private String phone;

	@NotBlank(message = "Email não pode ser vazio.")
	private String email;

	@NotBlank(message = "Codigo INEP não pode ser vazio.")
	private String inep;
	
	@NotBlank(message = "Codigo INEP não pode ser vazio.")
	private String cnpj;

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

	@NotNull(message = "Localização não pode ser vazio.")
	private String localization;

	@NotNull(message = "Categoria não pode ser vazio.")
	private String category;

	@NotNull(message = "Dependência não pode ser vazio.")
	private String dependence;

	public Institution toInstitution(Institution... institutions) {
		// TODO Auto-generated method stub
		Institution institution = new Institution();
		List<Institution> list = Arrays.asList(institutions);

		if (!list.isEmpty())
			institution = list.get(0);

		institution.setName(name);
		institution.setEmail(email);
		institution.setPhone(getPhone());
		institution.setInep(inep);
		institution.setCnpj(cnpj);
		institution.setLocalization(Localization.valueOf(localization));
		institution.setCategory(AdministrativeCategory.valueOf(category));
		institution.setDependence(AdministrativeDependence.valueOf(dependence));

		return institution;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getLocalization() {
		return localization;
	}

	public void setLocalization(String localization) {
		this.localization = localization;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDependence() {
		return dependence;
	}

	public void setDependence(String dependence) {
		this.dependence = dependence;
	}
}
