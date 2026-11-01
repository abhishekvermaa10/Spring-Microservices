package com.abhishekvermaa10.enums;

/**
 * @author abhishekvermaa10
 */
public enum MailType {
	
	WELCOME("Welcome to Petistaan", "welcome.ftlh"),
	MODIFY("Your data in Petistaan has been modified", "modify.ftlh"),
	EXIT("Thanks for visiting Petistaan", "exit.ftlh");
	
	private String subject;
	private String templateFileName;
	
	MailType(String subject, String templateFileName) {
		this.subject = subject;
		this.templateFileName = templateFileName;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getTemplateFileName() {
		return this.templateFileName;
	}

}
