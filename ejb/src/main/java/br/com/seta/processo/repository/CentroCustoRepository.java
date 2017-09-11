package br.com.seta.processo.repository;

import java.util.List;

import javax.annotation.PostConstruct;
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
import br.com.seta.processo.dto.CentroCusto;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.CentroCustoLocal;
import br.com.seta.processo.interfaces.CentroCustoRemote;



@Stateless(name="CentroCustoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(CentroCustoLocal.class)
@Remote(CentroCustoRemote.class)
@Interceptors({LogInterceptor.class})

public class CentroCustoRepository extends GenericJpaDaoConSinco<CentroCusto> implements CentroCustoLocal, CentroCustoRemote {

	
	@Inject
	private Logger logger;

	
	private static final String _CENTRO_CUSTO_NATUREZA = "SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
                                                         "CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico ORDER BY CTP.CENTROCUSTODB";

	private static final long serialVersionUID = 1L;

	

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Centro de Custo");
	}


	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CentroCusto> getCentroCusto(CentroCusto centroCusto) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA);
		query.setParameter("nroempresa", centroCusto.getNroempresa());
		query.setParameter("codhistorico",  centroCusto.getCodhistorico());
		List<CentroCusto> centroCustos = getResultListMap(query, CentroCusto.class);
		return centroCustos;
	}



}
