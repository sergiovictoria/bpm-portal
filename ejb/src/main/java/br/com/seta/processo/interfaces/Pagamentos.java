package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.Pagamento;

public interface Pagamentos {
	public abstract List<Pagamento> findPagamentoByID(Pagamento pagamento);
}

