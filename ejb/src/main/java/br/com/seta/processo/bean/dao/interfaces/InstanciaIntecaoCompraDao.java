package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import br.com.seta.processo.bean.dao.FiltroIntencaoCompra;
import br.com.seta.processo.dto.InstanciaProcesso;

public interface InstanciaIntecaoCompraDao {
	
	List<InstanciaProcesso> buscaInstanciasEncerradas(FiltroIntencaoCompra filtro, Integer primeiroRegistro, Integer quantRegistros);
	long quantidadeInstanciasEncerradas(FiltroIntencaoCompra filtro);

}
