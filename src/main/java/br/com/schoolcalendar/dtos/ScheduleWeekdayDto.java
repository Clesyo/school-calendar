package br.com.schoolcalendar.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.schoolcalendar.models.Clazz;

public class ScheduleWeekdayDto {

	private String clazz;
	private List<DisciplineDto> monday;
	private List<DisciplineDto> tuesday;
	private List<DisciplineDto> wednesday;
	private List<DisciplineDto> thursday;
	private List<DisciplineDto> friday;

	public ScheduleWeekdayDto() {
		this.monday = new ArrayList<>();
		this.tuesday = new ArrayList<>();
		this.wednesday = new ArrayList<>();
		this.thursday = new ArrayList<>();
		this.friday = new ArrayList<>();
	}

	public static ScheduleWeekdayDto buildScheduleWeekday(Clazz clazz) {
		ScheduleWeekdayDto dto = new ScheduleWeekdayDto();
		dto.setClazz(clazz.getDescription());
		
		clazz.getSchedules().forEach(discipline -> {
			switch (discipline.getDay()) {
			case SEGUNDA:
				dto.monday.add(DisciplineDto.convetTo(discipline.getDiscipline()));
				break;
			case TERCA:
				dto.tuesday.add(DisciplineDto.convetTo(discipline.getDiscipline()));
				break;
			case QUARTA:
				dto.wednesday.add(DisciplineDto.convetTo(discipline.getDiscipline()));
				break;
			case QUINTA:
				dto.thursday.add(DisciplineDto.convetTo(discipline.getDiscipline()));
				break;
			case SEXTA:
				dto.friday.add(DisciplineDto.convetTo(discipline.getDiscipline()));
				break;

			default:
				break;
			}
		});
		return dto;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<DisciplineDto> getMonday() {
		return monday;
	}

	public void setMonday(List<DisciplineDto> monday) {
		this.monday = monday;
	}

	public List<DisciplineDto> getTuesday() {
		return tuesday;
	}

	public void setTuesday(List<DisciplineDto> tuesday) {
		this.tuesday = tuesday;
	}

	public List<DisciplineDto> getWednesday() {
		return wednesday;
	}

	public void setWednesday(List<DisciplineDto> wednesday) {
		this.wednesday = wednesday;
	}

	public List<DisciplineDto> getThursday() {
		return thursday;
	}

	public void setThursday(List<DisciplineDto> thursday) {
		this.thursday = thursday;
	}

	public List<DisciplineDto> getFriday() {
		return friday;
	}

	public void setFriday(List<DisciplineDto> friday) {
		this.friday = friday;
	}

}
