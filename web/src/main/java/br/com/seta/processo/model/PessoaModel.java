package br.com.seta.processo.model;

import java.io.Serializable;

import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.dto.Pessoa;

public class PessoaModel implements Serializable, IClusterable {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	private String nomeRazao;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getNomeRazao() {
		return nomeRazao;
	}

	public void setNomeRazao(String nomeRazao) {
		this.nomeRazao = nomeRazao;
	}

	@Override
	public String toString() {
		return "PessoaModel [pessoa=" + pessoa + ", razaoSocial=" + nomeRazao
				+ "]";
	}

}
