package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.relatorios.SlaParametros;
import br.com.seta.processo.solicitacaopagamento.ParametrizarSolicitacaoPagamento;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ParametrosProvider implements  IDataProvider<RotuloEWebPage>{

	private static final long serialVersionUID = 1L;

	private List<RotuloEWebPage> servicos;
	
	public ParametrosProvider() {
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
		RotuloEWebPage servico = new RotuloEWebPage("Solicitação de Pagamento", ParametrizarSolicitacaoPagamento.class);
		RotuloEWebPage sla = new RotuloEWebPage("SLA por Processo", SlaParametros.class);
		ArrayList<RotuloEWebPage> servicos = new ArrayList<RotuloEWebPage>();
		servicos.add(servico);
		servicos.add(sla);		
		return servicos;
	}
}
