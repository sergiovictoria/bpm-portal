package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import br.com.seta.processo.dto.FiltroConsultaFornecProd;
import br.com.seta.processo.dto.InstanciaProcesso;

public interface ConsultaInstanciasProdutoFornecedor {
	
	/**
	 * Busca as instâncias do Processo de Cadastro de Produto e Cadastro de Fornecedor
	 * 
	 * @param filtro 
	 * @param primeiroRegistro
	 * @param quantRegistros
	 * @return Uma coleção contendos as instâncias encontradas
	 */
	List<InstanciaProcesso> busca(FiltroConsultaFornecProd filtro, Integer primeiroRegistro, Integer quantRegistros);
	
	/**
	 * Retorna a quantidade de registros baseando-se em um determinado filtro
	 * @param filtro
	 * @return quantidade de registros baseando-se em um determinado filtro
	 */
	long quantidade(FiltroConsultaFornecProd filtro);

}
