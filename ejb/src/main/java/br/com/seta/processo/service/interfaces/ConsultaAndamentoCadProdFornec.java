package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Remote;

import br.com.seta.processo.dto.DadosConsultaFornecProd;
import br.com.seta.processo.dto.FiltroConsultaFornecProd;

@Remote
public interface ConsultaAndamentoCadProdFornec {
	public abstract List<DadosConsultaFornecProd> consulta(FiltroConsultaFornecProd filtro, Integer primeiroRegistro, Integer quantRegistros);
	public abstract Long quantidadeRegistros(FiltroConsultaFornecProd filtro);
}
