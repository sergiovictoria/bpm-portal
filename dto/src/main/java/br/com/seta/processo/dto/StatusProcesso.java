package br.com.seta.processo.dto;

import java.io.Serializable;

/**
 * Representa o status de um determinado processo
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class StatusProcesso implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String status;

	public StatusProcesso() {

	}

	public StatusProcesso(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		StatusProcesso other = (StatusProcesso) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

}
