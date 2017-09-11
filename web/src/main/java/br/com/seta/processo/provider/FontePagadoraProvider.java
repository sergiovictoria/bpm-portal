package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.EmpresaFontePagadoraService;
import br.com.seta.processo.entity.FontePagadora;

/**
 * Provider para a tabela (table) de fontes pagadoras
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FontePagadoraProvider extends SortableDataProvider<FontePagadora, String> {
	private static final long serialVersionUID = 1L;
	
	private List<FontePagadora> fontesPagadoras;	
	
	@Inject
	EmpresaFontePagadoraService mapeamentoFontePagadora;
	
	public FontePagadoraProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.fontesPagadoras = mapeamentoFontePagadora.listaFontesPagadoras();
	}

	@Override
	public Iterator<? extends FontePagadora> iterator(long first, long count) {
		return this.fontesPagadoras.subList((int)first, (int)(first + count)).iterator();
	}

	@Override
	public long size() {
		return fontesPagadoras.size();		
	}

	@Override
	public IModel<FontePagadora> model(final FontePagadora object) {
		return new LoadableDetachableModel<FontePagadora>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected FontePagadora load() {
				return object;
			}
		};
	}
	
	public void setNomeReduzido(String nomeReduzido){	
		if (nomeReduzido == null) {
			this.fontesPagadoras = mapeamentoFontePagadora.listaFontesPagadoras();
		} else {
			this.fontesPagadoras = mapeamentoFontePagadora.listaFontesPagadoras(nomeReduzido);

		}		
	}
	
	public List<FontePagadora> getFontesPagadoras(){
		return Collections.unmodifiableList(this.fontesPagadoras);		
	}
	
	
	public List<FontePagadora> getFontePagadorasHabilitadas(){
		List<FontePagadora> habilitadas = new ArrayList<FontePagadora>();
		
		for(FontePagadora fonte : this.fontesPagadoras){
			if(fonte.isHabilitado()){
				habilitadas.add(fonte);
			}
		}	
		
		return Collections.unmodifiableList(habilitadas);
	}
}
