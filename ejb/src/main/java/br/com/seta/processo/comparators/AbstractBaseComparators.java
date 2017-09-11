package br.com.seta.processo.comparators;

import java.util.Comparator;

/**
 * Classe base que contém métodos que auxiliam na criação de Comparators
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AbstractBaseComparators {
	
	/**
	 * Compara dois objetos
	 * 
	 * @param obj1
	 * @param obj2
	 * @return 
	 * <ul>
	 * 	<li>0 se obj1 é igual ao obj2</li> 
	 *  <li>um inteiro negativo, se obj1 é menor que o obj2 ou nulo</li>
	 *  <li>um interiro positivo, se obj1 é maior que o obj2, ou obj2 é nulo</li>
	 * </ul>
	 */
	protected static <T extends Comparable<T>> int compara(T obj1, T obj2){
		int valor = verificaValores(obj1, obj2);
		
		if(valor != 0) 
			return valor;
		
		return obj1.compareTo(obj2);
	}
	
	/**
	 *Compara duas Strings sem case sensitive
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 * <ul>
	 * 	<li>0 se str1 é igual ao str2</li> 
	 *  <li>um inteiro negativo, se str1 é menor que o str2 ou nulo</li>
	 *  <li>um interiro positivo, se str1 é maior que o str2, ou str2 é nulo</li>
	 * </ul>
	 */
	protected static int comparaSemCaseSensitive(String str1, String str2){
		int valor = verificaValores(str1, str2);
		
		if(valor != 0) 
			return valor;
		
		return str1.compareToIgnoreCase(str2);
	}
	
	/**
	 * Compara dois objetos
	 * 
	 * @param obj1
	 * @param obj2
	 * @param comparator Comparator utilizado para fazer a comparação caso o obj1 e obj2 não são nulos
	 * @return
	 */
	protected static <T> int compara(T obj1, T obj2, Comparator<T> comparator){
		int valor = verificaValores(obj1, obj2);
		
		if(valor != 0)
			return valor;
		
		return comparator.compare(obj1, obj2);
	}
	
	private static int verificaValores(Object valor1, Object valor2){
		
		if(saoNulos(valor1, valor2)){
			if(ehNulo(valor1)){
				return -1;
			}else{
				return 1;
			}
		}
		return 0;
	}
	
	private static boolean saoNulos(Object valor1, Object valor2){
		if(valor1 == null || valor2 == null){
			return true;
		}
		return false;
	}
	
	private static boolean ehNulo(Object valor){
		return valor == null ? true : false;
	}

}
