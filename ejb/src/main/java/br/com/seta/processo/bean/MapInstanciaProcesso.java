/**
 * 
 */
package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.mongodb.morphia.dao.BasicDAO;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.interceptor.LogInterceptor;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

/**
 * @author Sérgio da Victória
 *
 *   
 */

@Stateless(name="MapInstanciaProcesso")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class MapInstanciaProcesso extends BasicDAO<InstanciaProcesso, String> {

	private static final String _FIND_REQUSICAO_RECEBIMENTO    = "{$and:[{\"status\" : \"Aprovado\" },{\"nomeProcesso\" : \"Processo de Suprimentos\" }, { fim: { $exists: true } }, { recebimento: { $exists: false } } ]}";
	private static final String _FIND_REQUSICAO_RECEBIMENTO_GRUPO = "{$and:[{\"status\" : \"Aprovado\" },{\"nomeProcesso\" : \"Processo de Suprimentos\" }, {\"atividades.variaveis.dtoOrRequisicao.valor.grupoRecebimento\" : \"[grupoRecebimento]\" }, { fim: { $exists: true } }, { recebimento: { $exists: false } } ]}";
	private static final String _FIND_REQUSICAO_RECEBIMENTO_ID = "{$and:[{\"status\" : \"Aprovado\" },{\"nomeProcesso\" : \"Processo de Suprimentos\" }, {\"atividades.variaveis.dtoOrRequisicao.valor.numeroIntencaoSolicitacaoCompra\" : [seqrequisicao] } , { fim: { $exists: true } }, { recebimento: { $exists: false } } ]}";
	private static final String _FIND_REQUSICAO_SLICE          = "{atividades: {$slice: -1}, \"atividades.variaveis.dtoOrRequisicao.valor\" : 1 }}";
	private static final String _FIND_REQUSICAO_SEARCH_UPDATE  = "{\"atividades.variaveis.dtoOrRequisicao.valor.seqrequisicao\" : [seqrequisicao] }";
	private static final String _FIND_REQUSICAO_EXECUTE_UPDATE = "{ $set: {recebimento: \"Recebendo\"}}";
	private static final String _FIND_REQUSICAO_SOLICITACAO_ID    = "{$and:[{\"status\" : \"Aprovado\" },{\"nomeProcesso\" : \"Processo de Suprimentos\" }, {\"atividades.variaveis.dtoSolicitacaoIntencaoCompra.valor.numeroIntencaoSolicitacaoCompra\" : [taskid] } , { fim: { $exists: true } }  ]}";
	private static final String _FIND_REQUSICAO_SOLICITACAO_SLICE = "{atividades: {$slice: -1}, \"atividades.variaveis.dtoSolicitacaoIntencaoCompra.valor\" : 1 }}";

	public MapInstanciaProcesso() {
		super(InstanciaProcesso.class,MongoConnectionManager.getInstance().getDatastore());
	}

	public List<InstanciaProcesso> listaRequisicoes() {
		BasicDBObject query = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_RECEBIMENTO);
		BasicDBObject slice = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_SLICE);
		DBCursor cursor =  getCollection().find(query,slice);
		return MapInstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(cursor);
	}
	
	
	public List<InstanciaProcesso> listaRequisicoesPorGrupo(String grupoRecebimento) {
		String queryString = _FIND_REQUSICAO_RECEBIMENTO_GRUPO.replace("[grupoRecebimento]", grupoRecebimento);
		BasicDBObject query = (BasicDBObject) JSON.parse(queryString);
		BasicDBObject slice = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_SLICE);
		DBCursor cursor =  getCollection().find(query,slice);
		return MapInstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(cursor);
	}
	
	public List<OrRequisicao> listaRequisicoesPorGrupoS(String grupoRecebimento) {
		List<OrRequisicao> orRequisicaos = new ArrayList<OrRequisicao>();
		List<InstanciaProcesso> instanciaProcessos = new ArrayList<InstanciaProcesso>(listaRequisicoesPorGrupo(grupoRecebimento));
		for (InstanciaProcesso dto : instanciaProcessos) {
			orRequisicaos.add((OrRequisicao) dto.getAtividades().get(0).getValorVariavel("dtoOrRequisicao"));
		}
		return orRequisicaos;
	}
	
	public void update(Long seqrequisicao) {
		String queryString = _FIND_REQUSICAO_SEARCH_UPDATE.replace("[seqrequisicao]", seqrequisicao.toString());
		BasicDBObject querySearch = (BasicDBObject) JSON.parse(queryString);
		BasicDBObject queryUpdate = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_EXECUTE_UPDATE);
		getCollection().update(querySearch,queryUpdate);
	}

	public InstanciaProcesso listaRequisicoes(Long seqrequisicao) {
		String queryString = _FIND_REQUSICAO_RECEBIMENTO_ID.replace("[seqrequisicao]", seqrequisicao.toString());
		BasicDBObject query = (BasicDBObject) JSON.parse(queryString);
		BasicDBObject slice = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_SLICE);
		DBCursor cursor =  getCollection().find(query,slice);
		List<InstanciaProcesso> instanciaProcessos = MapInstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(cursor);
		return instanciaProcessos.size() > 0 ? instanciaProcessos.get(0) : null;
	}
	
	public InstanciaProcesso listaSolicitacaoIntencaoCompra(Long taskId) {
		String queryString = _FIND_REQUSICAO_SOLICITACAO_ID.replace("[taskid]", taskId.toString());
		BasicDBObject query = (BasicDBObject) JSON.parse(queryString);
		BasicDBObject slice = (BasicDBObject) JSON.parse(_FIND_REQUSICAO_SOLICITACAO_SLICE);
		DBCursor cursor =  getCollection().find(query,slice);
		List<InstanciaProcesso> instanciaProcessos = MapInstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(cursor);
		return instanciaProcessos.size() > 0 ? instanciaProcessos.get(0) : null;
	}
	
	public SolicitacaoIntencaoCompra listaSolicitacaoIntencaoCompraOne(Long taskId) {
		InstanciaProcesso instancia = listaSolicitacaoIntencaoCompra(taskId);
		return (SolicitacaoIntencaoCompra) instancia.getAtividades().get(0).getValorVariavel("dtoSolicitacaoIntencaoCompra");
	}
	
	public OrRequisicao listaOrRequisicaoRecebimentoOne(Long seqrequisicao) {
		InstanciaProcesso instancia =listaRequisicoes(seqrequisicao);
		return (OrRequisicao) instancia.getAtividades().get(0).getValorVariavel("dtoOrRequisicao");
	}
	

	public List<OrRequisicao> listaOrRequisicaoRecebimento() {
		List<OrRequisicao> orRequisicaos = new ArrayList<OrRequisicao>();
		List<InstanciaProcesso> instanciaProcessos = new ArrayList<InstanciaProcesso>(listaRequisicoes());
		for (InstanciaProcesso dto : instanciaProcessos) {
			orRequisicaos.add((OrRequisicao) dto.getAtividades().get(0).getValorVariavel("dtoOrRequisicao"));
		}
		return orRequisicaos;
	}

	public static void main(String[] args) {
		MapInstanciaProcesso mapInstanciaProcessoDao = new MapInstanciaProcesso();
		InstanciaProcesso instanciaProcessos = mapInstanciaProcessoDao.listaRequisicoes(new Long(0));
		System.out.println(instanciaProcessos);
//		for (InstanciaProcesso instanciaProcesso : instanciaProcessos) {
//			OrRequisicao orRequisicao = (OrRequisicao) instanciaProcesso.getAtividades().get(0).getValorVariavel("dtoOrRequisicao");
//			System.out.println("***************** "+orRequisicao);
//		}
	}

}
