package br.com.seta.processo.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
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
import br.com.seta.processo.dto.Produto;
import br.com.seta.processo.entity.MapProdutoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.ProdutoLocal;
import br.com.seta.processo.interfaces.ProdutoRemote;


@Stateless(name="ProdutoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ProdutoLocal.class)
@Remote(ProdutoRemote.class)
@Interceptors({LogInterceptor.class})
public class ProdutoRepository extends GenericJpaDaoConSinco<MapProdutoEntity> implements ProdutoLocal, ProdutoRemote {
	
	private static final long serialVersionUID = 1L;
	private static final String _FIND_PRODUTO_TRIBUTO = "select ORV.* from  ORV_PRODUTOTRIB  ORV Where ORV.NROEMPRESA = :nroempresa AND ORV.SEQFORNECEDOR = :seqfornecedor";
	
	
	@Inject
    private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Produto");
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Produto");
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Produto> getProdutoIdPessoa(Produto produto) {
		List<Produto> produtos = new ArrayList<Produto>(0);
		Query query = getEntityManager().createNamedQuery("Produto.IdPessoa");
		query.setParameter("seqpessoa",produto.getSeqpessoa());
		try {
			produtos.addAll((Collection<? extends Produto>) copyObject(query.getResultList(),Produto.class));
		}catch (Exception e) {
            e.printStackTrace();
		}
		return produtos;
	}


	@Override
	public List<OrvProdutoTributo> getProdutoIdPessoaTributo(OrvProdutoTributo orvProdutoTributo) {
		Query query = getEntityManager().createNativeQuery(_FIND_PRODUTO_TRIBUTO);
		query.setParameter("nroempresa",orvProdutoTributo.getNroempresa());
		query.setParameter("seqfornecedor",orvProdutoTributo.getSeqfornecedor());
		List<OrvProdutoTributo>  orvProdutoTributos = (List<OrvProdutoTributo>) mapResult(OrvProdutoTributo.class,query.getResultList());
		return orvProdutoTributos;
	}


}
