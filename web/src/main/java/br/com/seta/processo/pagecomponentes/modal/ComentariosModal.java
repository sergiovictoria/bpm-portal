package br.com.seta.processo.pagecomponentes.modal;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;

public class ComentariosModal extends Panel {
	private static final long serialVersionUID = 1L;
	
	private String comentarios;
	private ComentariosDialog comentariosDialog;
	
	public ComentariosModal(String id, String idHtml, Component botaoOk) {
		super(id);
		comentariosDialog = new ComentariosDialog(idHtml, botaoOk);
		
		add(comentariosDialog);
	}
	
	public String getComentarios(){
		return this.comentarios;
	}
	
	public ComentariosModal exibe(AjaxRequestTarget target){
		this.comentariosDialog.exibe(target);
		return this;
	}
	
	public ComentariosModal oculta(AjaxRequestTarget target){
		this.comentariosDialog.oculta(target);
		return this;
	}
	
	private class ComentariosDialog extends Modal{
		private static final long serialVersionUID = 1L;

		public ComentariosDialog(String idHtml, Component botaoOk) {
			super("comentariosDialog", idHtml);
			setTitulo("Comentários")
			.adicionaCorpo(new CorpoModalFragment())
			.adicionaBotao(new Fragment("botao", "botaoFecharFragment", ComentariosModal.this))
			.adicionaBotao(botaoOk);					
		}
		
	}
	
	private class CorpoModalFragment extends Fragment{
		private static final long serialVersionUID = 1L;
		
		private TextArea<String> comentariosTxt;
		
		public CorpoModalFragment() {
			super("corpoModal", "corpoModalFragment", ComentariosModal.this);
			
			comentariosTxt = new TextArea<String>("comentariosTxt", new PropertyModel<String>(ComentariosModal.this, "comentarios"));
			
			add(comentariosTxt);
		}
		
	}

}
