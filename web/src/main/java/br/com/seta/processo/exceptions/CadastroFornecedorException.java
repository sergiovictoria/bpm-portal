package br.com.seta.processo.exceptions;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class CadastroFornecedorException extends Exception {
	private static final long serialVersionUID = 1L;

	public CadastroFornecedorException() {
	}

	public CadastroFornecedorException(String arg0) {
		super(arg0);
	}

	public CadastroFornecedorException(Throwable arg0) {
		super(arg0);
	}

	public CadastroFornecedorException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
