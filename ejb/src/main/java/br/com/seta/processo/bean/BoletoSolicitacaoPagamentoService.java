package br.com.seta.processo.bean;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.transaction.ManagerMongo;

/**
 * Serviço responsável pela lógica de integração com o MongoDB no que diz respeito ao boleto do Processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
public class BoletoSolicitacaoPagamentoService {
	
	private final static String KEY = "caseId";
	
	private ManagerMongo<Documento> managerMongo;
	
	@PostConstruct
	public void init(){
		managerMongo = new ManagerMongo<Documento>(Documento.class); 
	}
	
	public void save(Documento boleto){
		managerMongo.save(boleto);
	}
	
	public Documento find(Long caseId){
		return (Documento) managerMongo.findOne(KEY, caseId);
	}
}
