package br.com.seta.processo.pagecomponentes.secaoAbas;

import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

public class LinkAba extends Panel {
	private static final long serialVersionUID = 1L;
	
	private ListItem listItem;
	private Link link;
	private Label nomeAbaLbl;
	private Label indiceAbaLbl;
	
	public LinkAba(String id, String href, String nomeAba){
		this(id, href, nomeAba, false);
	}	
	
	public LinkAba(String id, String href, String nomeAba, boolean ativo){
		super("linkAba");
		listItem = new ListItem("listItem", id, ativo);
		link = new Link("link", "linkhtml", href);
		nomeAbaLbl = (Label) new Label("nomeAba", nomeAba).setEscapeModelStrings(false);
		indiceAbaLbl = new Label("indiceAba", 0);
		
		listItem.add(link);
		link.add(indiceAbaLbl, nomeAbaLbl);
		
		add(listItem);
	}	
	
	public LinkAba(String id, String href, String nomeAba, boolean ativo, AjaxEventBehavior event){
		this(id, href, nomeAba, ativo);
		indiceAbaLbl.add(event);
	}
	
	public void setIndice(int indice){
		this.indiceAbaLbl.setDefaultModelObject(indice);
	}
	
	public void setAtivo(boolean ativo){
		listItem.setAtivo(ativo);
	}
	
	private class ListItem extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;		
		
		public ListItem(String id, String idHtml) {
			super(id);			
			if(id == null)
				id = getMarkupId();			
			
			add(AttributeAppender.append("id", idHtml));			
		}
		
		public ListItem(String id, String idHtml, boolean ativo) {
			super(id);			
			if(id == null)
				id = getMarkupId();			
			
			add(AttributeAppender.append("id", idHtml));
			if(ativo)
				add(AttributeAppender.append("class", "active"));
		}	
		
		public void setAtivo(boolean ativo){
			if(ativo)
				add(AttributeAppender.append("class", "active"));
			else{
				add(AttributeAppender.remove("class"));
				add(AttributeAppender.append("class", "completed"));
			}
		}
	}
	
	private class Link extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		public Link(String id, String idHtml, String href) {
			super(id);
			
			add(AttributeAppender.append("id", idHtml));
			add(AttributeAppender.append("href", href));
		}
	}
}
