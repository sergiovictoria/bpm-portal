package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.inject.Inject;

import org.apache.wicket.PageReference;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.Requisicao;
import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.suprimentos.paginasFormulario.ExibirResumoIntencaoFormulario;

public class SecaoBotoesExibirResumoIntencao extends Panel {

	private static final long serialVersionUID = 1L;

	private AjaxButton voltarBtn, cancelarBtn, alterarRequisicaoBtn, abrirRequisicaoBtn;
	private SucessoModal sucessoModal;
	@Inject private Requisicao requisicao;
	private Map<String, Serializable> processVariables = new HashMap<String, Serializable>();
	private String comentariosAprovacao;
	private static final java.util.Date data = new java.util.Date();
	private static final String _TIPO_PAGAMENTO = "P";
	private static final String _SEPARATOR = "/";
	private static final String _TIPOCONTAB = "L";
	private BigDecimal _ZERO = BigDecimal.ZERO;
	private static final String _STATUS = "L"; 
	private static final String _USUARIO_ALTERACAO = "BPM-SETA";
	
	private ExibirResumoIntencaoFormulario parentPage;
	
	public SecaoBotoesExibirResumoIntencao(String id, final ExibirResumoIntencaoFormulario parentPage, final PageReference paginaAnterior) {
		super(id);

		this.parentPage = parentPage;
		
		sucessoModal = new SucessoModal("sucessoModal");
		
		voltarBtn = new AjaxButton("voltarBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				setResponsePage(paginaAnterior.getPage());
			}
		};

		cancelarBtn = new AjaxButton("cancelarBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = parentPage.getSolicitacao();
				solicitacaoIntencaoCompra.setStatus("Rejeitado");
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Cancelado");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					parentPage.executeFlowAndUpdateVariables(processVariables);
					parentPage.redirecionaParaPaginaDeAtividades();
				} catch (BonitaException e) {
					e.printStackTrace();
				}
			}

		};

		alterarRequisicaoBtn = new AjaxButton("alterarRequisicaoBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = parentPage.getSolicitacao();
				solicitacaoIntencaoCompra.setStatus("Alterar");
				adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Alterar");
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
				processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
				try {
					parentPage.executeFlowAndUpdateVariables(processVariables);
					parentPage.redirecionaParaPaginaDeAtividades();
				} catch (BonitaException e) {
					e.printStackTrace();
				}
			}
		};


		abrirRequisicaoBtn = new AjaxButton("abrirRequisicaoBtn") {
			private static final long serialVersionUID = 1L;
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				OrRequisicao orRequisicao = parentPage.getRequisicao();
				SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = parentPage.getSolicitacao();
				if (isValidoRateios(orRequisicao.getOrReqplanilhalanctos(),parentPage.getRateiosValidados())) {
					orRequisicao.getOrReqplanilhalanctos().clear();
					orRequisicao.setOrReqplanilhalanctos(parentPage.getRateiosValidados());
					try {
						orRequisicao = montoRequisicao(orRequisicao, solicitacaoIntencaoCompra);
						requisicao.criaRequsicao(orRequisicao);
						Long nrorequisicao = requisicao.findRequsicaoNroSeqRequisicao(orRequisicao.getSeqrequisicao());
						orRequisicao.setNrorequisicao(nrorequisicao);
						solicitacaoIntencaoCompra.setStatus("Aprovado");
						adicionaPreenchimentoAoHistorico(solicitacaoIntencaoCompra,"Abrir");
						solicitacaoIntencaoCompra.setDataInclusaoC5(new java.util.Date());
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA,solicitacaoIntencaoCompra);
						processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO,orRequisicao);
						try {
							parentPage.executeFlowAndUpdateVariables(processVariables);
							ocultarModal("#abrirRequisicaoModal", target);
							sucessoModal.setNroRequisicao(nrorequisicao).exibe(target);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}catch(Exception e) {
						e.printStackTrace();						
						 parentPage.setMensagemErro("Erro ao tentar enviar para consinco",target);
					}
				}else{
					ocultarModal("#abrirRequisicaoModal", target);
					parentPage.setMensagemErro("Existe centros de custo sem validação de CR",target);
				}
			}
		};		
		
		TextArea<String> comentariosAprovacao = new TextArea<String>("comentariosAprovacao", new PropertyModel<String>(this,"comentariosAprovacao"));
		add(voltarBtn, cancelarBtn, alterarRequisicaoBtn, abrirRequisicaoBtn, sucessoModal, comentariosAprovacao);
		
	}	
	
	private void ocultarModal(String idModal, AjaxRequestTarget target){
		target.appendJavaScript("$('"+ idModal +"').modal('hide')");
	}
	
	private void exibiModal(String idModal, AjaxRequestTarget target){
		target.appendJavaScript("$('"+ idModal +"').modal('show')");
	}
	
	private class SucessoModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private Label nroRequisicaoLbl = new Label("nroRequisicao", Model.of(""));
		private AjaxButton sucessoOkBtn;
		
		public SucessoModal(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			sucessoOkBtn = new AjaxButton("sucessoOkBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					parentPage.redirecionaParaPaginaDeAtividades();
				}
			};
			
			add(nroRequisicaoLbl, sucessoOkBtn);
		}
		
		public SucessoModal exibe(AjaxRequestTarget target){
			exibiModal("#sucessoModal", target);
			target.add(this);
			return this;
		}
		
		
		public SucessoModal setNroRequisicao(Long nroRequisicao){
			nroRequisicaoLbl.setDefaultModelObject(nroRequisicao);
			return this;
		}
		
	}
	
	private void adicionaPreenchimentoAoHistorico(SolicitacaoIntencaoCompra solicitacao, String intencao) {
		Historico historico = new Historico();
		historico.setNome(getUser().getUserName());
		historico.setMotivo("");
		historico.setComentario(this.comentariosAprovacao);
		if (intencao.equals("Abrir")) {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_ABRIR_REQUISICAO);
		}else if (intencao.equals("Alterar")) {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_ALTERAR_REQUISICAO);
		}else {
			historico.setStatus(StatusSolicitacaoIntencaoCompra.SOLICITANTE_CANCELA_REQUISICAO);
		}
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

	private User getUser() {
		return (User) Session.get().getAttribute("user");
	}
    
	private OrRequisicao montoRequisicao(OrRequisicao orRequisicao, SolicitacaoIntencaoCompra solicitacaoIntencaoCompra) {
		
		orRequisicao.setDtacompra(data);
		orRequisicao.setDtainclusao(data);
		orRequisicao.setStatus(_STATUS);
		orRequisicao.setUsualteracao(_USUARIO_ALTERACAO);
		String line = orRequisicao.getPrazopagto();
		StringTokenizer stz = new StringTokenizer(line,_SEPARATOR);
		Short qtdparcela = new Short(Integer.toString(stz.countTokens()));
		orRequisicao.setQtdparcela(qtdparcela);
		
		
		String observacao = orRequisicao.getObservacao();
		if (observacao!=null) {
			orRequisicao.setObservacao("");
		}
		orRequisicao.setObservacao("Nr. Intenção Compra  "+solicitacaoIntencaoCompra.getNumeroIntencaoSolicitacaoCompra()+ " Telefone  "+solicitacaoIntencaoCompra.getFoneSolicitante()+"  Solicitante "+solicitacaoIntencaoCompra.getNomeSolicitante()+ " | "+
		observacao);
		orRequisicao.setTipopgto(_TIPO_PAGAMENTO);
		
		/****
		 *  Validar itens de Despesa
		 */
		Set<OrReqitensdespesa> orReqitensdespesas = new HashSet<OrReqitensdespesa>();
		Short item = new Short("1");
		
		for (OrReqitensdespesa dto: orRequisicao.getOrReqitensdespesas() ) {
			dto.setVlrdesconto(_ZERO);
			dto.setVlracrescimos(_ZERO);
			dto.setComplemento(_ZERO);
			dto.setNroitem(item);
			orReqitensdespesas.add(dto);
			item++;
		}
		orRequisicao.setOrReqitensdespesas(orReqitensdespesas);
		
		/*****
		 * Validar Vencimentos
		 */
		
		Set<OrRequisicaovencto> orRequisicaovenctos = new HashSet<OrRequisicaovencto>();
		Short parcela = new Short("1");
		for (OrRequisicaovencto dto : orRequisicao.getOrRequisicaovenctos()) {
			dto.setNroparcela(parcela);
			orRequisicaovenctos.add(dto);
			parcela++;
		}
		orRequisicao.setOrRequisicaovenctos(orRequisicaovenctos);
		
		/***
		 * Validar Planilha
		 */
		Short linha = new Short("1");
		Set<OrReqplanilhalancto> orReqplanilhalanctos = new HashSet<OrReqplanilhalancto>();
		for (OrReqplanilhalancto dto : orRequisicao.getOrReqplanilhalanctos()) {
			dto.setNrolinha(linha);
			dto.setTipocontab(_TIPOCONTAB);
			dto.setFilial(orRequisicao.getNroempresa());
			orReqplanilhalanctos.add(dto);
			linha++;
		}
		
		orRequisicao.setOrReqplanilhalanctos(orReqplanilhalanctos);
		
		
		return orRequisicao;
	}

	private Boolean isValidoRateios(Set<OrReqplanilhalancto> orReqplanilhalanctos,  Set<OrReqplanilhalancto> rateiosValidados) {
		if (orReqplanilhalanctos.size()==rateiosValidados.size()) {
			return Boolean.TRUE;
		}else {
			return Boolean.FALSE;
		}
	}

}
