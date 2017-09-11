package br.com.seta.processo.gestaofilas;

import static br.com.seta.processo.utils.BonitaPriorityConstants.ABOVE_NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.HIGHEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.LOWEST_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.NORMAL_PRIORITY;
import static br.com.seta.processo.utils.BonitaPriorityConstants.UNDER_NORMAL_PRIORITY;
import static br.com.seta.processo.utils.PriorityIconCssClass.ABOVE_NORMAL_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.HIGHEST_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.LOWEST_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.NORMAL_ICON;
import static br.com.seta.processo.utils.PriorityIconCssClass.UNDER_NORMAL_ICON;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.model.TaskModel;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.pagecomponentes.TaskTimelinePanel;
import br.com.seta.processo.provider.GestaoFilaProvider;
import br.com.seta.processo.service.ProcessoService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;

public class GestaoFilas extends Templete {

	private static final long serialVersionUID = 1L;

	@Inject	private ProcessoService processoService;
	@Inject	private GestaoFilasService gestaofilaService;
	public final static int QUANT_ATIVIDADES_POR_PAGINA = 10;
	private static SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yy  HH:mm:ss");

	private AtividadesForm atividadesForm;
	private WebMarkupContainer secaoTimeline;
	private TaskModel atividadeSelecionada = new TaskModel();
	private WebMarkupContainer atividadesContainer;
	private DataView<HumanTask> atividadesDataView;
	private GestaoFilaProvider gestaoFilaProvider;	
	private AjaxPagingNavigator navigator;
	private transient User user = (User) Session.get().getAttribute("user");
	private TaskTimelinePanel timeline;
	
	DropDownChoice<TaskProcess> cmbProcessoAtribuir;
	DropDownChoice<Actor> cmbAtorAtribuir;
	DropDownChoice<User> cmbUsuarioAtribuir;
	
	private TaskProcess processoSelecionado;
	private TaskProcess processoSelecionadoAtribuir;
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();

	private Actor atorSelecionado;
	private Actor atorSelecionadoAtribuir;
	private List<Actor> atores = new ArrayList<Actor>();

	private User usuarioSelecionado;
	private User usuarioSelecionadoAtribuir;
	private List<User> usuarios = new ArrayList<User>();
	private List<User> usuariosAtribuir = new ArrayList<User>();

	private Label lblProcesso;
	private WebMarkupContainer painelGestaoFilas;


	public GestaoFilas() throws HttpStatus401Exception, ParseException, IOException, HttpStatusException {
		atividadesForm = new AtividadesForm("atividadesForm");
		secaoTimeline = (WebMarkupContainer) new WebMarkupContainer("secaoTimeline").setOutputMarkupId(true);
		timeline = (TaskTimelinePanel) new TaskTimelinePanel("timeline", user).setOutputMarkupId(true);
		secaoTimeline.add(timeline);
		setTituloPagina("Gestão de Filas");

		lblProcesso = (Label) new Label("lblProcesso", processoSelecionado.getDisplayName()).setOutputMarkupId(true);
		painelGestaoFilas = (WebMarkupContainer) new WebMarkupContainer("painelGestaoFilas").setOutputMarkupId(true);
		painelGestaoFilas.add(secaoTimeline);
		painelGestaoFilas.add(atividadesContainer);
		painelGestaoFilas.add(lblProcesso);		
		atividadesForm.add(painelGestaoFilas);
		atividadesForm.add(new ModalFiltro("modalFiltro"));
		atividadesForm.add(new ModalAtribuir("modalAtribuir"));
		atividadesForm.add(new ModalCancelar("modalCancelar"));		
		add(atividadesForm);
	}

	public class AtividadesForm extends Form<Void> {
		private static final long serialVersionUID = 1L;

		public AtividadesForm(String id)
				throws HttpStatus401Exception, ParseException, IOException, HttpStatusException {
			super(id);

			try {
				configuraComboModalFiltro();
				configuraComboModalAtribuir();
			} catch (BonitaException e) {
				throw new RuntimeException(e);
			}

			gestaoFilaProvider = new GestaoFilaProvider(processoSelecionado, atorSelecionado, usuarioSelecionado);
			atividadesContainer = (WebMarkupContainer) new WebMarkupContainer("atividadesContainer").setOutputMarkupId(true);
			atividadesDataView = new DataView<HumanTask>("atividadesDataView", gestaoFilaProvider, QUANT_ATIVIDADES_POR_PAGINA) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(final Item<HumanTask> item) {
					final HumanTask atividade = (HumanTask) item.getModelObject();
					item.setOutputMarkupId(true);

					WebMarkupContainer atividadeDados = new WebMarkupContainer("atividadeDados");

					Label descricaoLabel = (Label) new Label("descricao", atividade.getDisplayName()).add(AttributeAppender.append("title", atividade.getDisplayName()));
					atividadeDados.add(descricaoLabel);
					atividadeDados.add(new Label("rootContainerId", atividade.getRootCaseId()));
					atividadeDados.add(new Label("rootContainer", atividade.getRootContainerId().getDisplayName()));
					atividadeDados.add(new Label("rootContainerUsuario", (null == atividade.getAssigned_id() ? "" : atividade.getAssigned_id().getNomeCompleto())));
					AttributeAppender cssPriorityClass = criarCssPrioridade(atividade.getPriority());
					AttributeAppender titleHtmlAtribute = criarPrioridadeTitleHtml(atividade.getPriority());
					atividadeDados.add(new Label("prioridadeIcone").add(cssPriorityClass, titleHtmlAtribute));
					AttributeAppender cssPrazoClass = obterClassCssPrazo(atividade.getDueDate());
					atividadeDados.add(new Label("prazo", dateFormater.format(atividade.getDueDate())).add(cssPrazoClass));
					atividadeDados.add(new Label("prazoRelogio").add(cssPrazoClass));
					atividadeDados.add(new Label("prioridade", getPrioridade(atividade.getPriority())));

					CheckBox chkRootcontainer = new CheckBox("chkRootcontainer",
							new PropertyModel<Boolean>(atividade, "selecionado"));

					atividadeDados.add(new AjaxEventBehavior("click") {
						private static final long serialVersionUID = 1L;

						@Override
						protected void onEvent(AjaxRequestTarget target) {
							try {
								atividadeSelecionada.setTask(atividade);
								timeline.criaTimeLinePara(atividadeSelecionada.getTask().getCaseId(), false);
								target.add(timeline);
								target.appendJavaScript("removerSelecionados('#" + item.getMarkupId() + "')");
							} catch (IOException | HttpStatusException e) {
								throw new RuntimeException(e);
							}
						}
					});

					item.add(chkRootcontainer);
					item.add(atividadeDados);
				}
				
				@Override
				protected void onBeforeRender() {
					super.onBeforeRender();					
					selecionaPrimeiroItemECriaTimeline();
				}
			};
			atividadesDataView.setOutputMarkupId(true);			
			navigator = new AjaxPagingNavigator("navigator", atividadesDataView){
				private static final long serialVersionUID = 1L;
				
				@Override
				protected void onAjaxEvent(AjaxRequestTarget target) {
					atualizaAtividadesETimeline(target);
				}
				
			};
			
			atividadesContainer.add(atividadesDataView, navigator);
		}

		/**
		 * @throws BonitaException
		 */
		private void configuraComboModalFiltro() throws BonitaException {
			processos = processoService.listaProcessos(getUser());
			if (processos.size() > 0) {
				processoSelecionado = processos.get(0);
			}
			atores = gestaofilaService.listaTodosAtoresPorProcesso(getUser(), processoSelecionado);
			if (atores.size() > 0) {
				atorSelecionado = atores.get(0);
			}
			usuarios = gestaofilaService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado);
		}

		/**
		 * @throws BonitaException
		 */
		private void configuraComboModalAtribuir() throws BonitaException {
			processoSelecionadoAtribuir = new TaskProcess();
			atorSelecionadoAtribuir = new Actor();
			usuarioSelecionadoAtribuir = new User();
			usuariosAtribuir = gestaofilaService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado);
			usuariosAtribuir.remove(removeNaoAtribuidoComboUsuarioAtribuir());
		}
	}

	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalFiltro(String id) {
			super(id);

			final DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",
					new PropertyModel<TaskProcess>(GestaoFilas.this, "processoSelecionado"), processos,
					new ChoiceRenderer<TaskProcess>("name"));
			cmbProcesso.setOutputMarkupId(true);

			final DropDownChoice<Actor> cmbAtor = new DropDownChoice<Actor>("cmbAtor",
					new PropertyModel<Actor>(GestaoFilas.this, "atorSelecionado"), atores,
					new ChoiceRenderer<Actor>("displayName"));
			cmbAtor.setOutputMarkupId(true);

			final DropDownChoice<User> cmbUsuario = new DropDownChoice<User>("cmbUsuario", Model.of(new User()),
					usuarios, new ChoiceRenderer<User>("nomeCompleto")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			cmbUsuario.setNullValid(true).setOutputMarkupId(true);

			AjaxButton btnPesquisar = new AjaxButton("btnPesquisar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {

					try {
						
						usuarioSelecionado = (User) cmbUsuario.getDefaultModelObject();
						
						usuarios.clear();
						usuarios.addAll(recuperaListaUsuarios(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado));
						retiraUsuarioNaoAtribuidoDaListaUsuarios();

						gestaoFilaProvider.setProcessoSelecionado(processoSelecionado);
						gestaoFilaProvider.setAtorSelecionado(atorSelecionado);
						gestaoFilaProvider.setUsuarioSelecionado(usuarioSelecionado);
						gestaoFilaProvider.setUsuarios(usuarios);
						
						processoSelecionadoAtribuir = processoSelecionado;
						atorSelecionadoAtribuir = atorSelecionado;
						
						usuariosAtribuir.clear();
						usuariosAtribuir.addAll(gestaofilaService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado));
						usuariosAtribuir.remove(removeNaoAtribuidoComboUsuarioAtribuir());						
						
						lblProcesso.setDefaultModel(new Model<Serializable>(processoSelecionado.getDisplayName()));
						target.add(cmbProcessoAtribuir, cmbAtorAtribuir, cmbUsuarioAtribuir);
						//target.appendJavaScript("setEstiloAtividades(); existeAtividades(); habilitaDesabilitaBotoesAtribuicaoNoEventoDeCheck();");
						atualizaAtividadesETimeline(target);
						ocultaCarregamento(target);		
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					
				}

				/**
				 * Retirar usuario Não Atribuido da lista quando é selecionado
				 * um usuario especifico
				 */
				private void retiraUsuarioNaoAtribuidoDaListaUsuarios() {
					if (null != usuarioSelecionado) {
						User usuarioNaoAtribuido = null;
						for (User u : usuarios) {
							if (u.getId() == 0) {
								usuarioNaoAtribuido = u;
							}
						}
						if (usuarioSelecionado.getId() != usuarioNaoAtribuido.getId()) {
							usuarios.remove(usuarioNaoAtribuido);
						}
					}
				}
			};

			AjaxFormComponentUpdatingBehavior onChangeEventProcesso = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					try {
						atores.clear();
						atores.addAll(gestaofilaService.listaTodosAtoresPorProcesso(getUser(), processoSelecionado));
						
						target.add(cmbAtor, cmbAtorAtribuir);
						target.appendJavaScript("setEstiloAtividades(); existeAtividades();");
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			cmbProcesso.add(onChangeEventProcesso);

			AjaxFormComponentUpdatingBehavior onChangeEventAtor = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					try {
						usuarioSelecionado = (User) cmbUsuario.getDefaultModelObject();
						
						usuarios.clear();
						usuariosAtribuir.clear();
						usuarios.addAll(gestaofilaService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado));
						usuariosAtribuir.addAll(gestaofilaService.listaTodosUsuariosPorProcesso(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado));
						usuariosAtribuir.remove(removeNaoAtribuidoComboUsuarioAtribuir());
						
						atorSelecionadoAtribuir = atorSelecionado;
						
						target.add(cmbUsuario, cmbUsuarioAtribuir);
						target.appendJavaScript("setEstiloAtividades(); existeAtividades();");
					} catch (BonitaException e) {
						throw new RuntimeException(e);
					}
				}
			};
			cmbAtor.add(onChangeEventAtor);

			add(cmbProcesso, cmbAtor, cmbUsuario, btnPesquisar);

		}

	}

	/*
	 * MODAL ATRIBUIR
	 */
	private final class ModalAtribuir extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalAtribuir(String id) {
			super(id);

			cmbProcessoAtribuir = new DropDownChoice<TaskProcess>("cmbProcessoAtribuir", 
					new PropertyModel<TaskProcess>(GestaoFilas.this, "processoSelecionadoAtribuir"), processos,
					new ChoiceRenderer<TaskProcess>("name"));
			cmbProcessoAtribuir.setOutputMarkupId(true);
			cmbProcessoAtribuir.setEnabled(false);

			cmbAtorAtribuir = new DropDownChoice<Actor>("cmbAtorAtribuir",
					new PropertyModel<Actor>(GestaoFilas.this, "atorSelecionadoAtribuir"), atores, 
					new ChoiceRenderer<Actor>("displayName"));
			cmbAtorAtribuir.setOutputMarkupId(true);
			cmbAtorAtribuir.setEnabled(false);

			usuariosAtribuir.remove(removeNaoAtribuidoComboUsuarioAtribuir());
			
			cmbUsuarioAtribuir = new DropDownChoice<User>("cmbUsuarioAtribuir",
					new PropertyModel<User>(GestaoFilas.this, "usuarioSelecionadoAtribuir"), usuariosAtribuir,
					new ChoiceRenderer<User>("nomeCompleto"));
			cmbUsuarioAtribuir.setOutputMarkupId(true);

			AjaxButton btnAtribuir = new AjaxButton("btnAtribuir") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						if (null == usuarioSelecionadoAtribuir) {
							setMensagemErro("Selecione um usuário para atribuir a atividade!", target);
						} else {
							List<HumanTask> listaAtribuir = gestaoFilaProvider.getTaskList();
							for (HumanTask ht : listaAtribuir) {
								if (ht.isSelecionado()) {
									gestaofilaService.atribuiAtividadePorUsuario(getUser(), usuarioSelecionadoAtribuir, ht);
									exibeMensagemSucesso("Atribuir",
											"A atividade " + ht.getCaseId() + " foi atribuída à "
													+ usuarioSelecionadoAtribuir.getNomeCompleto() + " com sucesso!",
											target);
								}
							}
						}

						

						

						target.add(painelGestaoFilas);
						target.appendJavaScript("setEstiloAtividades(); existeAtividades(); habilitaDesabilitaBotoesAtribuicaoNoEventoDeCheck();");

					} catch (BonitaException e) {
					}
				}
			};

			add(cmbProcessoAtribuir, cmbAtorAtribuir, cmbUsuarioAtribuir, btnAtribuir);
		}
	}
	

	/*
	 * MODAL CANCELAR
	 */
	private final class ModalCancelar extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalCancelar(String id) {
			super(id);

			AjaxButton btnCancelar = new AjaxButton("btnCancelar") {
				private static final long serialVersionUID = 1L;

				@Override
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						List<HumanTask> listaAtribuir = gestaoFilaProvider.getTaskList();
						for (HumanTask ht : listaAtribuir) {
							if (ht.isSelecionado()) {
								if (null == ht.getAssigned_id()) {
									exibeMensagemAdvertencia("Cancelar Atribuição",
											"Apenas atividades já atribuídas ao usuário podem ser canceladas!", target);
								} else {
									gestaofilaService.cancelaAtividadePorUsuario(getUser(), ht);
									exibeMensagemSucesso("Cancelar Atribuição", "Foi cancelada a atribuição de usuario da atividade " + ht.getCaseId() + " com sucesso!", target);
								}
							}
						}

						

					

						target.add(painelGestaoFilas);
						target.appendJavaScript("setEstiloAtividades(); existeAtividades(); habilitaDesabilitaBotoesAtribuicaoNoEventoDeCheck();");

					} catch (BonitaException e) {
					}
				}
			};

			add(btnCancelar);
		}
	}

	private AttributeAppender criarCssPrioridade(String prioridade) {
		String cssPriorityIconClass = obterClasseCssIconePrioridade(prioridade);
		return AttributeAppender.append("class", cssPriorityIconClass);
	}

	private AttributeAppender criarPrioridadeTitleHtml(String prioridade) {
		String title = getPrioridade(prioridade);
		return AttributeAppender.append("title", title);
	}

	private String getPrioridade(String priority) {
		switch (priority) {
		case LOWEST_PRIORITY:
			return "Baixíssimo";
		case UNDER_NORMAL_PRIORITY:
			return "Baixo";
		case NORMAL_PRIORITY:
			return "Normal";
		case ABOVE_NORMAL_PRIORITY:
			return "Acima do Normal";
		case HIGHEST_PRIORITY:
			return "Altíssimo";
		default:
			return "";
		}
	}

	private String obterClasseCssIconePrioridade(String prioridade) {
		switch (prioridade) {
		case LOWEST_PRIORITY:
			return LOWEST_ICON;
		case UNDER_NORMAL_PRIORITY:
			return UNDER_NORMAL_ICON;
		case NORMAL_PRIORITY:
			return NORMAL_ICON;
		case ABOVE_NORMAL_PRIORITY:
			return ABOVE_NORMAL_ICON;
		case HIGHEST_PRIORITY:
			return HIGHEST_ICON;
		default:
			return "";
		}
	}

	private AttributeAppender obterClassCssPrazo(Date prazo) {
		String estiloCSS = atividadeAtrasada(prazo) ? "atrasado" : "pontual";
		return AttributeAppender.append("class", estiloCSS);
	}

	private boolean atividadeAtrasada(Date prazo) {
		Date agora = new Date();
		return agora.after(prazo);
	}

	private void selecionaPrimeiroItemECriaTimeline(){
		Iterator<Item<HumanTask>> items = atividadesDataView.getItems();

		if (items.hasNext()) {
			Item<HumanTask> primeiroItem = items.next();
			atividadeSelecionada.setTask(primeiroItem.getModelObject());
			try {
				timeline.criaTimeLinePara(atividadeSelecionada.getTask().getCaseId(), false);
			} catch (HttpStatus401Exception | HttpStatus404Exception
					| GenericHttpStatusException | IOException e) {
				throw new RuntimeException(e);
			}
		}
	}	
	
	private void atualizaAtividadesETimeline(AjaxRequestTarget target) {
		target.add(lblProcesso, atividadesContainer, secaoTimeline);
		target.appendJavaScript("setEstiloAtividades(); existeAtividades(); habilitaDesabilitaBotoesAtribuicaoNoEventoDeCheck();");
	}

	/**
	 * @return
	 * @throws BonitaException
	 */
	private List<User> recuperaListaUsuarios(User usuarioLogado, TaskProcess processoSelecionado, Actor atorSelecionado,
			User usuarioSelecionado) throws BonitaException {
		return gestaofilaService.listaTodosUsuariosPorProcesso(usuarioLogado, processoSelecionado, atorSelecionado,
				usuarioSelecionado);
	}
	
	/**
	 * @return
	 */
	private User removeNaoAtribuidoComboUsuarioAtribuir() {
		User usuarioNaoAtribuido = null;
		for (User u : usuariosAtribuir) {
			if (u.getId() == 0) {
				usuarioNaoAtribuido = u;
			}
		}
		return usuarioNaoAtribuido;
	}

	public final TaskProcess getProcessoSelecionado() {
		return processoSelecionado;
	}

	public final void setProcessoSelecionado(TaskProcess processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public Actor getAtorSelecionado() {
		return atorSelecionado;
	}

	public void setAtorSelecionado(Actor atorSelecionado) {
		this.atorSelecionado = atorSelecionado;
	}

	public User getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(User usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public List<User> getUsuariosAtribuir() {
		return usuariosAtribuir;
	}

	public TaskProcess getProcessoSelecionadoAtribuir() {
		return processoSelecionadoAtribuir;
	}

	public void setProcessoSelecionadoAtribuir(TaskProcess processoSelecionadoAtribuir) {
		this.processoSelecionadoAtribuir = processoSelecionadoAtribuir;
	}

	public Actor getAtorSelecionadoAtribuir() {
		return atorSelecionadoAtribuir;
	}

	public void setAtorSelecionadoAtribuir(Actor atorSelecionadoAtribuir) {
		this.atorSelecionadoAtribuir = atorSelecionadoAtribuir;
	}

	public WebMarkupContainer getPainelGestaoFilas() {
		return painelGestaoFilas;
	}

	public void setPainelGestaoFilas(WebMarkupContainer painelGestaoFilas) {
		this.painelGestaoFilas = painelGestaoFilas;
	}

	public User getUsuarioSelecionadoAtribuir() {
		return usuarioSelecionadoAtribuir;
	}

	public void setUsuarioSelecionadoAtribuir(User usuarioSelecionadoAtribuir) {
		this.usuarioSelecionadoAtribuir = usuarioSelecionadoAtribuir;
	}
}
