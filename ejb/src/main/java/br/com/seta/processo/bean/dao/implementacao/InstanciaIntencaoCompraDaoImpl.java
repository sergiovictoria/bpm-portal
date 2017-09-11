package br.com.seta.processo.bean.dao.implementacao;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import br.com.seta.processo.bean.dao.FiltroIntencaoCompra;
import br.com.seta.processo.bean.dao.interfaces.InstanciaIntecaoCompraDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.utils.MongoDbObjParser;

public class InstanciaIntencaoCompraDaoImpl extends BasicDAO<InstanciaProcesso, String> implements InstanciaIntecaoCompraDao, Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final MongoDbObjParser parser = MongoDbObjParser.getInstance();
	
	private static final String QUERY_BUSCA_INSTANCIA_FINALIZADAS = 
	"{" +
       // "nomeProcesso: {$in : ['Recebimento - Suprimentos', 'Processo de Suprimentos']}" +
       "fim: {$exists: true}" +        
    "}";
	
	private static final String PROJECTION_BUSCA_INSTANCIA_FINALIZADAS = 
	"{" +  
	   "inicio:1, nome:1, inicio:1, fim:1, status:1, nomeProcesso: 1 " +
	   ",atividades:{ $slice: -1} " +  
	   ",'atividades.variaveis' : 1" +
	"}";	
	
	public InstanciaIntencaoCompraDaoImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
		
	}

	@Override
	public List<InstanciaProcesso> buscaInstanciasEncerradas(FiltroIntencaoCompra filtro, Integer primeiroRegistro, Integer quantRegistros) {
		BasicDBObject query = criaQueryInstancia(filtro);
		BasicDBObject projection = (BasicDBObject) JSON.parse(PROJECTION_BUSCA_INSTANCIA_FINALIZADAS);		
		DBCursor resultados = getCollection().find(query, projection); 
		
		if(primeiroRegistro != null){
			resultados = resultados.skip(primeiroRegistro);
		}
		
		if(quantRegistros != null){
			resultados = resultados.limit(quantRegistros);
		}
		
		return parser.toList(resultados, InstanciaProcesso.class);
	}

	@Override
	public long quantidadeInstanciasEncerradas(	FiltroIntencaoCompra filtro) {
		BasicDBObject query = criaQueryInstancia(filtro);
		return getCollection().find(query).count();
	}
	
	private BasicDBObject criaQueryInstancia(FiltroIntencaoCompra filtro){
		BasicDBObject query = (BasicDBObject) JSON.parse(QUERY_BUSCA_INSTANCIA_FINALIZADAS);
		BasicDBList intervalo = new BasicDBList();
		
		if(filtro.getProcesso() != null){
			query.append("nomeProcesso", filtro.getProcesso());
		}
		
		if(filtro.getInicio() != null){
			intervalo.add(new BasicDBObject("fim", new BasicDBObject("$gte", filtro.getInicio())));
		}
		if(filtro.getFim() != null){
			intervalo.add(new BasicDBObject("fim", new BasicDBObject("$lte", filtro.getFim())));
		}
		
		if(!intervalo.isEmpty()){
			query.append("$and", intervalo);
		}
		
		if(filtro.getEmpresa() != null){
			query.append(dtoOrRequisicaoGet("nomeEmpresa"), contains(filtro.getEmpresa()));
		}
		
		if(filtro.getTipoDespesa() != null){
			query.append(dtoOrRequisicaoGet("tipoDespesa"), filtro.getTipoDespesa());
		}
		
		if(filtro.getFornecedor() != null){
			query.append(dtoOrRequisicaoGet("nomeFornecedor"), contains(filtro.getFornecedor()));
		}
		
		if(filtro.getStatus() != null){
			query.append("status", filtro.getStatus());
		}
		
		if(filtro.getCaseId() != null){
			query.append("_id", filtro.getCaseId());
		}
		
		if(filtro.getSolicitante() != null){
			query.append(dtoSolicitacaoIntencaoCompraGet("nomeSolicitante"), contains(filtro.getSolicitante()));
		}	
		
		return query;
	}
	
	private String dtoSolicitacaoIntencaoCompraGet(String variavel) {
		return "atividades.variaveis.dtoSolicitacaoIntencaoCompra.valor." + variavel;
	}
	
	private String dtoOrRequisicaoGet(String variavel) {
		return "atividades.variaveis.dtoOrRequisicao.valor." + variavel;
	}
	
	private BasicDBObject contains(String palavra) {
		BasicDBObject pattern = new BasicDBObject("$regex", palavra).append("$options", "i");
		return pattern;
	}

}
