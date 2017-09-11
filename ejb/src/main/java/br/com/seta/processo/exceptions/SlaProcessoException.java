package br.com.seta.processo.exceptions;

public class SlaProcessoException extends Exception {

	private static final long serialVersionUID = 1L;

	public SlaProcessoException() {
	}

	public SlaProcessoException(String arg0) {
		super(arg0);
	}

	public SlaProcessoException(Throwable arg0) {
		super(arg0);
	}

	public SlaProcessoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

}