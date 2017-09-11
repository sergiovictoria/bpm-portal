package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import br.com.seta.processo.validacoes.SomaParcelasSolicitacaoPagtoIgualAoVlrTotal;
import br.com.seta.processo.validacoes.SomaRateiosSolicPagtoIgualAoVlrTotal;
import br.com.seta.processo.validacoes.VencimentoMaiorQueDataEmissaoDaSolicitacaoPagamento;

/**
 * Representa uma Solicitação de Pagamento do Processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@SomaParcelasSolicitacaoPagtoIgualAoVlrTotal(message="A soma das parcelas da solicitação de pagamento deve ser igual ao seu valor total")
@SomaRateiosSolicPagtoIgualAoVlrTotal(message="A soma dos rateios da solicitação de pagamento deve ser igual ao seu valor total")
@VencimentoMaiorQueDataEmissaoDaSolicitacaoPagamento(message="A data de emissão não pode ser superior a data de vencimento")
@Entity
public class SolicitacaoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;	

	public static final String STATUS_EM_ANALISE = "Em Análise";
	public static final String STATUS_APROVADO = "Aprovado";	
	public static final String STATUS_REJEITADO = "Rejeitado";

	@Id
	private String id;
	private Long caseId;	
	@NotEmpty(message="O nome é obrigatório")
	private String nomeSolicitante;
	private Long idSolicitante;
	private String userNameSolicitante;
	@NotEmpty(message="O e-mail é obrigatório")
	@Email(message="O e-mail não está em um formato correto")
	private String emailSolicitante;
	private String telefoneSolicitante;
	@NotNull(message="A empresa é obrigatória")
	private Integer nroEmpresa;
	private String nomeLoja;
	@NotNull(message="A Natureza de Despesa é obrigatória")
	private Integer codNaturezaDespesa;
	private String naturezaDespesa;
	@NotEmpty(message="O fornecedor é obrigatório")
	private String fornecedor;	
	private BigDecimal codFornecedor;	
	@NotNull(message="O valor é obrigatório")
	@Digits(integer=15, fraction=2, message="O valor não deve ser maior que R$ 999.999.999.999.999,99")
	private BigDecimal valor = BigDecimal.ZERO;
	private String chaveAcesso;
	private boolean possuiChaveAcesso = false;
	@NotNull(message="O número da nota é obrigatório")
	@Digits(integer=10, fraction=0, message="O número da nota deve ter no máximo 10 dígitos")
	private Long nroNota;
	@NotNull(message="A data de emissão é obrigatória")
	private Date dataEmissao;	
	private Date dataLancamento;
	@Length(max=200, message="A mensagem não pode exceder 200 caracteres")
	private String mensagem;	
	private Long seqNota;
	private String status;	
	private String descricaoCentroCusto;
	private Integer centrocusto;
	@Embedded(value="historico")
	private List<RegistroHistSolicitacaoPagto> historico = new ArrayList<RegistroHistSolicitacaoPagto>();
	@NotEmpty(message="Deve-se adicionar o rateio dos custos na etapa 2 (Contabilização).")
	private List<OrReqplanilhalancto> orReqplanilhalanctos = new ArrayList<OrReqplanilhalancto>();
	@NotEmpty(message="Deve-se adicionar ao menos um lançamento na etapa 3 (Financeiro).")
	private List<OrSolicitacaovencto> orSolicitacaovenctos = new ArrayList<>();
	private String tipoGeracaoParecelas;
	private Integer qtdPrazo = 0;
	private Integer qtdDiasEntreVenc = 0;
	private Integer qtdParcelas = 0;
	private Date dataVencimentoInicial;
	private boolean geraDiaFixo;	
	
	public SolicitacaoPagamento(){
		//Gera id único para o MongoDB
		this.id = new ObjectId().toString(); 
	}
	
	public void adicionaRegistroAoHistorico(RegistroHistSolicitacaoPagto registro){
		historico.add(registro);
	}
	
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getEmailSolicitante() {
		return emailSolicitante;
	}

	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}

	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}

	public Integer getNroEmpresa() {
		return nroEmpresa;
	}

	public void setNroEmpresa(Integer nroEmpresa) {
		this.nroEmpresa = nroEmpresa;
	}

	public Integer getCodNaturezaDespesa() {
		return codNaturezaDespesa;
	}

	public void setCodNaturezaDespesa(Integer codNaturezaDespesa) {
		this.codNaturezaDespesa = codNaturezaDespesa;
	}

	public String getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}	

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public Long getNroNota() {
		return nroNota;
	}

	public boolean getPossuiChaveAcesso() {
		return possuiChaveAcesso;
	}

	public void setPossuiChaveAcesso(boolean possuiChaveAcesso) {
		this.possuiChaveAcesso = possuiChaveAcesso;
	}

	public void setNroNota(Long nroNota) {
		this.nroNota = nroNota;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Date getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Date dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public String getNaturezaDespesa() {
		return naturezaDespesa;
	}

	public void setNaturezaDespesa(String naturezaDespesa) {
		this.naturezaDespesa = naturezaDespesa;
	}

	public BigDecimal getCodFornecedor() {
		return codFornecedor;
	}

	public void setCodFornecedor(BigDecimal codFornecedor) {
		this.codFornecedor = codFornecedor;
	}

	public Long getSeqNota() {
		return seqNota;
	}

	public void setSeqNota(Long seqNota) {
		this.seqNota = seqNota;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RegistroHistSolicitacaoPagto> getHistorico() {
		return historico;
	}

	public void setHistorico(List<RegistroHistSolicitacaoPagto> historico) {
		this.historico = historico;
	}

	public String getUserNameSolicitante() {
		return userNameSolicitante;
	}

	public void setUserNameSolicitante(String loginSolicitante) {
		this.userNameSolicitante = loginSolicitante;
	}

	public Long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(Long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getDescricaoCentroCusto() {
		return descricaoCentroCusto;
	}

	public void setDescricaoCentroCusto(String descricaoCentroCusto) {
		this.descricaoCentroCusto = descricaoCentroCusto;
	}

	public Integer getCentrocusto() {
		return centrocusto;
	}

	public void setCentrocusto(Integer centrocusto) {
		this.centrocusto = centrocusto;
	}

	public List<OrReqplanilhalancto> getOrReqplanilhalanctos() {
		return orReqplanilhalanctos;
	}

	public void setOrReqplanilhalanctos(List<OrReqplanilhalancto> orReqplanilhalanctos) {
		this.orReqplanilhalanctos = orReqplanilhalanctos;
	}

	public List<OrSolicitacaovencto> getOrSolicitacaovenctos() {
		return orSolicitacaovenctos;
	}

	public void setOrSolicitacaovenctos(List<OrSolicitacaovencto> orSolicitacaovenctos) {
		this.orSolicitacaovenctos = orSolicitacaovenctos;
	}

	public String getTipoGeracaoParecelas() {
		return tipoGeracaoParecelas;
	}

	public void setTipoGeracaoParecelas(String tipoGeracaoParecelas) {
		this.tipoGeracaoParecelas = tipoGeracaoParecelas;
	}

	public Integer getQtdPrazo() {
		return qtdPrazo;
	}

	public void setQtdPrazo(Integer qtdPrazo) {
		this.qtdPrazo = qtdPrazo;
	}

	public Integer getQtdDiasEntreVenc() {
		return qtdDiasEntreVenc;
	}

	public void setQtdDiasEntreVenc(Integer qtdDiasEntreVenc) {
		this.qtdDiasEntreVenc = qtdDiasEntreVenc;
	}

	public Integer getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Date getDataVencimentoInicial() {
		return dataVencimentoInicial;
	}

	public void setDataVencimentoInicial(Date dataVencimentoInicial) {
		this.dataVencimentoInicial = dataVencimentoInicial;
	}

	public boolean isGeraDiaFixo() {
		return geraDiaFixo;
	}

	public void setGeraDiaFixo(boolean geraDiaFixo) {
		this.geraDiaFixo = geraDiaFixo;
	}

	@Override
	public String toString() {
		return "SolicitacaoPagamento [id=" + id + ", caseId=" + caseId
				+ ", nomeSolicitante=" + nomeSolicitante + ", idSolicitante="
				+ idSolicitante + ", userNameSolicitante="
				+ userNameSolicitante + ", emailSolicitante="
				+ emailSolicitante + ", telefoneSolicitante="
				+ telefoneSolicitante + ", nroEmpresa=" + nroEmpresa
				+ ", nomeLoja=" + nomeLoja + ", codNaturezaDespesa="
				+ codNaturezaDespesa + ", naturezaDespesa=" + naturezaDespesa
				+ ", fornecedor=" + fornecedor + ", codFornecedor="
				+ codFornecedor + ", valor=" + valor + ", chaveAcesso="
				+ chaveAcesso + ", possuiChaveAcesso=" + possuiChaveAcesso
				+ ", nroNota=" + nroNota + ", dataEmissao=" + dataEmissao
				+ ", dataLancamento=" + dataLancamento + ", mensagem="
				+ mensagem + ", seqNota=" + seqNota + ", status=" + status
				+ ", descricaoCentroCusto=" + descricaoCentroCusto
				+ ", centrocusto=" + centrocusto + ", historico=" + historico
				+ ", orReqplanilhalanctos=" + orReqplanilhalanctos
				+ ", orSolicitacaovenctos=" + orSolicitacaovenctos
				+ ", tipoGeracaoParecelas=" + tipoGeracaoParecelas
				+ ", qtdPrazo=" + qtdPrazo + ", qtdDiasEntreVenc="
				+ qtdDiasEntreVenc + ", qtdParcelas=" + qtdParcelas
				+ ", dataVencimentoInicial=" + dataVencimentoInicial
				+ ", geraDiaFixo=" + geraDiaFixo + "]";
	}
}
