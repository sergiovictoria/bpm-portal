package br.com.seta.processo.exceptions;

public class HttpStatus4XXException extends HttpStatusException{
	
	private static final long serialVersionUID = 1L;
	
	public HttpStatus4XXException(int statusCode, String url){
		super(statusCode, url);
	}
	
	public HttpStatus4XXException(String message, int statusCode, String url) {
		super(message, statusCode, url);		
	}
	
	public HttpStatus4XXException(String message, int statusCode, String url, Throwable cause){
		super(message, statusCode, url, cause);
	}
}
