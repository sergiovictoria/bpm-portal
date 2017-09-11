package br.com.seta.processo.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.OrIndicador;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.WrapperUtils;


@Stateless(name="Indicadores")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Indicadores {

	
	@Inject
	private Logger logger;
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Indicadores ");
	}
	
		
	public List<OrIndicador>  addIndicador(List<OrIndicador> orIndicadorors, String username,  String atividade) {
					
		Integer size = orIndicadorors.size();
		OrIndicador orIndicador = new OrIndicador();
		java.util.Date _dataIndicador = new java.util.Date();
	
		if (size > 0) {
			
			orIndicador.setUsuario(username);
			orIndicador.setData(_dataIndicador);
			orIndicador.setIntervalo(WrapperUtils.diffHoursMinutesSeconds(orIndicadorors.get(size-1).getData(),_dataIndicador));
			orIndicador.setTempoTotal(WrapperUtils.diffHoursMinutesSeconds(orIndicadorors.get(0).getData(),_dataIndicador));
			orIndicador.setAtividade(atividade);
			
		}else {
			
			orIndicador.setIntervalo("0");
			orIndicador.setTempoTotal("0");
			orIndicador.setData(_dataIndicador);
			orIndicador.setUsuario(username);
			orIndicador.setAtividade(atividade);

		}
		
		orIndicadorors.add(orIndicador);
		return orIndicadorors;

	}
	
	
	public List<OrIndicador>  addIndicador(List<OrIndicador> orIndicadorors, String username,  String atividade, String status) {
		
		Integer size = orIndicadorors.size();
		OrIndicador orIndicador = new OrIndicador();
		java.util.Date _dataIndicador = new java.util.Date();
	
		if (size > 0) {
			
			orIndicador.setUsuario(username);
			orIndicador.setData(_dataIndicador);
			orIndicador.setIntervalo(WrapperUtils.diffHoursMinutesSeconds(orIndicadorors.get(size-1).getData(),_dataIndicador));
			orIndicador.setTempoTotal(WrapperUtils.diffHoursMinutesSeconds(orIndicadorors.get(0).getData(),_dataIndicador));
			orIndicador.setAtividade(atividade);
			orIndicador.setStatus(status);
			
		}else {
			
			orIndicador.setIntervalo("0");
			orIndicador.setTempoTotal("0");
			orIndicador.setData(_dataIndicador);
			orIndicador.setUsuario(username);
			orIndicador.setAtividade(atividade);
			orIndicador.setStatus(status);

		}
		
		orIndicadorors.add(orIndicador);
		return orIndicadorors;

	}
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Indicadores");
	}


}

