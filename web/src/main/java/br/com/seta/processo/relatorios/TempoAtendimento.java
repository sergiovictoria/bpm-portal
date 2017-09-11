package br.com.seta.processo.relatorios;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByBorder;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.DownloadLink;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.handler.resource.ResourceStreamRequestHandler;
import org.apache.wicket.util.resource.FileResourceStream;
import org.apache.wicket.util.resource.IResourceStream;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.authentication.ProcessoApplication;
import static br.com.seta.processo.comparators.DadosTempoAtendimentoComparators.*;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.pagecomponentes.OrderCssProvider;
import br.com.seta.processo.provider.TempoAtendimentoProvider;
import br.com.seta.processo.report.TypeReportEnum;
import br.com.seta.processo.service.ProcessoService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;
import br.com.seta.processo.service.interfaces.RelatorioService;
import br.com.seta.processo.utils.DateUtils;

public class TempoAtendimento extends Templete {
	private static final long serialVersionUID = 1L;
	
	@Inject private ProcessoService processoService;
	@Inject RelatorioService relatorioService;
	@Inject GestaoFilasService filasService;
	
	private TempoAtendimentoProvider provider = new TempoAtendimentoProvider();
	private WebMarkupContainer rptAtendimento = new WebMarkupContainer("rptAtendimento");
	
	private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
	public TempoAtendimento(){
		setTituloPagina("Tempo de Atendimento");
		
		try {
			this.processos = this.processoService.listaProcessos(getUser());
			if(this.processos.size() > 0){
				this.processoSelecionado = this.processos.get(0);
				this.usuariosDoProcesso = filasService.listaTodosUsuariosPorProcesso(getUser(), this.processoSelecionado, null, null);
			}
		} catch (BonitaException e) {}
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		
		Date de = cal.getTime();
		setDataDe(de);
		setDataAte(new Date());
		
		preencheAtividadesAPartirDosParametros();
		
		add(new TempoAtendimentoForm("form"));
	}
	
	private class TempoAtendimentoForm extends Form<Object> {
		private static final long serialVersionUID = 1L;
		public TempoAtendimentoForm(String id) {
			super(id);
			
			final String fileDownload = null;
			
			final IModel<java.io.File> fileModel = new AbstractReadOnlyModel<java.io.File>() {
				private static final long serialVersionUID = 1L;

				@Override
				public java.io.File getObject() {
					return new java.io.File(fileDownload);
				}
			};
			
			add(new DownloadLink("btnImprimirPdf",fileModel){
				private static final long serialVersionUID = 1L;
				@Override
				public void onClick(){
					java.io.File file = relatorioService.reportTempoAtendimento(TypeReportEnum.PDF, 
																					ProcessoApplication.getContextPath(), 
																					caseId, 
																					dataDe, 
																					dataAte,
																					usuarioSelecionado, 
																					processoSelecionado.getName());

					IResourceStream resourceStream = new FileResourceStream(new org.apache.wicket.util.file.File(file));
					getRequestCycle().scheduleRequestHandlerAfterCurrent(new ResourceStreamRequestHandler(resourceStream, file.getName()));
				}
			});
			
			add(new DownloadLink("btnImprimirXls",fileModel){
				private static final long serialVersionUID = 1L;
				@Override
				public void onClick(){
					java.io.File file = relatorioService.reportTempoAtendimento(TypeReportEnum.XLS, 
																					ProcessoApplication.getContextPath(), 
																					caseId, 
																					dataDe, 
																					dataAte,
																					usuarioSelecionado, 
																					processoSelecionado.getName());

					IResourceStream resourceStream = new FileResourceStream(new org.apache.wicket.util.file.File(file));
					getRequestCycle().scheduleRequestHandlerAfterCurrent(new ResourceStreamRequestHandler(resourceStream, file.getName()));
				}
			});
			
			
			DataView<DadosTempoAtendimento> dtvAtividades = new DataView<DadosTempoAtendimento>("dtvAtendimento", provider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<DadosTempoAtendimento> item) {
					DadosTempoAtendimento atividade = (DadosTempoAtendimento) item.getDefaultModelObject();
					
					item.add(new Label("lblDtvCaseId", atividade.getCaseId()));
					item.add(new Label("lblDtvProcesso", atividade.getProcesso()));
					item.add(new Label("lblDtvCriacao", FORMATADOR_DATA.format(atividade.getCriacaoInstancia())));
					item.add(new Label("lblDtvStatus", FORMATADOR_DATA.format(atividade.getFimInstancia())));
					item.add(new Label("lblDtvUsuarioCriacao", atividade.getUsuarioaIniciador()));
					item.add(new Label("lblDtvDuracao", atividade.getDuracao()));
				}
			};
			
			dtvAtividades.setItemsPerPage(10L);
			AjaxPagingNavigator ajaxPagingNavigator = new AjaxPagingNavigator("ajaxPagingNavigator", dtvAtividades);
			
			
			rptAtendimento.setOutputMarkupId(true);
			
			rptAtendimento.add(dtvAtividades, ajaxPagingNavigator);
			
			add(new ModalFiltro("modalFiltro"));
			add(rptAtendimento);
			
			OrderDadosTempoAtendimento caseIdOrder = new OrderDadosTempoAtendimento("caseIdOrder", POR_CASE_ID);
			OrderDadosTempoAtendimento inicioOrder = new OrderDadosTempoAtendimento("inicioOrder", POR_INICIO);
			OrderDadosTempoAtendimento terminoOrder = new OrderDadosTempoAtendimento("terminoOrder", POR_TERMINO);
			OrderDadosTempoAtendimento usrIniciadorOrder = new OrderDadosTempoAtendimento("usrIniciadorOrder", POR_USUARIO_INICIADOR);
			OrderDadosTempoAtendimento duracaoOrder = new  OrderDadosTempoAtendimento("duracaoOrder", POR_DURACAO);
			
			rptAtendimento.add(caseIdOrder, inicioOrder, terminoOrder, usrIniciadorOrder, duracaoOrder);
		}
		
	}
	
	
	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public ModalFiltro(String id) {
			super(id);
			
			TextField<String> txtCaseId = new TextField<String>("txtCaseId", new PropertyModel<String>(TempoAtendimento.this, "caseId"));
			
			DateTextField txtDataDe = new DateTextField("txtDataDe", new PropertyModel<Date>(TempoAtendimento.this, "dataDe"), "dd/MM/yyyy");
			DateTextField txtDataAte = new DateTextField("txtDataAte", new PropertyModel<Date>(TempoAtendimento.this, "dataAte"), "dd/MM/yyyy");
			
			
			ChoiceRenderer<User> choiceRenderUsuarios = new ChoiceRenderer<User>(){
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(User user) {
					 String nome = user.getFirstname() + " " + user.getLastname();
					 return nome != null && !nome.trim().isEmpty() ? nome : user.getUserName();
				}
			};
			final DropDownChoice<User> cmbUsuario = new DropDownChoice<User>("cmbUsuario",  
					new PropertyModel<User> (TempoAtendimento.this, "usuarioSelecionado"), 
					usuariosDoProcesso,
					choiceRenderUsuarios){
				private static final long serialVersionUID = 1L;
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			cmbUsuario.setNullValid(true).setOutputMarkupId(true);
			
			DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",  
						new PropertyModel<TaskProcess> (TempoAtendimento.this, "processoSelecionado"), 
						processos,
						new ChoiceRenderer<TaskProcess>("name"));
			AjaxFormComponentUpdatingBehavior onChangeEvent = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					try {
						cmbUsuario.setChoices(filasService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, null, null));
						target.add(cmbUsuario);
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			cmbProcesso.add(onChangeEvent);
			
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					preencheAtividadesAPartirDosParametros();
					target.add(rptAtendimento);
					ocultaCarregamento(target);
				}				
			};
			
			add(txtCaseId, txtDataDe, txtDataAte, cmbProcesso, cmbUsuario, btnPesquisar);
		}	
	}
	
	private void preencheAtividadesAPartirDosParametros() {
		Long idUsuario = usuarioSelecionado != null ? usuarioSelecionado.getId() : null;
		dataDe = DateUtils.inicioDoDiaDe(dataDe);
		dataAte = DateUtils.fimDoDiaDe(dataAte); 
		provider.putParameter(caseId, processoSelecionado.getName(), idUsuario, dataDe, dataAte);
	}
	
	private Long caseId;
	private String usuario;
	private Date dataDe;
	private Date dataAte;
	private TaskProcess processoSelecionado;
	private User usuarioSelecionado;
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();
	private List<User> usuariosDoProcesso = new ArrayList<User>();

	public Date getDataDe() {
		return dataDe;
	}

	public void setDataDe(Date dataDe) {
		this.dataDe = dataDe;
	}

	public Date getDataAte() {
		return dataAte;
	}

	public void setDataAte(Date dataAte) {
		this.dataAte = dataAte;
	}

	public TaskProcess getProcessoSelecionado() {
		return processoSelecionado;
	}

	public void setProcessoSelecionado(TaskProcess processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public User getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(User usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	/**
	 * Ordenador de colunas da tabela de cadastro de produtos
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	@SuppressWarnings("rawtypes")
	private class OrderDadosTempoAtendimento extends AjaxFallbackOrderByBorder {

		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unchecked")
		public OrderDadosTempoAtendimento(String id, Object sortProperty) {
			super(id, sortProperty, provider, new OrderCssProvider<DadosTempoAtendimento>());
		}

		@Override
		protected void onAjaxClick(AjaxRequestTarget target) {
			target.add(rptAtendimento);
		}

		@Override
		protected void onSortChanged() {
		}

	}	
}
