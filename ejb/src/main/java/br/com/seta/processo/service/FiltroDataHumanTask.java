package br.com.seta.processo.service;

import java.util.Calendar;
import java.util.Date;

import br.com.seta.processo.dto.HumanTask;

import com.google.common.base.Predicate;

/**
 * Classe responsável por filtrar HumanTasks contidas em uma Collection pela data de atribuição (assignedDate) ou pela data de conclusão (archivedDate).
 * Será utilizada em conjunto com a classe Collections2 (https://github.com/google/guava).<br/>
 * Ex: <br/>
 * Collection filtrado = Collections2.filter(completedTaskList, FiltroDataHumanTask(FiltroDataHumanTask.POR_DATA_ATRIBUICAO, OperadorFiltro.MAIOR_IGUAL, new Date()))
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FiltroDataHumanTask implements Predicate<HumanTask>{	
	public static final int POR_DATA_ATRIBUICAO = 0; 
	public static final int POR_DATA_CONCLUSAO = 1;
	private Date dataFiltro;
	private int tipoFiltro;
	private OperadorFiltro operador;
	private boolean excluirNulos = true;
	
	/**
	 * Contrutor padrão. Ex:<br/>
	 * FiltroDataHumanTask(FiltroDataHumanTask.POR_DATA_ATRIBUICAO, OperadorFiltro.MAIOR_IGUAL, new Date())<br/>
	 * Será criado um novo filtro pela data de atribuição da tarefa que deve ser maior ou igual a data fornecida como último parâmetro
	 * 
	 * @param tipoFiltro "POR_DATA_ATRIBUICAO" para filtrar pela data de atribuição e "POR_DATA_CONCLUSAO" para filtrar pela data de concluão da atividade
	 * @param operador Tipo de operador que será utilizado no filtro
	 * @param dataFiltro Data (Date) que será utilizada como filtro
	 */
	public FiltroDataHumanTask(int tipoFiltro, OperadorFiltro operador, Date dataFiltro, boolean excluirNulos) {
		this.dataFiltro = normaliza(dataFiltro);
		this.tipoFiltro = tipoFiltro;
		this.operador = operador;	
		this.excluirNulos = excluirNulos;
	}
	
	@Override
	public boolean apply(HumanTask humanTask) {
		if(tipoFiltro == POR_DATA_ATRIBUICAO){
			return porDataAtribuicao(humanTask, operador);
		}
		
		return porDataConclusao(humanTask, operador);
	}
	
	/**
	 * Executa a comparação baseando-se na data de atribuição da tarefa
	 * 
	 * @param humanTask
	 * @param operador
	 * @return
	 */
	private boolean porDataAtribuicao(HumanTask humanTask, OperadorFiltro operador){
		Date assignedDate= humanTask.getAssigned_date();
		if(assignedDate == null){
			if(excluirNulos){
				return false;
			}
			
			return true;
		}			
		
		return executaComparacao(operador, assignedDate);
	}
	
	/**
	 * Executa a comparação baseando-se na data de conclusão da tarefa
	 * 
	 * @param humanTask
	 * @param operador
	 * @return
	 */
	private boolean porDataConclusao(HumanTask humanTask, OperadorFiltro operador){
		Date archivedDate = humanTask.getArchivedDate();
		if(archivedDate == null) 
			return false;
		
		return executaComparacao(operador, archivedDate);
	}

	/**
	 * Executa a comparação baseando-se no operador e a data a ser comparada
	 * 
	 * @param operador Operador a ser utilizada
	 * @param dataParaComparacao Data a ser comparada com a data fornecida no construtor da classe
	 * @return true se atende o operador e false para o caso contrário
	 */
	private boolean executaComparacao(OperadorFiltro operador, Date dataParaComparacao) {
		dataParaComparacao = normaliza(dataParaComparacao);		
		int valor = dataParaComparacao.compareTo(this.dataFiltro);
		
		switch(operador){
			case IGUAL: return (valor == 0);
			case DIFERENTE: return (valor != 0);
			case MAIOR: return valor > 0;
			case MENOR: return valor < 0;
			case MAIOR_IGUAL: return (valor == 0 || valor > 0);
			case MENOR_IGUAL: return (valor == 0 || valor < 0);			
			default: return false;
		}
	}	
	
	/**
	 * Normaliza uma data atribuindo hora, minutos, segundos e milisegundos para 0
	 * @param data Data a ser normalizada
	 * @return Data normlizada
	 */
	private Date normaliza(Date data){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		return calendar.getTime();
	}

}
