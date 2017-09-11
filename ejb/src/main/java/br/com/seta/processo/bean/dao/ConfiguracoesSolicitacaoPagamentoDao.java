package br.com.seta.processo.bean.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.ConfiguracaoSolicitacaoPagamento;

@Stateless(name="ConfiguracoesSolicitacaoPagamentoDao")
@LocalBean
public class ConfiguracoesSolicitacaoPagamentoDao extends BasicDAO<ConfiguracaoSolicitacaoPagamento, ObjectId> {
	
	public ConfiguracoesSolicitacaoPagamentoDao() {
		super(ConfiguracaoSolicitacaoPagamento.class, MongoConnectionManager.getInstance().getDatastore());		
	}
	
	public ConfiguracaoSolicitacaoPagamento buscaConfiguracaoSolicitacaoPagamento(){
		List<ConfiguracaoSolicitacaoPagamento> results = find().asList();
		
		if(results.size() == 0) return null;
		
		return results.get(0);
	}
	
}
