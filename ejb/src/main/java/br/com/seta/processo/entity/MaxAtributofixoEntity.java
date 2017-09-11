package br.com.seta.processo.entity;


import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;




@Entity
@Table(name = "MAX_ATRIBUTOFIXO")
@NamedNativeQueries({  
    @NamedNativeQuery(name = "ATRIBUTO.FIND.ALL",  query = "SELECT * FROM MAX_ATRIBUTOFIXO M WHERE M.TIPATRIBUTOFIXO = 'EMBALAGEM' ORDER BY M.DESCRICAO ASC", resultClass = MaxAtributofixoEntity.class),
    @NamedNativeQuery(name = "ATRIBUTO.FIND.ONE",  query = "SELECT * FROM MAX_ATRIBUTOFIXO M WHERE M.TIPATRIBUTOFIXO = 'EMBALAGEM' AND M.SEQATRIBUTOFIXO = :seqtributofixo", resultClass = MaxAtributofixoEntity.class),
    @NamedNativeQuery(name = "ATRIBUTO.LIKE_NAME", query = "SELECT * FROM MAX_ATRIBUTOFIXO M WHERE M.TIPATRIBUTOFIXO = 'EMBALAGEM' AND M.DESCRICAO LIKE :descricao", resultClass = MaxAtributofixoEntity.class),
    @NamedNativeQuery(name = "ATRIBUTO.LIKE_LIST", query = "SELECT A.LISTA FROM MAX_ATRIBUTOFIXO A WHERE A.TIPATRIBUTOFIXO = 'EMBALAGEM' ORDER BY A.LISTA",resultClass = MaxAtributofixoEntity.class)
})


public class MaxAtributofixoEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MaxAtributofixoId id;
	private String lista;
	private String descricao;
	private String indreplicacao;
	private String indgeroureplicacao;
	private String param1;
	private long nrobaseexportacao;

	public MaxAtributofixoEntity() {
	}

	public MaxAtributofixoEntity(MaxAtributofixoId id, String lista, String descricao, long nrobaseexportacao) {
		this.id = id;
		this.lista = lista;
		this.descricao = descricao;
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public MaxAtributofixoEntity(MaxAtributofixoId id, String lista, String descricao, String indreplicacao, String indgeroureplicacao,
			String param1, long nrobaseexportacao) {
		this.id = id;
		this.lista = lista;
		this.descricao = descricao;
		this.indreplicacao = indreplicacao;
		this.indgeroureplicacao = indgeroureplicacao;
		this.param1 = param1;
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "tipatributofixo", column = @Column(name = "TIPATRIBUTOFIXO", nullable = false, length = 21)),
			@AttributeOverride(name = "seqatributofixo", column = @Column(name = "SEQATRIBUTOFIXO", nullable = false, precision = 3, scale = 0)) })
	public MaxAtributofixoId getId() {
		return this.id;
	}

	public void setId(MaxAtributofixoId id) {
		this.id = id;
	}

	@Column(name = "LISTA", nullable = false, length = 5)
	public String getLista() {
		return this.lista;
	}

	public void setLista(String lista) {
		this.lista = lista;
	}

	@Column(name = "DESCRICAO", nullable = false, length = 246)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "INDREPLICACAO", length = 1)
	public String getIndreplicacao() {
		return this.indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	@Column(name = "INDGEROUREPLICACAO", length = 1)
	public String getIndgeroureplicacao() {
		return this.indgeroureplicacao;
	}

	public void setIndgeroureplicacao(String indgeroureplicacao) {
		this.indgeroureplicacao = indgeroureplicacao;
	}

	@Column(name = "PARAM1", length = 80)
	public String getParam1() {
		return this.param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

}
