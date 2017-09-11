package br.com.seta.processo.usuario;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.ContactData;

import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.DadosUsuarioService;

public class PerfilUsuario extends Templete {
	private static final long serialVersionUID = 1L;
	
	private TextField<String> nomeInput, sobrenomeInput, usuarioInput, emailInput;
	private org.bonitasoft.engine.identity.User usuario;
	private ContactData dadosProfissionais;
	private Link<String> alterarSenhaBtn, alterarEmailBtn;
	
	@Inject
	DadosUsuarioService usuarioService;	
	
	@SuppressWarnings("unchecked")
	public PerfilUsuario() throws BonitaException{
		setTituloPagina("Meu Perfil");
		
		nomeInput = (TextField<String>) new TextField<String>("nomeInput", new PropertyModel<String>(usuario, "firstName")).setEnabled(false);
		sobrenomeInput =  (TextField<String>) new TextField<String>("sobrenomeInput", new PropertyModel<String>(usuario, "lastName")).setEnabled(false);
		usuarioInput = (TextField<String>) new TextField<String>("usuarioInput", new PropertyModel<String>(usuario, "lastName")).setEnabled(false);
		emailInput = (TextField<String>) new TextField<String>("emailInput", new PropertyModel<String>(dadosProfissionais, "email")).setEnabled(false);
		alterarSenhaBtn = new Link<String>("alterarSenhaBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AlterarSenha.class);				
			}
		};
		alterarEmailBtn = new Link<String>("alterarEmailBtn") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(AlterarEmail.class);				
			}
		};
		
		add(nomeInput, sobrenomeInput, usuarioInput, emailInput, alterarSenhaBtn, alterarEmailBtn);
	}

}
