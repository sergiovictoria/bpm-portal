package br.com.seta.processo.consultas.suprimentos;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;

public class BotaoVoltarPanel extends Panel{
private static final long serialVersionUID = 1L;
	
	private AjaxButton voltarBtn;

	public BotaoVoltarPanel(String id) {
		super(id);
		
		voltarBtn = new AjaxButton("voltarBtn"){
			private static final long serialVersionUID = 1L;			
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(ConsultaIntencoesCompra.class);
			}
			
		};
		
		add(voltarBtn);
	}
}

