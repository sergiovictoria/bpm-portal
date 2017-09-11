package br.com.seta.processo.provider;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.dto.Documento;

public class QueryAnexosProvider extends SortableDataProvider<Documento, Comparable<Documento>>{
	private static final long serialVersionUID = 1L;
	
	private String query;
	
	@Inject
	private DocumentoDao dao;	
	
	public QueryAnexosProvider(String query){
		CdiContainer.get().getNonContextualManager().inject(this);
		this.query = query;
	}

	@Override
	public Iterator<? extends Documento> iterator(long first, long count) {
		List<Documento> metadadosDoc = dao.buscaMetadadosDocumento(this.query, first, count);
		return metadadosDoc.iterator();		
	}

	@Override
	public long size() {
		return dao.quantidadeDocumentos(this.query);
	}
	
	@Override
	public IModel<Documento> model(final Documento object) {
		return new LoadableDetachableModel<Documento>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected Documento load() {
				return object;
			}
		};
	}
	
	public void setQuery(String query){
		this.query = query;
	}

}
