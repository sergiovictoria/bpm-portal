package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.bean.SlaProcessoService;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.entity.SlaProcesso;
import br.com.seta.processo.exceptions.SlaProcessoException;


public class SlaProcessoProvider implements  IDataProvider<SlaProcesso>{

	private static final long serialVersionUID = 1L;
	
	@Inject private SlaProcessoService processoService;

	private List<SlaProcesso> slas;
	
	public SlaProcessoProvider(User user) {
		CdiContainer.get().getNonContextualManager().inject(this);
		
		try {
			this.slas = processoService.listaSlasProcessos(user);
		} catch (SlaProcessoException e) {
			this.slas = new ArrayList<SlaProcesso>();
		}
	}
	
	@Override
	public Iterator<? extends SlaProcesso> iterator(long first, long count) {
		return this.slas.iterator();		
	}

	@Override
	public long size() {
		return this.slas.size();
	}

	@Override
	public IModel<SlaProcesso> model(final SlaProcesso sla) {
		return new LoadableDetachableModel<SlaProcesso>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected SlaProcesso load() {
				return sla;
			}
		};
	}
	
	public List<SlaProcesso> getSlas(){
		return  this.slas;
	}

	@Override
	public void detach() {
		
	}
}
