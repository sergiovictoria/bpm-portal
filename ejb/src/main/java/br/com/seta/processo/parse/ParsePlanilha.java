package br.com.seta.processo.parse;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public abstract class ParsePlanilha implements Parse {
	
	/**
	 * Limpa qualquer caracter que não seja número
	 * 
	 * @param valor Valor original
	 * @return Valor não contendo caracteres não numéricos
	 */
	protected String apenasNumeros(String valor){		
		return valor.replaceAll("[^0-9]", "");
	}
}
