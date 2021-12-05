package br.com.schoolcalendar.enums;

public enum NoticeType {
	RELATO("REPORT"), AVISO("NOTICE"), OBSERVACAO("NOTE"), RECLAMACAO("COMPLAINT"), INFORMATIVO("INFORMATION");

	private String description;

	private NoticeType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
