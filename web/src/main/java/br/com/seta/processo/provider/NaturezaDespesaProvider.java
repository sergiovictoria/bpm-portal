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
import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.FontePagadora;

/**
 * Provider para a entidade CtHistorico (Natureza de Despesa)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class NaturezaDespesaProvider extends SortableDataProvider<CategoriaNaturezaDespesa, String> {
	private static final long serialVersionUID = 1L;
	
	private List<CategoriaNaturezaDespesa> naturezasDespesas = new ArrayList<CategoriaNaturezaDespesa>();
	private FontePagadora fontePagadora;

	@Inject
	private EmpresaFontePagadoraService empresasFontesPagadoras;
	
	public NaturezaDespesaProvider(FontePagadora fontePagadora) {
		CdiContainer.get().getNonContextualManager().inject(this);
		
		if (fontePagadora != null)
			this.naturezasDespesas = empresasFontesPagadoras.listaNaturezasDespesa(fontePagadora);
	}
	
	@Override
	public Iterator<? extends CategoriaNaturezaDespesa> iterator(long first, long count) {
		return this.naturezasDespesas.subList((int)first, (int)(first + count)).iterator();	
	}

	@Override
	public long size() {
		return this.naturezasDespesas.size();
	}

	@Override
	public IModel<CategoriaNaturezaDespesa> model(final CategoriaNaturezaDespesa object) {
		return new LoadableDetachableModel<CategoriaNaturezaDespesa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected CategoriaNaturezaDespesa load() {
				return object;
			}
		};
	}
	
	/**
	 * Ajusta a empresa para a qual será recuperada as naturezas de despesas
	 * 
	 * @param empresa
	 */
	public void setFontePagadora(FontePagadora fontePagadora){
		this.naturezasDespesas = empresasFontesPagadoras.listaNaturezasDespesa(fontePagadora);
		this.fontePagadora = fontePagadora;
	}
	
	/**
	 * Ajusta a descricao da natureza de despesa que servirá de critério de busca
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao){
		if(descricao == null){
			descricao = "";
		}
		
		this.naturezasDespesas = empresasFontesPagadoras.listaNaturezasDespesa(this.fontePagadora, descricao);		
	}
	
	/**
	 * Ajusta a empresa e a descrição que servirão de critério de busca para as naturezas de despesas 
	 * 
	 * @param fontePagadora
	 * @param descricao
	 */
	public void setFiltros(FontePagadora fontePagadora, String descricao){
		this.naturezasDespesas = empresasFontesPagadoras.listaNaturezasDespesa(fontePagadora, descricao);
		this.fontePagadora = fontePagadora;		
	}
	
	public void desabilitaTodos(){
		for(CategoriaNaturezaDespesa naturezaDespesa : naturezasDespesas){
			naturezaDespesa.setHabilitado(false);
		}
	}
	
	public List<CategoriaNaturezaDespesa> getNaturezasDespesas(){
		return Collections.unmodifiableList(this.naturezasDespesas);
	}
}
