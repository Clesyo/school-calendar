package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.schoolcalendar.enums.AccountableGrade;
import br.com.schoolcalendar.models.Accountable;
import br.com.schoolcalendar.models.Student;

public class AccountableForm {

	@NotBlank(message = "Nome não pode ser vazio.")
	private String name;

	@NotBlank(message = "Grau não pode ser vazio.")
	private String grade;

	@NotBlank(message = "CPF não pode ser vazio.")
	private String cpf;

	@NotBlank(message = "Telefone não pode ser vazio.")
	private String phone;

	@NotBlank(message = "Email não pode ser vazio.")
	private String email;

	public Accountable toAccountable(Student  student, Accountable... accountables) {
		Accountable accountable = new Accountable();
		List<Accountable> list = Arrays.asList(accountables);
		if (!list.isEmpty())
			accountable = list.get(0);

		accountable.setName(name);
		accountable.setCpf(cpf);
		accountable.setEmail(email);
		accountable.setPhone(phone);
		accountable.setGrade(AccountableGrade.valueOf(grade));
		accountable.setStudent(student);
		return accountable;
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
