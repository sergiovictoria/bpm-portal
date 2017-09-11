package br.com.seta.processo.relatorios.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.joda.time.Period;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import br.com.seta.processo.bean.CalendarioComercialSeta;
import br.com.seta.processo.bean.dao.SlaProcessoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.entity.SlaProcesso;
import br.com.seta.processo.relatorios.interfaces.RelatorioSlaService;
import br.com.seta.processo.servicos.InstanciaProcessoDao;
import br.com.seta.processo.utils.DateUtils;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.NOME_PROCESSO;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.FIM;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.INICIO;

/**
 * Classe que fornece os dados para os Dashboards de SLA
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless
@Local(RelatorioSlaService.class)
public class RelatorioSlaServiceImpl implements RelatorioSlaService {
	
	private static final String STATUS_DENTRO = "Dentro";
	private static final String STATUS_FORA = "Fora";
	
	@Inject
	private SlaProcessoDao slaDao;
	@Inject 
	private CalendarioComercialSeta calendarioComercial;
	private InstanciaProcessoDao instanciaDao;
	private DBCollection instanciaCollection;
	
	private List<SlaProcesso> slas;
	private Map<String, Long> mapSlas = new HashMap<String, Long>();
	
	@PostConstruct
	public void inicializa(){
		Datastore ds = MongoConnectionManager.getInstance().getDatastore();;
		this.instanciaDao = new InstanciaProcessoDao(ds);
		this.instanciaCollection = instanciaDao.getCollection();
		this.slas = slaDao.find().asList();
		removeSlasZerados();
		geraMapSlas();
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.relatorios.implementacao.RelatorioSlaService#slaGeralConsolidado(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Object[]> slaGeralConsolidado(Date inicio, Date fim){
		List<Object[]> resultado = new ArrayList<Object[]>();
		long contFora = 0L;
		long contDentro = 0L;
		
		for(SlaProcesso sla : slas){
			Map<String, Long> consolidadoPorProcesso = slaGeralConsolidadoPorProcesso(sla.getProcesso(), sla.getSlaEmMilisegundos(), inicio, fim);
			contDentro += consolidadoPorProcesso.get(STATUS_DENTRO);
			contFora += consolidadoPorProcesso.get(STATUS_FORA);
		}
		
		resultado.add(new Object[]{STATUS_DENTRO, contDentro});
		resultado.add(new Object[]{STATUS_FORA, contFora});
		
		return resultado;
	}	

	/* (non-Javadoc)
	 * @see br.com.seta.processo.relatorios.implementacao.RelatorioSlaService#slaPorProcessoConsolidado(java.util.Date, java.util.Date)
	 */
	@Override
	public List<Object[]> slaPorProcessoConsolidado(Date inicio, Date fim){
		List<Object[]> resultado = new ArrayList<Object[]>();
		
		for(SlaProcesso sla : slas){
			Map<String, Long> consolidadoPorProcesso = slaGeralConsolidadoPorProcesso(sla.getProcesso(), sla.getSlaEmMilisegundos(), inicio, fim);
			Long quantDentro = consolidadoPorProcesso.get(STATUS_DENTRO);
			Long quantFora = consolidadoPorProcesso.get(STATUS_FORA);
			resultado.add(new Object[]{sla.getProcesso(), quantDentro, quantFora});
		}
		
		return resultado;
	}
	
	/* (non-Javadoc)
	 * @see br.com.seta.processo.relatorios.implementacao.RelatorioSlaService#slaEvolucaoMensal(java.lang.String, java.util.Date, java.util.Date)
	 */
	@Override
	public List<Object[]> slaEvolucaoMensal(String processo, Date inicio, Date fim){
		Long slaProcesso = mapSlas.get(processo);
		
		//$match
		BasicDBList condicoes = new BasicDBList();
		condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$exists", true)));
		if(inicio != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$gte", inicio)));
		}
		if(fim != null){
			condicoes.add(new BasicDBObject(FIM, new BasicDBObject("$lte", fim)));
		}
		condicoes.add(new BasicDBObject(NOME_PROCESSO, processo));
		BasicDBObject $and = new BasicDBObject("$and", condicoes);
		DBObject $match = new BasicDBObject().append("$match", $and );
		
		//$group
		String $nomeProcesso = "$"+NOME_PROCESSO;
		String $inicio = "$"+INICIO;
		String $fim = "$"+FIM;
		DBObject $month = new BasicDBObject("$month", $fim);
		DBObject $year = new BasicDBObject("$year", $fim);
		DBObject _id = new BasicDBObject(NOME_PROCESSO, $nomeProcesso).append("mes", $month).append("ano", $year);
		DBObject $push = new BasicDBObject(NOME_PROCESSO, $nomeProcesso).append("_id", "$_id").append(INICIO, $inicio).append(FIM, $fim);
		DBObject $group = new BasicDBObject("$group", new BasicDBObject("_id", _id).append("instancias", new BasicDBObject("$push", $push)));
		
		ArrayList<DBObject> operacoes = new ArrayList<DBObject>();
		operacoes.add($match);
		operacoes.add($group);
		AggregationOutput output = instanciaCollection.aggregate(operacoes);
		
		return paraFormatoRelatorioEvolucaoMensal(output, slaProcesso);
	}
	
	private void geraMapSlas(){
		for(SlaProcesso sla : slas){
			mapSlas.put(sla.getProcesso(), sla.getSlaEmMilisegundos());
		}
	}

	private List<Object[]> paraFormatoRelatorioEvolucaoMensal(AggregationOutput output, long slaEmMilisegundos){
		List<Object[]> resultados = new ArrayList<Object[]>();
		
		for(DBObject aux : output.results()){
			BasicDBObject obj = (BasicDBObject) aux;
			long contDentro = 0;
			long contFora = 0;
			Object[] dentrosDoSla = new Object[3];
			Object[] forasDoSla = new Object[3];
			
			BasicDBObject _id = (BasicDBObject) obj.get("_id");
			int mes = _id.getInt("mes");
			int ano = _id.getInt("ano");
			String mesAno = DateUtils.pesquisarNomeMesResumido(mes) + "/" + ano;
			dentrosDoSla[0] = mesAno;
			dentrosDoSla[1] = STATUS_DENTRO;
			forasDoSla[0] = mesAno;
			forasDoSla[1] = STATUS_FORA;
			
			BasicDBList instancias = (BasicDBList) obj.get("instancias");
			//Para cada instância encontrada
			for(Object inst : instancias){
				BasicDBObject instancia = (BasicDBObject) inst;
				if(dentroDoSla(slaEmMilisegundos, instancia.getDate(INICIO), instancia.getDate(FIM))){
					contDentro++;
				}else{
					contFora++;
				}
			}
			dentrosDoSla[2] = contDentro;
			forasDoSla[2] = contFora;
			
			resultados.add(dentrosDoSla);
			resultados.add(forasDoSla);
		}
		
		return resultados;
	}
	
	private boolean dentroDoSla(long slaEmMilisegundos, Date inicio, Date fim){
		// TODO Adicionar a lógica de levar em consideração o calendário e o horário comercial no cálculo da adequação do intervalo da tarefa ao SLA
		Period duracaoAtividade = calendarioComercial.calculaDuracao(inicio, fim);
		long duracaoAtividadeEmMili = duracaoAtividade.toStandardDuration().getMillis();
		return duracaoAtividadeEmMili <= slaEmMilisegundos;
	}

	/**
	 * 
	 * db.getCollection('InstanciaProcesso').find({fim : {$exists : true}, nomeProcesso: NOME_PROCESSO}, {nomeProcesso: 1, inicio: 1, fim: 1})
	 * 
	 * @param processo
	 * @param slaEmMilesegundos
	 * @param inicio
	 * @param fim
	 * @return
	 */
	private Map<String, Long> slaGeralConsolidadoPorProcesso(String processo, long slaEmMilesegundos, Date inicio, Date fim){
		long contFora = 0L;
		long contDentro = 0L;
		Map<String, Long> resultado = new HashMap<String, Long>();
		
		Query<InstanciaProcesso> query = instanciaDao.createQuery()
				.field(NOME_PROCESSO).equal(processo)
				.field(FIM).exists().retrievedFields(true, NOME_PROCESSO, INICIO, FIM);
		
		if(inicio != null){
			query.field(FIM).greaterThanOrEq(inicio);
		}
		if(fim != null){
			query.field(FIM).lessThanOrEq(fim);
		}	
		List<InstanciaProcesso> instancias = query.asList();
		
		for(InstanciaProcesso instancia : instancias){
			if(dentroDoSla(slaEmMilesegundos, instancia.getInicio(), instancia.getFim())){
				contDentro++;
			}else{
				contFora++;
			}
		}
		resultado.put(STATUS_DENTRO, contDentro);
		resultado.put(STATUS_FORA, contFora);
		
		return resultado;
	}

	private void removeSlasZerados(){
		for(SlaProcesso sla : slas){
			if(sla.getTempo() == 0){
				slas.remove(sla);
			}
		}
	}
}
