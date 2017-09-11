package br.com.seta.processo.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Suprimento;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.ProcessoLocal;
import br.com.seta.processo.interfaces.ProcessoRemote;
import br.com.seta.processo.map.MapObjectValue;


@Stateless(name="ProcessoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(ProcessoLocal.class)
@Remote(ProcessoRemote.class)
@Interceptors({LogInterceptor.class})
public class ProcessoRepository implements ProcessoLocal, ProcessoRemote {

	@Inject
	private Logger logger;
	
	MapObjectValue<Suprimento> mapObjectValue = new MapObjectValue<Suprimento>(Suprimento.class);


	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Processo");
	}
	
	@Override
	public void save(Object object, Class<?> type) {
		MapObjectValue<Object> mapObjectValue = new MapObjectValue<Object>(getInstanceofClass(type));
		try {
			mapObjectValue.save(object);
		}catch (Exception e) {
			logger.error("Erro ao tentar incluir objeco no Mongo - DB ",e);    
		}
	}


	@Override
	public void save(List<Object> objects) {
		try {
			mapObjectValue.save(objects);
		}catch (Exception e) {
			logger.error("Erro ao tentar incluir objectos no Mongo - DB ",e);    
		}
	}

	@Override
	public void save(Object object) {
		try {
			mapObjectValue.save(object);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao tentar incluir objectos no Mongo - DB ",e);    
		}
	}


	@Override
	public List<Suprimento> getSuprimentos(Date dataInicio, Date dataFinal) {
		List<Suprimento> suprimentos = new ArrayList<Suprimento>(0);
		try {
			suprimentos =  (List<Suprimento>) mapObjectValue.getObjectsDate(dataInicio, dataFinal);
			logger.info("O Retorno de sua lista é de  "+suprimentos.size());
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao consultar objectos no Mongo - DB ",e);
		}
		return suprimentos;
	}
	
	public Class<Object> getInstanceofClass(Class<?> type) {
		Class<Object> clazz = null;
		try {
			clazz = (Class<Object>) Class.forName(type.getCanonicalName());
		}catch (Exception e ) {  
		}
		return clazz;
	}
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Processo");
	}




}
