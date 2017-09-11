package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.Contrato;

public class ItemSemContratoProvider implements IDataProvider<Contrato> {

	private static final long serialVersionUID = 932346092527269819L;

	private List<Contrato> contrato = new ArrayList<Contrato>();

	/**
	 * Constructor
	 * @param user
	 */
	public ItemSemContratoProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	@Override
	public Iterator<? extends Contrato> iterator(long first, long count) {
		return this.contrato.subList((int) first, (int) (first + count)).iterator();
	}

	@Override
	public long size() {
		return contrato.size();
	}

	@Override
	public IModel<Contrato> model(final Contrato object) {
		return new LoadableDetachableModel<Contrato>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Contrato load() {
				return object;
			}
		};
	}

	@Override
	public void detach() {
	}
	
}
