package br.com.seta.processo.gestaofilas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.ProcessoService;
import br.com.seta.processo.service.interfaces.DashboardService;
import br.com.seta.processo.service.interfaces.GestaoFilasService;

public class DashboardGestaoFilas extends Templete {

	@Inject
	private ProcessoService processoService;

	@Inject
	private GestaoFilasService gestaofilaService;

	@Inject
	private DashboardService dashboardService;

	private static final long serialVersionUID = 1L;
	private Label scriptGrfUm = new Label("scriptGrfUm", new Model<String>());

	private TaskProcess processoSelecionado;
	private List<TaskProcess> processos = new ArrayList<TaskProcess>();

	private Actor atorSelecionado;
	private List<Actor> atores = new ArrayList<Actor>();

	private User usuarioSelecionado;
	private List<User> usersBPM;
	
	private Label lblProcesso;
	private WebMarkupContainer painelDashboard;

	public DashboardGestaoFilas() throws BonitaException {
		setTituloPagina("Página Inicial");

		try {
			processos = processoService.listaProcessos(getUser());
			if (processos.size() > 0) {
				processoSelecionado = processos.get(0);
			}
			atores = gestaofilaService.listaTodosAtoresPorProcesso(getUser(), processoSelecionado);
			usersBPM = recuperaListaUsuarios(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado);
		} catch (BonitaException e) {
		}

		scriptGrfUm.setOutputMarkupId(true);
		scriptGrfUm.setEscapeModelStrings(false);

		scriptGrfUm.setDefaultModelObject(dashboardService.dashboardGestaoFilas("grfUm", getUser(), processoSelecionado, usersBPM));

		Form<Object> form = new Form<Object>("form");
	    lblProcesso = new Label("lblProcesso", processoSelecionado.getDisplayName());
	    
	    painelDashboard = (WebMarkupContainer) new WebMarkupContainer("painelDashboard").setOutputMarkupId(true);
	    painelDashboard.add(lblProcesso);
	    
		form.add(new ModalFiltro("modalFiltro"), painelDashboard);
		add(form, scriptGrfUm);
	}

	/**
	 * @return
	 * @throws BonitaException
	 */
	private List<User> recuperaListaUsuarios(User usuarioLogado, 
											 TaskProcess processoSelecionado, 
											 Actor atorSelecionado, 
											 User usuarioSelecionado) throws BonitaException {
		return gestaofilaService.listaTodosUsuariosPorProcesso(usuarioLogado, processoSelecionado, atorSelecionado,	usuarioSelecionado);
	}

	/*
	 * MODAL FILTRO
	 */
	private final class ModalFiltro extends WebMarkupContainer {
		private static final long serialVersionUID = 1L;

		public ModalFiltro(String id) {
			super(id);
		
			final DropDownChoice<TaskProcess> cmbProcesso = new DropDownChoice<TaskProcess>("cmbProcesso",  
				    new PropertyModel<TaskProcess> (DashboardGestaoFilas.this, "processoSelecionado"), processos,
				    new ChoiceRenderer<TaskProcess>("name"));

			final DropDownChoice<Actor> cmbAtor = new DropDownChoice<Actor>("cmbAtor",
					new PropertyModel<Actor>(DashboardGestaoFilas.this, "atorSelecionado"), atores,
					new ChoiceRenderer<Actor>("displayName")) {
				private static final long serialVersionUID = 1L;

				@Override
				protected String getNullValidDisplayValue() {
					return "Todos";
				}
			};
			cmbAtor.setNullValid(true).setOutputMarkupId(true);			

			final DropDownChoice<User> cmbUsuario = new DropDownChoice<User>("cmbUsuario",
					new PropertyModel<User>(DashboardGestaoFilas.this, "usuarioSelecionado"), usersBPM,
					new ChoiceRenderer<User>("nomeCompleto")) {
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
						usersBPM = recuperaListaUsuarios(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado);
						retiraUsuarioNaoAtribuidoDaListaUsuarios();
						scriptGrfUm.setDefaultModelObject(dashboardService.dashboardGestaoFilas("grfUm", getUser(),	processoSelecionado, usersBPM));
					} catch (BonitaException e) {
						e.printStackTrace();
					}
					lblProcesso.setDefaultModel(new Model<Serializable>(processoSelecionado.getDisplayName()));
					target.add(painelDashboard, scriptGrfUm);
				}

				/**
				 * Retirar usuario Não Atribuido da lista quando é selecionado um usuario especifico
				 */
				private void retiraUsuarioNaoAtribuidoDaListaUsuarios() {
					if (null != usuarioSelecionado) {
						User usuarioNãoAtribuido = null;
						for (User u : usersBPM) {
							if (u.getId() == 0) {
								usuarioNãoAtribuido = u;
							}
						}
						if (usuarioSelecionado.getId() != usuarioNãoAtribuido.getId()){
							usersBPM.remove(usuarioNãoAtribuido);							
						}
					}
				}
			};
			
			AjaxFormComponentUpdatingBehavior onChangeEventProcesso = new AjaxFormComponentUpdatingBehavior("change") {
				private static final long serialVersionUID = 1L;
			    @Override
			    protected void onUpdate(AjaxRequestTarget target) {
			    	try {
			    		cmbAtor.setChoices(gestaofilaService.listaTodosAtoresPorProcesso(getUser(), processoSelecionado));
			    		target.add(cmbAtor);
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
			    		cmbUsuario.setChoices(recuperaListaUsuarios(getUser(), processoSelecionado, atorSelecionado, usuarioSelecionado));
			    		target.add(cmbUsuario);
			    	} catch (BonitaException e) {
			    		throw new RuntimeException(e);
			    	}
			    }
			};
			cmbAtor.add(onChangeEventAtor);

			add(cmbProcesso, cmbAtor, cmbUsuario, btnPesquisar);
		}
	}

	@Override
	public void renderHead(IHeaderResponse response) {
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/fusioncharts.js"));
		response.render(JavaScriptHeaderItem.forUrl("resources/assets/fusioncharts/js/themes/fusioncharts.theme.fint.js"));
	}

	public final TaskProcess getProcessoName() {
		return processoSelecionado;
	}

	public final void setProcessoName(TaskProcess processoSelecionado) {
		this.processoSelecionado = processoSelecionado;
	}

	public final User getUsuario() {
		return usuarioSelecionado;
	}

	public final void setUsuario(User usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}

	public Actor getAtorSelecionado() {
		return atorSelecionado;
	}

	public void setAtorSelecionado(Actor atorSelecionado) {
		this.atorSelecionado = atorSelecionado;
	}

}
