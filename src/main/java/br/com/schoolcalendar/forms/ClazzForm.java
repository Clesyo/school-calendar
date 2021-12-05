package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import br.com.schoolcalendar.models.Clazz;

public class ClazzForm {

	@NotBlank(message = "Descrição não pode ser vazio.")
	private String description;

	@NotBlank(message = "Titulo não pode ser vazio.")
	private String title;

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

	public Clazz toClazz(Clazz... clazzs) {

		Clazz clazz = new Clazz();
		List<Clazz> list = Arrays.asList(clazzs);
		if (!list.isEmpty())
			clazz = list.get(0);

		clazz.setDescription(description);
		clazz.setTitle(title);
		return clazz;
	}
}
