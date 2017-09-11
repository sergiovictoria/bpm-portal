package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.FormularioCadastroItensTemplate;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.processo.validacoesCheck.FornecedorItensCadastradosCheck;

public class SecaoBotoesCadastro extends Panel {
	private static final long serialVersionUID = 1L;
	
	private String comentariosAprovacao;
	Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject 
	private ExecuteRestAPI executeRestAPI;
	@Inject
	private ValidadorBean validador;	
	private AjaxButton solicitarMaisInfoBtn, submeterAlteracoesBtn, voltarBtn;
	
	public SecaoBotoesCadastro(String id, final PageReference paginaAnterior, final FormularioCadastroItensTemplate parentPage) {
		super(id);
		
		solicitarMaisInfoBtn = new AjaxButton("solicitarMaisInfoBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				solicitacaoIntencaoCompra.setIsFornecedorPendente(Boolean.TRUE);
				solicitacaoIntencaoCompra.setIsItensPendente(Boolean.TRUE);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.TRUE);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
			}

		};
		
		submeterAlteracoesBtn = new AjaxButton("submeterAlteracoesBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				solicitacaoIntencaoCompra.setIsFornecedorPendente(Boolean.FALSE);
				solicitacaoIntencaoCompra.setIsItensPendente(Boolean.FALSE);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,Boolean.FALSE);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					validador.valida(orRequisicao, FornecedorItensCadastradosCheck.class);
					
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				}catch(ValidacaoBeanException e){
					target.appendJavaScript("$('#enviarModal').modal('hide')");
					parentPage.setMensagensErro(e.getMessages(), target);
				}
				catch (BonitaException e) {
					throw new RuntimeException(e);
				}
				
			}
		};
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(solicitarMaisInfoBtn, submeterAlteracoesBtn, voltarBtn,comentariosAprovacao);
		
	}
	
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean informacao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		if (!informacao) {
		     historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_CADASTRADO_SUCESSO);
		}else {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_MAIS_INFORMACOES);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}


	private User getUser() {
		return 	(User) Session.get().getAttribute("user");
	}

}
