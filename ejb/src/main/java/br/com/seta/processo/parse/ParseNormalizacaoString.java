package br.com.seta.processo.parse;

import java.text.Normalizer;

import br.com.seta.processo.exceptions.ParseException;

/**
 * 
 * Parse para normalização de Strings
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 * @param <T>
 */
public class ParseNormalizacaoString<T> extends ParseStringValues<T> {

	private static final long serialVersionUID = 1L;

	@Override
	protected String parseString(String valor) throws ParseException {
		valor = Normalizer.normalize(valor, Normalizer.Form.NFD);
		valor = valor.replaceAll("[^\\p{ASCII}]", "");
		return valor;
	}
}
