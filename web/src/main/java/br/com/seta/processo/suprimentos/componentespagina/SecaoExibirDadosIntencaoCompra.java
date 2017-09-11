package br.com.seta.processo.suprimentos.componentespagina;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;

public class SecaoExibirDadosIntencaoCompra extends Panel  {
	
	private static final long serialVersionUID = 1L;

	private AjaxButton exibirBtn;
	private SolicitacaoIntencaoCompra solicitacao;
	
	public SecaoExibirDadosIntencaoCompra(String id, SolicitacaoIntencaoCompra solicitacao) {
		super(id);
		
		this.solicitacao = solicitacao;
		this.exibirBtn = new AjaxButton("exibirBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				System.out.println(SecaoExibirDadosIntencaoCompra.this.solicitacao);
			}
		};
		
		add(exibirBtn);
	}
	
	public SecaoExibirDadosIntencaoCompra(SolicitacaoIntencaoCompra solicitacao){
		this("secaoBotoesAcao", solicitacao);
	}

}
