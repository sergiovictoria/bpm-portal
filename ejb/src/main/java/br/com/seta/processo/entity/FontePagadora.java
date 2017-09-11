package br.com.seta.processo.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Utilizado para mapear quais lojas (entidade GE_EMPRESA da CONSINCO) são fontes pagadoras
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Entity(noClassnameStored=true)
public class FontePagadora implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private ObjectId id;
	private int nroEmpresa;	
	private String razaoSocial;
	private String nomeReduzido;
	private long nroCGC;
	private byte digitoCGC;
	private boolean habilitado;	
	@Embedded("naturezasDespesa")
	private Set<CategoriaNaturezaDespesa> naturezasDespesa = new HashSet<CategoriaNaturezaDespesa>();	

	public FontePagadora(){
		
	}		

	public FontePagadora(int nroEmpresa, String razaoSocial, String nomeReduzido, long nroCGC, byte digitoCGC) {
		super();
		this.nroEmpresa = nroEmpresa;
		this.razaoSocial = razaoSocial;
		this.nomeReduzido = nomeReduzido;
		this.nroCGC = nroCGC;
		this.digitoCGC = digitoCGC;		
	}
	
	public void adicionaNaturezaDespesa(CategoriaNaturezaDespesa naturezaDespesa) {
		this.naturezasDespesa.add(naturezaDespesa);
	}
	
	public void removeNaturezaDespesa(CategoriaNaturezaDespesa naturezaDespesa){
		this.naturezasDespesa.remove(naturezaDespesa);
	}
	
	public void removeTodasNaturezasDespesas(){
		this.naturezasDespesa = new HashSet<CategoriaNaturezaDespesa>();
	}

	public int getNroEmpresa() {
		return nroEmpresa;
	}

	public void setNroEmpresa(int nroEmpresa) {
		this.nroEmpresa = nroEmpresa;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeReduzido() {
		return nomeReduzido;
	}

	public void setNomeReduzido(String nomeReduzido) {
		this.nomeReduzido = nomeReduzido;
	}

	public long getNroCGC() {
		return nroCGC;
	}

	public void setNroCGC(long nroCGC) {
		this.nroCGC = nroCGC;
	}

	public byte getDigitoCGC() {
		return digitoCGC;
	}

	public void setDigitoCGC(byte digitoCGC) {
		this.digitoCGC = digitoCGC;
	}

	public Set<CategoriaNaturezaDespesa> getNaturezasDespesa() {
		return naturezasDespesa;
	}

	public void setNaturezasDespesa(Set<CategoriaNaturezaDespesa> naturezasDespesa) {
		this.naturezasDespesa = naturezasDespesa;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return "FontePagadora [id=" + id + ", nroEmpresa=" + nroEmpresa
				+ ", razaoSocial=" + razaoSocial + ", nomeReduzido="
				+ nomeReduzido + ", nroCGC=" + nroCGC + ", digitoCGC="
				+ digitoCGC + ", naturezasDespesa=" + naturezasDespesa + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + digitoCGC;
		result = prime * result + (habilitado ? 1231 : 1237);
		result = prime * result
				+ ((nomeReduzido == null) ? 0 : nomeReduzido.hashCode());
		result = prime * result + (int) (nroCGC ^ (nroCGC >>> 32));
		result = prime * result + nroEmpresa;
		result = prime * result
				+ ((razaoSocial == null) ? 0 : razaoSocial.hashCode());
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
		FontePagadora other = (FontePagadora) obj;
		if (digitoCGC != other.digitoCGC)
			return false;
		if (habilitado != other.habilitado)
			return false;
		if (nomeReduzido == null) {
			if (other.nomeReduzido != null)
				return false;
		} else if (!nomeReduzido.equals(other.nomeReduzido))
			return false;
		if (nroCGC != other.nroCGC)
			return false;
		if (nroEmpresa != other.nroEmpresa)
			return false;
		if (razaoSocial == null) {
			if (other.razaoSocial != null)
				return false;
		} else if (!razaoSocial.equals(other.razaoSocial))
			return false;
		return true;
	}
						
}
