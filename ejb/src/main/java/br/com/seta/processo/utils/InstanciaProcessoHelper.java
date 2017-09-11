package br.com.seta.processo.utils;

import java.util.ArrayList;
import java.util.List;

import org.mongodb.morphia.Morphia;

import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.InstanciaProcesso;

import com.mongodb.DBCursor;

/**
 * Helper para conversão dos objetos nativos do driver do mongoDB para InstanciaProcesso
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class InstanciaProcessoHelper {
	
	private Morphia morphia;
	private static InstanciaProcessoHelper instanciaProcessoHelper;
	
	private InstanciaProcessoHelper(){
		MongoConnectionManager connection = MongoConnectionManager.getInstance();
		this.morphia = connection.getMorphia();
	}
	
	public static InstanciaProcessoHelper getInstance(){
		if (instanciaProcessoHelper == null) {
			instanciaProcessoHelper = new InstanciaProcessoHelper();
		}
		
		return instanciaProcessoHelper;
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
