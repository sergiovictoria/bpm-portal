package br.com.seta.processo.mongoutils;

import static br.com.seta.processo.mongoutils.MongoObjects.dbObj;
import static br.com.seta.processo.mongoutils.MongoObjects.dbArray;

import java.util.Collection;
import java.util.regex.Pattern;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Classe utilitária contendo métodos para construção de filtros utilizados nas consultas do MongoDB
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Filters {

	public static BasicDBObject and(BasicDBList conditions) {
		return dbObj("$and", conditions);
	}
	
	public static BasicDBObject and(BasicDBObject... conditions) {
		return dbObj("$and", dbArray((Object[])conditions));
	}

	public static BasicDBObject eq(Object value){
		return dbObj("$eq", value);
	}
	
	public static BasicDBObject eq(String field, Object value) {
		return dbObj(field, dbObj("$eq", value));
	}
	
	public static BasicDBObject lte(Object value) {
		return dbObj("$lte", value);
	}

	public static BasicDBObject lte(String field, Object value) {
		return dbObj(field, dbObj("$lte", value));
	}
	
	public static BasicDBObject gt(Object value){
		return dbObj("$gt", value);
	}
	
	public static BasicDBObject gt(String field, Object value){
		return dbObj(field, dbObj("$gt", value));
	}
	
	public static BasicDBObject gte(Object value) {
		return dbObj("$gte", value);
	}
	
	public static BasicDBObject gte(String field, Object value) {
		return dbObj(field, dbObj("$gte", value));
	}

	public static BasicDBObject exists(String field, boolean value) {
		return dbObj(field, dbObj("$exists", value));
	}
	
	public static BasicDBObject regex(String pattern, String options) {
		return dbObj("$regex", Pattern.compile(Pattern.quote(pattern))).append("$options", "i");
	}
	
	public static BasicDBObject contains(String value) {
		return dbObj("$regex", value);
	}	
	
	public static BasicDBObject containsCaseInsensitive(String value) {
		return dbObj("$regex", value).append("$options", "i");
	}	

	public static BasicDBObject contains(String field, String value){
		return dbObj(field, "/"+value+"/");
	}
	
	public static BasicDBObject containsCaseInsensitive(String field, String value){
		return dbObj(field, "/"+value+"/i");
	}
	
	/**
	 * Use o operador where para passar uma string contendo uma expressão Javascript ou uma função Javascript que retorne um booleano. 
	 * @see <a href="https://docs.mongodb.com/manual/reference/operator/query/where/">$where</a>
	 * 
	 * @param expression Uma expressão Javascript que retorne um booleano
	 * @return Um objeto representando {$where: <i>expressão</i>}
	 */
	public static BasicDBObject where(String expression){
		return dbObj("$where", expression);
	}
	
	public static BasicDBObject in(Object[] values){
		return dbObj("$in", dbArray(values));
	}
	
	public static BasicDBObject in(Collection<?> values){
		return dbObj("$in", values);
	}
	
	public static BasicDBObject slice(int index){
		return dbObj("$slice", index);
	}

}
