package br.com.seta.processo.provider;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.relatorios.DadosTempoAtendimento;
import br.com.seta.processo.relatorios.interfaces.RelatorioTempoAtendimentoService;

public class TempoAtendimentoProvider extends SortableDataProvider<DadosTempoAtendimento, Comparator<DadosTempoAtendimento>>{

	private static final long serialVersionUID = 1L;
	
	@Inject private RelatorioTempoAtendimentoService relatorioBean;
	
	private Long caseId = null;
	private String processo = null;
	private Long idUsuario = null;
	Date dataDe, dataAte = null;
	
	public TempoAtendimentoProvider() {
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	@Override
	public Iterator<? extends DadosTempoAtendimento> iterator(long first, long count) {
		List<DadosTempoAtendimento> resultados = 
				this.relatorioBean.lista(caseId, processo, dataDe, dataAte, idUsuario, (int)count, (int) (first > 0 ? (first / count) : first) );
		
		ordena(resultados);
		return resultados.iterator()	;
	}

	@Override
	public long size() {
		
		return this.relatorioBean.lista(caseId, processo,dataDe, dataAte, idUsuario, null, null).size();
	}

	@Override
	public IModel<DadosTempoAtendimento> model(final DadosTempoAtendimento atendimento) {
		return new LoadableDetachableModel<DadosTempoAtendimento>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected DadosTempoAtendimento load() {
				return atendimento;
			}
		};
	}
	
	private void clean(){
		this.caseId = null;
		this.processo = null;
		this.idUsuario = null;
		this.dataDe = null;
		this.dataAte = null;
	}
	
	public void putParameter(Long caseId, String processo, Long idUsuario, Date dataDe, Date dataAte) {
		this.clean();
		this.caseId = caseId;
		this.processo = processo;
		this.idUsuario = idUsuario;
		this.dataDe = dataDe;
		this.dataAte = dataAte;
	}

	@Override
	public void detach() {
		
	}
	
	private void ordena(List<DadosTempoAtendimento> dados){
		SortParam<Comparator<DadosTempoAtendimento>> sort = getSort();
		
		if(sort == null)
			return;
		
		Comparator<DadosTempoAtendimento> comparator = sort.getProperty();
		
		if(getSort().isAscending()){
			comparator = Collections.reverseOrder(comparator);
		}
		
		Collections.sort(dados, comparator);
	}
}
