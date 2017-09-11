package br.com.seta.processo.bean;

import java.math.BigDecimal;
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
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrvCentrocusto;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name="ComplementoCentroCustos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class ComplementoCentroCustos extends GenericJpaDaoConSinco<OrvCentrocusto> {

	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private Logger logger;

	private static final String _CENTRO_CUSTO_NATUREZA = "SELECT ORV_CENTROCUSTO.CENTROCUSTO, ORV_CENTROCUSTO.DESCRICAO FROM ORV_CENTROCUSTO WHERE 1=1 AND SITUACAO = 'A' AND NROEMPRESA = :nroempresa AND ORV_CENTROCUSTO.CENTROCUSTO = :centrocusto";


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Centro de Custo");
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public OrvCentrocusto getCentroCusto(BigDecimal nroempresa, long centrocusto) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("centrocusto",  centrocusto);
		List<OrvCentrocusto> centroCustos = getResultListMap(query, OrvCentrocusto.class);
		return centroCustos.size() > 0 ? centroCustos.get(0) : null; 
	}
	
	public OrvCentrocusto getCentroCusto(OrReqplanilhalancto lancto){
		BigDecimal nroempresa = new BigDecimal(lancto.getNroempresa());
		long centroCusto = lancto.getCentrocustodb() == null ? 0 : lancto.getCentrocustodb().longValue();
		return getCentroCusto(nroempresa, centroCusto);
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Centro de Custo");
	}

}