package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.MafFonecedores;
import br.com.seta.processo.dto.MafFornecedor;
import br.com.seta.processo.utils.OderProvider;


public class MafFornecedorProvider extends SortableDataProvider<MafFornecedor, String> {

	private static final long serialVersionUID = 1L;
	@Inject private MafFonecedores fornecedoresBean;  
	
	private String nomerazao = null;
	private BigDecimal seqfornecedor;
	

	public MafFornecedorProvider( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	

	@Override
	public Iterator<? extends MafFornecedor> iterator(long first, long count) {
		return fornecedoresBean.findAll(first, count, nomerazao, seqfornecedor).iterator();
		
	}

	@Override
	public long size() {
		return fornecedoresBean.size(nomerazao, seqfornecedor);
	}

	@Override
	public IModel<MafFornecedor> model(final MafFornecedor fornecedor) {
		return new LoadableDetachableModel<MafFornecedor>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected MafFornecedor load() {
				return fornecedor;
			}
		};
	}


	public String addOrder() {

		SortParam<?> sort = getSort();

		String returnIndex =  OderProvider.ModeloAscDescricao.getValue();

		if (sort==null) {
			return  OderProvider.ModeloAscDescricao.getValue();
		}	

		if (sort.getProperty().equals("modeloDescricao"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscDescricao.getValue() : OderProvider.ModeloDesDescricao.getValue();
		}

		if (sort.getProperty().equals("modeloCodigo"))  {
			return getSort().isAscending() ? OderProvider.ModeloAscCodigo.getValue() : OderProvider.ModeloDesCodigo.getValue();
		}

		return returnIndex;


	}


	public void clear() {
		if (this.nomerazao!=null) {
			this.nomerazao = null;
		}
		
		if(seqfornecedor != null)
			seqfornecedor = null;
	}


	public void putParameter(final String nomerazao, BigDecimal seqfornecedor) {
		this.nomerazao = nomerazao;
		this.seqfornecedor = seqfornecedor;
	}


}
