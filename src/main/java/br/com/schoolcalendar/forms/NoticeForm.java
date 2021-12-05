package br.com.schoolcalendar.forms;

import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.schoolcalendar.enums.NoticeType;
import br.com.schoolcalendar.models.Notice;

public class NoticeForm {

	@NotBlank(message = "Texto não pode ser vazio.")
	private String text;
	
	@NotNull(message = "ID do aluno não pode ser vazio.")
	private Long studentId;

	@NotBlank(message = "Tipo não pode ser vazio.")
	private String type;

	public Notice toNotice(Notice...notices) {
		Notice notice = new Notice();
		List<Notice> list = Arrays.asList(notices);
		if(!list.isEmpty())
			notice = list.get(0);
		
		notice.setText(text);
		notice.setType(NoticeType.valueOf(type));
		return notice;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
