package br.com.seta.processo.parse;

import br.com.seta.processo.exceptions.ParseException;

/**
 * Executa o parse das Strings para UpperCase
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 * @param <T>
 */
public class ParseToUpperCase<T> extends ParseStringValues<T>{

	private static final long serialVersionUID = 1L;

	@Override
	protected String parseString(String valor) throws ParseException {
		return valor.toUpperCase();
	}
}
