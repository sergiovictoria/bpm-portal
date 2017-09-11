package br.com.seta.processo.exceptions;

public class HttpStatus5XXException extends HttpStatusException {

	private static final long serialVersionUID = 1L;
	
	public HttpStatus5XXException(int statusCode, String url){
		super(statusCode, url);
	}
	
	public HttpStatus5XXException(String message, int statusCode, String url) {
		super(message, statusCode, url);		
	}
	
	public HttpStatus5XXException(String message, int statusCode, String url, Throwable cause){
		super(message, statusCode, url, cause);
	}
}
