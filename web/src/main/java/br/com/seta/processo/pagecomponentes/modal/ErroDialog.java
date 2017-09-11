package br.com.seta.processo.pagecomponentes.modal;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.RepeatingView;

public class ErroDialog extends Panel {
	private static final long serialVersionUID = 1L;

	private Modal modal;
	private RepeatingView mensagensErro;
	private Label cabecalhoMsgErro;

	private CorpoModalErroFragment corpoModalFragment;
	private BotaoFecharFragment botaoFecharFragment;
	
	public ErroDialog(String id) {
		this(id, "modalErro");
	}
	
	public ErroDialog(String id, String idHtml){
		super(id);
		
		corpoModalFragment = new CorpoModalErroFragment();
		botaoFecharFragment = new BotaoFecharFragment();
		
		modal = new Modal("modalErro", idHtml, Modal.ERRO, corpoModalFragment, "Erro", botaoFecharFragment);
		
		add(modal);
	}
	
	public ErroDialog setTitulo(String titulo){
		modal.setTitulo(titulo);
		return this;
	}
	
	public ErroDialog setCabecalhoMensagemErro(String cabecalho){
		cabecalhoMsgErro.setDefaultModelObject(cabecalho);
		return this;
	}

	public ErroDialog addMensagemErro(String mensagem) {
		addAListaDeMensagens(mensagem);
		return this;
	}

	private void addAListaDeMensagens(String mensagem) {
		String id = this.mensagensErro.newChildId();
		this.mensagensErro.add(new Label((id), mensagem));
	}

	public ErroDialog addMensagensErro(String... mensagens) {
		for (String mensagem : mensagens) {
			addAListaDeMensagens(mensagem);
		}
		return this;
	}

	public ErroDialog addMensagensErro(List<String> mensagens) {
		String[] mensagensArr = new String[mensagens.size()];
		addMensagensErro(mensagens.toArray(mensagensArr));
		return this;
	}

	public ErroDialog removeTodasMensagens() {
		this.mensagensErro.removeAll();
		return this;
	}
	
	public ErroDialog addFuncaoJsNoClickDoBtnFechar(String js){
		Button okBtn = botaoFecharFragment.getOkBtn();
		okBtn.add(AttributeAppender.remove("onclick"))
			.add(AttributeAppender.append("onclick", js));
		
		return this;
	}

	public ErroDialog exibe(AjaxRequestTarget target) {
		target.add(modal);
		modal.exibe(target);
		return this;
	}

	public ErroDialog oculta(AjaxRequestTarget target) {
		modal.oculta(target);
		return this;
	}

	private class CorpoModalErroFragment extends Fragment {
		private static final long serialVersionUID = 1L;

		public CorpoModalErroFragment() {
			super("corpoModal", "corpoModalAnexoFragment", ErroDialog.this);

			cabecalhoMsgErro = new Label("cabecalhoMsgErro");
			mensagensErro = new RepeatingView("msgsErroRepeatingView");
			
			add(cabecalhoMsgErro, mensagensErro);
		}
	}
	
	private class BotaoFecharFragment extends Fragment{
		private static final long serialVersionUID = 1L;
		
		private Button okBtn;
		
		public BotaoFecharFragment() {
			super("botao", "botaoFecharFragment", ErroDialog.this);		
			
			okBtn = new Button("okBtn");
			
			add(okBtn);
		}
		
		public Button getOkBtn(){
			return this.okBtn;
		}
		
	}

}
