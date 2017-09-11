package br.com.seta.processo.dto;

import java.util.List;

public class EmailMessage {
	
	
	private List<String> to;
	private List<String> cc;
	private String subject;
	private String msg;
	
	
	
	public EmailMessage() {
		super();
	}

	public EmailMessage(List<String> to, String subject, String msg) {
		super();
		this.to = to;
		this.subject = subject;
		this.msg = msg;
	}

	public EmailMessage(List<String> to, List<String> cc, String subject,
			String msg) {
		super();
		this.to = to;
		this.cc = cc;
		this.subject = subject;
		this.msg = msg;
	}
	
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
		this.cc = cc;
	}

	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	
	
	

}
