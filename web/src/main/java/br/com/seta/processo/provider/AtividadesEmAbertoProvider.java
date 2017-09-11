package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.relatorios.ProcessoEmAberto;
import br.com.seta.processo.relatorios.interfaces.RelatorioProcessosEmAberto;
import br.com.seta.processo.utils.DateUtils;

public class AtividadesEmAbertoProvider implements  IDataProvider<ProcessoEmAberto>{

	private static final long serialVersionUID = 1L;
	
	@Inject private RelatorioProcessosEmAberto relatorioBean;
	private List<ProcessoEmAberto> atividades = new ArrayList<ProcessoEmAberto>();
	
	public AtividadesEmAbertoProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public Iterator<? extends ProcessoEmAberto> iterator(long first, long count) {
		return this.atividades.subList((int)first, (int)(first + count)).iterator();
	}

	@Override
	public long size() {
		return atividades.size();
	}

	@Override
	public IModel<ProcessoEmAberto> model(final ProcessoEmAberto sla) {
		return new LoadableDetachableModel<ProcessoEmAberto>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected ProcessoEmAberto load() {
				return sla;
			}
		};
	}
	
	public void putParameter(Long caseId, String processo, String estado, String ator, Long idUsuario, Date dataDe, Date dataAte) {
		dataDe = DateUtils.inicioDoDiaDe(dataDe);
		dataAte = DateUtils.fimDoDiaDe(dataAte); 
		this.atividades = this.relatorioBean.busca(caseId, processo, estado, ator, idUsuario, dataDe, dataAte);
	}
	
	public List<ProcessoEmAberto> getSlas(){
		return  this.atividades;
	}

	@Override
	public void detach() {
		
	}
}
