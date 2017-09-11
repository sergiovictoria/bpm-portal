package br.com.seta.processo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.OrNfplanilhalanctoDao;
import br.com.seta.processo.bean.dao.SolicitacaoPagamentoDao;
import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.constant.ConstantesSolicitacaoDePagamento;
import br.com.seta.processo.dto.BoletoSolicitacaoPagamento;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.Email;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.dto.RegistroHistSolicitacaoPagto;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.entity.OrNfdespesaEntity;
import br.com.seta.processo.exceptions.RegraDeNegocioException;
import br.com.seta.processo.exceptions.SolicitacaoPagamentoServiceException;
import br.com.seta.processo.exceptions.ValidacaoBeanException;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.solicitacaopagamento.VariaveisSolicitacaoPagamento;
import br.com.seta.processo.validacao.ValidadorBean;

/**
 * Conjunto de serviços referentes ao processo de solicitação de pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="SolicitacaoPagamentoService")
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SolicitacaoPagamentoService {
	
	private static final String SIM = "S";
	@Resource
	private SessionContext sessionContext;
	@Inject
	private ExecuteRestAPI restApi;
	@Inject
	private ValidadorBean validadorBean;
	@Inject
	private NfDespesaSolicitacaoPagtoService despesaSolicitacaoService;
	@Inject
	private NfItensDespesaSolicitacaoPagtoService nfItensDespesaSolicitacaoService;
	@Inject
	private OrNfplanilhalanctoDao nfplanilhalanctoDao;
	@Inject
	private SolicitacaoPagamentoDao solicitacaoPagamentoDao;
	@Inject
	private ConfiguracoesSolicitacaoPagamentoService configuracoesService;
	@Inject
	private OrNfvencimentos orNfvencimentos;
	@Inject
	private DocumentoDao documentoDao;
	
	public void enviaSolicitacaoPagamento(User usuario, String nomeProcesso, String versaoProcesso, SolicitacaoPagamento solicitacao, BoletoSolicitacaoPagamento boletoSolicPagto) throws ValidacaoBeanException, SolicitacaoPagamentoServiceException{
		validadorBean.valida(solicitacao, boletoSolicPagto);		
		
		String emailCSC = configuracoesService.buscaConfiguracaoSolicitacaoPagamento().getEmailNotificacao();
		RegistroHistSolicitacaoPagto registroHistorico = new RegistroHistSolicitacaoPagto(SolicitacaoPagamento.STATUS_EM_ANALISE, usuario.getUserName(), new Date(), null);
		solicitacao.setStatus(SolicitacaoPagamento.STATUS_EM_ANALISE);
		solicitacao.adicionaRegistroAoHistorico(registroHistorico);
		
		Map<String, Object> variaveisProcesso = new HashMap<String, Object>();
		variaveisProcesso.put(VariaveisSolicitacaoPagamento.SOLICITACAO_PAGAMENTO, solicitacao);
		variaveisProcesso.put(VariaveisSolicitacaoPagamento.EMAIL_CSC, new Email(emailCSC));
		
		try {
			ProcessInstance processInstance = restApi.initCaseInstanceWithVariable(usuario.getUserName(), usuario.getPassword(), variaveisProcesso, nomeProcesso, versaoProcesso);
			long caseId = processInstance.getId();
			solicitacao.setCaseId(caseId);
			Documento documento = boletoSolicPagto.getBoleto();
			documento.setCaseId(caseId);
			documento.setDescricao(ConstantesSolicitacaoDePagamento.BOLETO_PAGAMENTO);
			
			documentoDao.salvaDocumento(documento);
			solicitacaoPagamentoDao.save(solicitacao);
			
		} catch (BonitaException e) {
			throw new SolicitacaoPagamentoServiceException(e);
		}
	}
	
	public void salvaSolicitacaoPagamento(User usuario, long taskId, SolicitacaoPagamento solicitacao) throws ValidacaoBeanException, SolicitacaoPagamentoServiceException{
		validadorBean.valida(solicitacao);
		Map<String, Serializable> variaveisProcesso = new HashMap<String, Serializable>();
		variaveisProcesso.put(VariaveisSolicitacaoPagamento.SOLICITACAO_PAGAMENTO, solicitacao);
		try {
			restApi.executeUpdateVariable(usuario, taskId, variaveisProcesso);
		} catch (BonitaException e) {
			throw new SolicitacaoPagamentoServiceException(e);
		}		
	}
	
	/**
	 * Aprova uma solicitação de pagamento 
	 * Passos: <br>
	 * -Integra a solicitação de pagamento com a CONSINCO <br>
	 * -Integra com o Bonita BPM para o andamento do fluxo do processo de Solicitação de Pagamento
	 * 
	 * @param usuario
	 * @param taskId
	 * @param solicitacao
	 * @return Número da nota fiscal de despesa criada na CONSINCO (SEQNOTA)
	 * @throws SolicitacaoPagamentoServiceException Qualquer exceção que não seja ligada a validação de dados da solicitação de pagamento ou regra de negócio
	 * @throws ValidacaoBeanException Quando ocorre um erro de validação com os dados da Solicitação de Pagamento
	 * @throws RegraDeNegocioException Quando alguma regra de negócio/lógica de negócio é violada
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public long aprovaSolicitacaoPagamento(User usuario, long taskId, SolicitacaoPagamento solicitacao) throws SolicitacaoPagamentoServiceException, ValidacaoBeanException, RegraDeNegocioException{
		validadorBean.valida(solicitacao);
		try {
			long seqNota = integraComConsinco(solicitacao);
			String comentarios = "ID SEQ NOTA C5: " + seqNota;
			RegistroHistSolicitacaoPagto registroHistorico = new RegistroHistSolicitacaoPagto(SolicitacaoPagamento.STATUS_APROVADO, usuario.getUserName(), new Date(), comentarios);
			solicitacao.setSeqNota(seqNota);
			solicitacao.setStatus(SolicitacaoPagamento.STATUS_APROVADO);
			solicitacao.adicionaRegistroAoHistorico(registroHistorico);
			
			solicitacaoPagamentoDao.save(solicitacao);
			
			Map<String, Serializable> variaveisProcesso = new HashMap<String, Serializable>();
			variaveisProcesso.put(VariaveisSolicitacaoPagamento.SOLICITACAO_PAGAMENTO, solicitacao);			
			restApi.executeFlowAndUpdateVariable(usuario, taskId, variaveisProcesso);
			return seqNota;
		}catch(RegraDeNegocioException regraDeNegocioException){
			sessionContext.setRollbackOnly();
			throw regraDeNegocioException;
		} catch(BonitaException exception){
			sessionContext.setRollbackOnly();
			throw new SolicitacaoPagamentoServiceException(exception);
		}
	}
	
	public void rejeitaSolicitacaoPagamento(User usuario, long taskId, SolicitacaoPagamento solicitacao, String comentarios) throws SolicitacaoPagamentoServiceException{
		RegistroHistSolicitacaoPagto registroHistorico = new RegistroHistSolicitacaoPagto(SolicitacaoPagamento.STATUS_REJEITADO, usuario.getUserName(), new Date(), comentarios);
		solicitacao.setStatus(SolicitacaoPagamento.STATUS_REJEITADO);		
		solicitacao.adicionaRegistroAoHistorico(registroHistorico);
		
		solicitacaoPagamentoDao.save(solicitacao);
		
		Map<String, Serializable> variaveisProcesso = new HashMap<String, Serializable>();
		variaveisProcesso.put(VariaveisSolicitacaoPagamento.SOLICITACAO_PAGAMENTO, solicitacao);		
		
		try {
			restApi.executeFlowAndUpdateVariable(usuario, taskId, variaveisProcesso);
		} catch (BonitaException e) {
			throw new SolicitacaoPagamentoServiceException(e);
		}
	}
	
	/**
	 * Efetua a integração da Solicitação de Pagamento com a CONSINCO.<br>
	 * Passos: <br>
	 * Inserir um novo registro na tabela OR_NFDESPESA <br>
	 * Inserir um novo registro na tabela OR_NFITENSDESPESA <br>
	 * Inserir um novo registro na tabela OR_NFPLANILHALANCTO
	 * 
	 * @param solicitacao
	 * @return Número da nota fiscal de despesa criada na CONSINCO (SEQNOTA)
	 * @throws RegraDeNegocioException 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	private long integraComConsinco(SolicitacaoPagamento solicitacao) throws RegraDeNegocioException{
		OrNfdespesaEntity nfDespesa = despesaSolicitacaoService.criaNovaNfDespesa(solicitacao);
		if(exigeItensNotFiscal(nfDespesa)){
			nfItensDespesaSolicitacaoService.criaNovoNfItemDespesa(nfDespesa);
		}
//		Integer codHistorico = solicitacao.getCodNaturezaDespesa();		
//		Integer nroEmpresa = solicitacao.getNroEmpresa();
		long seqNota = nfDespesa.getSeqnota();
		
		//INSERE OS REGISTROS DE CONTABILIZACAO
		for(OrReqplanilhalancto lcto : solicitacao.getOrReqplanilhalanctos()) {
			nfplanilhalanctoDao.insereNovoRegistroSolicitacaoPagamento(seqNota, lcto);
		}
		
		//INSERE OS REGISTROS DO FINANCEIRO
		for(OrSolicitacaovencto dcto : solicitacao.getOrSolicitacaovenctos()) {
			orNfvencimentos.insereNovoRegistroSolicitacaoPagamento(seqNota, solicitacao.getNroEmpresa(), solicitacao.getOrReqplanilhalanctos().get(0).getContadebito(), solicitacao.getCodNaturezaDespesa(), dcto);
		}
		
		return seqNota;
	}

	private boolean exigeItensNotFiscal(OrNfdespesaEntity nfDespesa) {
		return nfDespesa.getExigeitensnota() != null && nfDespesa.getExigeitensnota().equals(SIM);
	}

}
