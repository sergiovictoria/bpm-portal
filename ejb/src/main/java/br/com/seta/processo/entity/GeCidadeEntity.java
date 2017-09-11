package br.com.seta.processo.entity;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedNativeQueries({ @NamedNativeQuery(name = "GeCidadeEntity.find.cidade", query = "SELECT A.* FROM GE_CIDADE A WHERE A.CIDADE = fc5limpaacento(:cidade) AND A.UF = :uf", resultClass = br.com.seta.processo.entity.GeCidadeEntity.class)})

@Entity
@Table(name = "GE_CIDADE")
public class GeCidadeEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private int seqcidade;
	private String cidade;
	private String uf;
	private String regiao;
	private Integer populacao;
	private Date dtaaniversario;
	private String cepinicial;
	private String cepfinal;
	private String ddd;
	private String pais;
	private Date dtaferiado1;
	private String descferiado1;
	private Date dtaferiado2;
	private String descferiado2;
	private String exgbairro;
	private String exglogradouro;
	private Date dtaalteracao;
	private String usualteracao;
	private String indreplicacao;
	private String indgeroureplic;
	private Integer codmunicipio;
	private String pertzfmalc;
	private Short codestadozfmalc;
	private Integer codzfmalc;
	private Long codibge;
	private Short prazoadicvencto;
	private String cepinicial1;
	private String cepfinal1;
	private Integer codpais;
	private Long codpracabcosicred;
	private Long codsiafi;
	private String siglapais;
	private BigDecimal percfrete;
	private long nrobaseexportacao;
	private BigDecimal vlrlimiteboleto;
	private Integer codmundac;
	private Long seqcidadecorreios;
	private String inddistrito;
	private Integer seqcidadedistrito;

	public GeCidadeEntity() {
	}

	public GeCidadeEntity(int seqcidade, String cidade, String uf, String exgbairro, String exglogradouro, Date dtaalteracao,
			String usualteracao, long nrobaseexportacao) {
		this.seqcidade = seqcidade;
		this.cidade = cidade;
		this.uf = uf;
		this.exgbairro = exgbairro;
		this.exglogradouro = exglogradouro;
		this.dtaalteracao = dtaalteracao;
		this.usualteracao = usualteracao;
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public GeCidadeEntity(int seqcidade, String cidade, String uf, String regiao, Integer populacao, Date dtaaniversario, String cepinicial,
			String cepfinal, String ddd, String pais, Date dtaferiado1, String descferiado1, Date dtaferiado2, String descferiado2,
			String exgbairro, String exglogradouro, Date dtaalteracao, String usualteracao, String indreplicacao, String indgeroureplic,
			Integer codmunicipio, String pertzfmalc, Short codestadozfmalc, Integer codzfmalc, Long codibge, Short prazoadicvencto,
			String cepinicial1, String cepfinal1, Integer codpais, Long codpracabcosicred, Long codsiafi, String siglapais,
			BigDecimal percfrete, long nrobaseexportacao, BigDecimal vlrlimiteboleto, Integer codmundac, Long seqcidadecorreios,
			String inddistrito, Integer seqcidadedistrito) {
		this.seqcidade = seqcidade;
		this.cidade = cidade;
		this.uf = uf;
		this.regiao = regiao;
		this.populacao = populacao;
		this.dtaaniversario = dtaaniversario;
		this.cepinicial = cepinicial;
		this.cepfinal = cepfinal;
		this.ddd = ddd;
		this.pais = pais;
		this.dtaferiado1 = dtaferiado1;
		this.descferiado1 = descferiado1;
		this.dtaferiado2 = dtaferiado2;
		this.descferiado2 = descferiado2;
		this.exgbairro = exgbairro;
		this.exglogradouro = exglogradouro;
		this.dtaalteracao = dtaalteracao;
		this.usualteracao = usualteracao;
		this.indreplicacao = indreplicacao;
		this.indgeroureplic = indgeroureplic;
		this.codmunicipio = codmunicipio;
		this.pertzfmalc = pertzfmalc;
		this.codestadozfmalc = codestadozfmalc;
		this.codzfmalc = codzfmalc;
		this.codibge = codibge;
		this.prazoadicvencto = prazoadicvencto;
		this.cepinicial1 = cepinicial1;
		this.cepfinal1 = cepfinal1;
		this.codpais = codpais;
		this.codpracabcosicred = codpracabcosicred;
		this.codsiafi = codsiafi;
		this.siglapais = siglapais;
		this.percfrete = percfrete;
		this.nrobaseexportacao = nrobaseexportacao;
		this.vlrlimiteboleto = vlrlimiteboleto;
		this.codmundac = codmundac;
		this.seqcidadecorreios = seqcidadecorreios;
		this.inddistrito = inddistrito;
		this.seqcidadedistrito = seqcidadedistrito;
	}

	@Id
	@SequenceGenerator( name = "SEQCIDADE", sequenceName = "CLIENTE_SEQ", allocationSize = 1 )
	@Column(name = "SEQCIDADE", unique = true, nullable = false, precision = 6, scale = 0)
	public int getSeqcidade() {
		return this.seqcidade;
	}

	public void setSeqcidade(int seqcidade) {
		this.seqcidade = seqcidade;
	}

	@Column(name = "CIDADE", nullable = false, length = 60)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "UF", nullable = false, length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Column(name = "REGIAO", length = 12)
	public String getRegiao() {
		return this.regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	@Column(name = "POPULACAO", precision = 8, scale = 0)
	public Integer getPopulacao() {
		return this.populacao;
	}

	public void setPopulacao(Integer populacao) {
		this.populacao = populacao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAANIVERSARIO", length = 7)
	public Date getDtaaniversario() {
		return this.dtaaniversario;
	}

	public void setDtaaniversario(Date dtaaniversario) {
		this.dtaaniversario = dtaaniversario;
	}

	@Column(name = "CEPINICIAL", length = 12)
	public String getCepinicial() {
		return this.cepinicial;
	}

	public void setCepinicial(String cepinicial) {
		this.cepinicial = cepinicial;
	}

	@Column(name = "CEPFINAL", length = 12)
	public String getCepfinal() {
		return this.cepfinal;
	}

	public void setCepfinal(String cepfinal) {
		this.cepfinal = cepfinal;
	}

	@Column(name = "DDD", length = 5)
	public String getDdd() {
		return this.ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	@Column(name = "PAIS", length = 25)
	public String getPais() {
		return this.pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAFERIADO1", length = 7)
	public Date getDtaferiado1() {
		return this.dtaferiado1;
	}

	public void setDtaferiado1(Date dtaferiado1) {
		this.dtaferiado1 = dtaferiado1;
	}

	@Column(name = "DESCFERIADO1", length = 20)
	public String getDescferiado1() {
		return this.descferiado1;
	}

	public void setDescferiado1(String descferiado1) {
		this.descferiado1 = descferiado1;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAFERIADO2", length = 7)
	public Date getDtaferiado2() {
		return this.dtaferiado2;
	}

	public void setDtaferiado2(Date dtaferiado2) {
		this.dtaferiado2 = dtaferiado2;
	}

	@Column(name = "DESCFERIADO2", length = 20)
	public String getDescferiado2() {
		return this.descferiado2;
	}

	public void setDescferiado2(String descferiado2) {
		this.descferiado2 = descferiado2;
	}

	@Column(name = "EXGBAIRRO", nullable = false, length = 1)
	public String getExgbairro() {
		return this.exgbairro;
	}

	public void setExgbairro(String exgbairro) {
		this.exgbairro = exgbairro;
	}

	@Column(name = "EXGLOGRADOURO", nullable = false, length = 1)
	public String getExglogradouro() {
		return this.exglogradouro;
	}

	public void setExglogradouro(String exglogradouro) {
		this.exglogradouro = exglogradouro;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAO", nullable = false, length = 7)
	public Date getDtaalteracao() {
		return this.dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	@Column(name = "USUALTERACAO", nullable = false, length = 12)
	public String getUsualteracao() {
		return this.usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	@Column(name = "INDREPLICACAO", length = 1)
	public String getIndreplicacao() {
		return this.indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	@Column(name = "INDGEROUREPLIC", length = 1)
	public String getIndgeroureplic() {
		return this.indgeroureplic;
	}

	public void setIndgeroureplic(String indgeroureplic) {
		this.indgeroureplic = indgeroureplic;
	}

	@Column(name = "CODMUNICIPIO", precision = 5, scale = 0)
	public Integer getCodmunicipio() {
		return this.codmunicipio;
	}

	public void setCodmunicipio(Integer codmunicipio) {
		this.codmunicipio = codmunicipio;
	}

	@Column(name = "PERTZFMALC", length = 1)
	public String getPertzfmalc() {
		return this.pertzfmalc;
	}

	public void setPertzfmalc(String pertzfmalc) {
		this.pertzfmalc = pertzfmalc;
	}

	@Column(name = "CODESTADOZFMALC", precision = 3, scale = 0)
	public Short getCodestadozfmalc() {
		return this.codestadozfmalc;
	}

	public void setCodestadozfmalc(Short codestadozfmalc) {
		this.codestadozfmalc = codestadozfmalc;
	}

	@Column(name = "CODZFMALC", precision = 5, scale = 0)
	public Integer getCodzfmalc() {
		return this.codzfmalc;
	}

	public void setCodzfmalc(Integer codzfmalc) {
		this.codzfmalc = codzfmalc;
	}

	@Column(name = "CODIBGE", precision = 12, scale = 0)
	public Long getCodibge() {
		return this.codibge;
	}

	public void setCodibge(Long codibge) {
		this.codibge = codibge;
	}

	@Column(name = "PRAZOADICVENCTO", precision = 3, scale = 0)
	public Short getPrazoadicvencto() {
		return this.prazoadicvencto;
	}

	public void setPrazoadicvencto(Short prazoadicvencto) {
		this.prazoadicvencto = prazoadicvencto;
	}

	@Column(name = "CEPINICIAL1", length = 12)
	public String getCepinicial1() {
		return this.cepinicial1;
	}

	public void setCepinicial1(String cepinicial1) {
		this.cepinicial1 = cepinicial1;
	}

	@Column(name = "CEPFINAL1", length = 12)
	public String getCepfinal1() {
		return this.cepfinal1;
	}

	public void setCepfinal1(String cepfinal1) {
		this.cepfinal1 = cepfinal1;
	}

	@Column(name = "CODPAIS", precision = 5, scale = 0)
	public Integer getCodpais() {
		return this.codpais;
	}

	public void setCodpais(Integer codpais) {
		this.codpais = codpais;
	}

	@Column(name = "CODPRACABCOSICRED", precision = 10, scale = 0)
	public Long getCodpracabcosicred() {
		return this.codpracabcosicred;
	}

	public void setCodpracabcosicred(Long codpracabcosicred) {
		this.codpracabcosicred = codpracabcosicred;
	}

	@Column(name = "CODSIAFI", precision = 10, scale = 0)
	public Long getCodsiafi() {
		return this.codsiafi;
	}

	public void setCodsiafi(Long codsiafi) {
		this.codsiafi = codsiafi;
	}

	@Column(name = "SIGLAPAIS", length = 2)
	public String getSiglapais() {
		return this.siglapais;
	}

	public void setSiglapais(String siglapais) {
		this.siglapais = siglapais;
	}

	@Column(name = "PERCFRETE", precision = 7, scale = 4)
	public BigDecimal getPercfrete() {
		return this.percfrete;
	}

	public void setPercfrete(BigDecimal percfrete) {
		this.percfrete = percfrete;
	}

	@Column(name = "NROBASEEXPORTACAO", nullable = false, precision = 10, scale = 0)
	public long getNrobaseexportacao() {
		return this.nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	@Column(name = "VLRLIMITEBOLETO", precision = 15)
	public BigDecimal getVlrlimiteboleto() {
		return this.vlrlimiteboleto;
	}

	public void setVlrlimiteboleto(BigDecimal vlrlimiteboleto) {
		this.vlrlimiteboleto = vlrlimiteboleto;
	}

	@Column(name = "CODMUNDAC", precision = 6, scale = 0)
	public Integer getCodmundac() {
		return this.codmundac;
	}

	public void setCodmundac(Integer codmundac) {
		this.codmundac = codmundac;
	}

	@Column(name = "SEQCIDADECORREIOS", precision = 15, scale = 0)
	public Long getSeqcidadecorreios() {
		return this.seqcidadecorreios;
	}

	public void setSeqcidadecorreios(Long seqcidadecorreios) {
		this.seqcidadecorreios = seqcidadecorreios;
	}

	@Column(name = "INDDISTRITO", length = 1)
	public String getInddistrito() {
		return this.inddistrito;
	}

	public void setInddistrito(String inddistrito) {
		this.inddistrito = inddistrito;
	}

	@Column(name = "SEQCIDADEDISTRITO", precision = 6, scale = 0)
	public Integer getSeqcidadedistrito() {
		return this.seqcidadedistrito;
	}

	public void setSeqcidadedistrito(Integer seqcidadedistrito) {
		this.seqcidadedistrito = seqcidadedistrito;
	}

	
	@Override
	public String toString() {
		return "GeCidadeEntity [seqcidade=" + seqcidade + ", cidade=" + cidade + ", uf=" + uf + ", regiao=" + regiao + ", populacao="
				+ populacao + ", dtaaniversario=" + dtaaniversario + ", cepinicial=" + cepinicial + ", cepfinal=" + cepfinal + ", ddd="
				+ ddd + ", pais=" + pais + ", dtaferiado1=" + dtaferiado1 + ", descferiado1=" + descferiado1 + ", dtaferiado2="
				+ dtaferiado2 + ", descferiado2=" + descferiado2 + ", exgbairro=" + exgbairro + ", exglogradouro=" + exglogradouro
				+ ", dtaalteracao=" + dtaalteracao + ", usualteracao=" + usualteracao + ", indreplicacao=" + indreplicacao
				+ ", indgeroureplic=" + indgeroureplic + ", codmunicipio=" + codmunicipio + ", pertzfmalc=" + pertzfmalc
				+ ", codestadozfmalc=" + codestadozfmalc + ", codzfmalc=" + codzfmalc + ", codibge=" + codibge + ", prazoadicvencto="
				+ prazoadicvencto + ", cepinicial1=" + cepinicial1 + ", cepfinal1=" + cepfinal1 + ", codpais=" + codpais
				+ ", codpracabcosicred=" + codpracabcosicred + ", codsiafi=" + codsiafi + ", siglapais=" + siglapais + ", percfrete="
				+ percfrete + ", nrobaseexportacao=" + nrobaseexportacao + ", vlrlimiteboleto=" + vlrlimiteboleto + ", codmundac="
				+ codmundac + ", seqcidadecorreios=" + seqcidadecorreios + ", inddistrito=" + inddistrito + ", seqcidadedistrito="
				+ seqcidadedistrito + "]";
	}

}
