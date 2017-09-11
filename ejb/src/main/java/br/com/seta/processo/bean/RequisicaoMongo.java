package br.com.seta.processo.bean;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="RequisicaoMongo")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class RequisicaoMongo {

	
	@Inject
    private Logger logger;
	
	@Inject
	private Requisicao requisicao;
	
	@Inject
	private TransacaoMongo transacaoMongo;
	
	@PostConstruct
	public void init() {
		logger.info(" *************** Salvando Requisição Mongo ********************** ");
	}
		
	public void save(OrRequisicao orRequisicao) {
		BigDecimal numRec = requisicao.getRequsicaoNroSeqUni(orRequisicao.getSeqrequisicao());
		orRequisicao.setNrorequisicao(numRec.longValue());
		transacaoMongo.save(orRequisicao, OrRequisicao.class);
	}

	@PreDestroy
	public void destroy() {
		logger.info(" *************** Executado Requisição Mongo ********************** ");
	}

}
