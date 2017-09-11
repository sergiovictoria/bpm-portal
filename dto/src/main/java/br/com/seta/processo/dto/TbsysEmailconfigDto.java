package br.com.seta.processo.dto;


public class TbsysEmailconfigDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idemailconfig;
	private String descricao;
	private String smtp;
	private int smtpport;
	private String user;
	private String from;
	private String password;
	private String charset;
	private boolean sslconnect;
	private String urlimageheader;
	private String urlimagefooter;
	
	public TbsysEmailconfigDto() {
	}

	public int getIdemailconfig() {
		return idemailconfig;
	}

	public void setIdemailconfig(int idemailconfig) {
		this.idemailconfig = idemailconfig;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSmtp() {
		return smtp;
	}

	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	public int getSmtpport() {
		return smtpport;
	}

	public void setSmtpport(int smtpport) {
		this.smtpport = smtpport;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public boolean isSslconnect() {
		return sslconnect;
	}

	public void setSslconnect(boolean sslconnect) {
		this.sslconnect = sslconnect;
	}

	public String getUrlimageheader() {
		return urlimageheader;
	}

	public void setUrlimageheader(String urlimageheader) {
		this.urlimageheader = urlimageheader;
	}

	public String getUrlimagefooter() {
		return urlimagefooter;
	}

	public void setUrlimagefooter(String urlimagefooter) {
		this.urlimagefooter = urlimagefooter;
	}
	
}
