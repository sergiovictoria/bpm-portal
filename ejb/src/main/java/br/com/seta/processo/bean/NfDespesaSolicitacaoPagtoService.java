package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.bean.dao.OrNfdespesaDao;
import br.com.seta.processo.bean.dao.OrParametroDao;
import br.com.seta.processo.bean.dao.RfParamnatnfdespDao;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.entity.OrNfdespesaEntity;
import br.com.seta.processo.entity.OrParametroEntity;
import br.com.seta.processo.entity.RfParamnatnfdespEntity;
import br.com.seta.processo.interceptor.LogInterceptor;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name = "NfDespesaSolicitacaoPagtoService")
@LocalBean
@Interceptors({LogInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class NfDespesaSolicitacaoPagtoService {
	
	private static final int COD_ENERGIA_ELETRICA = 1;
	private static final String NF_CONTA_ENERGIA_ELETRICA = "06";
	private static final String NF_MODELO_1A = "01";
	
	private static final String NAO = "N";
	private static final String NF = "NF";
	private static final String SERIE = "0";
	private static final String TIPOPGTO = "P";
	private static final String TIPOTRIBUTACAO = "O";
	private static final String TIPOTRIBUTACAOIPI = "O";
	private static final String TRIBUTCONFINS = "N";
	private static final String TRIBUTOICMS = "N";
	private static final String TRIBUTPIS = "N";
	private static final String USUALTERACAO = "BPMSETA";
	private static final String USUINCLUSAO = "BPMSETA";
	private static final BigDecimal VLRACRESCIMOS = BigDecimal.ZERO;
	private static final BigDecimal VLRBASEICMS = BigDecimal.ZERO;
	private static final BigDecimal VLRCONFINS = BigDecimal.ZERO;
	private static final BigDecimal VLRCOFINSCRED = BigDecimal.ZERO;
	private static final BigDecimal VLRCSSLL = BigDecimal.ZERO;
	private static final BigDecimal VLRDESCONTOS = BigDecimal.ZERO;
	private static final BigDecimal VLRICMS = BigDecimal.ZERO;
	private static final BigDecimal VLRICMSDIF = BigDecimal.ZERO;
	private static final BigDecimal VLRICMSRETTRANSP = BigDecimal.ZERO;
	private static final BigDecimal VLRINSS = BigDecimal.ZERO;
	private static final BigDecimal VLRINSSPAT = BigDecimal.ZERO;
	private static final BigDecimal VLRINSSPESFIS = BigDecimal.ZERO;
	private static final BigDecimal VLRIPI = BigDecimal.ZERO;
	private static final BigDecimal VLRIR = BigDecimal.ZERO;
	private static final BigDecimal VLRIRPESFIS = BigDecimal.ZERO;
	private static final BigDecimal VLRISENTO = BigDecimal.ZERO;
	private static final BigDecimal VLRISENTOIPI = BigDecimal.ZERO;
	private static final BigDecimal VLRISS = BigDecimal.ZERO;
	private static final BigDecimal VLRISSST = BigDecimal.ZERO;
	private static final BigDecimal VLROUTRASOPDESC = BigDecimal.ZERO;
	private static final BigDecimal VLROUTROSIPI = BigDecimal.ZERO;
	private static final BigDecimal VLRPIS = BigDecimal.ZERO;
	private static final BigDecimal VLRPISCRED = BigDecimal.ZERO;
	private static final BigDecimal VLRSESTSENAT = BigDecimal.ZERO;
	
	@Inject
	private RfParamnatnfdespDao paramnatnfdespDao;
	@Inject
	private OrNfdespesaDao orNfdespesaDao;
	@Inject
	private OrParametroDao orParametroDao;
	@Inject
	private GePessoas gePessoasDao;
	
	/**
	 * Cria uma nova entidade (entrada) na tabela OR_NFDESPESA
	 * 
	 * @param solicitacao 
	 * @return A entidade OrNfdespesaEntity atualizada com o novo registro
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public OrNfdespesaEntity criaNovaNfDespesa(SolicitacaoPagamento solicitacao){		
		
		Integer codNaturezaDespesa = solicitacao.getCodNaturezaDespesa();
		Date agora = new Date();
		
		RfParamnatnfdespEntity rfParamnatnfdesp = paramnatnfdespDao.busca(solicitacao.getNroEmpresa(), codNaturezaDespesa);
		OrParametroEntity orParametro = orParametroDao.busca(solicitacao.getNroEmpresa());
		GePessoaEntity fornecedor = gePessoasDao.buscaGePessoaPorFornecedor(solicitacao.getCodFornecedor());
		
		OrNfdespesaEntity orNfdespesa = new OrNfdespesaEntity();
		orNfdespesa.setCfop(new Integer(rfParamnatnfdesp.getCfopestado()));
		orNfdespesa.setCgo(rfParamnatnfdesp.getCgo());
		orNfdespesa.setExigeitensnota(rfParamnatnfdesp.getExigeitensnota());
		orNfdespesa.setNroempresanatdesp(orParametro.getNroempresaorc());
		orNfdespesa.setNroempresaorc(orParametro.getNroempresaorc());
		orNfdespesa.setVersaopessoa(fornecedor.getVersao());
		
		orNfdespesa.setCodhistorico(codNaturezaDespesa.shortValue());
		orNfdespesa.setCodmodelo(getModeloNF(codNaturezaDespesa));
		
		orNfdespesa.setDtaalteracao(agora);
		orNfdespesa.setDtaemissao(solicitacao.getDataEmissao());
		orNfdespesa.setDtaentrada(agora);		
		orNfdespesa.setNroempresa(solicitacao.getNroEmpresa().shortValue());
		orNfdespesa.setNronota(solicitacao.getNroNota());
		orNfdespesa.setObservacao(getObservacao(solicitacao.getMensagem()));
		if(solicitacao.getOrSolicitacaovenctos() != null 
				&& solicitacao.getOrSolicitacaovenctos().size() == 1 
				&& solicitacao.getOrSolicitacaovenctos().get(0).getQtdPrazo() != null)
			orNfdespesa.setPrazopagto(solicitacao.getOrSolicitacaovenctos().get(0).getQtdPrazo()+"");
		else 
			orNfdespesa.setPrazopagto(null);
		orNfdespesa.setSeqpessoa(solicitacao.getCodFornecedor().intValue());
		BigDecimal valor = solicitacao.getValor();
		orNfdespesa.setValor(valor);
		orNfdespesa.setVlrfinanceiro(valor);
		orNfdespesa.setVlrliqnota(valor);
		orNfdespesa.setVlroutras(valor);
		
		orNfdespesa.setCodsitdoc((byte)0);
		orNfdespesa.setDiafixo(NAO);
		orNfdespesa.setEspecienf(NF);
		orNfdespesa.setGeratitipi(NAO);
		orNfdespesa.setIndvenctoantprazomin(NAO);
		orNfdespesa.setNfe(NAO);
		orNfdespesa.setRetencaocofinsnfdesp(NAO);
		orNfdespesa.setRetencaopisnfdesp(NAO);
		orNfdespesa.setSerie(SERIE);
		orNfdespesa.setTipopgto(TIPOPGTO);
		orNfdespesa.setTipotributacao(TIPOTRIBUTACAO);
		orNfdespesa.setTipotributacaoipi(TIPOTRIBUTACAOIPI);
		orNfdespesa.setTributcofins(TRIBUTCONFINS);
		orNfdespesa.setTributicms(TRIBUTOICMS);
		orNfdespesa.setTributpis(TRIBUTPIS);
		orNfdespesa.setUsualteracao(USUALTERACAO);
		orNfdespesa.setUsuinclusao(USUINCLUSAO);
		orNfdespesa.setVlracrescimos(VLRACRESCIMOS);
		orNfdespesa.setVlrbaseicms(VLRBASEICMS);
		orNfdespesa.setVlrcofins(VLRCONFINS);
		orNfdespesa.setVlrcofinscred(VLRCOFINSCRED);
		orNfdespesa.setVlrcssll(VLRCSSLL);
		orNfdespesa.setVlrdescontos(VLRDESCONTOS);
		orNfdespesa.setVlricms(VLRICMS);
		orNfdespesa.setVlricmsdif(VLRICMSDIF);
		orNfdespesa.setVlricmsrettransp(VLRICMSRETTRANSP);
		orNfdespesa.setVlrinss(VLRINSS);
		orNfdespesa.setVlrinsspat(VLRINSSPAT);
		orNfdespesa.setVlrinsspesfis(VLRINSSPESFIS);
		orNfdespesa.setVlripi(VLRIPI);
		orNfdespesa.setVlrir(VLRIR);
		orNfdespesa.setVlrirpesfis(VLRIRPESFIS);
		orNfdespesa.setVlrisento(VLRISENTO);
		orNfdespesa.setVlrisentoipi(VLRISENTOIPI);
		orNfdespesa.setVlriss(VLRISS);
		orNfdespesa.setVlrissst(VLRISSST);
		orNfdespesa.setVlroutrasopdesc(VLROUTRASOPDESC);
		orNfdespesa.setVlroutrosipi(VLROUTROSIPI);
		orNfdespesa.setVlrpis(VLRPIS);
		orNfdespesa.setVlrpiscred(VLRPISCRED);
		orNfdespesa.setVlrsestsenat(VLRSESTSENAT);
		
		return orNfdespesaDao.persist(orNfdespesa);
	}

	private String calculaPrazo(Date dataVencimento, Date dataEmissao) {
		long milisegundosVencimento = dataVencimento.getTime();
		long milisegundosEmissao = dataEmissao.getTime();
		
		long milisegundosPrazo = milisegundosVencimento - milisegundosEmissao;		
		long milisegundosDia = 24*60*60*1000;
		long prazoDias = milisegundosPrazo/milisegundosDia;
		
		return String.valueOf(prazoDias);
	}

	private String getObservacao(String mensagem) {		
		String bpmSeta = "[BPMSETA]";
		if(mensagem != null && !mensagem.isEmpty()){			
			return bpmSeta + " " +mensagem;
		}
		
		return bpmSeta;
	}

	private String getModeloNF(Integer codHistorico) {
		if(codHistorico.equals(COD_ENERGIA_ELETRICA)){
			return NF_CONTA_ENERGIA_ELETRICA;
		}else{
			return NF_MODELO_1A;
		}
	}

}
