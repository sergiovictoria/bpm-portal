package br.com.seta.processo.relatorios.implement;

import static br.com.seta.processo.mongoutils.Aggregations.filter;
import static br.com.seta.processo.mongoutils.Aggregations.gt;
import static br.com.seta.processo.mongoutils.Aggregations.ifNull;
import static br.com.seta.processo.mongoutils.Aggregations.match;
import static br.com.seta.processo.mongoutils.Aggregations.ne;
import static br.com.seta.processo.mongoutils.Aggregations.project;
import static br.com.seta.processo.mongoutils.Filters.and;
import static br.com.seta.processo.mongoutils.Filters.eq;
import static br.com.seta.processo.mongoutils.Filters.exists;
import static br.com.seta.processo.mongoutils.Filters.gte;
import static br.com.seta.processo.mongoutils.Filters.lte;
import static br.com.seta.processo.mongoutils.MongoObjects.dbArray;
import static br.com.seta.processo.mongoutils.MongoObjects.dbObj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import br.com.seta.processo.bean.CalendarioComercialSeta;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.AtividadeProcesso;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.mongoutils.Aggregations;
import br.com.seta.processo.relatorios.ProcessoEmAberto;
import br.com.seta.processo.relatorios.interfaces.RelatorioProcessosEmAberto;
import br.com.seta.processo.servicos.InstanciaProcessoDao;
import br.com.seta.processo.utils.MongoDbObjParser;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless
@Local(RelatorioProcessosEmAberto.class)
public class RelatorioProcessosEmAbertoImpl implements RelatorioProcessosEmAberto {
	
	private static final String ESTADO_DISPONIVEL = "Disponível";
	private static final String ESTADO_FINALIZADO = "Finalizado";
	
	private InstanciaProcessoDao dao;
	private DBCollection collection;
	private PeriodFormatter periodFormatter;
	private MongoDbObjParser mongoDBObjParser;
	
	@Inject
	private CalendarioComercialSeta calendarioComercial;
	
	@PostConstruct
	public void inicializa(){
		mongoDBObjParser = MongoDbObjParser.getInstance();
		MongoConnectionManager connection = MongoConnectionManager.getInstance();
		dao = new InstanciaProcessoDao(connection.getDatastore());
		collection = dao.getCollection();
		
		this.periodFormatter = new PeriodFormatterBuilder()	
			.printZeroAlways()
			.minimumPrintedDigits(2)
			.appendHours()
			.appendSeparator(":")
			.appendMinutes()
			.appendSeparator(":")
			.appendSeconds()
			.appendSuffix(" h")
			.toFormatter();
	}
	
	/**	 
	 * Executa uma busca baseando se nos parâmetros fornecidos
	 * 
	* Consulta construida pelo método 
	* <pre>
	* </code>
	* db.getCollection('InstanciaProcesso').aggregate([
	*{$match : {
	*    "$and": [{
	*    "fim": {
	*        "$exists": false
	*    }
	*}, {
	*    "inicio": {
	*        "$gte": &lt;Data Início&gt; <-- via parâmetro. Data de início
	*    }
	*}, {
	*    "inicio": {
	*        "$lte": &lt;Data Fim&gt; <-- via parâmetro. Data de fim        
	*    }
	*
	*        },  {
	*	"nomeProcesso": {
	*		"$eq":&lt;Processo&gt;    <-- via parâmetro. Processo
	*	}
	*	}]
	*    }
	*},
	*{
	*  $project :{
	*        nomeProcesso: 1,
	*        inicio: 1,
	*        atividades : {
	*           $filter: {
	*                input: "$atividades",
	*                as: "atividade",
	*                cond: {$and : [ 
	*                    {$ne : ["$$atividade.id", -1] },
	*                    {$eq : ["$$atividade.executadoPor.id", &lt;usuario&gt;},  <-- via parâmetro. Usuário
	*                    {$eq : ["$$atividade.ator", &lt;ator&gt;]},   <-- via parâmetro. Ator/Grupo
	*                    {$eq : [ {$ifNull : ["$$atividade.fim", 0]}, 0]}  <-- via parâmetro. Atividades Abertas
	*                    {$gt : [ {$ifNull : ["$$atividade.fim", 0]}, 0]}  <-- via parâmetro. Atividades Finalizadas
	*                ]}
	*            } 
	*        }
	*        
	*  }  
	*}
	*
	*]
	*)
	*</code>
	*</pre>
	*/
	@Override
	public List<ProcessoEmAberto> busca(Long caseId, String processo, String estadoAtividade, String ator, Long idUsuario, Date inicio, Date fim) {
		//Condições $match
		BasicDBList condicoesAnd = dbArray();
		condicoesAnd.add(exists("fim", false));
		if(inicio != null) 
			condicoesAnd.add(gte("inicio", inicio));
		
		if(fim != null)	
			condicoesAnd.add(lte("inicio", fim));
		
		if(caseId != null && !caseId.equals(0L)) 
			condicoesAnd.add(eq("_id", caseId));
		
		if(processo != null){
			condicoesAnd.add(eq("nomeProcesso", processo));
		}		
		
		//$match
		BasicDBObject match = match(and(condicoesAnd));
		
		//Condições $filter
		BasicDBList condicoesFilter = new BasicDBList();
		condicoesFilter.add(ne("$$atividade.id", -1));		
		
		if(idUsuario != null)
			condicoesFilter.add(Aggregations.eq("$$atividade.executadoPor.id", idUsuario));
		
		if(ator != null)
			condicoesFilter.add(Aggregations.eq("$$atividade.ator", ator));
		
		if(estadoAtividade != null){
			if(estadoAtividade.equals(ESTADO_DISPONIVEL)){
				condicoesFilter
						.add(Aggregations.eq(
								ifNull("$$atividade.fim", 0), 
								0));
			}
			
			if(estadoAtividade.equals(ESTADO_FINALIZADO)){
				condicoesFilter
					.add(gt(
							ifNull("$$atividade.fim", 0), 
							0));
			}
		}		
		
		//$project
		BasicDBObject project = project(dbObj("nomeProcesso", 1)
											.append("inicio", 1)
											.append("atividades", filter("$atividades", "atividade", and(condicoesFilter))) ); 
		
		//Agregação
		List<DBObject> pipeline = new ArrayList<DBObject>();
		pipeline.add(match);
		pipeline.add(project);
		AggregationOutput aggregationOutput = collection.aggregate(pipeline);		
		
		return extraiProcessosEmAberto(aggregationOutput);
	}
	
	private List<ProcessoEmAberto> extraiProcessosEmAberto(AggregationOutput aggregationOutput) {
		List<InstanciaProcesso> instancias = mongoDBObjParser.toList(InstanciaProcesso.class, aggregationOutput.results());
		 List<ProcessoEmAberto> resultados = deInstanciasProcessoParaProcessosEmAberto(instancias);
		return resultados;
	}	
	
	private List<ProcessoEmAberto> deInstanciasProcessoParaProcessosEmAberto(List<InstanciaProcesso> instancias){
		List<ProcessoEmAberto> processos = new ArrayList<ProcessoEmAberto>();
		
		for(InstanciaProcesso instancia : instancias){
			processos.addAll(deInstanciaProcessoParaProcessosEmAberto(instancia));
		}
		
		return processos;
	}
	
	private List<ProcessoEmAberto> deInstanciaProcessoParaProcessosEmAberto(InstanciaProcesso instancia){
		List<ProcessoEmAberto> processos = new ArrayList<ProcessoEmAberto>();
		
		long caseId = instancia.getCaseId();
		String nomeProcesso = instancia.getNomeProcesso();
		Date inicioInstancia = instancia.getInicio();
		List<AtividadeProcesso> atividades = instancia.getAtividades();
		
		for(AtividadeProcesso atividade : atividades){
			long idAtividade = atividade.getId();
			
			if(idAtividade == -1) continue;
			
			String nomeAtividade = atividade.getNome();
			Date inicioAtividade = atividade.getInicio();
			Date fimAtividade = atividade.getFim();
			String estadoAtividade = fimAtividade == null ? ESTADO_DISPONIVEL : ESTADO_FINALIZADO;
			String duracao = calculaDuracao(atividade.getInicio(), atividade.getFim());
			String ator = atividade.getAtor();
			String executadaPor = recuperaNomeExecutor(atividade);
			ProcessoEmAberto processo = new ProcessoEmAberto(caseId, nomeProcesso, idAtividade, nomeAtividade, estadoAtividade, inicioInstancia, inicioAtividade, fimAtividade, duracao, ator, executadaPor);
			
			processos.add(processo);
		}
		
		return processos;
	}

	private String calculaDuracao(Date inicio, Date fim) {
		Period duracao = calendarioComercial.calculaDuracao(inicio, fim);
		return periodFormatter.print(duracao.normalizedStandard(PeriodType.time()));
	}

	private String recuperaNomeExecutor(AtividadeProcesso atividade) {
		Usuario usuario = atividade.getExecutadoPor();
		if(usuario == null) return null;
		
		return usuario.getNome() + " " + usuario.getSobrenome();
	}
	
}
