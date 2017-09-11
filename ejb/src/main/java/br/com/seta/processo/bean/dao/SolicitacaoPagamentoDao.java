package br.com.seta.processo.bean.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.mongodb.morphia.dao.BasicDAO;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.SolicitacaoPagamento;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="SolicitacaoPagamentoDao")
@LocalBean
public class SolicitacaoPagamentoDao extends BasicDAO<SolicitacaoPagamento, String> {
	
	public SolicitacaoPagamentoDao() {
		super(SolicitacaoPagamento.class, MongoConnectionManager.getInstance().getDatastore());	
	}
	
//	public List<SolicitacaoPagamento> busca(FiltroSolicitacaoPagamento filtro, Integer primeiro, Integer quantidade){
//		Query<SolicitacaoPagamento> query = buscaPor(filtro).order("caseId");	
//		
//		if(primeiro != null)
//			query.offset(primeiro);
//		
//		if(quantidade != null)
//			query.limit(quantidade);
//		
//		return query.asList();		
//	}

//	public long quantidade(FiltroSolicitacaoPagamento filtro){
//		return buscaPor(filtro).countAll();
//	}

//	private Query<SolicitacaoPagamento> buscaPor(FiltroSolicitacaoPagamento filtro) {
//		Query<SolicitacaoPagamento> query = createQuery();
//		
//		if(filtro.getUserNameSolicitante() != null && !filtro.getUserNameSolicitante().trim().isEmpty())
//			query.field("userNameSolicitante").contains(filtro.getUserNameSolicitante());
//		
//		if(filtro.getCodFornecedor() != null)
//			query.field("codFornecedor").equal(filtro.getCodFornecedor());
//				
//		if(filtro.getNroNota() != null)
//			query.field("nroNota").equal(filtro.getNroNota());
//				
//		if(filtro.getValorDe() != null)
//			query.field("valor").greaterThanOrEq(filtro.getValorDe());
//				
//		if(filtro.getValorAte() != null)
//			query.field("valor").lessThanOrEq(filtro.getValorAte());
//		
//		if(filtro.getVencDe() != null)
//			query.field("dataVencimento").greaterThanOrEq(filtro.getVencDe());
//		
//		if(filtro.getVencAte() != null)			
//			query.field("dataVencimento").lessThanOrEq(filtro.getVencAte());		
//			
//		if(filtro.getStatus() != null)
//			query.field("status").equal(filtro.getStatus());
//		return query;
//	}
}
