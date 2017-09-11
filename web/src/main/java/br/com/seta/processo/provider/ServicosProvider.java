package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.recebimento.schedule.ScheduleContrato;
import br.com.seta.processo.suprimentos.Recebimentos;

public class ServicosProvider implements IDataProvider<RotuloEWebPage> {
	private static final long serialVersionUID = 1L;

	private List<RotuloEWebPage> servicos;
	
	public ServicosProvider() {
		this.servicos = recuperaServicos();
	}
	
	@Override
	public Iterator<? extends RotuloEWebPage> iterator(long first, long count) {
		return this.servicos.iterator();		
	}

	@Override
	public long size() {
		return this.servicos.size();
	}

	@Override
	public IModel<RotuloEWebPage> model(final RotuloEWebPage servico) {
		return new LoadableDetachableModel<RotuloEWebPage>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected RotuloEWebPage load() {
				return servico;
			}
		};
	}

	@Override
	public void detach() {
		
	}
	
	private List<RotuloEWebPage> recuperaServicos(){
		RotuloEWebPage recebimentos = new RotuloEWebPage("Inicia Recebimentos", Recebimentos.class);
		RotuloEWebPage scheduleContrato = new RotuloEWebPage("Schedule Contrato", ScheduleContrato.class);
		ArrayList<RotuloEWebPage> servicos = new ArrayList<RotuloEWebPage>();
		servicos.add(recebimentos);
		servicos.add(scheduleContrato);
		return servicos;
	}

}
