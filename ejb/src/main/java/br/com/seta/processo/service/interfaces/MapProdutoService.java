package br.com.seta.processo.service.interfaces;

import javax.ejb.Local;

import br.com.seta.processo.dto.FormularioProduto;

@Local
public interface MapProdutoService {
	public abstract String create(FormularioProduto formularioProduto);
}
