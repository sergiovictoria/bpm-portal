package br.com.seta.processo.entity;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;
/**
 * Utilizado para mapear quais são as categorias de dívidas pertecentes ao processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Embedded
public class CategoriaNaturezaDespesa implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int codHistorico;
	private String descricao;
	private boolean habilitado;
	private boolean chaveAcesso;
	private String nroplanilha;
	
	public CategoriaNaturezaDespesa(){
		
	}

	public CategoriaNaturezaDespesa(int id, String descricao, boolean habilitado) {
		super();
		this.codHistorico = id;
		this.descricao = descricao;
	}

	public int getCodHistorico() {
		return codHistorico;
	}

	public void setCodHistorico(int id) {
		this.codHistorico = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public boolean isChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(boolean chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public String getNroplanilha() {
		return nroplanilha;
	}

	public void setNroplanilha(String nroplanilha) {
		this.nroplanilha = nroplanilha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codHistorico;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaNaturezaDespesa other = (CategoriaNaturezaDespesa) obj;
		if (codHistorico != other.codHistorico)
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaNaturezaDespesa [codHistorico=" + codHistorico
				+ ", descricao=" + descricao + ", habilitado=" + habilitado + ", chaveAcesso=" + chaveAcesso + ", nroplanilha=" + nroplanilha
				+ "]";
	}	
}
