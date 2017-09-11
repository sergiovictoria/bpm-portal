package br.com.seta.processo.dto;

import java.math.BigDecimal;

public class MapFamfornec implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqfamilia;
	private BigDecimal seqfornecedor;
	private MapFamilia mapFamilia;
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

	public MapFamfornec() {
	}

	public BigDecimal getSeqfamilia() {
		return seqfamilia;
	}

	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}

	public BigDecimal getSeqfornecedor() {
		return seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	public MapFamilia getMapFamilia() {
		return mapFamilia;
	}

	public void setMapFamilia(MapFamilia mapFamilia) {
		this.mapFamilia = mapFamilia;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getIndreplicacao() {
		return indreplicacao;
	}

	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}

	public String getIndgeroureplicacao() {
		return indgeroureplicacao;
	}

	public void setIndgeroureplicacao(String indgeroureplicacao) {
		this.indgeroureplicacao = indgeroureplicacao;
	}

	public Short getNrosegfornec() {
		return nrosegfornec;
	}

	public void setNrosegfornec(Short nrosegfornec) {
		this.nrosegfornec = nrosegfornec;
	}

	public String getIndindenizavaria() {
		return indindenizavaria;
	}

	public void setIndindenizavaria(String indindenizavaria) {
		this.indindenizavaria = indindenizavaria;
	}

	public String getTipfornecedorfam() {
		return tipfornecedorfam;
	}

	public void setTipfornecedorfam(String tipfornecedorfam) {
		this.tipfornecedorfam = tipfornecedorfam;
	}

	public BigDecimal getPadraoembcomprafornec() {
		return padraoembcomprafornec;
	}

	public void setPadraoembcomprafornec(BigDecimal padraoembcomprafornec) {
		this.padraoembcomprafornec = padraoembcomprafornec;
	}

	public String getCalcdescsuframapiscofins() {
		return calcdescsuframapiscofins;
	}

	public void setCalcdescsuframapiscofins(String calcdescsuframapiscofins) {
		this.calcdescsuframapiscofins = calcdescsuframapiscofins;
	}

	public String getIndcontrolaflex() {
		return indcontrolaflex;
	}

	public void setIndcontrolaflex(String indcontrolaflex) {
		this.indcontrolaflex = indcontrolaflex;
	}

	public String getIndmedidametadistrib() {
		return indmedidametadistrib;
	}

	public void setIndmedidametadistrib(String indmedidametadistrib) {
		this.indmedidametadistrib = indmedidametadistrib;
	}

	public long getNrobaseexportacao() {
		return nrobaseexportacao;
	}

	public void setNrobaseexportacao(long nrobaseexportacao) {
		this.nrobaseexportacao = nrobaseexportacao;
	}

	public BigDecimal getEmbpadraoimpxml() {
		return embpadraoimpxml;
	}

	public void setEmbpadraoimpxml(BigDecimal embpadraoimpxml) {
		this.embpadraoimpxml = embpadraoimpxml;
	}

	public String getUsuarioalteracao() {
		return usuarioalteracao;
	}

	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}

	public String getIndbuscanfautodevfornec() {
		return indbuscanfautodevfornec;
	}

	public void setIndbuscanfautodevfornec(String indbuscanfautodevfornec) {
		this.indbuscanfautodevfornec = indbuscanfautodevfornec;
	}

	public BigDecimal getFatorconvembxml() {
		return fatorconvembxml;
	}

	public void setFatorconvembxml(BigDecimal fatorconvembxml) {
		this.fatorconvembxml = fatorconvembxml;
	}

	public String getIndcontrolaprinc() {
		return indcontrolaprinc;
	}

	public void setIndcontrolaprinc(String indcontrolaprinc) {
		this.indcontrolaprinc = indcontrolaprinc;
	}

}
