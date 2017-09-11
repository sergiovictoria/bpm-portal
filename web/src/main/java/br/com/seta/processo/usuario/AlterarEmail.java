package br.com.seta.processo.usuario;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.ContactData;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.DadosUsuarioService;

/**
 * Controller para a página de AlterarEmail.html
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AlterarEmail extends Templete {	
	private static final long serialVersionUID = 1L;
	
	private TextField<String> emailNovoInput, confirmacaoEmailInput, emailAntigoInput;
	private StatelessForm<Void> formAlteracaoEmail;
	private AjaxButton alterarEmailBtn;	
	
	private transient User user = (User) Session.get().getAttribute("user");
	
	@Inject
	DadosUsuarioService usuarioService;
	
	public AlterarEmail() throws BonitaException{
		setTituloPagina("Alterar E-mail");
		
		this.emailNovoInput = (TextField<String>) new TextField<String>("emailNovoInput", Model.of("")).setOutputMarkupId(true);
		this.confirmacaoEmailInput = (TextField<String>) new TextField<String>("confirmacaoEmailInput", Model.of("")).setOutputMarkupId(true);
		this.emailAntigoInput = (TextField<String>) new TextField<String>("emailAntigoInput", Model.of(getEmailAntigo(user))).setEnabled(false).setOutputMarkupId(true);
		this.alterarEmailBtn = new AlterarEmailBtn("alterarEmailBtn") ;
		
		this.formAlteracaoEmail = new StatelessForm<Void>("formAlteracaoEmail");
		formAlteracaoEmail.add(emailNovoInput, confirmacaoEmailInput, emailAntigoInput, alterarEmailBtn);
		add(formAlteracaoEmail);
	}
	
	/**
	 * Botão para executar a alteração do e-mail
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 */
	private class AlterarEmailBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;
		
		public AlterarEmailBtn(String id) {
			super(id);			
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			String emailNovo = (String) emailNovoInput.getDefaultModelObject();
			String confirmacaoEmail = (String) confirmacaoEmailInput.getDefaultModelObject();
			
			if(emailsPreenchidos(target, emailNovo, confirmacaoEmail) 
					&& emailsIguais(target, emailNovo, confirmacaoEmail) && emailsValidos(target, emailNovo, confirmacaoEmail)){
				
				try {
					emailAntigoInput.setDefaultModelObject(emailNovo); 
					limpaCamposInput();
					usuarioService.alteraEmail(user, emailNovo);
					
					exibeMensagemSucesso("Confirmação", "O e-mail foi atualizado com sucesso!", target);
					target.add(emailNovoInput, confirmacaoEmailInput, emailAntigoInput);
				} catch (BonitaException e) {
					throw new RuntimeException(e);
				}
				
			}			
		}
	}
	
	private boolean emailsIguais(AjaxRequestTarget target, String emailNovo, String confirmacaoEmail){
		if(emailNovo.equals(confirmacaoEmail))
			return true;
		
		setMensagemErro("O novo e-mail deve ser igual a confirmação de e-mail", target);
		return false;
	}
	
	private boolean emailsPreenchidos(AjaxRequestTarget target, String emailNovo, String confirmacaoEmail){
		if(emailNovo == null || confirmacaoEmail == null || emailNovo.isEmpty() || confirmacaoEmail.isEmpty()){			
			setMensagemErro("Os campos de novo e-mail e confirmação devem ser preenchidos", target);
			return false;
		}
		
		return true;
	}
	
	private boolean emailsValidos(AjaxRequestTarget target, String emailNovo, String confirmacaoEmail){
		String REGEX_EMAIL = "[a-zA-Z0-9][a-zA-Z0-9\\._-]+@([a-zA-Z0-9\\._-]+\\.)[a-zA-Z-0-9]{2,}";
		
		if(!emailNovo.matches(REGEX_EMAIL)){
			setMensagemErro("O novo e-mail deve está em um formato válido", target);
			return false;
		}
		
		if(!confirmacaoEmail.matches(REGEX_EMAIL)){
			setMensagemErro("A confirmação de e-mail deve está em um formato válido", target);
			return false;
		}
		
		return true;
	}
	
	public void limpaCamposInput(){
		this.emailNovoInput.setDefaultModelObject("");
		this.confirmacaoEmailInput.setDefaultModelObject("");
	}
	
	public String getEmailAntigo(User user) throws BonitaException{
		//ContactData dadosProfissionais = usuarioService.recuperaContatosProfissionais(user);
		//return dadosProfissionais.getEmail();
		return "";
	}	
}
