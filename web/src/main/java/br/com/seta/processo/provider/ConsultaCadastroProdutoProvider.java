package br.com.seta.processo.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.dao.FiltroCadastroProdutos;
import br.com.seta.processo.consultas.DadosCadastroProduto;
import br.com.seta.processo.consultas.interfaces.ConsultaCadastroDeProdutosService;

public class ConsultaCadastroProdutoProvider extends SortableDataProvider<DadosCadastroProduto, Comparator<DadosCadastroProduto>> {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private ConsultaCadastroDeProdutosService consultaCadProdService;
	
	private FiltroCadastroProdutos filtro = new FiltroCadastroProdutos();
	
	public ConsultaCadastroProdutoProvider(){
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public Iterator<? extends DadosCadastroProduto> iterator(long first,long count) {
		Integer primeiroRegistro = new Long(first).intValue();
		Integer quantRegistros = new Long(count).intValue();
		List<DadosCadastroProduto> dadosCadastroProduto = 
				consultaCadProdService.buscaCadastrosDeProduto(this.filtro, primeiroRegistro, quantRegistros);
		ordena(dadosCadastroProduto);
		return dadosCadastroProduto.iterator();
	}

	@Override
	public long size() {
		return consultaCadProdService.quantidadeTotalDeCadastros(this.filtro);
	}

	@Override
	public IModel<DadosCadastroProduto> model(final DadosCadastroProduto object) {
		return new LoadableDetachableModel<DadosCadastroProduto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected DadosCadastroProduto load() {
				return object;
			}
		};
	}
	
	public void setFiltro(FiltroCadastroProdutos filtro){
		this.filtro = filtro;
	}
	
	private void ordena(List<DadosCadastroProduto> dados){
		SortParam<Comparator<DadosCadastroProduto>> sort = getSort();
		
		if(sort == null)
			return;
		
		Comparator<DadosCadastroProduto> comparator = sort.getProperty();
		
		if(getSort().isAscending()){
			comparator = Collections.reverseOrder(comparator);
		}
		
		Collections.sort(dados, comparator);
	}

}
