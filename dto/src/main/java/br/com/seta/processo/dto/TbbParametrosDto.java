package br.com.seta.processo.dto;


public class TbbParametrosDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private int idparametro;
	private String descricao;
	private String valor;

	public TbbParametrosDto() {
	}

	public TbbParametrosDto(int idparametro, String descricao, String valor) {
		this.idparametro = idparametro;
		this.descricao = descricao;
		this.valor= valor;
	}

	public int getIdparametro() {
		return idparametro;
	}

	public void setIdparametro(int idparametro) {
		this.idparametro = idparametro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}


}
