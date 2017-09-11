package br.com.seta.processo.repository;

import java.math.BigDecimal;

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

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.FornecedorLocal;
import br.com.seta.processo.interfaces.FornecedorRemote;


@Stateless(name="FornecedorRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(FornecedorLocal.class)
@Remote(FornecedorRemote.class)
@Interceptors({LogInterceptor.class})

public class FornecedorRepository extends GenericJpaDaoConSinco<GePessoaEntity> implements FornecedorLocal, FornecedorRemote {

	
	@Inject
    private Logger logger;
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Fornecedores");
	}
	
	
	private static final long serialVersionUID = 1L;

	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity create(GePessoaEntity gePessoaEntity) throws DaoException {
		return persist(gePessoaEntity);
	}

	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(BigDecimal seqforncedor) throws DaoException {
		return null;
	}
	

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(GePessoaEntity gePessoaEntity) throws DaoException {
		return null;
	}

	

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Fornecedores");
	}
	

}
