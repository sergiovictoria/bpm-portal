package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.CentroCustos;
import br.com.seta.processo.dto.CentroCusto;


public class CentroCustoProvider implements IDataProvider<CentroCusto> {

	private static final long serialVersionUID = 1L;

	@Inject private  CentroCustos centroCustos;
	private BigDecimal nroempresa;
	private BigDecimal codhistorico;
	private String filtro;

	public CentroCustoProvider(BigDecimal nroempresa, BigDecimal codhistorico) {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.nroempresa = nroempresa;
		this.codhistorico = codhistorico;
	}
	
	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends CentroCusto> iterator(long first, long count) {
		if (this.filtro==null) {
			return centroCustos.findCustoS(this.nroempresa, this.codhistorico,  first, count).iterator();
		}else {
			return centroCustos.findCustoS(this.nroempresa, this.codhistorico, this.filtro, first, count).iterator();
		}
	}


	@Override
	public long size() {
		if (this.filtro==null) {
			return centroCustos.findCustoSize(this.nroempresa, this.codhistorico);
		}else {
			return centroCustos.findCustoSize(this.nroempresa, this.codhistorico, this.filtro);
		}
	}


	@Override
	public IModel<CentroCusto> model(final CentroCusto centroCusto) {
		return new LoadableDetachableModel<CentroCusto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected CentroCusto load() {
				return centroCusto;
			}
		};
	}
	
	
	public void putParameter(final String filtro) {
		this.filtro = filtro;
	}

	public void clear() {
		if (this.filtro!=null) {
			this.filtro = null;
		}
	}


}
