package br.com.seta.processo.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Remote;
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
import br.com.seta.processo.interfaces.PessoaLocal;
import br.com.seta.processo.interfaces.PessoaRemote;


@Stateless(name="PessoaRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(PessoaLocal.class)
@Remote(PessoaRemote.class)
@Interceptors({LogInterceptor.class})
public class PessoaRepository extends GenericJpaDaoConSinco<PessoaEntity> implements PessoaLocal, PessoaRemote {

	private static final long serialVersionUID = 1L;
	private static final String _PESSOA_RAZAO_SEQ     = "SELECT G.SEQPESSOA, G.FANTASIA, G.NROCGCCPF, G.DIGCGCCPF, G.VERSAO, G.NOMERAZAO FROM GE_PESSOA G, MAF_FORNECEDOR F WHERE G.SEQPESSOA = F.SEQFORNECEDOR AND G.STATUS = 'A' AND G.NOMERAZAO IS NOT NULL ORDER BY G.NOMERAZAO";
	

	@Inject
	private Logger logger;

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Pessoa");
	}
	
    
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Pessoa> getPessoaS( ) {
		Query query = getEntityManager().createNativeQuery(_PESSOA_RAZAO_SEQ);
		List<Pessoa> pessoas = (List <Pessoa>) mapPessoaS(query.getResultList());
		return pessoas;
	}

	
		
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Pessoa> getPessoasDetalhe( ) {
		Query query = getEntityManager().createNamedQuery("Pessoa.TodasAtivas");
		List<Pessoa> pessoas = (List<Pessoa>) copyObject(query.getResultList(),Pessoa.class);
		return pessoas;
	}
	
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa getPessoasDetalheComID(Pessoa pessoa) {
		Query query = getEntityManager().createNamedQuery("Pessoa.TodasAtivasID");
		query.setParameter("seqpessoa",pessoa.getSeqpessoa());
		List<PessoaEntity> pessoaEntities = (List<PessoaEntity>) query.getResultList();
		System.out.println(pessoaEntities.get(0).toString());
		return (Pessoa) (pessoaEntities.size() > 0 ? copyObject(new Pessoa() , pessoaEntities.get(0) ) : null);
	}
	

	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Pessoa getPessoasDetalheIdSeq(Pessoa pessoa) {
		Query query;
		if (pessoa.getStatus()==null) {
			query = getEntityManager().createNamedQuery("Pessoa.AtivasIdPessoa");
			query.setParameter("seqpessoa",pessoa.getSeqpessoa());
		}else {
			query = getEntityManager().createNamedQuery("Pessoa.AtivasIdPessoaStatus");
			query.setParameter("seqpessoa",pessoa.getSeqpessoa());
			query.setParameter("status",pessoa.getStatus());
		}
		List<PessoaEntity> pessoaEntities = (List<PessoaEntity>) query.getResultList();
		return (Pessoa) (pessoaEntities.size() > 0 ? copyObject(new Pessoa() , pessoaEntities.get(0) ) : null);
	}
	


}
