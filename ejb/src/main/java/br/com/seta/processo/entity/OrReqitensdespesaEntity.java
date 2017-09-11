package br.com.seta.processo.entity;


import java.math.BigDecimal;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "OR_REQITENSDESPESA")
public class OrReqitensdespesaEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private OrReqitensdespesaId id;
	private short nroempresa;
	private short nroempresaorc;
	private String codproduto;
	private short versaoprod;
	private String descricao;
	private int cfop;
	private String unidade;
	private BigDecimal quantidade;
	private BigDecimal vlritem;
	private BigDecimal vlrdesconto;
	private BigDecimal vlracrescimos;
	private String servico;
	private BigDecimal complemento;
	private String unidadepadrao;
	private String veiculo;
	private BigDecimal vlrtotal;
	private BigDecimal vlrliqdesp;
	private Integer codtributacao;

	public OrReqitensdespesaEntity() {
	}

	public OrReqitensdespesaEntity(OrReqitensdespesaId id, short nroempresa,
			short nroempresaorc, String codproduto, short versaoprod, int cfop,
			BigDecimal quantidade, BigDecimal vlritem, BigDecimal vlrtotal) {
		this.id = id;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.codproduto = codproduto;
		this.versaoprod = versaoprod;
		this.cfop = cfop;
		this.quantidade = quantidade;
		this.vlritem = vlritem;
		this.vlrtotal = vlrtotal;
	}

	public OrReqitensdespesaEntity(OrReqitensdespesaId id, short nroempresa,
			short nroempresaorc, String codproduto, short versaoprod,
			String descricao, int cfop, String unidade, BigDecimal quantidade,
			BigDecimal vlritem, BigDecimal vlrdesconto,
			BigDecimal vlracrescimos, String servico, BigDecimal complemento,
			String unidadepadrao, String veiculo, BigDecimal vlrtotal,
			BigDecimal vlrliqdesp, Integer codtributacao) {
		this.id = id;
		this.nroempresa = nroempresa;
		this.nroempresaorc = nroempresaorc;
		this.codproduto = codproduto;
		this.versaoprod = versaoprod;
		this.descricao = descricao;
		this.cfop = cfop;
		this.unidade = unidade;
		this.quantidade = quantidade;
		this.vlritem = vlritem;
		this.vlrdesconto = vlrdesconto;
		this.vlracrescimos = vlracrescimos;
		this.servico = servico;
		this.complemento = complemento;
		this.unidadepadrao = unidadepadrao;
		this.veiculo = veiculo;
		this.vlrtotal = vlrtotal;
		this.vlrliqdesp = vlrliqdesp;
		this.codtributacao = codtributacao;
	}

	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "seqrequisicao", column = @Column(name = "SEQREQUISICAO", nullable = false, precision = 15, scale = 0)),
			@AttributeOverride(name = "nroitem", column = @Column(name = "NROITEM", nullable = false, precision = 3, scale = 0)) })
	public OrReqitensdespesaId getId() {
		return this.id;
	}

	public void setId(OrReqitensdespesaId id) {
		this.id = id;
	}

	@Column(name = "NROEMPRESA", nullable = false, precision = 3, scale = 0)
	public short getNroempresa() {
		return this.nroempresa;
	}

	public void setNroempresa(short nroempresa) {
		this.nroempresa = nroempresa;
	}

	@Column(name = "NROEMPRESAORC", nullable = false, precision = 3, scale = 0)
	public short getNroempresaorc() {
		return this.nroempresaorc;
	}

	public void setNroempresaorc(short nroempresaorc) {
		this.nroempresaorc = nroempresaorc;
	}

	@Column(name = "CODPRODUTO", nullable = false, length = 14)
	public String getCodproduto() {
		return this.codproduto;
	}

	public void setCodproduto(String codproduto) {
		this.codproduto = codproduto;
	}

	@Column(name = "VERSAOPROD", nullable = false, precision = 3, scale = 0)
	public short getVersaoprod() {
		return this.versaoprod;
	}

	public void setVersaoprod(short versaoprod) {
		this.versaoprod = versaoprod;
	}

	@Column(name = "DESCRICAO", length = 75)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "CFOP", nullable = false, precision = 5, scale = 0)
	public int getCfop() {
		return this.cfop;
	}

	public void setCfop(int cfop) {
		this.cfop = cfop;
	}

	@Column(name = "UNIDADE", length = 3)
	public String getUnidade() {
		return this.unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	@Column(name = "QUANTIDADE", nullable = false, precision = 13, scale = 3)
	public BigDecimal getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@Column(name = "VLRITEM", nullable = false, precision = 15)
	public BigDecimal getVlritem() {
		return this.vlritem;
	}

	public void setVlritem(BigDecimal vlritem) {
		this.vlritem = vlritem;
	}

	@Column(name = "VLRDESCONTO", precision = 13)
	public BigDecimal getVlrdesconto() {
		return this.vlrdesconto;
	}

	public void setVlrdesconto(BigDecimal vlrdesconto) {
		this.vlrdesconto = vlrdesconto;
	}

	@Column(name = "VLRACRESCIMOS", precision = 15)
	public BigDecimal getVlracrescimos() {
		return this.vlracrescimos;
	}

	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}

	@Column(name = "SERVICO", length = 1)
	public String getServico() {
		return this.servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	@Column(name = "COMPLEMENTO", precision = 2, scale = 0)
	public BigDecimal getComplemento() {
		return this.complemento;
	}

	public void setComplemento(BigDecimal complemento) {
		this.complemento = complemento;
	}

	@Column(name = "UNIDADEPADRAO", length = 3)
	public String getUnidadepadrao() {
		return this.unidadepadrao;
	}

	public void setUnidadepadrao(String unidadepadrao) {
		this.unidadepadrao = unidadepadrao;
	}

	@Column(name = "VEICULO", length = 1)
	public String getVeiculo() {
		return this.veiculo;
	}

	public void setVeiculo(String veiculo) {
		this.veiculo = veiculo;
	}

	@Column(name = "VLRTOTAL", nullable = false, precision = 13)
	public BigDecimal getVlrtotal() {
		return this.vlrtotal;
	}

	public void setVlrtotal(BigDecimal vlrtotal) {
		this.vlrtotal = vlrtotal;
	}

	@Column(name = "VLRLIQDESP", precision = 15)
	public BigDecimal getVlrliqdesp() {
		return this.vlrliqdesp;
	}

	public void setVlrliqdesp(BigDecimal vlrliqdesp) {
		this.vlrliqdesp = vlrliqdesp;
	}

	@Column(name = "CODTRIBUTACAO", precision = 5, scale = 0)
	public Integer getCodtributacao() {
		return this.codtributacao;
	}

	public void setCodtributacao(Integer codtributacao) {
		this.codtributacao = codtributacao;
	}

}
