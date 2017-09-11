package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.Empresas;
import br.com.seta.processo.dto.Empresa;


public class EmpresaProvider implements IDataProvider<Empresa> {

	private static final long serialVersionUID = 1L;

	@Inject private Empresas empresas;
	private String filtro;

	public EmpresaProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends Empresa> iterator(long first, long count) {
		if (this.filtro == null) {
			return empresas.findEmpresaS(first, count).iterator();
		}else {
			return empresas.findEmpresaS(first, count, this.filtro).iterator();
		}
	}

	@Override
	public long size() {
		if (this.filtro == null) {
			return empresas.findEmpresaSize();
		}else {
			return empresas.findEmpresaSize(this.filtro);
		}
	}

	@Override
	public IModel<Empresa> model(final Empresa empresa) {
		return new LoadableDetachableModel<Empresa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Empresa load() {
				return empresa;
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
