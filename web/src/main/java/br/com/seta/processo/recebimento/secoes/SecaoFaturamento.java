package br.com.seta.processo.recebimento.secoes;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;

public class SecaoFaturamento extends SecaoFaturamentoVisualizacao {
	private static final long serialVersionUID = 1L;

	public SecaoFaturamento(String id, OrRequisicao requisicao,	BonitaPage parentPage) {
		super(id, requisicao, parentPage);
		
		visibilidadeAbrirModalAnexoCadFornecedorBtn(false)
		.visibilidadeAbrirModalAnexoOrcamentoBtn(false)
		.visibilidadeDownloadFormularioCadFornecedor(false)
		.visibilidadeSecaoAnexoOrcamento(false);
	}

}
