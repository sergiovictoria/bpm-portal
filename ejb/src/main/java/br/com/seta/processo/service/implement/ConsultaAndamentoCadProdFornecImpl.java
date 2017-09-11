package br.com.seta.processo.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import br.com.seta.processo.bean.dao.interfaces.ConsultaInstanciasProdutoFornecedor;
import br.com.seta.processo.constant.VariaveisCadastroFornecedores;
import br.com.seta.processo.constant.VariaveisCadastroProduto;
import br.com.seta.processo.dto.AtividadeProcesso;
import br.com.seta.processo.dto.DadosConsultaFornecProd;
import br.com.seta.processo.dto.FiltroConsultaFornecProd;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.service.interfaces.ConsultaAndamentoCadProdFornec;

@Stateless(name="ConsultaAndamentoCadProdFornecImpl")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(ConsultaAndamentoCadProdFornec.class)

public class ConsultaAndamentoCadProdFornecImpl implements ConsultaAndamentoCadProdFornec {

	@Inject	
	private ConsultaInstanciasProdutoFornecedor instancias;
	
	@Override
	public List<DadosConsultaFornecProd> consulta(FiltroConsultaFornecProd filtro, Integer primeiroRegistro, Integer quantRegistros) {
		List<InstanciaProcesso> insts = instancias.busca(filtro, primeiroRegistro, quantRegistros);
		
		return deListaInstanciasParaListaDadosConsultaFornecProd(insts);
	}

	@Override
	public Long quantidadeRegistros(FiltroConsultaFornecProd filtro) {
		return instancias.quantidade(filtro);
	}
	
	private List<DadosConsultaFornecProd> deListaInstanciasParaListaDadosConsultaFornecProd(List<InstanciaProcesso> instancias){
		List<DadosConsultaFornecProd> resultado = new ArrayList<DadosConsultaFornecProd>();
		for(InstanciaProcesso instancia : instancias){
			DadosConsultaFornecProd dadosConsulta = deInstanciaParaDadosConsultaFornecProd(instancia);
			resultado.add(dadosConsulta);
		}
		
		
		return  resultado;
	}
	
	private DadosConsultaFornecProd deInstanciaParaDadosConsultaFornecProd(InstanciaProcesso instancia){
		Long caseId = instancia.getCaseId();
		String operacao = instancia.getNomeProcesso();
		String descricao = getDescricao(instancia);
		String status = getStatus(instancia);
		Date dataRequisicao = instancia.getInicio();
		Date dataAprovacaoRejeicao = instancia.getFim();
		return new DadosConsultaFornecProd(caseId, operacao, descricao, status, dataRequisicao, dataAprovacaoRejeicao);		
	}

	private String getStatus(InstanciaProcesso instancia) {
		String status = instancia.getStatus();		
		return status == null ? DadosConsultaFornecProd.STATUS_EM_ANDAMENTO : status;
	}

	private String getDescricao(InstanciaProcesso instancia) {
		AtividadeProcesso ultimaAtividade = instancia.ultimaAtividade();
		
		if(ultimaAtividade == null) return "";
		
		if(instancia.getNomeProcesso().equals(DadosConsultaFornecProd.OPERACAO_CAD_FORNECEDOR)){
			FormularioFornecedor ff = (FormularioFornecedor) ultimaAtividade.getValorVariavel(VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR);
			return getDescricaoFornecedor(ff);
		}else{
			FormularioProduto fp = (FormularioProduto) ultimaAtividade.getValorVariavel(VariaveisCadastroProduto.FORMULARIO_PRODUTO);
			return fp.getDescCompleta();
		}		
	}
	
	private String getDescricaoFornecedor(FormularioFornecedor ff){		
		String nomeReduzido = ff.getNomeReduzido();		
		return nomeReduzido == null ? ff.getRazaoSocialReduzido() : nomeReduzido;		
	}

}
