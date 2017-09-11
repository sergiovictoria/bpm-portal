package br.com.seta.processo.dto;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

/**
 * Mantém as variáveis utilizadas durantes as atividades dos processos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Embedded
public class VariavelProcesso implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private Object valor;
	private String classe;

	public VariavelProcesso(){
		
	}	
	
	public VariavelProcesso(String nome, Object valor, String classeVariavel) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.classe = classeVariavel;
	}
	
	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getClasse() {
		return classe;
	}
	
	public void setClasse(String classeVariavel) {
		this.classe = classeVariavel;
	}

	@Override
	public String toString() {
		return "Variavel [nome=" + nome + ", valor=" + valor
				+ ", classeVariavel=" + classe + "]";
	}

}
