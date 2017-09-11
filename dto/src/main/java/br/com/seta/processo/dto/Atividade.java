package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigInteger;

import br.com.seta.processo.annotations.MongoColumm;
import br.com.seta.processo.annotations.MongoEntity;
import br.com.seta.processo.annotations.ReportColumn;
import br.com.seta.processo.classTypes.Classes;

@MongoEntity(collectionName="atividades")

public class Atividade implements Serializable {
		
	
	private static final long serialVersionUID = 1L;
	
	@ReportColumn(property="idProcesso",title="ID Instancia", colClass=Classes.BIGINTEGER)
	private BigInteger idProcesso;
	
	@ReportColumn(property="nomeProcesso",title="Nome do processo", colClass=Classes.STRING)
	private String nomeProcesso;
	
	@ReportColumn(property="descAtividade",title="Nome da atividade", colClass=Classes.STRING)
	private String descAtividade;
		
	@ReportColumn(property="stateName",title="Estado da atividade", colClass=Classes.STRING)
	private String stateName;
	
	private String kind;
	
	@ReportColumn(property="dataCriacaoInstancia",title="Data de criação da instância", colClass=Classes.STRING)
	private String dataCriacaoInstancia;
	
	@ReportColumn(property="dataInicioAtividade",title="Data inicio da atividade", colClass=Classes.STRING)
	private String dataInicioAtividade;
	
	@ReportColumn(property="dataFimEstimado",title="Data fim estimada", colClass=Classes.STRING)
	private String dataFimEstimado;
	
	@ReportColumn(property="lane",title="Área ou Raia", colClass=Classes.STRING)
	private String lane;

	@ReportColumn(property="situacao",title="Situação", colClass=Classes.STRING)
	private String situacao;
	
	
	
	public Atividade() {
	}
	
			
	@MongoColumm(property="idProcesso")
	public BigInteger getIdProcesso() {
		return idProcesso;
	}
	public void setIdProcesso(BigInteger idProcesso) {
		this.idProcesso = idProcesso;
	}
	
	@MongoColumm(property="nomeProcesso")
	public String getNomeProcesso() {
		return nomeProcesso;
	}
	public void setNomeProcesso(String nomeProcesso) {
		this.nomeProcesso = nomeProcesso;
	}
	
	@MongoColumm(property="descAtividade")
	public String getDescAtividade() {
		return descAtividade;
	}
	public void setDescAtividade(String descAtividade) {
		this.descAtividade = descAtividade;
	}
		
	@MongoColumm(property="stateName")
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String statename) {
		this.stateName = statename;
	}
	
	@MongoColumm(property="stateName")
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
	@MongoColumm(property="dataCriacaoInstancia")
	public String getDataCriacaoInstancia() {
		return dataCriacaoInstancia;
	}
	public void setDataCriacaoInstancia(String dataCriacaoInstancia) {
		this.dataCriacaoInstancia = dataCriacaoInstancia;
	}
	
	@MongoColumm(property="dataInicioAtividade")
	public String getDataInicioAtividade() {
		return dataInicioAtividade;
	}
	public void setDataInicioAtividade(String dataInicioAtividade) {
		this.dataInicioAtividade = dataInicioAtividade;
	}
	
	@MongoColumm(property="dataFimEstimado")
	public String getDataFimEstimado() {
		return dataFimEstimado;
	}
	public void setDataFimEstimado(String dataFimEstimado) {
		this.dataFimEstimado = dataFimEstimado;
	}
	
	@MongoColumm(property="lane")	
	public String getLane() {
		return lane;
	}
	public void setLane(String lane) {
		this.lane = lane;
	}
	
	@MongoColumm(property="situacao")
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
}
