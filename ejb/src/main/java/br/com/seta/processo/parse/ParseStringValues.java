package br.com.seta.processo.parse;

import java.io.Serializable;
import java.lang.reflect.Field;

import br.com.seta.processo.exceptions.ParseException;

/**
 * Classe abstrata para fazer parse de Strings
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 * @param <T>
 */
public abstract class ParseStringValues<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Executa o parse do objeto
	 * 
	 * @param obj
	 * @return retorna o mesmo objeto (referência) com os valores parseados
	 * @throws ParseException Erro de parse
	 */
	public T parse(T obj) throws ParseException{
		Field[] fields = obj.getClass().getDeclaredFields();
		
		for(Field field : fields){
			if(field.getType().isAssignableFrom(String.class)){
				field.setAccessible(true);
				String valor;
				try {
					valor = (String)field.get(obj);
					if(valor != null) {
						String valorParseado = parseString(valor);
						field.set(obj, valorParseado);
					}						
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new ParseException(e);
				}				
			}
		}
		
		return obj;	
	}

	/**
	 * Método "hook" que deverá conter a lógica do parse de strings
	 * 
	 * @param valor
	 * @return valor "parseado"
	 * @throws ParseException
	 */
	protected abstract String parseString(String valor) throws ParseException;
}
