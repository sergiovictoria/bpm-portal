package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * Mantém as operações de acesso a base de dados das Instâncias do processo de Cadastro de Produtos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface InstanciaCadastroProdutoDao {	
	/**
	 * Busca todas as instâncias de Cadastro de Produtos com base nos parâmetros fornecidos
	 * 
	 * @param filtro
	 * @param primeiroRegistor Opcional. Utilizado, para fins de paginação, para indicar o primeiro registro da base a ser retornado.
	 * @param quantRegistros Opcional. Utilizado, para fins de paginação, para indicar a quantidade total de registros a ser retornado.
	 * @return
	 */
	List<InstanciaProcesso> buscaInstanciaFinalizadasDeCadstroProduto(FiltroCadastroProdutos filtro, Integer primeiroRegistor, Integer quantRegistros);

	/**
	 * Retorna a quantidade total de instâncias de Cadastro de Produto com base nos parâmetros fornecidos
	 * 
	 * @param filtro
	 * @return
	 */
	int totalInstanciasFinalizadasDeCadastroProduto(FiltroCadastroProdutos filtro);
	

	/**
	 * Busca os dados mais recentes do CadastroProduto do processo de Cadastro de Produto
	 * 
	 * @param caseId Id da instância do processo para o qual o CadastroProduto será buscado
	 * @return CadastroProduto mais recente ou null caso não sejá encontrado um valor para o caseId fornecido
	 */
	CadastroProduto buscaCadastroProduto(long caseId);
	
	/**
	 * Busca os dados mais recentes do FormularioProduto do processo de Cadastro de Produto
	 * 
	 * @param caseId Id da instância do processo para o qual o FormularioProduto será buscado
	 * @return FormularioProduto mais recente ou null caso não sejá encontrado um valor para o caseId fornecido
	 */
	FormularioProduto buscaFormularioProduto(long caseId);

}
