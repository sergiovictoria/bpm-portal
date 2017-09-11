package br.com.seta.processo.consultas.implementacao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import br.com.seta.processo.bean.dao.interfaces.InstanciaCadastroFornecedorDao;
import br.com.seta.processo.consultas.DadosCadFornecedor;
import br.com.seta.processo.consultas.interfaces.ConsultaCadastroDeFornecedorService;
import br.com.seta.processo.dto.AtividadeProcesso;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * Gerencia o acesso aos dados dos relatórios do processo de Cadastro de Fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ConsultaCadastroDeFornecedorServiceImpl implements ConsultaCadastroDeFornecedorService, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private InstanciaCadastroFornecedorDao dao;
	
	@Override
	public List<DadosCadFornecedor> buscaInstanciasDeCadastroFornecedor(
			Long caseId, String codConsinco, String razaoSocialOuNome,
			String cpfCnpj, String comprador, Date inicio, Date fim,
			String status, Integer primeiroRegistro, Integer quantRegistros) {
		
		List<InstanciaProcesso> instancias = dao.buscaInstanciasFinalizadasDeCadastroFornecedor(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status, primeiroRegistro, quantRegistros);
		
		return paraListaDeDadosRelatorioCadFornecedor(instancias);
	}
	
	@Override
	public int quantidadeTotalRegistros(Long caseId, String codConsinco,
			String razaoSocialOuNome, String cpfCnpj, String comprador,
			Date inicio, Date fim, String status) {
		
		return dao.totalInstanciasFinalizadasDeCadastroFornecedor(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status);
	}

	private List<DadosCadFornecedor> paraListaDeDadosRelatorioCadFornecedor(List<InstanciaProcesso> instancias){
		List<DadosCadFornecedor> dadosRelatorio = new ArrayList<DadosCadFornecedor>();
		
		for(InstanciaProcesso instancia : instancias){
			DadosCadFornecedor dados = new DadosCadFornecedor();
			dados.setCaseId(instancia.getCaseId());
			dados.setInicio(instancia.getInicio());
			dados.setFim(instancia.getFim());
			dados.setStatus(instancia.getStatus());
			dados.setFormularioFornecedor(getFormularioFornecedor(instancia));
			
			dadosRelatorio.add(dados);
		}	
		
		return dadosRelatorio;
	}
	
	private FormularioFornecedor getFormularioFornecedor(InstanciaProcesso instancia){
		List<AtividadeProcesso> atividades = instancia.getAtividades();
		if(atividades == null || atividades.size() == 0)
			return null;
		
		return (FormularioFornecedor) atividades.get(0).getValorVariavel("dtoFormularioFornecedor");
	}

}
