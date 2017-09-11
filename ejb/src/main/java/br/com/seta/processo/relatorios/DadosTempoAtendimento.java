package br.com.seta.processo.relatorios;

import java.io.Serializable;
import java.util.Date;

import br.com.seta.processo.dto.InstanciaProcesso;
import br.com.seta.processo.dtobonita.Usuario;

public class DadosTempoAtendimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long caseId;
	private String processo;
	private Date criacaoInstancia;
	private Date fimInstancia;
	private String usuarioaIniciador;
	private String duracao;	
	
	public DadosTempoAtendimento(long caseId, String processo,
			Date criacaoInstancia, Date fimInstancia, String usuarioaIniciador,
			String duracao) {
		this.caseId = caseId;
		this.processo = processo;
		this.criacaoInstancia = criacaoInstancia;
		this.fimInstancia = fimInstancia;
		this.usuarioaIniciador = usuarioaIniciador;
		this.duracao = duracao;
	}
	
	public DadosTempoAtendimento(InstanciaProcesso instancia){
		this.caseId = instancia.getCaseId();
		this.processo = instancia.getNomeProcesso();
		this.criacaoInstancia = instancia.getInicio();
		this.fimInstancia = instancia.getFim();
		Usuario iniciador = instancia.getIniciadoPor();
		if (iniciador != null) {
			this.usuarioaIniciador = iniciador.getNome() + " " + iniciador.getSobrenome();
		}
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getProcesso() {
		return processo;
	}

	public void setProcesso(String processo) {
		this.processo = processo;
	}

	public Date getCriacaoInstancia() {
		return criacaoInstancia;
	}

	public void setCriacaoInstancia(Date criacaoInstancia) {
		this.criacaoInstancia = criacaoInstancia;
	}

	public Date getFimInstancia() {
		return fimInstancia;
	}

	public void setFimInstancia(Date fimInstancia) {
		this.fimInstancia = fimInstancia;
	}

	public String getUsuarioaIniciador() {
		return usuarioaIniciador;
	}

	public void setUsuarioaIniciador(String usuarioaIniciador) {
		this.usuarioaIniciador = usuarioaIniciador;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	@Override
	public String toString() {
		return "DadosTempoAtendimento [caseId=" + caseId + ", processo="
				+ processo + ", criacaoInstancia=" + criacaoInstancia
				+ ", fimInstancia=" + fimInstancia + ", usuarioaIniciador="
				+ usuarioaIniciador + ", duracaoEmMilisegundos="
				+ duracao + "]";
	}	

}
