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
import br.com.seta.processo.dto.CtCusto;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="CentroCustos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class CentroCustos extends GenericJpaDaoConSinco<CentroCusto> {

	private static final long serialVersionUID = 1L;


	@Inject
	private Logger logger;


	private static final String _CENTRO_CUSTO_NATUREZA = "SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
			"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico ORDER BY CTP.CENTROCUSTODB";

	static final String _CENTRO_CUSTO_NATUREZA_LIKE = "SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
			"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico AND CTP.HISTORICO LIKE :historico ORDER BY CTP.CENTROCUSTODB";
	
	private static final String _CENTRO_CUSTO_NATUREZA_SIZE = 
			"SELECT COUNT(*) FROM ( " + 
					"SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
					"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR " +
					"FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH " + 
					 	"ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico" +
			")";

	static final String _CENTRO_CUSTO_NATUREZA_SIZE_LIKE = "SELECT COUNT (DISTINCT CTP.NROLINHA, CTP.NROPLANILHA,  CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, "+
			"CTP.HISTORICO, CTH.CODHISTORICO, CTP.TIPOENTIDADECR) FROM CT_PLANILHALINHA CTP INNER JOIN CT_HISTORICO CTH ON (CTP.NROPLANILHA = CTH.NROPLANILHA) AND CTP.NROEMPRESA = :nroempresa AND CTH.CODHISTORICO =:codhistorico AND CTP.HISTORICO LIKE :historico";
	
	
	static final String _LISTA_CENTRO_CUSTOS_POR_LOJA = "SELECT CC.CENTROCUSTO, CC.DESCRICAO FROM CT_CENTROCUSTO CC"
			+ " WHERE CC.SITUACAO = 'A' AND CC.NROEMPRESA = :NROEMPRESA ORDER BY CC.DESCRICAO";
	
	static final String _LISTA_CENTRO_CUSTOS_POR_LOJA_LIKE_FILTRO = "SELECT CC.CENTROCUSTO, CC.DESCRICAO FROM CT_CENTROCUSTO CC"
			+ " WHERE CC.SITUACAO = 'A' AND CC.NROEMPRESA = :NROEMPRESA AND CC.CENTROCUSTO || CC.DESCRICAO like :FILTRO ORDER BY CC.DESCRICAO";
	
	static final String _LISTA_CENTRO_CUSTO_SOLICITACAO = "SELECT DISTINCT CTP.NROLINHA, CTP.NROPLANILHA, CTP.NROEMPRESA, CTP.FILIAL, CTP.CONTADEBITO, CTP.CENTROCUSTODB, CTP.CONTACREDITO, CTP.HISTORICO,"
			+ " 0,  CTP.TIPOENTIDADECR, CTP.TIPOENTIDADEDB, CTP.CODENTIDADEDB, CTP.CODENTIDADECR, CTP.CENTROCUSTOCR"
			+ " FROM CT_PLANILHALINHA CTP WHERE CTP.NROEMPRESA = :nroempresa AND CTP.NROPLANILHA = :nroplanilha ORDER BY CTP.NROLINHA";

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Centro de Custo");
	}

	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Centro de Custo
	 * @param codhistorico Codigo do Histórico Centro de Custo
	 * @param first Primeiro Registro
	 * @param count Quantidade de Registros
	 * @return Retorna uma lista com centro de custos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CentroCusto> findCustoS(BigDecimal nroempresa, BigDecimal codhistorico, long first, long count) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		List<CentroCusto>  centroCustos = getResultListMap(query, CentroCusto.class);
		return centroCustos;
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Centro de Custo
	 * @param codhistorico Codigo do Histórico Centro de Custo
	 * @param Filtro Nome do historico para LIKE
	 * @param first Primeiro Registro
	 * @param count Quantidade de Registros
	 * @return Retorna uma lista com centro de custos
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CentroCusto> findCustoS(BigDecimal nroempresa, BigDecimal codhistorico, String filtro ,  long first, long count) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		query.setParameter("historico",  filtro);
		List<CentroCusto>  centroCustos = getResultListMap(query, CentroCusto.class);
		return centroCustos;
	}
	
	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Número da Empresa para Centro de Custo
	 * @param codhistorico Codigo do Histórico Centro de Custo
	 * @return Retorna Quantidade de Registro
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findCustoSize(BigDecimal nroempresa, BigDecimal codhistorico) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA_SIZE);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}

	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Número da Empresa para Centro de Custo
	 * @param codhistorico Codigo do Histórico Centro de Custo
	 * @param Filtro nome centro de custa LIKE
	 * @return Retorna Quantidade de Registros
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findCustoSize(BigDecimal nroempresa, BigDecimal codhistorico, String filtro) {
		Query query = getEntityManager().createNativeQuery(_CENTRO_CUSTO_NATUREZA_SIZE);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("codhistorico",  codhistorico);
		query.setParameter("historico",  filtro);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	
	/**
	 * @author João Cesar
	 * @param nroempresa
	 * @param filtro codigo ou descricao do centro de custo
	 * @return lista com os centros de custos da empresa
	 */
	@SuppressWarnings({ "unchecked", "null" })
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CtCusto> findCtCustos(BigDecimal nroempresa, String filtro) {
		
		Query query = null;
		
		if(filtro != null && !filtro.isEmpty())
			query = getEntityManager().createNativeQuery(_LISTA_CENTRO_CUSTOS_POR_LOJA_LIKE_FILTRO);
		else
			query = getEntityManager().createNativeQuery(_LISTA_CENTRO_CUSTOS_POR_LOJA);
		
		query.setParameter("NROEMPRESA", nroempresa);
		
		if(filtro != null && !filtro.isEmpty())
			query.setParameter("FILTRO", "%" + filtro.toUpperCase() + "%");
		
		List<Object[]> result = query.getResultList();
		List<CtCusto> centroCustos = new ArrayList<CtCusto>();;
		
		for(Object[] row : result) {
			
			CtCusto cc = new CtCusto();
			
			try { cc.setCentrocusto((BigDecimal) row[0]); } catch(Exception e){}
			try { cc.setDescricao(row[1].toString()); } catch(Exception e){}
			
			centroCustos.add(cc);
		}
		
		return centroCustos;
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CentroCusto> findCentroCustosSolicitcao(BigDecimal nroempresa, String nroplanilha) {
		Query query = getEntityManager().createNativeQuery(_LISTA_CENTRO_CUSTO_SOLICITACAO);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("nroplanilha", nroplanilha);
		List<CentroCusto>  centroCustos = getResultListMap(query, CentroCusto.class);
		return centroCustos;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Centro de Custo");
	}

}
