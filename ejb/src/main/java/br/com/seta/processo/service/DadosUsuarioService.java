package br.com.seta.processo.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.exception.BonitaHomeNotSetException;
import org.bonitasoft.engine.exception.ServerAPIException;
import org.bonitasoft.engine.exception.UnknownAPITypeException;
import org.bonitasoft.engine.identity.ContactData;
import org.bonitasoft.engine.identity.ContactDataUpdater;
import org.bonitasoft.engine.identity.UserNotFoundException;
import org.bonitasoft.engine.identity.UserUpdater;
import org.bonitasoft.engine.session.APISession;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Contatos;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.helper.ContatosHelper;
import br.com.seta.processo.helper.UsuarioHelper;

/**
 * Serviço para a manipulação dos dados do usuário 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="DadosUsuarioService") 
@LocalBean
public class DadosUsuarioService extends BonitaService {	
	
	private UsuarioHelper usuarioHelper = new UsuarioHelper();
	private ContatosHelper contatosHelper = new ContatosHelper();	
	@Inject
	private  GroupService groupService;
	
	/**
	 * Recupera um usuario
	 * 
	 * @param user 
	 * @return 
	 * @throws BonitaException
	 */
	public Usuario recuperaUsuario(User user) throws BonitaException{
		try{
			org.bonitasoft.engine.identity.User usuario = getIdentityAPI(user).getUser(user.getIdUser());
			return usuarioHelper.criaUsuario(usuario);
		}catch(UserNotFoundException e){
			return null;
		}
	}
	
	public Usuario recuperaUsuario(User user, long userId) throws BonitaException{
		try{
			org.bonitasoft.engine.identity.User usuario = getIdentityAPI(user).getUser(userId);
			return usuarioHelper.criaUsuario(usuario);
		}catch(UserNotFoundException e){
			return null;
		}
		
	}
	
	public List<Usuario> recuperaUsuariosPorGrupo(String nomeGrupo) throws BonitaException{
		return recuperaUsuariosPorGrupo(nomeGrupo, 0, 1000000);
	}
	
	public List<Usuario> recuperaUsuariosPorGrupo(String nomeGrupo, int inicio, int quantidade) throws BonitaException{
		List<org.bonitasoft.engine.identity.User> users = groupService.findUserForGroups(nomeGrupo, inicio, quantidade);
		
		return usuarioHelper.criaUsuarios(users);
	}
	
	public void atribuiContatos(User user, Usuario usuario) throws BonitaException{
		Contatos contatosProfissionais = recuperaContatosProfissionais(user, usuario.getId());
		Contatos contatosPessoais = recuperaContatosPessoais(user, usuario.getId());
		
		usuario.setContatosPessoais(contatosPessoais);
		usuario.setContatosProfissionais(contatosProfissionais);
	}
	
	/**
	 * Recupera os contatos profissionais do usuário
	 * 
	 * @param user
	 * @return
	 * @throws BonitaException
	 */
	public Contatos recuperaContatosProfissionais(User user)throws BonitaException{
		ContactData contactData = getIdentityAPI(user).getUserContactData(user.getIdUser(), false);
		return contatosHelper.criaContato(contactData);
	}
	
	public Contatos recuperaContatosProfissionais(User user, long userId)throws BonitaException{
		ContactData contactData = getIdentityAPI(user).getUserContactData(userId, false);
		return contatosHelper.criaContato(contactData);
	}
	
	public Contatos recuperaContatosPessoais(User user, long userId) throws BonitaException{
		ContactData contactData = getIdentityAPI(user).getUserContactData(userId, true);
		return contatosHelper.criaContato(contactData);
	}
	
	/**
	 * Recupera os contatos pessoais do usuário
	 * 
	 * @param user
	 * @return
	 * @throws BonitaException
	 */
	public Contatos recuperaContatosPessoais(User user) throws BonitaException{
		ContactData contactData = getIdentityAPI(user).getUserContactData(user.getIdUser(), true);
		return contatosHelper.criaContato(contactData);
	}	

	/**
	 * Atualiza um usuário
	 * 
	 * @param usuario
	 * @param user
	 * @throws BonitaException
	 */
	public void atualizaUsuario(Usuario usuario, User user) throws BonitaException{
		UserUpdater updater = usuarioHelper.criaUserUpdater(usuario);
		getIdentityAPI(user).updateUser(user.getIdUser(), updater);
	}
	
	/**
	 * Altera os e-mails de contato pessoal e profissional
	 * 
	 * @param user Usuário para o qual será atualizado o e-mail
	 * @param novoEmail O novo e-mail que será atribuído aos contatos pessoal e professional
	 * @throws BonitaException 
	 */
	public void alteraEmail(User user, String novoEmail) throws BonitaException{
		UserUpdater updater = new UserUpdater();
		ContactDataUpdater contactData = new ContactDataUpdater();
		contactData.setEmail(novoEmail);
		updater.setPersonalContactData(contactData);
		updater.setProfessionalContactData(contactData);
		
		getIdentityAPI(user).updateUser(user.getIdUser(), updater);
	}
	
	/**
	 * Altera o e-mail de contato pessoal
	 * 
	 * @param user Usuário para o qual será atualizado o e-mail
	 * @param novoEmail O novo e-mail que será atribuído ao contato pessoal
	 * @throws BonitaException
	 */
	public void alteraEmailPessoal(User user, String novoEmail) throws BonitaException{
		UserUpdater updater = new UserUpdater();
		ContactDataUpdater contactData = new ContactDataUpdater();
		contactData.setEmail(novoEmail);
		updater.setPersonalContactData(contactData);
		
		getIdentityAPI(user).updateUser(user.getIdUser(), updater);
	}
	
	/**
	 * Altera o e-mail de contato pessoal
	 * 
	 * @param user Usuário para o qual será atualizado o e-mail
	 * @param novoEmail O novo e-mail que será atribuído ao contato professional
	 * @throws BonitaException
	 */
	public void alteraEmailProfissional(User user, String novoEmail)throws BonitaException{
		UserUpdater updater = new UserUpdater();
		ContactDataUpdater contactData = new ContactDataUpdater();
		contactData.setEmail(novoEmail);
		updater.setProfessionalContactData(contactData);
		
		getIdentityAPI(user).updateUser(user.getIdUser(), updater);
	}
	
	/**
	 * Altera a senha do usuário
	 * 
	 * @param user Usuário do qual a senha será alterada
	 * @param novaSenha A nova senha do usuário
	 * @throws BonitaException
	 */
	public void alteraSenha(User user, String novaSenha)throws BonitaException{
		IdentityAPI identityAPI = getIdentityAPI(user);
		UserUpdater updater = new UserUpdater();
		updater.setPassword(novaSenha);
		
		identityAPI.updateUser(user.getIdUser(), updater);
	}

	/**
	 * Retorna um objeto do tipo IdentiyApi, o qual é usado para a acessar a api de identificação do Bonita BPM
	 * 
	 * @param user
	 * @return
	 * @throws BonitaException
	 * @throws BonitaHomeNotSetException
	 * @throws ServerAPIException
	 * @throws UnknownAPITypeException
	 */
	private IdentityAPI getIdentityAPI(User user) throws BonitaException, BonitaHomeNotSetException, ServerAPIException,
			UnknownAPITypeException {
		APISession session = doTenantLogin(user);
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		return identityAPI;
	}	
}
