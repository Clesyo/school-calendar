package br.com.schoolcalendar.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.schoolcalendar.utils.Utils;

@Entity
public class Student extends BaseEntity {

	private static final long serialVersionUID = 3623929688536238498L;

	private String name;

	private LocalDate birthday;

	@Column(unique = true)
	private String cpf;

	private String email;

	private String phone;

	@Column(unique = true)
	private String registration;

	private String searchQuery;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "student")
	private List<Notice> notices;
	
	@OneToMany(mappedBy = "student")
	private List<Accountable> accountables;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name ="class_id")
	private Clazz clazz;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	@PrePersist
	protected void prePersiste() {
		super.prePersiste();
		concatenateSearchQuery();
	}

	public void concatenateSearchQuery() {
		String query = "";

		query += this.cpf != null ? this.cpf : "";
		query += this.name != null ? this.name : "";
		query += this.registration != null ? this.registration : "";

		this.setSearchQuery(Utils.normalizeToQuery(query));
	}

	@PostPersist
	protected void postPersist() {
		generateRegistration();
	}

	@PreUpdate
	protected void preUpdate() {
		generateRegistration();
	}

	public void generateRegistration() {
		String registration = "A-";

		String id = String.valueOf(this.getId());

		registration += String.valueOf(LocalDate.now().getYear());
		registration += this.cpf != null ? this.cpf.substring(0, 3) : "";

		if (id.length() == 1) {
			registration += "000";
		}
		if (id.length() == 2) {
			registration += "00";
		}
		if (id.length() == 3) {
			registration += "0";
		}

		registration += id;
		this.setRegistration(registration);
	}
}
