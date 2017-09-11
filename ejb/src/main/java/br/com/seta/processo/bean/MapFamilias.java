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
import br.com.seta.processo.dto.MapFamilia;
import br.com.seta.processo.entity.MapFamiliaEntity;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="MapFamilias")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapFamilias extends GenericJpaDaoConSinco<MapFamiliaEntity> {


	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private static final String FAMILIA_UPDATE_NCM = "UPDATE MAP_FAMILIA SET MAP_FAMILIA.CODNBMSH = :txtNcm WHERE MAP_FAMILIA.SEQFAMILIA = :seqfamilia";


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Famílias" );
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapFamiliaEntity create (MapFamiliaEntity familiaEntity) {
		return persist(familiaEntity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void delete(MapFamiliaEntity familiaEntity) {
		delete(familiaEntity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapFamiliaEntity find(BigDecimal seqfamilia) {
		Query query = getEntityManager().createNamedQuery("FAMILIA.FIND_ONE");
		query.setParameter("seqfamilia",seqfamilia);
		return (MapFamiliaEntity) query.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapFamiliaEntity> getMapFamiliaAll() {
		Query query = getEntityManager().createNamedQuery("FAMILIA.ALL");
		return (List<MapFamiliaEntity>) query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(String familia, long first, long count) {
		Query query = getEntityManager().createNamedQuery("FAMILIA.LIKE.SIZE").setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("familia","%"+familia+"%");
		return ((BigDecimal) query.getSingleResult()).longValue();
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateNcm(BigDecimal seqfamilia, BigDecimal txtNcm) {
		Query query = getEntityManager().createNativeQuery(FAMILIA_UPDATE_NCM);
		query.setParameter("seqfamilia", seqfamilia);
		query.setParameter("txtNcm", txtNcm);
		query.executeUpdate();
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapFamilia> findAll(long first, long count, String familia, BigDecimal seqfamilia, BigDecimal seqforncedor) {
		String sql = "SELECT MAF.FAMILIA, MAF.SEQFAMILIA FROM MAP_FAMILIA MAF, MAP_FAMFORNEC MFC WHERE 1=1 AND MAF.SEQFAMILIA = MFC.SEQFAMILIA AND MFC.SEQFORNECEDOR = :seqfornecedor";

		if(familia != null && !familia.isEmpty())
			sql += " and MAF.familia like :familia";

		if(seqfamilia != null)
			sql += " and MAF.seqfamilia like :seqfamilia";

		sql += " order by MAF.seqfamilia";

		Query query = getEntityManager().createNativeQuery(sql).setFirstResult((int)first).setMaxResults((int)count);

		query.setParameter("seqfornecedor", seqforncedor.longValue());
		
		if(familia != null && !familia.isEmpty())
			query.setParameter("familia","%"+familia.toUpperCase()+"%");

		if(seqfamilia != null)
			query.setParameter("seqfamilia","%"+seqfamilia+"%");

		List<MapFamilia> familias = new ArrayList<MapFamilia>();

		List<Object[]> list = query.getResultList();

		for(Object[] obj : list) {
			MapFamilia fam = new MapFamilia();
			try { fam.setFamilia(obj[0].toString()); } catch(Exception e) {}
			try { fam.setSeqfamilia( new BigDecimal(obj[1].toString())); } catch(Exception e) {}
			familias.add(fam);
		}

		return familias;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(String familia, BigDecimal seqfamilia, BigDecimal seqfornecedor) {
		String sql = "SELECT COUNT(*) FROM MAP_FAMILIA MAF, MAP_FAMFORNEC MFC WHERE 1=1 AND MAF.SEQFAMILIA = MFC.SEQFAMILIA AND MFC.SEQFORNECEDOR = :seqfornecedor";

		if(familia != null && !familia.isEmpty())
			sql += " and MAF.familia like :familia";

		if(seqfamilia != null)
			sql += " and MAF.seqfamilia like :seqfamilia";

		Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("seqfornecedor",seqfornecedor.longValue());

		if(familia != null && !familia.isEmpty())
			query.setParameter("familia","%"+familia.toUpperCase()+"%");

		if(seqfamilia != null)
			query.setParameter("seqfamilia","%"+seqfamilia+"%");

		return ((BigDecimal) query.getSingleResult()).longValue();
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapFamiliaEntity> getMapFamilias(String seqfamilia, String familia, long first, long count) {
		Query query = getEntityManager().createNamedQuery("FAMILIA.LIKE_NAME").setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("familia","%"+familia+"%");
		return (List<MapFamiliaEntity>) query.getResultList();
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo  EJB - Famílias" );
	}


}
