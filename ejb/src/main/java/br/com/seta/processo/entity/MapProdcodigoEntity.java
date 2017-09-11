package br.com.seta.processo.entity;



import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamedNativeQueries({  
    @NamedNativeQuery(name = "FIND.QTDEMBALAGEM", query = "SELECT * FROM MAP_PRODCODIGO PRO WHERE PRO.SEQPRODUTO = :seqproduto AND PRO.SEQFAMILIA = :seqfamilia AND PRO.QTDEMBALAGEM = :qtdembalagem", resultClass = MapProdcodigoEntity.class),
    @NamedNativeQuery(name = "FIND.QTDEMBALAGEM_ID", query = "SELECT * FROM MAP_PRODCODIGO PRO WHERE PRO.SEQFAMILIA = :seqfamilia AND PRO.QTDEMBALAGEM = :qtdembalagem", resultClass = MapProdcodigoEntity.class),
    @NamedNativeQuery(name = "FIND.QTDEMBALAGEM_ID_TIPO", query = "SELECT * FROM MAP_PRODCODIGO PRO WHERE PRO.SEQFAMILIA = :seqfamilia AND PRO.QTDEMBALAGEM = :qtdembalagem AND PRO.TIPCODIGO = :tipcodigo", resultClass = MapProdcodigoEntity.class),
    @NamedNativeQuery(name = "FIND.QTDEMBALAGEM_MAPPROD_COD", query = "SELECT * FROM MAP_PRODCODIGO PRO WHERE PRO.CGCFORNEC = :cgcfornec AND PRO.CODACESSO = :codacesso AND PRO.TIPCODIGO = :tipcodigo", resultClass = MapProdcodigoEntity.class)
})    


@Entity
@Table(name = "MAP_PRODCODIGO")
public class MapProdcodigoEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private MapProdcodigoId id;
	private String indreplicacao;
	private String indgeroureplicacao;
	private String indutilvenda;
	private BigDecimal codacessonum;
	private String indutilwms;
	private String indprefedi;
	private String indeantribnfe;
	private Date dtahoralteracargapdv;
	private String usuarioalteracao;
	private Date dtainiutilcodtrans;
	private Date dtafinalutilcodtrans;
	private BigDecimal seqfamilia;
	private BigDecimal seqproduto;
	private BigDecimal qtdembalagem;
	
	
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "codacesso", column = @Column(name = "CODACESSO", nullable = false, length = 60)),
			@AttributeOverride(name = "cgcfornec", column = @Column(name = "CGCFORNEC", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "tipcodigo", column = @Column(name = "TIPCODIGO", nullable = false, length = 1)) })
	public MapProdcodigoId getId() {
		return this.id;
	}

	public void setId(MapProdcodigoId id) {
		this.id = id;
	}
	

	@Column(name = "SEQFAMILIA", nullable = false, precision = 38, scale = 0)
	public BigDecimal getSeqfamilia() {
		return seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	@Column(name = "SEQPRODUTO", nullable = false, precision = 38, scale = 0)
	public BigDecimal getSeqproduto() {
		return seqproduto;
	}

	public void setSeqproduto(BigDecimal seqproduto) {
		this.seqproduto = seqproduto;
	}

	@Column(name = "QTDEMBALAGEM", nullable = false, precision = 12, scale = 6)
	public BigDecimal getQtdembalagem() {
		return qtdembalagem;
	}

	public void setQtdembalagem(BigDecimal qtdembalagem) {
		this.qtdembalagem = qtdembalagem;
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

	@Column(name = "INDUTILVENDA", length = 1)
	public String getIndutilvenda() {
		return this.indutilvenda;
	}

	public void setIndutilvenda(String indutilvenda) {
		this.indutilvenda = indutilvenda;
	}

	@Column(name = "CODACESSONUM", precision = 22, scale = 0)
	public BigDecimal getCodacessonum() {
		return this.codacessonum;
	}

	public void setCodacessonum(BigDecimal codacessonum) {
		this.codacessonum = codacessonum;
	}

	@Column(name = "INDUTILWMS", length = 1)
	public String getIndutilwms() {
		return this.indutilwms;
	}

	public void setIndutilwms(String indutilwms) {
		this.indutilwms = indutilwms;
	}

	@Column(name = "INDPREFEDI", length = 1)
	public String getIndprefedi() {
		return this.indprefedi;
	}

	public void setIndprefedi(String indprefedi) {
		this.indprefedi = indprefedi;
	}

	@Column(name = "INDEANTRIBNFE", length = 1)
	public String getIndeantribnfe() {
		return this.indeantribnfe;
	}

	public void setIndeantribnfe(String indeantribnfe) {
		this.indeantribnfe = indeantribnfe;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAHORALTERACARGAPDV", length = 7)
	public Date getDtahoralteracargapdv() {
		return this.dtahoralteracargapdv;
	}

	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}

	@Column(name = "USUARIOALTERACAO", length = 12)
	public String getUsuarioalteracao() {
		return this.usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAINIUTILCODTRANS", length = 7)
	public Date getDtainiutilcodtrans() {
		return this.dtainiutilcodtrans;
	}

	public void setDtainiutilcodtrans(Date dtainiutilcodtrans) {
		this.dtainiutilcodtrans = dtainiutilcodtrans;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAFINALUTILCODTRANS", length = 7)
	public Date getDtafinalutilcodtrans() {
		return this.dtafinalutilcodtrans;
	}

	public void setDtafinalutilcodtrans(Date dtafinalutilcodtrans) {
		this.dtafinalutilcodtrans = dtafinalutilcodtrans;
	}

}
