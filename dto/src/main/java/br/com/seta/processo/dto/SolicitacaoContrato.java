package br.com.seta.processo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SolicitacaoContrato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String aprovadorFuncional;
	private String aprovadorHierarquico;
	private String areaSolicitante;
	private Boolean botaoRetornar;
	private String comentarioAprovFunc;
	private String comentarioAprovHierarq;
	private java.util.Date dataFimContrato;
	private String dataFimContratoText;
	private java.util.Date dataInicioContrato;
	private String dataInicioContratoText;
	private java.util.Date dataInicioProcesso;
	private java.util.Date dataSolicitacao;
	private String descricaoCompra;
	private Boolean detalhe;
	private String diretoria;
	private String emailsCSCJuridicoContrato;
	private String emailSolicitante;
	private Boolean existeContratoPendente;
	private String faturamentoCNPJ;
	private String formaPagamento;
	private String gerencia;
	private Long idFornecedor;
	private String idFornecedorText;
	private String mensagemFinal;
	private String mensagemNumContrato;
	private String nomeFornecedor;
	private String nomeSolicitante;
	private Long nroInstanciaLong;
	private Long numeroContrato;
	private String numeroIntencaoCompra;
	private String numeroInternoJuridico;
	private String numeroParcelas;
	private String numeroProcesso;
	private String observacoesJuridico;
	private String prazoPagamento;
	private Long quantidadeInstancia;
	private String resumoInstancia;
	private Long status;
	private String telefoneSolicitante;
	private String valorTotalCompra;
	private List<String> emailsCSCJuridicoContratos = new ArrayList<String>();
	private String emailsCSCJuridicoContratoList;
	private List<Long> listaContratoSelected = new ArrayList<Long>();
	
	public String getAprovadorFuncional() {
		return aprovadorFuncional;
	}
	public void setAprovadorFuncional(String aprovadorFuncional) {
		this.aprovadorFuncional = aprovadorFuncional;
	}
	public String getAprovadorHierarquico() {
		return aprovadorHierarquico;
	}
	public void setAprovadorHierarquico(String aprovadorHierarquico) {
		this.aprovadorHierarquico = aprovadorHierarquico;
	}
	public String getAreaSolicitante() {
		return areaSolicitante;
	}
	public void setAreaSolicitante(String areaSolicitante) {
		this.areaSolicitante = areaSolicitante;
	}
	public Boolean getBotaoRetornar() {
		return botaoRetornar;
	}
	public void setBotaoRetornar(Boolean botaoRetornar) {
		this.botaoRetornar = botaoRetornar;
	}
	public String getComentarioAprovFunc() {
		return comentarioAprovFunc;
	}
	public void setComentarioAprovFunc(String comentarioAprovFunc) {
		this.comentarioAprovFunc = comentarioAprovFunc;
	}
	public String getComentarioAprovHierarq() {
		return comentarioAprovHierarq;
	}
	public void setComentarioAprovHierarq(String comentarioAprovHierarq) {
		this.comentarioAprovHierarq = comentarioAprovHierarq;
	}
	public java.util.Date getDataFimContrato() {
		return dataFimContrato;
	}
	public void setDataFimContrato(java.util.Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}
	public String getDataFimContratoText() {
		return dataFimContratoText;
	}
	public void setDataFimContratoText(String dataFimContratoText) {
		this.dataFimContratoText = dataFimContratoText;
	}
	public java.util.Date getDataInicioContrato() {
		return dataInicioContrato;
	}
	public void setDataInicioContrato(java.util.Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}
	public String getDataInicioContratoText() {
		return dataInicioContratoText;
	}
	public void setDataInicioContratoText(String dataInicioContratoText) {
		this.dataInicioContratoText = dataInicioContratoText;
	}
	public java.util.Date getDataInicioProcesso() {
		return dataInicioProcesso;
	}
	public void setDataInicioProcesso(java.util.Date dataInicioProcesso) {
		this.dataInicioProcesso = dataInicioProcesso;
	}
	public java.util.Date getDataSolicitacao() {
		return dataSolicitacao;
	}
	public void setDataSolicitacao(java.util.Date dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}
	public String getDescricaoCompra() {
		return descricaoCompra;
	}
	public void setDescricaoCompra(String descricaoCompra) {
		this.descricaoCompra = descricaoCompra;
	}
	public Boolean getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(Boolean detalhe) {
		this.detalhe = detalhe;
	}
	public String getDiretoria() {
		return diretoria;
	}
	public void setDiretoria(String diretoria) {
		this.diretoria = diretoria;
	}
	public String getEmailsCSCJuridicoContrato() {
		return emailsCSCJuridicoContrato;
	}
	public void setEmailsCSCJuridicoContrato(String emailsCSCJuridicoContrato) {
		this.emailsCSCJuridicoContrato = emailsCSCJuridicoContrato;
	}
	public String getEmailSolicitante() {
		return emailSolicitante;
	}
	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}
	public Boolean getExisteContratoPendente() {
		return existeContratoPendente;
	}
	public void setExisteContratoPendente(Boolean existeContratoPendente) {
		this.existeContratoPendente = existeContratoPendente;
	}
	public String getFaturamentoCNPJ() {
		return faturamentoCNPJ;
	}
	public void setFaturamentoCNPJ(String faturamentoCNPJ) {
		this.faturamentoCNPJ = faturamentoCNPJ;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public String getGerencia() {
		return gerencia;
	}
	public void setGerencia(String gerencia) {
		this.gerencia = gerencia;
	}
	public Long getIdFornecedor() {
		return idFornecedor;
	}
	public void setIdFornecedor(Long idFornecedor) {
		this.idFornecedor = idFornecedor;
	}
	public String getIdFornecedorText() {
		return idFornecedorText;
	}
	public void setIdFornecedorText(String idFornecedorText) {
		this.idFornecedorText = idFornecedorText;
	}
	public String getMensagemFinal() {
		return mensagemFinal;
	}
	public void setMensagemFinal(String mensagemFinal) {
		this.mensagemFinal = mensagemFinal;
	}
	public String getMensagemNumContrato() {
		return mensagemNumContrato;
	}
	public void setMensagemNumContrato(String mensagemNumContrato) {
		this.mensagemNumContrato = mensagemNumContrato;
	}
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getNomeSolicitante() {
		return nomeSolicitante;
	}
	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}
	public Long getNroInstanciaLong() {
		return nroInstanciaLong;
	}
	public void setNroInstanciaLong(Long nroInstanciaLong) {
		this.nroInstanciaLong = nroInstanciaLong;
	}
	public Long getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(Long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public String getNumeroIntencaoCompra() {
		return numeroIntencaoCompra;
	}
	public void setNumeroIntencaoCompra(String numeroIntencaoCompra) {
		this.numeroIntencaoCompra = numeroIntencaoCompra;
	}
	public String getNumeroInternoJuridico() {
		return numeroInternoJuridico;
	}
	public void setNumeroInternoJuridico(String numeroInternoJuridico) {
		this.numeroInternoJuridico = numeroInternoJuridico;
	}
	public String getNumeroParcelas() {
		return numeroParcelas;
	}
	public void setNumeroParcelas(String numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
	public String getNumeroProcesso() {
		return numeroProcesso;
	}
	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}
	public String getObservacoesJuridico() {
		return observacoesJuridico;
	}
	public void setObservacoesJuridico(String observacoesJuridico) {
		this.observacoesJuridico = observacoesJuridico;
	}
	public String getPrazoPagamento() {
		return prazoPagamento;
	}
	public void setPrazoPagamento(String prazoPagamento) {
		this.prazoPagamento = prazoPagamento;
	}
	public Long getQuantidadeInstancia() {
		return quantidadeInstancia;
	}
	public void setQuantidadeInstancia(Long quantidadeInstancia) {
		this.quantidadeInstancia = quantidadeInstancia;
	}
	public String getResumoInstancia() {
		return resumoInstancia;
	}
	public void setResumoInstancia(String resumoInstancia) {
		this.resumoInstancia = resumoInstancia;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}
	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}
	public String getValorTotalCompra() {
		return valorTotalCompra;
	}
	public void setValorTotalCompra(String valorTotalCompra) {
		this.valorTotalCompra = valorTotalCompra;
	}
	public List<String> getEmailsCSCJuridicoContratos() {
		return emailsCSCJuridicoContratos;
	}
	public void setEmailsCSCJuridicoContratos(List<String> emailsCSCJuridicoContratos) {
		this.emailsCSCJuridicoContratos = emailsCSCJuridicoContratos;
	}
	public String getEmailsCSCJuridicoContratoList() {
		return emailsCSCJuridicoContratoList;
	}
	public void setEmailsCSCJuridicoContratoList(String emailsCSCJuridicoContratoList) {
		this.emailsCSCJuridicoContratoList = emailsCSCJuridicoContratoList;
	}
	public List<Long> getListaContratoSelected() {
		return listaContratoSelected;
	}
	public void setListaContratoSelected(List<Long> listaContratoSelected) {
		this.listaContratoSelected = listaContratoSelected;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aprovadorFuncional == null) ? 0 : aprovadorFuncional.hashCode());
		result = prime * result + ((aprovadorHierarquico == null) ? 0 : aprovadorHierarquico.hashCode());
		result = prime * result + ((areaSolicitante == null) ? 0 : areaSolicitante.hashCode());
		result = prime * result + ((botaoRetornar == null) ? 0 : botaoRetornar.hashCode());
		result = prime * result + ((comentarioAprovFunc == null) ? 0 : comentarioAprovFunc.hashCode());
		result = prime * result + ((comentarioAprovHierarq == null) ? 0 : comentarioAprovHierarq.hashCode());
		result = prime * result + ((dataFimContrato == null) ? 0 : dataFimContrato.hashCode());
		result = prime * result + ((dataFimContratoText == null) ? 0 : dataFimContratoText.hashCode());
		result = prime * result + ((dataInicioContrato == null) ? 0 : dataInicioContrato.hashCode());
		result = prime * result + ((dataInicioContratoText == null) ? 0 : dataInicioContratoText.hashCode());
		result = prime * result + ((dataInicioProcesso == null) ? 0 : dataInicioProcesso.hashCode());
		result = prime * result + ((dataSolicitacao == null) ? 0 : dataSolicitacao.hashCode());
		result = prime * result + ((descricaoCompra == null) ? 0 : descricaoCompra.hashCode());
		result = prime * result + ((detalhe == null) ? 0 : detalhe.hashCode());
		result = prime * result + ((diretoria == null) ? 0 : diretoria.hashCode());
		result = prime * result + ((emailSolicitante == null) ? 0 : emailSolicitante.hashCode());
		result = prime * result + ((emailsCSCJuridicoContrato == null) ? 0 : emailsCSCJuridicoContrato.hashCode());
		result = prime * result + ((emailsCSCJuridicoContratoList == null) ? 0 : emailsCSCJuridicoContratoList.hashCode());
		result = prime * result + ((emailsCSCJuridicoContratos == null) ? 0 : emailsCSCJuridicoContratos.hashCode());
		result = prime * result + ((existeContratoPendente == null) ? 0 : existeContratoPendente.hashCode());
		result = prime * result + ((faturamentoCNPJ == null) ? 0 : faturamentoCNPJ.hashCode());
		result = prime * result + ((formaPagamento == null) ? 0 : formaPagamento.hashCode());
		result = prime * result + ((gerencia == null) ? 0 : gerencia.hashCode());
		result = prime * result + ((idFornecedor == null) ? 0 : idFornecedor.hashCode());
		result = prime * result + ((idFornecedorText == null) ? 0 : idFornecedorText.hashCode());
		result = prime * result + ((listaContratoSelected == null) ? 0 : listaContratoSelected.hashCode());
		result = prime * result + ((mensagemFinal == null) ? 0 : mensagemFinal.hashCode());
		result = prime * result + ((mensagemNumContrato == null) ? 0 : mensagemNumContrato.hashCode());
		result = prime * result + ((nomeFornecedor == null) ? 0 : nomeFornecedor.hashCode());
		result = prime * result + ((nomeSolicitante == null) ? 0 : nomeSolicitante.hashCode());
		result = prime * result + ((nroInstanciaLong == null) ? 0 : nroInstanciaLong.hashCode());
		result = prime * result + ((numeroContrato == null) ? 0 : numeroContrato.hashCode());
		result = prime * result + ((numeroIntencaoCompra == null) ? 0 : numeroIntencaoCompra.hashCode());
		result = prime * result + ((numeroInternoJuridico == null) ? 0 : numeroInternoJuridico.hashCode());
		result = prime * result + ((numeroParcelas == null) ? 0 : numeroParcelas.hashCode());
		result = prime * result + ((numeroProcesso == null) ? 0 : numeroProcesso.hashCode());
		result = prime * result + ((observacoesJuridico == null) ? 0 : observacoesJuridico.hashCode());
		result = prime * result + ((prazoPagamento == null) ? 0 : prazoPagamento.hashCode());
		result = prime * result + ((quantidadeInstancia == null) ? 0 : quantidadeInstancia.hashCode());
		result = prime * result + ((resumoInstancia == null) ? 0 : resumoInstancia.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((telefoneSolicitante == null) ? 0 : telefoneSolicitante.hashCode());
		result = prime * result + ((valorTotalCompra == null) ? 0 : valorTotalCompra.hashCode());
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
		SolicitacaoContrato other = (SolicitacaoContrato) obj;
		if (aprovadorFuncional == null) {
			if (other.aprovadorFuncional != null)
				return false;
		} else if (!aprovadorFuncional.equals(other.aprovadorFuncional))
			return false;
		if (aprovadorHierarquico == null) {
			if (other.aprovadorHierarquico != null)
				return false;
		} else if (!aprovadorHierarquico.equals(other.aprovadorHierarquico))
			return false;
		if (areaSolicitante == null) {
			if (other.areaSolicitante != null)
				return false;
		} else if (!areaSolicitante.equals(other.areaSolicitante))
			return false;
		if (botaoRetornar == null) {
			if (other.botaoRetornar != null)
				return false;
		} else if (!botaoRetornar.equals(other.botaoRetornar))
			return false;
		if (comentarioAprovFunc == null) {
			if (other.comentarioAprovFunc != null)
				return false;
		} else if (!comentarioAprovFunc.equals(other.comentarioAprovFunc))
			return false;
		if (comentarioAprovHierarq == null) {
			if (other.comentarioAprovHierarq != null)
				return false;
		} else if (!comentarioAprovHierarq.equals(other.comentarioAprovHierarq))
			return false;
		if (dataFimContrato == null) {
			if (other.dataFimContrato != null)
				return false;
		} else if (!dataFimContrato.equals(other.dataFimContrato))
			return false;
		if (dataFimContratoText == null) {
			if (other.dataFimContratoText != null)
				return false;
		} else if (!dataFimContratoText.equals(other.dataFimContratoText))
			return false;
		if (dataInicioContrato == null) {
			if (other.dataInicioContrato != null)
				return false;
		} else if (!dataInicioContrato.equals(other.dataInicioContrato))
			return false;
		if (dataInicioContratoText == null) {
			if (other.dataInicioContratoText != null)
				return false;
		} else if (!dataInicioContratoText.equals(other.dataInicioContratoText))
			return false;
		if (dataInicioProcesso == null) {
			if (other.dataInicioProcesso != null)
				return false;
		} else if (!dataInicioProcesso.equals(other.dataInicioProcesso))
			return false;
		if (dataSolicitacao == null) {
			if (other.dataSolicitacao != null)
				return false;
		} else if (!dataSolicitacao.equals(other.dataSolicitacao))
			return false;
		if (descricaoCompra == null) {
			if (other.descricaoCompra != null)
				return false;
		} else if (!descricaoCompra.equals(other.descricaoCompra))
			return false;
		if (detalhe == null) {
			if (other.detalhe != null)
				return false;
		} else if (!detalhe.equals(other.detalhe))
			return false;
		if (diretoria == null) {
			if (other.diretoria != null)
				return false;
		} else if (!diretoria.equals(other.diretoria))
			return false;
		if (emailSolicitante == null) {
			if (other.emailSolicitante != null)
				return false;
		} else if (!emailSolicitante.equals(other.emailSolicitante))
			return false;
		if (emailsCSCJuridicoContrato == null) {
			if (other.emailsCSCJuridicoContrato != null)
				return false;
		} else if (!emailsCSCJuridicoContrato.equals(other.emailsCSCJuridicoContrato))
			return false;
		if (emailsCSCJuridicoContratoList == null) {
			if (other.emailsCSCJuridicoContratoList != null)
				return false;
		} else if (!emailsCSCJuridicoContratoList.equals(other.emailsCSCJuridicoContratoList))
			return false;
		if (emailsCSCJuridicoContratos == null) {
			if (other.emailsCSCJuridicoContratos != null)
				return false;
		} else if (!emailsCSCJuridicoContratos.equals(other.emailsCSCJuridicoContratos))
			return false;
		if (existeContratoPendente == null) {
			if (other.existeContratoPendente != null)
				return false;
		} else if (!existeContratoPendente.equals(other.existeContratoPendente))
			return false;
		if (faturamentoCNPJ == null) {
			if (other.faturamentoCNPJ != null)
				return false;
		} else if (!faturamentoCNPJ.equals(other.faturamentoCNPJ))
			return false;
		if (formaPagamento == null) {
			if (other.formaPagamento != null)
				return false;
		} else if (!formaPagamento.equals(other.formaPagamento))
			return false;
		if (gerencia == null) {
			if (other.gerencia != null)
				return false;
		} else if (!gerencia.equals(other.gerencia))
			return false;
		if (idFornecedor == null) {
			if (other.idFornecedor != null)
				return false;
		} else if (!idFornecedor.equals(other.idFornecedor))
			return false;
		if (idFornecedorText == null) {
			if (other.idFornecedorText != null)
				return false;
		} else if (!idFornecedorText.equals(other.idFornecedorText))
			return false;
		if (listaContratoSelected == null) {
			if (other.listaContratoSelected != null)
				return false;
		} else if (!listaContratoSelected.equals(other.listaContratoSelected))
			return false;
		if (mensagemFinal == null) {
			if (other.mensagemFinal != null)
				return false;
		} else if (!mensagemFinal.equals(other.mensagemFinal))
			return false;
		if (mensagemNumContrato == null) {
			if (other.mensagemNumContrato != null)
				return false;
		} else if (!mensagemNumContrato.equals(other.mensagemNumContrato))
			return false;
		if (nomeFornecedor == null) {
			if (other.nomeFornecedor != null)
				return false;
		} else if (!nomeFornecedor.equals(other.nomeFornecedor))
			return false;
		if (nomeSolicitante == null) {
			if (other.nomeSolicitante != null)
				return false;
		} else if (!nomeSolicitante.equals(other.nomeSolicitante))
			return false;
		if (nroInstanciaLong == null) {
			if (other.nroInstanciaLong != null)
				return false;
		} else if (!nroInstanciaLong.equals(other.nroInstanciaLong))
			return false;
		if (numeroContrato == null) {
			if (other.numeroContrato != null)
				return false;
		} else if (!numeroContrato.equals(other.numeroContrato))
			return false;
		if (numeroIntencaoCompra == null) {
			if (other.numeroIntencaoCompra != null)
				return false;
		} else if (!numeroIntencaoCompra.equals(other.numeroIntencaoCompra))
			return false;
		if (numeroInternoJuridico == null) {
			if (other.numeroInternoJuridico != null)
				return false;
		} else if (!numeroInternoJuridico.equals(other.numeroInternoJuridico))
			return false;
		if (numeroParcelas == null) {
			if (other.numeroParcelas != null)
				return false;
		} else if (!numeroParcelas.equals(other.numeroParcelas))
			return false;
		if (numeroProcesso == null) {
			if (other.numeroProcesso != null)
				return false;
		} else if (!numeroProcesso.equals(other.numeroProcesso))
			return false;
		if (observacoesJuridico == null) {
			if (other.observacoesJuridico != null)
				return false;
		} else if (!observacoesJuridico.equals(other.observacoesJuridico))
			return false;
		if (prazoPagamento == null) {
			if (other.prazoPagamento != null)
				return false;
		} else if (!prazoPagamento.equals(other.prazoPagamento))
			return false;
		if (quantidadeInstancia == null) {
			if (other.quantidadeInstancia != null)
				return false;
		} else if (!quantidadeInstancia.equals(other.quantidadeInstancia))
			return false;
		if (resumoInstancia == null) {
			if (other.resumoInstancia != null)
				return false;
		} else if (!resumoInstancia.equals(other.resumoInstancia))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (telefoneSolicitante == null) {
			if (other.telefoneSolicitante != null)
				return false;
		} else if (!telefoneSolicitante.equals(other.telefoneSolicitante))
			return false;
		if (valorTotalCompra == null) {
			if (other.valorTotalCompra != null)
				return false;
		} else if (!valorTotalCompra.equals(other.valorTotalCompra))
			return false;
		return true;
	}
	
	
	@Override
	public String toString() {
		return "SolicitacaoContrato [aprovadorFuncional=" + aprovadorFuncional + ", aprovadorHierarquico=" + aprovadorHierarquico + ", areaSolicitante="
				+ areaSolicitante + ", botaoRetornar=" + botaoRetornar + ", comentarioAprovFunc=" + comentarioAprovFunc + ", comentarioAprovHierarq="
				+ comentarioAprovHierarq + ", dataFimContrato=" + dataFimContrato + ", dataFimContratoText=" + dataFimContratoText
				+ ", dataInicioContrato=" + dataInicioContrato + ", dataInicioContratoText=" + dataInicioContratoText + ", dataInicioProcesso="
				+ dataInicioProcesso + ", dataSolicitacao=" + dataSolicitacao + ", descricaoCompra=" + descricaoCompra + ", detalhe=" + detalhe
				+ ", diretoria=" + diretoria + ", emailsCSCJuridicoContrato=" + emailsCSCJuridicoContrato + ", emailSolicitante=" + emailSolicitante
				+ ", existeContratoPendente=" + existeContratoPendente + ", faturamentoCNPJ=" + faturamentoCNPJ + ", formaPagamento=" + formaPagamento
				+ ", gerencia=" + gerencia + ", idFornecedor=" + idFornecedor + ", idFornecedorText=" + idFornecedorText + ", mensagemFinal="
				+ mensagemFinal + ", mensagemNumContrato=" + mensagemNumContrato + ", nomeFornecedor=" + nomeFornecedor + ", nomeSolicitante="
				+ nomeSolicitante + ", nroInstanciaLong=" + nroInstanciaLong + ", numeroContrato=" + numeroContrato + ", numeroIntencaoCompra="
				+ numeroIntencaoCompra + ", numeroInternoJuridico=" + numeroInternoJuridico + ", numeroParcelas=" + numeroParcelas + ", numeroProcesso="
				+ numeroProcesso + ", observacoesJuridico=" + observacoesJuridico + ", prazoPagamento=" + prazoPagamento + ", quantidadeInstancia="
				+ quantidadeInstancia + ", resumoInstancia=" + resumoInstancia + ", status=" + status + ", telefoneSolicitante=" + telefoneSolicitante
				+ ", valorTotalCompra=" + valorTotalCompra + ", emailsCSCJuridicoContratos=" + emailsCSCJuridicoContratos
				+ ", emailsCSCJuridicoContratoList=" + emailsCSCJuridicoContratoList + ", listaContratoSelected=" + listaContratoSelected + "]";
	}
	

	
}
