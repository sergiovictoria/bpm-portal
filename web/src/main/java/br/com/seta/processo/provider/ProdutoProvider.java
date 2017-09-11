package br.com.seta.processo.provider;

import java.math.BigDecimal;
import java.util.Iterator;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.Produtos;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.entity.OrvProdutotribEntity;

public class ProdutoProvider implements IDataProvider<OrvProdutotribEntity> {
	
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private Produtos produtos;  
	
	private String filtro = null;
	private OrRequisicao requisicao;

	public ProdutoProvider(OrRequisicao requisicao){
		CdiContainer.get().getNonContextualManager().inject(this);
		this.requisicao = requisicao;
	}
	
	@Override
	public void detach() {
		
    }

	@Override
	public Iterator<? extends OrvProdutotribEntity> iterator(long first, long count) {
		BigDecimal nroempresa = new BigDecimal(requisicao.getNroempresa());
		BigDecimal seqfornecedor = requisicao.getSeqpessoa() == null ? BigDecimal.ZERO : new BigDecimal(requisicao.getSeqpessoa());		
		
		if (this.filtro == null || this.filtro.isEmpty()) {
			return produtos.findProdutos(nroempresa, seqfornecedor, first, count).iterator();
		}else {
			return produtos.findProdutos(nroempresa, seqfornecedor, this.filtro, first, count).iterator();
		}
	}

	@Override
	public long size() {
		BigDecimal nroempresa = new BigDecimal(requisicao.getNroempresa());
		BigDecimal seqfornecedor = requisicao.getSeqpessoa() == null ? BigDecimal.ZERO : new BigDecimal(requisicao.getSeqpessoa());	
		
		if (this.filtro==null) {
			return produtos.findPodutosSize(nroempresa, seqfornecedor);
		}else {
			return produtos.findPodutosSize(nroempresa, seqfornecedor, this.filtro);
		}
	}	

	@Override
	public IModel<OrvProdutotribEntity> model(final OrvProdutotribEntity produtotribEntity) {
		return new LoadableDetachableModel<OrvProdutotribEntity>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected OrvProdutotribEntity load() {
				return produtotribEntity;
			}
		};
	}	
	
	public void clear(){
		this.requisicao = null;
	}
	
	public void putParameter(OrRequisicao requisicao){
		this.requisicao = requisicao;
	}
	
	public void putParameter(String filtro){
		this.filtro = filtro;
	}
	
	public void putParameter(OrRequisicao requisicao, String filtro){
		this.requisicao = requisicao;
		this.filtro = filtro;
	}

}
