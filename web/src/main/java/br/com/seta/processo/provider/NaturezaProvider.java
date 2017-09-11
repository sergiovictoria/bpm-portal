package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.NaturezaDespesas;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.dto.OrRequisicao;


public class NaturezaProvider implements IDataProvider<NaturezaDespesa> { 
	
	private static final long serialVersionUID = 1L;
	
	private String historico = null;
	private OrRequisicao requisicao;
	@Inject private NaturezaDespesas naturezaDespesaService;
	
	public NaturezaProvider(OrRequisicao requisicao){
		CdiContainer.get().getNonContextualManager().inject(this);
		this.requisicao = requisicao;
	}	

	@Override
	public void detach() {
	}
	
	@Override
	public Iterator<? extends NaturezaDespesa> iterator(long first, long count) {
		if (this.historico == null) {
			return naturezaDespesaService.findNaturezaDespesaS(getNroEmpresa(), first, count).iterator();
		}else {
			return naturezaDespesaService.findNaturezaDespesaS(getNroEmpresa(), this.historico, first, count).iterator();
		}
	}

	@Override
	public long size() {
		if (this.historico == null) {
			return naturezaDespesaService.findNaturezaDespesaSize(getNroEmpresa());
		}else {
			return naturezaDespesaService.findNaturezaDespesaSize(getNroEmpresa(), this.historico);
		}
	}

	@Override
	public IModel<NaturezaDespesa> model(final NaturezaDespesa naturezaDespesa) {
		return new LoadableDetachableModel<NaturezaDespesa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected NaturezaDespesa load() {
				return naturezaDespesa;
			}
		};
	}
	
	public void setFiltro(String historico, OrRequisicao requisicao){
		this.historico = historico;
		this.requisicao = requisicao;
	}
	
	public void setFiltro(String historico){
		this.historico = historico;
	}

	private BigDecimal getNroEmpresa(){
		short nroempresa = this.requisicao.getNroempresa();		
		return new BigDecimal(nroempresa);
	}
}
