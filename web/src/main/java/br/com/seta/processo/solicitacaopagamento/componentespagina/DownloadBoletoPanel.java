package br.com.seta.processo.solicitacaopagamento.componentespagina;

import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.constant.ConstantesSolicitacaoDePagamento;
import br.com.seta.processo.pagecomponentes.Anexos;
import br.com.seta.processo.provider.QueryAnexosProvider;

/**
 * Seção contendo um botão para download do boleto/documento a ser pago
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DownloadBoletoPanel extends Panel{
	private static final long serialVersionUID = 1L;	
	
	private Anexos anexos;
	private static final String QUERY_BUSCA_BOLETOS = "{caseId: ?1, descricao : \"" + ConstantesSolicitacaoDePagamento.BOLETO_PAGAMENTO +"\"}";
	
	public DownloadBoletoPanel(String id, long taskId, Long caseId) {
		super(id);	
		
		String query = QUERY_BUSCA_BOLETOS.replace("?1", caseId.toString());
		QueryAnexosProvider provider = new QueryAnexosProvider(query);
		this.anexos = new Anexos("anexos", provider);
		
		add(anexos);
	}
}
