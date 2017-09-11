package br.com.seta.processo.suprimentos.componentespagina.secoesBotoes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.html.panel.Panel;
import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.constant.StatusSolicitacaoIntencaoCompra;
import br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.model.SolicitacaoCompraModel;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.suprimentos.paginasSolicitacao.SolicitarIntencaoCompra;
import br.com.seta.processo.validacao.ValidadorBean;
import br.com.seta.widgetswicketprocesso.modals.loading.LoadingModal;

public class SecaoBotoesEnviarSolicitacaoIntencaoCompra extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject 
	private ValidadorBean validador;
	@Inject 
	private ExecuteRestAPI restApi;
	
	private SolicitacaoIntencaoCompra solicitacao;	
	private BonitaPage parentPage;

	private AjaxButton enviarBtn;
	private LoadingModal bloqueioModal;
	
	public SecaoBotoesEnviarSolicitacaoIntencaoCompra(String id, final SolicitacaoIntencaoCompra solicitacao, final SolicitacaoCompraModel model, final SolicitarIntencaoCompra parentPage) {
		super(id);
		this.parentPage = parentPage;
		this.solicitacao = solicitacao;
		
		bloqueioModal = new LoadingModal("bloqueioModal", "bloqueioModal");
		enviarBtn = new AjaxButton("enviarBtn") {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected String getOnClickScript() {
				return "$('#envioSolicitacaoModal').modal('hide'); " + bloqueioModal.getExibeModalFunction();
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				
				System.out.println(" User "+getUser().getUserName());
				
				solicitacao.setAreaSolicitante(model.getGroupSelected());
				solicitacao.setComprador(model.getCompradorSelected());
				solicitacao.setDescricaoCompra(model.getDescricaoCompra());
				solicitacao.setDiretoria(model.getDiretoriaSelected());
				solicitacao.setEmailRespPreenchimento(model.getEmailRespPreenchimento());
				solicitacao.setGerencia(model.getGerenciaSelected());
				solicitacao.setNomeRespPreenchimento(model.getNomeRespPreenchimento());
				solicitacao.setTelRespPreenchimento(model.getTelRespPreenchimento());
				String nomeSolicitante = model.getUserSelected() == null ? null : model.getUserSelected().getUserName();
				solicitacao.setNomeSolicitante(nomeSolicitante);
				solicitacao.setFoneSolicitante(model.getFoneSolicitante());
				solicitacao.setEmailSolicitante(model.getEmailSolicitante());
				solicitacao.setFoneSolicitante(model.getFoneSolicitante());
				solicitacao.setNomeAprovadorHierarquio(model.getNomeAprovadorHierarquio());
				solicitacao.setEmailAprovadorHierarquio(model.getEmailAprovadorHierarquio());
				solicitacao.setIdAprovadorHierarquio(model.getIdAprovadorHierarquio());
				solicitacao.setEmailJuridicos(model.getEmailJuridicos());
				solicitacao.setMailCSCJuridicos(model.getMailCSCJuridicos());
				solicitacao.setEmailsAreaCadastros(model.getEmailsAreaCadastros());
				solicitacao.setEmailsAutomacaos(model.getEmailsAutomacaos());
				solicitacao.setEmailsAprovadorSubstitutos(model.getEmailsAprovadorSubstitutos());
				solicitacao.setIdUsuarioLogado(model.getIdUsuarioLogado());
				solicitacao.setTipoDespesa(model.getTipoDespesaSelected());
				solicitacao.setIdGrupoCSC(model.getIdGrupoCSC());
				solicitacao.setIdGrupoCSCIntegracao(model.getIdGrupoCSCIntegracao());
				solicitacao.setIdGrupoCSCJuridico(model.getIdGrupoCSCJuridico());
				solicitacao.setIdGrupoCSCValidacao(model.getIdGrupoCSCValidacao());
				solicitacao.setIdResponsavelPreenchimento(model.getIdResponsavelPreenchimento());
				adicionaPreenchimentoAoHistorico();
				OrRequisicao requisicao = new OrRequisicao();
				requisicao.setDataSolicitacaoIntencao(new java.util.Date());
				requisicao.setTipoDespesa(model.getTipoDespesaSelected());
				requisicao.setDtainclusao(new Date());
				requisicao.setNomeSolicitanteBPM(model.getNomeRespPreenchimento());

				try {
					validador.valida(solicitacao);
				} catch (ValidacaoBeanException validacaException) {
					bloqueioModal.oculta(target);
					parentPage.setMensagensErro(validacaException.getMessages(), target);
					return;
				}
				
				ProcessInstance instanciaProcesso = iniciarInstanciaProcesso(solicitacao, requisicao);
				redirecionarParaProximaAtividade(parentPage, instanciaProcesso);	
			}
		};
		add(enviarBtn, bloqueioModal);
	}
	
	private ProcessInstance iniciarInstanciaProcesso(SolicitacaoIntencaoCompra solicitacao, OrRequisicao requisicao){
		Map<String, Object> processVariables = new HashMap<String, Object>();
		processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA, solicitacao);
		processVariables.put(VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO, requisicao);		
		User user = getUser();
		try {
			return restApi.initCaseInstanceWithVariable(user.getUserName(), user.getPassword(), processVariables, parentPage.getNomeProcesso(), parentPage.getVersaoProcesso());
		} catch (BonitaException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void redirecionarParaProximaAtividade(SolicitarIntencaoCompra parentPage, ProcessInstance instanciaProcesso){
		try{
			//Aguardar que seja criada uma nova instancia pela método 'iniciarInstanciaProcesso'
			Thread.sleep(1000*5); 
			List<HumanTask> hts = restApi.retriveHumanTaskListToCaseId(instanciaProcesso.getId(), 0, 1, getUser());
			HumanTask ht = hts.get(0);
			br.com.seta.processo.dto.Form taskForm = restApi.retriveTaskForm(parentPage.getUser(), Long.toString(instanciaProcesso.getProcessDefinitionId()) , ht.getName());
			
			String url = parentPage.obtemContexto() + taskForm.getUrl() + "?id=" + ht.getId();
			setResponsePage(new RedirectPage(url));
		}catch(Exception e){
			setResponsePage(Atividades.class);
		}
	}
	
	private User getUser() {
		return (User) Session.get().getAttribute("user");

	}
	
	public SecaoBotoesEnviarSolicitacaoIntencaoCompra(SolicitacaoIntencaoCompra solicitacao, SolicitacaoCompraModel model, SolicitarIntencaoCompra compra){
		this("secaoBotoesAcao", solicitacao, model, compra);
	}
	
	
	private void adicionaPreenchimentoAoHistorico() {
		Historico historico = new Historico();
		historico.setNome(solicitacao.getNomeRespPreenchimento());
		historico.setMotivo("");
		historico.setComentario("");
		historico.setStatus(StatusSolicitacaoIntencaoCompra.SOCITACAO_ABERTA);
		historico.setData(new Date());
		if(solicitacao.getHistorico() == null) 
			solicitacao.setHistorico(new ArrayList<Historico>());
		solicitacao.getHistorico().add(historico);
	}

}
