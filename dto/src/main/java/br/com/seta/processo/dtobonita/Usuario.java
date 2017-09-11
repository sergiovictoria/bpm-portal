package br.com.seta.processo.dtobonita;

import java.io.Serializable;

/**
 * Representa um usuário do Bonita BPM
 * 
 * @author Hromenique Cezniowscki Leite Batista  
 *
 */
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;  
	private String password;
	private String nome;
	private String sobrenome;
	private String nomeUsuario;
	private String titulo;
	private String cargo;
	private String senha;
	private Contatos contatosProfissionais;
	private Contatos contatosPessoais;

	public String getNomeCompleto(){
		return this.nome + " " + sobrenome;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Contatos getContatosProfissionais() {
		return contatosProfissionais;
	}

	public void setContatosProfissionais(Contatos contatosProfissionais) {
		this.contatosProfissionais = contatosProfissionais;
	}

	public Contatos getContatosPessoais() {
		return contatosPessoais;
	}

	public void setContatosPessoais(Contatos contatosPessoais) {
		this.contatosPessoais = contatosPessoais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Usuario other = (Usuario) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", password=" + password + ", nome="
				+ nome + ", sobrenome=" + sobrenome + ", nomeUsuario="
				+ nomeUsuario + ", titulo=" + titulo + ", cargo=" + cargo
				+ ", senha=" + senha + ", contatosProfissionais="
				+ contatosProfissionais + ", contatosPessoais="
				+ contatosPessoais + "]";
	}
}
