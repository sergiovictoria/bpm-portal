package br.com.seta.processo.suprimentos.componentespagina.secoes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.pagecomponentes.OrderCssProvider;
import static br.com.seta.processo.comparators.HistoricoComparators.POR_STATUS;
import static br.com.seta.processo.comparators.HistoricoComparators.POR_NOME;
import static br.com.seta.processo.comparators.HistoricoComparators.POR_MOTIVO;
import static br.com.seta.processo.comparators.HistoricoComparators.POR_DATA;

/**
 * Renderiza uma seção que contém os históricos do processo (List of Historico)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SecaoHistorico extends Panel {
	private static final long serialVersionUID = 1L;

	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	private HistoricoProvider provider;
	private HistoricoDataView historicoDataView;
	
	public SecaoHistorico(String id, List<Historico> historicos) {
		super(id);
		
		setOutputMarkupId(true);
		
		if(historicos == null)
			historicos = new ArrayList<Historico>();
		
		this.provider = new HistoricoProvider(historicos);
		this.historicoDataView = new HistoricoDataView("historicoDataView", this.provider);
		
		OrderByBorderTabelaHistorico motivoOrder = new OrderByBorderTabelaHistorico("motivoOrder", POR_MOTIVO);			
		OrderByBorderTabelaHistorico statusOrder = new OrderByBorderTabelaHistorico("statusOrder", POR_STATUS);
		OrderByBorderTabelaHistorico nomeOrder = new OrderByBorderTabelaHistorico("nomeOrder", POR_NOME); 
		OrderByBorderTabelaHistorico dataOrder = new OrderByBorderTabelaHistorico("dataOrder", POR_DATA);
		
		add(this.historicoDataView);
		add(motivoOrder, statusOrder, nomeOrder, dataOrder);
	}
	
	public SecaoHistorico(String id, List<Historico> historicos, long itemsPorPagina) {
		super(id);
		
		if(historicos == null)
			historicos = new ArrayList<Historico>();
		
		this.provider = new HistoricoProvider(historicos);
		this.historicoDataView = new HistoricoDataView("historicoDataView", this.provider, itemsPorPagina);
		
		add(this.historicoDataView);
	}
	
	private class HistoricoDataView extends DataView<Historico>{
		
		private static final long serialVersionUID = 1L;

		protected HistoricoDataView(String id, IDataProvider<Historico> dataProvider) {
			super(id, dataProvider);
		}
		
		protected HistoricoDataView(String id, IDataProvider<Historico> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		@Override
		protected void populateItem(Item<Historico> item) {
			Historico historico = (Historico) item.getDefaultModelObject();
			item.add(new Label("satus", historico.getStatus()));
			item.add(new Label("nome", historico.getNome()));
			item.add(new Label("data", dateFormater.format(historico.getData()) ));
			item.add(new Label("motivo", historico.getMotivo()));
			item.add(new Label("comentario", historico.getComentario()));
		}
		
	}
	
	/**
	 * OrderByBorder customizado para a tabela de histórico
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	public class OrderByBorderTabelaHistorico extends AjaxFallbackOrderByBorder{
		private static final long serialVersionUID = 1L;		
		
		@SuppressWarnings("unchecked")
		public OrderByBorderTabelaHistorico(String id, Object sortProperty) {
			super(id, sortProperty, provider, new OrderCssProvider<Comparator<Historico>>());			
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(SecaoHistorico.this);
		}
		
		@Override
		protected void onSortChanged() {
			historicoDataView.setCurrentPage(0);
		}
	}
	
	/**
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class HistoricoProvider extends SortableDataProvider<Historico, Comparator<Historico>>{
		private static final long serialVersionUID = 1L;

		private List<Historico> historicos;
		
		public HistoricoProvider(List<Historico> historicos){
			setHistoricos(historicos);
		}
		
		@Override
		public Iterator<? extends Historico> iterator(long first, long count) {
			List<Historico> pagina = historicos.subList((int)first, (int)(first + count));
			Collections.sort(pagina, getComparator());
			
			return pagina.iterator();
		}

		@Override
		public long size() {
			return historicos.size();
		}

		@Override
		public IModel<Historico> model(final Historico historico) {
			return new LoadableDetachableModel<Historico>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected Historico load() {
					return historico;
				}
			};
		}
		
		public void setHistoricos(List<Historico> historicos){
			this.historicos = Collections.synchronizedList(historicos);
		}
		
		private Comparator<Historico> getComparator(){
			SortParam<Comparator<Historico>> sort = getSort();
			
			if(sort == null)
				return Collections.reverseOrder(POR_DATA);
			
			
			Comparator<Historico> comparator = sort.getProperty();		
						
			if(sort.isAscending())
				return comparator;			
			
			return Collections.reverseOrder(comparator);
		}
		
	}

}
