package br.com.seta.processo.interfaces;

import javax.ejb.Remote;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;


@Remote
public interface EmailTemplatesRemote {
	public abstract String build(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao, String email);
}
