package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.Transportadoras;
import br.com.seta.processo.dto.Transportadora;
import br.com.seta.processo.utils.OderProvider;


public class TransportadoraProvider extends SortableDataProvider<Transportadora, String> {

	private static final long serialVersionUID = 1L;
	@Inject private Transportadoras transportadoras;  
	private String nomerazao = null;

	public TransportadoraProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	

	@Override
	public Iterator<? extends Transportadora> iterator(long first, long count) {
		if (this.nomerazao==null) {
			return transportadoras.getTransportadoraS(addOrder(), first, count).iterator();
		}else {
			return transportadoras.getTransportadoraSLike(addOrder(), this.nomerazao, first, count).iterator();
		}
	}

	@Override
	public long size() {
		if (this.nomerazao==null) {
		  return transportadoras.size();
		}else {
			return transportadoras.sizeLike(this.nomerazao);
		}
	}

	@Override
	public IModel<Transportadora> model(final Transportadora transportadora) {
		return new LoadableDetachableModel<Transportadora>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Transportadora load() {
				return transportadora;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex = OderProvider.NomeRazaoAsc.getValue();

		if (sort==null) {
			return  OderProvider.NomeRazaoAsc.getValue();
		}	

		if (sort.getProperty().equals("NomeRazao"))  {
			return getSort().isAscending() ? OderProvider.NomeRazaoAsc.getValue() : OderProvider.NomeRazaoDesc.getValue();
		}

		if (sort.getProperty().equals("SeqPessoa"))  {
			return getSort().isAscending() ? OderProvider.SeqPessoaAsc.getValue() : OderProvider.SeqPessoaDesc.getValue();
		}

		return returnIndex;


	}


	public void clear() {
		if (this.nomerazao!=null) {
			this.nomerazao = null;
		}
	}


	public void putParameter(final String nomerazao) {
		this.nomerazao = nomerazao;
	}


}
