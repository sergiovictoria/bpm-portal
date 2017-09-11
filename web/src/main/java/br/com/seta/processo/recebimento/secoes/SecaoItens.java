package br.com.seta.processo.recebimento.secoes;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.page.BonitaPage;

public class SecaoItens extends br.com.seta.processo.suprimentos.componentespagina.abas.SecaoItens {
	private static final long serialVersionUID = 1L;

	public SecaoItens(String id, OrRequisicao requisicao, BonitaPage parentPage) {
		super(id, requisicao, parentPage);
		setEnabled(false);
		this.visibilidadeAnexarCadProdutosBtn(false)
		.visibilidadeArquivoCadProdutoBtn(false)
		.visibilidadeSecaoAnexo(false);
	}

}
