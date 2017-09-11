package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.dao.SlaProcessoDao;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.entity.SlaProcesso;
import br.com.seta.processo.exceptions.SlaProcessoException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.ProcessoService;

/**
 * Serviço responsável por tratar as regras de negócio referente a SLAs dos processos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="SlaProcessoService")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class SlaProcessoService {
	
	@Inject
	private SlaProcessoDao dao;
	@Inject
	private ProcessoService processoService;
	
	public List<SlaProcesso> listaSlasProcessos(User user) throws SlaProcessoException{
		try {
			List<SlaProcesso> slas = dao.find().asList();
			Set<String> processos = listaProcessos(user);
			return mergeSlas(slas, processos);
		} catch (BonitaException e) {
			throw new SlaProcessoException(e);
		}
	}
	
	public void salva(List<SlaProcesso> slas){
		dao.getDatastore().save(slas);
	}
	
	public void salva(SlaProcesso sla){
		dao.save(sla);
	}
	
	private List<SlaProcesso> mergeSlas(List<SlaProcesso> slas, Set<String> processos){
		List<String> processosComSlas = new ArrayList<String>();
		for(SlaProcesso sla : slas){
			processosComSlas.add(sla.getProcesso());
		}
		
		for(String processo : processos){
			if(!processosComSlas.contains(processo)){
				slas.add(criaSlaDefaultPara(processo));
			}
		}
		
		return slas;
	}
	
	private SlaProcesso criaSlaDefaultPara(String processo) {
		return new SlaProcesso(processo, 0, SlaProcesso.UnidadeTempo.Dias);
	}

	private Set<String> listaProcessos(User user) throws BonitaException{
		List<TaskProcess> processos = processoService.listaProcessos(user);
		Set<String> processosFiltrados = new HashSet<String>();
		
		for(TaskProcess processo : processos){
			processosFiltrados.add(processo.getName());
		}		
		
		return processosFiltrados;
	}

}
