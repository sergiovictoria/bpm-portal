package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import br.com.seta.processo.dtobonita.Usuario;

/**
 * Representa uma instância de um processo qualquer. Esta classe representa os dados históricos das instâncias executadas no Bonita BPM.
 * Seus dados serão persisitidos no MongoDB
 * 
 * @author Hromenique Cezniowscki Leite Batista
 */
@Entity(noClassnameStored=true)
public class InstanciaProcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long caseId;
	private Long processDefinitionId;
	private String nomeProcesso;
	private String versao;
	private Usuario iniciadoPor;
	private Date inicio;
	private Date fim;
	private String status;
	@Embedded
	private List<AtividadeProcesso> atividades;
	
	/**
	 * Classe que contém os nomes dos campos
	 * @author Hromenique Cezniowscki Leite Batista
	 *
	 */
	public static class Campos{
		public static final String ID = "_id";
		public static final String NOME_PROCESSO = "nomeProcesso";
		public static final String INICIO = "inicio";
		public static final String FIM = "fim";
		public static final String STATUS = "status";
		public static final String VERSAO = "versao";
		public static final String ATIVIDADES = "atividades";
		public static final String INICIADO_POR = "iniciadoPor";
	}

	public InstanciaProcesso(){
		this.atividades = new ArrayList<AtividadeProcesso>();
	}
	
	public InstanciaProcesso(Long caseId, Long processDefinitionId,
			String nomeProcesso, String versao, Usuario iniciadoPor,
			Date inicio, String status) {
		
		this();
		
		this.caseId = caseId;
		this.processDefinitionId = processDefinitionId;
		this.nomeProcesso = nomeProcesso;
		this.versao = versao;
		this.iniciadoPor = iniciadoPor;
		this.inicio = inicio;
		this.status = status;
	}
	
	public AtividadeProcesso getAtividade(int index){
		if(atividades == null)
			return null;
		
		return atividades.get(index);
	}
	
	public AtividadeProcesso primeiraAtividade(){
		return getAtividade(0);
	}
	
	public AtividadeProcesso ultimaAtividade(){
		if(atividades == null || atividades.size() == 0) 
			return null;
		
		int index = atividades.size() - 1;
		return atividades.get(index);
	}

	public void adiciona(AtividadeProcesso atividade){
		atividades.add(atividade);
	}
	
	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(Long processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getNomeProcesso() {
		return nomeProcesso;
	}

	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}

	public String getVersao() {
		return versao;
	}

	public void setVersao(String versao) {
		this.versao = versao;
	}

	public Usuario getIniciadoPor() {
		return iniciadoPor;
	}

	public void setIniciadoPor(Usuario iniciadoPor) {
		this.iniciadoPor = iniciadoPor;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AtividadeProcesso> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<AtividadeProcesso> atividades) {
		this.atividades = atividades;
	}

	@Override
	public String toString() {
		return "InstanciaProcesso [caseId=" + caseId + ", processDefinitionId="
				+ processDefinitionId + ", nomeProcesso=" + nomeProcesso
				+ ", versao=" + versao + ", iniciadoPor=" + iniciadoPor
				+ ", inicio=" + inicio + ", fim=" + fim + ", status=" + status
				+ ", atividades=" + atividades + "]";
	}

}