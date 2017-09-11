package br.com.seta.processo.consultas.interfaces;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.consultas.DadosCadFornecedor;

/**
 * Contém os relatórios do processo de Cadastro de Fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface ConsultaCadastroDeFornecedorService {
	
	List<DadosCadFornecedor> buscaInstanciasDeCadastroFornecedor(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status, Integer primeiroRegistro, Integer quantRegistros);
	
	int quantidadeTotalRegistros(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status);
}
