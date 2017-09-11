package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.HistoricoFornecedor;


public class HistoricoFonecedorProvider extends SortableDataProvider<HistoricoFornecedor, String> {

	private static Logger logger = Logger.getLogger(HistoricoFonecedorProvider.class);
	private static final long serialVersionUID = 1L;
	private List<HistoricoFornecedor> historicoFornecedors = Collections.synchronizedList(new ArrayList<HistoricoFornecedor>());
	

	public HistoricoFonecedorProvider(List<HistoricoFornecedor> historicoFornecedors) {
		this.historicoFornecedors = historicoFornecedors;
		getIndex(getSort());
		logger.info("Provider criado com sucesso [ "+this.historicoFornecedors.size()+ " ]");
	}

	@Override
	public Iterator<? extends HistoricoFornecedor> iterator(long first,	long count) {
		getIndex(getSort());
		return this.historicoFornecedors.subList((int)first, (int)(first + count)).iterator();
	}

	@Override
	public long size() {
		return this.historicoFornecedors.size();
	}

	@Override
	public IModel<HistoricoFornecedor> model(final HistoricoFornecedor historicoFornecedor) {
		return new LoadableDetachableModel<HistoricoFornecedor>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected HistoricoFornecedor load() {
				return historicoFornecedor;
			}
		};
	}

	public void getIndex(SortParam<?> sort) {
		if (sort == null) {
			    Collections.sort(this.historicoFornecedors, HistoricoFornecedor.Comparators._MOTIVO);
		}else 	if (sort.getProperty().equals("DisplayMotivo"))  {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, HistoricoFornecedor.Comparators._MOTIVO);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(HistoricoFornecedor.Comparators._MOTIVO));
			}
		}else if (sort.getProperty().equals("DisplayStatus")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, HistoricoFornecedor.Comparators._STATUS);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(HistoricoFornecedor.Comparators._STATUS));
			}
		}else if (sort.getProperty().equals("DisplayNome")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoFornecedors, HistoricoFornecedor.Comparators._NOME);
			}else {
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(HistoricoFornecedor.Comparators._NOME));
			}
		}else if(sort.getProperty().equals("displayData")){
			if(sort.isAscending()){
				Collections.sort(this.historicoFornecedors, HistoricoFornecedor.Comparators.DATA);
			}else{
				Collections.sort(this.historicoFornecedors, Collections.reverseOrder(HistoricoFornecedor.Comparators.DATA));
			}
		}		

	}
}
