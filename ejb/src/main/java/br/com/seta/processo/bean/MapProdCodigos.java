package br.com.seta.processo.bean;

import java.math.BigDecimal;

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
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.MapProdcodigoEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="MapProdCodigos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean

public class MapProdCodigos extends GenericJpaDaoConSinco<MapProdcodigoEntity> {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Fornecedores");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void create(MapProdcodigoEntity mapProdcodigoEntity) throws DaoException {
		insert(mapProdcodigoEntity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapProdcodigoEntity find(BigDecimal seqproduto, BigDecimal seqfamilia, BigDecimal qtdembalagem) {
		Query query = getEntityManager().createNativeQuery("FIND.QTDEMBALAGEM", MapProdcodigoEntity.class);
		query.setParameter("seqproduto",seqproduto);
		query.setParameter("seqfamilia",seqfamilia);
		query.setParameter("qtdembalagem",qtdembalagem);
		return (MapProdcodigoEntity) query.getResultList();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean findOne(BigDecimal seqproduto, BigDecimal seqfamilia, BigDecimal qtdembalagem) {
		Query query = getEntityManager().createNamedQuery("FIND.QTDEMBALAGEM", MapProdcodigoEntity.class);
		query.setParameter("seqfamilia",seqfamilia);
		query.setParameter("qtdembalagem",qtdembalagem);
		return query.getResultList().size() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
		
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean findOne(BigDecimal seqfamilia, BigDecimal qtdembalagem) {
		Query query = getEntityManager().createNamedQuery("FIND.QTDEMBALAGEM_ID", MapProdcodigoEntity.class);
		query.setParameter("seqfamilia",seqfamilia);
		query.setParameter("qtdembalagem",qtdembalagem);
		return  query.getResultList().size() > 0 ?  Boolean.TRUE : Boolean.FALSE;
	}

	/****
	 * @author Sérgio da Victória
	 * @since Faz busca na tabela MAP_PROCODIDO pela CONSTRAINT_NAME MAP_PRODCO Primary_key
	 * @param cgcfornec 
	 * @param codacesso
	 * @param tipcodigo
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean findMapprod_Cod(Integer cgcfornec, String codacesso, String tipcodigo) {
		Query query = getEntityManager().createNamedQuery("FIND.QTDEMBALAGEM_MAPPROD_COD", MapProdcodigoEntity.class);
		query.setParameter("cgcfornec",cgcfornec);
		query.setParameter("codacesso",codacesso);
		query.setParameter("tipcodigo",tipcodigo);
		return  query.getResultList().size() > 0 ?  Boolean.TRUE : Boolean.FALSE;
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Fornecedores");
	}

}
