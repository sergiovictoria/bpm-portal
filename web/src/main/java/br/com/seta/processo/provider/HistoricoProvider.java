package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import br.com.seta.processo.dto.Historico;


public class HistoricoProvider extends SortableDataProvider<Historico, String> {

	private static final long serialVersionUID = 1L;
	private List<Historico> historicoFornecedors = Collections.synchronizedList(new ArrayList<Historico>());
	

	public HistoricoProvider(List<Historico> historicos) {
		this.historicoFornecedors = historicos;
		getIndex(getSort());
	}

	@Override
	public Iterator<? extends Historico> iterator(long first, long count) {
		getIndex(getSort());
		return this.historicoFornecedors.subList((int)first, (int)(first + count)).iterator();
	}

	@Override
	public long size() {
		return this.historicoFornecedors.size();
	}

	@Override
	public IModel<Historico> model(final Historico historicoFornecedor) {
		return new LoadableDetachableModel<Historico>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Historico load() {
				return historicoFornecedor;
			}
		};
	}

	public void getIndex(SortParam<?> sort) {
		if (sort == null) {
			    Collections.sort(this.historicoFornecedors, Historico.Comparators._MOTIVO);
		}else 	if (sort.getProperty().equals("DisplayMotivo"))  {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, Historico.Comparators._MOTIVO);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(Historico.Comparators._MOTIVO));
			}
		}else if (sort.getProperty().equals("DisplayStatus")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, Historico.Comparators._STATUS);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(Historico.Comparators._STATUS));
			}
		}else if (sort.getProperty().equals("DisplayNome")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, Historico.Comparators._NOME);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(Historico.Comparators._NOME));
			}
		}else if(sort.getProperty().equals("displayData")){
			if(sort.isAscending()){
				Collections.sort(this.historicoFornecedors, Historico.Comparators.DATA);
			}else{
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(Historico.Comparators.DATA));
			}
		}		

	}
}
