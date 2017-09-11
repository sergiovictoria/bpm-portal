package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;

import org.mongodb.morphia.annotations.Embedded;

import br.com.seta.processo.dtobonita.Usuario;

/**
 * Representa uma atividade de uma instância de processo
 * 
 * @author Hromenique Cezniowscki Leite Batista
 */
@Embedded
public class AtividadeProcesso implements  Serializable {
	private static final long serialVersionUID = 1L;

	public static class Campos{
		public static final String ATOR = "ator";
		public static final String EXECUTADO_POR = "executadoPor";
		public static final String FIM = "fim";
	}
	
	private Long id;
	private String nome;	
	private String nomeExibicao;	
	private String tipoAtividade;
	private Date inicio;
	private Date fim;
	private Date atribuidoEm;
	private Date dataDeTerminoEsperada;
	private Usuario atribuidoA;
	private Usuario executadoPor;
	private Usuario executadoPorSubstituto;
	private String prioridade;
	private String ator;	
	@Embedded
	private HashMap<String, VariavelProcesso> variaveis;

	public AtividadeProcesso(){
		this.variaveis = new HashMap<String, VariavelProcesso>();
	}

	public AtividadeProcesso(Long id, String nome, String nomeExibicao, String tipoAtividade, Date inicio) {
		this();
		this.id = id;
		this.nome = nome;
		this.nomeExibicao = nomeExibicao;
		this.tipoAtividade = tipoAtividade;
		this.inicio = inicio;
	}
	
	public AtividadeProcesso(Long id, String nome, String nomeExibicao,
			String tipoAtividade, Date inicio,
			Date dataDeTerminoEsperada, String prioridade, String ator) {
		this();
		this.id = id;
		this.nome = nome;
		this.nomeExibicao = nomeExibicao;
		this.tipoAtividade = tipoAtividade;
		this.inicio = inicio;
		this.dataDeTerminoEsperada = dataDeTerminoEsperada;
		this.prioridade = prioridade;
		this.ator = ator;
	}

	public AtividadeProcesso(Long id, String nome, String nomeExibicao,
			String tipoAtividade, Date inicio, Date fim,
			Date atribuidoEm, Date dataDeTerminoEsperada, Usuario executadoPor,
			Usuario executadoPorSubstituto, String prioridade, String ator,
			HashMap<String, VariavelProcesso> variaveis, Usuario atribuidoA) {
		this();
		this.id = id;
		this.nome = nome;
		this.nomeExibicao = nomeExibicao;
		this.tipoAtividade = tipoAtividade;
		this.inicio = inicio;
		this.fim = fim;
		this.atribuidoEm = atribuidoEm;
		this.dataDeTerminoEsperada = dataDeTerminoEsperada;
		this.executadoPor = executadoPor;
		this.executadoPorSubstituto = executadoPorSubstituto;
		this.prioridade = prioridade;
		this.ator = ator;
		this.variaveis = variaveis;
		this.atribuidoA = atribuidoA;
	}

	private VariavelProcesso getVariavel(String nome) {
		VariavelProcesso variavel = variaveis.get(nome);
		return variavel;
	}
	
	public Object getValorVariavel(String nome){
		VariavelProcesso variavel = getVariavel(nome);
		
		if(variavel == null) return null;
		
		return variavel.getValor();
	}	
	
	public String getClasseVariavel(String nome){
		VariavelProcesso variavel = getVariavel(nome);
		
		if(variavel == null) return null;
		
		return variavel.getClasse();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String getTipoAtividade() {
		return tipoAtividade;
	}

	public void setTipoAtividade(String tipoAtiviade) {
		this.tipoAtividade = tipoAtiviade;
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

	public Usuario getExecutadoPor() {
		return executadoPor;
	}

	public void setExecutadoPor(Usuario executadoPor) {
		this.executadoPor = executadoPor;
	}

	public HashMap<String, VariavelProcesso> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(HashMap<String, VariavelProcesso> variaveis) {
		this.variaveis = variaveis;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeExibicao() {
		return nomeExibicao;
	}

	public void setNomeExibicao(String nomeExibicao) {
		this.nomeExibicao = nomeExibicao;
	}

	public Date getAtribuidoEm() {
		return atribuidoEm;
	}

	public void setAtribuidoEm(Date atribuidoEm) {
		this.atribuidoEm = atribuidoEm;
	}

	public Date getDataDeTerminoEsperada() {
		return dataDeTerminoEsperada;
	}

	public void setDataDeTerminoEsperada(Date dataDeTerminoEsperada) {
		this.dataDeTerminoEsperada = dataDeTerminoEsperada;
	}

	public Usuario getExecutadoPorSubstituto() {
		return executadoPorSubstituto;
	}

	public void setExecutadoPorSubstituto(Usuario executadoPorSubstituto) {
		this.executadoPorSubstituto = executadoPorSubstituto;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}

	public String getAtor() {
		return ator;
	}

	public void setAtor(String ator) {
		this.ator = ator;
	}

	public Usuario getAtribuidoA() {
		return atribuidoA;
	}

	public void setAtribuidoA(Usuario atribuidoA) {
		this.atribuidoA = atribuidoA;
	}

	@Override
	public String toString() {
		return "AtividadeProcesso [id=" + id + ", nome=" + nome
				+ ", nomeExibicao=" + nomeExibicao + ", tipoAtividade="
				+ tipoAtividade + ", inicio=" + inicio + ", fim=" + fim
				+ ", atribuidoEm=" + atribuidoEm + ", dataDeTerminoEsperada="
				+ dataDeTerminoEsperada + ", atribuidoA=" + atribuidoA
				+ ", executadoPor=" + executadoPor
				+ ", executadoPorSubstituto=" + executadoPorSubstituto
				+ ", prioridade=" + prioridade + ", ator=" + ator
				+ ", variaveis=" + variaveis + "]";
	}
}
