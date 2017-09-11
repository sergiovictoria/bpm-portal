package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

public class ProdutoModel implements Serializable, IClusterable  {
	
	
	
	private static final long serialVersionUID = 1L;

	private String descricao;
	private Short versaoprod;
	private String unidadepadrao;
	private BigDecimal quantidade;
	private BigDecimal valor;
	private BigDecimal vlrDesconto;
	private BigDecimal vlrAcescimo;
	private BigDecimal seqproduto;
	private BigDecimal codproduto;
	private BigDecimal nroempresa;
	private Integer codtributacao;
	private BigDecimal complemento;
	private short nroitem;
	private BigDecimal vlritem;
	private String servico;
	private String veiculo;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Short getVersaoprod() {
		return versaoprod;
	}
	public void setVersaoprod(Short versaoprod) {
		this.versaoprod = versaoprod;
	}
	public String getUnidadepadrao() {
		return unidadepadrao;
	}
	public void setUnidadepadrao(String unidadepadrao) {
		this.unidadepadrao = unidadepadrao;
	}
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getVlrDesconto() {
		return vlrDesconto;
	}
	public void setVlrDesconto(BigDecimal vlrDesconto) {
		this.vlrDesconto = vlrDesconto;
	}
	public BigDecimal getVlrAcescimo() {
		return vlrAcescimo;
	}
	public void setVlrAcescimo(BigDecimal vlrAcescimo) {
		this.vlrAcescimo = vlrAcescimo;
	}
	public BigDecimal getSeqproduto() {
		return seqproduto;
	}
	public void setSeqproduto(BigDecimal seqproduto) {
		this.seqproduto = seqproduto;
	}
	public BigDecimal getCodproduto() {
		return codproduto;
	}
	public void setCodproduto(BigDecimal codproduto) {
		this.codproduto = codproduto;
	}
	public BigDecimal getNroempresa() {
		return nroempresa;
	}
	public void setNroempresa(BigDecimal nroempresa) {
		this.nroempresa = nroempresa;
	}
	public Integer getCodtributacao() {
		return codtributacao;
	}
	public void setCodtributacao(Integer codtributacao) {
		this.codtributacao = codtributacao;
	}
	public BigDecimal getComplemento() {
		return complemento;
	}
	public void setComplemento(BigDecimal complemento) {
		this.complemento = complemento;
	}
	public short getNroitem() {
		return nroitem;
	}
	public void setNroitem(short nroitem) {
		this.nroitem = nroitem;
	}
	public BigDecimal getVlritem() {
		return vlritem;
	}
	public void setVlritem(BigDecimal vlritem) {
		this.vlritem = vlritem;
	}
	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	
	
	
	@Override
	public String toString() {
		return "ProdutoModel [descricao=" + descricao + ", versaoprod="
				+ versaoprod + ", unidadepadrao=" + unidadepadrao
				+ ", quantidade=" + quantidade + ", valor=" + valor
				+ ", vlrDesconto=" + vlrDesconto + ", vlrAcescimo="
				+ vlrAcescimo + ", seqproduto=" + seqproduto + ", codproduto="
				+ codproduto + ", nroempresa=" + nroempresa
				+ ", codtributacao=" + codtributacao + ", complemento="
				+ complemento + ", nroitem=" + nroitem + ", vlritem=" + vlritem
				+ ", servico=" + servico + ", veiculo=" + veiculo + "]";
	}
	



}
