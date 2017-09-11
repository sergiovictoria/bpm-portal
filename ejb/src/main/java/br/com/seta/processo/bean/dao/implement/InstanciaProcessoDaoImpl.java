package br.com.seta.processo.bean.dao.implement;

import static br.com.seta.processo.dto.InstanciaProcesso.Campos.FIM;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import br.com.seta.processo.bean.dao.interfaces.InstanciaProcessoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.interceptor.LogInterceptor;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Stateless(name="InstanciaProcessoDao")
@Local(InstanciaProcessoDao.class)
@Interceptors({LogInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InstanciaProcessoDaoImpl extends BasicDAO<InstanciaProcesso, String> implements InstanciaProcessoDao{	
	
	public InstanciaProcessoDaoImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
	}	
	
	@Override
	public long quantidadeInstanciasEncerradas(Date inicio, Date fim, String textSearch){
		Query<InstanciaProcesso> query = criaQueryBuscaInstanciasEncerradas(null, null, inicio, fim, textSearch);
		return query.countAll();
	}
	
	@Override
	public List<InstanciaProcesso> buscaInstanciasEncerradas(Integer primeiroRegistro, Integer quantRegistros, Date inicio, Date fim, String textSearch) {
		Query<InstanciaProcesso> query = criaQueryBuscaInstanciasEncerradas(primeiroRegistro, quantRegistros, inicio, fim, textSearch);
		return query.asList();
	}
	
	private Query<InstanciaProcesso> criaQueryBuscaInstanciasEncerradas(Integer primeiroRegistro, Integer quantRegistros, Date inicio, Date fim, String textSearch) {
		Query<InstanciaProcesso> query = this.createQuery().field(FIM).exists().retrievedFields(false, "atividades");
		
		if(primeiroRegistro != null){
			query.offset(primeiroRegistro);
		}
		
		if(quantRegistros != null){
			query.limit(quantRegistros);
		}
		
		if(inicio != null){
			query.field(FIM).greaterThanOrEq(inicio);
		}
		
		if(fim != null){
			query.field(FIM).lessThanOrEq(fim);
		}
		
		if(textSearch != null && !textSearch.trim().isEmpty()){
			query.search(textSearch);
		}
		
		return query;
	}

}
