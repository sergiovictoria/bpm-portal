package br.com.seta.processo.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.dao.interfaces.InstanciaProcessoDao;
import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AtividadesEncerradasProvider extends SortableDataProvider<InstanciaProcesso, Comparator<InstanciaProcesso>>{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private InstanciaProcessoDao dao; 
	private String textSearch;
	private Date inicio;
	private Date fim;
	
	public AtividadesEncerradasProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public Iterator<? extends InstanciaProcesso> iterator(long first, long count) {
		 List<InstanciaProcesso> instancias = this.dao.buscaInstanciasEncerradas(new Long(first).intValue(), new Long(count).intValue(), inicio, fim, textSearch);
		 ordena(instancias);
		 return instancias.iterator();
	}

	@Override
	public long size() {
		return this.dao.quantidadeInstanciasEncerradas(inicio, fim, textSearch);
	}

	@Override
	public IModel<InstanciaProcesso> model(final InstanciaProcesso object) {
		return new LoadableDetachableModel<InstanciaProcesso>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected InstanciaProcesso load() {
				return object;
			}
		};
	}
	
	public void setFiltros(String textSearch, Date inicio, Date fim){
		this.textSearch = textSearch;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	private void ordena(List<InstanciaProcesso> dados){
		SortParam<Comparator<InstanciaProcesso>> sort = getSort();
		
		if(sort == null)
			return;
		
		Comparator<InstanciaProcesso> comparator = sort.getProperty();
		
		if(getSort().isAscending()){
			comparator = Collections.reverseOrder(comparator);
		}
		
		Collections.sort(dados, comparator);
	}

}
