package br.com.seta.processo.entity;

import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "MAP_FAMFORNEC")
public class MapFamfornecEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private MapFamfornecId id;
	private MapFamiliaEntity mapFamiliaEntity;
	private String principal;
	private String indreplicacao;
	private String indgeroureplicacao;
	private Short nrosegfornec;
	private String indindenizavaria;
	private String tipfornecedorfam;
	private BigDecimal padraoembcomprafornec;
	private String calcdescsuframapiscofins;
	private String indcontrolaflex;
	private String indmedidametadistrib;
	private long nrobaseexportacao;
	private BigDecimal embpadraoimpxml;
	private String usuarioalteracao;
	private String indbuscanfautodevfornec;
	private BigDecimal fatorconvembxml;
	private String indcontrolaprinc;

	public MapFamfornecEntity() {
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqfamilia", column = @Column(name = "SEQFAMILIA", nullable = false, precision = 22, scale = 0)),
			@AttributeOverride(name = "seqfornecedor", column = @Column(name = "SEQFORNECEDOR", nullable = false, precision = 22, scale = 0)) })
	public MapFamfornecId getId() {
		return this.id;
	}

	public void setId(MapFamfornecId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEQFAMILIA", nullable = false, insertable = false, updatable = false)
	public MapFamiliaEntity getMapFamiliaEntity() {
		return mapFamiliaEntity;
	}

	public void setMapFamiliaEntity(MapFamiliaEntity mapFamiliaEntity) {
		this.mapFamiliaEntity = mapFamiliaEntity;
	}
		

	@Column(name = "PRINCIPAL", nullable = false, length = 1)
	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
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

	@Column(name = "NROSEGFORNEC", precision = 3, scale = 0)
	public Short getNrosegfornec() {
		return this.nrosegfornec;
	}

	public void setNrosegfornec(Short nrosegfornec) {
		this.nrosegfornec = nrosegfornec;
	}

	@Column(name = "INDINDENIZAVARIA", length = 1)
	public String getIndindenizavaria() {
		return this.indindenizavaria;
	}

	public void setIndindenizavaria(String indindenizavaria) {
		this.indindenizavaria = indindenizavaria;
	}

	@Column(name = "TIPFORNECEDORFAM", length = 1)
	public String getTipfornecedorfam() {
		return this.tipfornecedorfam;
	}

	public void setTipfornecedorfam(String tipfornecedorfam) {
		this.tipfornecedorfam = tipfornecedorfam;
	}

	@Column(name = "PADRAOEMBCOMPRAFORNEC", precision = 12, scale = 6)
	public BigDecimal getPadraoembcomprafornec() {
		return this.padraoembcomprafornec;
	}

	public void setPadraoembcomprafornec(BigDecimal padraoembcomprafornec) {
		this.padraoembcomprafornec = padraoembcomprafornec;
	}

	@Column(name = "CALCDESCSUFRAMAPISCOFINS", length = 1)
	public String getCalcdescsuframapiscofins() {
		return this.calcdescsuframapiscofins;
	}

	public void setCalcdescsuframapiscofins(String calcdescsuframapiscofins) {
		this.calcdescsuframapiscofins = calcdescsuframapiscofins;
	}

	@Column(name = "INDCONTROLAFLEX", length = 1)
	public String getIndcontrolaflex() {
		return this.indcontrolaflex;
	}

	public void setIndcontrolaflex(String indcontrolaflex) {
		this.indcontrolaflex = indcontrolaflex;
	}

	@Column(name = "INDMEDIDAMETADISTRIB", length = 1)
	public String getIndmedidametadistrib() {
		return this.indmedidametadistrib;
	}

	public void setIndmedidametadistrib(String indmedidametadistrib) {
		this.indmedidametadistrib = indmedidametadistrib;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "EMBPADRAOIMPXML", precision = 12, scale = 6)
	public BigDecimal getEmbpadraoimpxml() {
		return this.embpadraoimpxml;
	}

	public void setEmbpadraoimpxml(BigDecimal embpadraoimpxml) {
		this.embpadraoimpxml = embpadraoimpxml;
	}

	@Column(name = "USUARIOALTERACAO", length = 12)
	public String getUsuarioalteracao() {
		return this.usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}

	@Column(name = "INDBUSCANFAUTODEVFORNEC", length = 1)
	public String getIndbuscanfautodevfornec() {
		return this.indbuscanfautodevfornec;
	}

	public void setIndbuscanfautodevfornec(String indbuscanfautodevfornec) {
		this.indbuscanfautodevfornec = indbuscanfautodevfornec;
	}

	@Column(name = "FATORCONVEMBXML", precision = 12, scale = 6)
	public BigDecimal getFatorconvembxml() {
		return this.fatorconvembxml;
	}

	public void setFatorconvembxml(BigDecimal fatorconvembxml) {
		this.fatorconvembxml = fatorconvembxml;
	}

	@Column(name = "INDCONTROLAPRINC", length = 1)
	public String getIndcontrolaprinc() {
		return this.indcontrolaprinc;
	}

	public void setIndcontrolaprinc(String indcontrolaprinc) {
		this.indcontrolaprinc = indcontrolaprinc;
	}

}
