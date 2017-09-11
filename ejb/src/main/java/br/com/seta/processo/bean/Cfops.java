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
import br.com.seta.processo.dto.Cfop;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;


@Stateless(name="Cfops")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean

public class Cfops extends GenericJpaDaoConSinco<Cfop>  {

	private static final long serialVersionUID = 1L;


	@Inject
	private Logger logger;
	
	private static final String _CFOP_FIND     ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO ORDER BY CFO.CFOP, CFO.COMPLEMENTO";
	private static final String _CFOP_COMP_ASC ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO ORDER BY CFO.CFOP, CFO.COMPLEMENTO";
	private static final String _CFOP_COMP_DES ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO ORDER BY CFO.CFOP, CFO.COMPLEMENTO DESC";
	
	private static final String _CFOP_CFOP_ASC ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO ORDER BY CFO.CFOP, CFO.COMPLEMENTO";
	private static final String _CFOP_CFOP_DES ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO ORDER BY CFO.CFOP, CFO.COMPLEMENTO DESC";
	
	
	private static final String _CFOP_COMP_ASC_LIKE ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO WHERE CFO.DESCRICAORED LIKE :descricaored ORDER BY CFO.DESCRICAORED";
	private static final String _CFOP_COMP_DES_LIKE ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO WHERE CFO.DESCRICAORED LIKE :descricaored ORDER BY CFO.DESCRICAORED DESC";
	
	private static final String _CFOP_CFOP_ASC_LIKE ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO WHERE CFO.DESCRICAORED LIKE :descricaored ORDER BY CFO.CFOP";
	private static final String _CFOP_CFOP_DES_LIKE ="SELECT CFO.CFOP, CFO.COMPLEMENTO, CFO.DESCRICAORED FROM GE_CFOP CFO WHERE CFO.DESCRICAORED LIKE :descricaored ORDER BY CFO.CFOP DESC";
	
	
	private static final String _CFOP_SIZE      ="SELECT COUNT(CFOP) FROM GE_CFOP CFO";
	private static final String _CFOP_SIZE_LIKE ="SELECT COUNT(CFOP) FROM GE_CFOP CFO WHERE CFO.DESCRICAORED LIKE :descricaored";

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - EJB - Cfops");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Cfop> getCfopS(String oder, long first, long count) {
		Query query = null;
		if (oder.equals(OderProvider.CfopCompAsc.getValue().equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_COMP_ASC).setFirstResult((int)first).setMaxResults((int)count);
		}else if (oder.equals(OderProvider.CfopCompDes.equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_COMP_DES).setFirstResult((int)first).setMaxResults((int)count);
		}else if (oder.equals(OderProvider.CfopCfopAsc.equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_CFOP_ASC).setFirstResult((int)first).setMaxResults((int)count);
		}else {
			query = getEntityManager().createNativeQuery(_CFOP_CFOP_DES).setFirstResult((int)first).setMaxResults((int)count);
		}
		List<Cfop> cfops = getResultListMap(query, Cfop.class);
		return cfops;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Cfop> getCfopSLike(String oder, String descricaored, long first, long count) {
		Query query = null;
		if (oder.equals(OderProvider.CfopCompAsc.getValue().equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_COMP_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		}else if (oder.equals(OderProvider.CfopCompDes.equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_COMP_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		}else if (oder.equals(OderProvider.CfopCfopAsc.equals(oder))) {
			query = getEntityManager().createNativeQuery(_CFOP_CFOP_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		}else {
			query = getEntityManager().createNativeQuery(_CFOP_CFOP_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		}
		query.setParameter("descricaored", "%" + descricaored.toUpperCase() + "%");
		List<Cfop> cfops = getResultListMap(query, Cfop.class);
		return cfops;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size() {
		Query query = getEntityManager().createNativeQuery(_CFOP_SIZE);
		return ((BigDecimal)query.getSingleResult()).longValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(String descricaored) {
		Query query = getEntityManager().createNativeQuery(_CFOP_SIZE_LIKE);
		query.setParameter("descricaored", "%" + descricaored.toUpperCase() + "%");
		return ((BigDecimal)query.getSingleResult()).longValue();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Cfop> getCfopS() {
		Query query = getEntityManager().createNativeQuery(_CFOP_FIND);
		List<Cfop> cfops = getResultListMap(query, Cfop.class);
		return cfops;
	}

	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Cfops");
	}


}
