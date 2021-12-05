package br.com.schoolcalendar.dtos;

import java.util.Date;

import br.com.schoolcalendar.models.Notice;

public class NoticeDto {

	private String student;
	private Date createdAt;
	private String text;
	private String type;

	public NoticeDto(Notice notice) {
		this.student = notice.getStudent().getName();
		this.text = notice.getText();
		this.createdAt = notice.getCreatedAt();
		this.type = notice.getType().name();
	}

	public static NoticeDto convertoTo(Notice notice) {
		return new NoticeDto(notice);
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
