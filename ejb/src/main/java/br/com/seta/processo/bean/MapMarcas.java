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
import br.com.seta.processo.entity.MapMarcaEntity;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Marcas")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapMarcas extends GenericJpaDaoConSinco<MapMarcaEntity> {


	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Marcas" );
	}	
		

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapMarcaEntity find(BigDecimal seqmarca) {
		Query query = getEntityManager().createNamedQuery("MARCA.FIND_ONE");
		query.setParameter("seqmarca",seqmarca);
		return (MapMarcaEntity) query.getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapMarcaEntity> getMapFamiliaAll() {
		Query query = getEntityManager().createNamedQuery("MARCA.ALL");
		return (List<MapMarcaEntity>) query.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(String marca, long first, long count) {
		Query query = getEntityManager().createNamedQuery("MARCA.LIKE.SIZE").setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("marca","%"+marca+"%");
        return ((BigDecimal) query.getSingleResult()).longValue();
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapMarcaEntity> getMapFamilias(String marca, String familia, long first, long count) {
		Query query = getEntityManager().createNamedQuery("MARCA.LIKE_NAME").setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("marca","%"+marca+"%");
		return (List<MapMarcaEntity>) query.getResultList();
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo  EJB - Marcas" );
	}


}
