package br.com.seta.processo.interfaces;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;

public interface EmailTemplates {
	public abstract String build(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao, String email);
}
