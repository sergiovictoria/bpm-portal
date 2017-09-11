package br.com.seta.processo.consultas.interfaces;

import java.util.List;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.consultas.DadosCadastroProduto;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface ConsultaCadastroDeProdutosService {
	
	List<DadosCadastroProduto> buscaCadastrosDeProduto(FiltroCadastroProdutos filtro, Integer primeiroRegistro, Integer quantRegistros);
	
	int quantidadeTotalDeCadastros(FiltroCadastroProdutos filtro);

}
