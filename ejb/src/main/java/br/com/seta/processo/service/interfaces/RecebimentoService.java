package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.User;

/**
 * @author Eliel Sobral
 *
 */
@Local
public interface RecebimentoService {
	
	/**
	 * @return
	 * @throws BonitaException
	 */
	public abstract List<OrRequisicao> listaRequisicoes(User user) throws BonitaException;
		
	/**
	 * @throws BonitaException
	 */
	public abstract void receberPedidoSelecionado(OrRequisicao requisicao) throws BonitaException;
	

}
