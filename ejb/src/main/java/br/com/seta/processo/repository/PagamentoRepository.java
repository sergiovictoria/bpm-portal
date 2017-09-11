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
import br.com.seta.processo.dto.Pagamento;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.PagamentoLocal;
import br.com.seta.processo.interfaces.PagamentoRemote;


@Stateless(name="PagamentoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(PagamentoLocal.class)
@Remote(PagamentoRemote.class)
@Interceptors({LogInterceptor.class})
public class PagamentoRepository extends GenericJpaDaoConSinco<Pagamento> implements PagamentoLocal, PagamentoRemote {

	@Inject
    private Logger logger;
	
	private static final long serialVersionUID = 1L;
	private static final String _PAGAMENTO_ = "select a.abertoquitado, a.dtaquitacao, a.NROTITULO, a.SERIEDOC, a.VLRNOMINAL, a.DTAVENCIMENTO from fi_titulo a where 1=1 AND a.NROTITULO = :nrotitulo";

	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Pagamento");
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Pagamento> findPagamentoByID(Pagamento pagamento) {
		Query query = getEntityManager().createNativeQuery(_PAGAMENTO_);
		query.setParameter("nrotitulo",pagamento.getNrotitulo());
		List<Object[]> objects = (List <Object[]>) query.getResultList();
		return  getPagamentoS(objects);
	}
	
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Pagamento");
	}
	
	



}
