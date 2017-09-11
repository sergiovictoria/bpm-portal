package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.NaturezaDespesas;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.utils.OderProvider;


public class NaturezaSortProvider extends SortableDataProvider<NaturezaDespesa, String> { 
	private static final long serialVersionUID = 1L;
	
	private String historico = null;
	private BigDecimal numeroEmpresa;
	@Inject 
	private NaturezaDespesas naturezaDespesaService;  

	public NaturezaSortProvider(BigDecimal numeroEmpresa) {
		CdiContainer.get().getNonContextualManager().inject(this);
		this.numeroEmpresa = numeroEmpresa;
	}

	@Override
	public Iterator<? extends NaturezaDespesa> iterator(long first, long count) {
		if (this.historico==null) {
			return naturezaDespesaService.getNaturezaDespesaS(this.numeroEmpresa,addOrder(), first, count).iterator();
		}else {
			return naturezaDespesaService.getNaturezaDespesaSLike(this.numeroEmpresa,addOrder(), this.historico, first, count).iterator();
		}
	}

	@Override
	public long size() {
		if (this.historico==null) {
			return naturezaDespesaService.size(this.numeroEmpresa);
		}else {
			return naturezaDespesaService.sizeLike(this.numeroEmpresa,this.historico);
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

	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex = OderProvider.NatrurezaAscDescricao.getValue();

		if (sort==null) {
			return  OderProvider.NatrurezaAscDescricao.getValue();
		}	

		if (sort.getProperty().equals("naturezaDescricao"))  {
			return getSort().isAscending() ? OderProvider.NatrurezaAscDescricao.getValue() : OderProvider.NatrurezaDesDescricao.getValue();
		}

		if (sort.getProperty().equals("naturezaCodigo"))  {
			return getSort().isAscending() ? OderProvider.NatrurezaAscCodigo.getValue(): OderProvider.NatrurezaDesCodigo.getValue();
		}

		return returnIndex;
	}	

	public void putParameter(final String historico) {
		clear();
		this.historico = historico;
	}	


	public void clear() {
		if (this.historico!=null) {
			this.historico = null;
		}
	}



}
