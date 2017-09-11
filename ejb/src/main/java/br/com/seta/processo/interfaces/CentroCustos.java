package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.CentroCusto;

public interface CentroCustos {
	public abstract List<CentroCusto> getCentroCusto(CentroCusto centroCusto);
}
