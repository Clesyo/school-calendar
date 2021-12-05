package br.com.schoolcalendar.dtos;

import br.com.schoolcalendar.models.ScheduleDiscipline;

public class ScheduleDisciplineDto {

	private String clazz;

	private String discipline;

	private String day;

	public ScheduleDisciplineDto(ScheduleDiscipline scheduleDiscipline) {
		this.clazz = scheduleDiscipline.getClazz().getTitle();
		this.discipline = scheduleDiscipline.getDiscipline().getName();
		this.day = scheduleDiscipline.getDay().name();
	}

	public static ScheduleDisciplineDto convertTo(ScheduleDiscipline scheduleDiscipline) {
		// TODO Auto-generated method stub
		return new ScheduleDisciplineDto(scheduleDiscipline);
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
