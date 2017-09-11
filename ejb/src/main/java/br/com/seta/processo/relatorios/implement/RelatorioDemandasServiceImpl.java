package br.com.seta.processo.relatorios.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;

import org.mongodb.morphia.Datastore;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.relatorios.interfaces.RelatorioDemandasService;
import br.com.seta.processo.servicos.InstanciaProcessoDao;
import br.com.seta.processo.utils.DateUtils;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.NOME_PROCESSO;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.STATUS;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.FIM;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.INICIO;

/**
 * Classe que fornece os dados para os Dashboards de Demanda
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Stateless
@Local(RelatorioDemandasService.class)
public class RelatorioDemandasServiceImpl implements RelatorioDemandasService {
	
	private InstanciaProcessoDao dao;
	private DBCollection instanciaCollection;
	
	@PostConstruct
	public void inicializa(){
		Datastore ds = MongoConnectionManager.getInstance().getDatastore();
		this.dao = new InstanciaProcessoDao(ds);
		this.instanciaCollection = dao.getCollection();
	}
	
	/**
	 * Retorna os dados para o Dashboard de Quantidade de Cadastros: <br>
	 * <b>Consulta no MongoDB:</b> <br>
	 * db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *	{$group : {_id : "$nomeProcesso", quantidade: {$sum : 1} }} <br>
	 *	]) <br>
	 * 
	 * @param inicio
	 * @param fim
	 * @return
	 */
	@Override
	public List<Object[]> quantidadeDeCadastros(Date inicio, Date fim){
		//Group
		DBObject group = new BasicDBObject("$group", new BasicDBObject("_id", "$"+NOME_PROCESSO ).append("quantidade", new BasicDBObject("$sum", 1)));
		
		//Condições - Match
		BasicDBList condicoes = new BasicDBList();
		condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$exists", true)));
		if(inicio != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$lte", fim)));
		}
		BasicDBObject $and = new BasicDBObject("$and", condicoes);
		DBObject match = new BasicDBObject().append("$match", $and );
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add(match);
		operacoes.add(group);
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		
		return paraFormatoRelatorioQuantProcessos(output);
	}

	/**
	 * 
	 * Retorna os dados para o Dashboard de Demanda Mensal<br>
	 * 
	 * <b>Consulta no MongoDB</b>:<br>
	 * db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *   {$match: {fim : {$exists : true}}}, <br>
	 *   {$group : {_id :{processo : "$nomeProcesso", mes : {$month : "$fim"} , ano : {$year : "$fim"}}, quantidade: {$sum : 1}} }, <br>
	 *   {$sort: {"_id.mes": 1, "_id.ano": 1} } <br>
	 *	]) <br>
	 * 
	 */
	@Override
	public List<Object[]> demandaMensal(Date inicio, Date fim){
		//$group
		String $fim = "$"+FIM;
		DBObject $month = new BasicDBObject("$month", $fim);
		DBObject $year = new BasicDBObject("$year", $fim);
		DBObject _id = new BasicDBObject("processo", "$"+NOME_PROCESSO).append("mes", $month).append("ano", $year);
		DBObject $group = new BasicDBObject("$group", new BasicDBObject("_id", _id ).append("quantidade", new BasicDBObject("$sum", 1)));
		
		//$match
		BasicDBList condicoes = new BasicDBList();		
		condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$exists", true)));
		if(inicio != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$lte", fim)));
		}
		BasicDBObject $and = new BasicDBObject("$and", condicoes );
		DBObject $match = new BasicDBObject().append("$match", $and );
		
		//$sort
		BasicDBObject $sort = new BasicDBObject("$sort", new BasicDBObject("_id.ano", 1).append("_id.mes", 1).append("_id.processo", 1));
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add($match);
		operacoes.add($group);
		operacoes.add($sort);
		
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		return paraFormatoDoRelatorioDemandas(output);
	}

	/**
	 * Retorna os dados para o Dashboard de Demanda Semanal<br>
	 *  <b>Consulta no MongoDB</b>:<br>
	 *  
	 * db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *	{$match : {nomeProcesso: #nome_do_processo#} }, <br>
	 *	{$group : {_id :{diaDaSemana: {$dayOfWeek: "$inicio"} ,mes : {$month : "$inicio"} , ano : {$year : "$inicio"}}, quantidade: {$sum : 1}} }, <br<
	 *  {$sort : { "_id.ano": 1, "_id.mes": 1 , "_id.diaDaSemana" : 1}} <br>
	 *	]) <br>
	 * 
	 * @param processo
	 * @param inicio
	 * @param fim
	 */
	@Override
	public List<Object[]> demandaSemanal(String processo, Date inicio, Date fim){
		//$match
		BasicDBList condicoes = new BasicDBList();
		if(inicio != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$lte", fim)));
		}
		condicoes.add(new BasicDBObject(NOME_PROCESSO, processo));
		BasicDBObject $and = new BasicDBObject("$and", condicoes );
		DBObject $match = new BasicDBObject().append("$match", $and );
		
		//$group
		String $inicio = "$"+INICIO;
		DBObject $dayOfWeek = new BasicDBObject("$dayOfWeek", $inicio);
		DBObject $month = new BasicDBObject("$month", $inicio);
		DBObject $year = new BasicDBObject("$year", $inicio);
		DBObject _id = new BasicDBObject("diaDaSemana", $dayOfWeek).append("mes", $month).append("ano", $year);
		DBObject $group = new BasicDBObject("$group", new BasicDBObject("_id", _id).append("quantidade", new BasicDBObject("$sum", 1)));
		
		//$sort
		DBObject $sort = new BasicDBObject("$sort", new BasicDBObject("_id.ano", 1).append("_id.mes", 1).append("_id.diaDaSemana", 1)); 
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add($match);
		operacoes.add($group);
		operacoes.add($sort);
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		
		return paraFormatoDoRelatorioDemandaSemanal(output);
	}
	
	/**
	 * Retorna os dados para o Dashboard Status do Processo <br>
	 * <b>Consulta no MongoDB:</b> <br>
	 * 
	 * db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *	{$project:{status: {$ifNull:["$status", "Em Aberto"]}}}, <br>
	 *	{$group : {_id : "$status", quantidade: {$sum : 1} }} <br>
	 *]) <br>
	 * 
	 * @param inicio
	 * @param fim
	 */
	@Override
	public List<Object[]> statusDosProcessos(Date inicio, Date fim){
		String $status = "$"+STATUS;
		//$match
		BasicDBList condicoes = new BasicDBList();
		condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$exists", true)));
		if(inicio != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$lte", fim)));
		}
		BasicDBObject $and = new BasicDBObject("$and", condicoes);
		DBObject $match = new BasicDBObject().append("$match", $and );
		
		//$project
		BasicDBList valoresParaIfNull = new BasicDBList();
		valoresParaIfNull.add($status);
		valoresParaIfNull.add("Em Aberto");
		BasicDBObject $ifNull = new BasicDBObject("$ifNull", valoresParaIfNull);
		DBObject $project = new BasicDBObject("$project", new BasicDBObject(STATUS, $ifNull));
		
		//$group
		DBObject $group = new BasicDBObject("$group", new BasicDBObject("_id", $status  ).append("quantidade", new BasicDBObject("$sum", 1)));
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add($match);
		operacoes.add($project);
		operacoes.add($group);
		
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		
		return paraFormatoRelatorioStatusProcessos(output);
	}
	
	
	/**
	 *  Retorna os dados do relatório de Status dos Processos Evolução Mensal
	 *  
	 *  db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *		{$match : {nomeProcesso: "Solicitação de Pagamento", inicio:{$exists:true}} }, <br>
	 *		{$project:{status: {$ifNull:["$status", "Em Aberto"]}, inicio: 1}}, <br>
	 *		{$group : {_id : {status: "$status", mes : {$month : "$inicio"}, ano : {$year : "$inicio"}}, quantidade: {$sum : 1} }}, <br>
	 *		{$sort : {"_id.ano" : 1, "_id.mes": 1, "_id.status" : 1} } <br> 
	 * 	]) <br>
	 * 
	 * @param processo
	 * @param inicio
	 * @param fim
	 */
	@Override
	public List<Object[]> statusDosProcessosEvolucaoMensal(String processo, Date inicio, Date fim){
		String $status = "$"+STATUS;
		
		//$match
		BasicDBList condicoes = new BasicDBList();
		condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$exists", true)));
		if(inicio != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(INICIO, new BasicDBObject("$lte", fim)));
		}
		condicoes.add(new BasicDBObject(NOME_PROCESSO, processo));
		BasicDBObject $and = new BasicDBObject("$and", condicoes);
		DBObject $match = new BasicDBObject().append("$match", $and );		
		
		//$project
		BasicDBList valoresParaIfNull = new BasicDBList();
		valoresParaIfNull.add($status);
		valoresParaIfNull.add("Em Aberto");
		BasicDBObject $ifNull = new BasicDBObject("$ifNull", valoresParaIfNull);
		DBObject $project = new BasicDBObject("$project", new BasicDBObject("status", $ifNull).append("inicio", 1));
		
		//$group
		String $inicio = "$"+INICIO;
		DBObject $month = new BasicDBObject("$month", $inicio);
		DBObject $year = new BasicDBObject("$year", $inicio);
		DBObject ids = new BasicDBObject("status", $status).append("mes", $month).append("ano", $year);
		DBObject _id = new BasicDBObject("_id", ids).append("quantidade", new BasicDBObject("$sum", 1));
		DBObject $group = new BasicDBObject("$group", _id);
		
		//$sort
		DBObject $sort = new BasicDBObject("$sort", new BasicDBObject("_id.ano", 1).append("_id.mes", 1).append("_id.status", 1));
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add($match);
		operacoes.add($project);
		operacoes.add($group);
		operacoes.add($sort);
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		
		return paraFormatoRelatorioStatusDosProcessosEvolucaoMensal(output);
	}	
	
	private List<Object[]> paraFormatoRelatorioQuantProcessos(AggregationOutput output) {
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(DBObject aux : output.results()){
			BasicDBObject obj = (BasicDBObject)aux;
			String processo = obj.getString("_id");
			long quantidade = obj.getLong("quantidade");
			
			resultado.add(new Object[]{processo, quantidade});
		}
		
		return resultado;
	}

	private List<Object[]> paraFormatoDoRelatorioDemandas(AggregationOutput output) {
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(DBObject obj : output.results()){
			DBObject id = (DBObject) obj.get("_id");
			String processo = (String) id.get("processo");
			Integer mes = (Integer) id.get("mes");
			Integer ano = (Integer) id.get("ano");
			Integer quantidade = (Integer) obj.get("quantidade");
			resultado.add(new Object[]{processo, DateUtils.pesquisarNomeMesResumido(mes) + "/" + ano, quantidade});
		}
		
		return resultado;
	}

	private List<Object[]> paraFormatoRelatorioStatusProcessos(AggregationOutput output) {
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(DBObject aux : output.results()){
			BasicDBObject obj = (BasicDBObject) aux;
			String processo = obj.getString("_id");
			long quantidade = obj.getLong("quantidade");
			
			resultado.add(new Object[]{processo, quantidade});
		}
		
		return resultado;
	}

	private List<Object[]> paraFormatoDoRelatorioDemandaSemanal(AggregationOutput output) {
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(DBObject aux : output.results()){
			BasicDBObject obj = (BasicDBObject) aux;
			BasicDBObject _id = (BasicDBObject) obj.get("_id");
			int diaDaSemana = _id.getInt("diaDaSemana");
			int mes = _id.getInt("mes");
			int ano = _id.getInt("ano");
			long quantidade = obj.getLong("quantidade");
			
			resultado.add(new Object[]{DateUtils.dataSemana(diaDaSemana), DateUtils.pesquisarNomeMesResumido(mes) + "/" + ano, quantidade});
		}
		
		return resultado;
	}

	private List<Object[]> paraFormatoRelatorioStatusDosProcessosEvolucaoMensal(AggregationOutput output) {
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(DBObject aux : output.results()){
			BasicDBObject obj = (BasicDBObject) aux;
			BasicDBObject _id = (BasicDBObject) obj.get("_id");
			String status = _id.getString("status");
			int mes = _id.getInt("mes");
			int ano = _id.getInt("ano");
			long quantidade = obj.getLong("quantidade");
			
			resultado.add(new Object[]{status, DateUtils.pesquisarNomeMesResumido(mes) + "/" + ano, quantidade});
		}
		
		return resultado;
	}
}
