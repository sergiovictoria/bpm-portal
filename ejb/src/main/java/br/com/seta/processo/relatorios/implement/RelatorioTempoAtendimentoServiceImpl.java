package br.com.seta.processo.relatorios.implement;

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
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import br.com.seta.processo.bean.CalendarioComercialSeta;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.relatorios.DadosTempoAtendimento;
import br.com.seta.processo.relatorios.interfaces.RelatorioTempoAtendimentoService;
import br.com.seta.processo.servicos.InstanciaProcessoDao;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.NOME_PROCESSO;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.FIM;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.INICIO;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.ID;
import static br.com.seta.processo.dto.InstanciaProcesso.Campos.INICIADO_POR;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless
@Local(RelatorioTempoAtendimentoService.class)
public class RelatorioTempoAtendimentoServiceImpl implements RelatorioTempoAtendimentoService {
	
	private InstanciaProcessoDao dao;
	@Inject
	private CalendarioComercialSeta calendarioComercial;
	private PeriodFormatter periodFormatter;
	
	@PostConstruct
	public void inicializa(){
		Datastore ds = MongoConnectionManager.getInstance().getDatastore();;
		this.dao = new InstanciaProcessoDao(ds);
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
	
	@Override
	public List<DadosTempoAtendimento> lista(Long caseId, String nomeProcesso, Date inicio, Date fim, Long idUsuarioIniciador, Integer quantRegistros, Integer pagina){
		Query<InstanciaProcesso> query = dao.createQuery().field(FIM).exists();
		
		if(caseId != null){
			query.field(ID).equal(caseId);
		}
		if(nomeProcesso != null){
			query.field(NOME_PROCESSO).equal(nomeProcesso);
		}
		if(inicio != null){
			query.field(INICIO).greaterThanOrEq(inicio);
		}
		if(fim != null){
			query.field(INICIO).lessThanOrEq(fim);
		}
		if(idUsuarioIniciador != null){
			query.field(INICIADO_POR + ".id").equal(idUsuarioIniciador);
		}
		
		query.retrievedFields(true, ID, NOME_PROCESSO, INICIO, FIM, INICIADO_POR);	
		
		if(quantRegistros != null && pagina != null){
			query.limit(quantRegistros).offset(pagina * quantRegistros);
		}
		
		List<InstanciaProcesso> instancias = query.asList();
		
		return paraListaDeDadosTempoAtendimento(instancias);
	}

	private List<DadosTempoAtendimento> paraListaDeDadosTempoAtendimento(List<InstanciaProcesso> instancias) {
		List<DadosTempoAtendimento> resultado = new ArrayList<DadosTempoAtendimento>();
		
		for(InstanciaProcesso instancia : instancias){
			Period duracao = calendarioComercial.calculaDuracao(instancia.getInicio(), instancia.getFim());
			DadosTempoAtendimento dadosTempoAtendimento = new DadosTempoAtendimento(instancia);
			dadosTempoAtendimento.setDuracao(this.periodFormatter.print(duracao.normalizedStandard(PeriodType.time())));
			resultado.add(dadosTempoAtendimento);
		}
		
		return resultado;
	}

}
