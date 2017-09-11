package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.relatorios.AtividadesEmAberto;
import br.com.seta.processo.relatorios.DemandasAtividadesGeralIndicadores;
import br.com.seta.processo.relatorios.DemandasAtividadesProcessosIndicadores;
import br.com.seta.processo.relatorios.SlaGeralIndicadores;
import br.com.seta.processo.relatorios.SlaProcessoIndicadores;
import br.com.seta.processo.relatorios.TempoAtendimento;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class RelatoriosProvider implements  IDataProvider<RotuloEWebPage>{

	private static final long serialVersionUID = 1L;

	private List<RotuloEWebPage> servicos;
	
	public RelatoriosProvider() {
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
		RotuloEWebPage sla = new RotuloEWebPage("SLA Geral", SlaGeralIndicadores.class);
		RotuloEWebPage sla_processo = new RotuloEWebPage("SLA por Processo", SlaProcessoIndicadores.class);
		RotuloEWebPage demandas = new RotuloEWebPage("Demandas Gerais", DemandasAtividadesGeralIndicadores.class);
		RotuloEWebPage demandas_processo = new RotuloEWebPage("Demandas por Processo", DemandasAtividadesProcessosIndicadores.class);
		RotuloEWebPage atividades = new RotuloEWebPage("Atividades em Aberto", AtividadesEmAberto.class);
		RotuloEWebPage atendimento = new RotuloEWebPage("Tempo de Atendimento", TempoAtendimento.class);
		
		ArrayList<RotuloEWebPage> servicos = new ArrayList<RotuloEWebPage>();
		servicos.add(sla);
		servicos.add(sla_processo);
		servicos.add(demandas);
		servicos.add(demandas_processo);
		servicos.add(atividades);
		servicos.add(atendimento); 
		
		return servicos;
	}
}
