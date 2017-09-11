package br.com.seta.processo.dto;

public class TbbUsuariofornecedorDto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int idusuario;
	private Long nrseqfornecedor;
	private boolean snformularioenviado;

	public TbbUsuariofornecedorDto() {
	}

	public TbbUsuariofornecedorDto(int idusuario, Long nrseqfornecedor, boolean snformularioenviado) {
		this.idusuario = idusuario;
		this.nrseqfornecedor = nrseqfornecedor;
		this.snformularioenviado = snformularioenviado;
	}
	
	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	
	public Long getNrseqfornecedor() {
		return nrseqfornecedor;
	}

	public void setNrseqfornecedor(Long nrseqfornecedor) {
		this.nrseqfornecedor = nrseqfornecedor;
	}

	public boolean getSnformularioenviado() {
		return snformularioenviado;
	}

	public void setSnformularioenviado(boolean snformularioenviado) {
		this.snformularioenviado = snformularioenviado;
	}
}
