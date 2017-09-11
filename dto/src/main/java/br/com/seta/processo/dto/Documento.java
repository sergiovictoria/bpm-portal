package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Representa um documento que será enviado do/para o Bonita BPM. <br>
 * O atributo caseId servirá de ligação entre o documento e a instância do processo.
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Entity
public class Documento implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private long caseId;
	private String descricao;
	private byte[] conteudo;
	private String nome;
	private String contentType;
	private String tipo;
	private long tamanho;
	private Date dataCriacao;
	private List<String> keys = new ArrayList<String>();
			
	public Documento(){
		this.id = new ObjectId().toString();
		this.dataCriacao = new Date();
	}
	
	public Documento(long caseId, byte[] conteudo) {
		this();
		this.caseId = caseId;
		this.conteudo = conteudo;
	}
	
	public Documento(long caseId, byte[] conteudo, String nome, String contentType, String tipo, long tamanho) {
		this();
		this.caseId = caseId;
		this.conteudo = conteudo;
		this.nome = nome;
		this.contentType = contentType;
		this.tipo = tipo;
		this.tamanho = tamanho;
	}	
	
	public void addKey(String key){
		this.keys.add(key);
	}

	public long getCaseId() {
		return caseId;
	}
	public void setCaseId(long caseId) {
		this.caseId = caseId;
	}	

	public byte[] getConteudo() {
		return conteudo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setConteudo(byte[] conteudo) {
		this.conteudo = conteudo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String type) {
		this.tipo = type;
	}

	public long getTamanho() {
		return tamanho;
	}

	public void setTamanho(long tamanho) {
		this.tamanho = tamanho;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<String> getKeys() {
		return keys;
	}

	public void setKeys(List<String> keys) {
		this.keys = keys;
	}

	@Override
	public String toString() {
		return "Documento [id=" + id + ", caseId=" + caseId + ", descricao="
				+ descricao + ", conteudo=" + Arrays.toString(conteudo)
				+ ", nome=" + nome + ", contentType=" + contentType + ", tipo="
				+ tipo + ", tamanho=" + tamanho + ", dataCriacao="
				+ dataCriacao + ", keys=" + keys + "]";
	}	
}
