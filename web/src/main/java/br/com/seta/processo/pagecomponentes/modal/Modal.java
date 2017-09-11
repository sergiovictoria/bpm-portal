package br.com.seta.processo.pagecomponentes.modal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoVazia;

/**
 * Renderiza um modal
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Modal extends Panel {

	private static final long serialVersionUID = 1L;

	public static final String CONFIRMACAO = "confirmacao";
	public static final String SUCESSO = "sucesso";
	public static final String ERRO = "erro";
	public static final String ATENCAO = "atencao";

	private String idHtml = "";
	
	private List<Component> botoesList = new ArrayList<Component>();

	private Label tituloLbl;
	private WebMarkupContainer corpoContainer;
	private ListView<Component> botoesListView;

	private Container container;
	private ModalDialog modalDialog;

	public Modal(String id, String idHtml, String tipoModal, Component corpo, String titulo, Component... botoes) {
		this(id, idHtml, tipoModal);
		setTitulo(titulo).adicionaCorpo(corpo).adicionaBotoes(botoes);
	}

	public Modal(String id, String idHtml, String tipoModal) {
		super(id);

		setOutputMarkupId(true);
		
		this.idHtml = idHtml;
		
		tituloLbl = new Label("tituloLbl", Model.of(""));
		corpoContainer = new WebMarkupContainer("corpoContainer");
		adicionaCorpo(new SecaoVazia("corpoModal"));
		botoesListView = new ListView<Component>("botoesListView", botoesList) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(ListItem<Component> item) {
				item.add( item.getModelObject() );	
			}
		};
		
		container = new Container(idHtml);
		modalDialog = new ModalDialog("modalDialog", tipoModal);

		container.add(modalDialog);
		modalDialog.add(tituloLbl, corpoContainer, botoesListView);
		add(container);
	}
	
	public Modal(String id, String idHtml){
		this(id, idHtml, CONFIRMACAO);
	}

	public Modal setTitulo(String nome) {
		tituloLbl.setDefaultModelObject(nome);
		return this;
	}

	public Modal adicionaCorpo(Component corpo) {
		corpoContainer.addOrReplace(corpo);
		return this;
	}

	public Modal adicionaBotao(Component botao) {
		botoesList.add(botao);
		return this;
	}

	public Modal adicionaBotoes(Component... botoes) {
		botoesList.addAll(Arrays.asList(botoes));
		return this;
	}

	public Modal exibe(AjaxRequestTarget target) {
		String js = getExibeModalFunction();
		target.appendJavaScript(js);
		return this;
	}

	public Modal oculta(AjaxRequestTarget target) {
		String js = getOcultaModalFunction();
		target.appendJavaScript(js);
		return this;
	}
	
	public String getExibeModalFunction(){
		return "$('#"+this.idHtml+"').modal('show');";
	}
	
	public String getOcultaModalFunction(){
		return "$('#"+this.idHtml+"').modal('hide');";
	}

	private class Container extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
	
		public Container(String idHtml) {
			super("container");
			add(AttributeAppender.append("id", idHtml));
		}
	
	}

	private class ModalDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
	
		public ModalDialog(String id, String classeCssModal) {
			super(id);
	
			add(AttributeAppender.append("class", classeCssModal));
		}
	
	}

}
