package br.com.seta.processo.solicitacaopagamento.componentespagina;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.bean.SolicitacaoPagamentoService;
import br.com.seta.processo.dto.BoletoSolicitacaoPagamento;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.SolicitacaoPagamentoServiceException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.solicitacaopagamento.CadastrarSolicitacaoPagamento;

/**
 * Painel contendo o botão para envio da solicitação de pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class EnvioSolicPagamentoPanel extends Panel {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private SolicitacaoPagamentoService solicitacaoService;
	private AjaxButton enviarSolicPagtoBtn, confirmacaoOkBtn;
	
	public EnvioSolicPagamentoPanel(String id, final CadastrarSolicitacaoPagamento parentPage, final AnexarBoletoPanel anexoPanel) {
		super(id);
		
		this.enviarSolicPagtoBtn = new AjaxButton("enviarSolicPagtoBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				User usuario = parentPage.getUser();
				String nomeProcesso = parentPage.getNomeProcesso();
				String versaoProcesso = parentPage.getVersaoProcesso();
				SolicitacaoPagamento solicitacao = parentPage.getSolicitacaoPagamento();
				BoletoSolicitacaoPagamento boleto = anexoPanel.getBoletoSolicitacaoPagamento();
				
				try {
					target.appendJavaScript("$('#confirmacaoEnvioDialog').modal('hide')");
					solicitacaoService.enviaSolicitacaoPagamento(usuario, nomeProcesso, versaoProcesso, solicitacao, boleto);
					target.appendJavaScript("$('#sucessoEnvioSolicitacao').modal('show')");
				} catch (ValidacaoBeanException exception) {					
					parentPage.setMensagensErro(exception.getMessages(), "", target);
				}catch(SolicitacaoPagamentoServiceException exception){
					throw new RuntimeException(exception);
				}
			}
		};
		
		this.confirmacaoOkBtn = new AjaxButton("confirmacaoOkBtn") {		
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				parentPage.redirecionaParaPaginaDeAtividades();
			}
		};
		
		add(this.enviarSolicPagtoBtn, confirmacaoOkBtn);
	}	
	
}
