package br.com.seta.processo.bean;

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
import br.com.seta.processo.entity.GeCidadeEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Cidades")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Cidades extends GenericJpaDaoConSinco<GeCidadeEntity>  {


	private static final long serialVersionUID = 1L;
	@Inject
	private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Cidades ");
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GeCidadeEntity getGeCidade(String cidade, String uf) throws DaoException {
		
		Query query = getEntityManager().createNamedQuery("GeCidadeEntity.find.cidade");
		query.setParameter("cidade",cidade);
		query.setParameter("uf",uf);
		List<GeCidadeEntity> cidadeEntities = (List<GeCidadeEntity>) query.getResultList();
	    return cidadeEntities.size() > 0 ? cidadeEntities.get(0) : null; 
	    
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Cidades");
	}


}

