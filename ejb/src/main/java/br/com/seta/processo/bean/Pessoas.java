
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
import br.com.seta.processo.dto.Pessoa;
import br.com.seta.processo.entity.PessoaEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;



@Stateless(name="Pessoas")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Pessoas extends GenericJpaDaoConSinco<Pessoa> {
	
	
	private static final long serialVersionUID = 1L;

	private static final String _PESSOA_RAZAO_ASC = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL ORDER BY G.NOMERAZAO ASC";
	private static final String _PESSOA_RAZAO_DES = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL ORDER BY G.NOMERAZAO DES";
	
	
	private static final String _PESSOA_COD_ASC   = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL ORDER BY G.SEQPESSOA ASC";
	private static final String _PESSOA_COD_DES   = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL ORDER BY G.SEQPESSOA DESC";
	
	
	
	private static final String _PESSOA_RAZAO_ASC_LIKE = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO LIKE :nomerazao AND G.NOMERAZAO IS NOT NULL ORDER BY G.NOMERAZAO ASC";
	private static final String _PESSOA_RAZAO_DES_LIKE = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO LIKE :nomerazao AND G.NOMERAZAO IS NOT NULL ORDER BY G.NOMERAZAO DES";
	
	
	private static final String _PESSOA_COD_ASC_LIKE   = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO LIKE :nomerazao AND G.NOMERAZAO IS NOT NULL ORDER BY G.SEQPESSOA ASC";
	private static final String _PESSOA_COD_DES_LIKE   = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO LIKE :nomerazao AND G.NOMERAZAO IS NOT NULL ORDER BY G.SEQPESSOA DESC";
	
	
	private static final String _PESSOA_COUNT      = "SELECT COUNT(G.STATUS) FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL";
	private static final String _PESSOA_COUNT_LIKE = "SELECT COUNT(G.STATUS) FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO  LIKE :nomerazao AND G.NOMERAZAO IS NOT NULL";
	
		
	@Inject
    private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Pessoas ");
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Pessoa> getPessoaS(String oder, long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.PessoaAscDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_PESSOA_RAZAO_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.PessoaDesDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_PESSOA_RAZAO_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.PessoaAscCodigo.getValue())) {
			query = getEntityManager().createNativeQuery(_PESSOA_COD_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(_PESSOA_COD_DES).setFirstResult((int)first).setMaxResults((int)count);
		}
				
		List<Pessoa> pessoas = mapPessoaS(query.getResultList());
		return pessoas;
		
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Pessoa> getPessoaSLike(String oder, String nomerazao,long first, long count) {
		
		Query query = null;
		
		if (oder.equals(OderProvider.NomeRazaoAsc.getValue())) {
			
			query = getEntityManager().createNativeQuery(_PESSOA_RAZAO_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
			
		}else if (oder.equals(OderProvider.NomeRazaoDesc.getValue())) {
			
			query = getEntityManager().createNativeQuery(_PESSOA_RAZAO_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
			
		}else if (oder.equals(OderProvider.SeqPessoaAsc.getValue())) {
			
			query = getEntityManager().createNativeQuery(_PESSOA_COD_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
			
		}else {
			query = getEntityManager().createNativeQuery(_PESSOA_COD_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);
			query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
		}
				
		List<Pessoa> pessoas = mapPessoaS(query.getResultList());
		return pessoas;
		
	}
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size( ) {
		Query query = getEntityManager().createNativeQuery(_PESSOA_COUNT);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(String nomerazao) {
		Query query = getEntityManager().createNativeQuery(_PESSOA_COUNT_LIKE);
		query.setParameter("nomerazao","%"+nomerazao.toUpperCase()+"%");
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa getPessoasDetalheComID(Pessoa pessoa) {
		Query query = getEntityManager().createNamedQuery("Pessoa.TodasAtivasID");
		query.setParameter("seqpessoa",pessoa.getSeqpessoa());
		List<PessoaEntity> pessoaEntities = (List<PessoaEntity>) query.getResultList();
		System.out.println(pessoaEntities.get(0).toString());
		return (Pessoa) (pessoaEntities.size() > 0 ? copyObject(new Pessoa() , pessoaEntities.get(0) ) : null);
	}
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Pessoas ");
	}
	
		
	

}
