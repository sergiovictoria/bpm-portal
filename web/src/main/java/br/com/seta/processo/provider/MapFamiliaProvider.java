package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.MapFamilias;
import br.com.seta.processo.dto.MapFamilia;
import br.com.seta.processo.utils.OderProvider;


public class MapFamiliaProvider extends SortableDataProvider<MapFamilia, String> {

	private static final long serialVersionUID = 1L;
	@Inject private MapFamilias familiasBean;  

	private String familia = null;
	private BigDecimal seqfamilia;
	private BigDecimal seqfornecedor;


	public MapFamiliaProvider(BigDecimal seqfornecedor) {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.seqfornecedor = seqfornecedor;
	}


	@Override
	public Iterator<? extends MapFamilia> iterator(long first, long count) {
		return familiasBean.findAll(first, count, familia, seqfamilia, seqfornecedor).iterator();
	}

	@Override
	public long size() {
		return familiasBean.size(familia, seqfamilia, seqfornecedor);
	}

	@Override
	public IModel<MapFamilia> model(final MapFamilia familia) {
		return new LoadableDetachableModel<MapFamilia>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected MapFamilia load() {
				return familia;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex =  OderProvider.ModeloAscDescricao.getValue();

		if (sort==null) {
			return  OderProvider.ModeloAscDescricao.getValue();
		}	

		if (sort.getProperty().equals("modeloDescricao"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscDescricao.getValue() : OderProvider.ModeloDesDescricao.getValue();
		}

		if (sort.getProperty().equals("modeloCodigo"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscCodigo.getValue() : OderProvider.ModeloDesCodigo.getValue();
		}

		return returnIndex;


	}


	public void clear() {

		if (this.familia!=null) {
			this.familia = null;
		}

		if(seqfamilia != null) {
			seqfamilia = null;
		}

		if(seqfornecedor != null) {
			seqfornecedor = null;
		}

	}


	public void putParameter(final String familia, BigDecimal seqfamilia, BigDecimal seqfornecedor) {
		this.familia = familia;
		this.seqfamilia = seqfamilia;
		this.seqfornecedor = seqfornecedor;
	}


}
