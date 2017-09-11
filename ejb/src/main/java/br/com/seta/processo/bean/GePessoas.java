package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="GePessoas")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class GePessoas extends GenericJpaDaoConSinco<GePessoaEntity> {
	
	
    private static final String _SQL_COUNT_ALL =   "SELECT COUNT (GEP.SEQPESSOA) FROM GE_PESSOA GEP, MAF_FORNECEDOR FON WHERE GEP.SEQPESSOA = FON.SEQFORNECEDOR AND FON.STATUSGERAL = 'A'";
    private static final String BUSCA_PESSOAS_POR_CODIGO_RAZAO_CPFCNPJ = 
    		"SELECT GEP.* FROM GE_PESSOA GEP, MAF_FORNECEDOR FON " + 
    		"WHERE GEP.SEQPESSOA = FON.SEQFORNECEDOR AND FON.STATUSGERAL = 'A' " + 
    		"AND ( (TO_CHAR(GEP.NROCGCCPF) || TO_CHAR(DIGCGCCPF)) = :cpfCnpj OR GEP.NOMERAZAO LIKE :nomeRazao OR TO_CHAR(GEP.SEQPESSOA) = :seqPessoa) ORDER BY GEP.NOMERAZAO";

    private static final String COUNT_PESSOAS_POR_CODIGO_RAZAO_CPFCNPJ = 
    		"SELECT COUNT(SEQPESSOA) FROM GE_PESSOA GEP, MAF_FORNECEDOR FON " + 
    		"WHERE GEP.SEQPESSOA = FON.SEQFORNECEDOR AND FON.STATUSGERAL = 'A' " + 
    		"AND ( (TO_CHAR(GEP.NROCGCCPF) || TO_CHAR(DIGCGCCPF)) = :cpfCnpj OR GEP.NOMERAZAO LIKE :nomeRazao OR TO_CHAR(GEP.SEQPESSOA) = :seqPessoa) ORDER BY GEP.NOMERAZAO";

    
	@Inject
	private Logger logger;	

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Pessoas");
	}

	private static final long serialVersionUID = 1L;

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity create(GePessoaEntity gePessoaEntity) throws DaoException {
		return persist(gePessoaEntity);
	}	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(BigDecimal seqforncedor) throws DaoException {
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(GePessoaEntity gePessoaEntity) throws DaoException {
		return null;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity buscaGePessoaPorFornecedor(BigDecimal seqFornecedor){
		List<GePessoaEntity> pessoas = findFromNamedQuery("find.SQL_BUSCA_GE_PESSOA_POR_FORNECEDOR",seqFornecedor);
		return pessoas.isEmpty() ? null : pessoas.get(0);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity buscaGePessoaPorFornecedorByID(BigDecimal seqFornecedor){
		List<GePessoaEntity> pessoas = findFromNamedQuery("find.ID",seqFornecedor);
		return pessoas.isEmpty() ? null : pessoas.get(0);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long size() {
		Query query = getEntityManager().createNativeQuery(_SQL_COUNT_ALL);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long size(String filtro) {
		if(filtro == null) filtro = "";
		
		Query query = getEntityManager().createNativeQuery(COUNT_PESSOAS_POR_CODIGO_RAZAO_CPFCNPJ);
		query.setParameter("cpfCnpj", filtro);
		query.setParameter("nomeRazao", "%" + filtro.toUpperCase() + "%");
		query.setParameter("seqPessoa", filtro);
		
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<GePessoaEntity> find(String filtro, long first, long count) {
		if(filtro == null) filtro = "";
		
		
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("cpfCnpj", filtro);
		parametros.put("nomeRazao", "%"+filtro.toUpperCase()+"%");
		parametros.put("seqPessoa", filtro);
		
		return findFromNativeQuery(BUSCA_PESSOAS_POR_CODIGO_RAZAO_CPFCNPJ, parametros, first, count);
	}
	
		
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<GePessoaEntity> find(long first, long count) {
		List<GePessoaEntity> gePessoaEntities = new ArrayList<GePessoaEntity>();
		gePessoaEntities =  findFromNamedQuery("find.SQL_ALL",first,count); 
		return gePessoaEntities; 
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Boolean isExisteCpnjCpf(BigDecimal cnpjCpf, BigDecimal digCnpjCpf) throws DaoException {
		Query query = getEntityManager().createNamedQuery("find.cnpjCpf", GePessoaEntity.class);
		query.setParameter("cnpjCpf",cnpjCpf);
		query.setParameter("digCnpjCpf",digCnpjCpf);
		return query.getResultList().size() > 0 ? Boolean.TRUE : Boolean.FALSE; 
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Pessoas");
	}

}
