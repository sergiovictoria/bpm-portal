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
import br.com.seta.processo.dto.ModeloDocumento;
import br.com.seta.processo.dto.Transportadora;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;



@Stateless(name="ModeloDocumentos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class ModeloDocumentos extends GenericJpaDaoConSinco<Transportadora> {

	
	
	private static final long serialVersionUID = 1L;
	private final String _FIND_MODELO_ASC_DES = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC ORDER BY GE_MODELODOC.DESCRICAO ASC";
	private final String _FIND_MODELO_DES_DES = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC ORDER BY GE_MODELODOC.DESCRICAO DESC";
	
	private final String _FIND_MODELO_ASC_COD = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC ORDER BY GE_MODELODOC.CODMODELO ASC";
	private final String _FIND_MODELO_DES_COD = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC ORDER BY GE_MODELODOC.CODMODELO DESC";
	
	
	private final String _FIND_MODELO_ASC_DES_LIKE = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC WHERE GE_MODELODOC.CODMODELO LIKE :descricao ORDER BY GE_MODELODOC.DESCRICAO ASC";
	private final String _FIND_MODELO_DES_DES_LIKE = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC WHERE GE_MODELODOC.CODMODELO LIKE :descricao ORDER BY GE_MODELODOC.DESCRICAO DESC";
	
	private final String _FIND_MODELO_ASC_COD_LIKE = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC WHERE GE_MODELODOC.CODMODELO LIKE :descricao ORDER BY GE_MODELODOC.CODMODELO ASC";
	private final String _FIND_MODELO_DES_COD_LIKE = "SELECT GE_MODELODOC.CODMODELO, GE_MODELODOC.DESCRICAO, GE_MODELODOC.CODESPECIE FROM GE_MODELODOC WHERE GE_MODELODOC.CODMODELO LIKE :descricao ORDER BY GE_MODELODOC.CODMODELO DESC";
					
	private final String _FIND_MODELO_COUNT      = "SELECT COUNT(*) FROM GE_MODELODOC";
	private final String _FIND_MODELO_COUNT_LIKE = "SELECT COUNT(*) FROM GE_MODELODOC WHERE GE_MODELODOC.DESCRICAO LIKE :descricao";
	
	
	@Inject
    private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info(" Acessando EJB - Modelo Nota Fiscal ");
	}
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<ModeloDocumento> getModeloS(String oder,long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.ModeloAscDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_ASC_DES).setFirstResult((int)first).setMaxResults((int)count);;
			
		}else if (oder.equals(OderProvider.ModeloDesDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_DES_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.ModeloAscCodigo.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_ASC_COD).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_DES_COD).setFirstResult((int)first).setMaxResults((int)count);
		}
				
		List<ModeloDocumento> modeloDocumentos = (List <ModeloDocumento>) getModeloDocumentoS(query.getResultList());
		return modeloDocumentos;
		
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<ModeloDocumento> getModeloSLike(String oder, String descricao,long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.ModeloAscDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_ASC_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);

			
		}else if (oder.equals(OderProvider.ModeloDesDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_DES_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.ModeloAscCodigo.getValue())) {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_ASC_COD_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(_FIND_MODELO_DES_COD_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}
		
		query.setParameter("descricao", "%"+descricao+"%");
				
		List<ModeloDocumento> modeloDocumentos = (List <ModeloDocumento>) getModeloDocumentoS(query.getResultList());
		return modeloDocumentos;
		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size( ) {
		Query query = getEntityManager().createNativeQuery(_FIND_MODELO_COUNT);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(String descricao) {
		Query query = getEntityManager().createNativeQuery(_FIND_MODELO_COUNT_LIKE);
		query.setParameter("descricao","%"+descricao.toUpperCase()+"%");
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
			
	
	@PreDestroy
	public void destroy() {
		logger.info(" Acessando EJB - Modelo Nota Fiscal ");
	}
	
	


}
