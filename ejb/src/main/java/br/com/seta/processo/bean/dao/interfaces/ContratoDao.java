package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import br.com.seta.processo.dto.Contrato;

public interface ContratoDao {
	
	Contrato buscaContratoVigente(long codFornecedor);
	List<Contrato> buscaContratosVencidos(long codFornecedor);

}
