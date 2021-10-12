package br.com.schoolcalendar.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer ibgeCode;

	@ManyToOne
	@JoinColumn(name = "state_id")
	private State state;
	
	@OneToOne(mappedBy = "address")
	private Student student;

	public City(String name, Integer ibgeCode, State state) {
		this.name = name;
		this.ibgeCode = ibgeCode;
		this.state = state;
	}

	public City() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIbgeCode() {
		return ibgeCode;
	}

	public void setIbgeCode(Integer ibgeCode) {
		this.ibgeCode = ibgeCode;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
