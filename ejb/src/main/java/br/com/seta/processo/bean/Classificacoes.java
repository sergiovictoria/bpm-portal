package br.com.seta.processo.bean;

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
import br.com.seta.processo.dto.ClassificacaoComercial;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name = "Classificacoes")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({ LogInterceptor.class })
@LocalBean
public class Classificacoes extends GenericJpaDaoConSinco<ClassificacaoComercial> {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	private static final String _CLASSIFICACAOCOMERCIAL_FIND = "SELECT DISTINCT (CLASSIFCOMERCABC) FROM MAP_CLASSIFABC";

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - EJB - Classificacoes");
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<ClassificacaoComercial> getClassificacaoComercial() {
		Query query = getEntityManager().createNativeQuery(_CLASSIFICACAOCOMERCIAL_FIND);
		List<ClassificacaoComercial> listaClassificacaoComercial = new ArrayList<ClassificacaoComercial>();
		List<String> listaEncontrada = query.getResultList();
		for (String classificacao : listaEncontrada) {
			ClassificacaoComercial cc = new ClassificacaoComercial();
			cc.setClassifcomercabc(classificacao);
			listaClassificacaoComercial.add(cc);
		}
		return listaClassificacaoComercial;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> listaClassificacoesComercial(){
		Query query = getEntityManager().createNativeQuery(_CLASSIFICACAOCOMERCIAL_FIND);
		List<String> classificacoes = query.getResultList();
		return classificacoes;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Classificacoes");
	}

}
