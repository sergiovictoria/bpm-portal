package br.com.seta.processo.dto;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import br.com.seta.processo.validacoes.Depois;

@Entity
@Depois(datasIguaisValidas=true, getDate1="getDataFimContrato", getDate2="getDataInicioContrato", message="A data de início não pode ser maior que a data de fim do contrato")
public class Contrato implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@Min(value=1, message="O número do contrato é obrigatório")
	private long numeroContrato;
	private long numeroInstanciaOrigem;
	@Min(value=1, message="O número do jurídico é obrigatório")
	private long numeroInstanciaJuridico;
	private long statusContrato;
	@NotNull(message="A início do contrato é obrigatório")
	private Date dataInicioContrato;
	@NotNull(message="O fim do contrato é obrigatório")
	private Date dataFimContrato;
	private String numeroInternoJuridico;
	private String nomeFornecedor;
	private long codigoFornecedor;
	@NotNull(message="O contrato é obrigatório")
	private byte[] contrato;
	@NotBlank(message="As observações são obrigatórias")
	private String observacaoContrato;

	private boolean selecionado = false;
	private String status;
	private Long caseID;

	@Embedded
	private Set<ServicoContratado> servicoContratado = new HashSet<ServicoContratado>();

	public Contrato() {
		this.id = new ObjectId().toString();
	}

	public Contrato(long numeroContrato, long numeroInstanciaOrigem, long numeroInstanciaJuridico, long statusContrato,
			Date dataInicioContrato, Date dataFimContrato, String numeroInternoJuridico, String nomeFornecedor,
			long codigoFornecedor, Set<ServicoContratado> servicoContratado) {
		this.numeroContrato = numeroContrato;
		this.numeroInstanciaOrigem = numeroInstanciaOrigem;
		this.numeroInstanciaJuridico = numeroInstanciaJuridico;
		this.statusContrato = statusContrato;
		this.dataInicioContrato = dataInicioContrato;
		this.dataFimContrato = dataFimContrato;
		this.numeroInternoJuridico = numeroInternoJuridico;
		this.nomeFornecedor = nomeFornecedor;
		this.codigoFornecedor = codigoFornecedor;
		this.servicoContratado = servicoContratado;
	}

	/**
	 * @param id
	 * @param numeroContrato
	 * @param numeroInstanciaOrigem
	 * @param numeroInstanciaJuridico
	 * @param statusContrato
	 * @param dataInicioContrato
	 * @param dataFimContrato
	 * @param numeroInternoJuridico
	 * @param nomeFornecedor
	 * @param codigoFornecedor
	 * @param contrato
	 * @param observacaoContrato
	 * @param selecionado
	 * @param servicoContratado
	 */
	public Contrato(long numeroContrato, long numeroInstanciaOrigem, long numeroInstanciaJuridico,
			long statusContrato, Date dataInicioContrato, Date dataFimContrato, String numeroInternoJuridico,
			String nomeFornecedor, long codigoFornecedor, byte[] contrato, String observacaoContrato,
			boolean selecionado, Set<ServicoContratado> servicoContratado) {
		this.numeroContrato = numeroContrato;
		this.numeroInstanciaOrigem = numeroInstanciaOrigem;
		this.numeroInstanciaJuridico = numeroInstanciaJuridico;
		this.statusContrato = statusContrato;
		this.dataInicioContrato = dataInicioContrato;
		this.dataFimContrato = dataFimContrato;
		this.numeroInternoJuridico = numeroInternoJuridico;
		this.nomeFornecedor = nomeFornecedor;
		this.codigoFornecedor = codigoFornecedor;
		this.contrato = contrato;
		this.observacaoContrato = observacaoContrato;
		this.selecionado = selecionado;
		this.servicoContratado = servicoContratado;
	}

	public long getNumeroContrato() {
		return numeroContrato;
	}

	public void setNumeroContrato(long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public long getNumeroInstanciaOrigem() {
		return numeroInstanciaOrigem;
	}

	public void setNumeroInstanciaOrigem(long numeroInstanciaOrigem) {
		this.numeroInstanciaOrigem = numeroInstanciaOrigem;
	}

	public long getNumeroInstanciaJuridico() {
		return numeroInstanciaJuridico;
	}

	public void setNumeroInstanciaJuridico(long numeroInstanciaJuridico) {
		this.numeroInstanciaJuridico = numeroInstanciaJuridico;
	}

	public long getStatusContrato() {
		return statusContrato;
	}

	public void setStatusContrato(long statusContrato) {
		this.statusContrato = statusContrato;
	}

	public Date getDataInicioContrato() {
		return dataInicioContrato;
	}

	public void setDataInicioContrato(Date dataInicioContrato) {
		this.dataInicioContrato = dataInicioContrato;
	}

	public Date getDataFimContrato() {
		return dataFimContrato;
	}

	public void setDataFimContrato(Date dataFimContrato) {
		this.dataFimContrato = dataFimContrato;
	}

	public String getNumeroInternoJuridico() {
		return numeroInternoJuridico;
	}

	public void setNumeroInternoJuridico(String numeroInternoJuridico) {
		this.numeroInternoJuridico = numeroInternoJuridico;
	}

	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public long getCodigoFornecedor() {
		return codigoFornecedor;
	}

	public void setCodigoFornecedor(long codigoFornecedor) {
		this.codigoFornecedor = codigoFornecedor;
	}

	public Set<ServicoContratado> getServicoContratado() {
		return servicoContratado;
	}

	public void setServicoContratado(Set<ServicoContratado> servicoContratado) {
		this.servicoContratado = servicoContratado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public byte[] getContrato() {
		return contrato;
	}

	public void setContrato(byte[] contrato) {
		this.contrato = contrato;
	}

	public boolean isSelecionado() {
		return selecionado;
	}

	public void setSelecionado(boolean selecionado) {
		this.selecionado = selecionado;
	}

	public String getObservacaoContrato() {
		return observacaoContrato;
	}

	public void setObservacaoContrato(String observacaoContrato) {
		this.observacaoContrato = observacaoContrato;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Long getCaseID() {
		return caseID;
	}

	public void setCaseID(Long caseID) {
		this.caseID = caseID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contrato [");
		builder.append("numeroContrato=");
		builder.append(numeroContrato);
		builder.append(", numeroInstanciaOrigem=");
		builder.append(numeroInstanciaOrigem);
		builder.append(", numeroInstanciaJuridico=");
		builder.append(numeroInstanciaJuridico);
		builder.append(", statusContrato=");
		builder.append(statusContrato);
		builder.append(", ");
		if (dataInicioContrato != null) {
			builder.append("dataInicioContrato=");
			builder.append(dataInicioContrato);
			builder.append(", ");
		}
		if (dataFimContrato != null) {
			builder.append("dataFimContrato=");
			builder.append(dataFimContrato);
			builder.append(", ");
		}
		if (numeroInternoJuridico != null) {
			builder.append("numeroInternoJuridico=");
			builder.append(numeroInternoJuridico);
			builder.append(", ");
		}
		if (nomeFornecedor != null) {
			builder.append("nomeFornecedor=");
			builder.append(nomeFornecedor);
			builder.append(", ");
		}
		builder.append("codigoFornecedor=");
		builder.append(codigoFornecedor);
		builder.append(", ");
		if (contrato != null) {
			builder.append("contrato=");
			builder.append(Arrays.toString(contrato));
			builder.append(", ");
		}
		if (observacaoContrato != null) {
			builder.append("observacaoContrato=");
			builder.append(observacaoContrato);
			builder.append(", ");
		}
		builder.append("selecionado=");
		builder.append(selecionado);
		builder.append(", ");
		if (servicoContratado != null) {
			builder.append("servicoContratado=");
			builder.append(servicoContratado);
		}
		builder.append("]");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (codigoFornecedor ^ (codigoFornecedor >>> 32));
		result = prime * result + Arrays.hashCode(contrato);
		result = prime * result + ((dataFimContrato == null) ? 0 : dataFimContrato.hashCode());
		result = prime * result + ((dataInicioContrato == null) ? 0 : dataInicioContrato.hashCode());
		result = prime * result + ((nomeFornecedor == null) ? 0 : nomeFornecedor.hashCode());
		result = prime * result + (int) (numeroContrato ^ (numeroContrato >>> 32));
		result = prime * result + (int) (numeroInstanciaJuridico ^ (numeroInstanciaJuridico >>> 32));
		result = prime * result + (int) (numeroInstanciaOrigem ^ (numeroInstanciaOrigem >>> 32));
		result = prime * result + ((numeroInternoJuridico == null) ? 0 : numeroInternoJuridico.hashCode());
		result = prime * result + ((observacaoContrato == null) ? 0 : observacaoContrato.hashCode());
		result = prime * result + (selecionado ? 1231 : 1237);
		result = prime * result + ((servicoContratado == null) ? 0 : servicoContratado.hashCode());
		result = prime * result + (int) (statusContrato ^ (statusContrato >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (codigoFornecedor != other.codigoFornecedor)
			return false;
		if (!Arrays.equals(contrato, other.contrato))
			return false;
		if (dataFimContrato == null) {
			if (other.dataFimContrato != null)
				return false;
		} else if (!dataFimContrato.equals(other.dataFimContrato))
			return false;
		if (dataInicioContrato == null) {
			if (other.dataInicioContrato != null)
				return false;
		} else if (!dataInicioContrato.equals(other.dataInicioContrato))
			return false;
		if (nomeFornecedor == null) {
			if (other.nomeFornecedor != null)
				return false;
		} else if (!nomeFornecedor.equals(other.nomeFornecedor))
			return false;
		if (numeroContrato != other.numeroContrato)
			return false;
		if (numeroInstanciaJuridico != other.numeroInstanciaJuridico)
			return false;
		if (numeroInstanciaOrigem != other.numeroInstanciaOrigem)
			return false;
		if (numeroInternoJuridico == null) {
			if (other.numeroInternoJuridico != null)
				return false;
		} else if (!numeroInternoJuridico.equals(other.numeroInternoJuridico))
			return false;
		if (observacaoContrato == null) {
			if (other.observacaoContrato != null)
				return false;
		} else if (!observacaoContrato.equals(other.observacaoContrato))
			return false;
		if (selecionado != other.selecionado)
			return false;
		if (servicoContratado == null) {
			if (other.servicoContratado != null)
				return false;
		} else if (!servicoContratado.equals(other.servicoContratado))
			return false;
		if (statusContrato != other.statusContrato)
			return false;
		return true;
	}

}
