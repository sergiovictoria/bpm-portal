package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class Validator implements Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal value;
	private Boolean isValiad;
	
	
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public Boolean getIsValiad() {
		return isValiad;
	}
	public void setIsValiad(Boolean isValiad) {
		this.isValiad = isValiad;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	

}
