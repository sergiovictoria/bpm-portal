package br.com.seta.processo.bean.dao.implementacao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.dao.BasicDAO;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

import br.com.seta.processo.bean.dao.interfaces.InstanciaCadastroFornecedorDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.utils.InstanciaProcessoHelper;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.*;

public class InstanciaCadastroFornecedorImpl extends BasicDAO<InstanciaProcesso, String> implements InstanciaCadastroFornecedorDao, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String NOTIFICAÇÃO_DA_REJEIÇÃO_PARA_SOLICITANTE = "Notificação da rejeição para solicitante";
	private static final String NOTIFICAÇÃO_DA_REJEIÇÃO_PARA_SOLICITANTE_1 = "Notificação de Rejeição";
	private static final String NOTIFICAR_SOLICITANTE = "Notificar Solicitante";
	private static final String CADASTRO_DE_FORNECEDORES = "Cadastro de Fornecedores";
	
	private static final String DTOFORMULARIOFORNECEDOR = "dtoFormularioFornecedor";
	private static final String DTOCADASTROFORNECEDOR = "dtoCadastroFornecedor";
	private static final String DTOFORMULARIOFORNECEDOR_VALOR = "variaveis." + DTOFORMULARIOFORNECEDOR + ".valor";
	private static final String ATIVIDADES_DTOFORMULARIOFORNECEDOR_VALOR = ATIVIDADES+"." + DTOFORMULARIOFORNECEDOR_VALOR;
	
	private static final String CASE_ID = "_id";
	private static final String COMPRADOR = "comprador";
	private static final String COD_C5 = "idFornecedorC5";
	private static final String NOME = "nome";
	private static final String RAZAO_SOCIAL = "razaoSocialFornedor";
	private static final String CPF = "cpfFisica";
	private static final String CNPJ = "cpnjJuridico";
	
	public InstanciaCadastroFornecedorImpl(){
		super(MongoConnectionManager.getInstance().getDatastore());
	}	

	@Override
	public List<InstanciaProcesso> buscaInstanciasFinalizadasDeCadastroFornecedor(
			Long caseId, String codConsinco, String razaoSocialOuNome,
			String cpfCnpj, String comprador, Date inicio, Date fim,
			String status, Integer primeiroRegistro, Integer quantRegistros) {
		
		/**
		 * db.getCollection('InstanciaProcesso').find({
			    nomeProcesso: "Cadastro de Fornecedores",
			    fim: {
			        $exists: true
			    },
			    atividades: {
			        $elemMatch: {
			            "variaveis.dtoFormularioFornecedor.valor.comprador" : "RENATA CARDOSO"
			           .....
			        }
			    }
			
			}, {
			    nome: 1,
			    inicio: 1,
			    fim: 1,
			    status: 1,
			    atividades : {$slice: -1},
			   "atividades.variaveis.dtoFormularioFornecedor": 1,
			    
			})
		 */
		//Query
		BasicDBObject query = 
				queryBuscaInstanciaCadastroFornecedor(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status);
		
		//Projeção
		BasicDBObject projecao = new BasicDBObject()
		.append(STATUS, 1)
		.append(INICIO, 1)
		.append(FIM, 1)
		.append(ATIVIDADES, new BasicDBObject("$slice", -1))
		.append(ATIVIDADES_DTOFORMULARIOFORNECEDOR_VALOR, 1);
		
		DBCursor resultados;
		if(quantRegistros != null && primeiroRegistro != null){
			resultados = getCollection().find(query, projecao).skip(primeiroRegistro).limit(quantRegistros);
		}else{
			resultados = getCollection().find(query, projecao);
		}
		
		return InstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(resultados);
	}
	
	@Override
	public int totalInstanciasFinalizadasDeCadastroFornecedor(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status) {
		
		BasicDBObject query = queryBuscaInstanciaCadastroFornecedor(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status);
		DBCursor resultados = getCollection().find(query);
		return resultados.count();
	}

	@Override
	public CadastroFornecedor buscaCadastroFornecedor(long caseId) {
		return (CadastroFornecedor) buscaValorVariavel(caseId, DTOCADASTROFORNECEDOR);
	}
	
	@Override
	public FormularioFornecedor buscaFormularioFornecedor(long caseId) {
		return (FormularioFornecedor) buscaValorVariavel(caseId, DTOFORMULARIOFORNECEDOR);
	}

	private BasicDBObject queryBuscaInstanciaCadastroFornecedor(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status) {

		// Filtro de atividades e variaveis utilizado na consulta
		BasicDBList atividadesDeNotificacao = new BasicDBList();
		atividadesDeNotificacao.add(NOTIFICAR_SOLICITANTE);
		atividadesDeNotificacao.add(NOTIFICAÇÃO_DA_REJEIÇÃO_PARA_SOLICITANTE);
		atividadesDeNotificacao.add(NOTIFICAÇÃO_DA_REJEIÇÃO_PARA_SOLICITANTE_1);

		BasicDBObject filtroDadosFornecedor = new BasicDBObject().append(
				"nome", new BasicDBObject("$in", atividadesDeNotificacao));

		BasicDBList $and = new BasicDBList();

		if (comprador != null) {
			filtroDadosFornecedor.append(formularioFornecedorGet(COMPRADOR), contains(comprador));
		}

		if (codConsinco != null) {
			filtroDadosFornecedor.append(formularioFornecedorGet(COD_C5),
					codConsinco);
		}

		if (razaoSocialOuNome != null) {
			BasicDBObject contemRazaoSocialOuNome = contains(razaoSocialOuNome);
			
			BasicDBList values = new BasicDBList();
			values.add(new BasicDBObject(formularioFornecedorGet(RAZAO_SOCIAL),
					contemRazaoSocialOuNome));
			values.add(new BasicDBObject(formularioFornecedorGet(NOME), contemRazaoSocialOuNome));

			$and.add(new BasicDBObject("$or", values));
		}

		if (cpfCnpj != null) {
			BasicDBList values = new BasicDBList();
			values.add(new BasicDBObject(formularioFornecedorGet(CPF), cpfCnpj));
			values.add(new BasicDBObject(formularioFornecedorGet(CNPJ), cpfCnpj));

			$and.add(new BasicDBObject("$or", values));
		}

		if ($and.size() > 0) {
			filtroDadosFornecedor.append("$and", $and);
		}

		// Consulta
		BasicDBObject $elemMatch = new BasicDBObject("$elemMatch",
				filtroDadosFornecedor);
		BasicDBObject query = new BasicDBObject()
				.append(NOME_PROCESSO, CADASTRO_DE_FORNECEDORES)
				.append(FIM, new BasicDBObject("$exists", true))
				.append(ATIVIDADES, $elemMatch);

		if (caseId != null) {
			query.append(CASE_ID, caseId);
		}
		if (status != null) {
			query.append(STATUS, status);
		}
		
		BasicDBList intervalo = new BasicDBList();
		if (inicio != null) {
			intervalo.add(new BasicDBObject(INICIO, new BasicDBObject("$gte", inicio)));			
		}
		if (fim != null) {
			intervalo.add(new BasicDBObject(INICIO, new BasicDBObject("$lte", fim)));
		}
		if(!intervalo.isEmpty()){
			query.append("$and", intervalo);
		}

		System.out.println(query);
		return query;
	}

	private BasicDBObject contains(String palavra) {
		BasicDBObject pattern = new BasicDBObject("$regex", palavra).append("$options", "i");
		return pattern;
	}

	private String formularioFornecedorGet(String nomeVariavel){
		return DTOFORMULARIOFORNECEDOR_VALOR + "." +nomeVariavel;
	}
	
	private Object buscaValorVariavel(long caseId, String nomeVariavel) {
		/**
		 * db.getCollection('InstanciaProcesso').find({
				nomeProcesso: "Cadastro de Fornecedores"
			}, {
				atividades: {
					$slice: -1
				},
				"atividades.variaveis.${nomeVariavel}.valor": 1
			})
		 */
		
		BasicDBObject query = new BasicDBObject(CASE_ID, caseId).append(NOME_PROCESSO, CADASTRO_DE_FORNECEDORES);
		BasicDBObject projecao = new BasicDBObject(ATIVIDADES, new BasicDBObject("$slice", -1)) //última atividade
			.append("atividades.variaveis." + nomeVariavel, 1);
	
		DBCursor resultados = getCollection().find(query, projecao);
		List<InstanciaProcesso> instancias = InstanciaProcessoHelper.getInstance().paraListaDeInstanciaProcesso(resultados);
		
		return getVariavel(instancias, nomeVariavel);
	}

	private Object getVariavel(List<InstanciaProcesso> instancias, String nomeVariavel){
		if(instancias.isEmpty() || instancias.get(0).getAtividades().isEmpty()) 
			return null;
		
		return instancias.get(0).getAtividades().get(0).getValorVariavel(nomeVariavel);
	}
	
}
