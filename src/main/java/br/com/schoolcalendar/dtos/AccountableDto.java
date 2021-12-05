package br.com.schoolcalendar.dtos;

import java.util.List;

import br.com.schoolcalendar.models.Accountable;

public class AccountableDto {

	private String id;
	private String name;

	private String grade;

	private String cpf;

	private String phone;

	private String email;

	public AccountableDto(Accountable accountable) {
		this.id = accountable.getPublicId();
		this.name = accountable.getName();
		this.grade = accountable.getGrade().name();
		this.cpf = accountable.getCpf();
		this.phone = accountable.getPhone();
		this.email = accountable.getEmail();
	}

	public static AccountableDto convertTo(Accountable accountable) {
		return new AccountableDto(accountable);
	}

	public static List<AccountableDto> convertTo(List<Accountable> accountables) {
		return accountables.stream().map(AccountableDto::convertTo).toList();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
