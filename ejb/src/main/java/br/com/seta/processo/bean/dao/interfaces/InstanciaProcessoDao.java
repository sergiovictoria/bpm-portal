package br.com.seta.processo.bean.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * Representa as operações com a fonte de dados de InstanciaProcesso
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface InstanciaProcessoDao {
	
	/**
	 * Retorna as instãncias encerradas 
	 * 
	 * @param primeiroRegistro posição do primeiro registro a ser retornada. Utilizado para fins de paginação
	 * @param quantRegistros quantidade de registros retornados. Utilizado para fins de paginação
	 * @param inicio inicio opcional. Data inicial do intervalo de busca baseando se na data de encerramento da instância
	 * @param fim fim opcional. Data final do intervalo de busca baseando se na data de encerramento da instância
	 * @param textSearch textSearch parâmetro de consulta (filtro)
	 * @return quantidade de instâncias encerradas
	 */
	List<InstanciaProcesso> buscaInstanciasEncerradas(Integer primeiroRegistro, Integer quantRegistros, Date inicio, Date fim, String textSearch);

	/**
	 * Retorna a quantidade de instâncias encerradas
	 * 
	 * @param inicio opcional. Data inicial do intervalo de busca baseando se na data de encerramento da instância
	 * @param fim opcional. Data final do intervalo de busca baseando se na data de encerramento da instância
	 * @param textSearch parâmetro de consulta (filtro)
	 * @return quantidade de instâncias encerradas
	 */
	long quantidadeInstanciasEncerradas(Date inicio, Date fim, String textSearch);

}
