package br.com.seta.processo.exceptions;

/**
 * Representa um erro de usuário não autorizadO a acessar um recurso via o protocolo HTTP
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class HttpStatus401Exception extends HttpStatus4XXException {
	
	private static final long serialVersionUID = 1L;
	
	private static final  int STATUS_CODE_401 = 401;
	
	public HttpStatus401Exception(String url){
		super(STATUS_CODE_401, url);
	}
	
	public HttpStatus401Exception(String message, String url) {
		super(message, STATUS_CODE_401, url);		
	}
	
	public HttpStatus401Exception(String message, String url, Throwable cause){
		super(message, STATUS_CODE_401, url, cause);
	}
}
