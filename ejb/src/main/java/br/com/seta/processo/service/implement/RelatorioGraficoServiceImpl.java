package br.com.seta.processo.service.implement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.grafico.GraficoBarraVerticalAgrupado;
import br.com.seta.processo.grafico.GraficoPizza;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.relatorios.interfaces.RelatorioDemandasService;
import br.com.seta.processo.relatorios.interfaces.RelatorioSlaService;
import br.com.seta.processo.service.interfaces.RelatorioGraficosService;


@Stateless(name="RelatorioGraficosService")
@Local(RelatorioGraficosService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})

public class RelatorioGraficoServiceImpl implements RelatorioGraficosService {

    @Inject private RelatorioDemandasService relatorioDemandaService;
    @Inject private RelatorioSlaService relatorioSlaService;
    
    private static final String PALETTECOLORS = "#1aaf5d,#f23700";

    /* DEMANDAS */

	@Override
	public String demandasQtdCadastros(String divId, Date dataDe, Date dataAte) {
		
		List<Object[]> datasource = relatorioDemandaService.quantidadeDeCadastros(dataDe, dataAte);
		
		GraficoPizza pizza = new GraficoPizza(divId, datasource, 0, 1);
		
		return pizza.gerarGrafico();
	}

	@Override
	public String demandasDemandaMensal(String divId, Date dataDe, Date dataAte) {
		List<Object[]> datasource = relatorioDemandaService.demandaMensal(dataDe, dataAte);
		
		GraficoBarraVerticalAgrupado verticalAgrupado = new GraficoBarraVerticalAgrupado(divId, datasource, 1, 0, 2, "Mês", "Quantidade");
		
		return verticalAgrupado.gerarGrafico();
	}

	@Override
	public String demandasDemandaSemanal(String divId, Date dataDe, Date dataAte, String processoName) {
		List<Object[]> datasource = relatorioDemandaService.demandaSemanal(processoName, dataDe, dataAte);
		
		GraficoBarraVerticalAgrupado verticalAgrupado = new GraficoBarraVerticalAgrupado(divId, datasource, 1, 0, 2, "Mês", "Quantidade");
		
		return verticalAgrupado.gerarGrafico();
	}

	@Override
	public String demandasStatusIntencoes(String divId, Date dataDe, Date dataAte) {
		List<Object[]> datasource = relatorioDemandaService.statusDosProcessos(dataDe, dataAte);
		
		GraficoPizza pizza = new GraficoPizza(divId, datasource, 0, 1);
		
		return pizza.gerarGrafico();
	}

	@Override
	public String demandasStatusIntencoesEvolucao(String divId, Date dataDe, Date dataAte, String processoName) {
		List<Object[]> datasource = relatorioDemandaService.statusDosProcessosEvolucaoMensal(processoName,dataDe, dataAte);
		
		GraficoBarraVerticalAgrupado verticalAgrupado = new GraficoBarraVerticalAgrupado(divId, datasource, 1, 0, 2, "Mês", "Quantidade");
		
		return verticalAgrupado.gerarGrafico();
	}
	
	
	
	/* SLAS */

	@Override
	public String slaGeralConsolidado(String divId, Date dataDe, Date dataAte) {
		List<Object[]> datasource = relatorioSlaService.slaGeralConsolidado(dataDe, dataAte);
		
		GraficoPizza pizza = new GraficoPizza(divId, datasource, 0, 1);
		pizza.setPaletteColors(PALETTECOLORS);
		
		return pizza.gerarGrafico();
	}

	@Override
	public String slaProcessoConsolidado(String divId, Date dataDe, Date dataAte) {
		List<Object[]> datasource = relatorioSlaService.slaPorProcessoConsolidado(dataDe, dataAte);
		
		List<Integer> indexValues = new ArrayList<Integer>();
		indexValues.add(1);//dentro
		indexValues.add(2);//fora
		
		GraficoBarraVerticalAgrupado verticalAgrupado = new GraficoBarraVerticalAgrupado(divId, datasource, 0, indexValues, new String[]{"Dentro", "Fora"}, "Processo", "Quantidade");
		verticalAgrupado.setPaletteColors(PALETTECOLORS);
		
		return verticalAgrupado.gerarGrafico();
	}

	@Override
	public String slaEvolucaoMensal(String divId, String processo, Date dataDe, Date dataAte) {
		List<Object[]> datasource = relatorioSlaService.slaEvolucaoMensal(processo, dataDe, dataAte);
		
		GraficoBarraVerticalAgrupado verticalAgrupado = new GraficoBarraVerticalAgrupado(divId, datasource, 0, 1, 2, "Mês", "Quantidade");
		verticalAgrupado.setPaletteColors(PALETTECOLORS);
		
		return verticalAgrupado.gerarGrafico();
	}

}
