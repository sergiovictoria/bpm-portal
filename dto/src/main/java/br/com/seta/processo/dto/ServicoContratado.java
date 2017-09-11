package br.com.seta.processo.dto;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity("servicocontratado")
public class ServicoContratado implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private long numeroContrato;
	private long codigoItem;
	private String descricaoItem;
	
	
	
	public ServicoContratado() {}
	
	
		
	public ServicoContratado(long numeroContrato, long codigoItem, String descricaoItem) {
		this.numeroContrato = numeroContrato;
		this.codigoItem = codigoItem;
		this.descricaoItem = descricaoItem;
	}
	
	
	public long getNumeroContrato() {
		return numeroContrato;
	}
	public void setNumeroContrato(long numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
	public long getCodigoItem() {
		return codigoItem;
	}
	public void setCodigoItem(long codigoItem) {
		this.codigoItem = codigoItem;
	}
	public String getDescricaoItem() {
		return descricaoItem;
	}
	public void setDescricaoItem(String descricaoItem) {
		this.descricaoItem = descricaoItem;
	}
	
	
	
	@Override
	public String toString() {
		return "ServicoContratado [numeroContrato=" + numeroContrato
				+ ", codigoItem=" + codigoItem + ", descricaoItem="
				+ descricaoItem + "]";
	}

		
}
