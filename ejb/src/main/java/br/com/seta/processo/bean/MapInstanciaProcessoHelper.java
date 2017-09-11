/**
 * 
 */
package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Morphia;

import com.mongodb.DBCursor;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;

/**
 * @author Sérgio da Victória
 *
 *   
 */
public final class MapInstanciaProcessoHelper {

	private Morphia morphia;
	
	private static MapInstanciaProcessoHelper mapInstanciaProcesso;
	
	public static MapInstanciaProcessoHelper getInstance(){
		if (mapInstanciaProcesso == null) {
			mapInstanciaProcesso = new MapInstanciaProcessoHelper();
		}
		
		return mapInstanciaProcesso;
	}

	public MapInstanciaProcessoHelper() {
		MongoConnectionManager connection = MongoConnectionManager.getInstance();
		this.morphia = connection.getMorphia();
	}

	public List<InstanciaProcesso> paraListaDeInstanciaProcesso(DBCursor cursor){
		List<InstanciaProcesso> instancias = new ArrayList<InstanciaProcesso>();
		while (cursor.hasNext()) {
			InstanciaProcesso instancia = morphia.fromDBObject(InstanciaProcesso.class, cursor.next());
			instancias.add(instancia);
		}
		return instancias;
	}


}
