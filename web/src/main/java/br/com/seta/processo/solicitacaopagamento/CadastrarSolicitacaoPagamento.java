package br.com.seta.processo.solicitacaopagamento;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.dtobonita.Contatos;
import br.com.seta.processo.dtobonita.Usuario;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.DadosUsuarioService;
import br.com.seta.processo.solicitacaopagamento.componentespagina.AnexarBoletoPanel;
import br.com.seta.processo.solicitacaopagamento.componentespagina.EnvioSolicPagamentoPanel;

/**
 * Controller da página CadastrarSolicitacaoPagamento.html. Representa a atividade de cadastro de uma nova solicitação de pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class CadastrarSolicitacaoPagamento extends SolicitacaoPagamentoTemplate {
	private static final long serialVersionUID = 1L;	
	
	@Inject
	private DadosUsuarioService dadosUsuarioService;	
	
	public CadastrarSolicitacaoPagamento(PageParameters pageParameters) throws ClientProtocolException, IOException, ParseException, InstantiationException, IllegalAccessException, HttpStatusException, BonitaException{
		super(pageParameters);
		setTituloPagina("Cadastrar Solicitação de Pagamento");
		AnexarBoletoPanel secaoAnexo = new AnexarBoletoPanel("secaoAnexo");
		adicionaSecaoAnexo(secaoAnexo);
		addSecaoBotoes(new EnvioSolicPagamentoPanel("secaoBotoes", this, secaoAnexo));
				
		preencheDadosSolicitante();		
	}	
	
	private String getNomeSolicitante(Usuario usuario){
		String primeiroNome = usuario.getNome();
		
		if(primeiroNome != null && !primeiroNome.trim().isEmpty()){
			String sobrenome = usuario.getSobrenome();
			if(sobrenome != null){
				return primeiroNome + " " + sobrenome;
			}
			return primeiroNome;
		}		
		
		return usuario.getNomeUsuario();
	}
	
	private void preencheDadosSolicitante() throws BonitaException{
		Usuario usuario = dadosUsuarioService.recuperaUsuario(getUser());
		Contatos contatosProfissionais = dadosUsuarioService.recuperaContatosProfissionais(getUser());
		SolicitacaoPagamento solicitacaoPagamento = getSolicitacaoPagamento();
		
		String nomeSolicitante = getNomeSolicitante(usuario);
		String loginSolicitante = usuario.getNomeUsuario();
		long idSolicitante = usuario.getId();
		String email = contatosProfissionais.getEmail();
		String telefone = contatosProfissionais.getTelefone();		
		
		solicitacaoPagamento.setNomeSolicitante(nomeSolicitante);
		solicitacaoPagamento.setUserNameSolicitante(loginSolicitante);
		solicitacaoPagamento.setEmailSolicitante(email);
		solicitacaoPagamento.setTelefoneSolicitante(telefone);
		solicitacaoPagamento.setIdSolicitante(idSolicitante);
	}
}
