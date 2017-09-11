package br.com.seta.processo.authentication;


import javax.inject.Inject;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import br.com.seta.processo.authorization.AcessoService;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.ProcessoWebPage;
import br.com.seta.processo.service.gestor.acesso.interfaces.GestorAcessoService;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.DadosUsuarioDTOV2;
import br.com.seta.processo.session.SessionUser;



public final class SignInSession extends AuthenticatedWebSession {

	@Inject	private AcessoService acessoService;
	@Inject private GestorAcessoService gestorAcessoService;
	private DadosUsuarioDTOV2 usuario;
	private AcessoDTO acessoDto;
	private String erro;
	private static final long serialVersionUID = 1L;
	private User user;

	public SignInSession(Request request) {
		super(request);
	}

	@Override
	public boolean authenticate(String username, String password) {
		this.usuario = null;
		try {
			usuario = gestorAcessoService.login(username, password, ProcessoWebPage.nomeAplicacao);
		} catch (Exception e) {
			this.erro = e.getMessage();
			return false;
		}
		
		if(usuario != null) {
			this.acessoDto = new AcessoDTO();
			this.acessoDto.setLogin(username);
			this.acessoDto.setSenha(password);
			
			User userActive = acessoService.loginAs(username, password);
			SessionUser.getInstance().setUser(userActive);
			if(userActive.getIsLogin()){
				this.user = userActive;
				return true;
			}else{
				return false;
			}
		} else {
			this.erro = "Não foi possível efetuar o acesso ao sistema.";
			return false;
		}
	}

	@Override
	public Roles getRoles() {
		Roles resultRoles = new Roles();
		if(isSignedIn()) {
			resultRoles.add("SIGNED_IN");
			resultRoles.add(Roles.ADMIN);
		}
		return null;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DadosUsuarioDTOV2 getUsuario() { return usuario; }
	public AcessoDTO getAcessoDto() { return acessoDto; }
	public String getErro() { return erro; }

}
