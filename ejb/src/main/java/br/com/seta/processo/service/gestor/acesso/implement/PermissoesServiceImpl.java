package br.com.seta.processo.service.gestor.acesso.implement;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.seta.processo.service.gestor.acesso.interfaces.GestorAcessoService;
import br.com.seta.processo.service.gestor.acesso.interfaces.PermissoesService;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AutorizacaoException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.BusinessException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ConsultaPermissaoWSV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListaPermissaoDTORequest;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissao;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.ListarPorPermissaoResponse;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTOV2;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Local(PermissoesService.class)
@Stateless
public class PermissoesServiceImpl implements PermissoesService, Serializable{
	private static final long serialVersionUID = 1L;

	@Inject	 
	private GestorAcessoService gestorAcessoService;
	
	private ConsultaPermissaoWSV2 consultaPermissaoWS;
	
	@PostConstruct
	public void init() throws Exception{
		consultaPermissaoWS = gestorAcessoService.getServicoConsulta();
	}	

	@Override
	public List<PermissaoTagDTOV2> listaPermissoesUsuario(AcessoDTO acesso, String sistema) throws AutorizacaoException, BusinessException {
		ListarPorPermissao lpp = new ListarPorPermissao();
		PermissaoTagDTO permissao = new PermissaoTagDTO();
		ListaPermissaoDTORequest lpr = new ListaPermissaoDTORequest();
		
		permissao.setSistema(sistema);
		lpr.getPermissao().add(permissao);
		lpp.setPermissoes(lpr);		
		
		ListarPorPermissaoResponse lppResponse = consultaPermissaoWS.listarPorPermissao(lpp , acesso);
		return lppResponse.getAcesso().getPermissoes().getPermissao();		
	}

	@Override
	public List<PermissaoTagDTOV2> listaPermissoesUsuario(String usuario, String senha, String sistema) throws AutorizacaoException, BusinessException {
		AcessoDTO acesso = new AcessoDTO();
		acesso.setLogin(usuario);
		acesso.setSenha(senha);
		
		return listaPermissoesUsuario(acesso , sistema);
	}

}
