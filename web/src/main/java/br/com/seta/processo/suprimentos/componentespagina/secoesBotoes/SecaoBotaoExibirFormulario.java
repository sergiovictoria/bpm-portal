package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.page.BonitaPage;

/**
 * Renderiza o botão de exibir formulário
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SecaoBotaoExibirFormulario extends Panel{

	private static final long serialVersionUID = 1L;
	
	private AjaxButton exibirFormularioBtn;
	
	public SecaoBotaoExibirFormulario(String id, final BonitaPage formulario) {
		super(id);
		
		this.exibirFormularioBtn = new AjaxButton("exibirFormularioBtn") {			
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(formulario);
			}
		};
		
		add(exibirFormularioBtn);
	}

}
