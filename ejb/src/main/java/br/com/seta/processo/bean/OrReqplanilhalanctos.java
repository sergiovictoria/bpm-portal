package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import br.com.seta.processo.dto.CentroCusto;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="OrReqplanilhalanctos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class OrReqplanilhalanctos extends GenericJpaDaoConSinco<OrReqplanilhalancto> {

	private static final long serialVersionUID = 1L;



	@Inject
	private Logger logger;


	private static final String _CENTRO_CUSTO_NATUREZA = "SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
			"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico ORDER BY CTP.CENTROCUSTODB";


	private static final String _CENTRO_CUSTO_COUNT   = "SELECT COUNT(*) FROM (SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
			"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO = :codhistorico)";



	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Centro de Custo");
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrReqplanilhalancto> getCentroCustoOrReqplanilhalancto(BigDecimal nroempresa, BigDecimal codhistorico, long first, long count) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		List<OrReqplanilhalancto> orReqplanilhalanctos = mapCentoCustoTOorReqplanilhalancto((List<CentroCusto>)getResultListMap(query, CentroCusto.class));
		return orReqplanilhalanctos;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<OrReqplanilhalancto> getCentroCustoOrReqplanilhalancto(BigDecimal nroempresa, BigDecimal codhistorico) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		List<OrReqplanilhalancto> orReqplanilhalanctos = mapCentoCustoTOorReqplanilhalancto((List<CentroCusto>)getResultListMap(query, CentroCusto.class));
		return orReqplanilhalanctos;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(BigDecimal nroempresa, BigDecimal codhistorico) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_COUNT);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}



	public static List<OrReqplanilhalancto> mapCentoCustoTOorReqplanilhalancto(List<CentroCusto> centroCustos ) {
		List<OrReqplanilhalancto> orReqplanilhalanctos = new ArrayList<OrReqplanilhalancto>(centroCustos.size());
		for (CentroCusto dto : centroCustos) {

			OrReqplanilhalancto orReqplanilhalancto =  new OrReqplanilhalancto();

			if (dto.getCentrocustodb()!=null){
				orReqplanilhalancto.setCentrocustodb(dto.getCentrocustodb().intValue());
			}

			if (dto.getCentrocustodb()!=null){
				orReqplanilhalancto.setCodentidadedb(dto.getCentrocustodb().intValue());
			}

			if (dto.getContacredito()!=null){
				orReqplanilhalancto.setContacredito(dto.getContacredito().longValue());
			}

			if (dto.getContadebito()!=null){
				orReqplanilhalancto.setContadebito(dto.getContadebito().longValue());
			}

			if (dto.getHistorico()!=null){
				orReqplanilhalancto.setHistorico(dto.getHistorico());
			}

			if (dto.getNroempresa()!=null){
				orReqplanilhalancto.setNroempresa(dto.getNroempresa().shortValue());
			}

			if (dto.getNrolinha()!=null){
				orReqplanilhalancto.setNrolinha(dto.getNrolinha().shortValue());
			}

			if (dto.getNroplanilha()!=null){
				orReqplanilhalancto.setNroplanilha(dto.getNroplanilha());
			}

			orReqplanilhalanctos.add(orReqplanilhalancto);

		}

		return orReqplanilhalanctos;
	}



	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Centro de Custo");
	}

}
