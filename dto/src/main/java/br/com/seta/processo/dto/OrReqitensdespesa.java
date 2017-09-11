package br.com.seta.processo.dto;


import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.mongodb.morphia.annotations.Entity;



@Entity("OrReqitensdespesa")
public class OrReqitensdespesa implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private short nroempresa;
	private short nroempresaorc;
	private String codproduto;
	private short versaoprod;
	private String descricao;
	@Range(min=1000, max=3999, message="O CFOP deve ser um número entre 1000 e 3999")
	private int cfop;
	private String unidade;
	@NotNull(message="A quantidade dos itens é obrigatória")
	private BigDecimal quantidade = BigDecimal.ZERO;
	@NotNull(message="O valor unitário do item é obrigatório")
	private BigDecimal vlritem = BigDecimal.ZERO;
	private BigDecimal vlrdesconto = BigDecimal.ZERO;
	private BigDecimal vlracrescimos = BigDecimal.ZERO;
	private String servico;
	private BigDecimal complemento = BigDecimal.ZERO;
	private String unidadepadrao;
	private String veiculo;
	private BigDecimal vlrtotal = BigDecimal.ZERO;
	private BigDecimal vlrliqdesp = BigDecimal.ZERO;
	private long seqrequisicao;
	private short nroitem;
	private Integer codtributacao;
	private String descricaoCfop;
	private boolean naoCadastrado = false;

	public short getNroempresa() {
		return nroempresa;
	}
	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}

	public short getNroempresaorc() {
		return nroempresaorc;
	}
	public void setNroempresaorc(short nroempresaorc) {
		this.nroempresaorc = nroempresaorc;
	}

	public String getCodproduto() {
		return codproduto;
	}
	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	public short getVersaoprod() {
		return versaoprod;
	}
	public void setVersaoprod(short versaoprod) {
		this.versaoprod = versaoprod;
	}
	

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	public int getCfop() {
		return cfop;
	}
	public void setCfop(int cfop) {
		this.cfop = cfop;
	}
	

	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	
	
	public BigDecimal getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}
	
	

	public BigDecimal getVlritem() {
		return vlritem;
	}
	public void setVlritem(BigDecimal vlritem) {
		this.vlritem = vlritem;
	}
	

	public BigDecimal getVlrdesconto() {
		return vlrdesconto;
	}
	public void setVlrdesconto(BigDecimal vlrdesconto) {
		this.vlrdesconto = vlrdesconto;
	}
	

	public BigDecimal getVlracrescimos() {
		return vlracrescimos;
	}
	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}
	

	public String getServico() {
		return servico;
	}
	public void setServico(String servico) {
		this.servico = servico;
	}
	

	public BigDecimal getComplemento() {
		return complemento;
	}
	public void setComplemento(BigDecimal complemento) {
		this.complemento = complemento;
	}
	

	public String getUnidadepadrao() {
		return unidadepadrao;
	}
	public void setUnidadepadrao(String unidadepadrao) {
		this.unidadepadrao = unidadepadrao;
	}
	

	public String getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}
	

	public BigDecimal getVlrtotal() {
		return vlrtotal;
	}
	public void setVlrtotal(BigDecimal vlrtotal) {
		this.vlrtotal = vlrtotal;
	}
	

	public BigDecimal getVlrliqdesp() {
		return vlrliqdesp;
	}
	
	public void setVlrliqdesp(BigDecimal vlrliqdesp) {
		this.vlrliqdesp = vlrliqdesp;
	}
	

	public long getSeqrequisicao() {
		return seqrequisicao;
	}
	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}
	

	public short getNroitem() {
		return nroitem;
	}
	public void setNroitem(short nroitem) {
		this.nroitem = nroitem;
	}

	public Integer getCodtributacao() {
		return codtributacao;
	}
	
	public void setCodtributacao(Integer codtributacao) {
		this.codtributacao = codtributacao;
	}
	
	
	
	
	public String getDescricaoCfop() {
		return descricaoCfop;
	}
	public void setDescricaoCfop(String descricaoCfop) {
		this.descricaoCfop = descricaoCfop;
	}
	public boolean isNaoCadastrado() {
		return naoCadastrado;
	}
	public void setNaoCadastrado(boolean naoCadastrado) {
		this.naoCadastrado = naoCadastrado;
	}
	
	@Override
	public String toString() {
		return "OrReqitensdespesa [nroempresa=" + nroempresa
				+ ", nroempresaorc=" + nroempresaorc + ", codproduto="
				+ codproduto + ", versaoprod=" + versaoprod + ", descricao="
				+ descricao + ", cfop=" + cfop + ", unidade=" + unidade
				+ ", quantidade=" + quantidade + ", vlritem=" + vlritem
				+ ", vlrdesconto=" + vlrdesconto + ", vlracrescimos="
				+ vlracrescimos + ", servico=" + servico + ", complemento="
				+ complemento + ", unidadepadrao=" + unidadepadrao
				+ ", veiculo=" + veiculo + ", vlrtotal=" + vlrtotal
				+ ", vlrliqdesp=" + vlrliqdesp + ", seqrequisicao="
				+ seqrequisicao + ", nroitem=" + nroitem + ", codtributacao="
				+ codtributacao + ", descricaoCfop=" + descricaoCfop
				+ ", naoCadastrado=" + naoCadastrado + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((codproduto == null) ? 0 : codproduto.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
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
		OrReqitensdespesa other = (OrReqitensdespesa) obj;
		if (codproduto == null) {
			if (other.codproduto != null)
				return false;
		} else if (!codproduto.equals(other.codproduto))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}	

}
