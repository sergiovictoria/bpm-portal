package br.com.seta.processo.usuario;

import java.util.List;
import java.util.ArrayList;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.model.Model;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.authorization.AcessoService;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.page.Templete;
import br.com.seta.processo.service.DadosUsuarioService;

/**
 * Controller para a página de AlterarSenha.html
 * 
 * @author Hromenique Cezniowscki Leite Batista
 */
public class AlterarSenha extends Templete{
	private static final long serialVersionUID = 1L;

	private PasswordTextField senhaInput, confirmacaoSenhaInput;
	private AjaxButton alterarSenhaBtn;
	private Form<Void> formAlteracaoSenha;
	
	@Inject
	private DadosUsuarioService usuarioService;
	@Inject
	private AcessoService acessoService;
	
	private transient User user = (User) Session.get().getAttribute("user");
	
	public AlterarSenha(){
		setTituloPagina("Alterar Senha");
		this.senhaInput = (PasswordTextField) new PasswordTextField("senha", Model.of(""))
			.setRequired(false)
			.setOutputMarkupId(true);
		this.confirmacaoSenhaInput = (PasswordTextField) new PasswordTextField("confirmacaoSenha", Model.of(""))
			.setRequired(false)
			.setOutputMarkupId(true);
		this.formAlteracaoSenha = new Form<Void>("formAlteracaoSenha");
		this.alterarSenhaBtn = new AlterarSenhaBtn("alterarSenhaBtn");
		
		formAlteracaoSenha.add(senhaInput, confirmacaoSenhaInput, alterarSenhaBtn);
		add(formAlteracaoSenha);
	}
	
	/**
	 * Botão com função de executar a alteração de senha
	 * 
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	private class AlterarSenhaBtn extends AjaxButton{
		private static final long serialVersionUID = 1L;

		public AlterarSenhaBtn(String id) {
			super(id);
		}
		
		@Override
		protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
			String senha = (String) senhaInput.getDefaultModelObject();
			String confirmacaoSenha = (String) confirmacaoSenhaInput.getDefaultModelObject();
			
			try {
				if (senhasPreenchidas(target, senha, confirmacaoSenha)
						&& senhasIguais(target, senha, confirmacaoSenha)
						&& novaSenhaValida(target, senha)) {
					
					usuarioService.alteraSenha(user, senha);
					relogaUsuario(senha);					
					exibeMensagemSucesso("Sucesso", "Sua senha foi alterada com sucesso", target);
					limpaCampos();
					target.add(senhaInput, confirmacaoSenhaInput);
				}
			} catch (BonitaException e) {
				throw new RuntimeException(e);
			}			
		}			
	}
	
	/**
	 * Verifica se a senha e a confirmação de senha foram preenchidas
	 * 
	 * @param target
	 * @param senha
	 * @param confirmacaoSenha
	 * @return
	 */
	private boolean senhasPreenchidas(AjaxRequestTarget target, String senha, String confirmacaoSenha){
		if(senha == null || confirmacaoSenha == null || senha.isEmpty() || confirmacaoSenha.isEmpty()){
			setMensagemErro("Favor preencher os campos de nova senha e confirmação de senha", target);
			return false;
		}		
		return true;
	}
	
	/**
	 * Verifica se a senha e a confirmação de senha são iguais
	 * 
	 * @param target
	 * @param senha
	 * @param confirmacaoSenha
	 * @return
	 */
	private boolean senhasIguais(AjaxRequestTarget target, String senha, String confirmacaoSenha){
		if(senha.equals(confirmacaoSenha)){
			return true;
		}		
		setMensagemErro("A nova senha e a confirmação estão diferentes", target);
		return false;
	}
	
	/**
	 * Valida se a nova senha está no formato válido
	 * 
	 * @param target
	 * @param senha
	 * @return
	 */
	private boolean novaSenhaValida(AjaxRequestTarget target, String senha){		
		if(senhaValida(senha)) return true;
		
		List<String> erros = new ArrayList<String>();
		erros.add("Possuir no mínimo 8 caracteres");
		erros.add("Possuir pelo menos uma letra maiúscula");
		erros.add("Conter apenas caracteres alfanuméricos");		
		
		setMensagensErro(erros, target);
		return false;
	}
	
	/**
	 * Valida se a senha está no formato correto: <br>
	 * <ul>
	 * <li>Mínimo 8 caracteres</li>
	 * <li>Possuir pelo menos uma letra maiúscula</li>
	 * <li>Conter apenas caracteres alfanuméricos</li>
	 * </ul>
	 * 
	 * @param senha Senha a ser validada
	 * @return true, em caso da senha estar em formato válido, e false, em caso contrário
	 */
	private boolean senhaValida(String senha){
		String minimo8Caracteres = "^\\w{8,}$"; //no mínimo 8 caracteres alfanúmericos
		String minimoUmaLetraMaiuscula = "^\\w*[A-Z]+\\w*$"; //no mínimo uma letra maiúscula
		return senha.matches(minimo8Caracteres) && senha.matches(minimoUmaLetraMaiuscula);
	}
	
	/**
	 * Reloga o usuário e guardá-lo novamente na sessão
	 * 
	 * @param senha Nova senha com a qual o usuário será re-logado
	 */
	private void relogaUsuario(String senha) {
		user = acessoService.loginAs(user.getUserName(), senha);		
		Session.get().setAttribute("user", user);
	}	
	
	private void limpaCampos(){
		this.senhaInput.setDefaultModelObject("");
		this.confirmacaoSenhaInput.setDefaultModelObject("");
	}
}
