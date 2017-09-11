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
import br.com.seta.processo.dto.Comprador;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Compradores")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Compradores extends GenericJpaDaoConSinco<Comprador>  {

	private static final long serialVersionUID = 1L;

	private static final String LISTA_COMPRADORES        = "SELECT * FROM SETAV_COMPRADOR_BPMSETA";
	private static final String _COMPRADOR_FIND          = "SELECT A.SEQCOMPRADOR, A.APELIDO FROM MAX_COMPRADOR A ORDER BY A.APELIDO";
	private static final String _COMPRADOR_FIND_SIZE     = "SELECT COUNT(*) FROM MAX_COMPRADOR";
	private static final String _COMPRADOR_FIND_LIKE     = "SELECT A.SEQCOMPRADOR, A.APELIDO FROM MAX_COMPRADOR A WHERE A.APELIDO LIKE :apelido ORDER BY A.APELIDO";
	private static final String _COMPRADOR_FIND_LIKE_SIZE= "SELECT COUNT(*) FROM MAX_COMPRADOR A WHERE A.APELIDO LIKE :apelido";
	private static final String _COMPRADOR_FIND_NAME     = "SELECT A.APELIDO FROM MAX_COMPRADOR A ORDER BY A.APELIDO";
	
	@Inject
	private Logger logger;

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - EJB - Compradores");
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Comprador> getCompradoreS() {
		Query query = getEntityManager().createNativeQuery(LISTA_COMPRADORES);
		List<String> list = query.getResultList();
		return criarLista(list);
	}

	/*****
	 * @author Sérgio da Victória
	 * @param first Primeiro Registo
	 * @param count Quantidade de Registros
	 * @param Filtro para pesquisa do apelido comprador
	 * @return Lista de Compradores - DTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Comprador> findCompradoreS(long first, long count, String filtro) {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND_LIKE);
		query.setParameter("apelido", "%"+filtro+"%").setFirstResult((int)first).setMaxResults((int)count);
		List<Comprador> compradors = getResultListMap(query, Comprador.class);
		return compradors;

	}

	/*****
	 * @author Sérgio da Victória
	 * @param first Primeiro Registo
	 * @param count Quantidade de Registros
	 * @return Lista de Compradores - DTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Comprador> findCompradoreS(long first, long count) {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND).setFirstResult((int)first).setMaxResults((int)count);
		List<Comprador> compradors = getResultListMap(query, Comprador.class);
		return compradors;

	}
	
	
	/******
	 * 
	 * @author Sérgio da Victória
	 * @return Retorna quantidade de compradores size - count
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findCompradoresSize( ) {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND_SIZE);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}

	
	
	/******
	 * 
	 * @author Sérgio da Victória
	 * @return Retorna quantidade de compradores size - count
	 * @param Filtro para pesquisa do apelido comprador
	 * 
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findCompradoresSize(String filtro) {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND_LIKE_SIZE);
		query.setParameter("apelido", "%"+filtro+"%");
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	

	/*****
	 * @author Sérgio da Victória
	 * @return Lista de Compradores - DTO
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Comprador> findCompradoreS() {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND);
		List<Comprador> compradors = getResultListMap(query, Comprador.class);
		return compradors;
	}

	
	/*****
	 * @author Sérgio da Victória
	 * @return Lista Uma Lista de Nomes Compradores
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<String> findCompradoreSWithName() {
		Query query = getEntityManager().createNativeQuery(_COMPRADOR_FIND_NAME);
		List<String> compradors = getStringList(query.getResultList());
		return compradors;
	}



	/**
	 * Lista os nomes dos compradores contidas na Consinco
	 * 
	 * @return Uma lista contendo os noems dos compradores da Consinco
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@SuppressWarnings("unchecked")
	public List<String> listaCompradores(){
		Query query = getEntityManager().createNativeQuery(LISTA_COMPRADORES);
		List<String> compradores = query.getResultList();
		return compradores;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private List<Comprador> criarLista(List<String> list){
		List<Comprador> compradores = new ArrayList<Comprador>();
		for (String obj : list) {
			compradores.add(new Comprador(obj));
		}
		return compradores;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	private List<String> extraiNomesCompradores(List<Comprador> listaCompradores) {
		ArrayList<String> nomesCompradores = new ArrayList<String>();
		if (listaCompradores != null) {
			for (Comprador c : listaCompradores) {
				nomesCompradores.add(c.getNome());
			}
		}
		return nomesCompradores;
	}

	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Compradores");
	}



}
