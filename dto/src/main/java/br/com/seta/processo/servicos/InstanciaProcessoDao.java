package br.com.seta.processo.servicos;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;
import br.com.seta.processo.dto.InstanciaProcesso;

public class InstanciaProcessoDao extends BasicDAO<InstanciaProcesso, String> {

	public InstanciaProcessoDao(Datastore ds) {
		super(ds);
	}

	public InstanciaProcessoDao(Class<InstanciaProcesso> entityClass,
			Datastore ds) {
		super(entityClass, ds);
	}
}
