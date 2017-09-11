package br.com.seta.processo.bean.dao.implementacao;

import static br.com.seta.processo.mongoutils.Filters.and;
import static br.com.seta.processo.mongoutils.Filters.gte;
import static br.com.seta.processo.mongoutils.Filters.lte;
import static br.com.seta.processo.mongoutils.Filters.where;
import static br.com.seta.processo.mongoutils.MongoObjects.dbArray;
import static br.com.seta.processo.mongoutils.MongoObjects.dbObj;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;

import br.com.seta.processo.bean.dao.FiltroSolicitacaoPagamento;
import br.com.seta.processo.bean.dao.interfaces.InstanciaSolicitacaoPagamentoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.mongoutils.Filters;
import br.com.seta.processo.utils.MongoDbObjParser;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

public class InstanciaSolicitacaoPagamentoDaoImpl extends BasicDAO<InstanciaProcesso, String> implements InstanciaSolicitacaoPagamentoDao, Serializable {
	private static final long serialVersionUID = 1L;	
	
	private static final String PROJECTION_BUSCA_INSTANCIA_FINALIZADAS = 
			"{" +  
			   "inicio:1, nome:1, inicio:1, fim:1, status:1, nomeProcesso: 1 " +
			   ",atividades:{ $slice: -1} " +  
			   ",'atividades.variaveis' : 1" +
			"}";
	
	protected InstanciaSolicitacaoPagamentoDaoImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
	}

	@Override
	public List<InstanciaProcesso> buscaInstancias(FiltroSolicitacaoPagamento filtro, Integer primeiro, Integer quantidade) {
		
		BasicDBObject projection = (BasicDBObject) JSON.parse(PROJECTION_BUSCA_INSTANCIA_FINALIZADAS);
		BasicDBObject query = buscaPor(filtro);	
		
		DBCursor cursor = getCollection().find(query, projection);
		
		if(primeiro != null)
			cursor.skip(primeiro);
		
		if(quantidade != null)
			cursor.limit(quantidade);
		
		return MongoDbObjParser.getInstance().toList(cursor, InstanciaProcesso.class);
	}	

	private BasicDBObject buscaPor(FiltroSolicitacaoPagamento filtro) {
		BasicDBList condicoesAnd = dbArray();
		condicoesAnd.add(dbObj("nomeProcesso", "Solicitação de Pagamento"));
		
		BasicDBList condicoesDtSolicitacao = dbArray();
		if(filtro.getInicioDataSolicitacao()!= null)
			condicoesDtSolicitacao.add(dbObj( "inicio", gte(filtro.getInicioDataSolicitacao())) );
		
		if(filtro.getFimDataSolicitacao() != null)			
			condicoesDtSolicitacao.add(dbObj("inicio", lte(filtro.getFimDataSolicitacao())) );		
		
		if(!condicoesDtSolicitacao.isEmpty())
			condicoesAnd.add(and(condicoesDtSolicitacao));	
				
		if(filtro.getStatus() != null){
			switch (filtro.getStatus()) {
			case "Aprovado":
				condicoesAnd.add(dbObj("status", "Aprovado"));
				break;

			case "Rejeitado":
				condicoesAnd.add(dbObj("status", "Rejeitado"));
				break;

			case "Em Análise":
				condicoesAnd.add(Filters.exists("status", false));
				break;
			}		
		}
		
		String condicoesWhere = "";
		String solicitacaoPagamento = "this.atividades[(this.atividades.length - 1)].variaveis.solicitacaoPagamento.valor";
		if(filtro.getCodFornecedor() != null)			
			condicoesWhere += solicitacaoPagamento + ".codFornecedor == " + filtro.getCodFornecedor() + " &&";
		
		if(filtro.getNroNota() != null)
			condicoesWhere += solicitacaoPagamento + ".nroNota == " + filtro.getNroNota() + " &&";		
		
		if(filtro.getValorDe() != null)
			condicoesWhere += solicitacaoPagamento + ".valor >= " + filtro.getValorDe() + " &&";		
		
		if(filtro.getValorAte() != null)
			condicoesWhere += solicitacaoPagamento + ".valor <= " + filtro.getValorAte() + " &&";
		
		if(condicoesWhere.length() > 0){
			condicoesWhere = condicoesWhere.substring(0, condicoesWhere.length()- 2);
			condicoesAnd.add(where(condicoesWhere));
		}
	
		return and(condicoesAnd);
	}

	@Override
	public long quantidadeTotalInstancias(FiltroSolicitacaoPagamento filtro) {
		BasicDBObject query = buscaPor(filtro);
		return getCollection().count(query);
	}	

}
