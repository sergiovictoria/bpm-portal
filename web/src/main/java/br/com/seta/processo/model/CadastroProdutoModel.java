package br.com.seta.processo.model;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;


public class CadastroProdutoModel extends CadastroFornecedorModel {

	private static final long serialVersionUID = 1L;
	
	public CadastroProdutoModel() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException {
		super();
	}

}
