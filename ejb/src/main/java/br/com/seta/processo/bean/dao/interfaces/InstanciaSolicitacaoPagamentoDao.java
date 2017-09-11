package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import br.com.seta.processo.bean.dao.FiltroSolicitacaoPagamento;
import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface InstanciaSolicitacaoPagamentoDao {

	/**
	 * Retorna uma lista de instâncias do processo de Solicitação de Pagamento contendo os dados da última atividade
	 * 
	 * @param filtro Filtro contendo os dados para a busca da instância
	 * @return Uma lista contendo todas as instâncias encontradas segundo o filtro fornecedio
	 */
	
	/**
	 * Retorna uma lista de instâncias do processo de Solicitação de Pagamento contendo os dados da última atividade
	 * 
	 * @param filtro Filtro contendo os dados para a busca da instância
	 * @param primeiro Opcional. Índice do primeiro registro. Igual ou maior que zero
	 * @param quantidade Opcional. Quantidade máxima de registros retornados. Igual ou maior que zero
	 * @return Uma lista contendo as instâncias encontradas segundo o filtro fornecido
	 */
	List<InstanciaProcesso> buscaInstancias(FiltroSolicitacaoPagamento filtro, Integer primeiro, Integer quantidade);
	
	/**
	 * Quantidade de instânicias do processo de Solicitação de Pagamento
	 * 
	 * @param filtro Filtro contendo os dados para a busca da instância
	 * @return Quantidade de instâncias encontradas segundo o filtro fornecido
	 */
	long quantidadeTotalInstancias(FiltroSolicitacaoPagamento filtro);

}
