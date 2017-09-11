package br.com.seta.processo.repository;

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
import br.com.seta.processo.dto.Compra;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.CompraLocal;
import br.com.seta.processo.interfaces.CompraRemote;


@Stateless(name="CompraRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CompraLocal.class)
@Remote(CompraRemote.class)
@Interceptors({LogInterceptor.class})
public class CompraRepository extends GenericJpaDaoConSinco<Compra> implements CompraLocal, CompraRemote {

	
	@Inject
    private Logger logger;
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Compra");
	}
	
	
	private static final long serialVersionUID = 1L;
	private static final String _INTEGRACAO_STATUS  = "SELECT ehrtr.NROREQUISICAO, ehrtr.STATUS, ehrtr.DTACOMPRA FROM OR_REQUISICAO ehrtr where 1=1 and ehrtr.NROREQUISICAO = :requisicao";

		
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Compra getVerifaStatusCompraNro(Compra compra) {
		Query query = getEntityManager().createNativeQuery(_INTEGRACAO_STATUS);
		query.setParameter("requisicao",compra.getNrorequisicao());
		List<Object[]> objects = (List <Object[]>) query.getResultList();
		List<Compra> compras =  getCompras(objects);
		return  (compras != null && compras.size() > 0 ? compras.get(0) : null);
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Compra");
	}

}
