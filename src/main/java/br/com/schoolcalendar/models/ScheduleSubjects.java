package br.com.schoolcalendar.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.com.schoolcalendar.enums.Weekday;

@Entity
public class ScheduleSubjects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "subject_id")
	private Discipline subjects;
	
	@ManyToOne
	@JoinColumn(name = "class_id")
	private Clazz clazz;
	
	@Enumerated(EnumType.STRING)
	private Weekday day;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Discipline getSubjects() {
		return subjects;
	}

	public void setSubjects(Discipline subjects) {
		this.subjects = subjects;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public Weekday getDay() {
		return day;
	}

	public void setDay(Weekday day) {
		this.day = day;
	}
	
	
}
