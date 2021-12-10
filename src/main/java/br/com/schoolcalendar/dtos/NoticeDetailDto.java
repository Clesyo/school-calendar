package br.com.schoolcalendar.dtos;

import java.util.List;

import br.com.schoolcalendar.models.Notice;
import br.com.schoolcalendar.models.Student;

public class NoticeDetailDto {

	private String publicId;
	private String student;
	private List<NoticeDto> notices;

	public static NoticeDetailDto convert(Student student, List<Notice> notices) {
		NoticeDetailDto dto = new NoticeDetailDto();

		List<NoticeDto> list = notices.stream().map(NoticeDto::convertoTo).toList();
		dto.setStudent(student.getName());
		dto.setPublicId(student.getPublicId());
		dto.setNotices(list);
		return dto;
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getStudent() {
		return student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public List<NoticeDto> getNotices() {
		return notices;
	}

	public void setNotices(List<NoticeDto> notices) {
		this.notices = notices;
	}
}
