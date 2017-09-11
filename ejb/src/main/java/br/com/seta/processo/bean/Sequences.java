package br.com.seta.processo.bean;

import java.math.BigDecimal;

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
import br.com.seta.processo.entity.GeSequenciaEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Sequences")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean

public class Sequences extends GenericJpaDaoConSinco<GeSequenciaEntity> {

	
	@Inject
    private Logger logger;
	private static final BigDecimal _INCREMENT = new BigDecimal("1");
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Sequencias");
	}
	
	
	private static final long serialVersionUID = 1L;
	

	public BigDecimal nextValue(String nometabela) throws DaoException {
		 BigDecimal sequence = getLastSequence(nometabela);
		 update(nometabela, sequence);
		 return sequence;
	}
		
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private BigDecimal getLastSequence(String nometabela) throws DaoException {
		Query query = getEntityManager().createNativeQuery("SELECT G.sequencia FROM GE_SEQUENCIA G WHERE G.nometabela= :nometabela");
	    query.setParameter("nometabela",nometabela);
	    BigDecimal value = (BigDecimal) query.getSingleResult();
	    BigDecimal valueAdd = value.add(_INCREMENT);
	    return valueAdd;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private boolean update(String nometabela, BigDecimal sequence) throws DaoException {
		Query query = getEntityManager().createNativeQuery("UPDATE GE_SEQUENCIA G SET G.SEQUENCIA = :sequence WHERE G.NOMETABELA= :nometabela",GeSequenciaEntity.class);
	    query.setParameter("nometabela",nometabela);
	    query.setParameter("sequence",sequence);
	    query.executeUpdate();
	    return true;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Sequencias");
	}
	

}
