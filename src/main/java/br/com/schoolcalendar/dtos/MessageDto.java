package br.com.schoolcalendar.dtos;

public class MessageDto {

	private String message;

	public MessageDto(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
