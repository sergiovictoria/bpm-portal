package br.com.seta.processo.dto;

import java.io.Serializable;

import br.com.seta.processo.servicos.PropertiesEmail;

public class Email implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String enderecoEmail;
	private String smtpHost;
	private Integer smtpPort;
	private String userName;
	private String password;
	private String fromMail;
	private String toMail;
	private Boolean ssl;
	private Boolean starTtls; 
	
	public Email(String email) {
		this();
		this.toMail = email;
	}
	
	
	public Email() {
		this.smtpHost = PropertiesEmail.getInstance().smtpHost();
		this.smtpPort = (Integer) PropertiesEmail.getInstance().smtpPort();
		this.userName = PropertiesEmail.getInstance().userName();
        this.password = PropertiesEmail.getInstance().passWord();
        this.fromMail = PropertiesEmail.getInstance().fromMail();
        this.toMail   = PropertiesEmail.getInstance().toMail();
        this.ssl      = PropertiesEmail.getInstance().sslMail();
        this.starTtls = PropertiesEmail.getInstance().starTtlsMail();
	}
	

	public String getEnderecoEmail() {
		return enderecoEmail;
	}

	public void setEnderecoEmail(String enderecoEmail) {
		this.enderecoEmail = enderecoEmail;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public Integer getSmtpPort() {
		return smtpPort;
	}

	public void setSmtpPort(Integer smtpPort) {
		this.smtpPort = smtpPort;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromMail() {
		return fromMail;
	}

	public void setFromMail(String fromMail) {
		this.fromMail = fromMail;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}


	public Boolean getSsl() {
		return ssl;
	}


	public void setSsl(Boolean ssl) {
		this.ssl = ssl;
	}


	public Boolean getStarTtls() {
		return starTtls;
	}


	public void setStarTtls(Boolean starTtls) {
		this.starTtls = starTtls;
	}

	

	@Override
	public String toString() {
		return "Email [enderecoEmail=" + enderecoEmail + ", smtpHost="
				+ smtpHost + ", smtpPort=" + smtpPort + ", userName="
				+ userName + ", password=" + password + ", fromMail="
				+ fromMail + ", toMail=" + toMail + ", ssl=" + ssl
				+ ", starTtls=" + starTtls + "]";
	}

   
	
	
    


}
