package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;


@Entity("CadastroFornecedor")
public class CadastroFornecedor implements Serializable {
		
	private static final long serialVersionUID = 1L;
	
	private String nomeSolicitante;
	private String emailSolicitante;
	private String telefoneSolicitante;
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
	private String area;
	private String nomeRespPreench;
	private byte[] anexoPlan;
	private byte[] templatePlanCadFornecedor;
	private byte[] templateFornecLibreOffice;
	private String idFornecedorC5;
	private Integer caseId;
	private String idUsuario;
	
	
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

	public byte[] getAnexoPlan() {
		return anexoPlan;
	}

	public void setAnexoPlan(byte[] anexoPlan) {
		this.anexoPlan = anexoPlan;
	}

	public byte[] getTemplatePlanCadFornecedor() {
		return templatePlanCadFornecedor;
	}

	public void setTemplatePlanCadFornecedor(byte[] templatePlanCadFornecedor) {
		this.templatePlanCadFornecedor = templatePlanCadFornecedor;
	}

	public byte[] getTemplateFornecLibreOffice() {
		return templateFornecLibreOffice;
	}

	public void setTemplateFornecLibreOffice(byte[] templateFornecLibreOffice) {
		this.templateFornecLibreOffice = templateFornecLibreOffice;
	}

	public String getIdFornecedorC5() {
		return idFornecedorC5;
	}

	public void setIdFornecedorC5(String idFornecedorC5) {
		this.idFornecedorC5 = idFornecedorC5;
	}

	public Integer getCaseId() {
		return caseId;
	}

	public void setCaseId(Integer caseId) {
		this.caseId = caseId;
	}

	public List<HistoricoFornecedor> getHistoricoFornecedors() {
		return historicoFornecedors;
	}

	public void setHistoricoFornecedors(List<HistoricoFornecedor> historicoFornecedors) {
		this.historicoFornecedors = historicoFornecedors;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	@Override
	public String toString() {
		return "CadastroFornecedor [nomeSolicitante=" + nomeSolicitante
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
				+ ", nomeRespPreench=" + nomeRespPreench + ", anexoPlan="
				+ Arrays.toString(anexoPlan) + ", templatePlanCadFornecedor="
				+ Arrays.toString(templatePlanCadFornecedor)
				+ ", templateFornecLibreOffice="
				+ Arrays.toString(templateFornecLibreOffice)
				+ ", idFornecedorC5=" + idFornecedorC5 + ", caseId=" + caseId
				+ ", idUsuario=" + idUsuario + ", historicoFornecedors="
				+ historicoFornecedors + "]";
	}

	
}