package br.com.schoolcalendar.dtos;

import java.util.List;

import br.com.schoolcalendar.models.Clazz;

public class ClazzDto {

	private String description;

	private String title;

	public ClazzDto(Clazz clazz) {
		this.description = clazz.getDescription();
		this.title = clazz.getTitle();
	}

	public static ClazzDto convertTo(Clazz clazz) {
		return new ClazzDto(clazz);
	}
	
	public static List<ClazzDto> convertTo(List<Clazz> clazzs) {
		return clazzs.stream().map(ClazzDto::convertTo).toList();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
