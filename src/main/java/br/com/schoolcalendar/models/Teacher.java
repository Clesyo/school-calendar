package br.com.schoolcalendar.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import br.com.schoolcalendar.utils.Utils;

@Entity
public class Teacher extends BaseEntity {

	private static final long serialVersionUID = 2500553214100508739L;

	private String name;
	private String cpf;
	private String phone;
	private String registration;
	private String email;

	private String searchQuery;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	@Override
	@PrePersist
	protected void prePersiste() {
		super.prePersiste();
		concatenateSearchQuery();
	}

	@PreUpdate
	protected void preUpdate() {
		concatenateSearchQuery();
	}

	@PostPersist
	protected void postPersist() {
		generateRegistration();
	}

	public void concatenateSearchQuery() {
		String query = "";

		query += this.cpf != null ? this.cpf : "";
		query += this.name != null ? this.name : "";
		query += this.registration != null ? this.registration : "";

		this.setSearchQuery(Utils.normalizeToQuery(query));
	}

	public void generateRegistration() {
		String registration = "P-";

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
