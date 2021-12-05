package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.schoolcalendar.enums.Weekday;
import br.com.schoolcalendar.models.ScheduleDiscipline;

public class ScheduleDisciplineForm {

	@NotNull(message = "DisciplineId não pode ser vazio.")
	private Long disciplineId;

	@NotNull(message = "ClazzId não pode ser vazio.")
	private Long clazzId;

	@NotBlank(message = "Day não pode ser vazio.")
	private String day;

	public ScheduleDiscipline toScheduleDiscipline(ScheduleDiscipline... scheduleDisciplines) {

		ScheduleDiscipline discipline = new ScheduleDiscipline();
		List<ScheduleDiscipline> list = Arrays.asList(scheduleDisciplines);
		if (!list.isEmpty())
			discipline = list.get(0);

		discipline.setDay(Weekday.valueOf(day));
		return discipline;
	}

	public Long getDisciplineId() {
		return disciplineId;
	}

	public void setDisciplineId(Long disciplineId) {
		this.disciplineId = disciplineId;
	}

	public Long getClazzId() {
		return clazzId;
	}

	public void setClazzId(Long clazzId) {
		this.clazzId = clazzId;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
