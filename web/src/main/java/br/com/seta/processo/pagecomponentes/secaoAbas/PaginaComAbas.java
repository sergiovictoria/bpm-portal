package br.com.seta.processo.pagecomponentes.secaoAbas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoVazia;

public abstract class PaginaComAbas extends BonitaPage {
	
	private static final long serialVersionUID = 1L;

	private List<LinkAba> linksAbas = new ArrayList<LinkAba>();
	private List<SecaoAba> secoesAbas = new ArrayList<SecaoAba>();
	private Map<Integer, Aba> abas = new HashMap<Integer, Aba>();
	
	private Form<Void> form = new Form<Void>("form");
	private ListView<LinkAba> linkAbasListView;
	private ListView<SecaoAba> secoesAbasListView;
	
	public PaginaComAbas(PageParameters pageParameters)
			throws HttpStatus401Exception, HttpStatus404Exception,
			ClientProtocolException, GenericHttpStatusException, IOException {
		super(pageParameters);
		
		linkAbasListView = new ListView<LinkAba>("linksAbas", linksAbas) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<LinkAba> item) {
				item.add( (LinkAba)item.getModelObject() );				
			}
		};
		
		secoesAbasListView = new ListView<SecaoAba>("secoesAbas", secoesAbas) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<SecaoAba> item) {
				item.add( (SecaoAba)item.getModelObject() );	
			}
		};
		
		form.add(linkAbasListView, secoesAbasListView);
		add(form);
		addSecaoBotoes(new SecaoVazia("secaoBotoes"));		
		
	}
	
	public PaginaComAbas addSecaoBotoes(Component secaoBotoes){
		this.form.addOrReplace(secaoBotoes);
		return this;
	}
	
	public PaginaComAbas addAba(Aba aba){
		LinkAba link = aba.getLink();
		SecaoAba secao = aba.getSecao();
		
		int indice = abas.size() + 1;
		link.setIndice(indice);
		abas.put(indice, aba);
		linksAbas.add(link);
		secoesAbas.add(secao);
		
		return this;
	}
	
	public PaginaComAbas setAbaAtiva(int indice){
		for(Aba aba : abas.values()){
			aba.getLink().setAtivo(false);
			aba.getSecao().setAtivo(false);
		}
		
		Aba aba = abas.get(indice);
		aba.getLink().setAtivo(true);
		aba.getSecao().setAtivo(true);
		
		return this;
	}
	
	public Aba getAba(int indice){
		return abas.get(indice);
	}
	
	public SecaoAba getSecaoAba(int indice){
		return getAba(indice).getSecao();
	}
	
	public Component getConteudoAba(int indice){
		return getSecaoAba(indice).getConteudo();
	}
	
	
}
