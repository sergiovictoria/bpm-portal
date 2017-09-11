package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.entity.GePessoaEntity;

public class GePessoaFornecedorProvider extends SortableDataProvider<GePessoaEntity, String> {

	private static final long serialVersionUID = 1L;
	private String filtro = "";

	@Inject	private GePessoas gePessoas;

	public GePessoaFornecedorProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}


	@Override
	public Iterator<? extends GePessoaEntity> iterator(long first, long count) {
		if (this.filtro == null) {
			return gePessoas.find(first, count).iterator();
		}else {
			return gePessoas.find(this.filtro, first, count).iterator();	
		}
	}

	@Override
	public long size() {
		if (this.filtro == null) {
			return gePessoas.size();
		}else {
			return gePessoas.size(filtro);	
		}		
	}

	public boolean isEmpty(){
		if (size()==0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public IModel<GePessoaEntity> model(final GePessoaEntity gePessoaEntity) {
		return new LoadableDetachableModel<GePessoaEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected GePessoaEntity load() {
				return gePessoaEntity;
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
