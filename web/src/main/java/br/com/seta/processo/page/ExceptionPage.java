package br.com.seta.processo.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;


public class ExceptionPage extends ProcessoWebPage {

	private static final long serialVersionUID = 1L;
	
	public ExceptionPage() {  
		add(new ExceptionPageForm("formSendException"));
	}  

	public final class ExceptionPageForm extends Form<Void> {
		private static final long serialVersionUID = 1L;
		public ExceptionPageForm(String id) {
			super(id);
			AjaxButton voltar = new AjaxButton("voltar") {
				private static final long serialVersionUID = 1L;
				protected void onSubmit(AjaxRequestTarget target, Form form)    {
					setResponsePage(Atividades.class);					
				}
			};
			add(voltar);
		}
		
	}
}
