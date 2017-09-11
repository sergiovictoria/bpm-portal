package br.com.seta.processo.utils;

import java.io.Serializable;

import br.com.seta.processo.servicos.PropertiesEmail;
import br.com.seta.processo.servicos.PropertiesDtoUtils;

public class RemoteEmail implements Serializable {

	private static final long serialVersionUID = 1L;
	private static PropertiesEmail propertiesemail;

	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static PropertiesEmail getInstance(){
		if (propertiesemail == null){
			propertiesemail = new PropertiesEmail();
		}
		return propertiesemail;
	}

	public String smtpHost( ) {
		String SMTPHost = "smtp.setaatacadista.com.br";
		try {
			SMTPHost = (String) PropertiesDtoUtils.getInstance().getValues("SMTPHost");           
		}catch (Exception e) {
		}
		return SMTPHost;
	}

	public Integer smtpPort( ) {
		Integer SMTPPort = new Integer(587);
		try {
			SMTPPort = new Integer(PropertiesDtoUtils.getInstance().getValues("SMTPPort"));           
		}catch (Exception e) {
		}
		return SMTPPort;
	}
	
	public String userName( ) {
		 String USERname = new String("atendimento.csc@setaatacadista.com.br");
		try {
			USERname = new String(PropertiesDtoUtils.getInstance().getValues("USERname"));           
		}catch (Exception e) {
		}
		return USERname;
	}
	
	public String passWord( ) {
		 String PASSWord = new String("seta5645");
		try {
			PASSWord = new String(PropertiesDtoUtils.getInstance().getValues("PASSWord"));           
		}catch (Exception e) {
		}
		return PASSWord;
	}
	
	public String fromMail( ) {
		String FROMmail = new String("processos@setaatacadista.com.br");
		try {
			FROMmail = new String(PropertiesDtoUtils.getInstance().getValues("FROMmail"));           
		}catch (Exception e) {
		}
		return FROMmail;
	}
	
	public String toMail( ) {
		String TOmail = new String("processos@setaatacadista.com.br");
		try {
			TOmail = new String(PropertiesDtoUtils.getInstance().getValues("TOmail"));           
		}catch (Exception e) {
		}
		return TOmail;
	}
	
	public Boolean sslMail( ) {
		boolean sslMail = Boolean.FALSE;
		try {
			String ssl = new String(PropertiesDtoUtils.getInstance().getValues("sslMail")); 
			if (ssl.equals("true")) {
				sslMail = Boolean.TRUE;
			}else {
				sslMail = Boolean.FALSE;
			}
		}catch (Exception e) {
		}
		return sslMail;
	}
	
	public Boolean starTtlsMail( ) {
		boolean starTtlsMail = Boolean.FALSE;
		try {
			String starTtlsmail = new String(PropertiesDtoUtils.getInstance().getValues("starTtlsMail")); 
			if (starTtlsmail.equals("true")) {
				starTtlsMail = Boolean.TRUE;
			}else {
				starTtlsMail = Boolean.FALSE;
			}
		}catch (Exception e) {
		}
		return starTtlsMail;
	}
	
	
}
