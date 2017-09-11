package br.com.seta.processo.bean;

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

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.MafForneccontatoEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;



@Stateless(name="MafFonecedoreContatos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MafFonecedoreContatos extends GenericJpaDaoConSinco<MafForneccontatoEntity> {

	
	@Inject
    private Logger logger;
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Fornecedores - Contatos");
	}
	
	
	private static final long serialVersionUID = 1L;

	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MafForneccontatoEntity create(MafForneccontatoEntity mafForneccontatoEntity) throws DaoException {
		return persist(mafForneccontatoEntity);
	}

	
	

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Fornecedores - Contatos");
	}
	

}
