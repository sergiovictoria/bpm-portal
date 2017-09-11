package br.com.seta.processo.exceptions;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class CadastroProdutoException extends Exception {
	private static final long serialVersionUID = 1L;

	public CadastroProdutoException() {
	}

	public CadastroProdutoException(String arg0) {
		super(arg0);
	}

	public CadastroProdutoException(Throwable arg0) {
		super(arg0);
	}

	public CadastroProdutoException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
