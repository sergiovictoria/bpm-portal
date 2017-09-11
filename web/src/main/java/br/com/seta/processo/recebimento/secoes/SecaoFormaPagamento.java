package br.com.seta.processo.recebimento.secoes;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.page.BonitaPage;

public class SecaoFormaPagamento extends br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFormaPagamento {
	private static final long serialVersionUID = 1L;

	public SecaoFormaPagamento(String id, OrRequisicao requisicao, BonitaPage parentPage) {
		super(id, requisicao, parentPage);
		setEnabled(false);
	}

}
