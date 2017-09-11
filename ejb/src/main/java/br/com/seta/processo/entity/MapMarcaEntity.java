package br.com.seta.processo.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "MAP_MARCA")

@NamedNativeQueries({  
    @NamedNativeQuery(name = "MARCA.ALL",       query = "SELECT MAR.MARCA, MAR.SEQMARCA  FROM MAP_MARCA MAR ORDER BY MAR.MARCA ASC", resultClass = MapMarcaEntity.class),
    @NamedNativeQuery(name = "MARCA.LIKE_NAME", query = "SELECT MAR.MARCA, MAR.SEQMARCA FROM MAP_MARCA MAR WHERE MAR.MARCA LIKE :marca ORDER BY MAR.MARCA ASC", resultClass = MapMarcaEntity.class),
    @NamedNativeQuery(name = "MARCA.LIKE.SIZE", query = "SELECT COUNT (*) FROM MAP_MARCA MAR WHERE MAR.MARCA LIKE :marca"),
    @NamedNativeQuery(name = "MARCA.FIND_ONE",  query = "SELECT MAR.* FROM MAP_MARCA MAR WHERE MAR.SEQMARCA = :seqmarca", resultClass = MapMarcaEntity.class)
})

public class MapMarcaEntity implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	private int seqmarca;
	private String marca;
	private String indreplicacao;
	private String indgeroureplicacao;
	private String status;
	private String tipomarca;
	private String indtotrelpedvda;
	private long nrobaseexportacao;
	private Date datahoraalteracao;
	private String tituloecommerce;
	private String descecommerce;
	private String palavrachaveecommerce;
	private Date datahoraintegraecommerce;

	
	public MapMarcaEntity() {
	}

	@Id
	@Column(name = "SEQMARCA", unique = true, nullable = false, precision = 5, scale = 0)
	public int getSeqmarca() {
		return this.seqmarca;
	}

	public void setSeqmarca(int seqmarca) {
		this.seqmarca = seqmarca;
	}

	@Column(name = "MARCA", nullable = false, length = 20)
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
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

	@Column(name = "STATUS", length = 1)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "TIPOMARCA", length = 5)
	public String getTipomarca() {
		return this.tipomarca;
	}

	public void setTipomarca(String tipomarca) {
		this.tipomarca = tipomarca;
	}

	@Column(name = "INDTOTRELPEDVDA", length = 1)
	public String getIndtotrelpedvda() {
		return this.indtotrelpedvda;
	}

	public void setIndtotrelpedvda(String indtotrelpedvda) {
		this.indtotrelpedvda = indtotrelpedvda;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAHORAALTERACAO", length = 7)
	public Date getDatahoraalteracao() {
		return this.datahoraalteracao;
	}

	public void setDatahoraalteracao(Date datahoraalteracao) {
		this.datahoraalteracao = datahoraalteracao;
	}

	@Column(name = "TITULOECOMMERCE", length = 200)
	public String getTituloecommerce() {
		return this.tituloecommerce;
	}

	public void setTituloecommerce(String tituloecommerce) {
		this.tituloecommerce = tituloecommerce;
	}

	@Column(name = "DESCECOMMERCE", length = 500)
	public String getDescecommerce() {
		return this.descecommerce;
	}

	public void setDescecommerce(String descecommerce) {
		this.descecommerce = descecommerce;
	}

	@Column(name = "PALAVRACHAVEECOMMERCE", length = 500)
	public String getPalavrachaveecommerce() {
		return this.palavrachaveecommerce;
	}

	public void setPalavrachaveecommerce(String palavrachaveecommerce) {
		this.palavrachaveecommerce = palavrachaveecommerce;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DATAHORAINTEGRAECOMMERCE", length = 7)
	public Date getDatahoraintegraecommerce() {
		return this.datahoraintegraecommerce;
	}

	public void setDatahoraintegraecommerce(Date datahoraintegraecommerce) {
		this.datahoraintegraecommerce = datahoraintegraecommerce;
	}


}
