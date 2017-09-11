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
import br.com.seta.processo.dto.GePessoa;
import br.com.seta.processo.dto.MafFornecedor;
import br.com.seta.processo.dto.MapFamfornec;
import br.com.seta.processo.dto.ModeloDocumento;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.entity.MafFornecedorEntity;
import br.com.seta.processo.exceptions.DaoException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="MafFonecedores")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean

public class MafFonecedores extends GenericJpaDaoConSinco<MafFornecedorEntity> {


	@Inject
	private Logger logger;


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Fornecedores");
	}


	private static final long serialVersionUID = 1L;



	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MafFornecedorEntity create(MafFornecedorEntity mafFornecedorEntity) throws DaoException {
		return persist(mafFornecedorEntity);
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(BigDecimal seqforncedor) throws DaoException {
		return null;
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public GePessoaEntity getMafFornecedorEntity(MafFornecedorEntity mafFornecedorEntity) throws DaoException {
		return null;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<MafFornecedor> findAll(long first, long count, String nomerazao, BigDecimal seqfornecedor){
		String sql = "select f.seqfornecedor, g.seqpessoa, g.nomerazao, g.fantasia, g.cidade, g.uf"
						+ " from maf_fornecedor f inner join ge_pessoa g on g.seqpessoa = f.seqfornecedor"
						+ " where 1=1";
		
		if(nomerazao != null && !nomerazao.isEmpty())
			sql += " and g.nomerazao like :nomerazao";
		
		if(seqfornecedor != null)
			sql += " and f.seqfornecedor like :seqfornecedor";
		
		Query query = getEntityManager().createNativeQuery(sql).setFirstResult((int)first).setMaxResults((int)count);
		
		if(nomerazao != null && !nomerazao.isEmpty())
			query.setParameter("nomerazao", "%" + nomerazao.toUpperCase() + "%");
		
		if(seqfornecedor != null)
			query.setParameter("seqfornecedor", "%" + seqfornecedor + "%");
		
		List<Object[]> list = query.getResultList();
		
		List<MafFornecedor> fornecedores = new ArrayList<MafFornecedor>();
		
		if(list != null) {
			for(Object[] obj : list){
				MafFornecedor fornecedor = new MafFornecedor();
				MapFamfornec mapFamfornec = new MapFamfornec();
				GePessoa pessoa = new GePessoa();
				
				try { fornecedor.setSeqfornecedor((BigDecimal)obj[0]); } catch(Exception e){};
				try { pessoa.setSeqpessoa((BigDecimal)obj[1]); } catch(Exception e){};
				try { pessoa.setNomerazao((String)obj[2]); } catch(Exception e){};
				try { pessoa.setFantasia((String)obj[3]); } catch(Exception e){};
				try { pessoa.setCidade((String)obj[4]); } catch(Exception e){};
				try { pessoa.setUf((String)obj[5]); } catch(Exception e){};
				
				fornecedor.setGePessoa(pessoa);
				fornecedor.setMapFamfornec(mapFamfornec);
				fornecedores.add(fornecedor);
			}
		}
		
		return fornecedores;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public MafFornecedor find(BigDecimal seqfornecedor){
		String sql = "select f.seqfornecedor, g.seqpessoa, g.nomerazao, g.fantasia, g.cidade, g.uf"
						+ " from maf_fornecedor f inner join ge_pessoa g on g.seqpessoa = f.seqfornecedor"
						+ " where f.seqfornecedor = :seqfornecedor";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		query.setParameter("seqfornecedor", seqfornecedor);
		
		List<Object[]> list = query.getResultList();
		
		MafFornecedor fornecedor = null;
		
		if(list != null) {
			for(Object[] obj : list){
				fornecedor = new MafFornecedor();
				MapFamfornec mapFamfornec = new MapFamfornec();
				GePessoa pessoa = new GePessoa();
				
				try { fornecedor.setSeqfornecedor((BigDecimal)obj[0]); } catch(Exception e){};
				try { pessoa.setSeqpessoa((BigDecimal)obj[1]); } catch(Exception e){};
				try { pessoa.setNomerazao((String)obj[2]); } catch(Exception e){};
				try { pessoa.setFantasia((String)obj[3]); } catch(Exception e){};
				try { pessoa.setCidade((String)obj[4]); } catch(Exception e){};
				try { pessoa.setUf((String)obj[5]); } catch(Exception e){};
				
				fornecedor.setGePessoa(pessoa);
				fornecedor.setMapFamfornec(mapFamfornec);
			}
		}
		
		return fornecedor;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long size(String nomerazao, BigDecimal seqfornecedor){
		String sql = "select count(*)"
				+ " from maf_fornecedor f inner join ge_pessoa g on g.seqpessoa = f.seqfornecedor"
				+ " where 1=1";
		
		if(nomerazao != null && !nomerazao.isEmpty())
			sql += " and g.nomerazao like :nomerazao";
		
		if(seqfornecedor != null)
			sql += " and f.seqfornecedor like :seqfornecedor";

		Query query = getEntityManager().createNativeQuery(sql);
		
		if(nomerazao != null && !nomerazao.isEmpty())
			query.setParameter("nomerazao", "%" + nomerazao.toUpperCase() + "%");
		
		if(seqfornecedor != null)
			query.setParameter("seqfornecedor", "%" + seqfornecedor + "%");
		
		return ((BigDecimal)query.getSingleResult()).longValue();
	}



	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Fornecedores");
	}


}
