package br.com.seta.processo.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bonitasoft.engine.identity.User;
import org.bonitasoft.engine.identity.UserUpdater;

import br.com.seta.processo.dtobonita.Usuario;

/**
 * Helper para conversão dos tipos do Bonita BPM referente a usuário para um objeto da classe br.com.seta.processo.dtobonita.Usuario
 * 
 * @author Hromenique Cezniowscki Leite Batista    
 *
 */
public class UsuarioHelper {

	public Usuario criaUsuario(User user){
		if(user == null) return null;
		
		Usuario usuario = new Usuario();
		usuario.setNome(user.getFirstName());
		usuario.setSobrenome(user.getLastName());
		usuario.setTitulo(user.getTitle());
		usuario.setCargo(user.getJobTitle());
		usuario.setNomeUsuario(user.getUserName());	
		usuario.setId(user.getId());
		
		return usuario;
	}
	
	public List<Usuario> criaUsuarios(Collection<User> users){
		List<Usuario> usuarios = new ArrayList<Usuario>();
		
		for(User user : users)
			usuarios.add(criaUsuario(user));
		
		return usuarios;
	}
	
	public UserUpdater criaUserUpdater(Usuario usuario){
		if(usuario == null) return null;
		
		UserUpdater updater = new UserUpdater();
		updater.setFirstName(usuario.getNome());
		updater.setLastName(usuario.getSobrenome());
		updater.setTitle(usuario.getTitulo());
		updater.setJobTitle(usuario.getCargo());
		updater.setUserName(usuario.getNomeUsuario());
		
		return updater;
	}
}
