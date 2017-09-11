package br.com.seta.processo.provider;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.dao.FiltroIntencaoCompra;
import br.com.seta.processo.bean.dao.interfaces.InstanciaIntecaoCompraDao;
import br.com.seta.processo.dto.InstanciaProcesso;

public class InstanciasIntencaoCompraProvider extends SortableDataProvider<InstanciaProcesso, Comparator<InstanciaProcesso>>{
	private static final long serialVersionUID = 1L;

	@Inject
	private InstanciaIntecaoCompraDao dao;
	
	private FiltroIntencaoCompra filtro = new FiltroIntencaoCompra();
	
	public InstanciasIntencaoCompraProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	public InstanciasIntencaoCompraProvider(FiltroIntencaoCompra filtro){
		this();
		this.filtro = filtro;
	}
	
	@Override
	public Iterator<? extends InstanciaProcesso> iterator(long first, long count) {
		Integer primeiro = new Long(first).intValue();
		Integer quantidade = new Long(count).intValue();
		
		List<InstanciaProcesso> instanciasEncerradas = dao.buscaInstanciasEncerradas(this.filtro, primeiro, quantidade);
		return instanciasEncerradas.iterator();
	}

	@Override
	public long size() {
		return dao.quantidadeInstanciasEncerradas(this.filtro);
	}

	@Override
	public IModel<InstanciaProcesso> model(final InstanciaProcesso object) {
		return new LoadableDetachableModel<InstanciaProcesso>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected InstanciaProcesso load() {
				return object;
			}
		};
	}
	
	public void setFiltro(FiltroIntencaoCompra filtro){
		this.filtro = filtro;
	}

}
