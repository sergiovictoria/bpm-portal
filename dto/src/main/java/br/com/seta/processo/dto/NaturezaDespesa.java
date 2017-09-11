package br.com.seta.processo.dto;

import java.math.BigDecimal;
import java.util.Date;


public class NaturezaDespesa implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	
	private String historico;
    private BigDecimal numero_empresa;
    private String descricao_natureza;
    private String existe_itens_nota;
    private BigDecimal codhistorico;
    private BigDecimal cgc;
    private String codepecie;
    private String codmodelo;
    private String descricao_modelo;
    private String especie_nf;
    private BigDecimal empresa_matriz;
    private BigDecimal cgo;
//    private String status;
    private String processame;
	private long   processID;
	private long   processIDRoot;
	private long   idInstancia;
	private String nomeNaturezaDespesa;
	private java.util.Date dataExecucao;
	private Boolean selected = Boolean.FALSE;
	
	
	public NaturezaDespesa( ) {
	}
	
		
	
	public NaturezaDespesa(String historico, BigDecimal numero_empresa,
			String descricao_natureza, String existe_itens_nota,
			BigDecimal codhistorico, BigDecimal cgc, String codepecie,
			String codmodelo, String descricao_modelo, String especie_nf,
			BigDecimal empresa_matriz, BigDecimal cgo, String status,
			String processame, long processID, long processIDRoot,
			long idInstancia, String nomeNaturezaDespesa, Date dataExecucao,
			Boolean selected) {
		super();
		this.historico = historico;
		this.numero_empresa = numero_empresa;
		this.descricao_natureza = descricao_natureza;
		this.existe_itens_nota = existe_itens_nota;
		this.codhistorico = codhistorico;
		this.cgc = cgc;
		this.codepecie = codepecie;
		this.codmodelo = codmodelo;
		this.descricao_modelo = descricao_modelo;
		this.especie_nf = especie_nf;
		this.empresa_matriz = empresa_matriz;
		this.cgo = cgo;
//		this.status = status;
		this.processame = processame;
		this.processID = processID;
		this.processIDRoot = processIDRoot;
		this.idInstancia = idInstancia;
		this.nomeNaturezaDespesa = nomeNaturezaDespesa;
		this.dataExecucao = dataExecucao;
		this.selected = selected;
	}
	
	
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}
	public BigDecimal getNumero_empresa() {
		return numero_empresa;
	}
	public void setNumero_empresa(BigDecimal numero_empresa) {
		this.numero_empresa = numero_empresa;
	}
	public String getDescricao_natureza() {
		return descricao_natureza;
	}
	public void setDescricao_natureza(String descricao_natureza) {
		this.descricao_natureza = descricao_natureza;
	}
	public String getExiste_itens_nota() {
		return existe_itens_nota;
	}
	public void setExiste_itens_nota(String existe_itens_nota) {
		this.existe_itens_nota = existe_itens_nota;
	}
	public BigDecimal getCodhistorico() {
		return codhistorico;
	}
	public void setCodhistorico(BigDecimal codhistorico) {
		this.codhistorico = codhistorico;
	}
	public BigDecimal getCgc() {
		return cgc;
	}
	public void setCgc(BigDecimal cgc) {
		this.cgc = cgc;
	}
	public String getCodepecie() {
		return codepecie;
	}
	public void setCodepecie(String codepecie) {
		this.codepecie = codepecie;
	}
	public String getCodmodelo() {
		return codmodelo;
	}
	public void setCodmodelo(String codmodelo) {
		this.codmodelo = codmodelo;
	}
	public String getDescricao_modelo() {
		return descricao_modelo;
	}
	public void setDescricao_modelo(String descricao_modelo) {
		this.descricao_modelo = descricao_modelo;
	}
	public String getEspecie_nf() {
		return especie_nf;
	}
	public void setEspecie_nf(String especie_nf) {
		this.especie_nf = especie_nf;
	}
	public BigDecimal getEmpresa_matriz() {
		return empresa_matriz;
	}
	public void setEmpresa_matriz(BigDecimal empresa_matriz) {
		this.empresa_matriz = empresa_matriz;
	}
	public BigDecimal getCgo() {
		return cgo;
	}
	public void setCgo(BigDecimal cgo) {
		this.cgo = cgo;
	}
	public String getProcessame() {
		return processame;
	}
	public void setProcessame(String processame) {
		this.processame = processame;
	}
	public long getProcessID() {
		return processID;
	}
	public void setProcessID(long processID) {
		this.processID = processID;
	}
	public long getProcessIDRoot() {
		return processIDRoot;
	}
	public void setProcessIDRoot(long processIDRoot) {
		this.processIDRoot = processIDRoot;
	}
	public long getIdInstancia() {
		return idInstancia;
	}
	public void setIdInstancia(long idInstancia) {
		this.idInstancia = idInstancia;
	}
	public String getNomeNaturezaDespesa() {
		return nomeNaturezaDespesa;
	}
	public void setNomeNaturezaDespesa(String nomeNaturezaDespesa) {
		this.nomeNaturezaDespesa = nomeNaturezaDespesa;
	}
	public java.util.Date getDataExecucao() {
		return dataExecucao;
	}
	public void setDataExecucao(java.util.Date dataExecucao) {
		this.dataExecucao = dataExecucao;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}



	@Override
	public String toString() {
		return "NaturezaDespesa [historico=" + historico + ", numero_empresa="
				+ numero_empresa + ", descricao_natureza=" + descricao_natureza
				+ ", existe_itens_nota=" + existe_itens_nota
				+ ", codhistorico=" + codhistorico + ", cgc=" + cgc
				+ ", codepecie=" + codepecie + ", codmodelo=" + codmodelo
				+ ", descricao_modelo=" + descricao_modelo + ", especie_nf="
				+ especie_nf + ", empresa_matriz=" + empresa_matriz + ", cgo="
				+ cgo + ", processame=" + processame + ", processID="
				+ processID + ", processIDRoot=" + processIDRoot
				+ ", idInstancia=" + idInstancia + ", nomeNaturezaDespesa="
				+ nomeNaturezaDespesa + ", dataExecucao=" + dataExecucao
				+ ", selected=" + selected + "]";
	}



}
