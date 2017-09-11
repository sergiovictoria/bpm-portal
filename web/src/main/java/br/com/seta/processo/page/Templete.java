package br.com.seta.processo.page;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxEventBehavior;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.Model;

import br.com.seta.processo.authentication.AuthenticatedWebPage;
import br.com.seta.processo.authentication.SignOut;
import br.com.seta.processo.componentes.messagebox.MessageBox;
import br.com.seta.processo.componentes.messagebox.MessageBoxLoadingGif;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.gestaofilas.DashboardGestaoFilas;
import br.com.seta.processo.pagecomponentes.menus.ItemMenu;
import br.com.seta.processo.pagecomponentes.menus.LinkMenu;
import br.com.seta.processo.provider.ProcessProvider;
import br.com.seta.processo.provider.ConsultasProvider;
import br.com.seta.processo.provider.ParametrosProvider;
import br.com.seta.processo.provider.RelatoriosProvider;
import br.com.seta.processo.provider.ServicosProvider;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.usuario.PerfilUsuario;

public class Templete extends ProcessoWebPage implements AuthenticatedWebPage {
	
	private static final long serialVersionUID = 1L;
	private MessageBox bloqueioMB;
	private Label tituloPagina, dadosAdicionais;
	private MensagemErroContainer msgErroContainer;
	private MensagemAtencaoContainer msgAtencaoContainer;
	private DataView<TaskProcess> processosDataView;
	
	@Inject	private ExecuteRestAPI restAPI;
	private transient User user = (User) Session.get().getAttribute("user");

	public Templete() {		
		User user = (User) Session.get().getAttribute("user");

		this.tituloPagina = new Label("tituloPagina", Model.of(""));
		this.dadosAdicionais = new Label("dadosAdicionais", Model.of(""));

		add(new Label("usuarioLogado", user.getUserName()));		

		Link<String> home = new Link<String>("home") {
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {				
				setResponsePage(DashboardGestaoFilas.class);
			}
		};
		
		Link<String> perfilUsuario = new Link<String>("perfilUsuario"){
			private static final long serialVersionUID = 1L;
			@Override
			public void onClick() {
				setResponsePage(PerfilUsuario.class);				
			}			
		};		
		
		this.processosDataView = new ProcessosDataView("processos", new ProcessProvider());
		WebMarkupContainer opcaoProcessos = (WebMarkupContainer) new WebMarkupContainer("opcaoProcessos").add(processosDataView);
		
		if(processosDataView.getDataProvider().size() == 0){
			opcaoProcessos.setVisible(false);
		}
		ItemMenu atividadesAbertas = new ItemMenu("atividadesAbertas", "Atividades abertas", new String[]{"fa fa-tasks"}, Atividades.class);	
		ItemMenu atividadesEncerradas = new ItemMenu("atividadesEncerradas", "Atividades encerradas", new String[]{"fa fa-tasks fa-stack-2x", "fa fa-check fa-stack-1x"}, AtividadesEncerradas.class);
		LinkMenu opcaoRelatorios = new LinkMenu("opcaoRelatorios", "Relatórios", "fa fa-file-text-o", new RelatoriosProvider());
		LinkMenu opcaoConsultas = new LinkMenu("opcaoConsultas", "Consultas", "fa fa-sticky-note-o", new ConsultasProvider());		
		LinkMenu opcaoParametros = new LinkMenu("opcaoParametros", "Parâmetros", "fa fa-cogs", new ParametrosProvider());
		LinkMenu opcaoServicos = new LinkMenu("opcaoServicos", "Serviços", "fa fa-magic", new ServicosProvider());	
		
		this.bloqueioMB = new MessageBox("bloqueioMb", MessageBoxLoadingGif.PROGRESS2, "Carregando...");
		this.msgErroContainer = new MensagemErroContainer("msgErroContainer");
		this.msgAtencaoContainer = new MensagemAtencaoContainer("msgAtencaoContainer");
		
		add(home, atividadesAbertas, atividadesEncerradas);	
		add(perfilUsuario);
		add((new BookmarkablePageLink<Void>("SignOut", SignOut.class)));
		add(bloqueioMB);
		add(tituloPagina, dadosAdicionais);
		add(msgErroContainer, msgAtencaoContainer);
		add(opcaoProcessos, opcaoParametros, opcaoConsultas, opcaoRelatorios, opcaoServicos);
	}

	public MessageBox getBloqueioMessageBox(){
		return this.bloqueioMB;
	}

	public void setTituloPagina(String tituloPagina){
		this.tituloPagina.setDefaultModelObject(tituloPagina);
	}
	
	public void setDadosAdicionais(String dadosAdicionais){
		this.dadosAdicionais.setDefaultModelObject(dadosAdicionais);
	}
	
	public void exibeMensagemSucesso(String titulo, String mensagem, AjaxRequestTarget target){
		String js = "exibirMensagemSucesso(\"{titulo}\", \"{mensagem}\");".replace("{titulo}", titulo).replace("{mensagem}", mensagem);
		target.appendJavaScript(js);
	}
	
	public void exibeMensagemInformacao(String titulo, String mensagem, AjaxRequestTarget target){
		String js = "exibirMensagemInformacao(\"{titulo}\", \"{mensagem}\");".replace("{titulo}", titulo).replace("{mensagem}", mensagem);
		target.appendJavaScript(js);
	}
	
	public void exibeMensagemAdvertencia(String titulo, String mensagem, AjaxRequestTarget target){
		String js = "exibirMensagemAdvertencia(\"{titulo}\", \"{mensagem}\");".replace("{titulo}", titulo).replace("{mensagem}", mensagem);
		target.appendJavaScript(js);
	}	

	/**
	 * Ajusta a mensagem exibida na caixa de diálogo de atenção
	 * 
	 * @param mensagemAtencao mensagem de atencao
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagemAtencao(String mensagemAtencao, AjaxRequestTarget target){		
		this.msgAtencaoContainer.setMensagemAtencao(mensagemAtencao);
		showMensagemErro(target);
	}
	
	/**
	 * Exibe uma caixa de mensagem de erro
	 * 
	 * @param mensagemErro mensagem de erro
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagemErro(String mensagemErro, AjaxRequestTarget target){		
		this.msgErroContainer.setMensagemErro(mensagemErro);
		showMensagemErro(target);
	}
	
	/**
	 * Ajusta a mensagem exibida na caixa de diálogo de atenção
	 * 
	 * @param mensagemAtencao mensagem de atenção
	 * @param cabecalhoMensagem 
	 * @param target objeto para chamada Ajax
	 */
	public void setMensagemAtencao(String mensagemAtencao, String cabecalhoMsgAtencao, AjaxRequestTarget target){
		this.msgAtencaoContainer.setCabelhoMsgAtencao(cabecalhoMsgAtencao);
		setMensagemAtencao(mensagemAtencao, target);
	}
	
	/**
	 * Exibe uma caixa de mensagem de erro
	 * 
	 * @param mensagemErro mensagem de erro
	 * @param cabecalhoMensagem 
	 * @param target objeto para chamada Ajax
	 */
	public void setMensagemErro(String mensagemErro, String cabecalhoMensagem, AjaxRequestTarget target){
		this.msgErroContainer.setCabelhoMensagemErro(cabecalhoMensagem);
		setMensagemErro(mensagemErro, target);
	}
	
	/**
	 * Exibe uma caixa de diálogo contendo as mensagens de erro
	 * 
	 * @param mensagensAtencao lista contendo as mensagens de erro
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagensAtencao(List<String> mensagensAtencao, AjaxRequestTarget target){		
		this.msgAtencaoContainer.setMensagensAtencao(mensagensAtencao);
		showMensagemAtencao(target);		
	}
	
	/**
	 * Exibe uma caixa de diálogo contendo as mensagens de erro
	 * 
	 * @param mensagensErro lista contendo as mensagens de erro
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagensErro(List<String> mensagensErro, AjaxRequestTarget target){		
		this.msgErroContainer.setMensagensErro(mensagensErro);
		showMensagemErro(target);
	}
	
	public void setMensagensErro(List<String> mensagensErro, AjaxRequestTarget target, String... callbackFunction){		
		this.msgErroContainer.setMensagensErro(mensagensErro);
		setCallbackFunctions(target, callbackFunction);
		showMensagemErro(target);
	}
	
	/**
	 * Exibe uma caixa de diálogo contendo as mensagens de atenção
	 * 
	 * @param mensagensAtencao lista contendo as mensagens de atenção
	 * @param cabecalhoMensagem mensagem de cabeçalho para a lista de mensagens de atenção
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagensAtencao(List<String> mensagensAtencao, String cabecalhoMensagem, AjaxRequestTarget target){		
		this.msgAtencaoContainer.setCabelhoMsgAtencao(cabecalhoMensagem);
		setMensagensAtencao(mensagensAtencao, target);
	}
	
	/**
	 * Exibe uma caixa de diálogo contendo as mensagens de erro
	 * 
	 * @param mensagensErro lista contendo as mensagens de erro
	 * @param cabecalhoMensagem mensagem de cabeçalho para a lista de erros
	 * @param target Objeto para chamada Ajax 
	 */
	public void setMensagensErro(List<String> mensagensErro, String cabecalhoMensagem, AjaxRequestTarget target){		
		this.msgErroContainer.setCabelhoMensagemErro(cabecalhoMensagem);
		setMensagensErro(mensagensErro, target);
	}
	
	/**
	 * Fecha uma caixa de mensagem utilizando um comando javascript
	 * @param AjaxRequestTarget Objeto para chamada Ajax 
	 */
	public void close(AjaxRequestTarget target, Component container){
		target.add(container);
		target.appendJavaScript("$.magnificPopup.close();");
	}
	
	/**
	 * Exibe um modal de carregamento
	 * 
	 * @param target Objeto para chamada Ajax
	 */
	public void exibeCarregamento(AjaxRequestTarget target){
		target.appendJavaScript("exibeCarregamento()");
	}	
	
	/**
	 * Exibe um modal de carregamento
	 * 
	 * @param target Objeto para chamada Ajax
	 * @param callbackFunctions Uma ou mais funções/scripts javascripts chamados como callback, após a exibição do modal
	 */
	public void exibeCarregamento(AjaxRequestTarget target, String... callbackFunctions){
		exibeCarregamento(target);		
		setCallbackFunctions(target, callbackFunctions);
	}

	/**
	 * Oculta o modal de carregamento
	 * 
	 * @param target Objeto para chamada Ajax
	 */
	public void ocultaCarregamento(AjaxRequestTarget target){
		target.appendJavaScript("ocultaCarregamento()");
	}
	
	/**
	 * Oculta o modal de carregamento
	 * 
	 * @param target Objeto para chamada Ajax
	 * @param callbacks Uma ou mais funções/scripts javascripts chamados como callback, após a ocultação do modal
	 */
	public void ocultaCarregamento(AjaxRequestTarget target, String... callbacks){
		ocultaCarregamento(target);		
		setCallbackFunctions(target, callbacks);
	}
	
	/**
	 * Exibe uma caixa de mensagem de erro
	 * 
	 * @param target
	 */
	private void showMensagemErro(AjaxRequestTarget target){
		this.show("modalMensagemErro", this.msgErroContainer, target);
	}
	
	/**
	 * Exibe uma caixa de diálogo de atenção
	 * 
	 * @param target
	 */
	private void showMensagemAtencao(AjaxRequestTarget target){
		this.show("modalMensagemAtencao", this.msgAtencaoContainer, target);
	}

	/**
	 * Exibe uma caixa de mensagem qualquer
	 * 
	 * @param htmlId Id do componente de caixa de mensagme no arquivo html
	 * @param container Componente Wicket que servirá de container para a caixa de mensagem
	 * @param target Objeto para chamada Ajax
	 */
	private void show(String htmlId, Component container, AjaxRequestTarget target){
		String js = "$('#" + htmlId + "').modal('show')";
		target.add(container);
		target.appendJavaScript(js);
	}
	
	public User getUser(){
		return (User) Session.get().getAttribute("user");
	}

	/**
	 * WebMarkupContainer que renderiza uma caixa de mensagem de erro
	 * 
	 * @author Hromenique Cezniowscki Leite Batista	 *
	 */
	private class MensagemErroContainer extends WebMarkupContainer{
		
		private static final long serialVersionUID = 1L;

		private Label cabecalhoModalErro;
		private Label cabelhoMsgErro;
		private RepeatingView msgsErroRepeatingView;
		
		public MensagemErroContainer(String id) {
			super(id);
			this.cabecalhoModalErro = new Label("cabecalhoModalErro", Model.of("Erro"));
			this.cabelhoMsgErro = new Label("cabelhoMsgErro", Model.of(""));
			this.msgsErroRepeatingView = new RepeatingView("msgsErroRepeatingView");		
			this.setOutputMarkupId(true);
			
			add(cabecalhoModalErro, cabelhoMsgErro, msgsErroRepeatingView);			
		}
		
//		public void setCabecalhoModalErro(String cabelho){
//			this.cabecalhoModalErro.setDefaultModelObject(cabelho);
//		}
		
		public void setCabelhoMensagemErro(String cabelhoMsgErro){
			this.cabelhoMsgErro.setDefaultModelObject(cabelhoMsgErro);
		}
		
		public void setMensagemErro(String mensagemErro){
			this.msgsErroRepeatingView.removeAll();
			
			String id = this.msgsErroRepeatingView.newChildId();
			this.msgsErroRepeatingView.add(new Label((id), mensagemErro));
		}
		
		public void setMensagensErro(List<String> mensagensErro){
			this.msgsErroRepeatingView.removeAll();
			
			for(String mensagem : mensagensErro){
				String id = this.msgsErroRepeatingView.newChildId();
				this.msgsErroRepeatingView.add(new Label((id), mensagem));
			}
		}
	}
	
	/**
	 * WebMarkupContainer que renderiza uma caixa de diálogo de mensagens de atenção
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class MensagemAtencaoContainer extends WebMarkupContainer{
		
		private static final long serialVersionUID = 1L;

		private Label cabecalhoModalAtencao;
		private Label cabelhoMsgAtencao;
		private RepeatingView msgsAtencaoRepeatingView;
		
		public MensagemAtencaoContainer(String id) {
			super(id);
			this.cabecalhoModalAtencao = new Label("cabecalhoModalAtencao", Model.of("Atenção"));
			this.cabelhoMsgAtencao = new Label("cabelhoMsgAtencao", Model.of(""));
			this.msgsAtencaoRepeatingView = new RepeatingView("msgsAtencaoRepeatingView");		
			this.setOutputMarkupId(true);
			
			add(cabecalhoModalAtencao, cabelhoMsgAtencao, msgsAtencaoRepeatingView);			
		}
		
//		public void setCabecalhoModalAtencao(String cabelho){
//			this.cabecalhoModalAtencao.setDefaultModelObject(cabelho);
//		}
		
		public void setCabelhoMsgAtencao(String cabecalhoMsgAtencao){
			this.cabelhoMsgAtencao.setDefaultModelObject(cabecalhoMsgAtencao);
		}
		
		public void setMensagemAtencao(String mensagemAtencao){
			this.msgsAtencaoRepeatingView.removeAll();
			
			String id = this.msgsAtencaoRepeatingView.newChildId();
			this.msgsAtencaoRepeatingView.add(new Label((id), mensagemAtencao));
		}
		
		public void setMensagensAtencao(List<String> mensagensAtencao){
			this.msgsAtencaoRepeatingView.removeAll();
			
			for(String mensagem : mensagensAtencao){
				String id = this.msgsAtencaoRepeatingView.newChildId();
				this.msgsAtencaoRepeatingView.add(new Label((id), mensagem));
			}
		}
	}
	
	/**
	 * DataView customizado para listar os processos no menu lateral do Portal BPM Seta
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class ProcessosDataView extends DataView<TaskProcess>{
	
		private static final long serialVersionUID = 1L;
		
		protected ProcessosDataView(String id, IDataProvider<TaskProcess> dataProvider) {
			super(id, dataProvider);			
		}

		@Override
		protected void populateItem(Item<TaskProcess> item) {
			final TaskProcess processo  = (TaskProcess) item.getModelObject();
			Label nomeProcesso     = new Label("nomeProcesso", processo.getName());
			Label versaoProcesso   = new Label("versaoProcesso", processo.getVersion());
			/*Ao clicar no processo, devemos direcionar para a página de instanciação de processo*/
			AjaxEventBehavior onClickEvento = new AjaxEventBehavior("click") {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onEvent(AjaxRequestTarget target) {					
					RedirectPage instantiationPage;
					try {
						instantiationPage = criaPaginaDeInstanciacao(processo);
						setResponsePage(instantiationPage);
					} catch (IOException | HttpStatusException e) {
						throw new RuntimeException(e);
					}
				}
			};
			
			item.add(onClickEvento);
			item.add(nomeProcesso, versaoProcesso);
		}		
	}	
	
	private void setCallbackFunctions(AjaxRequestTarget target, String... callbackFunction) {
		for(String js : callbackFunction){
			target.appendJavaScript(js);
		}
	}

	private RedirectPage criaPaginaDeInstanciacao(final TaskProcess taskProcess) throws HttpStatus401Exception, ClientProtocolException,
			IOException, HttpStatusException {
		String nomeProcesso = taskProcess.getName();
		String versao = taskProcess.getVersion();
		br.com.seta.processo.dto.Form form = restAPI.retriveInstantiationForm(user, taskProcess.getId());
		String url = obtemContexto() + form.getUrl() + "?processo=" + nomeProcesso + "&versao="+ versao;
		RedirectPage instantiationPage = new RedirectPage(url);
		return instantiationPage;
	}
	

	
}