package br.com.seta.processo.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "GE_FERIADO")
public class GeFeriado implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date dtaferiado;
	private String descricao;
	private String feriadofixo;
	private String uf;

	public GeFeriado() {
	}

	public GeFeriado(Date dtaferiado, String descricao, String feriadofixo) {
		this.dtaferiado = dtaferiado;
		this.descricao = descricao;
		this.feriadofixo = feriadofixo;
	}

	public GeFeriado(Date dtaferiado, String descricao, String feriadofixo,
			String uf) {
		this.dtaferiado = dtaferiado;
		this.descricao = descricao;
		this.feriadofixo = feriadofixo;
		this.uf = uf;
	}

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "DTAFERIADO", nullable = false, length = 7)
	public Date getDtaferiado() {
		return this.dtaferiado;
	}

	public void setDtaferiado(Date dtaferiado) {
		this.dtaferiado = dtaferiado;
	}

	@Column(name = "DESCRICAO", nullable = false, length = 40)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "FERIADOFIXO", nullable = false, length = 1)
	public String getFeriadofixo() {
		return this.feriadofixo;
	}

	public void setFeriadofixo(String feriadofixo) {
		this.feriadofixo = feriadofixo;
	}

	@Column(name = "UF", length = 2)
	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}
