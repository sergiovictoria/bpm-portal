package br.com.seta.processo.bean;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Observes;
import javax.interceptor.Interceptors;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.entity.OrReqplanilhalanctoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.map.MapObjectEntity;


@Stateless(name="RequisicaoPlanilha")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class RequisicaoPlanilha extends GenericJpaDaoConSinco<OrReqplanilhalanctoEntity> {

	
	private static final long serialVersionUID = 1L;
	
    public void onOrRequisicaoPlanilhaChanged(@Observes final OrRequisicao orRequisicao) {
    	criaLinhaPlanilha(orRequisicao);
    }
    
	public void criaLinhaPlanilha(OrRequisicao orRequisicao) {
		Set<OrReqplanilhalancto> orReqplanilhalanctos = orRequisicao.getOrReqplanilhalanctos();
		Short linha = new Short("1");
		Long seqrequisicao = orRequisicao.getSeqrequisicao();
	    for (OrReqplanilhalancto dto : orReqplanilhalanctos) {
	    	OrReqplanilhalanctoEntity orReqplanilhalanctoEntity = (OrReqplanilhalanctoEntity) MapObjectEntity.mapPlanilhaEntity(dto,seqrequisicao);
	    	insert(orReqplanilhalanctoEntity);
	    	linha++;
	    }	
	}
	
	

}
