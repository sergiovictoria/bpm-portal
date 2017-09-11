package br.com.seta.processo.connection;


import java.net.UnknownHostException;

import org.jboss.logging.Logger;

import br.com.seta.processo.utils.PropertiesEJBUtils;

import com.mongodb.DB;
import com.mongodb.Mongo;

public class MongoConnection {


	private static Logger logger = Logger.getLogger(MongoConnection.class);

	private String hostMongo;
	private String dbMongo;
	private Integer portMongo; 

	private static MongoConnection uniqInstance;
	private static int mongoInstance = 1;

	private Mongo mongo;
	private DB db;

	private MongoConnection() {
		try {
			hostMongo = PropertiesEJBUtils.getInstance().getValues("hostMongo");
			dbMongo   = PropertiesEJBUtils.getInstance().getValues("dbMongo");
			portMongo = Integer.parseInt(PropertiesEJBUtils.getInstance().getValues("portMongo"));
		}catch (Exception e) {
			hostMongo = "127.0.0.1";
			dbMongo   = "seta";
			portMongo = 27017;
		}
     }


	public static synchronized MongoConnection getInstance() {
		if (uniqInstance == null) {
			uniqInstance = new MongoConnection();
		}
		return uniqInstance;
	}

	@SuppressWarnings("deprecation")
	public DB getDB() {
		if (mongo == null) {
			try {
				mongo = new Mongo(hostMongo,portMongo);
				db = mongo.getDB(dbMongo);
				logger.info("Mongo Criando Instancia [ " + mongoInstance ++  +" ] ["+hostMongo+ "]  [ "+portMongo+ " ] ");
			} catch (UnknownHostException e) {
				logger.info("Erro ao Tentar Conectar com Mongo -DB  [ " + mongoInstance ++  +" ]",e);
			}
		}
		return db;
	}

}
