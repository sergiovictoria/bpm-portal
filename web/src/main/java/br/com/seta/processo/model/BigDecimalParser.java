package br.com.seta.processo.model;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.wicket.util.convert.converter.BigDecimalConverter;

public class BigDecimalParser {



	public static BigDecimal parseBigDecimal(String value, Locale locale) throws BigDecimalException {
		BigDecimal bigDecimal = null;
		try {

			BigDecimalConverter converter = new BigDecimalConverter();

			if (value.contains("R") ) {
				value = value.replace("R","");
			}

			if (value.contains("$") ) {
				value = value.replace("$","");
			}

			value = value.trim();

			bigDecimal = (BigDecimal) converter.convertToObject(value,locale);
			return bigDecimal;

		}catch (Exception e ) {
			throw new BigDecimalException("Valor invalido verefique ");			
		}
	}
	


	public static BigDecimal parse(String value, Locale locale) {
		BigDecimalConverter converter = new BigDecimalConverter();

		if (value.contains("R") ) {
			value = value.replace("R","");
		}

		if (value.contains("$") ) {
			value = value.replace("$","");
		}

		value = value.trim();

		BigDecimal bigDecimal = (BigDecimal) converter.convertToObject(value,locale);
		return bigDecimal;
	}
	
	public static BigDecimal parse(String value){
		return parse(value, new Locale("pt", "BR"));
	}

}
