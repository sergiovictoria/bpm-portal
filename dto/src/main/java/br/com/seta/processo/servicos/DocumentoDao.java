package br.com.seta.processo.servicos;

import java.util.Collection;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.dao.BasicDAO;

import br.com.seta.processo.dto.Documento;

public class DocumentoDao extends BasicDAO<Documento, String> {
	public DocumentoDao(Datastore ds) {
		super(ds);
	}

	public DocumentoDao(Class<Documento> entityClass, Datastore ds) {
		super(entityClass, ds);
	}
	
	public void salva(Documento documento, Long caseId){
		documento.setCaseId(caseId);
		this.save(documento);
	}
	
	public void salva(Collection<Documento> documentos, Long caseId){
		for(Documento documento : documentos){
			documento.setCaseId(caseId);
			this.save(documento);
		}
	}
}
