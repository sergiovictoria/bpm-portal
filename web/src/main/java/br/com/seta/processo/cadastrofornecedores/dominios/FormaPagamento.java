package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dados do domíno de Formas de Pagamento do Cadastro de Fornecedore
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FormaPagamento {
	private static final List<String> FORMAS_PAGAMENTO;
	
	static {
		ArrayList<String> formasPagamento = new ArrayList<String>();
		formasPagamento.add("DINHEIRO");
		formasPagamento.add("CHEQUE");
		formasPagamento.add("CARTÃO DÉBITO/CRÉDITO");
		formasPagamento.add("DOC");
		formasPagamento.add("TED");
		formasPagamento.add("BOLETO BANCÁRIO");
		formasPagamento.add("TRANSFERÊNCIA BANCÁRIA");
		formasPagamento.add("DEPÓSITO BANCÁRIO");
		formasPagamento.add("DDA - DÉBITO DIRETO AUTORIZADO");
		FORMAS_PAGAMENTO = Collections.unmodifiableList(formasPagamento);
	}
	
	/**
	 * 
	 * @return Lista imutável contendo os tipos de pagamentos do processo de Cadastro de Fornecedor
	 */
	public static List<String> getFormasPagamento(){
		return FORMAS_PAGAMENTO;
	}
}
