package br.com.seta.processo.exceptions;

public class RemoteException extends Exception {

	private static final long serialVersionUID = 1L;
	
	
	
	public RemoteException() {
	}

	
	public RemoteException(String arg0) {
		super(arg0);
	}

	public RemoteException(Throwable arg0) {
		super(arg0);
	}
	

	public RemoteException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
	
	

}
