package br.com.seta.processo.bean;

import java.math.BigDecimal;

import javax.ejb.LocalBean;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import br.com.seta.processo.bean.dao.OrNfitensdespesaDao;
import br.com.seta.processo.bean.dao.OrvProdutotribDao;
import br.com.seta.processo.entity.OrNfdespesaEntity;
import br.com.seta.processo.entity.OrNfitensdespesaEntity;
import br.com.seta.processo.entity.OrNfitensdespesaId;
import br.com.seta.processo.entity.OrvProdutotribEntity;
import br.com.seta.processo.exceptions.RegraDeNegocioException;

@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class NfItensDespesaSolicitacaoPagtoService {
	
	private static final Byte COMPLEMENTO = 0;
	private static final String GERATITIPI = "N";
	private static final String INDFINANCEIRO = "S";
	private static final String INDISENTODEBPISCOFINS = "N";
	private static final String INDISENTOPISCOFINS = "N";
	private static final short NROITEM = 1;
	private static final BigDecimal PERCREDBASEICMS = BigDecimal.ZERO;
	private static final BigDecimal QUANTIDADE = BigDecimal.ONE;
	private static final String SERVICO = "N";
	private static final String TIPOTRIBUTACAO = "O";
	private static final String TIPOTRIBUTACAOIPI = "O";
	private static final String TRIBUTACOFINSNFDESP = "N";
	private static final String TRIBUTAICMSNFDESP = "N";
	private static final String TRIBUTAPISNFDESP = "N";
	private static final String VEICULO = "N";
	
	@Inject
	private  OrvProdutotribDao orvProdutotribDao;
	@Inject
	private OrNfitensdespesaDao orNfitensdespesaDao;
	
	/**
	 * Cria uma nova entidade da classe OrNfitensdespesa
	 * 
	 * @param nfdespesa
	 * @return
	 * @throws RegraDeNegocioException Uma exceção é lançada quando não são encotrados produtos vinculados ao fornecedor
	 */
	public OrNfitensdespesaEntity criaNovoNfItemDespesa(OrNfdespesaEntity nfdespesa) throws RegraDeNegocioException {
		
		OrvProdutotribEntity orvProdutotrib = orvProdutotribDao.busca(new Integer(nfdespesa.getNroempresa()), nfdespesa.getSeqpessoa());
		
		if(orvProdutotrib == null){		
			String mensagemDeErro = "Não foram encontrados produtos para nroEmpresa " + nfdespesa.getNroempresa() + " e seqPessoa " + nfdespesa.getSeqpessoa();
			RegraDeNegocioException regraDeNegocioException = new RegraDeNegocioException(mensagemDeErro);
			regraDeNegocioException.adicionaMensagem("Não existem produtos vinculados a esse fornecedor");
			throw regraDeNegocioException;
		}		
		
		OrNfitensdespesaEntity nfitensdespesa = new OrNfitensdespesaEntity();
		OrNfitensdespesaId id = new OrNfitensdespesaId();
		nfitensdespesa.setId(id);
		
		id.setNroitem(NROITEM);
		id.setSeqnota(nfdespesa.getSeqnota());
		
		nfitensdespesa.setCfop(nfdespesa.getCfop());
		nfitensdespesa.setNroempresa(nfdespesa.getNroempresa());
		nfitensdespesa.setNroempresaorc(nfdespesa.getNroempresaorc());
		nfitensdespesa.setVlritem(nfdespesa.getValor());
		nfitensdespesa.setVlrliqdesp(nfdespesa.getVlrliqnota());
		nfitensdespesa.setVlroutras(nfdespesa.getVlroutras());
		nfitensdespesa.setVlrtotal(nfdespesa.getValor());
		
		nfitensdespesa.setAliqicmsprod(orvProdutotrib.getAliqicms());
		nfitensdespesa.setCodncm(orvProdutotrib.getCodncm());
		nfitensdespesa.setCodproduto(orvProdutotrib.getCodproduto());
		nfitensdespesa.setCodstf(orvProdutotrib.getCodstf());
		nfitensdespesa.setCodtributacao(orvProdutotrib.getNrotributacao());
		nfitensdespesa.setDescricao(orvProdutotrib.getDescricao());
		nfitensdespesa.setUnidade(orvProdutotrib.getUnidadepadrao());
		nfitensdespesa.setUnidadepadrao(orvProdutotrib.getUnidadepadrao());
		nfitensdespesa.setVersaoprod(orvProdutotrib.getVersaoprod());
		
		nfitensdespesa.setComplemento(COMPLEMENTO);
		nfitensdespesa.setGeratitipi(GERATITIPI);
		nfitensdespesa.setIndfinanceiro(INDFINANCEIRO);
		nfitensdespesa.setIndisentodebpiscofins(INDISENTODEBPISCOFINS);
		nfitensdespesa.setIndisentopiscofins(INDISENTOPISCOFINS);
		nfitensdespesa.setPercredbaseicms(PERCREDBASEICMS);
		nfitensdespesa.setQuantidade(QUANTIDADE);
		nfitensdespesa.setServico(SERVICO);
		nfitensdespesa.setTipotributacao(TIPOTRIBUTACAO);
		nfitensdespesa.setTipotributacaoipi(TIPOTRIBUTACAOIPI);
		nfitensdespesa.setTributacofinsnfdesp(TRIBUTACOFINSNFDESP);
		nfitensdespesa.setTributaicmsnfdesp(TRIBUTAICMSNFDESP);
		nfitensdespesa.setTributapisnfdesp(TRIBUTAPISNFDESP);
		nfitensdespesa.setVeiculo(VEICULO);	
		
		return orNfitensdespesaDao.persist(nfitensdespesa);		
	}



}
