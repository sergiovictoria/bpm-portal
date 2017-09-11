package br.com.seta.processo.solicitacaopagamento.componentespagina;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.solicitacaopagamento.SolicitacaoPagamentoTemplate;

public class RodapeVazio extends Panel {

	private static final long serialVersionUID = 1L;

	private AjaxButton confirmacaoOkBtn;	
	
	public RodapeVazio(String id, final SolicitacaoPagamentoTemplate parentPage) {
		super(id);
		
		this.confirmacaoOkBtn = new AjaxButton("confirmacaoOkBtn") {		
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				parentPage.redirecionaParaPaginaDeAtividades();
			}
		};
		
		add(confirmacaoOkBtn);
	}

}
