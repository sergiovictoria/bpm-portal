package br.com.seta.processo.exceptions;

public class CalculoException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	
	public CalculoException() {
	}

	
	public CalculoException(String arg0) {
		super(arg0);
	}

	public CalculoException(Throwable arg0) {
		super(arg0);
	}
	

	public CalculoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	

}
