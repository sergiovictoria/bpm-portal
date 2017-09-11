package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.VerificaContratosIntes;
import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.ItensContrato;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.helper.WicketHelper;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;
import br.com.seta.processo.suprimentos.componentespagina.abas.SecaoFaturamentoVisualizacao;
import br.com.seta.processo.validacao.ValidadorBean;

/**
 * Seção com os botões de aprovação
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SecaoBotoesAprovacao extends Panel {
	private static final long serialVersionUID = 1L;

	private AjaxButton voltarBtn;
	private AjaxButton aprovarBtn;
	private AjaxButton rejeitarBtn;
	private AprovacaoHierarquicaModal secaoNecessidadeAprovHierarquica;
	private String comentariosAprovacao;
	Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	@Inject 
	private ExecuteRestAPI executeRestAPI;
	@Inject 
	private VerificaContratosIntes verificaContratosIntes;
	@Inject
	private ValidadorBean validador;
	@Inject 
	private DocumentoDao documentoDao;
	private FormularioIntencaoCompraTemplate parentPage;


	public SecaoBotoesAprovacao(String id, final PageReference paginaAnterior, final FormularioIntencaoCompraTemplate parentPage) {
		super(id);

		this.parentPage = parentPage;
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};


		/***
		 * Botão Arpovado
		 */
		aprovarBtn = new AjaxButton("aprovarBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				solicitacaoIntencaoCompra.setCanceladoRejeitadoAprovFunc(Boolean.FALSE);
				solicitacaoIntencaoCompra.setIdAprovadorFuncional(getUser().getIdUser());
				solicitacaoIntencaoCompra.setIdUsuarioLogado(getUser().getIdUser());
												
				if(solicitacaoIntencaoCompra.getNecessidadeAprvHierarq()){
					solicitacaoIntencaoCompra.setSituacaoAprovador("Hierarquico");
				}else {
					solicitacaoIntencaoCompra.setSituacaoAprovador("Aprovado");
					solicitacaoIntencaoCompra.setStatus("Aprovado");
				}


				if (solicitacaoIntencaoCompra.getTipoDespesa().equals("Serviço")){
					List<ItensContrato> itensSemContrato = verificaContratosIntes.saveContratosPendentes(orRequisicao, orRequisicao.getNumeroIntencaoSolicitacaoCompra());
					if (itensSemContrato==null) {
						solicitacaoIntencaoCompra.setIsContratoPendente(Boolean.FALSE);
					}else  {
						solicitacaoIntencaoCompra.setIsContratoPendente(Boolean.TRUE);
					}
				}				
				
				verificaSeExisteFornecedorOuItensPendentesDeCadastro(solicitacaoIntencaoCompra, orRequisicao);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.TRUE);

				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);			
				
				try {
					validador.valida(parentPage.getRequisicao());
					
					Documento orcamento = getOrcamento();
					if(orcamento != null){
						documentoDao.salvaDocumento(orcamento, parentPage.getCaseId());
					}
					
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				}catch(ValidacaoBeanException e){
					target.appendJavaScript("$('#aprovacaoModal').modal('hide');");
					parentPage.setMensagensErro(e.getMessages(), target);
				} catch (BonitaException e) {
					throw new RuntimeException(e);
				}
				
			}
			private void verificaSeExisteFornecedorOuItensPendentesDeCadastro(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra,
					OrRequisicao orRequisicao) {
				
				if (orRequisicao.getSeqpessoa()==null) 
					solicitacaoIntencaoCompra.setIsFornecedorPendente(true);
				else
					solicitacaoIntencaoCompra.setIsFornecedorPendente(false);
					
				solicitacaoIntencaoCompra.setIsItensPendente(false);
				for(OrReqitensdespesa item : orRequisicao.getOrReqitensdespesas()){
					if(item.getCodproduto() == null || item.getCodproduto().trim().isEmpty()){
						solicitacaoIntencaoCompra.setIsItensPendente(true);
						break;
					}
				}
			}
		};

		rejeitarBtn = new AjaxButton("rejeitarBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra =  parentPage.getSolicitacao();
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				solicitacaoIntencaoCompra.setCanceladoRejeitadoAprovFunc(Boolean.FALSE);
				solicitacaoIntencaoCompra.setNecessidadeAprvHierarq(Boolean.FALSE);
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra, Boolean.FALSE);
				solicitacaoIntencaoCompra.setStatus("Rejeitado");
				solicitacaoIntencaoCompra.setSituacaoAprovador("Rejeitado");
				solicitacaoIntencaoCompra.setIdUsuarioLogado(getUser().getIdUser());
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					executeRestAPI.executeFlowAndUpdateVariable(getUser(), parentPage.getTaskId(), processVariables);
					setResponsePage(Atividades.class);
				} catch (BonitaException e) {
					e.printStackTrace();
				}
			}
		};

		secaoNecessidadeAprovHierarquica = new AprovacaoHierarquicaModal("aprovacaoHierarquicaModal",parentPage.getSolicitacao());

		add(voltarBtn, aprovarBtn, rejeitarBtn, secaoNecessidadeAprovHierarquica);
	}

	private class AprovacaoHierarquicaModal extends WebMarkupContainer {

		private static final long serialVersionUID = 1L;

		public AprovacaoHierarquicaModal(String id, SolicitacaoIntencaoCompra solicitacao) {
			super(id);

			List<Boolean> valores = new ArrayList<Boolean>();
			valores.add(true);
			valores.add(false);
			solicitacao.setNecessidadeAprvHierarq(Boolean.FALSE);
			RadioGroup<Boolean> necessidadeAprvHierarq = WicketHelper.criaRadioGroup("necessidadeAprvHierarq", new PropertyModel<Boolean>(solicitacao, "necessidadeAprvHierarq"), valores);
			TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(SecaoBotoesAprovacao.this,"comentariosAprovacao"));		
			add(necessidadeAprvHierarq,comentariosAprovacao);

		}

	}

	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, Boolean aprovadoRejeitado) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		if (aprovadoRejeitado) {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.APROVADO_FUNCIONAL_APROVADO);
		}else {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.APROVADO_FUNCIONAL_REJEITADO);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return 	(User) Session.get().getAttribute("user");

	}
	
	private Documento getOrcamento(){
		SecaoFaturamentoVisualizacao sf = (SecaoFaturamentoVisualizacao) this.parentPage.getSecaoFaturamento();
		return sf.getOrcamento();
	}

}
