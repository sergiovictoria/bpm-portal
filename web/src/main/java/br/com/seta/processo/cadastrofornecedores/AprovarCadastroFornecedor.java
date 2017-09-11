package br.com.seta.processo.cadastrofornecedores;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.TaskPage;

public class AprovarCadastroFornecedor extends TaskPage {

	private static final long serialVersionUID = 1L;

	public AprovarCadastroFornecedor(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
	}

}
