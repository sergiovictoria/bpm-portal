package br.com.seta.processo.utils;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Morphia;

import com.mongodb.Cursor;
import com.mongodb.DBObject;

import br.com.seta.processo.connection.MongoConnectionManager;

/**
 * Classe utilitária para conversão de objetos nativos do MongoDB para objetos/classes Java
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class MongoDbObjParser {
	
	private Morphia morphia;
	private static MongoDbObjParser parser;
	
	private MongoDbObjParser(){
		MongoConnectionManager connection =  MongoConnectionManager.getInstance();
		this.morphia = connection.getMorphia();
	}
	
	/**
	 * Retorna uma instância da classe MongoDbObjParser
	 * 
	 * @return
	 */
	public static MongoDbObjParser getInstance(){
		if(parser == null){
			parser = new MongoDbObjParser();
		}
		
		return parser;
	}
	
	/**
	 * Faz a conversão de um Cursor para uma lista de objetos Java
	 * 
	 * @param cursor Cursor que será convertido
	 * @param clazz Classe que "tipará" a lista de objetos retornada
	 * @return Lista contendo os objetos convertidos
	 */
	public <T> List<T> toList(Cursor cursor, Class<T> clazz){
		List<T> resultados = new ArrayList<T>();
		
		while(cursor.hasNext()){
			T obj = morphia.fromDBObject(clazz, cursor.next());
			resultados.add(obj);
		}
		
		return resultados;
	}
	
	/**
	 * Faz a conversão de uma collecction de DBObject para uma lista de objetos
	 * 
	 * @param dbObjects DBObjects que serão convertidos
	 * @param clazz Classe que "tipará" a lista de objetos retornada
	 * @return Lista contendo os objetos convertidos
	 */
	public <T> List<T> toList(Class<T> clazz, Iterable<DBObject> dbObjects){
		List<T> resultados = new ArrayList<T>();
		
		for(DBObject dbObj : dbObjects){
			T obj = morphia.fromDBObject(clazz, dbObj);
			resultados.add(obj);
		}
		
		return resultados;
	}

}
