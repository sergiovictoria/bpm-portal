package br.com.seta.processo.provider;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.OrReqitensdespesa;


public class OrReqitensdespesaProvider implements IDataProvider<OrReqitensdespesa> {


	private static final long serialVersionUID = 1L;
	private OrReqitensdespesaDatabase orReqitensdespesaDatabase = new OrReqitensdespesaDatabase();


	public OrReqitensdespesaProvider() {
	}


	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends OrReqitensdespesa> iterator(long first, long count) {
		return getOrReqitensdespesaDatabase().find(first, count);
	}

	@Override
	public long size() {
		return getOrReqitensdespesaDatabase().size();
	}


	@Override
	public IModel<OrReqitensdespesa> model(final OrReqitensdespesa orReqitensdespesa) {
		return new LoadableDetachableModel<OrReqitensdespesa>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected OrReqitensdespesa load() {
				return orReqitensdespesa;
			}
		};
	}
	
	public void delte(OrReqitensdespesa orReqitensdespesa) {
		getOrReqitensdespesaDatabase().delte(orReqitensdespesa);
	}

	
	public void putParameter(final List<OrReqitensdespesa> orReqitensdespesas) {
		getOrReqitensdespesaDatabase().setOrReqitensdespesas(orReqitensdespesas);
	}
	
		
	public void add(OrReqitensdespesa orReqitensdespesa) {
		getOrReqitensdespesaDatabase().add(orReqitensdespesa);
	}
	
	
	public Boolean find(OrReqitensdespesa orRequisicaovencto ) {
		return getOrReqitensdespesaDatabase().find(orRequisicaovencto);
	}


	public OrReqitensdespesaDatabase getOrReqitensdespesaDatabase() {
		return orReqitensdespesaDatabase;
	}


	public void setOrReqitensdespesaDatabase(
			OrReqitensdespesaDatabase orReqitensdespesaDatabase) {
		this.orReqitensdespesaDatabase = orReqitensdespesaDatabase;
	}
	
	
}
