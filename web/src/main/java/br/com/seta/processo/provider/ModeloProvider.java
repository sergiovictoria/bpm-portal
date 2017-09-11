package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.ModeloDocumentos;
import br.com.seta.processo.dto.ModeloDocumento;
import br.com.seta.processo.utils.OderProvider;


public class ModeloProvider extends SortableDataProvider<ModeloDocumento, String> {

	private static final long serialVersionUID = 1L;
	@Inject private ModeloDocumentos modeloDocumentos;  
	private String descricao = null;
	

	public ModeloProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	

	@Override
	public Iterator<? extends ModeloDocumento> iterator(long first, long count) {
		if (this.descricao==null) {
			return modeloDocumentos.getModeloS(addOrder(),first, count).iterator();
		}else {
			return modeloDocumentos.getModeloSLike(addOrder(), this.descricao, first, count).iterator();
		}
	}

	@Override
	public long size() {
		if (this.descricao==null) {
		  return modeloDocumentos.size();
		}else {
			return modeloDocumentos.sizeLike(descricao);
		}
	}

	@Override
	public IModel<ModeloDocumento> model(final ModeloDocumento modeloDocumento) {
		return new LoadableDetachableModel<ModeloDocumento>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected ModeloDocumento load() {
				return modeloDocumento;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex =  OderProvider.ModeloAscDescricao.getValue();

		if (sort==null) {
			return  OderProvider.ModeloAscDescricao.getValue();
		}	

		if (sort.getProperty().equals("modeloDescricao"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscDescricao.getValue() : OderProvider.ModeloDesDescricao.getValue();
		}

		if (sort.getProperty().equals("modeloCodigo"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscCodigo.getValue() : OderProvider.ModeloDesCodigo.getValue();
		}

		return returnIndex;


	}


	public void clear() {
		if (this.descricao!=null) {
			this.descricao = null;
		}
	}


	public void putParameter(final String nomerazao) {
		this.descricao = nomerazao;
	}


}
