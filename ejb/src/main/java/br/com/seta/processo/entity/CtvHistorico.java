package br.com.seta.processo.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CTV_HISTORICO")
public class CtvHistorico implements java.io.Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	private BigDecimal nroempresa;
	private BigDecimal codhistorico;
	private String descricao;
	private String pesquisarapida;
	private BigDecimal contadebito;
	private BigDecimal contacredito;
	private String codplanilha;
	private String tipoentidadedb;
	private BigDecimal codentidadedb;
	private BigDecimal centrocustodb;
	private String tipoentidadecr;
	private BigDecimal codentidadecr;
	private BigDecimal centrocustocr;
	private String nroplanilha;

	public CtvHistorico() {
	}
	
	

	public CtvHistorico(BigDecimal nroempresa, BigDecimal codhistorico, String descricao) {
		this.nroempresa = nroempresa;
		this.codhistorico = codhistorico;
		this.descricao = descricao;
	}



	public CtvHistorico(BigDecimal nroempresa, BigDecimal codhistorico,
			String descricao, String pesquisarapida, BigDecimal contadebito,
			BigDecimal contacredito, String codplanilha, String tipoentidadedb,
			BigDecimal codentidadedb, BigDecimal centrocustodb,
			String tipoentidadecr, BigDecimal codentidadecr,
			BigDecimal centrocustocr, String nroplanilha) {
		this.nroempresa = nroempresa;
		this.codhistorico = codhistorico;
		this.descricao = descricao;
		this.pesquisarapida = pesquisarapida;
		this.contadebito = contadebito;
		this.contacredito = contacredito;
		this.codplanilha = codplanilha;
		this.tipoentidadedb = tipoentidadedb;
		this.codentidadedb = codentidadedb;
		this.centrocustodb = centrocustodb;
		this.tipoentidadecr = tipoentidadecr;
		this.codentidadecr = codentidadecr;
		this.centrocustocr = centrocustocr;
		this.nroplanilha = nroplanilha;
	}

	@Column(name = "NROEMPRESA", precision = 22, scale = 0)
	public BigDecimal getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}

	@Column(name = "CODHISTORICO", precision = 22, scale = 0)
	public BigDecimal getCodhistorico() {
		return this.codhistorico;
	}

	public void setCodhistorico(BigDecimal codhistorico) {
		this.codhistorico = codhistorico;
	}

	@Column(name = "DESCRICAO", length = 50)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "PESQUISARAPIDA", length = 20)
	public String getPesquisarapida() {
		return this.pesquisarapida;
	}

	public void setPesquisarapida(String pesquisarapida) {
		this.pesquisarapida = pesquisarapida;
	}

	@Column(name = "CONTADEBITO", precision = 22, scale = 0)
	public BigDecimal getContadebito() {
		return this.contadebito;
	}

	public void setContadebito(BigDecimal contadebito) {
		this.contadebito = contadebito;
	}

	@Column(name = "CONTACREDITO", precision = 22, scale = 0)
	public BigDecimal getContacredito() {
		return this.contacredito;
	}

	public void setContacredito(BigDecimal contacredito) {
		this.contacredito = contacredito;
	}

	@Column(name = "CODPLANILHA", length = 20)
	public String getCodplanilha() {
		return this.codplanilha;
	}

	public void setCodplanilha(String codplanilha) {
		this.codplanilha = codplanilha;
	}

	@Column(name = "TIPOENTIDADEDB", length = 2)
	public String getTipoentidadedb() {
		return this.tipoentidadedb;
	}

	public void setTipoentidadedb(String tipoentidadedb) {
		this.tipoentidadedb = tipoentidadedb;
	}

	@Column(name = "CODENTIDADEDB", precision = 22, scale = 0)
	public BigDecimal getCodentidadedb() {
		return this.codentidadedb;
	}

	public void setCodentidadedb(BigDecimal codentidadedb) {
		this.codentidadedb = codentidadedb;
	}

	@Column(name = "CENTROCUSTODB", precision = 22, scale = 0)
	public BigDecimal getCentrocustodb() {
		return this.centrocustodb;
	}

	public void setCentrocustodb(BigDecimal centrocustodb) {
		this.centrocustodb = centrocustodb;
	}

	@Column(name = "TIPOENTIDADECR", length = 2)
	public String getTipoentidadecr() {
		return this.tipoentidadecr;
	}

	public void setTipoentidadecr(String tipoentidadecr) {
		this.tipoentidadecr = tipoentidadecr;
	}

	@Column(name = "CODENTIDADECR", precision = 22, scale = 0)
	public BigDecimal getCodentidadecr() {
		return this.codentidadecr;
	}

	public void setCodentidadecr(BigDecimal codentidadecr) {
		this.codentidadecr = codentidadecr;
	}

	@Column(name = "CENTROCUSTOCR", precision = 22, scale = 0)
	public BigDecimal getCentrocustocr() {
		return this.centrocustocr;
	}

	public void setCentrocustocr(BigDecimal centrocustocr) {
		this.centrocustocr = centrocustocr;
	}

	@Column(name = "NROPLANILHA", length = 40)
	public String getNroplanilha() {
		return this.nroplanilha;
	}

	public void setNroplanilha(String nroplanilha) {
		this.nroplanilha = nroplanilha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((centrocustocr == null) ? 0 : centrocustocr.hashCode());
		result = prime * result
				+ ((centrocustodb == null) ? 0 : centrocustodb.hashCode());
		result = prime * result
				+ ((codentidadecr == null) ? 0 : codentidadecr.hashCode());
		result = prime * result
				+ ((codentidadedb == null) ? 0 : codentidadedb.hashCode());
		result = prime * result
				+ ((codhistorico == null) ? 0 : codhistorico.hashCode());
		result = prime * result
				+ ((codplanilha == null) ? 0 : codplanilha.hashCode());
		result = prime * result
				+ ((contacredito == null) ? 0 : contacredito.hashCode());
		result = prime * result
				+ ((contadebito == null) ? 0 : contadebito.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((nroempresa == null) ? 0 : nroempresa.hashCode());
		result = prime * result
				+ ((nroplanilha == null) ? 0 : nroplanilha.hashCode());
		result = prime * result
				+ ((pesquisarapida == null) ? 0 : pesquisarapida.hashCode());
		result = prime * result
				+ ((tipoentidadecr == null) ? 0 : tipoentidadecr.hashCode());
		result = prime * result
				+ ((tipoentidadedb == null) ? 0 : tipoentidadedb.hashCode());
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
		CtvHistorico other = (CtvHistorico) obj;
		if (centrocustocr == null) {
			if (other.centrocustocr != null)
				return false;
		} else if (!centrocustocr.equals(other.centrocustocr))
			return false;
		if (centrocustodb == null) {
			if (other.centrocustodb != null)
				return false;
		} else if (!centrocustodb.equals(other.centrocustodb))
			return false;
		if (codentidadecr == null) {
			if (other.codentidadecr != null)
				return false;
		} else if (!codentidadecr.equals(other.codentidadecr))
			return false;
		if (codentidadedb == null) {
			if (other.codentidadedb != null)
				return false;
		} else if (!codentidadedb.equals(other.codentidadedb))
			return false;
		if (codhistorico == null) {
			if (other.codhistorico != null)
				return false;
		} else if (!codhistorico.equals(other.codhistorico))
			return false;
		if (codplanilha == null) {
			if (other.codplanilha != null)
				return false;
		} else if (!codplanilha.equals(other.codplanilha))
			return false;
		if (contacredito == null) {
			if (other.contacredito != null)
				return false;
		} else if (!contacredito.equals(other.contacredito))
			return false;
		if (contadebito == null) {
			if (other.contadebito != null)
				return false;
		} else if (!contadebito.equals(other.contadebito))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nroempresa == null) {
			if (other.nroempresa != null)
				return false;
		} else if (!nroempresa.equals(other.nroempresa))
			return false;
		if (nroplanilha == null) {
			if (other.nroplanilha != null)
				return false;
		} else if (!nroplanilha.equals(other.nroplanilha))
			return false;
		if (pesquisarapida == null) {
			if (other.pesquisarapida != null)
				return false;
		} else if (!pesquisarapida.equals(other.pesquisarapida))
			return false;
		if (tipoentidadecr == null) {
			if (other.tipoentidadecr != null)
				return false;
		} else if (!tipoentidadecr.equals(other.tipoentidadecr))
			return false;
		if (tipoentidadedb == null) {
			if (other.tipoentidadedb != null)
				return false;
		} else if (!tipoentidadedb.equals(other.tipoentidadedb))
			return false;
		return true;
	}
	

	@Override
	public String toString() {
		return "CtvHistorico [nroempresa=" + nroempresa + ", codhistorico="
				+ codhistorico + ", descricao=" + descricao
				+ ", pesquisarapida=" + pesquisarapida + ", contadebito="
				+ contadebito + ", contacredito=" + contacredito
				+ ", codplanilha=" + codplanilha + ", tipoentidadedb="
				+ tipoentidadedb + ", codentidadedb=" + codentidadedb
				+ ", centrocustodb=" + centrocustodb + ", tipoentidadecr="
				+ tipoentidadecr + ", codentidadecr=" + codentidadecr
				+ ", centrocustocr=" + centrocustocr + ", nroplanilha="
				+ nroplanilha + "]";
	}

	

}
