package br.com.seta.processo.service.gestor.acesso.interfaces;

import javax.ejb.Local;

import br.com.seta.processo.service.gestor.acesso.ws.consulta.ConsultaPermissaoWSV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.DadosUsuarioDTOV2;
import br.com.seta.processo.service.gestor.acesso.ws.consulta.DadosUsuarioDTOV2;


@Local
public interface GestorAcessoService {

	public abstract ConsultaPermissaoWSV2 getServicoConsulta() throws Exception;
	
	public abstract DadosUsuarioDTOV2 login(String username, String password, String nomeAplicacao) throws Exception;
}
