package br.com.seta.processo.exceptions;

public class HttpStatus404Exception extends HttpStatus4XXException{
	private static final long serialVersionUID = 1L;
	
	private static final  int STATUS_CODE_404 = 404;
	
	public HttpStatus404Exception(String url){
		super(STATUS_CODE_404, url);
	}
	
	public HttpStatus404Exception(String message, String url) {
		super(message, STATUS_CODE_404, url);		
	}
	
	public HttpStatus404Exception(String message, String url, Throwable cause){
		super(message, STATUS_CODE_404, url, cause);
	}
}
