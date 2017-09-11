package br.com.seta.processo.bean.dao.implementacao;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import br.com.seta.processo.bean.dao.interfaces.ConsultaInstanciasProdutoFornecedor;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.FiltroConsultaFornecProd;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.utils.MongoDbObjParser;

public class InstanciasProdutoFornecedorImpl extends BasicDAO<InstanciaProcesso, String> implements ConsultaInstanciasProdutoFornecedor, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String PROJECAO = 
	"{" +  
	   "inicio:1, fim:1, status:1, nomeProcesso: 1 " +
	   ",atividades:{ $slice: -1} " +  
	   ",'atividades.variaveis' : 1" +
	"}";
	
	
	private static final String[] PROCESSOS = {"Cadastro de Produto", "Cadastro Fornecedores"};	
	private static final MongoDbObjParser parser = MongoDbObjParser.getInstance();
	
	protected InstanciasProdutoFornecedorImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
	}

	@Override
	public List<InstanciaProcesso> busca(FiltroConsultaFornecProd filtro, Integer primeiroRegistro, Integer quantRegistros) {
		BasicDBObject query = criaConsulta(filtro);
		BasicDBObject projecao = (BasicDBObject) JSON.parse(PROJECAO);	
		
		DBCursor resultados = getCollection().find(query, projecao); 
		
		if(primeiroRegistro != null){
			resultados = resultados.skip(primeiroRegistro);
		}
		
		if(quantRegistros != null){
			resultados = resultados.limit(quantRegistros);
		}	
		
		return parser.toList(resultados, InstanciaProcesso.class);
	}

	@Override
	public long quantidade(FiltroConsultaFornecProd filtro) {
		BasicDBObject query = criaConsulta(filtro);
		return getCollection().find(query).count();
	}

	private BasicDBObject criaConsulta(FiltroConsultaFornecProd filtro) {
		BasicDBObject query = new BasicDBObject();
		
		if(filtro.getOperacao() != null){
			query.append("nomeProcesso", filtro.getOperacao());
		}else{
			BasicDBList processos = new BasicDBList();
			processos.addAll(Arrays.asList(PROCESSOS));
			query.append("nomeProcesso", new BasicDBObject("$in", processos));			
		}
		
		if(filtro.getCaseId() != null){
			query.append("_id", filtro.getCaseId());			
		}
		
		BasicDBList intervalo = new BasicDBList();
		if(filtro.getInicio() != null){
			intervalo.add(new BasicDBObject("fim", new BasicDBObject("$gte", filtro.getInicio())));
		}
		if(filtro.getFim() != null){
			intervalo.add(new BasicDBObject("fim", new BasicDBObject("$lte", filtro.getFim())));
		}
		
		if(!intervalo.isEmpty()){
			query.append("$and", intervalo);
		}		
	
		return query;
	}	

}
