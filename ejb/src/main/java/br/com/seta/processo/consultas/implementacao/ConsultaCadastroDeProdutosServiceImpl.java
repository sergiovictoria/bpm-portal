package br.com.seta.processo.consultas.implementacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.bean.dao.interfaces.InstanciaCadastroProdutoDao;
import br.com.seta.processo.consultas.DadosCadastroProduto;
import br.com.seta.processo.consultas.interfaces.ConsultaCadastroDeProdutosService;
import br.com.seta.processo.dto.AtividadeProcesso;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.InstanciaProcesso;

public class ConsultaCadastroDeProdutosServiceImpl implements ConsultaCadastroDeProdutosService, Serializable {
	private static final long serialVersionUID = 1L;

	private static final String DTO_FORMULARIO_PRODUTO = "dtoFormularioProduto";
	
	@Inject
	InstanciaCadastroProdutoDao dao;
	
	@Override
	public List<DadosCadastroProduto> buscaCadastrosDeProduto(FiltroCadastroProdutos filtro, Integer primeiroRegistro, Integer quantRegistros) {
		
		List<InstanciaProcesso> instancias = 
				dao.buscaInstanciaFinalizadasDeCadstroProduto(filtro, primeiroRegistro, quantRegistros);
		
		return paraListaDeDadosCadastroProduto(instancias);
	}

	@Override
	public int quantidadeTotalDeCadastros(FiltroCadastroProdutos filtro) {
		return dao.totalInstanciasFinalizadasDeCadastroProduto(filtro);
	}

	private List<DadosCadastroProduto> paraListaDeDadosCadastroProduto(
			List<InstanciaProcesso> instancias) {
		
		List<DadosCadastroProduto> dadosCadastroProduto = new ArrayList<DadosCadastroProduto>();
		
		for(InstanciaProcesso instancia : instancias){
			DadosCadastroProduto dados = paraDadosCadastroProduto(instancia);
			dadosCadastroProduto.add(dados);
		}
		
		return dadosCadastroProduto;
	}

	private DadosCadastroProduto paraDadosCadastroProduto(InstanciaProcesso instancia) {
		
		DadosCadastroProduto dados = new DadosCadastroProduto();
		dados.setCaseId(instancia.getCaseId());
		dados.setInicio(instancia.getInicio());
		dados.setFim(instancia.getFim());
		dados.setStatus(instancia.getStatus());
		dados.setFormularioProduto(getFormularioFornecedor(instancia));
		
		return dados;
	}
	
	private FormularioProduto getFormularioFornecedor(InstanciaProcesso instancia){
		List<AtividadeProcesso> atividades = instancia.getAtividades();
		if(atividades == null || atividades.isEmpty())
			return null;
		
		return (FormularioProduto) atividades.get(0).getValorVariavel(DTO_FORMULARIO_PRODUTO);
	}

}
