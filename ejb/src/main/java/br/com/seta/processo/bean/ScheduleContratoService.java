/**
 * 
 */
package br.com.seta.processo.bean;


import static br.com.seta.processo.constant.StatusContratoServico.SOLICITACAO_CONTRATO_DTO;
import static br.com.seta.processo.constant.StatusContratoServico.SOLICITACAO_CONTRATO_PROCESSO;
import static br.com.seta.processo.constant.StatusContratoServico.SOLICITACAO_CONTRATO_VERSAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.bpm.process.ProcessInstance;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.Semana;
import br.com.seta.processo.dto.SolicitacaoContrato;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;
import br.com.seta.processo.utils.PropertiesEJBUtils;

/**
 * @author Sérgio da Victória
 *
 *   
 */

@Singleton(name="ScheduleContratoService")
@TransactionManagement(TransactionManagementType.BEAN)
@Interceptors({LogInterceptor.class})
@LocalBean
//@Startup

public class ScheduleContratoService {

	
	@Inject private Logger logger;
	@Inject TransacaoMongo transacaoMongo; 
	@Resource private TimerService timerService;
	@Inject private GroupService groupService;
	@Inject private ExecuteRestAPI executeRestAPI;
	private Map<String, Object> listVariablesSerializable = new HashMap<String, Object>(); 
	private String bpmUser;
	private String bpmPassword;

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - ScheduleContratoService");
		this.bpmUser = PropertiesEJBUtils.getInstance().getValues("bpmUser");
		this.bpmPassword = PropertiesEJBUtils.getInstance().getValues("bpmPassword");
	}


	@Lock(LockType.WRITE)
	public void agendar() {
		ScheduleExpression expression = new ScheduleExpression();
		
		Semana semana = (Semana) transacaoMongo.findOne(Semana.class);

		
		if ((semana.getHoras().equals("0")) || (semana.getHoras().equals("*")) ) {
			expression.hour(semana.getHoras());
		}else {
			String horas = "*/"+semana.getHoras();
			expression.hour(horas);
		}
		
		if (semana.getMinutos().equals("0")  ||  semana.getMinutos().equals("*")  ) {
		   expression.minute(semana.getMinutos());
		}else{
			String minutos = "*/"+semana.getMinutos();
			expression.minute(minutos);
		}
		
		
		expression.dayOfWeek(semana.getIntervalo());
		TimerConfig config = new TimerConfig();
		config.setInfo("ContratosServicos");
		config.setPersistent(false);
		this.timerService.createCalendarTimer(expression,config);
		logger.info("Novo agendamento realizado  " + expression);
		
	}


	@Lock(LockType.WRITE)
	public void stop(String info) {

		for (Object obj :  this.timerService.getTimers()) {
			if (obj!=null) {
				Timer t = (Timer) obj;
				if (t.getInfo().equals("ContratosServicos")) {
					logger.info("Removendo ===>>> "+obj);
					t.cancel();
				}
			}
		}
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - ScheduleContratoService");
	}

	@Timeout
	public void execute(Timer timer) throws Exception {
		
		SolicitacaoContrato solicitacaoContrato = new SolicitacaoContrato();

		logger.info("Procurando contrato de serviços ");

		String listEmails = null;
		List<Contrato> contratos = (List<Contrato>) transacaoMongo.find("statusContrato", 2L ,Contrato.class);

		if (contratos.size()>0) {
			
			transacaoMongo.updateWithTwoAttributes("statusContrato",2L,"statusContrato",3L,Contrato.class);
			
			List<String> emails = groupService.findEmails("CSC - Juridico");
			for (String dto : emails) {
				listEmails += dto + ", ";
			}
			
			solicitacaoContrato.setEmailsCSCJuridicoContratoList(listEmails);
			solicitacaoContrato.setEmailsCSCJuridicoContrato(listEmails);
			listVariablesSerializable.put(SOLICITACAO_CONTRATO_DTO, solicitacaoContrato);
			ProcessInstance processInstance = executeRestAPI.initCaseInstanceWithVariable(this.bpmUser, this.bpmPassword, listVariablesSerializable, SOLICITACAO_CONTRATO_PROCESSO,SOLICITACAO_CONTRATO_VERSAO);
			long caseId = processInstance.getId();
	      
			for (Contrato contrato : contratos) {
	        	Long numeroInstanciaOrigem = contrato.getNumeroInstanciaOrigem(); 
				transacaoMongo.updateWithTwoAttributes("numeroInstanciaOrigem",numeroInstanciaOrigem,"caseID",caseId,Contrato.class);				
			}
				
		}

		logger.info("Fim do processo contrato serviços, total de contratos para processar [ "+contratos.size()+ " ] ");

	}

	
	public void save(String horas, String minutos, String inicioSemana, String fimSemana) {
		String ini = diaSemana(inicioSemana);
		String fim = diaSemana(fimSemana);
		String intervalo = ini+"-"+fim;
		Semana semana = new Semana();
		semana.setHoras(horas);
		semana.setMinutos(minutos);
		semana.setInicioSemana(inicioSemana);
		semana.setFimSemana(fimSemana);
		semana.setIntervalo(intervalo);
		transacaoMongo.removeCollection(Semana.class);
		transacaoMongo.save(semana, Semana.class);
	}
	
	
	public Semana getSemana() {
		return (Semana) transacaoMongo.findOne(Semana.class); 
	}


	private String diaSemana(String key) {
		Map<String, String> mapDias = new HashMap<String, String>();
		mapDias.put("Domingo","Sun");
		mapDias.put("Segunda-Feira","Mon");
		mapDias.put("Terça-Feira","Tue");
		mapDias.put("Quarta-Feira","Wed");
		mapDias.put("Quinta-Feira","Thu");
		mapDias.put("Sexta-Feira","Fri");
		mapDias.put("Sabado","Sat");
		return mapDias.get(key);
	}

}
