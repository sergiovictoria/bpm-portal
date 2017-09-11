package br.com.seta.processo.utils;

import java.util.regex.Pattern;

/**
 * Classe contendo Pattern (regex) para verificação dos Http Status Codes 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class HttpStatusCodeUtils {
	
	private static final String _200_OK = "200";
	private static final String _2XX = "2\\d\\d";
	private static final String _401_UNAUTHORIZED = "401";
	private static final String _404_NOT_FOUND = "404";
	private static final String _4XX = "4\\d\\d";		
	private static final String _5XX = "5\\d\\d";	
	
	public static boolean is_200_OK(int statusCode){
		return Pattern.matches(_200_OK, String.valueOf(statusCode));
	}
	
	public static boolean is_2XX(int statusCode){
		return Pattern.matches(_2XX, String.valueOf(statusCode));
	}
	
	public static boolean is_401_UNAUTHORIZED(int statusCode){
		return Pattern.matches(_401_UNAUTHORIZED, String.valueOf(statusCode));
	}
	
	public static boolean is_404_NOT_FOUND(int statusCode){
		return Pattern.matches(_404_NOT_FOUND, String.valueOf(statusCode));
	}
	
	public static boolean is_4XX(int statusCode){
		return Pattern.matches(_4XX, String.valueOf(statusCode));
	}
	
	public static boolean is_5XX(int statusCode){
		return Pattern.matches(_5XX, String.valueOf(statusCode));
	}	
}
