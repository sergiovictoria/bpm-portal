package br.com.seta.processo.recebimento.secoesBotoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.pagecomponentes.modal.Modal;
import br.com.seta.processo.recebimento.formulario.ClassificarImpostosFormulario;
import br.com.seta.processo.service.ExecuteRestAPI;

public class SecaoBotoesClassificarImpostos extends  Panel {
	private static final long serialVersionUID = 1L;

	private String comentarios;	
	private Modal modalDescricao;
	private AjaxButton voltarBtn;
	private SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = new SolicitacaoIntencaoCompra();
	private Long taskID;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject private ExecuteRestAPI executeRestAPI;
	
	public SecaoBotoesClassificarImpostos(String id, final PageReference paginaAnterior, ClassificarImpostosFormulario classificarImpostosFormulario) {
		super(id);
		
		solicitacaoIntencaoCompra = classificarImpostosFormulario.getSolicitacao();
		taskID =  classificarImpostosFormulario.getTaskId();
		
		modalDescricao = new Modal("modalDescricao", "modalDescricao", Modal.CONFIRMACAO, new CorpoModalFragment(), "Confirmação", new BotoesFragment());
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		add(modalDescricao, voltarBtn);
	}
	
	
	private class CorpoModalFragment extends Fragment {
		private static final long serialVersionUID = 1L;
		
		public CorpoModalFragment() {
			super("corpoModal", "corpoModalFragment", SecaoBotoesClassificarImpostos.this);
			TextArea<String> descricaoTxt = new TextArea<String>("descricaoTxt", new PropertyModel<String>(SecaoBotoesClassificarImpostos.this, "comentarios"));
			add(descricaoTxt);
		}		
	}
	
	private class BotoesFragment extends Fragment{
		private static final long serialVersionUID = 1L;

		private AjaxButton okButton;
		
		public BotoesFragment() {
			super("botao", "botoesFragment", SecaoBotoesClassificarImpostos.this);
			
			okButton = new AjaxButton("okButton") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					modalDescricao.oculta(target);
					
					adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra);
					processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
					try {
						executeRestAPI.executeFlowAndUpdateVariable(getUser(), taskID , processVariables);
						setResponsePage(Atividades.class);
					} catch (BonitaException e) {
						e.printStackTrace();
					}

					
				}
			};
			
			add(okButton);
		}
		
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentarios);
		historico.setStatus(VariaveisRecebimento.CLASSIFICAO_FISCAL_IMPOSTOS);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return 	(User) Session.get().getAttribute("user");

	}

}
