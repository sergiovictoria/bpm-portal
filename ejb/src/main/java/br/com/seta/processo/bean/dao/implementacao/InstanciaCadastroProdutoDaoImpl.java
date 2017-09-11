package br.com.seta.processo.bean.dao.implementacao;

import java.io.Serializable;
import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.bean.dao.interfaces.InstanciaCadastroProdutoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.utils.InstanciaProcessoHelper;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.*;


public class InstanciaCadastroProdutoDaoImpl extends BasicDAO<InstanciaProcesso, String> implements InstanciaCadastroProdutoDao, Serializable {
private static final long serialVersionUID = 1L;
	
	private static final String DTOFORMULARIOPRODUTO = "dtoFormularioProduto";
	private static final String DTOCADASTROPRODUTO = "dtoCadastroProduto";
	private static final String VARIAVEIS_DTOFORMULARIOPRODUTO_VALOR = "variaveis." + DTOFORMULARIOPRODUTO + ".valor";
	
	private static final String SEQ_FORNECEDOR = "seqfornecedor";
	private static final String COD_CONSINCO = "codProduto";
	private static final String DESC_PRODUTO = "descCompleta";
	private static final String EAN_UNIDADE = "eanUnidade";
	private static final String DESCRICAO_NCM = "descricaoNcm";
	private static final String CNM = "cnm";
	
	private static final String QUERY_BUSCA_INSTANCIA_FINALIZADAS = 
			"{ nomeProcesso:'Cadastro de Produto', " +
		   	  "fim:{$exists:true}}";
	private static final String PROJECTION_BUSCA_INSTANCIA_FINALIZADAS = 
		"{" +  
		   "inicio:1, nome:1, inicio:1, fim:1, status:1," +
		   "atividades:{ $slice: -1}, " +  //apenas dados da última atividade
		   "'atividades.variaveis.dtoFormularioProduto':1" +
		"}";
	
	private static final String QUERY_BUSCA_INSTANCIA_POR_CASEID = 
			"{_id: ?1, nomeProcesso: 'Cadastro de Produto'}";
	private static final String PROJECTION_ULTIMO_VALOR_VARIAVEL = 
		"{atividades: {$slice: -1}, 'atividades.variaveis.?1.valor': 1}";
	
	
	public InstanciaCadastroProdutoDaoImpl(){
		super(MongoConnectionManager.getInstance().getDatastore());
	}	

	@Override
	public List<InstanciaProcesso> buscaInstanciaFinalizadasDeCadstroProduto(FiltroCadastroProdutos filtro, Integer primeiroRegistro, Integer quantRegistros) {
		
		BasicDBObject query = 
				criaQueryBuscaInstanciaFinalizadas(filtro);
		BasicDBObject projection = (BasicDBObject) JSON.parse(PROJECTION_BUSCA_INSTANCIA_FINALIZADAS);
		
		DBCursor resultados = getCollection().find(query, projection); 
		if(primeiroRegistro != null && quantRegistros != null){ 
			resultados = resultados.skip(primeiroRegistro).limit(quantRegistros);
		}
		
		return paraListaDeInstanciaProcesso(resultados);
	}

	@Override
	public int totalInstanciasFinalizadasDeCadastroProduto(FiltroCadastroProdutos filtro) {
		
		BasicDBObject query = 
				criaQueryBuscaInstanciaFinalizadas(filtro);
		
		return getCollection().find(query).count();
	}

	@Override
	public CadastroProduto buscaCadastroProduto(long caseId) {
		return (CadastroProduto) buscaValorVariavel(caseId, DTOCADASTROPRODUTO);
	}

	@Override
	public FormularioProduto buscaFormularioProduto(long caseId) {
		return (FormularioProduto) buscaValorVariavel(caseId, DTOFORMULARIOPRODUTO);
	}

	/**
	 * Cria uma consulta para a busca de instâncias finalizadas do processo de Cadastro de Produto
	 * 
	 * db.getCollection('InstanciaProcesso').find({  
		   nomeProcesso:"Cadastro de Produto",
		   fim:{  
		      $exists:true
		   },
		   atividades:{  
		      $elemMatch:{  
		         "variaveis.dtoFormularioProduto.valor.seqfornecedor":"45705"
		      }
		   }
		},
		{  
		   inicio:1,
		   nome:1,
		   inicio:1,
		   fim:1,
		   status:1,
		   atividades:{  
		      $slice:-1
		   },
		   "atividades.variaveis.dtoFormularioProduto":1
		})
	 * 
	 * 
	 * @param caseId
	 * @param codFornecedor
	 * @param codProdutoC5
	 * @param descricaoProduto
	 * @param ncm
	 * @param status
	 * @param inicio
	 * @param fim
	 * @return
	 */
	private BasicDBObject criaQueryBuscaInstanciaFinalizadas(FiltroCadastroProdutos filtro){	
		BasicDBObject query = (BasicDBObject) JSON.parse(QUERY_BUSCA_INSTANCIA_FINALIZADAS);
		BasicDBObject filtrosDadosProduto = new BasicDBObject();
		BasicDBList intervalo = new BasicDBList();
		
		if(filtro.getCaseId() != null){
			query.append(ID, filtro.getCaseId());
		}
		
		if(filtro.getInicio() != null){
			intervalo.add(new BasicDBObject(INICIO, new BasicDBObject("$gte", filtro.getInicio())));
		}
		if(filtro.getFim() != null){
			intervalo.add(new BasicDBObject(INICIO, new BasicDBObject("$lte", filtro.getFim())));
		}
		
		if(!intervalo.isEmpty()){
			query.append("$and", intervalo);
		}
		
		if(filtro.getStatus() != null){
			query.append(STATUS, filtro.getStatus());
		}
		
		if(filtro.getCodFornecedor() != null){
			filtrosDadosProduto.append(formularioFornecedorGet(SEQ_FORNECEDOR), filtro.getCodFornecedor());
		}
		
		if(filtro.getEanUnidade() != null){
			filtrosDadosProduto.append(formularioFornecedorGet(EAN_UNIDADE), filtro.getEanUnidade());
		}
		
		if(filtro.getCodProdutoC5() != null){
			filtrosDadosProduto.append(formularioFornecedorGet(COD_CONSINCO), filtro.getCodProdutoC5());
		}
		
		if(filtro.getDescricaoProduto() != null){
			filtrosDadosProduto.append(formularioFornecedorGet(DESC_PRODUTO), contains(filtro.getDescricaoProduto()));
		}
				
		if(filtro.getNcm() != null){
			String ncm = formularioFornecedorGet(CNM);
			String descricaoNcm = formularioFornecedorGet(DESCRICAO_NCM);
			
			BasicDBList condicoesNcm = new BasicDBList();
			condicoesNcm.add(new BasicDBObject(ncm, contains(filtro.getNcm())));
			condicoesNcm.add(new BasicDBObject(descricaoNcm, contains(filtro.getNcm())));
			
			filtrosDadosProduto.append("$or", condicoesNcm);
		}
		
		query.append(ATIVIDADES, new BasicDBObject("$elemMatch", filtrosDadosProduto));
		
		return query;
	}
	
	private Object buscaValorVariavel(long caseId, String nomeVariavel) {
		String id = new Long(caseId).toString();
		BasicDBObject query = (BasicDBObject) JSON.parse(QUERY_BUSCA_INSTANCIA_POR_CASEID.replace("?1", id));
		BasicDBObject projection = (BasicDBObject) JSON.parse(PROJECTION_ULTIMO_VALOR_VARIAVEL.replace("?1", nomeVariavel));
	
		DBCursor resultados = getCollection().find(query, projection);
		List<InstanciaProcesso> instancias = paraListaDeInstanciaProcesso(resultados);
		
		return getVariavel(instancias, nomeVariavel);
	}
	
	private Object getVariavel(List<InstanciaProcesso> instancias, String nomeVariavel){
		if(instancias.isEmpty() || instancias.get(0).getAtividades().isEmpty()) 
			return null;
		
		return instancias.get(0).getAtividades().get(0).getValorVariavel(nomeVariavel);
	}

	private List<InstanciaProcesso> paraListaDeInstanciaProcesso(DBCursor resultados) {
		return InstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(resultados);
	}

	private String formularioFornecedorGet(String variavel) {
		return VARIAVEIS_DTOFORMULARIOPRODUTO_VALOR + "." + variavel;
	}
	
	private BasicDBObject contains(String palavra) {
		BasicDBObject pattern = new BasicDBObject("$regex", palavra).append("$options", "i");
		return pattern;
	}
	
}
