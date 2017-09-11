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

public class AnexosProvider extends SortableDataProvider<Documento, Comparable<Documento>>{
	private static final long serialVersionUID = 1L;

	@Inject
	private DocumentoDao dao;
	
	private String descricao = null;
	private Long caseId = null;
	
	public AnexosProvider(Long caseId, String descricao){
		CdiContainer.get().getNonContextualManager().inject(this);		
		this.caseId = caseId;
		this.descricao = descricao;
	}
	
	@Override
	public Iterator<? extends Documento> iterator(long first, long count) {
		List<Documento> documentos = dao.buscaMetadadosDocumentos(caseId, descricao, null, null);
		return documentos.iterator();
	}

	@Override
	public long size() {
		return dao.quantidadeDocumentos(caseId, descricao);
	}
	
	public void setDescricao(String descricao){
		this.descricao = descricao;
	}
	
	public void setCaseId(Long caseId){
		this.caseId = caseId;
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

}
