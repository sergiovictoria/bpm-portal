package br.com.seta.processo.componentes.messagebox;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.resource.ContextRelativeResource;

import br.com.seta.processo.componentes.messagebox.MessageBoxButtons;
import br.com.seta.processo.componentes.messagebox.MessageBoxLoadingGif;
import br.com.seta.processo.componentes.messagebox.MessageBoxTipo;

/**
 * Esse componente tem como objetivo facilitar e economizar tempo no momento que são necessarios exibições de mensagens 
 * e do bloqueio de tela para o GIF de loading.
 * <br/>
 * Esse componente requer a tag <b>span</b> no html para a utilização dela. 
 * 
 * @author Joao C. Fernandes
 * @version 0.0.1
 * @date 10/11/2015
 */
public class MessageBox extends Panel{

	private static final long serialVersionUID = 1L;
	
	private String titulo;
	private String mensagem;
	private MessageBoxTipo messageBoxTipo;
	private MessageBoxButtons messageBoxButtons;
	private String lblConfirmar;
	private String lblCancelar;
	private String lblIcon;
	
	private boolean exibirBtnConfirmar = true;
	private boolean exibirBtnCancelar = true;
	
	private boolean dialogResult = false;
	
	private WebMarkupContainer grpModal = new  WebMarkupContainer("grpModal");
	
	/**
	 * Para uso desse componente é necessario o uso da seguinte tag no html <b>&lt;span wicket:id="messageBox"&gt;&lt;/span&gt;</b>
	 * <br/>
	 * O id no exemplo é um id proposto.
	 * <br/>
	 * Essa construtora estancia o messagebox para exibição de mensagens na tela.
	 * 
	 * @param id - id do componente no html
	 * @param titulo
	 * @param mensagem
	 * @param messageBoxTipo - Tipo de mensagem, sucesso, erro, aviso, etc...
	 * @param messageBoxButtons - Botões que devem ser exibidos
	 */
	public MessageBox (String id, String titulo, String mensagem, MessageBoxTipo messageBoxTipo, MessageBoxButtons messageBoxButtons) {
		
		super(id);
		
		this.titulo = titulo;
		this.mensagem = mensagem;
		this.messageBoxTipo = messageBoxTipo;
		this.messageBoxButtons = messageBoxButtons;
		
		add(new FrmModalMessageBox("frmModalMessageBox"));
	}
	
	
	
	/**
	 * Para uso desse componente é necessario o uso da seguinte tag no html <b>&lt;span wicket:id="messageBox"&gt;&lt;/span&gt;</b>
	 * <br/>
	 * O id no exemplo é um id proposto.
	 * <br/>
	 * Essa construtora estancia o messagebox para exibição de um gif de loading.
	 * <br/>
	 * Esse componentem deve ser aberto e fechado utilizando os metodos <b>show</b> e <b>close</b>, por não possuir botão para fechar
	 * 
	 * @param String id - id do componente no html
	 * @param MessageBoxLoadingGif messageBoxLoadingGif
	 * @param String titulo
	 */
	public MessageBox(String id, MessageBoxLoadingGif messageBoxLoadingGif, String titulo) {
		super(id);
		
		this.titulo = titulo;
		this.mensagem = "";
		this.messageBoxTipo = null;
		this.messageBoxButtons = null;
		
		add(new FrmModalMessageBox("frmModalMessageBox", messageBoxLoadingGif, titulo));
	}
	
	
	
	
	/**
	 * Exibe a caixa de mensagem utilizando um comando javascript
	 * @param AjaxRequestTarget target 
	 */
	public void show(AjaxRequestTarget target){
		setDialogResult(false);
		target.add(grpModal);
		target.appendJavaScript("$.magnificPopup.open({"
									+ " 				items: {"
									+ " 					src: '#modalMessageBox',"
									+ " 					type: 'inline'"
									+ " 				},"
									+ "					modal: true,"
									+ "					mainClass: 'mfp-lock-screen'"
									+ "				 });");
	}
	
	/**
	 * Fecha a caixa de mensagem utilizando um comando javascript
	 * @param AjaxRequestTarget target 
	 */
	public void close(AjaxRequestTarget target){
		target.add(grpModal);
		target.appendJavaScript("$.magnificPopup.close();");
	}
	
	/**
	 * Exibe o messagebox alterando todas as informações
	 * <br/>
	 * <b>Usar somente se o messagebox for para exibição de mensagens.</b>
	 * @param AjaxRequestTarget target
	 * @param String titulo
	 * @param Stringmensagem
	 * @param MessageBoxTipo messageBoxTipo
	 * @param MessageBoxButtons messageBoxButtons
	 */
	public void show(AjaxRequestTarget target, String titulo, String mensagem, MessageBoxTipo messageBoxTipo, MessageBoxButtons messageBoxButtons){
		target.appendJavaScript("$('#modalMessageBox').removeClass('"+ getMessageBoxTipo().getCssClass() +"');");
		
		setTitulo(titulo);
		setMensagem(mensagem);
		setMessageBoxTipo(messageBoxTipo);
		setMessageBoxButtons(messageBoxButtons);
		setLblIcon(messageBoxTipo.getIcon());
		
		target.appendJavaScript("$('#modalMessageBox').addClass('"+ messageBoxTipo.getCssClass() +"');");
		
		switch (messageBoxButtons) {
			case SIM:
				setLblConfirmar("Sim");
				exibirBtnConfirmar = true;
				exibirBtnCancelar = false;
				break;
			
			case SIM_NAO:
				setLblConfirmar("Sim");
				setLblCancelar("Não");
				exibirBtnConfirmar = true;
				exibirBtnCancelar = true;
				break;
			
			case CONFIRMAR:
				setLblConfirmar("Confirmar");
				exibirBtnConfirmar = true;
				exibirBtnCancelar = false;
				break;
	
			case CONFIRMAR_CANCELAR:
				setLblConfirmar("Confirmar");
				setLblCancelar("Cancelar");
				exibirBtnConfirmar = true;
				exibirBtnCancelar = true;
				break;
				
			case OK:
				setLblConfirmar("OK");
				exibirBtnConfirmar = true;
				exibirBtnCancelar = false;
				break;
				
			case FECHAR:
				setLblCancelar("Fechar");
				exibirBtnConfirmar = false;
				exibirBtnCancelar = true;
				break;
	
			default:
				setLblCancelar("Cancelar");
				exibirBtnConfirmar = false;
				exibirBtnCancelar = true;
				break;
		}
		
		show(target);
	}
	
	
	/**
	 * Form do modal, usar as contrutoras conforme a utilidade
	 * @author joao
	 *
	 */
	private final class FrmModalMessageBox extends Form<Object>{
		private static final long serialVersionUID = 1L;
		
		public FrmModalMessageBox(String id){
			super(id);
			
			grpModal.setOutputMarkupId(true);
			grpModal.add(new AttributeModifier("class", getMessageBoxTipo().getCssClass()));
			
			setLblIcon(getMessageBoxTipo().getIcon());
						
			AjaxButton btnConfirmar = new AjaxButton("btnConfirmar") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form) {	
					setDialogResult(true);
					close(target);
				}
			};
			btnConfirmar.setVisible(exibirBtnConfirmar);
			
			Button btnCancelar = new Button("btnCancelar") {
				private static final long serialVersionUID = 1L;
				
			};
			btnCancelar.setVisible(exibirBtnCancelar);
			
			switch (messageBoxButtons) {
				case SIM:
					setLblConfirmar("Sim");
					exibirBtnConfirmar = true;
					exibirBtnCancelar = false;
					break;
				
				case SIM_NAO:
					setLblConfirmar("Sim");
					setLblCancelar("Não");
					exibirBtnConfirmar = true;
					exibirBtnCancelar = true;
					break;
				
				case CONFIRMAR:
					setLblConfirmar("Confirmar");
					exibirBtnConfirmar = true;
					exibirBtnCancelar = false;
					break;

				case CONFIRMAR_CANCELAR:
					setLblConfirmar("Confirmar");
					setLblCancelar("Cancelar");
					exibirBtnConfirmar = true;
					exibirBtnCancelar = true;
					break;
					
				case OK:
					setLblConfirmar("OK");
					exibirBtnConfirmar = true;
					exibirBtnCancelar = false;
					break;
					
				case FECHAR:
					setLblCancelar("Fechar");
					exibirBtnConfirmar = false;
					exibirBtnCancelar = true;
					break;
		
				default:
					setLblCancelar("Cancelar");
					exibirBtnConfirmar = false;
					exibirBtnCancelar = true;
					break;
			}
			
			Image imgMessageBoxLoading = new Image("imgMessageBoxLoading","");
			imgMessageBoxLoading.setVisible(false);
			
			Label lblTitulo = new Label("lblTitulo", new PropertyModel<String>(MessageBox.this, "titulo"));
			Label lblMensagem = new Label("lblMensagem",  new PropertyModel<String>(MessageBox.this, "mensagem"));
			Label lblConfirmar = new Label("lblConfirmar", new PropertyModel<String>(MessageBox.this, "lblConfirmar"));
			Label lblCancelar = new Label("lblCancelar", new PropertyModel<String>(MessageBox.this, "lblCancelar"));
			Label lblIcon = new Label("lblIcon","");
			lblIcon.add(new AttributeModifier("class", new PropertyModel<String>(MessageBox.this, "lblIcon")));
			
			btnConfirmar.add(lblConfirmar);
			btnCancelar.add(lblCancelar);
			
			grpModal.add(lblIcon);
			grpModal.add(lblTitulo);
			grpModal.add(lblMensagem);
			grpModal.add(imgMessageBoxLoading);
			grpModal.add(btnConfirmar);
			grpModal.add(btnCancelar);
			
			add(grpModal);
		}
		
		
		
		public FrmModalMessageBox(String id, MessageBoxLoadingGif messageBoxLoadingGif, String titulo){
			super(id);
			
			grpModal.setOutputMarkupId(true);
			grpModal.add(new AttributeModifier("class", "modal-block modal-header-color modal-block-primary mfp-hide"));
					
			Image imgMessageBoxLoading = new Image("imgMessageBoxLoading", new ContextRelativeResource(messageBoxLoadingGif.getValue()));
			Label lblTitulo = new Label("lblTitulo", Model.of(getTitulo()));
			Label lblMensagem = new Label("lblMensagem",  "");
			lblMensagem.setVisible(false);
			Label lblConfirmar = new Label("lblConfirmar", "");
			Label lblCancelar = new Label("lblCancelar", "");
			Label lblIcon = new Label("lblIcon","");
			lblIcon.add(new AttributeModifier("class", getLblIcon()));
			
			AjaxButton btnConfirmar = new AjaxButton("btnConfirmar") {
				private static final long serialVersionUID = 1L;
			};		
			btnConfirmar.setVisible(false);
			
			Button btnCancelar = new Button("btnCancelar") {
				private static final long serialVersionUID = 1L;
			};
			btnCancelar.setVisible(false);
			
			btnConfirmar.add(lblConfirmar);
			btnCancelar.add(lblCancelar);
			
			grpModal.add(lblIcon);
			grpModal.add(lblTitulo);
			grpModal.add(lblMensagem);
			grpModal.add(imgMessageBoxLoading);
			grpModal.add(btnConfirmar);
			grpModal.add(btnCancelar);
			
			add(grpModal);
		}
	}	

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public MessageBoxTipo getMessageBoxTipo() {
		return messageBoxTipo;
	}

	public void setMessageBoxTipo(MessageBoxTipo messageBoxTipo) {
		this.messageBoxTipo = messageBoxTipo;
	}

	public MessageBoxButtons getMessageBoxButtons() {
		return messageBoxButtons;
	}

	public void setMessageBoxButtons(MessageBoxButtons messageBoxButtons) {
		this.messageBoxButtons = messageBoxButtons;
	}

	public String getLblConfirmar() {
		return lblConfirmar;
	}

	public void setLblConfirmar(String lblConfirmar) {
		this.lblConfirmar = lblConfirmar;
	}

	public String getLblCancelar() {
		return lblCancelar;
	}

	public void setLblCancelar(String lblCancelar) {
		this.lblCancelar = lblCancelar;
	}

	/**
	 * Se o messagebox for fechado através do botão <b>confirmar</b>, <b>sim</b> ou <b>ok</b> esse boolean é carregado com true.
	 * <br/>
	 * Se não ele é carregado com false, quando é clicado no <b>cancelar</b>, <b>fechar</b>, <b>não</b>.
	 * <br/>
	 * <b>false</b> é o valor default.
	 * @return boolean resultado do message box
	 */
	public boolean isDialogResult() {
		return dialogResult;
	}

	public void setDialogResult(boolean dialogResult) {
		this.dialogResult = dialogResult;
	}

	public String getLblIcon() {
		return lblIcon;
	}

	public void setLblIcon(String lblIcon) {
		this.lblIcon = lblIcon;
	}

}