package br.com.seta.processo.utils;

/**
 * Contém constatnes representando os status das atividades (tasks)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class BonitaStatusTaskConstants {	
	public final static String FAILED = "failed"; 
	public final static String INITIALIZING = "initializing"; 
	public final static String READY = "ready";
	public final static String EXECUTING = "executing";
	public final static String COMPLETING = "completing";
	public final static String COMPLETED = "completed";
	public final static String WAITING = "waiting";
	public final static String SKIPPED = "skipped";
	public final static String CANCELLED = "cancelled";
	public final static String ABORTED = "aborted";
	public final static String CANCELLING_SUBTASKS = "cancelling subtasks";
}
