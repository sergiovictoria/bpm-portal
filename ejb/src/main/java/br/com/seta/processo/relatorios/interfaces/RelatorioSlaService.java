package br.com.seta.processo.relatorios.interfaces;

import java.util.Date;
import java.util.List;

public interface RelatorioSlaService {

	public abstract List<Object[]> slaGeralConsolidado(Date inicio, Date fim);

	public abstract List<Object[]> slaPorProcessoConsolidado(Date inicio,
			Date fim);

	/**
	 * Gera o dashboard de Evolução Mensal para um determinado processo.<br>
	 * <b>Consulta MongoDB:</b><br>
	 * db.getCollection('InstanciaProcesso').aggregate([ <br>
	 *	{$match: {fim : {$exists : true}}}, <br>
	 *	{$group : <br>
	 *        { <br>
	 *          _id :{nomeProcesso : "$nomeProcesso", mes : {$month : "$fim"} , ano: {$year: "$fim"}}, <br>
	 *          instancias: {$push : {nomeProcesso: "$nomeProcesso", id: "$_id", inicio: "$inicio", fim: "$fim" }}  <br>
	 *        } <br>
	 *    }  <br>     
	 *])  <br>
	 * 
	 * @param processo
	 * @param inicio
	 * @param fim
	 * @return
	 */
	public abstract List<Object[]> slaEvolucaoMensal(String processo,
			Date inicio, Date fim);

}