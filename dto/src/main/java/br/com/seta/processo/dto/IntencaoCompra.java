package br.com.seta.processo.dto;

import java.io.Serializable;

public class IntencaoCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tipoIntensao;
	private String areaSolicitante;
	private String nomeSolicitante;
	private String emailSolicitante;
	private String foneSolicitante;
	private String diretoria;
	private String gerencia;
	private String descricaoCompra;

	private String tipoDespesa;
	private String numeroIntencaoCompra;
	private String numeroRequisicao;
	private String localEntrega;
	private long codigoFornecedor;
	private String nomeFornecedor;

	public IntencaoCompra() {
	}

	/**
	 * @return the tipoIntensao
	 */
	public final String getTipoIntensao() {
		return tipoIntensao;
	}

	/**
	 * @param tipoIntensao
	 *            the tipoIntensao to set
	 */
	public final void setTipoIntensao(String tipoIntensao) {
		this.tipoIntensao = tipoIntensao;
	}

	/**
	 * @return the areaSolicitante
	 */
	public final String getAreaSolicitante() {
		return areaSolicitante;
	}

	/**
	 * @param areaSolicitante
	 *            the areaSolicitante to set
	 */
	public final void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}

	/**
	 * @return the nomeSolicitante
	 */
	public final String getNomeSolicitante() {
		return nomeSolicitante;
	}

	/**
	 * @param nomeSolicitante
	 *            the nomeSolicitante to set
	 */
	public final void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	/**
	 * @return the emailSolicitante
	 */
	public final String getEmailSolicitante() {
		return emailSolicitante;
	}

	/**
	 * @param emailSolicitante
	 *            the emailSolicitante to set
	 */
	public final void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}

	/**
	 * @return the foneSolicitante
	 */
	public final String getFoneSolicitante() {
		return foneSolicitante;
	}

	/**
	 * @param foneSolicitante
	 *            the foneSolicitante to set
	 */
	public final void setFoneSolicitante(String foneSolicitante) {
		this.foneSolicitante = foneSolicitante;
	}

	/**
	 * @return the diretoria
	 */
	public final String getDiretoria() {
		return diretoria;
	}

	/**
	 * @param diretoria
	 *            the diretoria to set
	 */
	public final void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}

	/**
	 * @return the gerencia
	 */
	public final String getGerencia() {
		return gerencia;
	}

	/**
	 * @param gerencia
	 *            the gerencia to set
	 */
	public final void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}

	/**
	 * @return the descricaoCompra
	 */
	public final String getDescricaoCompra() {
		return descricaoCompra;
	}

	/**
	 * @param descricaoCompra
	 *            the descricaoCompra to set
	 */
	public final void setDescricaoCompra(String descricaoCompra) {
		this.descricaoCompra = descricaoCompra;
	}

	/**
	 * @return the tipoDespesa
	 */
	public final String getTipoDespesa() {
		return tipoDespesa;
	}

	/**
	 * @param tipoDespesa
	 *            the tipoDespesa to set
	 */
	public final void setTipoDespesa(String tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}

	/**
	 * @return the numeroIntencaoCompra
	 */
	public final String getNumeroIntencaoCompra() {
		return numeroIntencaoCompra;
	}

	/**
	 * @param numeroIntencaoCompra
	 *            the numeroIntencaoCompra to set
	 */
	public final void setNumeroIntencaoCompra(String numeroIntencaoCompra) {
		this.numeroIntencaoCompra = numeroIntencaoCompra;
	}

	/**
	 * @return the numeroRequisicao
	 */
	public final String getNumeroRequisicao() {
		return numeroRequisicao;
	}

	/**
	 * @param numeroRequisicao
	 *            the numeroRequisicao to set
	 */
	public final void setNumeroRequisicao(String numeroRequisicao) {
		this.numeroRequisicao = numeroRequisicao;
	}

	/**
	 * @return the localEntrega
	 */
	public final String getLocalEntrega() {
		return localEntrega;
	}

	/**
	 * @param localEntrega
	 *            the localEntrega to set
	 */
	public final void setLocalEntrega(String localEntrega) {
		this.localEntrega = localEntrega;
	}

	/**
	 * @return the codigoFornecedor
	 */
	public final long getCodigoFornecedor() {
		return codigoFornecedor;
	}

	/**
	 * @param codigoFornecedor
	 *            the codigoFornecedor to set
	 */
	public final void setCodigoFornecedor(long codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	/**
	 * @return the nomeFornecedor
	 */
	public final String getNomeFornecedor() {
		return nomeFornecedor;
	}

	/**
	 * @param nomeFornecedor
	 *            the nomeFornecedor to set
	 */
	public final void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IntencaoCompra [");
		if (tipoIntensao != null) {
			builder.append("tipoIntensao=");
			builder.append(tipoIntensao);
			builder.append(", ");
		}
		if (areaSolicitante != null) {
			builder.append("areaSolicitante=");
			builder.append(areaSolicitante);
			builder.append(", ");
		}
		if (nomeSolicitante != null) {
			builder.append("nomeSolicitante=");
			builder.append(nomeSolicitante);
			builder.append(", ");
		}
		if (emailSolicitante != null) {
			builder.append("emailSolicitante=");
			builder.append(emailSolicitante);
			builder.append(", ");
		}
		if (foneSolicitante != null) {
			builder.append("foneSolicitante=");
			builder.append(foneSolicitante);
			builder.append(", ");
		}
		if (diretoria != null) {
			builder.append("diretoria=");
			builder.append(diretoria);
			builder.append(", ");
		}
		if (gerencia != null) {
			builder.append("gerencia=");
			builder.append(gerencia);
			builder.append(", ");
		}
		if (descricaoCompra != null) {
			builder.append("descricaoCompra=");
			builder.append(descricaoCompra);
			builder.append(", ");
		}
		if (tipoDespesa != null) {
			builder.append("tipoDespesa=");
			builder.append(tipoDespesa);
			builder.append(", ");
		}
		if (numeroIntencaoCompra != null) {
			builder.append("numeroIntencaoCompra=");
			builder.append(numeroIntencaoCompra);
			builder.append(", ");
		}
		if (numeroRequisicao != null) {
			builder.append("numeroRequisicao=");
			builder.append(numeroRequisicao);
			builder.append(", ");
		}
		if (localEntrega != null) {
			builder.append("localEntrega=");
			builder.append(localEntrega);
			builder.append(", ");
		}
		builder.append("codigoFornecedor=");
		builder.append(codigoFornecedor);
		builder.append(", ");
		if (nomeFornecedor != null) {
			builder.append("nomeFornecedor=");
			builder.append(nomeFornecedor);
		}
		builder.append("]");
		return builder.toString();
	}

}
