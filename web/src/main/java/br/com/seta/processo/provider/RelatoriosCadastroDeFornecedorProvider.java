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

import br.com.seta.processo.consultas.DadosCadFornecedor;
import br.com.seta.processo.consultas.interfaces.ConsultaCadastroDeFornecedorService;

public class RelatoriosCadastroDeFornecedorProvider extends SortableDataProvider<DadosCadFornecedor, Comparator<DadosCadFornecedor>>{
	private static final long serialVersionUID = 1L;
	
	private Long caseId;
	private String codConsinco, razaoSocialOuNome, cpfCnpj, comprador, status;
	private Date inicio, fim;
	
	@Inject
	private ConsultaCadastroDeFornecedorService relCadFornecedor;
	
	public RelatoriosCadastroDeFornecedorProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public Iterator<? extends DadosCadFornecedor> iterator(long first,	long count) {
		int primeiro = new Long(first).intValue();
		int quantidade = new Long(count).intValue();
		List<DadosCadFornecedor> dadosRelCadFornecedor = 
				relCadFornecedor.buscaInstanciasDeCadastroFornecedor(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status, primeiro, quantidade);
		ordena(dadosRelCadFornecedor);
		return dadosRelCadFornecedor.iterator();
	}

	@Override
	public long size() {
		return relCadFornecedor.quantidadeTotalRegistros(caseId, codConsinco, razaoSocialOuNome, cpfCnpj, comprador, inicio, fim, status);
	}

	@Override
	public IModel<DadosCadFornecedor> model(
			final DadosCadFornecedor object) {
		return new LoadableDetachableModel<DadosCadFornecedor>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected DadosCadFornecedor load() {
				return object;
			}
		};
	}
	
	public void setFiltros(Long caseId,
			String codConsinco, String razaoSocialOuNome, String cpfCnpj,
			String comprador, Date inicio, Date fim, String status){
		
		this.caseId = caseId;
		this.codConsinco = codConsinco;
		this.razaoSocialOuNome = razaoSocialOuNome; 
		this.cpfCnpj = cpfCnpj; 
		this.comprador = comprador;
		this.status = status;
		this.inicio = inicio;
		this.fim = fim;
	}
	
	private void ordena(List<DadosCadFornecedor> dados){
		SortParam<Comparator<DadosCadFornecedor>> sort = getSort();
		
		if(sort == null)
			return;
		
		Comparator<DadosCadFornecedor> comparator = sort.getProperty();
		
		if(getSort().isAscending()){
			comparator = Collections.reverseOrder(comparator);
		}
		
		Collections.sort(dados, comparator);
	}
}
