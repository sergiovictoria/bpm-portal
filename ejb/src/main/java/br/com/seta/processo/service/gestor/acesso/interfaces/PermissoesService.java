package br.com.seta.processo.service.gestor.acesso.interfaces;

import java.util.List;

import br.com.seta.processo.service.gestor.acesso.ws.consulta.AcessoDTO;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.AutorizacaoException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.BusinessException;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.PermissaoTagDTOV2;

/**
 * Serviço de acesso às permissões dos usuários no Gestor de Acessos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface PermissoesService {
	
	/**
	 * Lista as permissões do usuario
	 * 
	 * @param acesso
	 * @param sistema
	 * @return Uma relação contendo os módulo a que o usuário tem acesso
	 */
	List<PermissaoTagDTOV2> listaPermissoesUsuario(AcessoDTO acesso, String sistema)  throws AutorizacaoException, BusinessException;
	
	/**
	 * Lista as permissões do usuario
	 * 
	 * @param login
	 * @param senha
	 * @param sistema
	 * @return Uma relação contendo os módulo a que o usuário tem acesso
	 */
	List<PermissaoTagDTOV2> listaPermissoesUsuario(String login, String senha, String sistema)  throws AutorizacaoException, BusinessException;

}
