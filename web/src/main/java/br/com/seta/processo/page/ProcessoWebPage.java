package br.com.seta.processo.page;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Component;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.TransparentWebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.protocol.http.WebApplication;

import br.com.seta.processo.authentication.AuthenticatedWebPage;
import br.com.seta.processo.authentication.Permissoes;
import br.com.seta.processo.authentication.PermissoesSistema;
import br.com.seta.processo.authentication.SignInApplication;
import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.service.gestor.acesso.annotation.AcessoModulo;
import br.com.seta.processo.service.gestor.acesso.interfaces.GestorAcessoService;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AutorizacaoException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.BusinessException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ConsultaPermissaoWSV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.DadosUsuarioDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListaPermissaoDTORequest;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissao;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissaoResponse;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTOV2;
import br.com.seta.processo.utils.GestorAcessoConstants;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ProcessoWebPage extends WebPage {
	
	private static final long serialVersionUID = 1L;
	
	private String contexto;
	public static final String nomeAplicacao = "ProcessoSeta";
	
	@Inject	private GestorAcessoService gestorService;
	
	public ProcessoWebPage(){
		this.contexto = WebApplication.get().getServletContext().getContextPath();
	}	
	
	public String obtemContexto(){ return this.contexto; }
	
	/*
	 * Sobre escrevo o metodo onBeforeRender() para fazer a validação se o usuário possui permissão na tela. Com isso consigo evitar de o usuario digitar 
	 * o endereço da pagina diretamente no browser
	 */
	@Override
	protected void onBeforeRender() {
		String modulo = "";
		
		//NÃO FAZ SENTIDO VERIFICAR PAGINAS NÃO AUTENTICADAS PORTANTO SÃO PULADAS
		if(paginaAutenticada()) {
			if(this.getPage().getClass().isAnnotationPresent(AcessoModulo.class))
				modulo = this.getPage().getClass().getAnnotation(AcessoModulo.class).moduloResponsavel();
			else
				modulo = this.getPage().getClass().getSimpleName();
			
			//VERIFICA SE O MODULO EXISTE NA LISTA DE PERMISSÕES DO SISTEMA, SE EXISTIR, A TELA DEVE POSSUIR PERMISSÃO PARA ACESSO
			if(getPermissoesSistema().containsKey(modulo)){
				if(!permissaoModulo(modulo))
					setResponsePage(new SignInApplication().getHomePage());
			}
		}
		
		super.onBeforeRender();
	}
	
	@Override
	public MarkupContainer add(Component... childs) {
		
		//NÃO FAZ SENTIDO VERIFICAR PAGINAS NÃO AUTENTICADAS PORTANTO SÃO PULADAS
		if(paginaAutenticada()) {
			
			if(childs.length > 0) {
				for(Component comp : childs){
					//VERIFICO SE O COMPONENTE NÃO É UM TransparentWebMarkupContainer POIS ESSE TIPO É DE USO INTERNO DO WICKET
					if(comp instanceof TransparentWebMarkupContainer == false){
						validarComponente(comp);
					}
				}
			}
		}
		
		//CONTINUO COM A FUNCIONALIDADE NORMAL DO METODO ADD
		return super.add(childs);
	}

	private void validarComponente(Component comp) {
		MarkupContainer mrkc = null;
		
		//TENTO CONVERTER O COMPONENT PARA MarkupContainer, POIS NESSE MOMENTO QUERO SABER SE O COMPONENTE É UM CONTAINER
		//NÃO CONFUNDIR WebMarkupContainer COM MarkupContainer
		//OS COMPONENTES QUE PODEM POSSUIR FILHOS HERDÃO O MarkupContainer
		try {
			mrkc = ((MarkupContainer) comp); 
		} catch(ClassCastException e) {}
		
		//SE O COMPONENTE FOR UM CONTAINER E SE EXISTIR MAIS COMPONENTES DENTRO DELE, 
		//ELE REPETIRA ESSE MESMO METODO ATE CHEGAR AO ULTIMO FILHO
		if(mrkc != null && mrkc.size() > 0) {			
			Iterator<Component> it = mrkc.iterator();
			while(it.hasNext()) {
				//FAÇO UMA CHAMADA RECURSIVA PARA CHEGAR AO ULTIMO FILHO
				validarComponente(it.next());
			}			
		} else {
			//CASO NÃO SEJA UM CONTAINER O COMPONENTE OU QUE NÃO POSSUA FILHOS
			// É VALIDADO SE EXISTE PERMISSÃO, E SE EXISTIR É APLICADO A REGRA NO COMPONENTE
			permissaoComponent(comp);
		}
	}
	
	private void permissaoComponent(Component comp) {
		String modulo = "";		
		
		//VERIFICO SE A CLASSE POSSUI A ANOTAÇÃO AcessoModulo SE ELA TIVER
		//SIGNIFICA QUE É UMA PAGINA INTERNA DE UM MODULO EX: 
		// 		Usando como modulo uma tela de consulta (Consulta Produtos)	
		// 		Se existir uma tela de cadastro de produtos, essa sera uma pagina interna do consultar, já que no padrão fazemos Consulta->Cadastro
		//SE FOR, PEGO O NOME DO MODULO NA ANOTAÇÃO
		//SE NÃO, PEGO O SIMPLENAME DA PAGINA
		if(this.getPage().getClass().isAnnotationPresent(AcessoModulo.class))
			modulo = this.getPage().getClass().getAnnotation(AcessoModulo.class).moduloResponsavel();
		else
			modulo = this.getPage().getClass().getSimpleName();
		
		
		//CONSULTO SE ESSE MODULO EXISTE NAS PEMISSÕES DO SISTEMA, 
		//SE NÃO EXISTIR SIGNIFICA QUE ESSE MODULO NÃO POSSIU COMPONENTES COM PERMISSÕES
		//CASO TENHA, BUSCO NO GESTOR E ADICIONO NAS PERMISSÕES DO USUARIO PARA EVITAR CHAMADAS DESNECESSARIAS AO WEB SERVICE
		if(getPermissoesSistema().containsKey(modulo)){
			if(!getPermissoesUsuario().containsKey(modulo)) {
				getPermissoesUsuario().put(modulo, carregarFuncionalidades(modulo));
			}
			
			//VERIFICO SE NA LISTA DE FUNCIONALIDADES DO SISTEMA SE AQUELE COMPONENTE POSSUI ALGUMA FUNCIONALIDADE
			boolean validar = false;
			for(PermissaoTagDTO permissao : getPermissoesSistema().get(modulo)) {
				if(permissao.getFuncionalidade().equals(comp.getId())){
					validar = true;
					break;
				}
			}
			
			//SE EXISTE A FUNCIONALIDADE PARA O COMPONENTE E SE O USUARIO NÃO POSSUIR, SIGNIFACA QUE O COMPONENTE NÃO DEVE SER EXIBIDO
			//CASO EXISTA CHECAR SE O COMPONENTE DEVE VIR BLOQUEADO OU LIBERADO
			if(validar){
				int funcionalidadesEncontradas = 0;
				for(PermissaoTagDTOV2 permissao : getPermissoesUsuario().get(modulo)) {
					if(permissao.getFuncionalidade().equals(comp.getId())){
						comp.setEnabled(!permissao.isDesabilitado());
						funcionalidadesEncontradas ++;
						break;
					}
				}
				
				//O SISTEM POSSUI FUNCIONALIDADE PARA O COMPONENTE MAS O USUARIO NÃO POSSUI, LOGO O COMPONENTE NÃO DEVE SER EXIBIDO
				if(funcionalidadesEncontradas == 0)
					comp.setVisible(false);
			}
		}
		
	}
	
	/*
	 * RETORNO A LISTA DE PERMISSÕES DO USUARIO, CASO AINDA NÃO EXISTA NA SESSÃO É CRIADA UMA
	 * A LISTA É INCREMENTADA A CADA MODULO NOVO VISITADO
	 */
	public Permissoes getPermissoesUsuario() {
		if(Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_USUARIO) != null)
			return (Permissoes) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_USUARIO);
		else {
			Session.get().setAttribute(GestorAcessoConstants.PERMISSOES_USUARIO, new Permissoes());
			Session.get().bind();
			return (Permissoes) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_USUARIO);
		}
	}
	
	/*
	 * RETORNO A LISTA DE FUNCIONALIDADE DO SISTEMA, CASO AINDA NÃO EXISTA NA SESSÃO É CRIADA UMA E A MESMA JÁ É PREENCHIDA
	 */
	public PermissoesSistema getPermissoesSistema() {
		if(Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_SISTEMA) != null)
			return (PermissoesSistema) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_SISTEMA);
		else {
			Session.get().setAttribute(GestorAcessoConstants.PERMISSOES_SISTEMA, carregarFuncionalidadesSistema(new PermissoesSistema()));
			Session.get().bind();
			return (PermissoesSistema) Session.get().getAttribute(GestorAcessoConstants.PERMISSOES_SISTEMA);
		}
	}
	
	/*
	 * USADO PARA CONSULTAR AS FUNCIONALIDADES DOS USUARIOS
	 */
	private List<PermissaoTagDTOV2> carregarFuncionalidades(String modulo) {
		ConsultaPermissaoWSV2 inter = null;
		try {
			inter = gestorService.getServicoConsulta();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ListarPorPermissao lpp = new ListarPorPermissao();
		PermissaoTagDTO permissao = new PermissaoTagDTO();
		ListaPermissaoDTORequest lpr = new ListaPermissaoDTORequest();
		
		permissao.setSistema(nomeAplicacao);
		permissao.setModulo(modulo);
		lpr.getPermissao().add(permissao);
		lpp.setPermissoes(lpr);
		
		try{
			ListarPorPermissaoResponse lppr =  inter.listarPorPermissao(lpp, getAcessoUsuarioLogado());
			return  lppr.getAcesso().getPermissoes().getPermissao();
		} catch(AutorizacaoException e){} catch(BusinessException e){}
		
		return null;
	}
	
	private PermissoesSistema carregarFuncionalidadesSistema(PermissoesSistema permissoesSistema) {
		ConsultaPermissaoWSV2 inter = null;
		try {
			inter = gestorService.getServicoConsulta();
		} catch (Exception e) { }
		
		try{
			ListaPermissaoDTORequest listaPermissao = new ListaPermissaoDTORequest();
			PermissaoTagDTO dto = new PermissaoTagDTO();
			dto.setSistema(nomeAplicacao);
			listaPermissao.getPermissao().add(dto);
			br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarFuncionalidadeResponse.Permissoes prms = inter.listarFuncionalidade(listaPermissao);
			
			if(prms != null && prms.getPermissao()!= null && prms.getPermissao().size() > 0) {
				for(PermissaoTagDTO per : prms.getPermissao()) {
					if(permissoesSistema.containsKey(per.getModulo())){
						permissoesSistema.get(per.getModulo()).add(per);
					} else {
						List<PermissaoTagDTO> list = new ArrayList<>();
						list.add(per);
						permissoesSistema.put(per.getModulo(), list);
					}
				}
			}
			
			return permissoesSistema;
		} catch(AutorizacaoException e){} catch(BusinessException e){}
		
		return null;
	}
	
	private boolean paginaAutenticada() {
		
		for(Class<?> interfaces : this.getPage().getClass().getSuperclass().getInterfaces())
			if(interfaces.equals(AuthenticatedWebPage.class))
				return true;
		
		for(Class<?> interfaces : this.getPage().getClass().getInterfaces())
			if(interfaces.equals(AuthenticatedWebPage.class))
				return true;
					
		return false;
	}
	
	
	
	/**
	 * ESSE USUARIO É OBTIDO AO LOGAR NO GESTOR DE ACESSO E <b>NÃO É O USUARIO DO BONITA</b>.
	 * USADO NOS METODOS DE VALIDAÇÕES DE MODULOS E FUNCIONALIDADES
	 * @return
	 * @author João 
	 */
	protected DadosUsuarioDTO getUsuarioLogado(){ return (DadosUsuarioDTO) Session.get().getAttribute(GestorAcessoConstants.USUARIO); }
	
	/**
	 * OBJETO DE ACESSO DO GESTOR, USADO NOS METODOS DE VALIDAÇÕES DE MODULOS E FUNCIONALIDADES
	 * @return
	 * @author João 
	 */
	protected AcessoDTO getAcessoUsuarioLogado() { return (AcessoDTO) Session.get().getAttribute(GestorAcessoConstants.ACESSO); }
	
	
	/**
	 * O provider obrigatoriamente tem que ser do tipo Servico
	 * @param provider
	 * @return true se exitir algum item do menu que o usuario possua permissão
	 * @author João 
	 */
	protected boolean exibirMenu(IDataProvider<RotuloEWebPage> provider) {
		Iterator<?> it = provider.iterator(0, provider.size() - 1);
		while(it.hasNext()) {
			RotuloEWebPage srv = (RotuloEWebPage)it.next();
			String modulo = srv.getWebPage().getSimpleName();
			// VERIFICO SE O MODULO POSSUI PERMISSÃO NO SISTEMA
			if(getPermissoesSistema().containsKey(modulo)) {
				// VERIFICO SE O USUARIO POSSUI ACESSO AO MODULO
				//SE POSSUIR, O MODULO É EXIBIDO
				if(!getPermissoesUsuario().containsKey(modulo)) {
					//CASO NÃO TENHA É VALIDADO NO GESTOR, POIS O MODULO AINDA PODE NÃO TER SIDO CARREGADO PARA A SESSÃO AINDA
					if(!permissaoModulo(modulo)) {
						// SE O GESTOR RETORNAR FALSE O MODULO É REMOVIDO
						it.remove();
					} else {
						// SE NÃO CARREGO ELE PARA A LISTA DE PERMISSÕES DO USUARIO
						getPermissoesUsuario().put(modulo, carregarFuncionalidades(modulo));
					}
				}
			}
		}
		return provider.size() > 0 ? true : false;
	}
	
	
	/**
	 * usar o .getClass().getSimpleName() da pagina como parametro ou o moduloResponsavel() da anotação do AcessoModulo
	 * @param simpleName
	 * @return true se possui permissão de acesso
	 * @author João 
	 */
	protected boolean permissaoModulo(String simpleName){
		ConsultaPermissaoWSV2 inter = null;
		try {
			inter = gestorService.getServicoConsulta();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		ListarPorPermissao lpp = new ListarPorPermissao();
		PermissaoTagDTO permissao = new PermissaoTagDTO();
		ListaPermissaoDTORequest lpr = new ListaPermissaoDTORequest();
		
		permissao.setSistema(nomeAplicacao);
		permissao.setModulo(simpleName);
		lpr.getPermissao().add(permissao);
		lpp.setPermissoes(lpr);
		
		try {			
			inter.listarPorPermissao(lpp, getAcessoUsuarioLogado());			
		} catch(AutorizacaoException e){
			return false;
		} catch(BusinessException e){
			return false;
		}
		
		return true;
	}
}
