package br.com.seta.processo.dto;

import java.math.BigDecimal;

public class OrvProdutoTributo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal seqproduto;
	private String codproduto;
	private String descricao;
	private BigDecimal nroempresa;
	private String indusanfdespesa;
	private BigDecimal aliqipi;
	private BigDecimal aliqicms;
	private BigDecimal percredbaseicms;
	private String vlrbaseicmsst;
	private String codncm;
	private String codstf;
	private String unidadepadrao;
	private Character isentopiscofins;
	private Character indisentodebpiscofins;
	private BigDecimal versaoprod;
	private String servico;
	private Character veiculo;
	private BigDecimal seqfornecedor;
	private String codobservacao;
	private BigDecimal codnatrec;
	private BigDecimal aliqpadraoicms;
	private BigDecimal codservico;
	private BigDecimal subitemcodservico;
	private BigDecimal nrotributacao;
	private String situacaonfipi;
	private BigDecimal nroitem;
	
	private boolean selected = Boolean.FALSE;

	public BigDecimal getSeqproduto() {
		return seqproduto;
	}

	public void setSeqproduto(BigDecimal seqproduto) {
		this.seqproduto = seqproduto;
	}

	public String getCodproduto() {
		return codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getNroempresa() {
		return nroempresa;
	}

	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}

	public String getIndusanfdespesa() {
		return indusanfdespesa;
	}

	public void setIndusanfdespesa(String indusanfdespesa) {
		this.indusanfdespesa = indusanfdespesa;
	}

	public BigDecimal getAliqipi() {
		return aliqipi;
	}

	public void setAliqipi(BigDecimal aliqipi) {
		this.aliqipi = aliqipi;
	}

	public BigDecimal getAliqicms() {
		return aliqicms;
	}

	public void setAliqicms(BigDecimal aliqicms) {
		this.aliqicms = aliqicms;
	}

	public BigDecimal getPercredbaseicms() {
		return percredbaseicms;
	}

	public void setPercredbaseicms(BigDecimal percredbaseicms) {
		this.percredbaseicms = percredbaseicms;
	}

	public String getVlrbaseicmsst() {
		return vlrbaseicmsst;
	}

	public void setVlrbaseicmsst(String vlrbaseicmsst) {
		this.vlrbaseicmsst = vlrbaseicmsst;
	}

	public String getCodncm() {
		return codncm;
	}

	public void setCodncm(String codncm) {
		this.codncm = codncm;
	}

	public String getCodstf() {
		return codstf;
	}

	public void setCodstf(String codstf) {
		this.codstf = codstf;
	}

	public String getUnidadepadrao() {
		return unidadepadrao;
	}

	public void setUnidadepadrao(String unidadepadrao) {
		this.unidadepadrao = unidadepadrao;
	}

	public Character getIsentopiscofins() {
		return isentopiscofins;
	}

	public void setIsentopiscofins(Character isentopiscofins) {
		this.isentopiscofins = isentopiscofins;
	}

	public Character getIndisentodebpiscofins() {
		return indisentodebpiscofins;
	}

	public void setIndisentodebpiscofins(Character indisentodebpiscofins) {
		this.indisentodebpiscofins = indisentodebpiscofins;
	}

	public BigDecimal getVersaoprod() {
		return versaoprod;
	}

	public void setVersaoprod(BigDecimal versaoprod) {
		this.versaoprod = versaoprod;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Character getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Character veiculo) {
		this.veiculo = veiculo;
	}

	public BigDecimal getSeqfornecedor() {
		return seqfornecedor;
	}

	public void setSeqfornecedor(BigDecimal seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}

	public String getCodobservacao() {
		return codobservacao;
	}

	public void setCodobservacao(String codobservacao) {
		this.codobservacao = codobservacao;
	}

	public BigDecimal getCodnatrec() {
		return codnatrec;
	}

	public void setCodnatrec(BigDecimal codnatrec) {
		this.codnatrec = codnatrec;
	}

	public BigDecimal getAliqpadraoicms() {
		return aliqpadraoicms;
	}

	public void setAliqpadraoicms(BigDecimal aliqpadraoicms) {
		this.aliqpadraoicms = aliqpadraoicms;
	}

	public BigDecimal getCodservico() {
		return codservico;
	}

	public void setCodservico(BigDecimal codservico) {
		this.codservico = codservico;
	}

	public BigDecimal getSubitemcodservico() {
		return subitemcodservico;
	}

	public void setSubitemcodservico(BigDecimal subitemcodservico) {
		this.subitemcodservico = subitemcodservico;
	}

	public BigDecimal getNrotributacao() {
		return nrotributacao;
	}

	public void setNrotributacao(BigDecimal nrotributacao) {
		this.nrotributacao = nrotributacao;
	}

	public String getSituacaonfipi() {
		return situacaonfipi;
	}

	public void setSituacaonfipi(String situacaonfipi) {
		this.situacaonfipi = situacaonfipi;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getNroitem() {
		return nroitem;
	}

	public void setNroitem(BigDecimal nroitem) {
		this.nroitem = nroitem;
	}

	@Override
	public String toString() {
		return "OrvProdutoTributo [seqproduto=" + seqproduto + ", codproduto="
				+ codproduto + ", descricao=" + descricao + ", nroempresa="
				+ nroempresa + ", indusanfdespesa=" + indusanfdespesa
				+ ", aliqipi=" + aliqipi + ", aliqicms=" + aliqicms
				+ ", percredbaseicms=" + percredbaseicms + ", vlrbaseicmsst="
				+ vlrbaseicmsst + ", codncm=" + codncm + ", codstf=" + codstf
				+ ", unidadepadrao=" + unidadepadrao + ", isentopiscofins="
				+ isentopiscofins + ", indisentodebpiscofins="
				+ indisentodebpiscofins + ", versaoprod=" + versaoprod
				+ ", servico=" + servico + ", veiculo=" + veiculo
				+ ", seqfornecedor=" + seqfornecedor + ", codobservacao="
				+ codobservacao + ", codnatrec=" + codnatrec
				+ ", aliqpadraoicms=" + aliqpadraoicms + ", codservico="
				+ codservico + ", subitemcodservico=" + subitemcodservico
				+ ", nrotributacao=" + nrotributacao + ", situacaonfipi="
				+ situacaonfipi + "]";
	}
}
