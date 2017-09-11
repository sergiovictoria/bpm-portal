package br.com.seta.processo.consultas.suprimentos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;

import br.com.seta.processo.bean.dao.FiltroIntencaoCompra;
import br.com.seta.processo.constant.VariaveisRecebimento;
import br.com.seta.processo.dominios.TipoDespesa;
import br.com.seta.processo.dto.AtividadeProcesso;
import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.provider.InstanciasIntencaoCompraProvider;
import br.com.seta.processo.utils.DateUtils;
import static br.com.seta.processo.bean.dao.FiltroIntencaoCompra.RECEBIMENTO;
import static br.com.seta.processo.bean.dao.FiltroIntencaoCompra.SUPRIMENTOS;

public class ConsultaIntencoesCompra extends BonitaPage {
	private static final long serialVersionUID = 1L;
	
	private static final int QUANT_ITENS = 15;
	
	private FiltroIntencaoCompra filtro = new FiltroIntencaoCompra();
	private InstanciasIntencaoCompraProvider provider;
	private InstanciasForm instanciasForm;
	private FormModalFiltro formModalFiltro;
	
	private static final List<String> statusDoProcesso = new ArrayList<String>();
	static{
		statusDoProcesso.add("Aprovado");
		statusDoProcesso.add("Rejeitado");
	}
	
	public ConsultaIntencoesCompra(){
		this.filtro.setProcesso(SUPRIMENTOS);
		this.provider = new InstanciasIntencaoCompraProvider(this.filtro);
		this.instanciasForm = new InstanciasForm("instanciasForm");
		this.formModalFiltro = new FormModalFiltro("formModalFiltro");
		
		add(instanciasForm, formModalFiltro);
		setTituloPagina("Consulta Intenções de Compra");
	}
	
	private class InstanciasForm extends Form<Void>{
		private static final long serialVersionUID = 1L;

		private TabelaInstancias tabelaInstancias;
		private MensagemContainer mensagemContainer;
		
		public InstanciasForm(String id) {
			super(id);
			setOutputMarkupId(true);
			this.tabelaInstancias = new TabelaInstancias("tabelaInstancias");
			this.mensagemContainer = new MensagemContainer("mensagemContainer");
			add(tabelaInstancias, mensagemContainer);
		}	
		
		@Override
		protected void onConfigure() {			
			super.onConfigure();
			
			if(provider.size() > 0){
				tabelaInstancias.setVisible(true);
				mensagemContainer.setVisible(false);				
			}else{
				tabelaInstancias.setVisible(false);
				mensagemContainer.setVisible(true);
			}	
		}
		
	}
	
	private class TabelaInstancias extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private InstanciasDataView instanciasDataView;		
		private AjaxPagingNavigator navigator;		
		
		public TabelaInstancias(String id) {
			super(id);
			
			setOutputMarkupId(true);
			
			this.instanciasDataView = new InstanciasDataView("instancias", provider, QUANT_ITENS);
			this.navigator = new AjaxPagingNavigator("navigator", this.instanciasDataView){
				private static final long serialVersionUID = 1L;

				@Override
				protected void onConfigure() {					
					super.onConfigure();
					
					if(provider.size() > QUANT_ITENS)
						setVisible(true);
					else
						setVisible(false);
					
				}
			};
			
			add(instanciasDataView, this.navigator);
		}
		
	}
	
	private class InstanciasDataView extends DataView<InstanciaProcesso>{
		private static final long serialVersionUID = 1L;		
		
		public InstanciasDataView(String id, IDataProvider<InstanciaProcesso> dataProvider, long itemsPerPage) {
			super(id, dataProvider, itemsPerPage);
		}

		protected InstanciasDataView(String id, IDataProvider<InstanciaProcesso> dataProvider) {
			super(id, dataProvider);		
		}

		@Override
		protected void populateItem(Item<InstanciaProcesso> item) {
			final InstanciaProcesso instancia = (InstanciaProcesso) item.getDefaultModelObject();
			AtividadeProcesso atividade = instancia.primeiraAtividade();
			
			final SolicitacaoIntencaoCompra solicitacao = (SolicitacaoIntencaoCompra) atividade.getValorVariavel(VariaveisRecebimento.SOLICITACAO);
			final OrRequisicao requisicao = (OrRequisicao) atividade.getValorVariavel(VariaveisRecebimento.REQUISICAO);
			final Recebimento recebimento = (Recebimento) atividade.getValorVariavel(VariaveisRecebimento.RECEBIMENTO);
			
			String solicitante = solicitacao.getNomeSolicitante();
			String tipoDespesa = solicitacao.getTipoDespesa();
			String fornecedor = requisicao.getNomeFornecedor();
			String empresa = requisicao.getNomeEmpresa();
			Long caseId = instancia.getCaseId();
			String caseIdOrigem = requisicao.getNumeroIntencaoSolicitacaoCompra().equals(caseId) ? "" : requisicao.getNumeroIntencaoSolicitacaoCompra().toString();
			String status = instancia.getStatus();
			
			Label processoLbl = new Label("processo", instancia.getNomeProcesso());
			Label solicitanteLbl = new Label("solicitante", solicitante);
			Label tipoDespesaLbl = new Label("tipoDespesa", tipoDespesa);
			Label fornecedorLbl = new Label("fornecedor", fornecedor);
			Label empresaLbl = new Label("empresa", empresa);
			Label caseIdLbl= new Label("caseId", caseId);
			Label caseIdOrigemLbl= new Label("caseIdOrigem", caseIdOrigem);
			Label statusLbl = new Label("status", status);
			Label terminoLbl = new Label("termino", instancia.getFim());
			AjaxButton detalhesBtn = new AjaxButton("detalhesBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						DadosIntencao formulario = new DadosIntencao(getPageParameters(), requisicao, solicitacao, recebimento, instancia.getCaseId());
						setResponsePage(formulario);
					} catch (HttpStatus401Exception | HttpStatus404Exception
							| GenericHttpStatusException | IOException e) {
						throw new RuntimeException(e);
					}					
				}
			};
			
			item.add(processoLbl, solicitanteLbl, tipoDespesaLbl, fornecedorLbl, empresaLbl, caseIdLbl, caseIdOrigemLbl, statusLbl, terminoLbl, detalhesBtn);			
		}
		
	}
	
	private class FormModalFiltro extends Form<Void>{
		private static final long serialVersionUID = 1L;

		private ModalFiltro modalFiltro;
		
		public FormModalFiltro(String id) {
			super(id);		
			
			modalFiltro = new ModalFiltro("modalFiltro", filtro);
			
			add(modalFiltro);
		}
		
	}
	private class ModalFiltro extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private TextField<String> solicitante, fornecedor, empresa;
		private DropDownChoice<String> status, tipoDespesa, processo;
		private TextField<Long> caseId;
		private AjaxButton pesquisarBtn;
		private DateTextField inicio, fim;
		
		public ModalFiltro(String id, final FiltroIntencaoCompra filtro) {
			super(id, new CompoundPropertyModel<FiltroIntencaoCompra>(filtro));	
			
			processo = new  DropDownChoice<String>("processo", Arrays.asList(SUPRIMENTOS, RECEBIMENTO)){
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isNullValid() {
					return false;
				}
			};
			
			solicitante = new TextField<String>("solicitante");
			fornecedor = new TextField<String>("fornecedor");
			empresa = new TextField<String>("empresa");
			caseId = new TextField<Long>("caseId");
			status = new DropDownChoice<String>("status", statusDoProcesso){
				private static final long serialVersionUID = 1L;
				
				@Override
				public boolean isNullValid() {
					return true;
				}		
				
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
				
			};
			tipoDespesa = new DropDownChoice<String>("tipoDespesa", TipoDespesa.getValores()){
				private static final long serialVersionUID = 1L;

				@Override
				public boolean isNullValid() {
					return true;
				}
				
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			inicio = new DateTextField("inicio");
			fim = new DateTextField("fim");
			
			pesquisarBtn = new AjaxButton("pesquisarBtn") {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					Date fim =  filtro.getFim() == null? null : DateUtils.fimDoDiaDe(filtro.getFim());
					Date inicio = filtro.getInicio() == null ? null : DateUtils.inicioDoDiaDe(filtro.getInicio());
					
					filtro.setInicio(inicio);
					filtro.setFim(fim);
					
					target.add(instanciasForm);
					ocultaCarregamento(target);
				}
			};
			
			add(processo, solicitante, fornecedor, empresa, status, tipoDespesa, caseId, inicio, fim, pesquisarBtn);
		}
		
	}
	
	private class MensagemContainer extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private WebMarkupContainer mensagem;
		
		public MensagemContainer(String id) {
			super(id);
			
			mensagem = new WebMarkupContainer("mensagem");
			
			add(mensagem);
		}		
	}
	
	

}
