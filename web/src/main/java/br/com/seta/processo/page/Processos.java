package br.com.seta.processo.page;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.provider.ProcessProvider;
import br.com.seta.processo.service.ExecuteRestAPI;

public class Processos extends Templete {

	private static final long serialVersionUID = 1L;

	@Inject
	private ExecuteRestAPI executeRestAPI; 
	private final WebMarkupContainer processoContainer =  new WebMarkupContainer("processoContainer");
	private transient User user = (User) Session.get().getAttribute("user");
	
	public Processos() {
		add(new ProcessosForm("processoForm"));
		setTituloPagina("Processos");
	}

	private class ProcessosForm extends Form<Void> {	

		private static final long serialVersionUID = 1L;

		private DataView<TaskProcess> processosDataView;
		private ProcessProvider processProvider = new ProcessProvider();
		//PageParameters pageParameters = new PageParameters();

		public ProcessosForm(String id){
			super(id);
			processoContainer.setOutputMarkupId(true);
			processosDataView = new DataView<TaskProcess>("pessoasRow", processProvider) {
				private static final long serialVersionUID = 1L;
				@Override
				protected void populateItem(Item<TaskProcess> item) {
					final TaskProcess taskProcess  = (TaskProcess) item.getModelObject();
					Label nomeProcesso     = new Label("nomeProcesso", taskProcess.getName());
					Label versaoProcesso   = new Label("versaoProcesso", taskProcess.getVersion());
					Label descricaoProcesso= new Label("descricaoProcesso", taskProcess.getDescription());
					
					Button iniciarProcessoBtn = new Button("iniciarProcessoBtn"){
						private static final long serialVersionUID = 1L;
						@Override
						public void onSubmit() {							
							try {
								RedirectPage instantiationPage = criaPaginaDeInstanciacao(taskProcess);								
								setResponsePage(instantiationPage);
							} catch (IOException | HttpStatusException e) {								
								e.printStackTrace();
							}							
						}
			
					};
					
					item.add(nomeProcesso);
					item.add(versaoProcesso);
					item.add(descricaoProcesso);
					item.add(iniciarProcessoBtn);
				}
			};
			add(processoContainer,processosDataView);
			processoContainer.add(processosDataView);
		}
	}

	private RedirectPage criaPaginaDeInstanciacao(final TaskProcess taskProcess)
			throws HttpStatus401Exception, ClientProtocolException,
			IOException, HttpStatusException {
		String nomeProcesso = taskProcess.getName();
		String versao = taskProcess.getVersion();
		br.com.seta.processo.dto.Form form = executeRestAPI.retriveInstantiationForm(user, taskProcess.getId());
		String url = obtemContexto() + form.getUrl() + "?processo=" + nomeProcesso + "&versao="+ versao;
		RedirectPage instantiationPage = new RedirectPage(url);
		return instantiationPage;
	}
}
