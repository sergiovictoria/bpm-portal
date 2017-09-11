package br.com.seta.processo.bean.dao.interfaces;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.InstanciaProcesso;

public interface InstanciaCadastroFornecedorDao {
	
	/**
	 * Busca instâncias finalizadas do processo de Cadastro de Fornecedor baseando-se nos filtros fornecidos como parâmetros
	 * 
	 * @param caseId
	 * @param codConsinco
	 * @param razaoSocialOuNome
	 * @param cpfCnpj
	 * @param comprador
	 * @param inicio
	 * @param fim
	 * @param status
	 * @param primeiroRegistro Opcional. Indíce do primeiro registro
	 * @param quantRegistros Opcional. Quantidade de registros retornados a partir do primeiroRegistro
	 * @return
	 */
	List<InstanciaProcesso> buscaInstanciasFinalizadasDeCadastroFornecedor(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status, Integer primeiroRegistro, Integer quantRegistros);
	
	/**
	 * Total de instâncias finalizadas do processo de Cadastro de Fornecedor
	 * 
	 * @param caseId
	 * @param codConsinco
	 * @param razaoSocialOuNome
	 * @param cpfCnpj
	 * @param comprador
	 * @param inicio
	 * @param fim
	 * @param status
	 * @return
	 */
	int totalInstanciasFinalizadasDeCadastroFornecedor(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status);
	
	/**
	 * Busca os dadoas mais recentes do CadastroFornecedor
	 * 
	 * @param caseId Id da instância do processo para o qual o CadastroFornecedor será buscado
	 * @return CadastroFornecedor mais recente ou null caso não sejá encontrado um valor para o caseId fornecido
	 */
	CadastroFornecedor buscaCadastroFornecedor(long caseId);
	
	/**
	 * Busca os dados mais recentes do FormularioFornecedor
	 * 
	 * @param caseId Id da instância do processo para o qual o FormularioFornecedor será buscado
	 * @return FormularioFornecedor mais recente ou null caso não sejá encontrado um valor para o caseId fornecido
	 */
	FormularioFornecedor buscaFormularioFornecedor(long caseId);

}
