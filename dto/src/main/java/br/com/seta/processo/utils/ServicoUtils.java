package br.com.seta.processo.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Classe contendo conjunto de métodos utilitários
 * 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ServicoUtils {
	/**
	 * Verifica se um valor é nulo
	 * 
	 * @param valor Valor a ser verificado
	 * @param outroValor Valor que será retornado caso o parâmetro 'valor' seja nulo (null)
	 * @return o parâmetro 'valor', caso este não seja nulo, ou o parâmetro 'outroValor', para o caso contrário
	 */
	public static Object ehNulo(Object valor, Object outroValor){
		if(valor == null){
			return outroValor;
		}
		
		return valor;
	}
	
	/**
	 * Formato um valor (BigDecimal) em uma String representando seu valor monetário
	 * 
	 * @param valor
	 * @param locale
	 * @param formato
	 * @return
	 */
	public static String formataValorMonetario(BigDecimal valor, Locale locale, String formato){
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		
		DecimalFormat df = new DecimalFormat(formato, symbols);
		return df.format(valor);
	}
	
	/**
	 * Formato um valor (BigDecimal) em uma String representando seu valor monetário. Para a formatação é utilizado o locale pt-br
	 * 
	 * @param valor
	 * @param formato
	 * @return
	 */
	public static String formataValorMonetario(BigDecimal valor, String formato){
		Locale locale = new Locale( "pt", "BR" );
		return formataValorMonetario(valor, locale , formato);
	}
	
	/**
	 * Formato um valor (BigDecimal) em uma String representando seu valor monetário. Para a formatação é utilizado o locale pt-br e o formato "#,##0.00"
	 * 
	 * @param valor
	 * @return
	 */
	public static String formataValorMonetario(BigDecimal valor){
		String formatoMoedaReais = "#,##0.00";
		return formataValorMonetario(valor, formatoMoedaReais);
	}
}
