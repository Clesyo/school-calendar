package br.com.schoolcalendar.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.schoolcalendar.enums.AccountableGrade;

@Entity
public class Accountable extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String name;

	private AccountableGrade grade;

	private String cpf;

	private String phone;

	private String email;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

	
	public Accountable() {
	}

	public Accountable(String name, AccountableGrade grade, String cpf, String phone, String email) {
		this.name = name;
		this.grade = grade;
		this.cpf = cpf;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AccountableGrade getGrade() {
		return grade;
	}

	public void setGrade(AccountableGrade grade) {
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

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
