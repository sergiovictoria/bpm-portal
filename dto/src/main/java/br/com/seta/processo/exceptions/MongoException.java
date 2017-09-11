package br.com.seta.processo.exceptions;

public class MongoException extends Exception {
	
	
	private static final long serialVersionUID = 1L;
	
	
	public MongoException() {
	}

	
	public MongoException(String arg0) {
		super(arg0);
	}

	public MongoException(Throwable arg0) {
		super(arg0);
	}
	

	public MongoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	

}
