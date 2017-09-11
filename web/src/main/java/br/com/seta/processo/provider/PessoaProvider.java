package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;




import br.com.seta.processo.bean.Pessoas;
import br.com.seta.processo.dto.Pessoa;
import br.com.seta.processo.utils.OderProvider;

public class PessoaProvider extends SortableDataProvider<Pessoa, String> {

	private static final long serialVersionUID = 1L;
	@Inject private Pessoas pessoas;  
	private String historico = null;
	

	public PessoaProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	
	@Override
	public Iterator<? extends Pessoa> iterator(long first, long count) {
		if (this.historico == null) {
			return pessoas.getPessoaS(addOrder(), first, count).iterator();
		}else {
			return pessoas.getPessoaSLike(addOrder(), this.historico, first, count).iterator();
		}
	}

	@Override
	public long size() {
		if (this.historico==null) {
		  return pessoas.size();
		}else {
			return pessoas.sizeLike(this.historico);
		}
	}

	@Override
	public IModel<Pessoa> model(final Pessoa pessoa) {
		return new LoadableDetachableModel<Pessoa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Pessoa load() {
				return pessoa;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex = OderProvider.PessoaAscDescricao.getValue();

		if (sort==null) {
			return  OderProvider.PessoaAscDescricao.getValue();
		}	

		if (sort.getProperty().equals("modeloDescricao"))  {
			return getSort().isAscending() ? OderProvider.PessoaAscDescricao.getValue() : OderProvider.PessoaDesDescricao.getValue();
		}

		if (sort.getProperty().equals("modeloCodigo"))  {
			return getSort().isAscending() ? OderProvider.PessoaAscCodigo.getValue() : OderProvider.PessoaDesCodigo.getValue();
		}

		return returnIndex;


	}


	public void clear() {
		if (this.historico!=null) {
			this.historico = null;
		}
	}


	public void putParameter(final String historico) {
		this.historico = historico;
	}


}
