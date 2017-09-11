package br.com.seta.processo.relatorios.interfaces;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.relatorios.ProcessoEmAberto;

/**
 * Relatório de Processos em aberto
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public interface RelatorioProcessosEmAberto {

	/**
	 * 
	 * @param caseId id da instância/case
	 * @param processo nome do processo
	 * @param status Disponível/Finalizado
	 * @param ator nome do ator
	 * @param idUsuario id do usuário
	 * @param inicio
	 * @param fim
	 * @return
	 */
	List<ProcessoEmAberto> busca(Long caseId, String processo,
			String estadoAtividade, String ator, Long idUsuario, Date inicio,
			Date fim);

}