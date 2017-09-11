package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.Contrato;

/**
 * @author Eliel Sobral
 *
 */
@Local
public interface ContratoService {
	public abstract List<Contrato> listaContratosPendentes(Long caseID, Class<?> type) throws BonitaException;
}
