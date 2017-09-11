package br.com.seta.processo.bean;

import java.math.BigDecimal;
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
import br.com.seta.processo.entity.MaxAtributofixoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name="MaxAtributofixos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MaxAtributofixos extends GenericJpaDaoConSinco<MaxAtributofixoEntity> {
	
	
	

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - MaxAtributofixos" );
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MaxAtributofixoEntity> getMaxAtributoAll( ) {
		Query query = getEntityManager().createNamedQuery("ATRIBUTO.FIND.ALL");
		return (List<MaxAtributofixoEntity>) query.getResultList();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MaxAtributofixoEntity find(BigDecimal seqtributofixo) {
		Query query = getEntityManager().createNamedQuery("ATRIBUTO.FIND.ONE");
		query.setParameter("seqtributofixo",seqtributofixo);
		return (MaxAtributofixoEntity) query.getSingleResult();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MaxAtributofixoEntity> getMaxAtributofixo(String descricao, long first, long count) {
		Query query = getEntityManager().createNamedQuery("ATRIBUTO.LIKE_NAME");
		query.setParameter("descricao",descricao);
		return (List<MaxAtributofixoEntity>) query.getResultList();
	}
	
	

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo  EJB - MaxAtributofixos" );
	}



}
