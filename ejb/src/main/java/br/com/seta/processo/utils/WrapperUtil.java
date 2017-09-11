package br.com.seta.processo.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.swing.text.MaskFormatter;

/**
 * 
 * @version 0.1.24
 *
 */
public class WrapperUtil {

	public static Date ultimoDiaMes(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	public static String[] convertList(List<String> list) {
		String[] toArray = null;

		if (list != null && list.size() > 0) {
			toArray = new String[list.size()];
		}

		for (int i = 0; i < list.size(); i++) {
			toArray[i] = list.get(i);
		}
		return toArray;
	}

	public static Date ultimoDiaMes(String data) {
		Calendar c = Calendar.getInstance();
		c.setTime(WrapperUtil.stringToDate(data));
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * Esse metodo formata datas e documentos.
	 * 
	 * @param enmPattern
	 *            usar a Enum FormatacaoTipo
	 * @param value
	 *            valor a ser formatado
	 * @return valor formatado, caso de algum erro na formata��o � retornado ""
	 */
	public static String format(FormatacaoTipo enmPattern, Object value) {

		switch (enmPattern) {
		case DATA:
		case DATA_HORA:
		case HORA_MINUTO:
		case ANO_MES:
		case MES_ANO:
			try {
				Locale locale = new Locale("pt", "BR");
				DateFormat df = new SimpleDateFormat(enmPattern.getValue(),
						locale);
				df.setLenient(false);
				return df.format((Date) value);
			} catch (Exception e) {
				return "";
			}
		default:
			MaskFormatter mask;
			try {
				mask = new MaskFormatter(enmPattern.getValue());
				mask.setValueContainsLiteralCharacters(false);
				return mask.valueToString(value);
			} catch (ParseException e) {
				return "";
			}
		}
	}

	public static String encodeStringToHtml(String sequence) {
		String out = "";
		for (int i = 0; i < sequence.length(); i++) {
			char ch = sequence.charAt(i);
			if (Character.UnicodeBlock.of(ch) == Character.UnicodeBlock.BASIC_LATIN) {
				out += ch;
			} else {
				int codepoint = Character.codePointAt(sequence, i);
				// handle supplementary range chars
				i += Character.charCount(codepoint) - 1;
				// emit entity
				out += "&#x";
				out += Integer.toHexString(codepoint);
				out += ";";
			}
		}
		return out;

	}

	public static String decodeUrlString(String string, String encodeUtf8) {
		String retorno = "";
		try {
			retorno = URLDecoder.decode(string, encodeUtf8);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return retorno;
	}

	public static String converteStringEmData(String stringData) {
		SimpleDateFormat simpleDateForma2 = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		
		try {
			Date receivedDate = simpleDateForma2.parse(stringData);
			String formatedDate = new SimpleDateFormat("yyyy-MM-dd").format(receivedDate);
			return formatedDate;
		} catch (ParseException e) {
			
		}

		return null;
	}

	/**
	 * Esse metodo retorna o dia da semana da data que foi passada como
	 * paramentro.
	 * 
	 * @param data
	 * @return O dia da semana: Domingo, Segunda-feira, Ter�a-feira...
	 */
	public static String dataSemana(java.util.Date data) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);

		switch (gc.get(Calendar.DAY_OF_WEEK)) {

		case Calendar.SUNDAY:
			return "Domingo";
		case Calendar.MONDAY:
			return "Segunda-feira";
		case Calendar.TUESDAY:
			return "Terça-feira";
		case Calendar.WEDNESDAY:
			return "Quarta-Feira";
		case Calendar.THURSDAY:
			return "Quinta-Feira";
		case Calendar.FRIDAY:
			return "Sexta-Feira";
		case Calendar.SATURDAY:
			return "Sabado";
		default:
			return "Sem Dias";
		}

	}

	/**
	 * Esse metodo retorna o dia da semana da data que foi passada como
	 * parametro.
	 * 
	 * @param data
	 * @return O dia da semana: Dom, Seg, Ter...
	 */
	public static String dataSemanaResumido(java.util.Date data) {
		if (data == null)
			return "";

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);

		switch (gc.get(Calendar.DAY_OF_WEEK)) {

		case Calendar.SUNDAY:
			return "Dom";
		case Calendar.MONDAY:
			return "Seg";
		case Calendar.TUESDAY:
			return "Ter";
		case Calendar.WEDNESDAY:
			return "Qua";
		case Calendar.THURSDAY:
			return "Qui";
		case Calendar.FRIDAY:
			return "Sex";
		case Calendar.SATURDAY:
			return "Sab";
		default:
			return "Sem Dias";
		}

	}

	/**
	 * Convert uma string em um Date no formato dd/MM/yyyy
	 * 
	 * @param data
	 * @return Date dd/MM/yyyy
	 */
	public static Date stringToDate(String data) {
		if (validaData(data)) {
			try {
				Locale locale = new Locale("pt", "BR");
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", locale);
				return sdf.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Convert uma string em um Date no formato dd/MM/yyyy
	 * 
	 * @param data
	 * @param dataFormat
	 * @return Date dd/MM/yyyy
	 */
	public static Date stringToDate(String data, String dataFormat) {
		if (validaData(data, dataFormat)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
				return sdf.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Convert uma string em um Date no formato dd/MM/yyyy HH:mm
	 * 
	 * @param data
	 * @return Date dd/MM/yyyy HH:MM
	 */
	public static Date stringToFullDate(String data) {
		if (validaData(data)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
				return sdf.parse(data);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static Calendar dateToCalendar(Date date) {
		if (date != null) {
			Calendar calendar = GregorianCalendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
		return null;
	}

	/**
	 * Convert uma string em um calendar em um formato dd/MM/yyyy
	 * 
	 * @param date
	 * @return retorna um calendar no formato dd/MM/yyyy
	 */
	public static Calendar StringToCalendar(String date) {
		if (date != null) {
			if (validaData(date)) {
				return dateToCalendar(stringToDate(date));
			}
		}
		return null;
	}

	private static boolean validaData(String input) {
		Date date = null;
		if (null == input)
			return Boolean.FALSE;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			sdf.setLenient(Boolean.FALSE);
			date = sdf.parse(input);

		} catch (ParseException e) {
			return Boolean.FALSE;
		}
		if (date != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	private static boolean validaData(String input, String dataFormat) {
		Date date = null;
		if (null == input)
			return Boolean.FALSE;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
			sdf.setLenient(Boolean.FALSE);
			date = sdf.parse(input);

		} catch (ParseException e) {
			return Boolean.FALSE;
		}
		if (date != null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	/**
	 * Converte um arquivo em um array de bytes
	 * 
	 * @param file
	 * @return array de bytes
	 */
	public static byte[] convertFileToByte(File file) {
		FileInputStream fileInputStream = null;

		byte[] bFile = new byte[(int) file.length()];

		try {
			// convert file into array of bytes
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bFile);
			fileInputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return bFile;
	}

	/**
	 * Convert um array de bytes em um arquivo (File)
	 * 
	 * @param arquivo
	 *            array de bytes
	 * @param arquivoDescricao
	 *            nome do arquivo com o formato
	 * @return java.io.File file
	 */
	public static File convertBytesToFile(byte[] arquivo,
			String arquivoDescricao, String path) {

		FileOutputStream fileOuputStream = null;
		File file = new File(path + "\\" + arquivoDescricao);

		try {
			// convert array of bytes into file
			fileOuputStream = new FileOutputStream(file);
			fileOuputStream.write(arquivo);
			fileOuputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	/**
	 * Subtrai ou adiciona X meses � um Date
	 * 
	 * @param dataAtual
	 * @param mesSomarOrSubtrair
	 * @return
	 */
	public static Date modificarMes(Date dataAtual, Integer mesSomarOrSubtrair) {

		GregorianCalendar c = new GregorianCalendar();
		c.setTime(dataAtual);
		c.add(GregorianCalendar.MONTH, mesSomarOrSubtrair);

		return c.getTime();
	}

	/**
	 * 
	 * @param anomes
	 * @param tipo
	 * @return
	 */
	public static String mesExtenso(String anomes, MesExtensoTipo tipo) {
		int mes = Integer.parseInt(anomes.substring(4, 6));

		switch (tipo) {
		case REDUZ_SO_ANO:
			return pesquisarNomeMes(mes) + "/" + anomes.substring(2, 4);
		case MAIS_REDUZIDO:
			return pesquisarNomeMes(mes).substring(0, 3) + "/"
					+ anomes.substring(2, 4);
		case REDUZIDO:
			return pesquisarNomeMes(mes).substring(0, 3) + "/"
					+ anomes.substring(0, 4);
		default:
			return pesquisarNomeMes(mes) + "/" + anomes.substring(0, 4);
		}
	}

	public static String getMesEscrito(Date data) {

		Locale local = new Locale("pt", "BR");
		DateFormat dateFormat = new SimpleDateFormat("MMMM", local);
		return dateFormat.format(data);

	}

	/**
	 * 
	 * @param Date
	 *            data
	 * @return String dataEscrita
	 */
	@SuppressWarnings("deprecation")
	public static String dataPorEscrito(Date data) {
		String dataEscrita = "";
		try {
			dataEscrita = data.getDate() + " de "
					+ pesquisarNomeMes(data.getMonth() + 1) + " de "
					+ (data.getYear() + 1900);
		} catch (Exception e) {

		}

		return dataEscrita;
	}

	/**
	 * Retorna o nome do m�s dado um inteiro de 1 a 12
	 * 
	 * @param mes
	 *            valor de 1 � 12
	 * @return nome do m�s
	 */
	public static String pesquisarNomeMes(int mes) {
		String nomeMes = null;

		switch (mes) {
		case 1:
			nomeMes = "Janeiro";
			break;

		case 2:
			nomeMes = "Fevereiro";
			break;

		case 3:
			nomeMes = "Março";
			break;

		case 4:
			nomeMes = "Abril";
			break;

		case 5:
			nomeMes = "Maio";
			break;

		case 6:
			nomeMes = "Junho";
			break;

		case 7:
			nomeMes = "Julho";
			break;

		case 8:
			nomeMes = "Agosto";
			break;

		case 9:
			nomeMes = "Setembro";
			break;

		case 10:
			nomeMes = "Outubro";
			break;

		case 11:
			nomeMes = "Novembro";
			break;

		case 12:
			nomeMes = "Dezembro";
			break;
		}

		return nomeMes;
	}

	/**
	 * Retorna o nome do mes dado um inteiro de 1 a 12
	 * 
	 * @param mes
	 *            valor de 1 � 12
	 * @return nome do mes resumido
	 */
	public static String pesquisarNomeMesResumido(int mes) {
		String nomeMes = null;

		switch (mes) {
		case 1:
			nomeMes = "Jan";
			break;

		case 2:
			nomeMes = "Fev";
			break;

		case 3:
			nomeMes = "Mar";
			break;

		case 4:
			nomeMes = "Abr";
			break;

		case 5:
			nomeMes = "Mai";
			break;

		case 6:
			nomeMes = "Jun";
			break;

		case 7:
			nomeMes = "Jul";
			break;

		case 8:
			nomeMes = "Ago";
			break;

		case 9:
			nomeMes = "Set";
			break;

		case 10:
			nomeMes = "Out";
			break;

		case 11:
			nomeMes = "Nov";
			break;

		case 12:
			nomeMes = "Dez";
			break;
		}

		return nomeMes;
	}

	/**
	 * Retorna o valor de uma propriedade
	 * 
	 * @param caminhoArquivo
	 *            caminho do arquivo + nome do arquivo
	 * @param propertieName
	 * @return
	 */
	public static String getProperties(String caminhoArquivo,
			String propertieName) {
		Properties properties = new Properties();
		try {
			// properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("url-access.properties"));
			File file = new File(caminhoArquivo);
			FileInputStream inputStream = new FileInputStream(file);
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (String) properties.getProperty(propertieName);
	}

	/**
	 * ENUNS DE USO DESSA CLASSE
	 * 
	 * @author joao
	 *
	 */
	public enum FormatacaoTipo {
		/**
		 * Retorna #####-###
		 */
		CEP("#####-###"),

		/**
		 * Retorna ###.###.###-##
		 */
		CPF("###.###.###-##"),

		/**
		 * Retorna ##.###.###/####-##
		 */
		CNPJ("##.###.###/####-##"),

		/**
		 * Retorna ##.###.#####/##
		 */
		CEI("##.###.#####/##"),

		/**
		 * Esse pattern passa como parametro a data e hora, fazendo o metodo
		 * retornar ##/##/#### ##:##
		 */
		DATA_HORA("dd/MM/yyyy HH:mm"),

		/**
		 * Esse pattern passa como parametro a data e hora, fazendo o metodo
		 * retornar ##/##/####
		 */
		DATA("dd/MM/yyyy"),

		/**
		 * Esse pattern passa como parametro o mes e ano, fazendo o metodo
		 * retornar yyyy-MM
		 */
		ANO_MES("yyyy-MM"),

		/**
		 * Esse pattern passa como parametro o mes e ano, fazendo o metodo
		 * retornar MM/yyyy
		 */
		MES_ANO("MM/yyyy"),

		/**
		 * Esse pattern passa como parametro a hora e o minuto, fazendo o metodo
		 * retornar ##:##
		 */
		HORA_MINUTO("HH:mm");

		private String value;

		private FormatacaoTipo(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	public enum MesExtensoTipo {
		NORMAL(0), REDUZIDO(1), MAIS_REDUZIDO(2), REDUZ_SO_ANO(3);

		private int valor;

		private MesExtensoTipo(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return valor;
		}

		public void setValor(int valor) {
			this.valor = valor;
		}
	}
}