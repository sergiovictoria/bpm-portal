package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.Compradores;
import br.com.seta.processo.dto.Comprador;

public class CompradorProvider implements IDataProvider<Comprador> {

	private static final long serialVersionUID = 1L;
	
	@Inject private Compradores compradores;
	private String filtro;
	
	public CompradorProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends Comprador> iterator(long first, long count) {
		if (this.filtro == null) {
		    return compradores.findCompradoreS(first, count).iterator();
		}else {
			return compradores.findCompradoreS(first, count, this.filtro).iterator();
		}
	}
	
	@Override
	public long size() {
		if (this.filtro == null) {
		     return compradores.findCompradoresSize();
		}else {
			return compradores.findCompradoresSize(this.filtro);
		}
	}

	@Override
	public IModel<Comprador> model(final Comprador comprador) {
		return new LoadableDetachableModel<Comprador>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Comprador load() {
				return comprador;
			}
		};
	}
	
	public void clear() {
		if (this.filtro!=null) {
			this.filtro = null;
		}
	}


	public void putParameter(final String filtro) {
		this.filtro = filtro;
	}


}
