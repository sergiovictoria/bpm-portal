package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.dto.ProcessCase;


/**
 * 
 * @author Sérgio da Victória
 *
 */
public class ProcessCaseProvider extends SortableDataProvider<ProcessCase, String> {

	private static final long serialVersionUID = 1L;

	public ProcessCaseProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
		
	@Inject
	private TransacaoMongo transacaoMongo;
	private String textSearch;
	private java.util.Date firstDate;
	private java.util.Date lastDate;
	
	@Override
	public Iterator<? extends ProcessCase> iterator(long first, long count) {
		if(this.textSearch != null && !this.textSearch.trim().isEmpty()){
			return (Iterator<? extends ProcessCase>) transacaoMongo.getRecords(this.textSearch, first, count, this.firstDate, this.lastDate, ProcessCase.class).iterator();
		}else{
			return (Iterator<? extends ProcessCase>) transacaoMongo.getRecords(first, count, this.firstDate, this.lastDate, ProcessCase.class).iterator();
		}
	}

	@Override
	public long size() {
		if(this.textSearch != null && !this.textSearch.trim().isEmpty()){
			return transacaoMongo.size(this.textSearch, this.firstDate, this.lastDate, ProcessCase.class);
		}else{
			return transacaoMongo.size(this.firstDate, this.lastDate, ProcessCase.class);
		}		
	}

	@Override
	public IModel<ProcessCase> model(final ProcessCase processCase) {
		return new LoadableDetachableModel<ProcessCase>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected ProcessCase load() {
				return processCase;
			}
		};
	}
	
	public void putParameter(final String textSearch, java.util.Date firstDate, java.util.Date lastDate) {
		this.textSearch = textSearch;
		this.firstDate = firstDate;
		this.lastDate = lastDate;
	}
}
