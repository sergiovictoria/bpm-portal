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
import br.com.seta.processo.dto.NotaFiscal;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.NotaFiscalLocal;
import br.com.seta.processo.interfaces.NotaFiscalRemote;


@Stateless(name="NotaFiscalRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(NotaFiscalLocal.class)
@Remote(NotaFiscalRemote.class)
@Interceptors({LogInterceptor.class})
public class NotaFiscalRepository extends GenericJpaDaoConSinco<NotaFiscal> implements NotaFiscalLocal, NotaFiscalRemote {

	
	@Inject
    private Logger logger;
	
	private static final long serialVersionUID = 1L;
	private static final String _INTEGRACAO = "SELECT A.SITUACAO, A.REQUISICOES, A.NRONOTA FROM OR_NFDESPESA A WHERE A.SITUACAO = 'I' AND A.REQUISICOES = :requisicoes";
	private static final String _INTEGRACAO_NRO_REQ = "SELECT A.SITUACAO, A.REQUISICOES, A.NRONOTA FROM OR_NFDESPESA A WHERE A.NRONOTA = :nronota AND A.REQUISICOES = :requisicoes";

	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - NotaFiscal");
	}
	
		
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public NotaFiscal getVerifaIntegracaoReq(NotaFiscal notaFIscal) {
			Query query = getEntityManager().createNativeQuery(_INTEGRACAO);
			query.setParameter("requisicoes",notaFIscal.getRequisicoes());
			List<NotaFiscal> notaFiscals = (List <NotaFiscal>) getResultList(query,NotaFiscal.class);
			return  (notaFiscals != null && notaFiscals.size() > 0 ? notaFiscals.get(0) : null);
	}


	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public NotaFiscal getVerifaIntegracaoNroReq(NotaFiscal notaFIscal) {
		Query query = getEntityManager().createNativeQuery(_INTEGRACAO_NRO_REQ);
		query.setParameter("requisicoes",notaFIscal.getRequisicoes());
		query.setParameter("nronota",notaFIscal.getNronota());
		List<NotaFiscal> notaFiscals = (List <NotaFiscal>) getResultList(query,NotaFiscal.class);
		return  (notaFiscals != null && notaFiscals.size() > 0 ? notaFiscals.get(0) : null);
	}


	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - NotaFiscal");
	}
	

}
