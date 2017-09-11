package br.com.seta.processo.usuario;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.dtobonita.Contatos;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.DadosUsuarioService;

public class Perfil extends Templete {
	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	private Contatos contatosProfissional, contatosPessoais;
	
	@Inject
	DadosUsuarioService usuarioService;
	private transient User user = (User) Session.get().getAttribute("user");
	
	public Perfil() throws BonitaException {
		setTituloPagina("Meu Perfil");
		
		this.usuario = usuarioService.recuperaUsuario(user);
		this.contatosProfissional = usuarioService.recuperaContatosProfissionais(user);
		this.contatosPessoais = usuarioService.recuperaContatosPessoais(user);
	}
	
	private class DadosGeraisContainer extends WebMarkupContainer {
		
		private static final long serialVersionUID = 1L;
		private TextField<String> nome, sobrenome, titulo, cargo, nomeUsuario;
		private PasswordTextField senha, confirmacaoSenha;
		
		public DadosGeraisContainer(String id, Usuario usuario) {
			super(id, new CompoundPropertyModel<Usuario>(usuario));
			
			nome = new TextField<String>("nome");
			sobrenome = new TextField<String>("sobrenome");
			titulo = new TextField<String>("titulo");
			cargo = new TextField<String>("cargo");
			nomeUsuario = new TextField<String>("nomeUsuario");
		}
		
	}
}
