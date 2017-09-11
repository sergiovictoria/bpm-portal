package br.com.seta.processo.provider;

import java.util.Iterator;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;

import br.com.seta.processo.dto.Contrato;

public class CadastrarItensProvider implements IDataProvider<Contrato> {
	private static final long serialVersionUID = -5950069029907840862L;

	@Override
	public void detach() {		
	}

	@Override
	public Iterator<? extends Contrato> iterator(long first, long count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IModel<Contrato> model(Contrato object) {
		// TODO Auto-generated method stub
		return null;
	}

}
