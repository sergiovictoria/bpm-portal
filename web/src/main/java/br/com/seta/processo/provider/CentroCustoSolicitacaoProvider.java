package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.CentroCustos;
import br.com.seta.processo.dto.CentroCusto;


public class CentroCustoSolicitacaoProvider implements IDataProvider<CentroCusto> {

	private static final long serialVersionUID = 1L;

	@Inject private  CentroCustos centroCustos;
	private BigDecimal nroempresa;
	private BigDecimal codhistorico;
	private String filtro;
	
	private List<CentroCusto> list = new ArrayList<CentroCusto>();

	public CentroCustoSolicitacaoProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends CentroCusto> iterator(long first, long count) {
		return list.iterator();
	}


	@Override
	public long size() {
		return list.size();
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
	
	
	public void putParameter(BigDecimal nroempresa, final String nroplanilha) {
		this.list.clear();
		this.list.addAll(centroCustos.findCentroCustosSolicitcao(nroempresa, nroplanilha));
	}

	public void clear() {
		if (this.filtro!=null) {
			this.filtro = null;
		}
	}

	public List<CentroCusto> getCentrosCustos(){
		return list;
	}
}
