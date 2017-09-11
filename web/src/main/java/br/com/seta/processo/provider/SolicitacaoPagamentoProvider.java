package br.com.seta.processo.provider;

import java.util.ArrayList;
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

import br.com.seta.processo.bean.dao.FiltroSolicitacaoPagamento;
import br.com.seta.processo.bean.dao.interfaces.InstanciaSolicitacaoPagamentoDao;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.solicitacaopagamento.DadosConsultaSolicitacaoPagto;

public class SolicitacaoPagamentoProvider extends SortableDataProvider<DadosConsultaSolicitacaoPagto, Comparator<DadosConsultaSolicitacaoPagto>>  {
	private static final long serialVersionUID = 1L;
	
	private FiltroSolicitacaoPagamento filtro = new FiltroSolicitacaoPagamento();
	
	@Inject
	private InstanciaSolicitacaoPagamentoDao instanciasSolicPagtoDao;
	
	public SolicitacaoPagamentoProvider () {		
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	public SolicitacaoPagamentoProvider (FiltroSolicitacaoPagamento filtro) {		
		this();
		this.filtro = filtro;
	}

	@Override
	public Iterator<DadosConsultaSolicitacaoPagto> iterator(long first, long count) {
		Integer primeiroRegistro = new Long(first).intValue();
		Integer quantRegistros = new Long(count).intValue();
		
		List<InstanciaProcesso> instancias = instanciasSolicPagtoDao.buscaInstancias(filtro, primeiroRegistro, quantRegistros);
		List<DadosConsultaSolicitacaoPagto> dadosConsultaSolicitacaoPagto = deInstanciasParDadosConsultaSolicitacaoPagto(instancias);
		ordena(dadosConsultaSolicitacaoPagto);
		
		return dadosConsultaSolicitacaoPagto.iterator();
	}

	@Override
	public long size() {
		return instanciasSolicPagtoDao.quantidadeTotalInstancias(filtro);
	}

	@Override
	public IModel<DadosConsultaSolicitacaoPagto> model(final DadosConsultaSolicitacaoPagto solicitacao) {
		return new LoadableDetachableModel<DadosConsultaSolicitacaoPagto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected DadosConsultaSolicitacaoPagto load() {
				return solicitacao;
			}
		};
	}

	public void setFiltro(FiltroSolicitacaoPagamento filtro){
		this.filtro = filtro;
	}

	private void ordena(List<DadosConsultaSolicitacaoPagto> dadosConsultaSolicitacaoPagto){
		SortParam<Comparator<DadosConsultaSolicitacaoPagto>> sort = getSort();
		
		if(sort == null)
			return;
		
		Comparator<DadosConsultaSolicitacaoPagto> comparator = sort.getProperty();
		
		if(sort.isAscending()){
			comparator = Collections.reverseOrder(comparator);
		}
		
		Collections.sort(dadosConsultaSolicitacaoPagto, comparator);
	}

	private List<DadosConsultaSolicitacaoPagto> deInstanciasParDadosConsultaSolicitacaoPagto(
			List<InstanciaProcesso> instancias) {
		List<DadosConsultaSolicitacaoPagto> dadosConsultaSolicitacaoPagto = new ArrayList<DadosConsultaSolicitacaoPagto>();
		
		for(InstanciaProcesso instancia : instancias){
			SolicitacaoPagamento solicitacao = (SolicitacaoPagamento) instancia.ultimaAtividade().getValorVariavel("solicitacaoPagamento");
			DadosConsultaSolicitacaoPagto dados = new DadosConsultaSolicitacaoPagto(instancia, solicitacao);
			dadosConsultaSolicitacaoPagto.add(dados);
		}
		
		return dadosConsultaSolicitacaoPagto;
	}
	
}
