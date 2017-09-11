package br.com.seta.processo.dto;


import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.mongodb.morphia.annotations.Entity;


@Entity("OrReqplanilhalancto")
public class OrReqplanilhalancto implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nroplanilha;
	private short nroempresa;
	private short filial;
	private Long contadebito;
	private String tipoentidadedb;
	private Integer codentidadedb;
	private Integer centrocustodb;
	private Long contacredito;
	private String tipoentidadecr;
	private Integer codentidadecr;
	private Integer centrocustocr;
	private String historico;
	private BigDecimal vlrlancamento;
	private Date dtacontabiliza;
	private String tablink;
	private Long nrodocumento;
	private String situacao;
	private String tipocontab;
	@NotNull(message="O percentual de rateio é obrigatório")
	private BigDecimal percrateio = BigDecimal.ZERO;
	private long seqrequisicao;
	private short nrolinha;	

	public OrReqplanilhalancto() {
	}	

	public OrReqplanilhalancto(String nroplanilha, short nroempresa,
			short filial, Long contadebito, String tipoentidadedb,
			Integer codentidadedb, Integer centrocustodb, Long contacredito,
			String tipoentidadecr, Integer codentidadecr,
			Integer centrocustocr, String historico, BigDecimal vlrlancamento,
			Date dtacontabiliza, String tablink, Long nrodocumento,
			String situacao, String tipocontab, BigDecimal percrateio,
			long seqrequisicao, short nrolinha) {
		super();
		this.nroplanilha = nroplanilha;
		this.nroempresa = nroempresa;
		this.filial = filial;
		this.contadebito = contadebito;
		this.tipoentidadedb = tipoentidadedb;
		this.codentidadedb = codentidadedb;
		this.centrocustodb = centrocustodb;
		this.contacredito = contacredito;
		this.tipoentidadecr = tipoentidadecr;
		this.codentidadecr = codentidadecr;
		this.centrocustocr = centrocustocr;
		this.historico = historico;
		this.vlrlancamento = vlrlancamento;
		this.dtacontabiliza = dtacontabiliza;
		this.tablink = tablink;
		this.nrodocumento = nrodocumento;
		this.situacao = situacao;
		this.tipocontab = tipocontab;
		this.percrateio = percrateio;
		this.seqrequisicao = seqrequisicao;
		this.nrolinha = nrolinha;
	}
	
	public String getNroplanilha() {
		return nroplanilha;
	}

	public void setNroplanilha(String nroplanilha) {
		this.nroplanilha = nroplanilha;
	}


	public short getNroempresa() {
		return nroempresa;
	}

	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}
	

	public short getFilial() {
		return filial;
	}

	public void setFilial(short filial) {
		this.filial = filial;
	}


	public Long getContadebito() {
		return contadebito;
	}

	public void setContadebito(Long contadebito) {
		this.contadebito = contadebito;
	}


	public String getTipoentidadedb() {
		return tipoentidadedb;
	}

	public void setTipoentidadedb(String tipoentidadedb) {
		this.tipoentidadedb = tipoentidadedb;
	}


	public Integer getCodentidadedb() {
		return codentidadedb;
	}

	public void setCodentidadedb(Integer codentidadedb) {
		this.codentidadedb = codentidadedb;
	}


	public Integer getCentrocustodb() {
		return centrocustodb;
	}

	public void setCentrocustodb(Integer centrocustodb) {
		this.centrocustodb = centrocustodb;
	}


	public Long getContacredito() {
		return contacredito;
	}

	public void setContacredito(Long contacredito) {
		this.contacredito = contacredito;
	}


	public String getTipoentidadecr() {
		return tipoentidadecr;
	}

	public void setTipoentidadecr(String tipoentidadecr) {
		this.tipoentidadecr = tipoentidadecr;
	}


	public Integer getCodentidadecr() {
		return codentidadecr;
	}
	
	public void setCodentidadecr(Integer codentidadecr) {
		this.codentidadecr = codentidadecr;
	}
	

	public Integer getCentrocustocr() {
		return centrocustocr;
	}

	public void setCentrocustocr(Integer centrocustocr) {
		this.centrocustocr = centrocustocr;
	}


	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}


	public BigDecimal getVlrlancamento() {
		return vlrlancamento;
	}

	public void setVlrlancamento(BigDecimal vlrlancamento) {
		this.vlrlancamento = vlrlancamento;
	}


	public Date getDtacontabiliza() {
		return dtacontabiliza;
	}

	public void setDtacontabiliza(Date dtacontabiliza) {
		this.dtacontabiliza = dtacontabiliza;
	}


	public String getTablink() {
		return tablink;
	}

	public void setTablink(String tablink) {
		this.tablink = tablink;
	}

	public Long getNrodocumento() {
		return nrodocumento;
	}

	public void setNrodocumento(Long nrodocumento) {
		this.nrodocumento = nrodocumento;
	}


	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}


	public String getTipocontab() {
		return tipocontab;
	}

	public void setTipocontab(String tipocontab) {
		this.tipocontab = tipocontab;
	}


	public BigDecimal getPercrateio() {
		return percrateio;
	}

	public void setPercrateio(BigDecimal percrateio) {
		this.percrateio = percrateio;
	}


	public long getSeqrequisicao() {
		return seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}


	public short getNrolinha() {
		return nrolinha;
	}

	public void setNrolinha(short nrolinha) {
		this.nrolinha = nrolinha;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((centrocustodb == null) ? 0 : centrocustodb.hashCode());
		result = prime * result + nrolinha;
		return result;
	}	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrReqplanilhalancto other = (OrReqplanilhalancto) obj;
		if (centrocustodb == null) {
			if (other.centrocustodb != null)
				return false;
		} else if (!centrocustodb.equals(other.centrocustodb))
			return false;
		if (nrolinha != other.nrolinha)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrReqplanilhalancto [nroplanilha=" + nroplanilha
				+ ", nroempresa=" + nroempresa + ", filial=" + filial
				+ ", contadebito=" + contadebito + ", tipoentidadedb="
				+ tipoentidadedb + ", codentidadedb=" + codentidadedb
				+ ", centrocustodb=" + centrocustodb + ", contacredito="
				+ contacredito + ", tipoentidadecr=" + tipoentidadecr
				+ ", codentidadecr=" + codentidadecr + ", centrocustocr="
				+ centrocustocr + ", historico=" + historico
				+ ", vlrlancamento=" + vlrlancamento + ", dtacontabiliza="
				+ dtacontabiliza + ", tablink=" + tablink + ", nrodocumento="
				+ nrodocumento + ", situacao=" + situacao + ", tipocontab="
				+ tipocontab + ", percrateio=" + percrateio
				+ ", seqrequisicao=" + seqrequisicao + ", nrolinha=" + nrolinha
				+ "]";
	}
	
	
	


}
