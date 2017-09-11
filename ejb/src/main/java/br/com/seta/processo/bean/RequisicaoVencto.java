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
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.entity.OrRequisicaovenctoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.map.MapObjectEntity;


@Stateless(name="RequisicaoVencto")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class RequisicaoVencto extends GenericJpaDaoConSinco<OrRequisicaovenctoEntity> {

	
	@Inject
    private Logger logger;
	
	
	@PostConstruct
	public void init() {
		logger.info(" *************** Inserindo Vencimentos da Requisicão ********************** ");
	}

	private static final long serialVersionUID = 1L;

	public void onOrRequisicaoVenctoChanged(@Observes OrRequisicao orRequisicao) {
		criaVencimento(orRequisicao);
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void criaVencimento(OrRequisicao orRequisicao) {
		Set<OrRequisicaovencto>   requisicaovenctos = orRequisicao.getOrRequisicaovenctos();
		Short nroparcela = new Short("1");
		Long seqrequisicao = orRequisicao.getSeqrequisicao();
		for (OrRequisicaovencto dto : requisicaovenctos) {
			OrRequisicaovenctoEntity orRequisicaovenctoEntity = (OrRequisicaovenctoEntity) MapObjectEntity.mapVenctoEntity(dto,seqrequisicao);
			insert(orRequisicaovenctoEntity);
			nroparcela++;
		}
	}
	
	@PreDestroy
	public void destroy() {
		logger.info(" *************** Vencimentos  Executado ********************** ");
	}

}
