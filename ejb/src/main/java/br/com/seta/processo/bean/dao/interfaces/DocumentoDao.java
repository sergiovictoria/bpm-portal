package br.com.seta.processo.bean.dao.interfaces;

import java.util.List;

import org.mongodb.morphia.query.Query;

import br.com.seta.processo.dto.Documento;

public interface DocumentoDao {

	long quantidadeDocumentos(Long caseId, String descricao);
	long quantidadeDocumentos(long ...caseId);
	long quantidadeDocumentos(String query);
	List<Documento> buscaMetadadosDocumentos(Long caseId, String descricao, Long primeiro, Long quantidade);
	List<Documento> buscaMetadadosDocumentos(Long primeiro, Long quantidade, long ...caseId);
	/**
	 * Busca os metadados dos documentos 
	 * 
	 * @param query Consulta base para a busca dos metadados dos documentos
	 * @param primeiro índice do primeiro registro. Opcional, caso seja nulo, será desconsiderado
	 * @param quantidade quantidade de registros retornados. Opcional, caso seja nulo, será desconsiderado
	 * @return Uma lista contendo os metadados dos documentos
	 */
	List<Documento> buscaMetadadosDocumento(String query, Long primeiro, Long quantidade);
	Documento buscaDocumento(String id);
	void salvaDocumento(Documento documento, long caseId);
	void salvaDocumento(Documento documento);
	Query<Documento> criaQuery();
	
}
