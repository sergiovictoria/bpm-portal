package br.com.seta.processo.recebimento.secoes;

import br.com.seta.processo.dto.OrRequisicao;

public class SecaoContabilizacao extends br.com.seta.processo.suprimentos.componentespagina.abas.SecaoContabilizacao {
	private static final long serialVersionUID = 1L;

	public SecaoContabilizacao(String id, OrRequisicao requisicao) {
		super(id, requisicao);
		setEnabled(false);
	}

}
