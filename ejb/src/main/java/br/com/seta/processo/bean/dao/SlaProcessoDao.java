package br.com.seta.processo.bean.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.bson.types.ObjectId;
import org.mongodb.morphia.dao.BasicDAO;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.entity.SlaProcesso;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="SlaProcessoDao")
@LocalBean
public class SlaProcessoDao extends BasicDAO<SlaProcesso, ObjectId> {

	public SlaProcessoDao(){
		super(SlaProcesso.class, MongoConnectionManager.getInstance().getDatastore());
	}
}
