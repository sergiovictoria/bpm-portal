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
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.entity.OrRequisicaoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;



@Stateless(name="Requisicao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Requisicao extends GenericJpaDaoConSinco<OrRequisicaoEntity> {

	@Inject
    private Logger logger;
	
	private static final String _FIND_REQUISICAO="SELECT REQ, orReqplanilhalanctos FROM OrRequisicaoEntity AS REQ INNER JOIN FETCH REQ.orReqitensdespesas AS orReqitensdespesas INNER JOIN FETCH REQ.orReqplanilhalanctos AS orReqplanilhalanctos INNER JOIN FETCH REQ.orRequisicaovenctos AS orRequisicaovenctos WHERE REQ.nrorequisicao= :nrorequisicao";
	
	private static final String _FIND_REQUISICAO_NATIVE_NRO   = "SELECT * FROM OR_REQUISICAO REQ INNER JOIN OR_REQITENSDESPESA DES ON REQ.SEQREQUISICAO = DES.SEQREQUISICAO "+
			                                                    "INNER JOIN OR_REQPLANILHALANCTO LAN ON REQ.SEQREQUISICAO = LAN.SEQREQUISICAO "+
			                                                    "INNER JOIN OR_REQUISICAOVENCTO VEN ON REQ.SEQREQUISICAO = VEN.SEQREQUISICAO WHERE REQ.NROREQUISICAO = :nrorequisicao";
	
	
	private static final String _FIND_REQUISICAO_NATIVE_SEQ_UN = "SELECT R.NROREQUISICAO FROM OR_REQUISICAO R WHERE R.seqrequisicao= :seqrequisicao";
          
	
	
	@PostConstruct
	public void init() {
		logger.info(" *************** Inserindo requisicão ********************** ");
	}
	
	
	@Inject @Any private Event<OrRequisicao> event; 
	
	private static final long serialVersionUID = 1L;

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long getNextSeQRequsicao() {
		Query query = getEntityManager().createNativeQuery("SELECT S_ORREQUISICAO.nextval from DUAL");
		BigDecimal result=(BigDecimal)query.getSingleResult();   
		return result.longValue();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long getNextNroRequsicao() {
		Query query = getEntityManager().createNativeQuery("SELECT S_SEQREQORCAMENTO.nextval from DUAL");
		BigDecimal result=(BigDecimal)query.getSingleResult();   
		return result.longValue();
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insert(OrRequisicao orRequisicao) {
		OrRequisicaoEntity orRequisicaoEntity  = (OrRequisicaoEntity) copyObject(new OrRequisicaoEntity() , orRequisicao);
		insert(orRequisicaoEntity);
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void criaRequsicao(OrRequisicao orRequisicao) {
		OrRequisicaoEntity orRequisicaoEntity  = (OrRequisicaoEntity) copyObject(new OrRequisicaoEntity(),orRequisicao);
		orRequisicao.setSeqrequisicao(getNextNroRequsicao());
		orRequisicaoEntity.setSeqrequisicao(orRequisicao.getSeqrequisicao());
		insert(orRequisicaoEntity);
    	event.fire(orRequisicao);
	}
		
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public OrRequisicaoEntity getRequsicaoNroReq(long nrorequisicao) {
		Query query = getEntityManager().createNativeQuery(_FIND_REQUISICAO_NATIVE_NRO,OrRequisicaoEntity.class);
		query.setParameter("nrorequisicao",nrorequisicao);
		List<?> list = query.getResultList();
		return (OrRequisicaoEntity) (list.size()>0 ? list.get(0) : null);
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public BigDecimal getRequsicaoNroSeqUni(long seqrequisicao) {
		Query query = getEntityManager().createNativeQuery(_FIND_REQUISICAO_NATIVE_SEQ_UN);
		query.setParameter("seqrequisicao",seqrequisicao);
 	    return (BigDecimal) query.getSingleResult();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findRequsicaoNroSeqRequisicao(long seqrequisicao) {
		Query query = getEntityManager().createNativeQuery(_FIND_REQUISICAO_NATIVE_SEQ_UN);
		query.setParameter("seqrequisicao",seqrequisicao);
 	    return ((BigDecimal) query.getSingleResult()).longValue();
	}
		

	@PreDestroy
	public void destroy() {
		logger.info(" *************** Requisicão  Executada ********************** ");
	}
		
	
}
