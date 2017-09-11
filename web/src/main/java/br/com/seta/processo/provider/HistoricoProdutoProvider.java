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

import br.com.seta.processo.dto.HistoricoProduto;


public class HistoricoProdutoProvider extends SortableDataProvider<HistoricoProduto, String> {

	private static Logger logger = Logger.getLogger(HistoricoProdutoProvider.class);
	private static final long serialVersionUID = 1L;
	private List<HistoricoProduto> historicoProdutos = Collections.synchronizedList(new ArrayList<HistoricoProduto>());
	

	public HistoricoProdutoProvider(List<HistoricoProduto> historicoProdutos) {
		this.historicoProdutos = historicoProdutos;
		getIndex(getSort());
		logger.info("Provider criado com sucesso [ "+this.historicoProdutos.size()+ " ]");
	}

	@Override
	public Iterator<? extends HistoricoProduto> iterator(long first,	long count) {
		getIndex(getSort());
		return this.historicoProdutos.subList((int)first, (int)(first + count)).iterator();
	}

	@Override
	public long size() {
		return this.historicoProdutos.size();
	}

	@Override
	public IModel<HistoricoProduto> model(final HistoricoProduto historicoProduto) {
		return new LoadableDetachableModel<HistoricoProduto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected HistoricoProduto load() {
				return historicoProduto;
			}
		};
	}

	public void getIndex(SortParam<?> sort) {
		if (sort == null) {
			    Collections.sort(this.historicoProdutos, HistoricoProduto.Comparators.DATA);
		}else 	if (sort.getProperty().equals("DisplayMotivo"))  {
			if (sort.isAscending()) {
				Collections.sort(this.historicoProdutos, HistoricoProduto.Comparators._MOTIVO);
			}else {
				Collections.sort(this.historicoProdutos, Collections.reverseOrder(HistoricoProduto.Comparators._MOTIVO));
			}
		}else if (sort.getProperty().equals("DisplayStatus")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoProdutos, HistoricoProduto.Comparators._STATUS);
			}else {
				Collections.sort(this.historicoProdutos, Collections.reverseOrder(HistoricoProduto.Comparators._STATUS));
			}
		}else if (sort.getProperty().equals("DisplayNome")) {
			if (sort.isAscending()) {
				Collections.sort(this.historicoProdutos, HistoricoProduto.Comparators._NOME);
			}else {
				Collections.sort(this.historicoProdutos, Collections.reverseOrder(HistoricoProduto.Comparators._NOME));
			}
		}else if(sort.getProperty().equals("displayData")){
			if(sort.isAscending()){
				Collections.sort(this.historicoProdutos, HistoricoProduto.Comparators.DATA);
			}else{
				Collections.sort(this.historicoProdutos, Collections.reverseOrder(HistoricoProduto.Comparators.DATA));
			}
		}		

	}
}
