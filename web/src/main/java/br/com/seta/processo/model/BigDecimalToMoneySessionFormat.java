package br.com.seta.processo.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BigDecimalToMoneySessionFormat {
	private static final String DECIMAL_FORMAT = "#,##0.00";
	
	public static String format(BigDecimal decimal, Locale locale, Integer digito) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		
		DecimalFormat df = new DecimalFormat(DECIMAL_FORMAT, symbols);

		if (decimal == null) {
			return "0,00";
		}else {
				return df.format(decimal);
		}
	}

}
