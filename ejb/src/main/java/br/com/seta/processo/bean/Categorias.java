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
import br.com.seta.processo.dto.Categoria;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name = "Categorias")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({ LogInterceptor.class })
@LocalBean
public class Categorias extends GenericJpaDaoConSinco<Categoria> {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_CATEGORIAS = 
			"select categoria"  
			+ " from map_categoria"
			+ " where nrodivisao = '3'"              
			+ " and tipcategoria = 'M'"                
			+ " and nivelhierarquia = 1  "
			+ " and statuscategor = 'A'"
			+ " and seqcategoria not in (2603, 2590)"
			+ " order by categoria";
	
	
	@Inject
	private Logger logger;

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - EJB - Categorias");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Categoria> getCategorias() {
		Query query = getEntityManager().createNativeQuery(LISTA_CATEGORIAS);
		List<String> list = query.getResultList();
		return criarLista(list);
	}
	
	/**
	 * Lista todas as categorias
	 * 
	 * @return Uma lista contendo as categorias
	 */
	@SuppressWarnings("unchecked")
	public List<String> listaCategorias(){
		Query query = getEntityManager().createNativeQuery(LISTA_CATEGORIAS);
		List<String> categorias = query.getResultList();
		return categorias;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Categorias");
	}
	
	private List<Categoria> criarLista(List<String> list){
		List<Categoria> categorias = new ArrayList<Categoria>();
		for (String obj : list) {
			categorias.add(new Categoria(obj));
		}
		return categorias;
	}

}
