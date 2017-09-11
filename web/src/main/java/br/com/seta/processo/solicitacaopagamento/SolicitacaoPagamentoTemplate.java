package br.com.seta.processo.solicitacaopagamento;

import java.io.IOException;

//import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

//import br.com.seta.processo.bean.EmpresaFontePagadoraService;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.pagecomponentes.secaoAbas.Aba;
import br.com.seta.processo.pagecomponentes.secaoAbas.PaginaComAbas;
import br.com.seta.processo.provider.CentroCustoSolicitacaoProvider;
import br.com.seta.processo.solicitacaopagamento.abas.SecaoContabilizacao;
import br.com.seta.processo.solicitacaopagamento.abas.SecaoFinanceiro;
import br.com.seta.processo.solicitacaopagamento.abas.SecaoSolicitacao;

/**
 * Representa o template para as páginas do processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class SolicitacaoPagamentoTemplate extends PaginaComAbas {
	
	private static final long serialVersionUID = 1L;
	
	private SolicitacaoPagamento solicitacao = new SolicitacaoPagamento();
	private SecaoSolicitacao secaoSolicitacao;
	private SecaoContabilizacao secaoContabilizacao;
	private SecaoFinanceiro secaoFinanceiro;	
	private CentroCustoSolicitacaoProvider centroCustoSolicitacaoProvider = new CentroCustoSolicitacaoProvider();
	
	public SolicitacaoPagamentoTemplate(PageParameters pageParameters) throws ClientProtocolException, IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException{
		super(pageParameters);	
		this.solicitacao = (SolicitacaoPagamento) retriveProcessVariable(VariaveisSolicitacaoPagamento.SOLICITACAO_PAGAMENTO, SolicitacaoPagamento.class, new SolicitacaoPagamento());
		
		
		this.secaoContabilizacao = new SecaoContabilizacao("conteudo", centroCustoSolicitacaoProvider, this.solicitacao);
		this.secaoFinanceiro = new SecaoFinanceiro("conteudo", this.solicitacao, this);
		this.secaoSolicitacao = new SecaoSolicitacao("conteudo", this.solicitacao, centroCustoSolicitacaoProvider, secaoContabilizacao, secaoFinanceiro);
		
		AjaxEventBehavior abaContabilizacaoOnclickEvent = new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.add(SolicitacaoPagamentoTemplate.this.secaoContabilizacao);
				target.appendJavaScript("ativarAbaSecao('linkAbaContabilizacao', 'secaoContabilizacao');");
			}
		};
		
		AjaxEventBehavior abaFinanceiroOnclickEvent = new AjaxEventBehavior("click") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onEvent(AjaxRequestTarget target) {
				target.add(SolicitacaoPagamentoTemplate.this.secaoFinanceiro);
				target.appendJavaScript("ativarAbaSecao('linkAbaFinanceiro', 'secaoFinanceiro'); iniciarCamposData();");
			}
		};
		
		Aba abaSolicitacao = new Aba("linkAbaSolicitacao", "Solicitação", "secaoSolicitacao", secaoSolicitacao);
		Aba abaContabilizacao = new Aba("linkAbaContabilizacao", "Contabilização", "secaoContabilizacao", secaoContabilizacao, false, abaContabilizacaoOnclickEvent);
		Aba abaFinanceiro =  new Aba("linkAbaFinanceiro", "Financeiro", "secaoFinanceiro", secaoFinanceiro, false, abaFinanceiroOnclickEvent);
		
		addAba(abaSolicitacao);
		addAba(abaContabilizacao);
		addAba(abaFinanceiro);
		
		setAbaAtiva(1);
	}
	
	public SolicitacaoPagamento getSolicitacaoPagamento(){
		return this.solicitacao;
	}
	
	public void adicionaAoSolicitacaoPagamentoForm(Component... componentes){
		this.add(componentes);
	}
	
	
	public void adicionaSecaoAnexo(Panel secaoAnexo){
		this.secaoSolicitacao.addOrReplace(secaoAnexo);
	}
	
	

	public SecaoSolicitacao getSolicitacaoPanel() {
		return secaoSolicitacao;
	}
	
}
