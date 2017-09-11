package br.com.seta.processo.service.interfaces;

import java.util.Date;

import javax.ejb.Local;


@Local
public interface RelatorioGraficosService {

	public abstract String demandasQtdCadastros(String divId, Date dataDe, Date dataAte);
	public abstract String demandasDemandaMensal(String divId, Date dataDe, Date dataAte);
	public abstract String demandasDemandaSemanal(String divId, Date dataDe, Date dataAte, String processoName);
	public abstract String demandasStatusIntencoes(String divId, Date dataDe, Date dataAte);
	public abstract String demandasStatusIntencoesEvolucao(String divId, Date dataDe, Date dataAte, String processoName);
	
	public abstract String slaGeralConsolidado(String divId, Date dataDe, Date dataAte);
	public abstract String slaProcessoConsolidado(String divId, Date dataDe, Date dataAte);
	public abstract String slaEvolucaoMensal(String divId, String processo, Date dataDe, Date dataAte);
}
