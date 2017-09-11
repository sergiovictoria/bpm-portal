package br.com.seta.processo.model;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.dto.Semana;


public class ScheduleModel implements  IClusterable, Serializable {

	private static final long serialVersionUID = 1L;
	private String cmbDiasSemanaInicio;
	private String cmbDiasSemanaFim;
	private String cmbHora;
	private String cmbMinuto;
	private String cmbSegundo;
	@Inject private TransacaoMongo transacaoMongo;
	
		
	
	public ScheduleModel() {
		CdiContainer.get().getNonContextualManager().inject(this);
		Semana semana = (Semana) transacaoMongo.findOne(Semana.class);
		this.cmbDiasSemanaInicio = semana.getInicioSemana();
		this.cmbDiasSemanaFim = semana.getFimSemana();
		this.cmbHora = semana.getHoras();
		this.cmbMinuto = semana.getMinutos();
		this.cmbSegundo = semana.getSegundos();	
	}

	
	public ScheduleModel(String cmbDiasSemanaInicio, String cmbDiasSemanaFim,
			String cmbHora, String cmbMinuto, String cmbSegundo) {
		super();
		this.cmbDiasSemanaInicio = cmbDiasSemanaInicio;
		this.cmbDiasSemanaFim = cmbDiasSemanaFim;
		this.cmbHora = cmbHora;
		this.cmbMinuto = cmbMinuto;
		this.cmbSegundo = cmbSegundo;
	}
	
	public String getCmbDiasSemanaInicio() {
		return cmbDiasSemanaInicio;
	}
	public void setCmbDiasSemanaInicio(String cmbDiasSemanaInicio) {
		this.cmbDiasSemanaInicio = cmbDiasSemanaInicio;
	}
	public String getCmbDiasSemanaFim() {
		return cmbDiasSemanaFim;
	}
	public void setCmbDiasSemanaFim(String cmbDiasSemanaFim) {
		this.cmbDiasSemanaFim = cmbDiasSemanaFim;
	}
	public String getCmbHora() {
		return cmbHora;
	}
	public void setCmbHora(String cmbHora) {
		this.cmbHora = cmbHora;
	}
	public String getCmbMinuto() {
		return cmbMinuto;
	}
	public void setCmbMinuto(String cmbMinuto) {
		this.cmbMinuto = cmbMinuto;
	}
	public String getCmbSegundo() {
		return cmbSegundo;
	}
	public void setCmbSegundo(String cmbSegundo) {
		this.cmbSegundo = cmbSegundo;
	}
	
	
	@Override
	public String toString() {
		return "ScheduleModel [cmbDiasSemanaInicio=" + cmbDiasSemanaInicio
				+ ", cmbDiasSemanaFim=" + cmbDiasSemanaFim + ", cmbHora="
				+ cmbHora + ", cmbMinuto=" + cmbMinuto + ", cmbSegundo="
				+ cmbSegundo + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((cmbDiasSemanaFim == null) ? 0 : cmbDiasSemanaFim.hashCode());
		result = prime
				* result
				+ ((cmbDiasSemanaInicio == null) ? 0 : cmbDiasSemanaInicio
						.hashCode());
		result = prime * result + ((cmbHora == null) ? 0 : cmbHora.hashCode());
		result = prime * result
				+ ((cmbMinuto == null) ? 0 : cmbMinuto.hashCode());
		result = prime * result
				+ ((cmbSegundo == null) ? 0 : cmbSegundo.hashCode());
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
		ScheduleModel other = (ScheduleModel) obj;
		if (cmbDiasSemanaFim == null) {
			if (other.cmbDiasSemanaFim != null)
				return false;
		} else if (!cmbDiasSemanaFim.equals(other.cmbDiasSemanaFim))
			return false;
		if (cmbDiasSemanaInicio == null) {
			if (other.cmbDiasSemanaInicio != null)
				return false;
		} else if (!cmbDiasSemanaInicio.equals(other.cmbDiasSemanaInicio))
			return false;
		if (cmbHora == null) {
			if (other.cmbHora != null)
				return false;
		} else if (!cmbHora.equals(other.cmbHora))
			return false;
		if (cmbMinuto == null) {
			if (other.cmbMinuto != null)
				return false;
		} else if (!cmbMinuto.equals(other.cmbMinuto))
			return false;
		if (cmbSegundo == null) {
			if (other.cmbSegundo != null)
				return false;
		} else if (!cmbSegundo.equals(other.cmbSegundo))
			return false;
		return true;
	}
	
	
	
	

}
