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
import br.com.seta.processo.entity.MapFamfornecEntity;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="MapFamfornecs")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapFamfornecs extends GenericJpaDaoConSinco<MapFamfornecEntity> {


	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Fornecedores" );
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapFamfornecEntity create (MapFamfornecEntity mapFamfornecEntity) {
         return persist(mapFamfornecEntity);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void delete(MapFamfornecEntity mapFamfornecEntity) {
        delete(mapFamfornecEntity);
	}




	@PreDestroy
	public void destroy() {
		logger.info("Destruindo  EJB - Fornecedores" );
	}


}
