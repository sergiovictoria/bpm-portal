package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrvProdutoTributo;
import br.com.seta.processo.entity.OrvProdutotribEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;


@Stateless(name="Produtos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean


public class Produtos extends GenericJpaDaoConSinco<OrvProdutotribEntity> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
    private Logger logger;

	private static final String _FIND_PRODUTO_TRIBUTO = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor";
	
    private static final String _PRODUTO_DESCRICAO_ASC = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR ORDER BY ORV.DESCRICAO ASC";
    private static final String _PRODUTO_DESCRICAO_DES = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR ORDER BY ORV.DESCRICAO DESC";
    
    private static final String _PRODUTO_SEQPRODUTO_ASC = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR ORDER BY ORV.SEQPRODUTO ASC";
    private static final String _PRODUTO_SEQPRODUTO_DES = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR ORDER BY ORV.SEQPRODUTO DESC";
            
    private static final String _PRODUTO_DESCRICAO_ASC_LIKE = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR and ORV.DESCRICAO LIKE :descricao ORDER BY ORV.DESCRICAO ASC";
    private static final String _PRODUTO_DESCRICAO_DES_LIKE = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR and ORV.DESCRICAO LIKE :descricao ORDER BY ORV.DESCRICAO DESC";
    
    private static final String _PRODUTO_SEQPRODUTO_ASC_LIKE = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR and ORV.DESCRICAO LIKE :descricao ORDER BY ORV.SEQPRODUTO ASC";
    private static final String _PRODUTO_SEQPRODUTO_DES_LIKE = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR and ORV.DESCRICAO LIKE :descricao ORDER BY ORV.SEQPRODUTO DESC";
    
    private static final String _PRODUTO_COUNT      = "select COUNT(*) from  ORV_PRODUTOTRIB ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR";
    private static final String _PRODUTO_COUNT_LIKE = "select COUNT(*) from  ORV_PRODUTOTRIB ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.NROEMPRESA = ORV.SEQFORNECEDOR and ORV.DESCRICAO LIKE :descricao";
    
    private static final String _FIND_PRODUTO       = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor ORDER BY ORV.DESCRICAO ASC";
    private static final String _FIND_PRODUTO_LIKE  = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor   AND (ORV.DESCRICAO LIKE :descricao OR TO_CHAR(ORV.SEQPRODUTO) = :codproduto ) ORDER BY ORV.DESCRICAO ASC";
    
    private static final String _FIND_PRODUTO_SIZE       = "select COUNT(SEQPRODUTO) from  ORV_PRODUTOTRIB ORV where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor";
    private static final String _FIND_PRODUTO_SIZE_LIKE  = "select COUNT(SEQPRODUTO) from  ORV_PRODUTOTRIB ORV where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor AND (ORV.DESCRICAO LIKE :descricao OR TO_CHAR(ORV.SEQPRODUTO) = :codproduto ) ORDER BY ORV.DESCRICAO ASC";
    
    private static final String BUSCA_PRODUTOS_POR_EMPRESA_FORNECEDOR_SEQPRODUTO = "SELECT * FROM ORV_PRODUTOTRIB WHERE SEQPRODUTO = ?1 and NROEMPRESA = ?2 AND SEQFORNECEDOR = ?3";
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Produto");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrvProdutoTributo> getProdutoS(String order, BigDecimal  nroempresa , long first, long count) {
		
		Query query = null;
		List<OrvProdutoTributo> orvProdutoTributos = new ArrayList<OrvProdutoTributo>(0);
		
		if (order.equals(OderProvider.ProdutoDescAsc.getValue() )) {
			query = getEntityManager().createNativeQuery(_PRODUTO_DESCRICAO_ASC).setFirstResult((int)first).setMaxResults((int)count);

		}else if (order.equals(OderProvider.ProdutoDescDes.getValue() )) {
			
			query = getEntityManager().createNativeQuery(_PRODUTO_DESCRICAO_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (order.equals(OderProvider.ProdutoSeqAsc.getValue() )) {
			
			query = getEntityManager().createNativeQuery(_PRODUTO_SEQPRODUTO_ASC).setFirstResult((int)first).setMaxResults((int)count);

		}else {
			query = getEntityManager().createNativeQuery(_PRODUTO_SEQPRODUTO_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}
			
		query.setParameter("nroempresa",nroempresa);
		orvProdutoTributos = (List<OrvProdutoTributo>) mapResult(OrvProdutoTributo.class,query.getResultList());
		return orvProdutoTributos;
		
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca
	 * @param seqfornecedor Sequencia do Fornecedor para Busca
	 * @param first  Primeiro Registro 
	 * @param count Quantidade de Registro
	 * @return Lista de Produto
	 */
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrvProdutotribEntity> findProdutos(BigDecimal  nroempresa , BigDecimal seqfornecedor, long first, long count) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nroempresa", nroempresa);
		map.put("seqfornecedor",seqfornecedor);
		List<OrvProdutotribEntity> orvProdutotribEntities = new ArrayList<OrvProdutotribEntity>(0);
		orvProdutotribEntities = findFromNativeQuery(_FIND_PRODUTO, map, first, count);
		return orvProdutotribEntities;
	}

	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca
	 * @param seqfornecedor Sequencia do Fornecedor para Busca
	 * @param first  Primeiro Registro 
	 * @param count Quantidade de Registro
	 * @param filtro Descição para Busca
	 * @return Lista de Produto
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrvProdutotribEntity> findProdutos(BigDecimal  nroempresa , BigDecimal seqfornecedor, String filtro, long first, long count) {
		if(filtro == null)
			filtro = "";
		
		List<OrvProdutotribEntity> produtotribEntities = new ArrayList<OrvProdutotribEntity>(0);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("nroempresa",nroempresa);
		map.put("seqfornecedor",seqfornecedor);
		map.put("descricao", "%" + filtro.toUpperCase() + "%");
		map.put("codproduto", filtro);
		produtotribEntities = findFromNativeQuery(_FIND_PRODUTO_LIKE, map, first, count);
		
		return produtotribEntities;
	}
	
	
	
	/******
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa Para Busca
	 * @param seqfornecedor Sequencia fornecedor para Busca
	 * @return Quantidade de Resgistro Size 
	 */ 
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findPodutosSize(BigDecimal  nroempresa , BigDecimal seqfornecedor) {
		Query query = getEntityManager().createNativeQuery(_FIND_PRODUTO_SIZE);
		query.setParameter("nroempresa",nroempresa);
		query.setParameter("seqfornecedor",seqfornecedor);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	/******
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa Para Busca
	 * @param seqfornecedor Sequencia fornecedor para Busca
	 * @param filtro Descrição do Produto LIKE
	 * @return Quantidade de Resgistro Size 
	 */ 
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findPodutosSize(BigDecimal  nroempresa , BigDecimal seqfornecedor, String filtro) {
		if(filtro == null)
			filtro = "";
		
		Query query = getEntityManager().createNativeQuery(_FIND_PRODUTO_SIZE_LIKE);
		query.setParameter("nroempresa",nroempresa);
		query.setParameter("seqfornecedor",seqfornecedor);
		query.setParameter("descricao", "%" + filtro.toUpperCase() + "%");
		query.setParameter("codproduto", filtro);
		
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	public OrvProdutoTributo buscaProduto(BigDecimal nroEmpresa, BigDecimal seqFornecedor, BigDecimal seqProduto){
		Query query = getEntityManager().createNativeQuery(BUSCA_PRODUTOS_POR_EMPRESA_FORNECEDOR_SEQPRODUTO);
		query.setParameter(1, seqProduto);
		query.setParameter(2, nroEmpresa);
		query.setParameter(3, seqFornecedor);
		
		@SuppressWarnings("unchecked")
		List<OrvProdutoTributo> results = (List<OrvProdutoTributo>) mapResult(OrvProdutoTributo.class,query.getResultList());
		return results.isEmpty() ? null : results.get(0);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrvProdutoTributo> getProdutoSLike(String order, BigDecimal  nroempresa , String descricao,  long first, long count) {
		
		Query query = null;
		List<OrvProdutoTributo> orvProdutoTributos = new ArrayList<OrvProdutoTributo>(0);
		
		if (order.equals(OderProvider.ProdutoDescAsc.getValue() )) {
			query = getEntityManager().createNativeQuery(_PRODUTO_DESCRICAO_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}else if (order.equals(OderProvider.ProdutoDescDes.getValue() )) {
			
			query = getEntityManager().createNativeQuery(_PRODUTO_DESCRICAO_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (order.equals(OderProvider.ProdutoSeqAsc.getValue() )) {
			
			query = getEntityManager().createNativeQuery(_PRODUTO_SEQPRODUTO_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}else {
			query = getEntityManager().createNativeQuery(_PRODUTO_SEQPRODUTO_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}
			
		query.setParameter("descricao", "%"+descricao.toUpperCase()+"%");
		query.setParameter("nroempresa",nroempresa);
		orvProdutoTributos = (List<OrvProdutoTributo>) mapResult(OrvProdutoTributo.class,query.getResultList());
		return orvProdutoTributos;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(BigDecimal nroempresa) {
		Query query = getEntityManager().createNativeQuery(_PRODUTO_COUNT);
		query.setParameter("nroempresa",nroempresa);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(BigDecimal nroempresa, String descricao) {
		Query query = getEntityManager().createNativeQuery(_PRODUTO_COUNT_LIKE);
		query.setParameter("nroempresa",nroempresa);
		query.setParameter("descricao", "%"+descricao.toUpperCase()+"%");;
		return ((BigDecimal)query.getSingleResult()).longValue();
	}
		

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrvProdutoTributo> getProdutoIdPessoaTributo(OrvProdutoTributo orvProdutoTributo) {
		Query query = getEntityManager().createNativeQuery(_FIND_PRODUTO_TRIBUTO);
		query.setParameter("nroempresa",orvProdutoTributo.getNroempresa());
		query.setParameter("seqfornecedor",orvProdutoTributo.getSeqfornecedor());
		List<OrvProdutoTributo>  orvProdutoTributos = (List<OrvProdutoTributo>) mapResult(OrvProdutoTributo.class,query.getResultList());
		return orvProdutoTributos;
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Produto");
	}



}
