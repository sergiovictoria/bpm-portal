package br.com.seta.processo.exceptions;

public abstract class HttpStatusException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private int statusCode;
	private String url;
	
	public HttpStatusException(int statusCode, String url) {
		super();
		this.statusCode = statusCode;
		this.url = url;
	}
	
	public HttpStatusException(String message, int statusCode, String url) {
        super(message);
        this.statusCode = statusCode;
        this.url = url;
    }
	
	public HttpStatusException(String message, int statusCode, String url, Throwable cause){
		super(message, cause);
		this.statusCode = statusCode;
        this.url = url;
	}

    public int getStatusCode() {
        return statusCode;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return super.toString() + ". Foi lançada uma exceção para requisição http para URL '" + url + "' e foi retornado o status code " + statusCode;
    }	
	
}
