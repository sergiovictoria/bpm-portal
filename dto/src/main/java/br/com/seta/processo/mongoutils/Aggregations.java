package br.com.seta.processo.mongoutils;

import static br.com.seta.processo.mongoutils.MongoObjects.dbArray;
import static br.com.seta.processo.mongoutils.MongoObjects.dbObj;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

/**
 * Classe utilitária contendo métodos para construção de agregações
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Aggregations {
	
	/**
	 * Operador $match para agregações: <br>
	 * { $match: { <query> } }
	 * 
	 * @param query
	 * @return
	 */
	public static BasicDBObject match(BasicDBObject query) {
		return dbObj("$match", query);
	}
	
	/**
	 * Operador $project
	 * 
	 * { $project: { &lt;specifications&gt; } }
	 * 
	 * @param specifications
	 * @return
	 */
	public static BasicDBObject project(BasicDBObject specifications) {
		return dbObj("$project", specifications);
	}
	
	/**
	 * Operador $eq para agregações: <br>
	 * { $eq: [ &lt;expression1&gt;, &lt;expression2&gt; ] }
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BasicDBObject eq(Object value1, Object value2) {
		return dbObj("$eq", dbArray(value1, value2));
	}
	
	/**
	 * Operador $gt para agregações: <br>
	 * { $gt: [ &lt;expression1&gt;, &lt;expression2&gt; ] }
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BasicDBObject gt(Object value1, Object value2) {
		return dbObj("$gt", dbArray(value1, value2));
	}
	
	/**
	 * Operador $ifNull para agregações
	 * { $ifNull: [ &lt;expression&gt;, &lt;replacement-expression-if-null&gt; ] }
	 * 
	 * @param valor
	 * @param replace
	 * @return
	 */
	public static BasicDBObject ifNull(Object valor, Object replace){
		return dbObj("$ifNull", dbArray(valor, replace) );
	}

	/**
	 * Operador $ne para agregações
	 * { $ne: [ &lt;expression1&gt;, &lt;expression2&gt; ] }
	 * 
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static BasicDBObject ne(Object value1, Object value2) {
		return dbObj("$ne", dbArray(value1, value2));
	}
	
	/**
	 * Operador $filter
	 * 
	 * @param arrayReference  An expression that resolves to an array
	 * @param as The variable name for the element in the input array
	 * 		The <b>as</b> expression accesses each element in the input array by this variable.
	 * @param cond The expression that determines whether to include the element in the resulting array. 
	 * 	The expression accesses the element by the variable name specified in <b>as<b>.
	 * @return
	 */
	public static BasicDBObject filter(String arrayReference, String as, BasicDBObject cond){
		return filterOperator(arrayReference, as, cond);
	}
	
	/**
	 * 
	 * @param array
	 * @param as The variable name for the element in the input array
	 * 		The <b>as</b> expression accesses each element in the input array by this variable.
	 * @param cond The expression that determines whether to include the element in the resulting array. 
	 * 		The expression accesses the element by the variable name specified in <b>as<b>.
	 * @return
	 */
	public static BasicDBObject filter(BasicDBList array, String as, BasicDBObject cond){
		return filterOperator(array, as, cond);
	}
	
	/**
	 * Operador $filter
	 * 
	 * @param input An expression that resolves to an array
	 * @param as The variable name for the element in the input array
	 * 		The <b>as</b> expression accesses each element in the input array by this variable.
	 * @param cond The expression that determines whether to include the element in the resulting array. 
	 * 		The expression accesses the element by the variable name specified in <b>as<b>.
	 * @return
	 */
	private static BasicDBObject filterOperator(Object input, String as, BasicDBObject cond){
		return dbObj("$filter", dbObj("input", input).append("as", as).append("cond", cond));
	}
	
	public static BasicDBObject skip(int i){
		return dbObj("$skip", i);
	}
	
	public static BasicDBObject limit(int i){
		return dbObj("$limit", i);
	}
}
