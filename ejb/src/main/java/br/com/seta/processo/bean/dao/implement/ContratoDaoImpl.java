package br.com.seta.processo.bean.dao.implement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import br.com.seta.processo.bean.dao.interfaces.ContratoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="ContratoDao")
@Local(ContratoDao.class)
@Interceptors({LogInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)

public class ContratoDaoImpl extends BasicDAO<Contrato, String> implements ContratoDao, Serializable {
	private static final long serialVersionUID = 1L;

	public ContratoDaoImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
	}

	@Override
	public Contrato buscaContratoVigente(long codFornecedor) {
		Query<Contrato> query = createQuery()
				.field("codigoFornecedor").equal(codFornecedor)
				.field("dataFimContrato").greaterThanOrEq(new Date())
				.field("statusContrato").equal(4);		
		List<Contrato> results = query.asList();
		return results.isEmpty() ? null : results.get(0);
	}

	@Override
	public List<Contrato> buscaContratosVencidos(long codFornecedor) {
		Query<Contrato> query = createQuery()
				.field("codigoFornecedor").equal(codFornecedor)
				.field("dataFimContrato").lessThanOrEq(new Date());		
		List<Contrato> results = query.asList();
		return results;
	}

}
