package br.com.seta.processo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class FormularioProdutoTransfer implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	//#######################################
	//DADOS DO PRODUTO
	//#######################################
	@Size(max=60, message="A descrição do produto não pode exceder 60 caracteres.")
	private String descCompleta;
	@Size(max=14, message="O código do produto não pode exceder 14 caracteres.")
	private String codProduto;
	private String descReduzida;
	private String paisOrigem;  
	private String simplesNacional;
	private byte[] uploadfile;
	private String origemProduto;
	
	//#######################################
	//EMBALAGEM
	//#######################################
	@NotNull(message="A quantidade da unidade é obrigatorio.")
	@NotEmpty(message="A quantidade da unidade é obrigatorio.")
	@Size(max=12, message="A quantidade no grupo de unidade não pode ultrapassar os 12 dígitos")
	private String quantidadeUnidade;
	
	@NotNull(message="Selecione uma embalagem para a unidade.")
	@Size(max=2)
	private String embalagemUnidade;
	
	private String eanUnidade;
	
	@NotNull(message="O peso líquido da unidade é obrigatorio.")
	@NotEmpty(message="O peso líquido da unidade é obrigatorio.")
	@Size(max=7, message="O campo peso líquido não pode ultrapassar 7 caracteres.")
	private String pesoLiqUnidade;
	
	@NotNull(message="O peso bruto da unidade é obrigatorio.")
	@NotEmpty(message="O peso bruto da unidade é obrigatorio.")
	@Size(max=7, message="O campo peso bruto não pode ultrapassar 7 caracteres.")
	private String pesoBrutoUnidade;
	
	@NotNull(message="O comprimento da unidade é obrigatorio.")
	@NotEmpty(message="O comprimento da unidade é obrigatorio.")
	@Size(max=9, message="O campo comprimento não pode ultrapassar 9 caracteres.")
	private String comprimentoUnidade;
	
	@NotNull(message="A altura da unidade é obrigatorio.")
	@NotEmpty(message="A altura da unidade é obrigatorio.")
	@Size(max=9, message="O campo altura não pode ultrapassar 9 caracteres.")
	private String alturaUnidade;
	
	@NotNull(message="A largura da unidade é obrigatorio.")
	@NotEmpty(message="A largura da unidade é obrigatorio.")
	@Size(max=9, message="O campo largura não pode ultrapassar 9 caracteres.")
	private String larguraUnidade;
	
	private String quantidadeDisplay;
	private String embalagemDisplay;
	private String eanDisplay;
	private String pesoLiqDisplay;
	private String pesoBrutoDisplay;
	private String comprimentoDisplay;
	private String alturaDisplay;
	private String larguraDisplay;
	
	private String possuiEmbalagem;
	private String embalagemEmbalagem;
	private String eanEmbalagem;
	private String quantidadeEmbalagem;
	private String pesoBrutoEmbalagem;
	private String comprimentoEmbalagem;
	private String alturaEmbalagem;
	private String larguraEmbalagem;
	private String formaEntrega;
	
	//PALETIZAÇÃO E ESTOCAGEM
	private String produtoPaletizado;
	private String quantidadeLastroPaletizado;
	private String totalPalletPaletizado;
	private String pesoTotalPaletizado;
	private String comprimentoPaletizado;
	private String alturaPaletizado;
	private String larguraPaletizado;
	private String quantidadeAlturaPaletizado;
	
	private String perecivel;
	private String pesoVariavel;
	private String sazonal;
	private String cMaximaEstocagem;
	private String cMinimaEstocagem;
	private String validadeEstocagem;
	private String empilhamentoEstocagem;
	
	//DADOS NUTRICIONAIS
	private String quantidadePorcao;
	private String valorCalorico;
	private String proteina;
	private String gorduraSaturada;
	private String fibraAlimentar;
	private String sodio;
	private String carboidrato;
	private String gorduraTotal;
	private String gorduraTrans;
	private String unidadePorcao;
	
	//DADOS FISCAIS
	private String cnm;
	private String descricaoNcm;
	private String aliquotaIpi;
	private String aliquotaCofins;
	private String aliquotaPis;
	private String pauta;
	private String ivaSt;
	private String incentivoFiscal;
	private String incentivoFiscalQual;
	private String fundamentacao;
	private String tributacaoIcms; 
	private String alicotaIcms; 
	private String reducaoIcms; 
	
	
	
	public String getDescCompleta() {
		return descCompleta;
	}
	public void setDescCompleta(String descCompleta) {
		this.descCompleta = descCompleta;
	}
	public String getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(String codProduto) {
		this.codProduto = codProduto;
	}
	public String getDescReduzida() {
		return descReduzida;
	}
	public void setDescReduzida(String descReduzida) {
		this.descReduzida = descReduzida;
	}
	public String getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public String getSimplesNacional() {
		return simplesNacional;
	}
	public void setSimplesNacional(String simplesNacional) {
		this.simplesNacional = simplesNacional;
	}
	public byte[] getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(byte[] uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getQuantidadeUnidade() {
		return quantidadeUnidade;
	}
	public void setQuantidadeUnidade(String quantidadeUnidade) {
		this.quantidadeUnidade = quantidadeUnidade;
	}
	public String getEanUnidade() {
		return eanUnidade;
	}
	public void setEanUnidade(String eanUnidade) {
		this.eanUnidade = eanUnidade;
	}
	public String getPesoLiqUnidade() {
		return pesoLiqUnidade;
	}
	public void setPesoLiqUnidade(String pesoLiqUnidade) {
		this.pesoLiqUnidade = pesoLiqUnidade;
	}
	public String getPesoBrutoUnidade() {
		return pesoBrutoUnidade;
	}
	public void setPesoBrutoUnidade(String pesoBrutoUnidade) {
		this.pesoBrutoUnidade = pesoBrutoUnidade;
	}
	public String getComprimentoUnidade() {
		return comprimentoUnidade;
	}
	public void setComprimentoUnidade(String comprimentoUnidade) {
		this.comprimentoUnidade = comprimentoUnidade;
	}
	public String getAlturaUnidade() {
		return alturaUnidade;
	}
	public void setAlturaUnidade(String alturaUnidade) {
		this.alturaUnidade = alturaUnidade;
	}
	public String getLarguraUnidade() {
		return larguraUnidade;
	}
	public void setLarguraUnidade(String larguraUnidade) {
		this.larguraUnidade = larguraUnidade;
	}
	public String getQuantidadeDisplay() {
		return quantidadeDisplay;
	}
	public void setQuantidadeDisplay(String quantidadeDisplay) {
		this.quantidadeDisplay = quantidadeDisplay;
	}
	public String getEanDisplay() {
		return eanDisplay;
	}
	public void setEanDisplay(String eanDisplay) {
		this.eanDisplay = eanDisplay;
	}
	public String getPesoLiqDisplay() {
		return pesoLiqDisplay;
	}
	public void setPesoLiqDisplay(String pesoLiqDisplay) {
		this.pesoLiqDisplay = pesoLiqDisplay;
	}
	public String getPesoBrutoDisplay() {
		return pesoBrutoDisplay;
	}
	public void setPesoBrutoDisplay(String pesoBrutoDisplay) {
		this.pesoBrutoDisplay = pesoBrutoDisplay;
	}
	public String getComprimentoDisplay() {
		return comprimentoDisplay;
	}
	public void setComprimentoDisplay(String comprimentoDisplay) {
		this.comprimentoDisplay = comprimentoDisplay;
	}
	public String getAlturaDisplay() {
		return alturaDisplay;
	}
	public void setAlturaDisplay(String alturaDisplay) {
		this.alturaDisplay = alturaDisplay;
	}
	public String getLarguraDisplay() {
		return larguraDisplay;
	}
	public void setLarguraDisplay(String larguraDisplay) {
		this.larguraDisplay = larguraDisplay;
	}
	public String getPossuiEmbalagem() {
		return possuiEmbalagem;
	}
	public void setPossuiEmbalagem(String possuiEmbalagem) {
		this.possuiEmbalagem = possuiEmbalagem;
	}
	public String getEanEmbalagem() {
		return eanEmbalagem;
	}
	public void setEanEmbalagem(String eanEmbalagem) {
		this.eanEmbalagem = eanEmbalagem;
	}
	public String getQuantidadeEmbalagem() {
		return quantidadeEmbalagem;
	}
	public void setQuantidadeEmbalagem(String quantidadeEmbalagem) {
		this.quantidadeEmbalagem = quantidadeEmbalagem;
	}
	public String getPesoBrutoEmbalagem() {
		return pesoBrutoEmbalagem;
	}
	public void setPesoBrutoEmbalagem(String pesoBrutoEmbalagem) {
		this.pesoBrutoEmbalagem = pesoBrutoEmbalagem;
	}
	public String getComprimentoEmbalagem() {
		return comprimentoEmbalagem;
	}
	public void setComprimentoEmbalagem(String comprimentoEmbalagem) {
		this.comprimentoEmbalagem = comprimentoEmbalagem;
	}
	public String getAlturaEmbalagem() {
		return alturaEmbalagem;
	}
	public void setAlturaEmbalagem(String alturaEmbalagem) {
		this.alturaEmbalagem = alturaEmbalagem;
	}
	public String getLarguraEmbalagem() {
		return larguraEmbalagem;
	}
	public void setLarguraEmbalagem(String larguraEmbalagem) {
		this.larguraEmbalagem = larguraEmbalagem;
	}
	public String getProdutoPaletizado() {
		return produtoPaletizado;
	}
	public void setProdutoPaletizado(String produtoPaletizado) {
		this.produtoPaletizado = produtoPaletizado;
	}
	public String getQuantidadeLastroPaletizado() {
		return quantidadeLastroPaletizado;
	}
	public void setQuantidadeLastroPaletizado(String quantidadeLastroPaletizado) {
		this.quantidadeLastroPaletizado = quantidadeLastroPaletizado;
	}
	public String getTotalPalletPaletizado() {
		return totalPalletPaletizado;
	}
	public void setTotalPalletPaletizado(String totalPalletPaletizado) {
		this.totalPalletPaletizado = totalPalletPaletizado;
	}
	public String getPesoTotalPaletizado() {
		return pesoTotalPaletizado;
	}
	public void setPesoTotalPaletizado(String pesoTotalPaletizado) {
		this.pesoTotalPaletizado = pesoTotalPaletizado;
	}
	public String getComprimentoPaletizado() {
		return comprimentoPaletizado;
	}
	public void setComprimentoPaletizado(String comprimentoPaletizado) {
		this.comprimentoPaletizado = comprimentoPaletizado;
	}
	public String getAlturaPaletizado() {
		return alturaPaletizado;
	}
	public void setAlturaPaletizado(String alturaPaletizado) {
		this.alturaPaletizado = alturaPaletizado;
	}
	public String getLarguraPaletizado() {
		return larguraPaletizado;
	}
	public void setLarguraPaletizado(String larguraPaletizado) {
		this.larguraPaletizado = larguraPaletizado;
	}
	public String getPerecivel() {
		return perecivel;
	}
	public void setPerecivel(String perecivel) {
		this.perecivel = perecivel;
	}
	public String getPesoVariavel() {
		return pesoVariavel;
	}
	public void setPesoVariavel(String pesoVariavel) {
		this.pesoVariavel = pesoVariavel;
	}
	public String getSazonal() {
		return sazonal;
	}
	public void setSazonal(String sazonal) {
		this.sazonal = sazonal;
	}
	public String getcMaximaEstocagem() {
		return cMaximaEstocagem;
	}
	public void setcMaximaEstocagem(String cMaximaEstocagem) {
		this.cMaximaEstocagem = cMaximaEstocagem;
	}
	public String getcMinimaEstocagem() {
		return cMinimaEstocagem;
	}
	public void setcMinimaEstocagem(String cMinimaEstocagem) {
		this.cMinimaEstocagem = cMinimaEstocagem;
	}
	public String getValidadeEstocagem() {
		return validadeEstocagem;
	}
	public void setValidadeEstocagem(String validadeEstocagem) {
		this.validadeEstocagem = validadeEstocagem;
	}
	public String getEmpilhamentoEstocagem() {
		return empilhamentoEstocagem;
	}
	public void setEmpilhamentoEstocagem(String empilhamentoEstocagem) {
		this.empilhamentoEstocagem = empilhamentoEstocagem;
	}
	public String getQuantidadePorcao() {
		return quantidadePorcao;
	}
	public void setQuantidadePorcao(String quantidadePorcao) {
		this.quantidadePorcao = quantidadePorcao;
	}
	public String getValorCalorico() {
		return valorCalorico;
	}
	public void setValorCalorico(String valorCalorico) {
		this.valorCalorico = valorCalorico;
	}
	public String getProteina() {
		return proteina;
	}
	public void setProteina(String proteina) {
		this.proteina = proteina;
	}
	public String getGorduraSaturada() {
		return gorduraSaturada;
	}
	public void setGorduraSaturada(String gorduraSaturada) {
		this.gorduraSaturada = gorduraSaturada;
	}
	public String getFibraAlimentar() {
		return fibraAlimentar;
	}
	public void setFibraAlimentar(String fibraAlimentar) {
		this.fibraAlimentar = fibraAlimentar;
	}
	public String getSodio() {
		return sodio;
	}
	public void setSodio(String sodio) {
		this.sodio = sodio;
	}
	public String getCarboidrato() {
		return carboidrato;
	}
	public void setCarboidrato(String carboidrato) {
		this.carboidrato = carboidrato;
	}
	public String getGorduraTotal() {
		return gorduraTotal;
	}
	public void setGorduraTotal(String gorduraTotal) {
		this.gorduraTotal = gorduraTotal;
	}
	public String getGorduraTrans() {
		return gorduraTrans;
	}
	public void setGorduraTrans(String gorduraTrans) {
		this.gorduraTrans = gorduraTrans;
	}
	public String getCnm() {
		return cnm;
	}
	public void setCnm(String cnm) {
		this.cnm = cnm;
	}
	public String getDescricaoNcm() {
		return descricaoNcm;
	}
	public void setDescricaoNcm(String descricaoNcm) {
		this.descricaoNcm = descricaoNcm;
	}
	public String getAliquotaIpi() {
		return aliquotaIpi;
	}
	public void setAliquotaIpi(String aliquotaIpi) {
		this.aliquotaIpi = aliquotaIpi;
	}
	public String getAliquotaCofins() {
		return aliquotaCofins;
	}
	public void setAliquotaCofins(String aliquotaCofins) {
		this.aliquotaCofins = aliquotaCofins;
	}
	public String getAliquotaPis() {
		return aliquotaPis;
	}
	public void setAliquotaPis(String aliquotaPis) {
		this.aliquotaPis = aliquotaPis;
	}
	public String getPauta() {
		return pauta;
	}
	public void setPauta(String pauta) {
		this.pauta = pauta;
	}
	public String getIvaSt() {
		return ivaSt;
	}
	public void setIvaSt(String ivaSt) {
		this.ivaSt = ivaSt;
	}
	public String getIncentivoFiscal() {
		return incentivoFiscal;
	}
	public void setIncentivoFiscal(String incentivoFiscal) {
		this.incentivoFiscal = incentivoFiscal;
	}
	public String getFundamentacao() {
		return fundamentacao;
	}
	public void setFundamentacao(String fundamentacao) {
		this.fundamentacao = fundamentacao;
	}
	public String getEmbalagemUnidade() {
		return embalagemUnidade;
	}
	public void setEmbalagemUnidade(String embalagemUnidade) {
		this.embalagemUnidade = embalagemUnidade;
	}
	public String getEmbalagemDisplay() {
		return embalagemDisplay;
	}
	public void setEmbalagemDisplay(String embalagemDisplay) {
		this.embalagemDisplay = embalagemDisplay;
	}
	public String getEmbalagemEmbalagem() {
		return embalagemEmbalagem;
	}
	public void setEmbalagemEmbalagem(String embalagemEmbalagem) {
		this.embalagemEmbalagem = embalagemEmbalagem;
	}
	public String getUnidadePorcao() {
		return unidadePorcao;
	}
	public void setUnidadePorcao(String unidadePorcao) {
		this.unidadePorcao = unidadePorcao;
	}
	public String getTributacaoIcms() {
		return tributacaoIcms;
	}
	public void setTributacaoIcms(String tributacaoIcms) {
		this.tributacaoIcms = tributacaoIcms;
	}
	public String getAlicotaIcms() {
		return alicotaIcms;
	}
	public void setAlicotaIcms(String alicotaIcms) {
		this.alicotaIcms = alicotaIcms;
	}
	public String getReducaoIcms() {
		return reducaoIcms;
	}
	public void setReducaoIcms(String reducaoIcms) {
		this.reducaoIcms = reducaoIcms;
	}
	public String getQuantidadeAlturaPaletizado() {
		return quantidadeAlturaPaletizado;
	}
	public void setQuantidadeAlturaPaletizado(String quantidadeAlturaPaletizado) {
		this.quantidadeAlturaPaletizado = quantidadeAlturaPaletizado;
	}
	public String getOrigemProduto() {
		return origemProduto;
	}
	public void setOrigemProduto(String origemProduto) {
		this.origemProduto = origemProduto;
	}
	public String getFormaEntrega() {
		return formaEntrega;
	}
	public void setFormaEntrega(String formaEntrega) {
		this.formaEntrega = formaEntrega;
	}
	public String getIncentivoFiscalQual() {
		return incentivoFiscalQual;
	}
	public void setIncentivoFiscalQual(String incentivoFiscalQual) {
		this.incentivoFiscalQual = incentivoFiscalQual;
	}
}
