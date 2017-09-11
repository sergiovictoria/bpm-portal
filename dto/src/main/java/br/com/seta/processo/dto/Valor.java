package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

public class Valor implements IClusterable, Serializable {
	
	private static final long serialVersionUID = 1L;

	BigDecimal valorTotal;
	BigDecimal valor;
	BigDecimal vlrDescontos;
	BigDecimal vlrAcescimo;
	BigDecimal vlrOutrosDescontos;
	BigDecimal vlrParcela;
	java.util.Date dataRequsicao;
	java.util.Date dataProgramada;
	java.util.Date dataVencimento;
	java.util.Date dataDesdobramento;
	Integer linha;
	Integer parcela;
	
	
	public BigDecimal getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public BigDecimal getVlrDescontos() {
		return vlrDescontos;
	}
	public void setVlrDescontos(BigDecimal vlrDescontos) {
		this.vlrDescontos = vlrDescontos;
	}
	public BigDecimal getVlrAcescimo() {
		return vlrAcescimo;
	}
	public void setVlrAcescimo(BigDecimal vlrAcescimo) {
		this.vlrAcescimo = vlrAcescimo;
	}
	public BigDecimal getVlrOutrosDescontos() {
		return vlrOutrosDescontos;
	}
	public void setVlrOutrosDescontos(BigDecimal vlrOutrosDescontos) {
		this.vlrOutrosDescontos = vlrOutrosDescontos;
	}
	public java.util.Date getDataRequsicao() {
		return dataRequsicao;
	}
	public void setDataRequsicao(java.util.Date dataRequsicao) {
		this.dataRequsicao = dataRequsicao;
	}
	public java.util.Date getDataProgramada() {
		return dataProgramada;
	}
	public void setDataProgramada(java.util.Date dataProgramada) {
		this.dataProgramada = dataProgramada;
	}
	public java.util.Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(java.util.Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public java.util.Date getDataDesdobramento() {
		return dataDesdobramento;
	}
	public void setDataDesdobramento(java.util.Date dataDesdobramento) {
		this.dataDesdobramento = dataDesdobramento;
	}
	public Integer getLinha() {
		return linha;
	}
	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	public Integer getParcela() {
		return parcela;
	}
	public void setParcela(Integer parcela) {
		this.parcela = parcela;
	}
	
	
	
	
	public BigDecimal getVlrParcela() {
		return vlrParcela;
	}
	public void setVlrParcela(BigDecimal vlrParcela) {
		this.vlrParcela = vlrParcela;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((dataDesdobramento == null) ? 0 : dataDesdobramento
						.hashCode());
		result = prime * result
				+ ((dataProgramada == null) ? 0 : dataProgramada.hashCode());
		result = prime * result
				+ ((dataRequsicao == null) ? 0 : dataRequsicao.hashCode());
		result = prime * result
				+ ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
		result = prime * result + ((linha == null) ? 0 : linha.hashCode());
		result = prime * result + ((parcela == null) ? 0 : parcela.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		result = prime * result
				+ ((valorTotal == null) ? 0 : valorTotal.hashCode());
		result = prime * result
				+ ((vlrAcescimo == null) ? 0 : vlrAcescimo.hashCode());
		result = prime * result
				+ ((vlrDescontos == null) ? 0 : vlrDescontos.hashCode());
		result = prime
				* result
				+ ((vlrOutrosDescontos == null) ? 0 : vlrOutrosDescontos
						.hashCode());
		return result;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Valor))
			return false;
		Valor other = (Valor) obj;
		if (dataDesdobramento == null) {
			if (other.dataDesdobramento != null)
				return false;
		} else if (!dataDesdobramento.equals(other.dataDesdobramento))
			return false;
		if (dataProgramada == null) {
			if (other.dataProgramada != null)
				return false;
		} else if (!dataProgramada.equals(other.dataProgramada))
			return false;
		if (dataRequsicao == null) {
			if (other.dataRequsicao != null)
				return false;
		} else if (!dataRequsicao.equals(other.dataRequsicao))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		if (linha == null) {
			if (other.linha != null)
				return false;
		} else if (!linha.equals(other.linha))
			return false;
		if (parcela == null) {
			if (other.parcela != null)
				return false;
		} else if (!parcela.equals(other.parcela))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		if (valorTotal == null) {
			if (other.valorTotal != null)
				return false;
		} else if (!valorTotal.equals(other.valorTotal))
			return false;
		if (vlrAcescimo == null) {
			if (other.vlrAcescimo != null)
				return false;
		} else if (!vlrAcescimo.equals(other.vlrAcescimo))
			return false;
		if (vlrDescontos == null) {
			if (other.vlrDescontos != null)
				return false;
		} else if (!vlrDescontos.equals(other.vlrDescontos))
			return false;
		if (vlrOutrosDescontos == null) {
			if (other.vlrOutrosDescontos != null)
				return false;
		} else if (!vlrOutrosDescontos.equals(other.vlrOutrosDescontos))
			return false;
		return true;
	}
	
	
	
	@Override
	public String toString() {
		return "Valor [valorTotal=" + valorTotal + ", valor=" + valor
				+ ", vlrDescontos=" + vlrDescontos + ", vlrAcescimo="
				+ vlrAcescimo + ", vlrOutrosDescontos=" + vlrOutrosDescontos
				+ ", dataRequsicao=" + dataRequsicao + ", dataProgramada="
				+ dataProgramada + ", dataVencimento=" + dataVencimento
				+ ", dataDesdobramento=" + dataDesdobramento + ", linha="
				+ linha + ", parcela=" + parcela + "]";
	}
	
	
	
	
	

}
