package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import br.com.seta.processo.validacoes.ItensRequisicao;
import br.com.seta.processo.validacoes.ProdutosCadastrados;
import br.com.seta.processo.validacoes.SomaPercentualDeRateios;
import br.com.seta.processo.validacoes.SomaVencimentos;
import br.com.seta.processo.validacoesCheck.FornecedorItensCadastradosCheck;

@ItensRequisicao
@ProdutosCadastrados(message="Todos os produtos contidos na requisição devem estar validados e cadastrados", groups={FornecedorItensCadastradosCheck.class})
@SomaVencimentos(message="O valor total das parcelas deve ser igual ao valor líquido da requisição acrescido do valor do frete")
@SomaPercentualDeRateios(message="A soma dos percentuais dos rateios deve ser igual a 100%")
@Entity("OrRequisicao")
public class OrRequisicao implements java.io.Serializable , Comparable<OrRequisicao> {

	private static final long serialVersionUID = 1L;
	private long seqrequisicao;
	private long idBonita;
	@Min(value=1, message="A empresa é obrigatório")
	private short nroempresa;
	private String nomeEmpresa;
	private short nroempresaorc;
	private String codmodelo;
	private short cgo;
	private String especienf;
	@NotNull(message="O fornecedor deve ser validado e cadastrado", groups={FornecedorItensCadastradosCheck.class})
	@Min(message="O fornecedor deve ser validado e cadastrado", groups={FornecedorItensCadastradosCheck.class}, value = 1)
	private Integer seqpessoa;
	private BigDecimal versaopessoa;
	private long nrorequisicao;
	private Date dtacompra;
	private Date dtainclusao;
	private Date dataSolicitacaoIntencao;
	private String naturezaDespesa;
	@NotNull(message="A natureza é obrigatória")
	@Min( value=1, message="A natureza é obrigatória")
	private Short codhistorico;
	private String observacao;
	private String opcaoFrete = "NÃO";
	private String opcaoTransportadora = "NÃO";
	
	private BigDecimal valor = BigDecimal.ZERO;
	@NotNull(message="O desconto é obrigatório")
	private BigDecimal vlrdescontos = BigDecimal.ZERO;
	private BigDecimal vlroutrasopdesc = BigDecimal.ZERO;
	@NotNull(message="O acréscimo é obrigatório")
	private BigDecimal vlracrescimos = BigDecimal.ZERO;
	private BigDecimal vlrliqreq = BigDecimal.ZERO;
	private BigDecimal vlrFrete = BigDecimal.ZERO;
	
	private String tipoDespesa;
	private String nomeSolicitanteBPM;
	@NotBlank(message="O prazo de pagamento é obrigatório")
	private String prazopagto;
	private String observacaofin;
	private String status;
	private String usualteracao;	
	
	@NotBlank(message="O fornecedor é obrigatório")
	private String nomeFornecedor;
	@NotBlank(message="O local de entrega é obrigatório")
	private String localDeEntrega;
	private String grupoRecebimento;
	private Date dtaalteracao;
	private String tipopgto;
	private Short qtdparcela;
	private Short diasentrevenc;
	private Date dtavencinicial;
	private String diafixo;
	private String usuautorizacao;
	private Date dtaautorizacao;
	private Short seqcomprador;
	private String autorizado;
	private String autorizadonivel2;
	private String usuautorizacao2;
	private Date dtaautorizacao2;
	private BigDecimal vlrautorizado;
	private Integer seqtransportador;
	private Transportadora transportadora;
	private String exigeitensnota;
	private Short nroempresanatdesp;
	private Boolean mcsseqcontrato;
	@NotBlank(message="A forma de pagamento é obrigatória")
	private String formaPagamento;
	private String statusBpmn;
	private byte[] anexoItens;		
	private boolean selecionado;
	private Long numeroIntencaoSolicitacaoCompra;

	@Embedded
	@NotEmpty(message="É necessário a geração dos vencimentos")
	private Set<OrRequisicaovencto> orRequisicaovenctos = new HashSet<OrRequisicaovencto>();

	@Embedded
	@NotEmpty(message="É necessário adicionar itens à requisição")
	@Valid
	private Set<OrReqitensdespesa> orReqitensdespesas = new HashSet<OrReqitensdespesa>();

	@Embedded
	@Valid
	@NotEmpty(message="É necessário fazer o rateios dos centros de custo")
	private Set<OrReqplanilhalancto> orReqplanilhalanctos = new HashSet<OrReqplanilhalancto>();


	public OrRequisicao() {
		this.dtainclusao = new java.util.Date();
		this.dtacompra   = new java.util.Date();
	}

	public long getSeqrequisicao() {
		return seqrequisicao;
	}

	public void setSeqrequisicao(long seqrequisicao) {
		this.seqrequisicao = seqrequisicao;
	}

	public Date getDataSolicitacaoIntencao() {
		return dataSolicitacaoIntencao;
	}

	public void setDataSolicitacaoIntencao(Date dataSolicitacaoIntencao) {
		this.dataSolicitacaoIntencao = dataSolicitacaoIntencao;
	}

	public long getIdBonita() {
		return idBonita;
	}

	public void setIdBonita(long idBonita) {
		this.idBonita = idBonita;
	}

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

	public String getCodmodelo() {
		return codmodelo;
	}

	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}

	public short getCgo() {
		return cgo;
	}

	public void setCgo(short cgo) {
		this.cgo = cgo;
	}

	public String getEspecienf() {
		return especienf;
	}

	public void setEspecienf(String especienf) {
		this.especienf = especienf;
	}

	public Integer getSeqpessoa() {
		return seqpessoa;
	}

	public void setSeqpessoa(Integer seqpessoa) {
		this.seqpessoa = seqpessoa;
	}

	public BigDecimal getVersaopessoa() {
		return versaopessoa;
	}

	public void setVersaopessoa(BigDecimal versaopessoa) {
		this.versaopessoa = versaopessoa;
	}

	public long getNrorequisicao() {
		return nrorequisicao;
	}

	public void setNrorequisicao(long nrorequisicao) {
		this.nrorequisicao = nrorequisicao;
	}

	public Date getDtacompra() {
		return dtacompra;
	}

	public void setDtacompra(Date dtacompra) {
		this.dtacompra = dtacompra;
	}

	public Date getDtainclusao() {
		return dtainclusao;
	}

	public void setDtainclusao(Date dtainclusao) {
		this.dtainclusao = dtainclusao;
	}

	public Short getCodhistorico() {
		return codhistorico;
	}

	public void setCodhistorico(Short codhistorico) {
		this.codhistorico = codhistorico;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getVlrdescontos() {
		return vlrdescontos;
	}

	public void setVlrdescontos(BigDecimal vlrdescontos) {
		this.vlrdescontos = vlrdescontos;
	}

	public BigDecimal getVlroutrasopdesc() {
		return vlroutrasopdesc;
	}

	public void setVlroutrasopdesc(BigDecimal vlroutrasopdesc) {
		this.vlroutrasopdesc = vlroutrasopdesc;
	}

	public BigDecimal getVlracrescimos() {
		return vlracrescimos;
	}

	public void setVlracrescimos(BigDecimal vlracrescimos) {
		this.vlracrescimos = vlracrescimos;
	}

	public BigDecimal getVlrliqreq() {
		BigDecimal vlrLiquido = this.valor.add(vlracrescimos).subtract(vlrdescontos);
		this.vlrliqreq = vlrLiquido;
		return vlrLiquido;
	}
	
	public void setVlrliqreq(BigDecimal vlrliqreq) {
		this.vlrliqreq = vlrliqreq;
	}

	public String getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	public String getNomeSolicitanteBPM() {
		return nomeSolicitanteBPM;
	}

	public void setNomeSolicitanteBPM(String nomeSolicitanteBPM) {
		this.nomeSolicitanteBPM = nomeSolicitanteBPM;
	}

	public String getPrazopagto() {
		return prazopagto;
	}

	public void setPrazopagto(String prazopagto) {
		this.prazopagto = prazopagto;
	}

	public String getObservacaofin() {
		return observacaofin;
	}

	public void setObservacaofin(String observacaofin) {
		this.observacaofin = observacaofin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUsualteracao() {
		return usualteracao;
	}

	public void setUsualteracao(String usualteracao) {
		this.usualteracao = usualteracao;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public String getLocalDeEntrega() {
		return localDeEntrega;
	}

	public void setLocalDeEntrega(String localDeEntrega) {
		this.localDeEntrega = localDeEntrega;
	}

	public Date getDtaalteracao() {
		return dtaalteracao;
	}

	public void setDtaalteracao(Date dtaalteracao) {
		this.dtaalteracao = dtaalteracao;
	}

	public String getTipopgto() {
		return tipopgto;
	}

	public void setTipopgto(String tipopgto) {
		this.tipopgto = tipopgto;
	}

	public Short getQtdparcela() {
		return qtdparcela;
	}

	public void setQtdparcela(Short qtdparcela) {
		this.qtdparcela = qtdparcela;
	}

	public Short getDiasentrevenc() {
		return diasentrevenc;
	}

	public void setDiasentrevenc(Short diasentrevenc) {
		this.diasentrevenc = diasentrevenc;
	}

	public Date getDtavencinicial() {
		return dtavencinicial;
	}

	public void setDtavencinicial(Date dtavencinicial) {
		this.dtavencinicial = dtavencinicial;
	}

	public String getDiafixo() {
		return diafixo;
	}

	public void setDiafixo(String diafixo) {
		this.diafixo = diafixo;
	}

	public String getUsuautorizacao() {
		return usuautorizacao;
	}

	public void setUsuautorizacao(String usuautorizacao) {
		this.usuautorizacao = usuautorizacao;
	}

	public Date getDtaautorizacao() {
		return dtaautorizacao;
	}

	public void setDtaautorizacao(Date dtaautorizacao) {
		this.dtaautorizacao = dtaautorizacao;
	}

	public Short getSeqcomprador() {
		return seqcomprador;
	}

	public void setSeqcomprador(Short seqcomprador) {
		this.seqcomprador = seqcomprador;
	}

	public String getAutorizado() {
		return autorizado;
	}

	public void setAutorizado(String autorizado) {
		this.autorizado = autorizado;
	}

	public String getAutorizadonivel2() {
		return autorizadonivel2;
	}

	public void setAutorizadonivel2(String autorizadonivel2) {
		this.autorizadonivel2 = autorizadonivel2;
	}

	public String getUsuautorizacao2() {
		return usuautorizacao2;
	}

	public void setUsuautorizacao2(String usuautorizacao2) {
		this.usuautorizacao2 = usuautorizacao2;
	}

	public Date getDtaautorizacao2() {
		return dtaautorizacao2;
	}

	public void setDtaautorizacao2(Date dtaautorizacao2) {
		this.dtaautorizacao2 = dtaautorizacao2;
	}

	public BigDecimal getVlrautorizado() {
		return vlrautorizado;
	}

	public void setVlrautorizado(BigDecimal vlrautorizado) {
		this.vlrautorizado = vlrautorizado;
	}

	public Integer getSeqtransportador() {
		return seqtransportador;
	}

	public void setSeqtransportador(Integer seqtransportador) {
		this.seqtransportador = seqtransportador;
	}

	public String getExigeitensnota() {
		return exigeitensnota;
	}

	public void setExigeitensnota(String exigeitensnota) {
		this.exigeitensnota = exigeitensnota;
	}

	public Short getNroempresanatdesp() {
		return nroempresanatdesp;
	}

	public void setNroempresanatdesp(Short nroempresanatdesp) {
		this.nroempresanatdesp = nroempresanatdesp;
	}

	public Boolean getMcsseqcontrato() {
		return mcsseqcontrato;
	}

	public void setMcsseqcontrato(Boolean mcsseqcontrato) {
		this.mcsseqcontrato = mcsseqcontrato;
	}

	public String getStatusBpmn() {
		return statusBpmn;
	}

	public void setStatusBpmn(String statusBpmn) {
		this.statusBpmn = statusBpmn;
	}
	
	public String getGrupoRecebimento() {
		return grupoRecebimento;
	}

	public void setGrupoRecebimento(String grupoRecebimento) {
		this.grupoRecebimento = grupoRecebimento;
	}

	public Set<OrRequisicaovencto> getOrRequisicaovenctos() {
		return orRequisicaovenctos;
	}

	public void setOrRequisicaovenctos(
			Set<OrRequisicaovencto> orRequisicaovenctos) {
		this.orRequisicaovenctos = orRequisicaovenctos;
	}

	public Set<OrReqitensdespesa> getOrReqitensdespesas() {
		return orReqitensdespesas;
	}

	public void setOrReqitensdespesas(Set<OrReqitensdespesa> orReqitensdespesas) {
		this.orReqitensdespesas = orReqitensdespesas;
	}

	public Set<OrReqplanilhalancto> getOrReqplanilhalanctos() {
		return orReqplanilhalanctos;
	}

	public void setOrReqplanilhalanctos(
			Set<OrReqplanilhalancto> orReqplanilhalanctos) {
		this.orReqplanilhalanctos = orReqplanilhalanctos;
	}


	public byte[] getAnexoItens() {
		return anexoItens;
	}

	public void setAnexoItens(byte[] anexoItens) {
		this.anexoItens = anexoItens;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public void setNomeEmpresa(String empresa) {
		this.nomeEmpresa = empresa;
	}
	
	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public String getOpcaoFrete() {
		return opcaoFrete;
	}

	public void setOpcaoFrete(String opcaoFrete) {
		this.opcaoFrete = opcaoFrete;
	}

	public String getOpcaoTransportadora() {
		return opcaoTransportadora;
	}

	public void setOpcaoTransportadora(String opcaoTransportadora) {
		this.opcaoTransportadora = opcaoTransportadora;
	}

	public BigDecimal getVlrFrete() {
		return vlrFrete;
	}

	public void setVlrFrete(BigDecimal vlrFrete) {
		this.vlrFrete = vlrFrete;
	}

	public Transportadora getTransportadora() {
		return transportadora;
	}

	public void setTransportadora(Transportadora transportadora) {
		this.transportadora = transportadora;
	}

	public String getNaturezaDespesa() {
		return naturezaDespesa;
	}

	public void setNaturezaDespesa(String naturezaDespesa) {
		this.naturezaDespesa = naturezaDespesa;
	}

	public Long getNumeroIntencaoSolicitacaoCompra() {
		return numeroIntencaoSolicitacaoCompra;
	}

	public void setNumeroIntencaoSolicitacaoCompra(Long numeroIntencaoSolicitacaoCompra) {
		this.numeroIntencaoSolicitacaoCompra = numeroIntencaoSolicitacaoCompra;
	}

	@Override
	public String toString() {
		return "OrRequisicao [seqrequisicao=" + seqrequisicao + ", idBonita=" + idBonita + ", nroempresa=" + nroempresa + ", nomeEmpresa=" + nomeEmpresa
				+ ", nroempresaorc=" + nroempresaorc + ", codmodelo=" + codmodelo + ", cgo=" + cgo + ", especienf=" + especienf + ", seqpessoa="
				+ seqpessoa + ", versaopessoa=" + versaopessoa + ", nrorequisicao=" + nrorequisicao + ", dtacompra=" + dtacompra + ", dtainclusao="
				+ dtainclusao + ", dataSolicitacaoIntencao=" + dataSolicitacaoIntencao + ", naturezaDespesa=" + naturezaDespesa + ", codhistorico="
				+ codhistorico + ", observacao=" + observacao + ", opcaoFrete=" + opcaoFrete + ", opcaoTransportadora=" + opcaoTransportadora + ", valor="
				+ valor + ", vlrdescontos=" + vlrdescontos + ", vlroutrasopdesc=" + vlroutrasopdesc + ", vlracrescimos=" + vlracrescimos + ", vlrliqreq="
				+ vlrliqreq + ", vlrFrete=" + vlrFrete + ", tipoDespesa=" + tipoDespesa + ", nomeSolicitanteBPM=" + nomeSolicitanteBPM + ", prazopagto="
				+ prazopagto + ", observacaofin=" + observacaofin + ", status=" + status + ", usualteracao=" + usualteracao + ", nomeFornecedor="
				+ nomeFornecedor + ", localDeEntrega=" + localDeEntrega + ", grupoRecebimento=" + grupoRecebimento + ", dtaalteracao=" + dtaalteracao
				+ ", tipopgto=" + tipopgto + ", qtdparcela=" + qtdparcela + ", diasentrevenc=" + diasentrevenc + ", dtavencinicial=" + dtavencinicial
				+ ", diafixo=" + diafixo + ", usuautorizacao=" + usuautorizacao + ", dtaautorizacao=" + dtaautorizacao + ", seqcomprador=" + seqcomprador
				+ ", autorizado=" + autorizado + ", autorizadonivel2=" + autorizadonivel2 + ", usuautorizacao2=" + usuautorizacao2 + ", dtaautorizacao2="
				+ dtaautorizacao2 + ", vlrautorizado=" + vlrautorizado + ", seqtransportador=" + seqtransportador + ", transportadora=" + transportadora
				+ ", exigeitensnota=" + exigeitensnota + ", nroempresanatdesp=" + nroempresanatdesp + ", mcsseqcontrato=" + mcsseqcontrato
				+ ", formaPagamento=" + formaPagamento + ", statusBpmn=" + statusBpmn + ", anexoItens=" + Arrays.toString(anexoItens) + ", selecionado="
				+ selecionado + ", numeroIntencaoSolicitacaoCompra=" + numeroIntencaoSolicitacaoCompra + ", orRequisicaovenctos=" + orRequisicaovenctos
				+ ", orReqitensdespesas=" + orReqitensdespesas + ", orReqplanilhalanctos=" + orReqplanilhalanctos + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(anexoItens);
		result = prime * result
				+ ((autorizado == null) ? 0 : autorizado.hashCode());
		result = prime
				* result
				+ ((autorizadonivel2 == null) ? 0 : autorizadonivel2.hashCode());
		result = prime * result + cgo;
		result = prime * result
				+ ((codhistorico == null) ? 0 : codhistorico.hashCode());
		result = prime * result
				+ ((codmodelo == null) ? 0 : codmodelo.hashCode());
		result = prime
				* result
				+ ((dataSolicitacaoIntencao == null) ? 0
						: dataSolicitacaoIntencao.hashCode());
		result = prime * result + ((diafixo == null) ? 0 : diafixo.hashCode());
		result = prime * result
				+ ((diasentrevenc == null) ? 0 : diasentrevenc.hashCode());
		result = prime * result
				+ ((dtaalteracao == null) ? 0 : dtaalteracao.hashCode());
		result = prime * result
				+ ((dtaautorizacao == null) ? 0 : dtaautorizacao.hashCode());
		result = prime * result
				+ ((dtaautorizacao2 == null) ? 0 : dtaautorizacao2.hashCode());
		result = prime * result
				+ ((dtacompra == null) ? 0 : dtacompra.hashCode());
		result = prime * result
				+ ((dtainclusao == null) ? 0 : dtainclusao.hashCode());
		result = prime * result
				+ ((dtavencinicial == null) ? 0 : dtavencinicial.hashCode());
		result = prime * result
				+ ((especienf == null) ? 0 : especienf.hashCode());
		result = prime * result
				+ ((exigeitensnota == null) ? 0 : exigeitensnota.hashCode());
		result = prime * result
				+ ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime
				* result
				+ ((grupoRecebimento == null) ? 0 : grupoRecebimento.hashCode());
		result = prime * result + (int) (idBonita ^ (idBonita >>> 32));
		result = prime * result
				+ ((localDeEntrega == null) ? 0 : localDeEntrega.hashCode());
		result = prime * result
				+ ((mcsseqcontrato == null) ? 0 : mcsseqcontrato.hashCode());
		result = prime * result
				+ ((nomeEmpresa == null) ? 0 : nomeEmpresa.hashCode());
		result = prime * result
				+ ((nomeFornecedor == null) ? 0 : nomeFornecedor.hashCode());
		result = prime
				* result
				+ ((nomeSolicitanteBPM == null) ? 0 : nomeSolicitanteBPM
						.hashCode());
		result = prime * result + nroempresa;
		result = prime
				* result
				+ ((nroempresanatdesp == null) ? 0 : nroempresanatdesp
						.hashCode());
		result = prime * result + nroempresaorc;
		result = prime * result
				+ (int) (nrorequisicao ^ (nrorequisicao >>> 32));
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		result = prime * result
				+ ((observacaofin == null) ? 0 : observacaofin.hashCode());
		result = prime * result
				+ ((opcaoFrete == null) ? 0 : opcaoFrete.hashCode());
		result = prime
				* result
				+ ((opcaoTransportadora == null) ? 0 : opcaoTransportadora
						.hashCode());
		result = prime
				* result
				+ ((orReqitensdespesas == null) ? 0 : orReqitensdespesas
						.hashCode());
		result = prime
				* result
				+ ((orReqplanilhalanctos == null) ? 0 : orReqplanilhalanctos
						.hashCode());
		result = prime
				* result
				+ ((orRequisicaovenctos == null) ? 0 : orRequisicaovenctos
						.hashCode());
		result = prime * result
				+ ((prazopagto == null) ? 0 : prazopagto.hashCode());
		result = prime * result
				+ ((qtdparcela == null) ? 0 : qtdparcela.hashCode());
		result = prime * result
				+ ((seqcomprador == null) ? 0 : seqcomprador.hashCode());
		result = prime * result
				+ ((seqpessoa == null) ? 0 : seqpessoa.hashCode());
		result = prime * result
				+ (int) (seqrequisicao ^ (seqrequisicao >>> 32));
		result = prime
				* result
				+ ((seqtransportador == null) ? 0 : seqtransportador.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result
				+ ((statusBpmn == null) ? 0 : statusBpmn.hashCode());
		result = prime * result
				+ ((tipoDespesa == null) ? 0 : tipoDespesa.hashCode());
		result = prime * result
				+ ((tipopgto == null) ? 0 : tipopgto.hashCode());
		result = prime * result
				+ ((transportadora == null) ? 0 : transportadora.hashCode());
		result = prime * result
				+ ((usualteracao == null) ? 0 : usualteracao.hashCode());
		result = prime * result
				+ ((usuautorizacao == null) ? 0 : usuautorizacao.hashCode());
		result = prime * result
				+ ((usuautorizacao2 == null) ? 0 : usuautorizacao2.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result
				+ ((versaopessoa == null) ? 0 : versaopessoa.hashCode());
		result = prime * result
				+ ((vlrFrete == null) ? 0 : vlrFrete.hashCode());
		result = prime * result
				+ ((vlracrescimos == null) ? 0 : vlracrescimos.hashCode());
		result = prime * result
				+ ((vlrautorizado == null) ? 0 : vlrautorizado.hashCode());
		result = prime * result
				+ ((vlrdescontos == null) ? 0 : vlrdescontos.hashCode());
		result = prime * result
				+ ((vlrliqreq == null) ? 0 : vlrliqreq.hashCode());
		result = prime * result
				+ ((vlroutrasopdesc == null) ? 0 : vlroutrasopdesc.hashCode());
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
		OrRequisicao other = (OrRequisicao) obj;
		if (!Arrays.equals(anexoItens, other.anexoItens))
			return false;
		if (autorizado == null) {
			if (other.autorizado != null)
				return false;
		} else if (!autorizado.equals(other.autorizado))
			return false;
		if (autorizadonivel2 == null) {
			if (other.autorizadonivel2 != null)
				return false;
		} else if (!autorizadonivel2.equals(other.autorizadonivel2))
			return false;
		if (cgo != other.cgo)
			return false;
		if (codhistorico == null) {
			if (other.codhistorico != null)
				return false;
		} else if (!codhistorico.equals(other.codhistorico))
			return false;
		if (codmodelo == null) {
			if (other.codmodelo != null)
				return false;
		} else if (!codmodelo.equals(other.codmodelo))
			return false;
		if (dataSolicitacaoIntencao == null) {
			if (other.dataSolicitacaoIntencao != null)
				return false;
		} else if (!dataSolicitacaoIntencao
				.equals(other.dataSolicitacaoIntencao))
			return false;
		if (diafixo == null) {
			if (other.diafixo != null)
				return false;
		} else if (!diafixo.equals(other.diafixo))
			return false;
		if (diasentrevenc == null) {
			if (other.diasentrevenc != null)
				return false;
		} else if (!diasentrevenc.equals(other.diasentrevenc))
			return false;
		if (dtaalteracao == null) {
			if (other.dtaalteracao != null)
				return false;
		} else if (!dtaalteracao.equals(other.dtaalteracao))
			return false;
		if (dtaautorizacao == null) {
			if (other.dtaautorizacao != null)
				return false;
		} else if (!dtaautorizacao.equals(other.dtaautorizacao))
			return false;
		if (dtaautorizacao2 == null) {
			if (other.dtaautorizacao2 != null)
				return false;
		} else if (!dtaautorizacao2.equals(other.dtaautorizacao2))
			return false;
		if (dtacompra == null) {
			if (other.dtacompra != null)
				return false;
		} else if (!dtacompra.equals(other.dtacompra))
			return false;
		if (dtainclusao == null) {
			if (other.dtainclusao != null)
				return false;
		} else if (!dtainclusao.equals(other.dtainclusao))
			return false;
		if (dtavencinicial == null) {
			if (other.dtavencinicial != null)
				return false;
		} else if (!dtavencinicial.equals(other.dtavencinicial))
			return false;
		if (especienf == null) {
			if (other.especienf != null)
				return false;
		} else if (!especienf.equals(other.especienf))
			return false;
		if (exigeitensnota == null) {
			if (other.exigeitensnota != null)
				return false;
		} else if (!exigeitensnota.equals(other.exigeitensnota))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (grupoRecebimento == null) {
			if (other.grupoRecebimento != null)
				return false;
		} else if (!grupoRecebimento.equals(other.grupoRecebimento))
			return false;
		if (idBonita != other.idBonita)
			return false;
		if (localDeEntrega == null) {
			if (other.localDeEntrega != null)
				return false;
		} else if (!localDeEntrega.equals(other.localDeEntrega))
			return false;
		if (mcsseqcontrato == null) {
			if (other.mcsseqcontrato != null)
				return false;
		} else if (!mcsseqcontrato.equals(other.mcsseqcontrato))
			return false;
		if (nomeEmpresa == null) {
			if (other.nomeEmpresa != null)
				return false;
		} else if (!nomeEmpresa.equals(other.nomeEmpresa))
			return false;
		if (nomeFornecedor == null) {
			if (other.nomeFornecedor != null)
				return false;
		} else if (!nomeFornecedor.equals(other.nomeFornecedor))
			return false;
		if (nomeSolicitanteBPM == null) {
			if (other.nomeSolicitanteBPM != null)
				return false;
		} else if (!nomeSolicitanteBPM.equals(other.nomeSolicitanteBPM))
			return false;
		if (nroempresa != other.nroempresa)
			return false;
		if (nroempresanatdesp == null) {
			if (other.nroempresanatdesp != null)
				return false;
		} else if (!nroempresanatdesp.equals(other.nroempresanatdesp))
			return false;
		if (nroempresaorc != other.nroempresaorc)
			return false;
		if (nrorequisicao != other.nrorequisicao)
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		if (observacaofin == null) {
			if (other.observacaofin != null)
				return false;
		} else if (!observacaofin.equals(other.observacaofin))
			return false;
		if (opcaoFrete == null) {
			if (other.opcaoFrete != null)
				return false;
		} else if (!opcaoFrete.equals(other.opcaoFrete))
			return false;
		if (opcaoTransportadora == null) {
			if (other.opcaoTransportadora != null)
				return false;
		} else if (!opcaoTransportadora.equals(other.opcaoTransportadora))
			return false;
		if (orReqitensdespesas == null) {
			if (other.orReqitensdespesas != null)
				return false;
		} else if (!orReqitensdespesas.equals(other.orReqitensdespesas))
			return false;
		if (orReqplanilhalanctos == null) {
			if (other.orReqplanilhalanctos != null)
				return false;
		} else if (!orReqplanilhalanctos.equals(other.orReqplanilhalanctos))
			return false;
		if (orRequisicaovenctos == null) {
			if (other.orRequisicaovenctos != null)
				return false;
		} else if (!orRequisicaovenctos.equals(other.orRequisicaovenctos))
			return false;
		if (prazopagto == null) {
			if (other.prazopagto != null)
				return false;
		} else if (!prazopagto.equals(other.prazopagto))
			return false;
		if (qtdparcela == null) {
			if (other.qtdparcela != null)
				return false;
		} else if (!qtdparcela.equals(other.qtdparcela))
			return false;
		if (seqcomprador == null) {
			if (other.seqcomprador != null)
				return false;
		} else if (!seqcomprador.equals(other.seqcomprador))
			return false;
		if (seqpessoa == null) {
			if (other.seqpessoa != null)
				return false;
		} else if (!seqpessoa.equals(other.seqpessoa))
			return false;
		if (seqrequisicao != other.seqrequisicao)
			return false;
		if (seqtransportador == null) {
			if (other.seqtransportador != null)
				return false;
		} else if (!seqtransportador.equals(other.seqtransportador))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusBpmn == null) {
			if (other.statusBpmn != null)
				return false;
		} else if (!statusBpmn.equals(other.statusBpmn))
			return false;
		if (tipoDespesa == null) {
			if (other.tipoDespesa != null)
				return false;
		} else if (!tipoDespesa.equals(other.tipoDespesa))
			return false;
		if (tipopgto == null) {
			if (other.tipopgto != null)
				return false;
		} else if (!tipopgto.equals(other.tipopgto))
			return false;
		if (transportadora == null) {
			if (other.transportadora != null)
				return false;
		} else if (!transportadora.equals(other.transportadora))
			return false;
		if (usualteracao == null) {
			if (other.usualteracao != null)
				return false;
		} else if (!usualteracao.equals(other.usualteracao))
			return false;
		if (usuautorizacao == null) {
			if (other.usuautorizacao != null)
				return false;
		} else if (!usuautorizacao.equals(other.usuautorizacao))
			return false;
		if (usuautorizacao2 == null) {
			if (other.usuautorizacao2 != null)
				return false;
		} else if (!usuautorizacao2.equals(other.usuautorizacao2))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (versaopessoa == null) {
			if (other.versaopessoa != null)
				return false;
		} else if (!versaopessoa.equals(other.versaopessoa))
			return false;
		if (vlrFrete == null) {
			if (other.vlrFrete != null)
				return false;
		} else if (!vlrFrete.equals(other.vlrFrete))
			return false;
		if (vlracrescimos == null) {
			if (other.vlracrescimos != null)
				return false;
		} else if (!vlracrescimos.equals(other.vlracrescimos))
			return false;
		if (vlrautorizado == null) {
			if (other.vlrautorizado != null)
				return false;
		} else if (!vlrautorizado.equals(other.vlrautorizado))
			return false;
		if (vlrdescontos == null) {
			if (other.vlrdescontos != null)
				return false;
		} else if (!vlrdescontos.equals(other.vlrdescontos))
			return false;
		if (vlrliqreq == null) {
			if (other.vlrliqreq != null)
				return false;
		} else if (!vlrliqreq.equals(other.vlrliqreq))
			return false;
		if (vlroutrasopdesc == null) {
			if (other.vlroutrasopdesc != null)
				return false;
		} else if (!vlroutrasopdesc.equals(other.vlroutrasopdesc))
			return false;
		return true;
	}

	@Override
	public int compareTo(OrRequisicao o) {
		return Comparators._TIPODESPESA.compare(this, o);
	}
	
	public static class Comparators {
			
		public static Comparator<OrRequisicao> _TIPODESPESA = new Comparator<OrRequisicao>() {
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				return o1.getTipoDespesa().compareTo(o2.getTipoDespesa());
			}
		};
		
		public static Comparator<OrRequisicao> _INTENCAO = new Comparator<OrRequisicao>() {
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				Long or1 = o1.getSeqrequisicao();
				Long or2 = o2.getSeqrequisicao();
				return or2.compareTo(or1);
			}
		};
		
		public static Comparator<OrRequisicao> _REQUISICAO = new Comparator<OrRequisicao>() {
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				Long or1 = o1.getNrorequisicao();
				Long or2 = o2.getNrorequisicao();
				return or2.compareTo(or1);
			}
		};
		
		public static Comparator<OrRequisicao> _LOCALENTREGA = new Comparator<OrRequisicao>(){
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				return o1.getGrupoRecebimento().compareTo(o2.getGrupoRecebimento());
			}
		};
		
		public static Comparator<OrRequisicao> _SOLICITANTE = new Comparator<OrRequisicao>(){
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				return o1.getNomeSolicitanteBPM().compareTo(o2.getNomeSolicitanteBPM());
			}
		};
		
		public static Comparator<OrRequisicao> _FORNECEDOR = new Comparator<OrRequisicao>(){
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				return o1.getNomeFornecedor().compareTo(o2.getNomeFornecedor());
			}
		};
		
		public static Comparator<OrRequisicao> _EMFILA = new Comparator<OrRequisicao>(){
			@Override
			public int compare(OrRequisicao o1, OrRequisicao o2) {
				return o1.getDataSolicitacaoIntencao().compareTo(o2.getDataSolicitacaoIntencao());
			}
		};

	}

}
