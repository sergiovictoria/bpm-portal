package br.com.seta.processo.exceptions;

public class GenericHttpStatusException extends HttpStatusException{

	private static final long serialVersionUID = 1L;
	
	public GenericHttpStatusException(int statusCode, String url){
		super(statusCode, url);
	}
	
	public GenericHttpStatusException(String message, int statusCode, String url) {
		super(message, statusCode, url);		
	}
	
	public GenericHttpStatusException(String message, int statusCode, String url, Throwable cause){
		super(message, statusCode, url, cause);
	}

}
