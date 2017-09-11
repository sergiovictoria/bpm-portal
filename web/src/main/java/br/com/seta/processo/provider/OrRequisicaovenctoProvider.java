package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.dto.OrRequisicaovencto;


public class OrRequisicaovenctoProvider implements IDataProvider<OrRequisicaovencto> {


	private static final long serialVersionUID = 1L;
	private List<OrRequisicaovencto> orRequisicaovenctos = Collections.synchronizedList(new ArrayList<OrRequisicaovencto>());
    private OrRequisicaovenctoDatabase orRequisicaovenctoDatabase = new OrRequisicaovenctoDatabase();
	
    
	public OrRequisicaovenctoProvider(List<OrRequisicaovencto> orRequisicaovenctos) {
		getOrRequisicaovenctoDatabase().setOrRequisicaovenctos(orRequisicaovenctos);
	}

	public OrRequisicaovenctoProvider() {
	}

	
	@Override
	public void detach() {
	}

	@Override
	public Iterator<? extends OrRequisicaovencto> iterator(long first, long count) {
		return getOrRequisicaovenctoDatabase().find(first, count);
	}

	@Override
	public long size() {
		return getOrRequisicaovenctoDatabase().size();
	}


	@Override
	public IModel<OrRequisicaovencto> model(final OrRequisicaovencto orRequisicaovencto) {
		return new LoadableDetachableModel<OrRequisicaovencto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected OrRequisicaovencto load() {
				return orRequisicaovencto;
			}
		};
	}

	public void delte(OrRequisicaovencto orRequisicaovencto) {
		getOrRequisicaovenctoDatabase().delte(orRequisicaovencto);
		this.orRequisicaovenctos = getOrRequisicaovenctoDatabase().getOrRequisicaovenctos();
	}

	public void putParameter(final List<OrRequisicaovencto> orRequisicaovenctos) {
		clear();
		getOrRequisicaovenctoDatabase().setOrRequisicaovenctos(orRequisicaovenctos);
	}



	public void clear() {
		if (getOrRequisicaovenctos()!=null) {
			getOrRequisicaovenctos().clear();
			setOrRequisicaovenctos(null);
		}
	}
		

	public List<OrRequisicaovencto> getOrRequisicaovenctos() {
		return orRequisicaovenctos;
	}

	public void setOrRequisicaovenctos(List<OrRequisicaovencto> orRequisicaovenctos) {
		this.orRequisicaovenctos = orRequisicaovenctos;
	}

	public OrRequisicaovenctoDatabase getOrRequisicaovenctoDatabase() {
		return orRequisicaovenctoDatabase;
	}
	
	
    public void getSeqrequisicao(long seqrequisicao) {
    	for (int i = 0; i < orRequisicaovenctos.size()-1; i++) {
    		this.orRequisicaovenctos.get(i).setSeqrequisicao(seqrequisicao);
		}
    }
	
	

}

