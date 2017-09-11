package br.com.seta.processo.repository;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.bean.Requisicao;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.RequisicaoLocal;
import br.com.seta.processo.interfaces.RequisicaoRemote;




@Stateless(name="RequisicaoRepository")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(RequisicaoLocal.class)
@Remote(RequisicaoRemote.class)
@Interceptors({LogInterceptor.class})
public class RequisicaoRepository implements RequisicaoLocal, RequisicaoRemote {
	
	@Inject
	private Requisicao requisicao; 

	
	@Override
	public void criaRequsicao(OrRequisicao orRequisicao) {
		requisicao.criaRequsicao(orRequisicao);
	}
	
	
}
