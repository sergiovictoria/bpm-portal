package br.com.seta.processo.pagecomponentes.modal;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoVazia;

/**
 * Classe abstrata que representa um modal genérico. Permite configurar o corpo, rodapé e título da modal
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class AbstractModal extends Panel {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Utilizado para aplicar o estilo CSS de confirmação
	 */
	public static final String CONFIRMACAO = "confirmacao";
	
	/**
	 * Utilizado para aplicar o estilo CSS de sucesso
	 */
	public static final String SUCESSO = "sucesso";
	
	/**
	 * Utilizado para aplicar o estilo CSS de erro
	 */
	public static final String ERRO = "erro";
	
	/**
	 * Utilizado para aplicar o estilo CSS de atenção
	 */
	public static final String ATENCAO = "atencao";	
	
	private String idHtml = "";
	
	private Label tituloLbl;
	private Container container;
	private ModalDialog modalDialog;
	private Component corpoModal, rodapeModal;
	
	/**
	 * 
	 * @param id Wicket id
	 * @param idHtml Id Html utilizado como atributo id. Deve ser único
	 */
	public AbstractModal(String id, String idHtml, String tipoModal) {
		super(id);
		
		setOutputMarkupId(true);
		
		this.idHtml = idHtml;
		tituloLbl = new Label("tituloLbl", Model.of(""));
		container = new Container(idHtml);
		modalDialog = new ModalDialog(tipoModal);
		
		adicionaComponente(tituloLbl);
		adicionaCorpo(new SecaoVazia("corpoModal"));
		adicionaRodape(new SecaoVazia("rodapeModal"));		
		
		container.add(modalDialog);		
		add(container);		
	}
	
	/**
	 * 
	 * @param id Wicket id
	 * @param idHtml Id Html utilizado como atributo id. Deve ser único
	 * @param tipoModal Classe CSS que define o estilo do modal. Pode-se utilizar as constantes SUCESSO, ERRO, ATENCAO e SUCESSO
	 * @param titulo Título da modal
	 * @param corpoModal Componente wicket que representa o corpo da modal. O wicket id deve ser obrigatoriamente <b>corpoModal</b>
	 * @param rodapeModal Componente wicket que representa o rodapé da modal. O wicket id deve ser obrigatoriamente <b>rodape</b>
	 */
	public AbstractModal(String id, String idHtml, String tipoModal, String titulo, Component corpoModal, Component rodapeModal) {
		this(id, idHtml, tipoModal);
		adicionaCorpo(corpoModal).adicionaRodape(rodapeModal).adicionaTitulo(titulo);
	}
	
	/**
	 * 
	 * @param id Será utilizado como wicket id e id html. Deve ser único tanto no wicket, quanto no html
	 * @param tipoModal Classe CSS que define o estilo do modal. Pode-se utilizar as constantes SUCESSO, ERRO, ATENCAO e SUCESSO
	 * @param titulo Título da modal
	 * @param corpoModal Componente wicket que representa o corpo da modal. O wicket id deve ser obrigatoriamente <b>corpoModal</b>
	 * @param rodapeModal Componente wicket que representa o rodapé da modal. O wicket id deve ser obrigatoriamente <b>rodape</b>
	 */
	public AbstractModal(String id, String titulo, Component corpoModal, Component rodapeModal, String tipoModal) {
		this(id, id, tipoModal, titulo, corpoModal, rodapeModal);
	}
	
	/**
	 * 
	 * @param id Será utilizado como wicket id e id html. Deve ser único tanto no wicket, quanto no html
	 * @param titulo Título da modal
	 * @param corpoModal Componente wicket que representa o corpo da modal. O wicket id deve ser obrigatoriamente <b>corpoModal</b>
	 * @param rodapeModal Componente wicket que representa o rodapé da modal. O wicket id deve ser obrigatoriamente <b>rodape</b>
	 */
	public AbstractModal(String id, String idHtml, String titulo, Component corpoModal, Component rodapeModal){
		this(id, idHtml, CONFIRMACAO, titulo, corpoModal, rodapeModal);
	}
	
	public AbstractModal adicionaTitulo(String nome) {
		tituloLbl.setDefaultModelObject(nome);
		return this;
	}

	/**
	 * Adiciona um componente wicket como corpo da modal. O componente deve possuir obrigatoriamente o wicket id igual a <b>corpoModal</b>
	 * @param corpoModal
	 * @return
	 */
	public AbstractModal adicionaCorpo(Component corpoModal) {
		this.corpoModal = corpoModal;
		return adicionaComponente(corpoModal);
	}	
	
	/**
	 * Adiciona um componente wicket como rodapé da modal. O componente deve possuir obrigatoriamente o wicket id igual a <b>rodapeModal</b>
	 * @param rodapeModal
	 * @return
	 */
	public AbstractModal adicionaRodape(Component rodapeModal) {
		this.rodapeModal = rodapeModal;
		return adicionaComponente(rodapeModal);
	}
	
	private AbstractModal adicionaComponente(Component corpo) {
		modalDialog.addOrReplace(corpo);
		return this;
	}
	
	public AbstractModal exibe(AjaxRequestTarget target) {
		String js = getExibeModalFunction();
		target.appendJavaScript(js);
		return this;
	}

	public AbstractModal oculta(AjaxRequestTarget target) {
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
	
	public Component getCorpoModal() {
		return corpoModal;
	}

	public Component getRodapeModal() {
		return rodapeModal;
	}

	private class Container extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
	
		public Container(String idHtml) {
			super("container");
			add(AttributeAppender.append("id", idHtml));
		}
	
	}	
	
	/**
	 * Renderiza a caixa de modal (não inclui o bloqueio de tela)
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ModalDialog extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
	
		public ModalDialog(String classeCssModal) {
			super("modalDialog");
	
			add(AttributeAppender.append("class", classeCssModal));
		}
	
	}
}
