package br.com.seta.processo.relatorios;

import java.io.Serializable;
import java.util.Date;

/**
 * Dados do Relatório de Processos em Aberto
 * 
 * @author Hromenique Cezniowscki Leite Batista
 */
public class ProcessoEmAberto implements Serializable {

	private static final long serialVersionUID = 1L;

	private long caseId;
	private String nomeProcesso;
	private long idAtividade;
	private String nomeAtividade;
	private String estadoAtividade;
	private Date inicioInstancia;
	private Date inicioAtividade;
	private Date fimAtividade;
	private String duracao;
	private String ator;
	private String executadaPor;

	public ProcessoEmAberto(){
		
	}
	
	public ProcessoEmAberto(long caseId, String nomeProcesso, long idAtividade,
			String nomeAtividade, String estadoAtividade, Date inicioInstancia,
			Date inicioAtividade, Date fimAtividade,
			String duracao, String ator, String executadaPor) {
		this.caseId = caseId;
		this.nomeProcesso = nomeProcesso;
		this.idAtividade = idAtividade;
		this.nomeAtividade = nomeAtividade;
		this.estadoAtividade = estadoAtividade;
		this.inicioInstancia = inicioInstancia;
		this.inicioAtividade = inicioAtividade;
		this.fimAtividade = fimAtividade;
		this.duracao = duracao;
		this.ator = ator;
		this.executadaPor = executadaPor;
	}

	public long getCaseId() {
		return caseId;
	}

	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public long getIdAtividade() {
		return idAtividade;
	}

	public void setIdAtividade(long idAtividade) {
		this.idAtividade = idAtividade;
	}

	public String getEstadoAtividade() {
		return estadoAtividade;
	}

	public void setEstadoAtividade(String estadoAtividade) {
		this.estadoAtividade = estadoAtividade;
	}

	public Date getInicioInstancia() {
		return inicioInstancia;
	}

	public void setInicioInstancia(Date inicioInstancia) {
		this.inicioInstancia = inicioInstancia;
	}

	public Date getInicioAtividade() {
		return inicioAtividade;
	}

	public void setInicioAtividade(Date inicioAtividade) {
		this.inicioAtividade = inicioAtividade;
	}

	public Date getFimAtividade() {
		return fimAtividade;
	}

	public void setFimAtividade(Date fimAtividade) {
		this.fimAtividade = fimAtividade;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracaoAtividadeEmSegundos) {
		this.duracao = duracaoAtividadeEmSegundos;
	}

	public String getAtor() {
		return ator;
	}

	public void setAtor(String ator) {
		this.ator = ator;
	}

	public String getExecutadaPor() {
		return executadaPor;
	}

	public void setExecutadaPor(String executadaPor) {
		this.executadaPor = executadaPor;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	@Override
	public String toString() {
		return "ProcessoEmAberto [caseId=" + caseId + ", nomeProcesso="
				+ nomeProcesso + ", idAtividade=" + idAtividade
				+ ", nomeAtividade=" + nomeAtividade + ", estadoAtividade="
				+ estadoAtividade + ", inicioInstancia=" + inicioInstancia
				+ ", inicioAtividade=" + inicioAtividade + ", fimAtividade="
				+ fimAtividade + ", duracaoAtividadeEmSegundos="
				+ duracao + ", ator=" + ator
				+ ", executadaPor=" + executadaPor + "]";
	}

}
