package br.com.seta.processo.pagecomponentes.menus;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.wicket.Session;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;

import br.com.seta.processo.authentication.Permissoes;
import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.utils.GestorAcessoConstants;

/**
 * Renderiza um menu de links com o seguinte html: <br>
 * 
 * &lt;li class="nav-parent"&gt;<br />
 * &nbsp	   &lt;a&gt;<br />
 * &nbsp &nbsp	   &lt;i wicket:id="icone" aria-hidden="true"&gt;&lt;/i&gt;<br />
 * &nbsp &nbsp   &lt;span wicket:id="label"&gt;&lt;/span&gt;<br />
 * &nbsp   &lt;/a&gt;<br />
 * &nbsp   &lt;ul class="nav nav-children"&gt;<br />
 * &nbsp &nbsp    &lt;li wicket:id="opcaoMenu"&gt;<br />
 * &nbsp &nbsp &nbsp    &lt;a wicket:id="linkOpcao" href="#"&gt;<br />
 * &nbsp &nbsp &nbsp  &nbsp   &lt;span wicket:id="labelOpcao"&gt;&lt;/span&gt;<br />
 * &nbsp &nbsp &nbsp   &lt;/a&gt;<br />
 * &nbsp &nbsp      &lt;/li&gt;<br />
 * &nbsp	  &lt;/ul&gt;<br />
 *	&lt;/li&gt; <br>
 *
 *O componente deve estar dentro de um: <br>
 *
 *	&lt;nav id="menu" class="nav-main" role="navigation"&gt; <br>								
 *  &nbsp &lt;ul class="nav nav-main"&gt;<br />
 *	&nbsp &nbsp  Adiciona o(s) componente(s) aqui           <br />
 *	&nbsp &lt;/ul&gt;	<br />						
 *  &lt;/nav&gt; <br />
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class LinkMenu extends Panel{
	private static final long serialVersionUID = 1L;

	private RotuloEWebPageDataView opcoesMenu;
	private WebMarkupContainer icone;
	private Label label;
	
	/**
	 * @param id Será usado como wicket:id
	 * @param labelMenu Rótulo do menu
	 * @param iconCssClass classe css para ser usado como ícone do menu
	 * @param dataProvider Uma fonte de dados do tipo RotuloEWebPage que fonecerá as entradas para o menu de links
	 */
	public LinkMenu(String id, String labelMenu, String iconCssClass, IDataProvider<RotuloEWebPage> dataProvider) {
		super(id);
		
		label = new Label("label", labelMenu);
		icone = new WebMarkupContainer("icone");
		if(iconCssClass != null) icone.add(AttributeAppender.append("class", iconCssClass));
		opcoesMenu = new RotuloEWebPageDataView("opcaoMenu", dataProvider);		
		
		add(label, icone, opcoesMenu);
	}
	
	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();

		if (opcoesMenu.getQuantidadeItens() == 0)
			setVisible(false);
		else
			setVisible(true);

	}
	
	/**
	 * DataView que renderiza uma repetição de links para páginas. Implementa o acesso ao Gestor de Acessos
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class RotuloEWebPageDataView extends DataView<RotuloEWebPage>{
		private static final long serialVersionUID = 1L;

		private long cachedItensCount = 0;
		
		protected RotuloEWebPageDataView(String id, IDataProvider<RotuloEWebPage> dataProvider) {
			super(id, dataProvider);
		}
		
		@Override
		protected void addItems(Iterator<Item<RotuloEWebPage>> items) {
			ArrayList<Item<RotuloEWebPage>> itemsPermitidos = new ArrayList<Item<RotuloEWebPage>>();
			
			while(items.hasNext()){
				Item<RotuloEWebPage> item = items.next();
				RotuloEWebPage rotuloEWebPage = (RotuloEWebPage) item.getDefaultModelObject();
				
				if(temPermissao(rotuloEWebPage))	
					itemsPermitidos.add(item);
			}
			
			cachedItensCount = itemsPermitidos.size();
			super.addItems(itemsPermitidos.iterator());
		}

		@Override
		protected void populateItem(Item<RotuloEWebPage> item) {
			RotuloEWebPage rotuloEWebPage = (RotuloEWebPage) item.getDefaultModelObject();			
			final Class<? extends WebPage> webPage = rotuloEWebPage.getWebPage();
			String labelOpcao = rotuloEWebPage.getRotulo();

			Link<String> linkOpcao = new Link<String>("linkOpcao") {
				private static final long serialVersionUID = 1L;

				@Override
				public void onClick() {
					setResponsePage(webPage);
				}
			};						
			
			linkOpcao.add(new Label("labelOpcao", labelOpcao));
			item.add(linkOpcao);			
		}
		
		public long getQuantidadeItens(){
			return cachedItensCount;
		}
		
		private boolean temPermissao(RotuloEWebPage rotuloEWebPage) {
			Permissoes permissoes = (Permissoes) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_USUARIO);
			
			return permissoes.containsKey(rotuloEWebPage.getWebPage().getSimpleName());
		}

	}
	
}
