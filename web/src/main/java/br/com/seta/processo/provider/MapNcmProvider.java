package br.com.seta.processo.provider;

import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.dto.MapNcmDto;

public class MapNcmProvider extends SortableDataProvider<MapNcmDto, String> {

	private static final long serialVersionUID = 1L;
	@Inject private MapEmbalagens acruxService;  
	
	private String ncm = null;
	
	public MapNcmProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	

	@Override
	public Iterator<? extends MapNcmDto> iterator(long first, long count) {
		return acruxService.findNcm(first, count, ncm).iterator();
		
	}

	@Override
	public long size() {
		return acruxService.sizeNcm(ncm);
	}

	@Override
	public IModel<MapNcmDto> model(final MapNcmDto ncm) {
		return new LoadableDetachableModel<MapNcmDto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected MapNcmDto load() {
				return ncm;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex =  "";

//		if (sort==null) {
//			return  OderProvider.ModeloAscDescricao.getValue();
//		}	
//
//		if (sort.getProperty().equals("modeloDescricao"))  {
//			return getSort().isAscending() ? OderProvider.ModeloAscDescricao.getValue() : OderProvider.ModeloDesDescricao.getValue();
//		}
//
//		if (sort.getProperty().equals("modeloCodigo"))  {
//			return getSort().isAscending() ? OderProvider.ModeloAscCodigo.getValue() : OderProvider.ModeloDesCodigo.getValue();
//		}

		return returnIndex;


	}


	public void clear() {
		if (this.ncm!=null)
			this.ncm = null;
	}

	public void putParameter(final String ncm) {
		this.ncm = ncm;
	}


}
