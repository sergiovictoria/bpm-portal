package br.com.seta.processo.pagecomponentes.menus;

import java.util.Arrays;

import org.apache.wicket.Session;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.authentication.Permissoes;
import br.com.seta.processo.utils.GestorAcessoConstants;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ItemMenu extends Panel{
	private static final long serialVersionUID = 1L;
	
	private Class<? extends WebPage> paginaDestino;
	private Link<String> linkOpcaoStack, linkOpcao; 
	
	public ItemMenu(String id, String labelOpcao, String[] iconCssClasses, Class<? extends WebPage> paginaDestino) {
		super(id);
		
		this.paginaDestino = paginaDestino;
		
		if(iconCssClasses == null || iconCssClasses.length < 2){
			String iconCssClass = "";
			
			if(iconCssClasses != null && iconCssClasses.length > 0){
				iconCssClass = iconCssClasses[0];
			}	
			
			linkOpcao = new LinkOpcao("linkOpcao", iconCssClass , labelOpcao, paginaDestino);
			linkOpcaoStack = getLinkOpcaoStackNullObject();
		}else{
			linkOpcao = getLinkOpcaoNullObject();
			linkOpcaoStack = new LinkOpcaoStack("linkOpcaoStack", iconCssClasses, labelOpcao, paginaDestino);
		}
		
		add(linkOpcaoStack, linkOpcao);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		
		if(temPermissao())
			setVisible(true);
		else
			setVisible(false);
		
	}
	
	private boolean temPermissao() {
		Permissoes permissoes = (Permissoes) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_USUARIO);
		return permissoes.containsKey(paginaDestino.getSimpleName());
	}
	
	private LinkOpcaoStack getLinkOpcaoStackNullObject(){
		String[] arr = {};
		LinkOpcaoStack nullObject = new LinkOpcaoStack("linkOpcaoStack", arr, "", null);
		nullObject.setVisible(false);
		
		return nullObject;
	}
	
	private LinkOpcao getLinkOpcaoNullObject(){
		LinkOpcao nullObject = new LinkOpcao("linkOpcao", "", "", null);
		nullObject.setVisible(false);
		
		return nullObject;
	}
	
	private class LinkOpcaoStack extends Link<String>{
		private static final long serialVersionUID = 1L;

		Class<? extends WebPage> paginaDestino;
		
		public LinkOpcaoStack(String id, String[] iconCssClasses, String labelOpcao, Class<? extends WebPage> classPagina) {
			super(id);
			
			paginaDestino = classPagina;
			Label label = new Label("labelOpcao", labelOpcao);
			ListView<String> icones = new ListView<String>("icones", Arrays.asList(iconCssClasses)) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<String> item) {
					String iconCssClass = item.getDefaultModelObjectAsString();
					item.add(AttributeAppender.append("class", iconCssClass));
				}
			};			
			
			add(label, icones);
		}

		@Override
		public void onClick() {
			setResponsePage(paginaDestino);			
		}
		
	}
	
	private class LinkOpcao extends Link<String>{
		private static final long serialVersionUID = 1L;
		
		Class<? extends WebPage> paginaDestino;
		
		public LinkOpcao(String id, String iconCssClass, String labelOpcao, Class<? extends WebPage> classPagina) {
			super(id);
			
			paginaDestino = classPagina;
			Label label = new Label("labelOpcao", labelOpcao);
			Icone icone = new Icone("icone", iconCssClass);

			add(label, icone);
		}

		@Override
		public void onClick() {
			setResponsePage(paginaDestino);
		}
		
	}
	
	private class Icone extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;		
		
		public Icone(String id, String iconCssClass) {
			super(id);
			
			if(iconCssClass != null) 
				add(AttributeAppender.append("class", iconCssClass));
		}		
	}

}
