package br.com.seta.processo.mongoutils;

import java.util.Collection;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Classe utilitária contendo métodos para criação de objetos comuns  às consultas do mongo
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class MongoObjects {
	
	/**
	 * 
	 * @return Retorna uma instância de BasicDBObject
	 */
	public static BasicDBObject dbObj() {
		return new BasicDBObject();
	}

	/**
	 * 
	 * @param key 
	 * @param value
	 * @return Retorna uma instância de BasicDBObject contendo a chave e valor passados como parâmetros
	 */
	public static BasicDBObject dbObj(String key, Object value){
		return new BasicDBObject(key, value);
	}

	/**
	 * 
	 * @param values Valores que estarão contidos no objeto criado
	 * @return Retorna uma instância de BasicDBList
	 */
	public static BasicDBList dbArray(Object ...values){
		BasicDBList dbList = new BasicDBList();
		for(Object value : values)
			dbList.add(value);
		
		return dbList;
	}
	
	public static BasicDBList dbArray(Collection<?> values){
		BasicDBList dbArray = dbArray();
		dbArray.addAll(values);
		
		return dbArray;
	}

	/**
	 * 
	 * @return Retorna uma instância de BasicDBList
	 */
	public static BasicDBList dbArray(){
		return new BasicDBList();
	}
}
