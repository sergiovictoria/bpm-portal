package br.com.seta.processo.authentication;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.value.ValueMap;

import br.com.seta.processo.page.ProcessoWebPage;
import br.com.seta.processo.service.gestor.acesso.interfaces.PermissoesService;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AutorizacaoException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.BusinessException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTOV2;
import br.com.seta.processo.utils.GestorAcessoConstants;

public class SignIn extends ProcessoWebPage {

	private static final long serialVersionUID = 1L;	
	
	@Inject	private PermissoesService permissoesService;
	
	public SignIn() {
		add(new SignInForm("signInForm"));
	}
	
	public final class SignInForm extends Form<Void> {
		
		private static final long serialVersionUID = 1L;
		private static final String USERNAME = "username";
		private static final String PASSWORD = "password";
		private final ValueMap properties = new ValueMap();
		private AlertaContainer mensagemContainer;
		
		public SignInForm(final String id) {
			super(id);
			
			mensagemContainer = (AlertaContainer) new AlertaContainer("alertaContainer").setOutputMarkupId(true);
			mensagemContainer.setVisibilidade(false);
			
			add(new TextField<String>(USERNAME, new PropertyModel<String>(properties, USERNAME)));
			add(new PasswordTextField(PASSWORD, new PropertyModel<String>(properties, PASSWORD)));
			add( mensagemContainer);
			add(new AjaxButton("login") {
				private static final long serialVersionUID = 1L;
				@Override
				public void onSubmit(AjaxRequestTarget target, Form<?> form) {
					SignInSession session = getMySession();
					if (session.signIn(getUsername(), getPassword())) {	
						try {
							Session.get().setAttribute("user", session.getUser());
							Session.get().setAttribute(GestorAcessoConstants.USUARIO, session.getUsuario());
							Session.get().setAttribute(GestorAcessoConstants.ACESSO, session.getAcessoDto());							
							br.com.seta.processo.authentication.Permissoes permissoes = getPermissoes(session.getAcessoDto());							
							Session.get().setAttribute(GestorAcessoConstants.PERMISSOES_USUARIO, permissoes);							
							Session.get().bind();
							
							continueToOriginalDestination();
							setResponsePage(getApplication().getHomePage());
						} catch (AutorizacaoException | BusinessException e) {
							getSession().invalidate();
							getSession().clear();
							e.printStackTrace();
							
							exibeMensagemErro(target, "Ocorreu um erro interno");
						}						
					} else {
						String errmsg = null;
						if(session.getErro() != null && !session.getErro().isEmpty())
							errmsg = session.getErro();
						else
							errmsg = getString("loginError", null, getLocalizer().getString("InvalidUser", this));
						
						exibeMensagemErro(target, errmsg);
					}
				}
				
				private void exibeMensagemErro(AjaxRequestTarget target, String errmsg) {
					mensagemContainer.setMensagem(errmsg);
					mensagemContainer.setVisibilidade(true);
					target.add(mensagemContainer);
				}
				
			});
		}
		
//		@Override
//		public void renderHead(IHeaderResponse response) {
//		  response.render(CssHeaderItem.forUrl("./resources/login.css"));
//		}

		private String getPassword() {
			return properties.getString(PASSWORD);
		}


		private String getUsername() {
			return properties.getString(USERNAME);
		}
		

		private SignInSession getMySession() {
			return (SignInSession) getSession();
		}		
	}
	
	private class AlertaContainer extends WebMarkupContainer{
	
		private static final long serialVersionUID = 1L;
		
		private Label lblMensagem;
		private WebMarkupContainer alerta;
		
		public AlertaContainer(String id) {
			super(id);
			this.alerta = new WebMarkupContainer("alerta");
			this.lblMensagem = new Label("lblMensagem", Model.of(""));
			this.alerta.add(this.lblMensagem);
			
			add(this.alerta);
		}
		
		public void setMensagem(String mensagem){
			this.lblMensagem.setDefaultModelObject(mensagem);
		}
		
		public void setVisibilidade(boolean visibilidade){
			this.alerta.setVisible(visibilidade);
		}
	}
	
	private br.com.seta.processo.authentication.Permissoes getPermissoes(AcessoDTO acessoDto) throws AutorizacaoException, BusinessException{
		List<PermissaoTagDTOV2> permissoes = permissoesService.listaPermissoesUsuario(acessoDto, GestorAcessoConstants.APLICACAO_BPM_SETA);
		
		Map<String, List<PermissaoTagDTOV2>> permissoesPorModulo = new HashMap<String, List<PermissaoTagDTOV2>>();
		
		for(PermissaoTagDTOV2 permissao : permissoes){
			String modulo = permissao.getModulo();
			List<PermissaoTagDTOV2> permissoesDoModulo = permissoesPorModulo.get(modulo);
			
			if(permissoesDoModulo == null){ 
				permissoesDoModulo = new ArrayList<PermissaoTagDTOV2>();
				permissoesPorModulo.put(modulo, permissoesDoModulo);
			}
			
			permissoesDoModulo.add(permissao);
		}			
			
		return new br.com.seta.processo.authentication.Permissoes(permissoesPorModulo);
	}
}
