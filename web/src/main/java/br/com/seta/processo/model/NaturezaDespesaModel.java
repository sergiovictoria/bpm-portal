package br.com.seta.processo.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.wicket.util.io.IClusterable;

import br.com.seta.processo.dto.NaturezaDespesa;

public class NaturezaDespesaModel implements IClusterable, Serializable {
	

	private static final long serialVersionUID = 1L;
	private Boolean IsExisteIntes = false;
	private String descricaoNatureza;
	private BigDecimal codigoNatureza;
	private String historico;
	private NaturezaDespesa naturezaDespesaSelected;
	private String natDespesaSelecionada;
	private BigDecimal codhistorico;
	
		
		
	
	public String getDescricaoNatureza() {
		return descricaoNatureza;
	}
	public void setDescricaoNatureza(String descricaoNatureza) {
		this.descricaoNatureza = descricaoNatureza;
	}
	public BigDecimal getCodigoNatureza() {
		return codigoNatureza;
	}
	public void setCodigoNatureza(BigDecimal codigoNatureza) {
		this.codigoNatureza = codigoNatureza;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public NaturezaDespesa getNaturezaDespesaSelected() {
		return naturezaDespesaSelected;
	}
	public void setNaturezaDespesaSelected(NaturezaDespesa naturezaDespesaSelected) {
		this.naturezaDespesaSelected = naturezaDespesaSelected;
	}
	public String getNatDespesaSelecionada() {
		return natDespesaSelecionada;
	}
	public void setNatDespesaSelecionada(String natDespesaSelecionada) {
		this.natDespesaSelecionada = natDespesaSelecionada;
	}
	public Boolean getIsExisteIntes() {
		return IsExisteIntes;
	}
	public void setIsExisteIntes(Boolean isExisteIntes) {
		IsExisteIntes = isExisteIntes;
	}
	
	public BigDecimal getCodhistorico() {
		return codhistorico;
	}
	public void setCodhistorico(BigDecimal codhistorico) {
		this.codhistorico = codhistorico;
	}
	
	public void ExisteIntes(String existe_itens_nota) {
		if (existe_itens_nota.equals("N")) {
			this.IsExisteIntes = Boolean.FALSE;
		}else {
			this.IsExisteIntes = Boolean.TRUE;
		}
	}
	
}
