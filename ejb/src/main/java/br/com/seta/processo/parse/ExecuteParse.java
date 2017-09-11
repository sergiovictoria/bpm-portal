package br.com.seta.processo.parse;

import java.io.File;
import java.io.IOException;

import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.parse.factory.ParseFactory;

public class ExecuteParse {

	private FormularioFornecedor formularioFornecedor = new FormularioFornecedor();   
	
	public ExecuteParse(ParseFactory factory, File file) throws IOException {
		Parse parse = factory.createParse();
		this.formularioFornecedor = parse.parseBuild(file);
		
	}

	public FormularioFornecedor getFormularioFonecedor() {
		return formularioFornecedor;
	}

	public void setFormularioFonecedor(FormularioFornecedor formularioFornecedor) {
		this.formularioFornecedor = formularioFornecedor;
	}
	
	
	
}
