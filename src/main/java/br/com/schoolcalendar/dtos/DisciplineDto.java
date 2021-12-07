package br.com.schoolcalendar.dtos;

import br.com.schoolcalendar.models.Discipline;

public class DisciplineDto {

	private String name;

	public DisciplineDto(Discipline discipline) {
		this.name = discipline.getName();
	}

	public static DisciplineDto convetTo(Discipline discipline) {
		return new DisciplineDto(discipline);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
