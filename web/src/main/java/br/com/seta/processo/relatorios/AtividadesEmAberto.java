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
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.provider.AtividadesEmAbertoProvider;
import br.com.seta.processo.relatorios.dominio.Estado;
import br.com.seta.processo.report.TypeReportEnum;
import br.com.seta.processo.service.AtorService;
import br.com.seta.processo.service.ProcessoService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;
import br.com.seta.processo.service.interfaces.RelatorioService;
import br.com.seta.processo.utils.DateUtils;

public class AtividadesEmAberto extends Templete {
	private static final long serialVersionUID = 1L;
	
	@Inject 
	private ProcessoService processoService;
	@Inject 
	private RelatorioService relatorioService;
	@Inject
	private AtorService atorService;
	@Inject
	private GestaoFilasService filasService;
	
	private AtividadesEmAbertoProvider provider = new AtividadesEmAbertoProvider();
	private WebMarkupContainer rptAtividades = new WebMarkupContainer("rptAtividades");
	
	private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy HH:mm 'h'");
			
	public AtividadesEmAberto(){
		setTituloPagina("Atividades Em Aberto");
		
		try {
			this.processos = this.processoService.listaProcessos(getUser());
			
			if(this.processos.size() > 0){
				this.processoSelecionado = this.processos.get(0);
				this.atores = atorService.listaAtores(getUser(), this.processoSelecionado);
				this.usuariosDoProcesso = filasService.listaTodosUsuariosPorProcesso(getUser(), this.processoSelecionado, null, null);
			}
			
		} catch (BonitaException e) {}
		
		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.MONTH, -6);
		Date de = cal.getTime();
		setDataDe(DateUtils.inicioDoDiaDe(de));
		setDataAte(DateUtils.fimDoDiaDe(new Date()));
		this.estado = Estado.getEstados().get(0);
		preencheAtividadesAPartirDosParametros();
		
		add(new AtividadesEmAbertoForm("form"));
	}
	
	private class AtividadesEmAbertoForm extends Form<Object> {
		private static final long serialVersionUID = 1L;
		public AtividadesEmAbertoForm(String id) {
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
					java.io.File file = relatorioService.reportAtividadesEmAberto(TypeReportEnum.PDF, 
																					ProcessoApplication.getContextPath(), 
																					caseId, 
																					dataDe, 
																					dataAte, 
																					atorSelecionado, 
																					usuarioSelecionado, 
																					processoSelecionado.getName(), 
																					estado);

					IResourceStream resourceStream = new FileResourceStream(new org.apache.wicket.util.file.File(file));
					getRequestCycle().scheduleRequestHandlerAfterCurrent(new ResourceStreamRequestHandler(resourceStream, file.getName()));
				}
			});
			
			add(new DownloadLink("btnImprimirXls", fileModel){
				private static final long serialVersionUID = 1L;
				@Override
				public void onClick() {
					java.io.File file = relatorioService.reportAtividadesEmAberto(TypeReportEnum.XLS, 
																					ProcessoApplication.getContextPath(), 
																					caseId, 
																					dataDe, 
																					dataAte, 
																					atorSelecionado, 
																					usuarioSelecionado, 
																					processoSelecionado.getName(), 
																					estado);

					IResourceStream resourceStream = new FileResourceStream(new org.apache.wicket.util.file.File(file));
					getRequestCycle().scheduleRequestHandlerAfterCurrent(new ResourceStreamRequestHandler(resourceStream, file.getName()));
				}
			});
			
			
			DataView<ProcessoEmAberto> dtvAtividades = new DataView<ProcessoEmAberto>("dtvAtividades", provider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<ProcessoEmAberto> item) {
					ProcessoEmAberto atividade = (ProcessoEmAberto) item.getDefaultModelObject();
					
					item.add(new Label("lblDtvCaseId", atividade.getCaseId()));
					item.add(new Label("lblDtvProcesso", atividade.getNomeProcesso()));
					item.add(new Label("lblDtvIdAtividade", atividade.getIdAtividade()));
					item.add(new Label("lblDtvAtividade", atividade.getNomeAtividade()));
					item.add(new Label("lblDtvEstado", atividade.getEstadoAtividade()));
					item.add(new Label("lblDtvDataInstancia", FORMATADOR_DATA.format(atividade.getInicioInstancia())));
					item.add(new Label("lblDtvInicioAtividade", FORMATADOR_DATA.format(atividade.getInicioAtividade())));
					String fimAtividade = atividade.getFimAtividade() == null ? "" : FORMATADOR_DATA.format(atividade.getFimAtividade());
					item.add(new Label("lblDtvFimAtividade", fimAtividade));
					item.add(new Label("lblDtvDuracao", atividade.getDuracao()));
					item.add(new Label("lblDtvAtor", atividade.getAtor()));
					item.add(new Label("lblDtvUsuario", atividade.getExecutadaPor()));
				}
			};
			
			dtvAtividades.setItemsPerPage(10L);
			AjaxPagingNavigator ajaxPagingNavigator = new AjaxPagingNavigator("ajaxPagingNavigator", dtvAtividades);
			
			
			rptAtividades.setOutputMarkupId(true);
			
			rptAtividades.add(dtvAtividades, ajaxPagingNavigator);
			
			add(new ModalFiltro("modalFiltro"));
			add(rptAtividades);
		}
		
	}
	
	
	
	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;
		public ModalFiltro(String id) {
			super(id);
			
			TextField<String> txtCaseId = new TextField<String>("txtCaseId", new PropertyModel<String>(AtividadesEmAberto.this, "caseId"));
			
			DateTextField txtDataDe = new DateTextField("txtDataDe", new PropertyModel<Date>(AtividadesEmAberto.this, "dataDe"), "dd/MM/yyyy");
			DateTextField txtDataAte = new DateTextField("txtDataAte", new PropertyModel<Date>(AtividadesEmAberto.this, "dataAte"), "dd/MM/yyyy");
			DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",  
						new PropertyModel<TaskProcess> (AtividadesEmAberto.this, "processoSelecionado"), 
						processos,
						new ChoiceRenderer<TaskProcess>("name"));
			
			ChoiceRenderer<User> choiceRenderUsuarios = new ChoiceRenderer<User>(){
				private static final long serialVersionUID = 1L;

				@Override
				public Object getDisplayValue(User user) {
					 String nome = user.getFirstname() + " " + user.getLastname();
					 return nome != null && !nome.trim().isEmpty() ? nome : user.getUserName();
				}
			};
			final DropDownChoice<User> cmbUsuario = new DropDownChoice<User>("cmbUsuario",  
					new PropertyModel<User> (AtividadesEmAberto.this, "usuarioSelecionado"), 
					usuariosDoProcesso,
					choiceRenderUsuarios){
				private static final long serialVersionUID = 1L;
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			cmbUsuario.setNullValid(true).setOutputMarkupId(true);
			
			final DropDownChoice<Actor> cmbAtor = new DropDownChoice<Actor>(
					"cmbAtor", new PropertyModel<Actor>(
							AtividadesEmAberto.this, "atorSelecionado"),
					atores, new ChoiceRenderer<Actor>("displayName")) {
				private static final long serialVersionUID = 1L;
				
				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			cmbAtor.setNullValid(true).setOutputMarkupId(true);
			
			AjaxFormComponentUpdatingBehavior onChangeEvent = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					try {
						cmbAtor.setChoices(atorService.listaAtores(getUser(), processoSelecionado));
						cmbUsuario.setChoices(filasService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, null, null));
						target.add(cmbAtor, cmbUsuario);
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			cmbProcesso.add(onChangeEvent);
			
			DropDownChoice<String> cmbEstado = new DropDownChoice<String>("cmbEstado",  
					new PropertyModel<String> (AtividadesEmAberto.this, "estado"), 
					Estado.getEstados());
						
			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					preencheAtividadesAPartirDosParametros();
					target.add(rptAtividades);
					ocultaCarregamento(target);
				}				
			};
			
			add(txtCaseId, txtDataDe, txtDataAte, cmbUsuario, cmbProcesso, cmbAtor, cmbEstado, btnPesquisar);
		}	
	}
	
	private void preencheAtividadesAPartirDosParametros() {
		String nomeAtor = atorSelecionado != null ? atorSelecionado.getDisplayName() : null; 
		Long idUsuario = usuarioSelecionado != null ? usuarioSelecionado.getId() : null;
		
		dataDe = DateUtils.inicioDoDiaDe(dataDe);
		dataAte = DateUtils.fimDoDiaDe(dataAte);
		
		provider.putParameter(caseId, processoSelecionado.getName(), estado, nomeAtor, idUsuario, dataDe, dataAte);
	}
	
	private Long caseId;
	private String ator;
	private String usuario;
	private Date dataDe;
	private Date dataAte;
	private TaskProcess processoSelecionado;
	private Actor atorSelecionado;
	private User usuarioSelecionado;
	private String estado;
	
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();
	private List<User> usuariosDoProcesso = new ArrayList<User>();
	private List<Actor> atores = new ArrayList<Actor>();

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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public String getAtor() {
		return ator;
	}

	public void setAtor(String ator) {
		this.ator = ator;
	}

	public Actor getAtorSelecionado() {
		return atorSelecionado;
	}

	public void setAtorSelecionado(Actor atorSelecionado) {
		this.atorSelecionado = atorSelecionado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<User> getUsuariosDoProcesso() {
		return usuariosDoProcesso;
	}

	public User getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(User usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public void setUsuariosDoProcesso(List<User> usuariosDoProcesso) {
		this.usuariosDoProcesso = usuariosDoProcesso;
	}
}
