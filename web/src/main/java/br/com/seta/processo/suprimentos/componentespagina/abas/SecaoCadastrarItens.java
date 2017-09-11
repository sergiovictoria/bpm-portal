package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.bean.Produtos;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrvProdutoTributo;
import br.com.seta.processo.entity.GePessoaEntity;

public class SecaoCadastrarItens extends Panel {
	private static final long serialVersionUID = 1L;

	@Inject private GePessoas gePessoas;
	@Inject	private Produtos produtos;
	
	private OrRequisicao requisicao;
	private List<OrReqitensdespesa> produtosSemCadastro = new ArrayList<OrReqitensdespesa>();
	private Set<OrReqitensdespesa> produtosValidados = new HashSet<OrReqitensdespesa>();
	
	private SecaoProdutosSemCadastro secaoProdutosSemCadastro;
	private SecaoFornecedorSemCadastro secaoFornecedorSemCadastro;
	private SecaoProdutosValidados secaoProdutosValidados;
	private ValidacaoFornecedorModal validacaoFornecedorModal;
	private ValidacaoProdutoModal validacaoProdutoModal;
	
	public SecaoCadastrarItens(String id, OrRequisicao requisicao) {
		super(id);
		this.requisicao = requisicao;
		produtosSemCadastro = extraiProdutosSemCadastro(this.requisicao);
		
		secaoFornecedorSemCadastro = new SecaoFornecedorSemCadastro("secaoFornecedorSemCadastro");
		secaoProdutosSemCadastro = new SecaoProdutosSemCadastro("secaoProdutosSemCadastro");
		secaoProdutosValidados = new SecaoProdutosValidados("secaoProdutosValidados");
		validacaoFornecedorModal = new ValidacaoFornecedorModal("validacaoFornecedorModal");
		validacaoProdutoModal = new ValidacaoProdutoModal("validacaoProdutoModal");
		
		add(secaoProdutosSemCadastro, secaoFornecedorSemCadastro, secaoProdutosValidados, validacaoFornecedorModal, validacaoProdutoModal);
	}
	
	private List<OrReqitensdespesa> extraiProdutosSemCadastro(OrRequisicao requisicao){
		Set<OrReqitensdespesa> itens = requisicao.getOrReqitensdespesas();
		List<OrReqitensdespesa> produtosSemCadastro = new ArrayList<OrReqitensdespesa>();
		
		for(OrReqitensdespesa item : itens){
			if(item.getCodproduto() == null || item.getCodproduto().trim().isEmpty()){
				produtosSemCadastro.add(item);
			}
		}
		
		return produtosSemCadastro;
	}
	
	private class SecaoFornecedorSemCadastro extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private BigDecimal idFornecedor;
		
		private TextField<String> nomeFornecedor;
		private TextField<BigDecimal> codFornecedor;
		private AjaxButton validaFornecedorBtn;
		
		public SecaoFornecedorSemCadastro(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			nomeFornecedor = new TextField<String>("nomeFornecedor", new PropertyModel<String>(requisicao, "nomeFornecedor"));
			nomeFornecedor.setEnabled(false);
			codFornecedor = new TextField<BigDecimal>("codFornecedor", new PropertyModel<BigDecimal>(this, "idFornecedor"));
			
			validaFornecedorBtn = new AjaxButton("validaFornecedorBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					validacaoFornecedorModal.valida(idFornecedor).exibe(target);
				}
				
			};
			
			add(nomeFornecedor, codFornecedor, validaFornecedorBtn);
			
			if(requisicao.getSeqpessoa() != null && requisicao.getSeqpessoa() != 0){
				setVisible(false);
			}
		}
		
		public void atualiza(String fornecedor, BigDecimal idFornecedor, boolean habilitado, AjaxRequestTarget target){
			nomeFornecedor.setDefaultModelObject(fornecedor);
			codFornecedor.setDefaultModelObject(idFornecedor).setEnabled(habilitado);
			validaFornecedorBtn.setEnabled(habilitado);
			target.add(this);
		}
	}
	
	private class ValidacaoFornecedorModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<String> codFornecedorInput;
		private TextField<String> nomeFornecedorInput;
		private AjaxButton addOutroCodigoBtn, confirmarBtn;
		private WebMarkupContainer mensagem;
		private WebMarkupContainer camposDescritivosFornecedor;
		
		private GePessoaEntity fornecedor;
		
		public ValidacaoFornecedorModal(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			codFornecedorInput = new TextField<String>("codFornecedorInput", Model.of(""));
			nomeFornecedorInput = new TextField<String>("nomeFornecedorInput", Model.of(""));
			camposDescritivosFornecedor = new WebMarkupContainer("camposDescritivosFornecedor");
			addOutroCodigoBtn = new AddOutroCodigoBtn("addOutroCodigoBtn");
			confirmarBtn = new ConfirmarBtn("confirmarBtn");
			mensagem = new WebMarkupContainer("mensagem");
			
			camposDescritivosFornecedor.add(codFornecedorInput, nomeFornecedorInput).setEnabled(false);
			add(camposDescritivosFornecedor, mensagem, addOutroCodigoBtn, confirmarBtn);
		}
		
		public ValidacaoFornecedorModal valida(BigDecimal idFornecedor){
			fornecedor = gePessoas.buscaGePessoaPorFornecedor(idFornecedor);
			
			if(fornecedor != null){
				String nomerazao = fornecedor.getNomerazao();
				int codFornecedor = fornecedor.getSeqpessoa().intValue();				
				codFornecedorInput.setDefaultModelObject(codFornecedor);
				nomeFornecedorInput.setDefaultModelObject(nomerazao);
				
				setVisibilidadeCampos(true);
			}else{
				setVisibilidadeCampos(false);
			}
			
			return this;
		}
		
		public void exibe(AjaxRequestTarget target) {
			target.add(this);
			target.appendJavaScript("$('#validacaoFornecedorModal').modal('show')");
		}
		
		private void setVisibilidadeCampos(boolean visibilidade){
			mensagem.setVisible(!visibilidade);
			camposDescritivosFornecedor.setVisible(visibilidade);
			confirmarBtn.setVisible(visibilidade);
		}
		
		private class ConfirmarBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public ConfirmarBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				BigDecimal seqpessoa = fornecedor.getSeqpessoa();
				String nomerazao = fornecedor.getNomerazao();				
				
				requisicao.setNomeFornecedor(nomerazao);
				requisicao.setSeqpessoa(seqpessoa.intValue());
				
				secaoFornecedorSemCadastro.atualiza(nomerazao, seqpessoa, false, target);
				oculta(target);				
			}
			
		}
		
		private class AddOutroCodigoBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public AddOutroCodigoBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				oculta(target);
			}
			
		}
		
		private void oculta(AjaxRequestTarget target){
			target.appendJavaScript("$('#validacaoFornecedorModal').modal('hide')");
		}
		
	}
	
	private class ValidacaoProdutoModal extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<String> codigoProdutoInput;
		private TextField<String> nomeProdutoInput;
		private AjaxButton addOutroCodigoBtn, confirmarBtn;
		private WebMarkupContainer mensagemContainer;
		private Label mensagem;
		private WebMarkupContainer camposDescritivosProduto;		
		
		private OrvProdutoTributo produto;
		private OrReqitensdespesa itemDespesa;
		
		public ValidacaoProdutoModal(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			codigoProdutoInput = new TextField<String>("codProdutoInput", Model.of(""));
			nomeProdutoInput = new TextField<String>("nomeProdutoInput", Model.of(""));
			camposDescritivosProduto = new WebMarkupContainer("camposDescritivosProduto");
			addOutroCodigoBtn = new AddOutroCodigoBtn("addOutroCodigoBtn");
			confirmarBtn = new ConfirmarBtn("confirmarBtn");
			mensagemContainer = new WebMarkupContainer("mensagemContainer");
			mensagem = new Label("mensagem", "");
			
			mensagemContainer.add(mensagem);
			camposDescritivosProduto.add(codigoProdutoInput, nomeProdutoInput).setEnabled(false);
			add(camposDescritivosProduto, mensagemContainer, addOutroCodigoBtn, confirmarBtn);
		}
		
		public ValidacaoProdutoModal valida(OrReqitensdespesa itemDespesa){
			BigDecimal nroEmpresa = new BigDecimal(requisicao.getNroempresa());
			BigDecimal seqPessoa = requisicao.getSeqpessoa() == null ? BigDecimal.ZERO : new BigDecimal(requisicao.getSeqpessoa());
			BigDecimal codproduto = getCodProduto(itemDespesa);
			
			this.itemDespesa = itemDespesa;
			this.produto = produtos.buscaProduto(nroEmpresa, seqPessoa, codproduto);
			
			if(produto == null){
				mensagem.setDefaultModelObject("Produto inexistente");
				setVisibilidadeCampos(false);
			}else{
				if(produtoJaCadastrado(produto)){
					mensagem.setDefaultModelObject("Produto já cadastrado");
					setVisibilidadeCampos(false);
				}else{
					String descricao = produto.getDescricao();
					BigDecimal seqproduto = produto.getSeqproduto();
					
					nomeProdutoInput.setDefaultModelObject(descricao);
					codigoProdutoInput.setDefaultModelObject(seqproduto);				
					
					setVisibilidadeCampos(true);
				}
			}
	
			return this;
		}		
		
		public ValidacaoProdutoModal exibe(AjaxRequestTarget target) {
			target.add(this);
			target.appendJavaScript("$('#validacaoProdutoModal').modal('show')");
			return this;
		}
		
		private boolean produtoJaCadastrado(OrvProdutoTributo produto){
			
			for(OrReqitensdespesa prodValidado  : produtosValidados){
				if(prodValidado.getCodproduto().equals(produto.getCodproduto())){
					return true;
				}
			}
			
			return false;
		}
		
		private void setVisibilidadeCampos(boolean visibilidade){
			mensagemContainer.setVisible(!visibilidade);
			camposDescritivosProduto.setVisible(visibilidade);
			confirmarBtn.setVisible(visibilidade);
		}
		
		private BigDecimal getCodProduto(OrReqitensdespesa itemDespesa){
			String codprodutoStr = itemDespesa.getCodproduto();
			
			try{
				Integer codProduto = Integer.valueOf(codprodutoStr);
				return new BigDecimal(codProduto);
			}catch(Exception e){
				return BigDecimal.ZERO;
			}
		}
		
		private class ConfirmarBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public ConfirmarBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				itemDespesa.setCodproduto(produto.getCodproduto());
				itemDespesa.setDescricao(produto.getDescricao());
				
				produtosValidados.add(itemDespesa);
				target.add(secaoProdutosSemCadastro, secaoProdutosValidados);
				oculta(target);
			}
			
		}
		
		private class AddOutroCodigoBtn extends AjaxButton{
			private static final long serialVersionUID = 1L;

			public AddOutroCodigoBtn(String id) {
				super(id);
			}
			
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
				oculta(target);
			}
			
		}
		
		private void oculta(AjaxRequestTarget target){
			target.appendJavaScript("$('#validacaoProdutoModal').modal('hide')");
		}
		
	}
	
	private class SecaoProdutosSemCadastro extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private ProdutosSemCadastroDV produtos;
		private ProdutosSemCadastroProvider  provider;
		
		public SecaoProdutosSemCadastro(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			provider = new ProdutosSemCadastroProvider();
			produtos = new ProdutosSemCadastroDV("produtos", provider);
			
			add(produtos);
		}
		
	}

	private class ProdutosSemCadastroDV extends DataView<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;		
		
		protected ProdutosSemCadastroDV(String id, IDataProvider<OrReqitensdespesa> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(final Item<OrReqitensdespesa> item) {
			final OrReqitensdespesa itemDespesa =  (OrReqitensdespesa) item.getDefaultModelObject();
			
			Label descricao = new Label("descricao", itemDespesa.getDescricao());
			Label cfop = new Label("cfop", itemDespesa.getCfop());
			Label quantidade = new Label("quantidade", itemDespesa.getQuantidade());
			Label vlritem = new Label("vlritem", itemDespesa.getVlritem());	
			TextField<String> codProduto = new TextField<String>("codProduto", new PropertyModel<String>(itemDespesa, "codproduto"));
			
			
			AjaxButton validarBtn = new AjaxButton("validarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					validacaoProdutoModal.valida(itemDespesa).exibe(target);
				}
				
			};
			
			item.add(codProduto, descricao, cfop, quantidade, vlritem, validarBtn);	
		}
		
	}
	
	private class SecaoProdutosValidados extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private ProdutosValidadosDV produtosValidados;
		private ProdutosValidadosProvider provider;
		
		public SecaoProdutosValidados(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			this.provider = new ProdutosValidadosProvider();
			this.produtosValidados = new ProdutosValidadosDV("produtosValidados", provider);
			
			add(produtosValidados);
		}
		
	}
	
	private class ProdutosValidadosDV extends DataView<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;

		protected ProdutosValidadosDV(String id, IDataProvider<OrReqitensdespesa> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(Item<OrReqitensdespesa> item) {
			final OrReqitensdespesa itemDespesa =  (OrReqitensdespesa) item.getDefaultModelObject();
			
			Label codProduto = new Label("codproduto", itemDespesa.getCodproduto());
			Label descricao = new Label("descricao", itemDespesa.getDescricao());
			Label cfop = new Label("cfop", itemDespesa.getCfop());
			Label quantidade = new Label("quantidade", itemDespesa.getQuantidade());
			Label vlritem = new Label("vlritem", itemDespesa.getVlritem());
			
			AjaxLink<Void> exclusaoItemBtn = new AjaxLink<Void>("exclusaoBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				public void onClick(AjaxRequestTarget target) {
					produtosValidados.remove(itemDespesa);
					target.add(secaoProdutosValidados);
				}
			};	
			
			item.add(codProduto, descricao, cfop, quantidade, vlritem, exclusaoItemBtn);	
		}
		
	}
	
	private class ProdutosSemCadastroProvider implements IDataProvider<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqitensdespesa> iterator(long first, long count) {
			return produtosSemCadastro.iterator();
		}

		@Override
		public long size() {
			return produtosSemCadastro.size();
		}

		@Override
		public IModel<OrReqitensdespesa> model(final OrReqitensdespesa orvProdutoTributo) {
			return new LoadableDetachableModel<OrReqitensdespesa>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrReqitensdespesa load() {
					return orvProdutoTributo;
				}
			};
		}
		
	}
	
	private class ProdutosValidadosProvider implements IDataProvider<OrReqitensdespesa>{
		private static final long serialVersionUID = 1L;

		@Override
		public void detach() {
			
		}

		@Override
		public Iterator<? extends OrReqitensdespesa> iterator(long first, long count) {
			return produtosValidados.iterator();
		}

		@Override
		public long size() {
			return produtosValidados.size();
		}

		@Override
		public IModel<OrReqitensdespesa> model(final OrReqitensdespesa orvProdutoTributo) {
			return new LoadableDetachableModel<OrReqitensdespesa>() {
				private static final long serialVersionUID = 1L;
				@Override
				protected OrReqitensdespesa load() {
					return orvProdutoTributo;
				}
			};
		}
	}

}
