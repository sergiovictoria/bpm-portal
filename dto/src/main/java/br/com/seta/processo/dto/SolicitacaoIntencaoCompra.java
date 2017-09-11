package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


public class SolicitacaoIntencaoCompra implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="O nome do responsável pelo preenchimento é obrigatório")	
	private String nomeRespPreenchimento;
	@NotBlank(message="O e-mail do responsável pelo preenchimento é obrigatório")
	@Email(message="O formato do e-mail do responsável pelo preenchimento está incorreto")
	private String emailRespPreenchimento;
	@NotBlank(message="O telefone do responsável pelo preenchimento é obrigatório")
	private String telRespPreenchimento;
	@NotBlank(message="A despesa é obrigatória")
	private String tipoDespesa;
	@NotBlank(message="A área solicitante é obrigatória")
	private String areaSolicitante;
	@NotBlank(message="A diretoria é obrigatória")
	private String diretoria;
	@NotBlank(message="A gerência é obrigatória")
	private String gerencia;
	@NotBlank(message="O comprador é obrigatório")
	private String comprador;
	@NotBlank(message="A descrição é obrigatória")
	private String descricaoCompra;
	@NotBlank(message="O nome do solicitante é obrigatório")
	private String nomeSolicitante;
	@NotBlank(message="O e-mail do solicitante é obrigatório")
	@Email(message="O formato do e-mail do solicitante está incorreto")
	private String emailSolicitante;
	@NotBlank(message="O telefone do solicitante é obrigatório")
	private String foneSolicitante;
	private String nomeAprovadorHierarquio;
	private String emailAprovadorHierarquio;
	private List<Historico> historico;
	private String mailCSCJuridicos;
	private String emailJuridicos;
	private String emailsAreaCadastros;
	private String emailsAutomacaos;
	private String emailsAprovadorSubstitutos;
	private String nomeAprovadorFuncional;
	private Long numeroIntencaoSolicitacaoCompra;
	private Long idAprovadorHierarquio;
	private Long idUsuarioLogado;
	private List<ItensContrato> listaItensSemContrato;
	private String usuarioLogado;
	private String emailUsuarioLogado;
	private String status;
	private String nomerespcscjuridico;
	private Long idGrupoCSC;
	private Long idGrupoCSCIntegracao;
	private Long idGrupoCSCJuridico;
	private Long idGrupoCSCValidacao;
	private String situacaoAprovador;
	private java.util.Date dataInclusaoC5;
	private Long idResponsavelPreenchimento;
	private Long idAprovadorFuncional;
	
	
	
	private Boolean necessidadeAprvHierarq, canceladoRejeitadoAprovFunc, canceladoRejeitadoAprovHierar, isContratoPendente, isItensPendente, isFornecedorPendente, continuaIntencao;

	public String getNomeRespPreenchimento() {
		return nomeRespPreenchimento;
	}
	public void setNomeRespPreenchimento(String nomeRespPreenchimento) {
		this.nomeRespPreenchimento = nomeRespPreenchimento;
	}
	public String getEmailRespPreenchimento() {
		return emailRespPreenchimento;
	}
	public void setEmailRespPreenchimento(String emailRespPreenchimento) {
		this.emailRespPreenchimento = emailRespPreenchimento;
	}
	public String getTelRespPreenchimento() {
		return telRespPreenchimento;
	}
	public void setTelRespPreenchimento(String telRespPreenchimento) {
		this.telRespPreenchimento = telRespPreenchimento;
	}
	public String getTipoDespesa() {
		return tipoDespesa;
	}
	public void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}
	public String getAreaSolicitante() {
		return areaSolicitante;
	}
	public void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}
	public String getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getDescricaoCompra() {
		return descricaoCompra;
	}
	public void setDescricaoCompra(String descricaoCompra) {
		this.descricaoCompra = descricaoCompra;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
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
	public String getFoneSolicitante() {
		return foneSolicitante;
	}
	public void setFoneSolicitante(String foneSolicitante) {
		this.foneSolicitante = foneSolicitante;
	}
	public String getNomeAprovadorHierarquio() {
		return nomeAprovadorHierarquio;
	}
	public void setNomeAprovadorHierarquio(String nomeAprovadorHierarquio) {
		this.nomeAprovadorHierarquio = nomeAprovadorHierarquio;
	}
	public String getEmailAprovadorHierarquio() {
		return emailAprovadorHierarquio;
	}
	public void setEmailAprovadorHierarquio(String emailAprovadorHierarquio) {
		this.emailAprovadorHierarquio = emailAprovadorHierarquio;
	}
	public List<Historico> getHistorico() {
		return historico;
	}
	public void setHistorico(List<Historico> historico) {
		this.historico = historico;
	}
	public String getMailCSCJuridicos() {
		return mailCSCJuridicos;
	}
	public void setMailCSCJuridicos(String mailCSCJuridicos) {
		this.mailCSCJuridicos = mailCSCJuridicos;
	}
	public String getEmailJuridicos() {
		return emailJuridicos;
	}
	public void setEmailJuridicos(String emailJuridicos) {
		this.emailJuridicos = emailJuridicos;
	}
	public String getEmailsAreaCadastros() {
		return emailsAreaCadastros;
	}
	public void setEmailsAreaCadastros(String emailsAreaCadastros) {
		this.emailsAreaCadastros = emailsAreaCadastros;
	}
	public String getEmailsAutomacaos() {
		return emailsAutomacaos;
	}
	public void setEmailsAutomacaos(String emailsAutomacaos) {
		this.emailsAutomacaos = emailsAutomacaos;
	}
	public String getEmailsAprovadorSubstitutos() {
		return emailsAprovadorSubstitutos;
	}
	public void setEmailsAprovadorSubstitutos(String emailsAprovadorSubstitutos) {
		this.emailsAprovadorSubstitutos = emailsAprovadorSubstitutos;
	}
	public String getNomeAprovadorFuncional() {
		return nomeAprovadorFuncional;
	}
	public void setNomeAprovadorFuncional(String nomeAprovadorFuncional) {
		this.nomeAprovadorFuncional = nomeAprovadorFuncional;
	}
	public Long getNumeroIntencaoSolicitacaoCompra() {
		return numeroIntencaoSolicitacaoCompra;
	}
	public void setNumeroIntencaoSolicitacaoCompra(Long numeroIntencaoSolicitacaoCompra) {
		this.numeroIntencaoSolicitacaoCompra = numeroIntencaoSolicitacaoCompra;
	}
	public Boolean getNecessidadeAprvHierarq() {
		return necessidadeAprvHierarq;
	}
	public void setNecessidadeAprvHierarq(Boolean necessidadeAprvHierarq) {
		this.necessidadeAprvHierarq = necessidadeAprvHierarq;
	}
	public Boolean getCanceladoRejeitadoAprovFunc() {
		return canceladoRejeitadoAprovFunc;
	}
	public void setCanceladoRejeitadoAprovFunc(Boolean canceladoRejeitadoAprovFunc) {
		this.canceladoRejeitadoAprovFunc = canceladoRejeitadoAprovFunc;
	}
	public Boolean getCanceladoRejeitadoAprovHierar() {
		return canceladoRejeitadoAprovHierar;
	}
	public void setCanceladoRejeitadoAprovHierar(Boolean canceladoRejeitadoAprovHierar) {
		this.canceladoRejeitadoAprovHierar = canceladoRejeitadoAprovHierar;
	}
	public Long getIdAprovadorHierarquio() {
		return idAprovadorHierarquio;
	}
	public void setIdAprovadorHierarquio(Long idAprovadorHierarquio) {
		this.idAprovadorHierarquio = idAprovadorHierarquio;
	}
	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}
	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}
	public Boolean getIsItensPendente() {
		return isItensPendente;
	}
	public void setIsItensPendente(Boolean isItensPendente) {
		this.isItensPendente = isItensPendente;
	}
	public Boolean getIsFornecedorPendente() {
		return isFornecedorPendente;
	}
	public void setIsFornecedorPendente(Boolean isFornecedorPendente) {
		this.isFornecedorPendente = isFornecedorPendente;
	}
	public Boolean getContinuaIntencao() {
		return continuaIntencao;
	}
	public void setContinuaIntencao(Boolean continuaIntencao) {
		this.continuaIntencao = continuaIntencao;
	}

	public String getUsuarioLogado() {
		return usuarioLogado;
	}
	public void setUsuarioLogado(String usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	public String getEmailUsuarioLogado() {
		return emailUsuarioLogado;
	}
	public void setEmailUsuarioLogado(String emailUsuarioLogado) {
		this.emailUsuarioLogado = emailUsuarioLogado;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNomerespcscjuridico() {
		return nomerespcscjuridico;
	}
	public void setNomerespcscjuridico(String nomerespcscjuridico) {
		this.nomerespcscjuridico = nomerespcscjuridico;
	}
	public List<ItensContrato> getListaItensSemContrato() {
		return listaItensSemContrato;
	}
	public void setListaItensSemContrato(List<ItensContrato> listaItensSemContrato) {
		this.listaItensSemContrato = listaItensSemContrato;
	}
	public Boolean getIsContratoPendente() {
		return isContratoPendente;
	}
	public void setIsContratoPendente(Boolean isContratoPendente) {
		this.isContratoPendente = isContratoPendente;
	}

	public Long getIdGrupoCSC() {
		return idGrupoCSC;
	}
	public void setIdGrupoCSC(Long idGrupoCSC) {
		this.idGrupoCSC = idGrupoCSC;
	}
	public Long getIdGrupoCSCIntegracao() {
		return idGrupoCSCIntegracao;
	}
	public void setIdGrupoCSCIntegracao(Long idGrupoCSCIntegracao) {
		this.idGrupoCSCIntegracao = idGrupoCSCIntegracao;
	}
	public Long getIdGrupoCSCJuridico() {
		return idGrupoCSCJuridico;
	}
	public void setIdGrupoCSCJuridico(Long idGrupoCSCJuridico) {
		this.idGrupoCSCJuridico = idGrupoCSCJuridico;
	}
	public Long getIdGrupoCSCValidacao() {
		return idGrupoCSCValidacao;
	}
	public void setIdGrupoCSCValidacao(Long idGrupoCSCValidacao) {
		this.idGrupoCSCValidacao = idGrupoCSCValidacao;
	}
	public String getSituacaoAprovador() {
		return situacaoAprovador;
	}
	public void setSituacaoAprovador(String situacaoAprovador) {
		this.situacaoAprovador = situacaoAprovador;
	}
	public java.util.Date getDataInclusaoC5() {
		return dataInclusaoC5;
	}
	public void setDataInclusaoC5(java.util.Date dataInclusaoC5) {
		this.dataInclusaoC5 = dataInclusaoC5;
	}
	public Long getIdResponsavelPreenchimento() {
		return idResponsavelPreenchimento;
	}
	public void setIdResponsavelPreenchimento(Long idResponsavelPreenchimento) {
		this.idResponsavelPreenchimento = idResponsavelPreenchimento;
	}
	public Long getIdAprovadorFuncional() {
		return idAprovadorFuncional;
	}
	public void setIdAprovadorFuncional(Long idAprovadorFuncional) {
		this.idAprovadorFuncional = idAprovadorFuncional;
	}
	
	
	
	@Override
	public String toString() {
		return "SolicitacaoIntencaoCompra [nomeRespPreenchimento="
				+ nomeRespPreenchimento + ", emailRespPreenchimento="
				+ emailRespPreenchimento + ", telRespPreenchimento="
				+ telRespPreenchimento + ", tipoDespesa=" + tipoDespesa
				+ ", areaSolicitante=" + areaSolicitante + ", diretoria="
				+ diretoria + ", gerencia=" + gerencia + ", comprador="
				+ comprador + ", descricaoCompra=" + descricaoCompra
				+ ", nomeSolicitante=" + nomeSolicitante
				+ ", emailSolicitante=" + emailSolicitante
				+ ", foneSolicitante=" + foneSolicitante
				+ ", nomeAprovadorHierarquio=" + nomeAprovadorHierarquio
				+ ", emailAprovadorHierarquio=" + emailAprovadorHierarquio
				+ ", historico=" + historico + ", mailCSCJuridicos="
				+ mailCSCJuridicos + ", emailJuridicos=" + emailJuridicos
				+ ", emailsAreaCadastros=" + emailsAreaCadastros
				+ ", emailsAutomacaos=" + emailsAutomacaos
				+ ", emailsAprovadorSubstitutos=" + emailsAprovadorSubstitutos
				+ ", nomeAprovadorFuncional=" + nomeAprovadorFuncional
				+ ", numeroIntencaoSolicitacaoCompra="
				+ numeroIntencaoSolicitacaoCompra + ", idAprovadorHierarquio="
				+ idAprovadorHierarquio + ", idUsuarioLogado="
				+ idUsuarioLogado + ", listaItensSemContrato="
				+ listaItensSemContrato + ", usuarioLogado=" + usuarioLogado
				+ ", emailUsuarioLogado=" + emailUsuarioLogado + ", status="
				+ status + ", nomerespcscjuridico=" + nomerespcscjuridico
				+ ", idGrupoCSC=" + idGrupoCSC + ", idGrupoCSCIntegracao="
				+ idGrupoCSCIntegracao + ", idGrupoCSCJuridico="
				+ idGrupoCSCJuridico + ", idGrupoCSCValidacao="
				+ idGrupoCSCValidacao + ", situacaoAprovador="
				+ situacaoAprovador + ", dataInclusaoC5=" + dataInclusaoC5
				+ ", idResponsavelPreenchimento=" + idResponsavelPreenchimento
				+ ", idAprovadorFuncional=" + idAprovadorFuncional
				+ ", necessidadeAprvHierarq=" + necessidadeAprvHierarq
				+ ", canceladoRejeitadoAprovFunc="
				+ canceladoRejeitadoAprovFunc
				+ ", canceladoRejeitadoAprovHierar="
				+ canceladoRejeitadoAprovHierar + ", isContratoPendente="
				+ isContratoPendente + ", isItensPendente=" + isItensPendente
				+ ", isFornecedorPendente=" + isFornecedorPendente
				+ ", continuaIntencao=" + continuaIntencao + "]";
	}
	
	
	
	
	
	
}
