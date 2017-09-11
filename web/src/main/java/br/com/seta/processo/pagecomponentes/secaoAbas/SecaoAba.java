package br.com.seta.processo.pagecomponentes.secaoAbas;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class SecaoAba extends Panel {
	private static final long serialVersionUID = 1L;

	private Component conteudo;
	private TabPane tabPane;
	
	public SecaoAba(String idHtml, Component conteudo) {
		this(idHtml, conteudo, false);
	}	
	
	public SecaoAba(String idHtml, Component conteudo, boolean ativo) {
		super("secaoAba");
		this.conteudo = conteudo;
		
		tabPane = new TabPane(idHtml, ativo);
		
		tabPane.add(conteudo);
		add(tabPane);
	}
	
	public void setAtivo(boolean ativo){
		tabPane.setAtivo(ativo);
	}
	
	public Component getConteudo() {
		return conteudo;
	}
	
	private class TabPane extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		public TabPane(String idHtml) {
			super("tabPane");
			add(AttributeAppender.append("id", idHtml));
		}
		
		public TabPane(String idHtml, boolean ativo) {
			super("tabPane");
			
			add(AttributeAppender.append("id", idHtml));
			if(ativo)
				add(AttributeAppender.append("class", "active"));
		}
		
		public void setAtivo(boolean ativo){
			if(ativo)
				add(AttributeAppender.append("class", "active"));
			else{
				add(AttributeAppender.remove("class"));
				add(AttributeAppender.append("class", "tab-pane"));

			}
		}
		
	}
	
}
