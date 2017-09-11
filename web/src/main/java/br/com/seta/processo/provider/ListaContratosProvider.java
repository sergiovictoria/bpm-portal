/**
 * 
 */
package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.service.interfaces.ContratoService;

/**
 * @author Eliel Sobral
 *
 */
public class ListaContratosProvider implements IDataProvider<Contrato> {

	private static final long serialVersionUID = 1L;
	@Inject TransacaoMongo transacaoMongo;

	@Inject	private ContratoService contratoService;
	private List<Contrato> contratos = new ArrayList<Contrato>();
	private Long caseID;

	public ListaContratosProvider(Long caseID) {
		this.caseID = caseID;
		CdiContainer.get().getNonContextualManager().inject(this);
		try {
			this.contratos = (List<Contrato>) contratoService.listaContratosPendentes(caseID, Contrato.class);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Iterator<? extends Contrato> iterator(long first, long count) {
		return this.contratos.subList((int) first, (int) (first + count)).iterator();
	}

	@Override
	public long size() {
		return contratos.size();
	}

	@Override
	public IModel<Contrato> model(final Contrato object) {
		return new LoadableDetachableModel<Contrato>() {
			private static final long serialVersionUID = 1L;

			@Override
			protected Contrato load() {
				return object;
			}
		};
	}

	@Override
	public void detach() {
	}
	
	public final void refresh() {
	    Map<String, Object> map = new HashMap<String, Object>();
        map.put("statusContrato", 3L);
        map.put("status", "processando");
		try {
			this.contratos = (List<Contrato>) contratoService.listaContratosPendentes(this.caseID, Contrato.class);
		} catch (BonitaException e) {
			e.printStackTrace();
		}
	}
	
	public List<Contrato> getContratosAtualizadosParaEnviar() {
		List<Contrato> contratosAtualizadosParaEnviar = new ArrayList<Contrato>();
		for (Contrato contrato : this.contratos) {
			if(contrato.isSelecionado()) {
				contratosAtualizadosParaEnviar.add(contrato);
			}
		}
		return contratosAtualizadosParaEnviar;
	}

	/**
	 * @return the contratos
	 */
	public final List<Contrato> getContratos() {
		return contratos;
	}

	/**
	 * @param contratos the contratos to set
	 */
	public final void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

}
