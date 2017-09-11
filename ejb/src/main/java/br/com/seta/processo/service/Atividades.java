package br.com.seta.processo.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.interceptor.LogInterceptor;


/****
 * 
 * Serviço trazer atividades
 * @author Sérgio da Victória 
 *
 */

@Stateless(name="Atividades")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
@Interceptors({LogInterceptor.class})

public class Atividades {

	@Inject
	private Logger logger;
	
	@Inject
	private TransacaoMongo transacaoMongo; 
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Atividades ");
	}
	
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Atividades ");
	}

}
