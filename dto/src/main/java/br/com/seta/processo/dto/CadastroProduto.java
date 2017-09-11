package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;


@Entity("CadastroProduto")
public class CadastroProduto implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message="O solicitante é obrigatório")
	private String nomeSolicitante;
	private String emailSolicitante;
	private String telefoneSolicitante;
	@NotBlank(message="A descrição é obrigatoria")
	private String descricao;
	private Date dataSolicitacao;
	private String comentarioGerenteComercial;
	private boolean aprovacaoGerComercia;
	private String comentarioCadastro;
	private String comentarioCorrecaoCad;
	private String idCadastro;
	private long idSolicitante;
	private String idGerComercial;
	private boolean finalizarCadastr;
	private long idRespPreench;
	@NotBlank(message="A área é obrigatória")
	private String area;
	@NotBlank(message="O responsável pelo preenchimento é obrigatório")
	private String nomeRespPreench;
	private String idFornecedorC5;
	private String caseId;
	private String IsPrecadastro;	
	private boolean acordoComercial;
	@NotBlank(message="O número de acordo é obrigatório")
	private String nroAcordo;
	
	@Embedded
	private List<HistoricoFornecedor> historicoFornecedors = new ArrayList<HistoricoFornecedor>();

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

	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}

	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getComentarioGerenteComercial() {
		return comentarioGerenteComercial;
	}

	public void setComentarioGerenteComercial(String comentarioGerenteComercial) {
		this.comentarioGerenteComercial = comentarioGerenteComercial;
	}

	public boolean isAprovacaoGerComercia() {
		return aprovacaoGerComercia;
	}

	public void setAprovacaoGerComercia(boolean aprovacaoGerComercia) {
		this.aprovacaoGerComercia = aprovacaoGerComercia;
	}

	public String getComentarioCadastro() {
		return comentarioCadastro;
	}

	public void setComentarioCadastro(String comentarioCadastro) {
		this.comentarioCadastro = comentarioCadastro;
	}

	public String getComentarioCorrecaoCad() {
		return comentarioCorrecaoCad;
	}

	public void setComentarioCorrecaoCad(String comentarioCorrecaoCad) {
		this.comentarioCorrecaoCad = comentarioCorrecaoCad;
	}

	public String getIdCadastro() {
		return idCadastro;
	}

	public void setIdCadastro(String idCadastro) {
		this.idCadastro = idCadastro;
	}

	public long getIdSolicitante() {
		return idSolicitante;
	}

	public void setIdSolicitante(long idSolicitante) {
		this.idSolicitante = idSolicitante;
	}

	public String getIdGerComercial() {
		return idGerComercial;
	}

	public void setIdGerComercial(String idGerComercial) {
		this.idGerComercial = idGerComercial;
	}

	public boolean isFinalizarCadastr() {
		return finalizarCadastr;
	}

	public void setFinalizarCadastr(boolean finalizarCadastr) {
		this.finalizarCadastr = finalizarCadastr;
	}

	public long getIdRespPreench() {
		return idRespPreench;
	}

	public void setIdRespPreench(long idRespPreench) {
		this.idRespPreench = idRespPreench;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getNomeRespPreench() {
		return nomeRespPreench;
	}

	public void setNomeRespPreench(String nomeRespPreench) {
		this.nomeRespPreench = nomeRespPreench;
	}

	public String getIdFornecedorC5() {
		return idFornecedorC5;
	}

	public void setIdFornecedorC5(String idFornecedorC5) {
		this.idFornecedorC5 = idFornecedorC5;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public List<HistoricoFornecedor> getHistoricoFornecedors() {
		return historicoFornecedors;
	}

	public void setHistoricoFornecedors(List<HistoricoFornecedor> historicoFornecedors) {
		this.historicoFornecedors = historicoFornecedors;
	}

	public String getIsPrecadastro() {
		return IsPrecadastro;
	}

	public void setIsPrecadastro(String isPrecadastro) {
		IsPrecadastro = isPrecadastro;
	}

	
	public boolean isAcordoComercial() {
		return acordoComercial;
	}

	public String getNroAcordo() {
		return nroAcordo;
	}

	public void setAcordoComercial(boolean acordoComercial) {
		this.acordoComercial = acordoComercial;
	}

	public void setNroAcordo(String nroAcordo) {
		this.nroAcordo = nroAcordo;
	}

	@Override
	public String toString() {
		return "CadastroProduto [nomeSolicitante=" + nomeSolicitante
				+ ", emailSolicitante=" + emailSolicitante
				+ ", telefoneSolicitante=" + telefoneSolicitante
				+ ", descricao=" + descricao + ", dataSolicitacao="
				+ dataSolicitacao + ", comentarioGerenteComercial="
				+ comentarioGerenteComercial + ", aprovacaoGerComercia="
				+ aprovacaoGerComercia + ", comentarioCadastro="
				+ comentarioCadastro + ", comentarioCorrecaoCad="
				+ comentarioCorrecaoCad + ", idCadastro=" + idCadastro
				+ ", idSolicitante=" + idSolicitante + ", idGerComercial="
				+ idGerComercial + ", finalizarCadastr=" + finalizarCadastr
				+ ", idRespPreench=" + idRespPreench + ", area=" + area
				+ ", nomeRespPreench=" + nomeRespPreench + ", idFornecedorC5="
				+ idFornecedorC5 + ", caseId=" + caseId + ", IsPrecadastro="
				+ IsPrecadastro + ", acordoComercial=" + acordoComercial
				+ ", nroAcordo=" + nroAcordo + ", historicoFornecedors="
				+ historicoFornecedors + "]";
	}
	
}