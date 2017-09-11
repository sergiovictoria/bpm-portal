package br.com.seta.processo.parse;

import java.io.File;
import java.io.IOException;

import br.com.seta.processo.dto.FormularioFornecedor;

public interface Parse {
	public abstract FormularioFornecedor parseBuild(File file) throws IOException;
}
