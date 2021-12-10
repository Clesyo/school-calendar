package br.com.schoolcalendar.dtos;

import java.util.Date;
import java.util.List;

import br.com.schoolcalendar.models.Notice;

public class NoticeDto {

	private Long noticeId;
	private Date createdAt;
	private String message;
	private String type;
	private String status;
	private String actionMessage;
	private String url;
	

	public NoticeDto(Notice notice) {
		this.message = notice.getText();
		this.createdAt = notice.getCreatedAt();
		this.type = notice.getType().name();
		this.noticeId = notice.getId();
		this.status = notice.getStatus().name();
		this.url = "/notice/".concat(notice.getId().toString());
		this.actionMessage = "Clique aqui";
	}

	public static NoticeDto convertoTo(Notice notice) {
		return new NoticeDto(notice);
	}
	
	public static List<NoticeDto> convertoTo(List<Notice> notices) {
		return notices.stream().map(NoticeDto::convertoTo).toList();
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String text) {
		this.message = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getActionMessage() {
		return actionMessage;
	}

	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}
}
