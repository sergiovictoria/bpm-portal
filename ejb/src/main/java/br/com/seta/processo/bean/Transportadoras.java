
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
import br.com.seta.processo.dto.Transportadora;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;

@Stateless(name="Transportadoras")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Transportadoras extends GenericJpaDaoConSinco<Transportadora> {
	
	private static final long serialVersionUID = 1L;
	
	private static final String FIND_TRANSPORTADORA                    = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA order by NOMERAZAO";
	private static final String FIND_TRANSPORTADORA_ODER_NOMERAZAO_ASC = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA ORDER BY GE_PESSOA.NOMERAZAO ASC"; 
	private static final String FIND_TRANSPORTADORA_ODER_SEQPESSOA_ASC = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA ORDER BY GE_PESSOA.SEQPESSOA ASC";
	private static final String FIND_TRANSPORTADORA_ODER_NOMERAZAO_DES = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA ORDER BY GE_PESSOA.NOMERAZAO DESC"; 
	private static final String FIND_TRANSPORTADORA_ODER_SEQPESSOA_DES = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA ORDER BY GE_PESSOA.SEQPESSOA DESC";
	private static final String FIND_TRANSPORTADORA_COUNT              = "SELECT COUNT(*) FROM MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA"; 
	
	private static final String FIND_TRANSPORTADORA_ODER_NOMERAZAO_ASC_LIKE = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA AND GE_PESSOA.NOMERAZAO LIKE :nomerazao ORDER BY GE_PESSOA.NOMERAZAO ASC"; 
	private static final String FIND_TRANSPORTADORA_ODER_SEQPESSOA_ASC_LIKE = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA AND GE_PESSOA.NOMERAZAO LIKE :nomerazao ORDER BY GE_PESSOA.SEQPESSOA ASC";
	private static final String FIND_TRANSPORTADORA_ODER_NOMERAZAO_DES_LIKE = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA AND GE_PESSOA.NOMERAZAO LIKE :nomerazao ORDER BY GE_PESSOA.NOMERAZAO DESC"; 
	private static final String FIND_TRANSPORTADORA_ODER_SEQPESSOA_DES_LIKE = "SELECT GE_PESSOA.SEQPESSOA, GE_PESSOA.NOMERAZAO, MAD_TRANSPORTADOR.TIPOTRANSPORTE from MAD_TRANSPORTADOR, GE_PESSOA  where MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA AND GE_PESSOA.NOMERAZAO LIKE :nomerazao ORDER BY GE_PESSOA.SEQPESSOA DESC";
	private static final String FIND_TRANSPORTADORA_COUNT_LIKE              = "SELECT COUNT(*) FROM MAD_TRANSPORTADOR, GE_PESSOA WHERE MAD_TRANSPORTADOR.SEQTRANSPORTADOR = GE_PESSOA.SEQPESSOA AND GE_PESSOA.NOMERAZAO LIKE :nomerazao";
	          																  
	@Inject
    private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Transportadora ");
	}
	
	public List<Transportadora> buscaTodasTransportadoras(){
		Query query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_NOMERAZAO_ASC);
		return getResultListMap(query, Transportadora.class);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Transportadora> getTransportadoraS(String oder, long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.NomeRazaoAsc.getValue())) {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_NOMERAZAO_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.NomeRazaoDesc.getValue())) {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_NOMERAZAO_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.SeqPessoaAsc.getValue())) {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_SEQPESSOA_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_SEQPESSOA_DES).setFirstResult((int)first).setMaxResults((int)count);
		}

		List<Transportadora> transportadoras = (List <Transportadora>) getResultListMap(query, Transportadora.class);
		return transportadoras;
		
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Transportadora> getTransportadoraSLike(String oder, String nomerazao, long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.NomeRazaoAsc.getValue())) {
			
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_NOMERAZAO_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.NomeRazaoDesc.getValue())) {
			
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_NOMERAZAO_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.SeqPessoaAsc.getValue())) {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_SEQPESSOA_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_ODER_SEQPESSOA_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}
		
		query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
				
		List<Transportadora> transportadoras = (List <Transportadora>) getResultListMap(query, Transportadora.class);
		return transportadoras;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size( ) {
		Query query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_COUNT);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(String nomerazao) {
		Query query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA_COUNT_LIKE);
		query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
		return ((BigDecimal) query.getSingleResult()).longValue();
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Transportadora> getTransportadoraS() {
		Query query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA);
		List<Transportadora> transportadoras = (List <Transportadora>) getResultListMap(query, Transportadora.class);
		return transportadoras;
	}

	
	/*****
	 * @author Sérgio da Victória
	 * @return Retorna Transportadoras
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Transportadora> findTransportadoraS() {
		Query query = getEntityManager().createNativeQuery(FIND_TRANSPORTADORA);
		List<Transportadora> transportadoras = (List <Transportadora>) getResultListMap(query, Transportadora.class);
		return transportadoras;
	}
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Transportadora ");
	}
	
	
	
	

}
