package br.com.seta.processo.bean;

import java.util.Date;

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
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Fc5Datautil")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Fc5Datautil extends GenericJpaDaoConSinco<Object>{

	

	private static final long serialVersionUID = 1L;
	@Inject
    private Logger logger;

	
	private static final String FIND_QUERY = "SELECT FC5F_DATAUTIL(:pdDtaUtil, NULL, 0, 0, 'P', :pnNroEmpresa) AS PDPROXDTAUTIL FROM DUAL";
	

		
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Util Datas");
	}
		
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public java.util.Date getDate(Date pdDtaUtil, String  pnNroEmpresa) {
		Query query = getEntityManager().createNativeQuery(FIND_QUERY)
		.setParameter("pdDtaUtil", pdDtaUtil)
		.setParameter("pnNroEmpresa",pnNroEmpresa);		
		return (java.util.Date) query.getSingleResult();
	}

	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Util Datas");
	}


	
}

