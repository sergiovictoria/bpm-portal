package br.com.seta.processo.relatorios.interfaces;

import java.util.Date;
import java.util.List;

public interface RelatorioDemandasService {

	/**
	 * Retorna os dados para o Dashboard de Quantidade de Cadastros
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public abstract List<Object[]> quantidadeDeCadastros(Date inicio, Date fim);

	/**
	 * Retorna os dados para o Dashboard de Demanda Mensal<br>
	 * 
	 * @param inicio
	 * @param fim
	 * @return 
	 * @return 
	 */
	public abstract List<Object[]> demandaMensal(Date inicio, Date fim);

	/**
	 * Retorna os dados para o Dashboard de Demanda Semanal
	 * 
	 * @param processo
	 * @param inicio
	 * @param fim
	 */
	public abstract List<Object[]> demandaSemanal(String processo, Date inicio,
			Date fim);

	/**
	 * Retorna os dados para o Dashboard Status do Processo 
	 * 
	 * @param inicio
	 * @param fim
	 */
	public abstract List<Object[]> statusDosProcessos(Date inicio, Date fim);

	/**
	 *  Retorna os dados do relatório de Status dos Processos Evolução Mensal
	 *  
	 * @param processo
	 * @param inicio
	 * @param fim
	 */
	public abstract List<Object[]> statusDosProcessosEvolucaoMensal(
			String processo, Date inicio, Date fim);

}