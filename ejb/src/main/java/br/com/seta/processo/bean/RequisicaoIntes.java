package br.com.seta.processo.bean;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.entity.OrReqitensdespesaEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.map.MapObjectEntity;




@Stateless(name="RequisicaoIntes")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class RequisicaoIntes extends GenericJpaDaoConSinco<OrReqitensdespesaEntity> {



	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@PostConstruct
	public void init() {
		logger.info(" *************** Inserindo Itens da Requisicão ********************** ");
	}

	public void onOrRequisicaoItensChanged(@Observes OrRequisicao orRequisicao) {
		criaItens(orRequisicao);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void criaItens(OrRequisicao orRequisicao) {
		Set<OrReqitensdespesa> orReqitensdespesas = orRequisicao.getOrReqitensdespesas();
		Long seqrequisicao = orRequisicao.getSeqrequisicao();
		for (OrReqitensdespesa dto : orReqitensdespesas) {
			OrReqitensdespesaEntity orReqitensdespesaEntity = MapObjectEntity.mapIntensDespesasEntity(dto,seqrequisicao);
			insert(orReqitensdespesaEntity);
		}
	}	



	@PreDestroy
	public void destroy() {
		logger.info(" *************** Itens  Executado ********************** ");
	}



}
