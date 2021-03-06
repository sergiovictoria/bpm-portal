package br.com.seta.processo.servicos;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoConnectionManager {

	private static MongoConnectionManager mongoConnectionManager;
	private Morphia morphia = null;
	private Datastore datastore = null;
	private MongoClient mongoClient = null;
	private String hostMongo;
	private String dbMongo;
	private Integer portMongo;

	/*****
	 * 
	 * @return Singleton Object
	 */
	public static MongoConnectionManager getInstance() {
		if (mongoConnectionManager == null) {
			mongoConnectionManager = new MongoConnectionManager();
		}
		return mongoConnectionManager;
	}

	private MongoConnectionManager() {
		try {
			hostMongo = PropertiesDtoUtils.getInstance().getValues("hostMongo");
			dbMongo   = PropertiesDtoUtils.getInstance().getValues("dbMongo");
			portMongo = Integer.parseInt(PropertiesDtoUtils.getInstance().getValues("portMongo"));
		} catch (Exception e) {
			hostMongo = "localhost";
			dbMongo   = "seta";
			portMongo = 27017;
		}

		try {
			mongoClient = new MongoClient(hostMongo, portMongo);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
		morphia = new Morphia();
		morphia.getMapper().getConverters()
				.addConverter(BigDecimalConverter.class);
		morphia.createDatastore(mongoClient, dbMongo);
		datastore = morphia.mapPackage("br.com.seta.dto").createDatastore(mongoClient, dbMongo);
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setMorphia(Morphia morphia) {
		this.morphia = morphia;
	}

	public Datastore getDatastore() {
		return datastore;
	}

	public void setDatastore(Datastore datastore) {
		this.datastore = datastore;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public void setMongoClient(MongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	public String getHostMongo() {
		return hostMongo;
	}

	public void setHostMongo(String hostMongo) {
		this.hostMongo = hostMongo;
	}

	public String getDbMongo() {
		return dbMongo;
	}

	public void setDbMongo(String dbMongo) {
		this.dbMongo = dbMongo;
	}

	public Integer getPortMongo() {
		return portMongo;
	}

	public void setPortMongo(Integer portMongo) {
		this.portMongo = portMongo;
	}

}
