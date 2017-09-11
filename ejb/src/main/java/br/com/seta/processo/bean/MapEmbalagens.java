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
import br.com.seta.processo.dto.MapNcmDto;
import br.com.seta.processo.entity.MapEmbalagemEntity;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="MapEmbalagens")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapEmbalagens extends GenericJpaDaoConSinco<MapEmbalagemEntity> {


	private static final long serialVersionUID = 1L;
	
	private static final String _FIND_EMBALAGEM = "SELECT * FROM MAP_FAMEMBALAGEM EMB WHERE EMB.SEQFAMILIA = :seqfamilia AND EMB.QTDEMBALAGEM = :qtdembalagem";

	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Embalagens" );
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MapEmbalagemEntity create(MapEmbalagemEntity embalagemEntity) {
		getEntityManager().persist(embalagemEntity);
		return embalagemEntity;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void delete(MapEmbalagemEntity embalagemEntity) {
		delete(embalagemEntity);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> findEmbalagem() {
		String sql = "select a.lista from max_atributofixo a"
				+ " where a.tipatributofixo = 'EMBALAGEM' order by a.lista";
		
		Query query = getEntityManager().createNativeQuery(sql);
		List<String> list = query.getResultList();
		return list;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> findEmbalagemReduzido() {
		String sql = "select a.lista from max_atributofixo a"
				+ " where a.tipatributofixo = 'EMBALAGEM' order by a.lista";
		
		Query query = getEntityManager().createNativeQuery(sql);
		List<String> list = query.getResultList();
		return list;
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public boolean IsExisteEmbalagem(BigDecimal seqfamilia, BigDecimal qtdembalagem) {
		Query query = getEntityManager().createNativeQuery(_FIND_EMBALAGEM);
		query.setParameter("seqfamilia",seqfamilia);
		query.setParameter("qtdembalagem",qtdembalagem);
		return query.getResultList().size() > 0 ? Boolean.TRUE : Boolean.FALSE;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> findOrigemProduto(){
		String sql = "select CONCAT(a.lista, CONCAT(' - ', a.descricao))"
				+ " from max_atributofixo a"
				+ " where a.tipatributofixo like '%ORIGEMMERCADORIA%' order by a.lista";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		List<String> list = query.getResultList();
		
		return list;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MapNcmDto> findNcm(long first, long count, String ncm){
		String sql = "SELECT A.CODNCM, A.DESCREDUZIDA FROM MAP_NCM A WHERE 1=1";
		
		if(ncm != null && !ncm.isEmpty())
			sql += " AND A.CODNCM || A.DESCREDUZIDA LIKE :DESCREDUZIDA";
		
		Query query = getEntityManager().createNativeQuery(sql).setFirstResult((int)first).setMaxResults((int)count);
		
		if(ncm != null && !ncm.isEmpty())
			query.setParameter("DESCREDUZIDA", "%" + ncm + "%");
		
		List<Object[]> list = query.getResultList();
		
		List<MapNcmDto> ncms = new ArrayList<MapNcmDto>();
		
		for(Object[] obj : list){
			MapNcmDto dto = new MapNcmDto();
			
			try { dto.setCodncm( (String) obj[0]);} catch (Exception e) {}
			try { dto.setDescricao( (String) obj[1]);} catch (Exception e) {}
			
			ncms.add(dto);
		}
		
		return ncms;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long sizeNcm(String ncm) {
		String sql = "SELECT count(*) FROM MAP_NCM A WHERE 1=1";
		
		if(ncm != null && !ncm.isEmpty())
			sql += " AND A.CODNCM || A.DESCREDUZIDA LIKE :DESCREDUZIDA";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		if(ncm != null && !ncm.isEmpty())
			query.setParameter("DESCREDUZIDA", "%" + ncm + "%");
		
		Long size = ((BigDecimal) query.getSingleResult()).longValue();
		
		
		return size;
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo  EJB - Embalagens" );
	}


}
