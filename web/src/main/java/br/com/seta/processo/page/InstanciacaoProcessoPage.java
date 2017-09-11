package br.com.seta.processo.page;

import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.pages.RedirectPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.ExecuteRestAPI;

/**
 * 
 * Classe base para todas páginas de instanciação de processo. 
 * As classes que extendê-na devem utilizar o seu construtor com parâmetros para receber e tratar os dados vindos na URL (url query string).
 * A classe já é preparada para atender uma url no formato {endereço}?processo={nome do processo}&versao={versão do processo}. 
 * Onde:<br>
 * {nome do processo} é o nome do processo  <br>
 * {versão do processo} é a versão do processo <br>
 * 
 * Ex: localhost:8080/processos/iniciarProcesso?processo=Meu Processo&versao=2.1
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class InstanciacaoProcessoPage extends Templete {
	

	
	private static final long serialVersionUID = 1L;
	private final static String PROCESSO_NOME = "processo";
	private final static String PROCESSO_VERSAO = "versao";
	private String nomeProcesso;
	private String versaoProcesso;
	private transient User usuario;
	
	@Inject
	private ExecuteRestAPI restApi;
		
	
	/**
	 * Construtor que default que recebe os parâmetros da URL
	 * 
	 * @param pageParameters 
	 */
	public InstanciacaoProcessoPage(PageParameters pageParameters){
		this.nomeProcesso = pageParameters.get(PROCESSO_NOME).toString();	
		this.versaoProcesso = pageParameters.get(PROCESSO_VERSAO).toString();
		usuario = (User) Session.get().getAttribute("user");
	}
	
	/**
	 * Instância um novo case (instância do processo) enviando dados para sua inicialização
	 * 
	 * @param campo Nome da váriável de processo (process variable) contida em um processo do Bonita BPM
	 * @param variavel Objeto contendo os valores para serem enviado para o Bontia BPM
	 * @throws BonitaException
	 */
	public void executeCaseWithVariable(String campo, Object variavel) throws BonitaException {
		restApi.execueteCaseWithVariable(usuario.getUserName(), usuario.getPassword(), variavel, campo, nomeProcesso, versaoProcesso);
	}
	
	public void executeCaseWithVariable(Map<String, Object> listVariablesSerializable) throws BonitaException {
		restApi.execueteCaseWithVariable(usuario.getUserName(), usuario.getPassword(), listVariablesSerializable, nomeProcesso, versaoProcesso);
	}
	
	/**
	 * Redireciona para a página de Atividades do Portal
	 */
	public void redirecionaParaPaginaDeAtividades(){
		String pageName = Atividades.class.getSimpleName();
		String contexto = getRequest().getContextPath();
		String url = contexto + "/" + pageName;
		setResponsePage(new RedirectPage(url));
	}
	
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	
	public String getVersaoProcesso() {
		return versaoProcesso;
	}
}
