package br.com.seta.processo.entity;


import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "FI_FORNCONTA")

public class FiForncontaEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FiForncontaId id;
	private String propterceiro;
	private Short banco;
	private Integer agencia;
	private String digagencia;
	private String nroconta;
	private String digconta;
	private String tipoconta;
	private Integer tipoinscricao;
	private Long cnpjcpf;
	private Integer digcnpjcpf;
	private String nome;
	private String endereco;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private Integer cep;
	private Short complcep;
	private String estado;
	private String contapadrao;
	private String situacao;
	private String observacao;
	private String usualteracao;
	private Date dtaalteracao;
	private String contapessoajuridica;
	private String nroconvenio;
	private String codmodelo;

	public FiForncontaEntity() {
	}

	public FiForncontaEntity(FiForncontaId id) {
		this.id = id;
	}

	public FiForncontaEntity(FiForncontaId id, String propterceiro, Short banco, Integer agencia, String digagencia, String nroconta, String digconta, String tipoconta,
			Integer tipoinscricao, Long cnpjcpf, Integer digcnpjcpf, String nome, String endereco, Integer numero, String complemento, String bairro, String cidade,
			Integer cep, Short complcep, String estado, String contapadrao, String situacao, String observacao, String usualteracao, Date dtaalteracao,
			String contapessoajuridica, String nroconvenio, String codmodelo) {
		this.id = id;
		this.propterceiro = propterceiro;
		this.banco = banco;
		this.agencia = agencia;
		this.digagencia = digagencia;
		this.nroconta = nroconta;
		this.digconta = digconta;
		this.tipoconta = tipoconta;
		this.tipoinscricao = tipoinscricao;
		this.cnpjcpf = cnpjcpf;
		this.digcnpjcpf = digcnpjcpf;
		this.nome = nome;
		this.endereco = endereco;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.cep = cep;
		this.complcep = complcep;
		this.estado = estado;
		this.contapadrao = contapadrao;
		this.situacao = situacao;
		this.observacao = observacao;
		this.usualteracao = usualteracao;
		this.dtaalteracao = dtaalteracao;
		this.contapessoajuridica = contapessoajuridica;
		this.nroconvenio = nroconvenio;
		this.codmodelo = codmodelo;
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "seqpessoa", column = @Column(name = "SEQPESSOA", nullable = false, precision = 8, scale = 0)),
			@AttributeOverride(name = "seqconta", column = @Column(name = "SEQCONTA", nullable = false, precision = 3, scale = 0)) })
	public FiForncontaId getId() {
		return this.id;
	}

	public void setId(FiForncontaId id) {
		this.id = id;
	}

	@Column(name = "PROPTERCEIRO", length = 1)
	public String getPropterceiro() {
		return this.propterceiro;
	}

	public void setPropterceiro(String propterceiro) {
		this.propterceiro = propterceiro;
	}

	@Column(name = "BANCO", precision = 3, scale = 0)
	public Short getBanco() {
		return this.banco;
	}

	public void setBanco(Short banco) {
		this.banco = banco;
	}

	@Column(name = "AGENCIA", precision = 5, scale = 0)
	public Integer getAgencia() {
		return this.agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	@Column(name = "DIGAGENCIA", length = 2)
	public String getDigagencia() {
		return this.digagencia;
	}

	public void setDigagencia(String digagencia) {
		this.digagencia = digagencia;
	}

	@Column(name = "NROCONTA", length = 15)
	public String getNroconta() {
		return this.nroconta;
	}

	public void setNroconta(String nroconta) {
		this.nroconta = nroconta;
	}

	@Column(name = "DIGCONTA", length = 2)
	public String getDigconta() {
		return this.digconta;
	}

	public void setDigconta(String digconta) {
		this.digconta = digconta;
	}

	@Column(name = "TIPOCONTA", length = 1)
	public String getTipoconta() {
		return this.tipoconta;
	}

	public void setTipoconta(String tipoconta) {
		this.tipoconta = tipoconta;
	}

	@Column(name = "TIPOINSCRICAO", precision = 1, scale = 0)
	public Integer getTipoinscricao() {
		return this.tipoinscricao;
	}

	public void setTipoinscricao(Integer tipoinscricao) {
		this.tipoinscricao = tipoinscricao;
	}

	@Column(name = "CNPJCPF", precision = 12, scale = 0)
	public Long getCnpjcpf() {
		return this.cnpjcpf;
	}

	public void setCnpjcpf(Long cnpjcpf) {
		this.cnpjcpf = cnpjcpf;
	}

	@Column(name = "DIGCNPJCPF", precision = 2, scale = 0)
	public Integer getDigcnpjcpf() {
		return this.digcnpjcpf;
	}

	public void setDigcnpjcpf(Integer digcnpjcpf) {
		this.digcnpjcpf = digcnpjcpf;
	}

	@Column(name = "NOME", length = 30)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "ENDERECO", length = 30)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Column(name = "NUMERO", precision = 5, scale = 0)
	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Column(name = "COMPLEMENTO", length = 15)
	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "BAIRRO", length = 15)
	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "CIDADE", length = 20)
	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "CEP", precision = 5, scale = 0)
	public Integer getCep() {
		return this.cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	@Column(name = "COMPLCEP", precision = 3, scale = 0)
	public Short getComplcep() {
		return this.complcep;
	}

	public void setComplcep(Short complcep) {
		this.complcep = complcep;
	}

	@Column(name = "ESTADO", length = 2)
	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "CONTAPADRAO", length = 1)
	public String getContapadrao() {
		return this.contapadrao;
	}

	public void setContapadrao(String contapadrao) {
		this.contapadrao = contapadrao;
	}

	@Column(name = "SITUACAO", length = 1)
	public String getSituacao() {
		return this.situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Column(name = "OBSERVACAO", length = 250)
	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(name = "USUALTERACAO", length = 12)
	public String getUsualteracao() {
		return this.usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DTAALTERACAO", length = 7)
	public Date getDtaalteracao() {
		return this.dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	@Column(name = "CONTAPESSOAJURIDICA", length = 1)
	public String getContapessoajuridica() {
		return this.contapessoajuridica;
	}

	public void setContapessoajuridica(String contapessoajuridica) {
		this.contapessoajuridica = contapessoajuridica;
	}

	@Column(name = "NROCONVENIO", length = 15)
	public String getNroconvenio() {
		return this.nroconvenio;
	}

	public void setNroconvenio(String nroconvenio) {
		this.nroconvenio = nroconvenio;
	}

	@Column(name = "CODMODELO", length = 20)
	public String getCodmodelo() {
		return this.codmodelo;
	}

	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}

}
