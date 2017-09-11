package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.dao.interfaces.ContratoDao;
import br.com.seta.processo.dto.Contrato;

public class ContratosAnterioresProvider implements IDataProvider<Contrato> {

	private static final long serialVersionUID = 932346092527269819L;

	@Inject
	private ContratoDao dao;
	
	private List<Contrato> contratos = new ArrayList<Contrato>();

	public ContratosAnterioresProvider(long codFornecedor) {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.contratos = dao.buscaContratosVencidos(codFornecedor);
	}

	@Override
	public Iterator<? extends Contrato> iterator(long first, long count) {
		return contratos.iterator();
	}

	@Override
	public long size() {
		return contratos.size();
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
