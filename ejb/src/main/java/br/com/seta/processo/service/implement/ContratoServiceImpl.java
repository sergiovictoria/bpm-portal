package br.com.seta.processo.service.implement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.interfaces.ContratoService;

@Stateless(name = "ContratoService")
@Local(ContratoService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})

public class ContratoServiceImpl implements ContratoService {

	@Inject
	private TransacaoMongo transacaoMongo;

	public ContratoServiceImpl() {
	}
	

	@Override
	public List<Contrato> listaContratosPendentes(Long caseID, Class<?> type) throws BonitaException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("statusContrato", 3L);
		map.put("caseID",caseID);
		return (List<Contrato>) transacaoMongo.find(map,Contrato.class);
	}
	

}
