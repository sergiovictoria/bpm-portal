package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.MapInstanciaProcesso;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.service.interfaces.RecebimentoService;

public class RecebimentosDetalhesProvider implements IDataProvider<OrReqitensdespesa> {
	private static final long serialVersionUID = 1L;
	
	@Inject private MapInstanciaProcesso mapInstanciaProcesso;

	
	private List<OrReqitensdespesa> despesas = new ArrayList<OrReqitensdespesa>();

	/**
	 * Constructor
	 */
	public RecebimentosDetalhesProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}

	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends OrReqitensdespesa> iterator(long first, long count) {
		return this.despesas.subList((int) first, (int) (first + count)).iterator();
	}

	@Override
	public long size() {
		return this.despesas.size();
	}

	@Override
	public IModel<OrReqitensdespesa> model(final OrReqitensdespesa object) {
		return new LoadableDetachableModel<OrReqitensdespesa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected OrReqitensdespesa load() {
				return object;
			}
		};
	}
	
	public void setListaDespesas(Set<OrReqitensdespesa> despesas) {
		this.despesas.addAll(despesas);
	}
	
	public void configureProvider(Long seqrequisicao) {
		try {
			this.despesas.clear();
			OrRequisicao requisicao = mapInstanciaProcesso.listaOrRequisicaoRecebimentoOne(seqrequisicao);
			if(null != requisicao) {
				this.despesas.addAll(requisicao.getOrReqitensdespesas());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
