package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.CentroCustos;
import br.com.seta.processo.dto.CentroCusto;
import br.com.seta.processo.dto.OrRequisicao;

public class OrReqplanilhalanctoProvider implements	IDataProvider<CentroCusto> {
	
	private static final long serialVersionUID = 1L;

	private OrRequisicao requisicao;
	
	@Inject
	private CentroCustos centrosCustos;

	@Override
	public void detach() {

	}
	
	public OrReqplanilhalanctoProvider(OrRequisicao requisicao){
		CdiContainer.get().getNonContextualManager().inject(this);
		this.requisicao = requisicao;
	}

	@Override
	public Iterator<? extends CentroCusto> iterator(long first, long count) {
		BigDecimal nroempresa = getNroEmpresa();
		BigDecimal codhistorico = getCodHistorico();
		List<CentroCusto> ccs = centrosCustos.findCustoS(nroempresa, codhistorico, first, count);
		
		return ccs.iterator();
	}

	@Override
	public long size() {
		BigDecimal nroempresa = getNroEmpresa();
		BigDecimal codhistorico = getCodHistorico();
		return centrosCustos.findCustoSize(nroempresa, codhistorico);
	}

	@Override
	public IModel<CentroCusto> model(final CentroCusto object) {
		return new LoadableDetachableModel<CentroCusto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected CentroCusto load() {
				return object;
			}
		};
	}
	
	public void setFiltro(OrRequisicao requisicao){
		this.requisicao= requisicao;
	}


	private BigDecimal getCodHistorico() {
		return requisicao.getCodhistorico() == null ? BigDecimal.ZERO : new BigDecimal(requisicao.getCodhistorico());
	}

	private BigDecimal getNroEmpresa() {
		return new BigDecimal(requisicao.getNroempresa());
	}

}
