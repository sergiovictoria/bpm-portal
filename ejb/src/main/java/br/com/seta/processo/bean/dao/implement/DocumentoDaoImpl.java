package br.com.seta.processo.bean.dao.implement;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.connection.MongoConnectionManager;
import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.MongoDbObjParser;

@Stateless(name="DocumentoDao")
@Local(DocumentoDao.class)
@Interceptors({LogInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DocumentoDaoImpl extends BasicDAO<Documento, String> implements Serializable, DocumentoDao {
	private static final long serialVersionUID = 1L;

	public DocumentoDaoImpl() {
		super(MongoConnectionManager.getInstance().getDatastore());
	}	

	@Override
	public List<Documento> buscaMetadadosDocumentos(Long caseId, String descricao, Long primeiro, Long quantidade) {
		Query<Documento> query = criaQueryBuscaDocumentos(caseId, descricao);
		return comoLista(query, primeiro, quantidade);
	}

	@Override
	public List<Documento> buscaMetadadosDocumentos(Long primeiro, Long quantidade, long... caseId) {
		Query<Documento> query = criaQuerybuscaDocumentos(caseId);
		return comoLista(query, primeiro, quantidade);
	}

	public Documento buscaDocumento(String id){
		Query<Documento> query = createQuery().field("_id").equal(id);
		return findOne(query);
	}

	@Override
	public long quantidadeDocumentos(Long caseId, String descricao){
		Query<Documento> query = criaQueryBuscaDocumentos(caseId, descricao);
		return query.countAll();
	}

	@Override
	public void salvaDocumento(Documento documento, long caseId) {
		documento.setCaseId(caseId);
		save(documento);
	}

	@Override
	public void salvaDocumento(Documento documento) {
		save(documento);
	}

	@Override
	public Query<Documento> criaQuery() {
		return createQuery();
	}

	private Query<Documento> criaQuerybuscaDocumentos(long... caseId) {
		return createQuery().field("caseId").in(Arrays.asList(caseId)).order("-dataCriacao");
	}

	private Query<Documento> criaQueryBuscaDocumentos(Long caseId, String descricao) {
		Query<Documento> query = createQuery().retrievedFields(false, "conteudo");
		
		if(caseId != null){
			query.field("caseId").equal(caseId);
		}
		
		if(descricao != null){
			query.field("descricao").equal(descricao);
		}
		return query;
	}

	private List<Documento> comoLista(Query<Documento> query, Long primeiro, Long quantidade) {
		if(primeiro != null)
			query.offset(primeiro.intValue());
			
		if(quantidade != null)
			query.limit(quantidade.intValue());
			
		return query.asList();
	}

	@Override
	public long quantidadeDocumentos(long... caseId) {
		return criaQuerybuscaDocumentos(caseId).countAll();
	}

	@Override
	public List<Documento> buscaMetadadosDocumento(String query, Long primeiro, Long quantidade) {
		DBObject consulta = (DBObject) JSON.parse(query);
		DBObject projecao = (DBObject) JSON.parse("{conteudo: false}");
		DBObject orderBy = (DBObject) JSON.parse("{dataCriacao: -1}");
		DBCursor cursor = getCollection().find(consulta, projecao).sort(orderBy);
		
		if(quantidade != null)
			cursor = cursor.limit(quantidade.intValue());
		if(primeiro != null)
			cursor = cursor.skip(primeiro.intValue());
		
		return MongoDbObjParser.getInstance().toList(cursor, Documento.class);	
	}

	@Override
	public long quantidadeDocumentos(String query) {
		DBObject consulta = (DBObject) JSON.parse(query);
		return getCollection().count(consulta);
	}

}
