package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.Cfops;
import br.com.seta.processo.dto.Cfop;
import br.com.seta.processo.utils.OderProvider;


public class CfopProvider extends SortableDataProvider<Cfop, String> {

	private static final long serialVersionUID = 1L;
	@Inject private Cfops cfops;
	private String descricaored;
	

	public CfopProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	

	@Override
	public Iterator<? extends Cfop> iterator(long first, long count) {
		if (this.descricaored == null) {
			return cfops.getCfopS(addOrder(), first, count).iterator();
		}else {
			return cfops.getCfopSLike(addOrder(), this.descricaored, first, count).iterator();

		}
	}


	@Override
	public long size() {
		if (this.descricaored == null) {
			return cfops.size();
		}else {
			return cfops.sizeLike(this.descricaored);
		}
	}


	@Override
	public IModel<Cfop> model(final Cfop cfop) {
		return new LoadableDetachableModel<Cfop>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Cfop load() {
				return cfop;
			}
		};
	}

	
	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex = OderProvider.CfopCompAsc.getValue();

		if (sort==null) {
			return  OderProvider.CfopCompAsc.getValue();
		}	
		
		if (sort.getProperty().equals("descricaoRed"))  {
			return getSort().isAscending() ? OderProvider.CfopCompAsc.getValue() : OderProvider.CfopCompDes.getValue();
		}

		if (sort.getProperty().equals("CfopCfop"))  {
			return getSort().isAscending() ? OderProvider.CfopCfopAsc.getValue() : OderProvider.CfopCfopDes.getValue();
		}

		return returnIndex;


	}
	
	public void clear() {
		if (this.descricaored!=null) {
			this.descricaored = null;
		}
	}


	public void putParameter(final String descricaored) {
		this.descricaored = descricaored;
	}



}
