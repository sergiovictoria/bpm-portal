package br.com.seta.processo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;

import br.com.seta.processo.validacoes.NoMinimoUmPreenchido;
import br.com.seta.processo.validacoes.NotEquals;


@NotEquals.List({
	@NotEquals(primeiroCampo="quantidadeDisplay", segundoCampo="quantidadeEmbalagem", message="A quantidade do Display não pode ser igual à quantidade da Embalagem Master"),
	@NotEquals(primeiroCampo="quantidadeUnidade", segundoCampo="quantidadeEmbalagem", message="A quantidade da Unidade não pode ser igual à quantidade da Embalagem Master"),
	@NotEquals(primeiroCampo="quantidadeUnidade", segundoCampo="quantidadeDisplay", message="A quantidade da Unidade não pode ser igual à quantidade do Display")
	})
@NoMinimoUmPreenchido(value={"quantidadeUnidade", "embalagemUnidade", "quantidadeDisplay", "quantidadeEmbalagem"}, message="Deve-se preencher pelo menos um tipo de embalagem")
@Entity("FormularioProduto")
public class FormularioProduto implements Serializable  {

	private static final long serialVersionUID = 1;
	
	private String seqfornecedor;
	private String nomeFornecedor;
	
	//#######################################
	//DADOS DO PRODUTO
	//#######################################
	private String idFamilia;
	private String descFamilia;
	
	@NotBlank(message="Preencha uma descrição para o produto.")
	@Size(max=60, message="A descrição do produto não pode exceder 60 caracteres.")
	private String descCompleta;
	
	@Size(max=14, message="O código do produto não pode exceder 14 caracteres.")
	private String codProduto;
	
	@NotBlank(message="Preencha uma descrição reduzida para o produto.")
	private String descReduzida;
	
	private String paisOrigem;  
	private String simplesNacional;
	private byte[] uploadfile;
	private String origemProduto;
	
	//#######################################
	//EMBALAGEM
	//#######################################
	@Pattern(regexp="[0-9]*", message="A Quantidade da Unidade deve possuir apenas números.")
	@Size(max=4, message="A Quantidade da Unidade não pode ultrapassar 4 caracteres.")
	private String quantidadeUnidade;
	
	private String embalagemUnidade;	
	
	@Pattern(regexp="[0-9]{0,14}", message="O EAN da Unidade deve possuir apenas números.")
	@Size(max=14, message="O EAN da Unidade não pode ultrapassar 14 caracteres.")
	private String eanUnidade;	
	
	@Pattern(regexp="[0-9]{0,9}", message="O Peso Liq. da Unidade deve possuir apenas números.")
	@Size(max=9, message="O Peso Liq. da Unidade não pode ultrapassar 9 caracteres.")
	private String pesoLiqUnidade;	
	
	@Pattern(regexp="[0-9]{0,9}", message="O Peso Bruto da Unidade deve possuir apenas números.")
	@Size(max=9, message="O Peso Bruto da Unidade não pode ultrapassar 9 caracteres.")
	private String pesoBrutoUnidade;	
	
	@Pattern(regexp="[0-9]{0,9}", message="O Comprimento da Unidade deve possuir apenas números.")
	@Size(max=9, message="O Comprimento da Unidade não pode ultrapassar 9 caracteres.")
	private String comprimentoUnidade;	
	
	@Pattern(regexp="[0-9]{0,9}", message="A Altura da Unidade deve possuir apenas números.")
	@Size(max=9, message="A Altura da Unidade não pode ultrapassar 9 caracteres.")
	private String alturaUnidade;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Largura da Unidade deve possuir apenas números.")
	@Size(max=9, message="A Largura da Unidade não pode ultrapassar 9 caracteres.")
	private String larguraUnidade;
	
	@Pattern(regexp="[0-9]*", message="A Quantidade do Display deve possuir apenas números.")
	@Size(max=4, message="A Quantidade do Display não pode ultrapassar 4 caracteres.")
	private String quantidadeDisplay;
	
	private String embalagemDisplay;
	
	@Pattern(regexp="[0-9]{0,14}", message="O EAN do Display deve possuir apenas números.")
	@Size(max=14, message="O EAN do Display não pode ultrapassar 14 caracteres.")
	private String eanDisplay;
	
	@Pattern(regexp="[0-9]{0,9}", message="O Peso Liq. do Display deve possuir apenas números.")
	@Size(max=9, message="O Peso Liq. do Display não pode ultrapassar 9 caracteres.")
	private String pesoLiqDisplay;
	
	@Pattern(regexp="[0-9]{0,9}", message="O Peso Bruto do Display deve possuir apenas números.")
	@Size(max=9, message="O Peso Bruto do Display não pode ultrapassar 9 caracteres.")
	private String pesoBrutoDisplay;
	
	@Pattern(regexp="[0-9]{0,9}", message="O Comprimento do Display deve possuir apenas números.")
	@Size(max=9, message="O Comprimento do Display não pode ultrapassar 9 caracteres.")
	private String comprimentoDisplay;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Altura do Display deve possuir apenas números.")
	@Size(max=9, message="A Altura do Display não pode ultrapassar 9 caracteres.")
	private String alturaDisplay;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Largura do Display deve possuir apenas números.")
	@Size(max=9, message="A Largura do Display não pode ultrapassar 9 caracteres.")
	private String larguraDisplay;
	
	private String possuiEmbalagem;
	private String embalagemEmbalagem;
	
	@Pattern(regexp="[0-9]{0,15}", message="O EAN da Embalagem Master deve possuir apenas números.")
	@Size(max=15, message="O Dun da Embalagem Master não pode ultrapassar 15 caracteres.")
	private String eanEmbalagem;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Quantidade da Embalagem Master deve possuir apenas números.")
	@Size(max=4, message="A Quantidade da Embalagem Master não pode ultrapassar 4 caracteres.")
	private String quantidadeEmbalagem;
	
	@Pattern(regexp="[0-9]{0,9}", message="O Peso Bruto da Embalagem Master deve possuir apenas números.")
	@Size(max=9, message="O Peso Bruto da Embalagem Master não pode ultrapassar 9 caracteres.")
	private String pesoBrutoEmbalagem;
	
	@Pattern(regexp="[0-9]{0,9}", message="O Comprimento da Embalagem Master deve possuir apenas números.")
	@Size(max=9, message="O Comprimento da Embalagem Master não pode ultrapassar 9 caracteres.")
	private String comprimentoEmbalagem;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Altura da Embalagem Master deve possuir apenas números.")
	@Size(max=9, message="A Altura da Embalagem Master não pode ultrapassar 9 caracteres.")
	private String alturaEmbalagem;
	
	@Pattern(regexp="[0-9]{0,9}", message="A Largura da Embalagem Master deve possuir apenas números.")
	@Size(max=9, message="A Largura da Embalagem Master não pode ultrapassar 9 caracteres.")
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
	private String caseId;
	
	//PREENCHIMENTO DO SETA
	private String produtoPara;
	private String comprador;
	private String categoria;
	private String divisao;
	
	private String atacado;
	private String telemarketing;
	private String grupo;
	private String varejo;
	
	private String nomeRespPreench;
	private String emailSolicitante;
	private String telefoneSolicitante;
	
	private String nomeRespPreencimento;
	private String emailRespPreencimento;
	private String telefoneRespPreencimento;
	

	private String nomeAprovadorComercial;
	private String emailAprovadorComercial;
	private String telefoneAprovadorComercial;
	
	private long idComprador;
	private String nomeComprador;
	private String emailComprador;
	private String telefoneComprador;
	
	private String IsDadosFiscaisCompleto;
	
	
	private String emailsFiscal;
	private String identificador;
	private String emailsAprovadoNovosCadastro;
	private String emailsPrecificacao;
	private String emailsLogistica;
	private String emailsCadastro;
	private BigDecimal custoBase;
	private BigDecimal desconto;
	private BigDecimal despAcessorios;
	private BigDecimal custoBruto;
	
	
	
	@Embedded
	private List<HistoricoProduto> historicoProdutos = new ArrayList<HistoricoProduto>();
	
	private AprovacaoGerenciaComercial aprovacaoGerenciaComercial;
	
	
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
	public List<HistoricoProduto> getHistoricoProdutos() {
		return historicoProdutos;
	}
	public void setHistoricoProdutos(List<HistoricoProduto> historicoProdutos) {
		this.historicoProdutos = historicoProdutos;
	}
	public AprovacaoGerenciaComercial getAprovacaoGerenciaComercial() {
		return aprovacaoGerenciaComercial;
	}
	public void setAprovacaoGerenciaComercial(AprovacaoGerenciaComercial aprovacaoGerenciaComercial) {
		this.aprovacaoGerenciaComercial = aprovacaoGerenciaComercial;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getProdutoPara() {
		return produtoPara;
	}
	public void setProdutoPara(String produtoPara) {
		this.produtoPara = produtoPara;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getDivisao() {
		return divisao;
	}
	public void setDivisao(String divisao) {
		this.divisao = divisao;
	}
	public String getAtacado() {
		return atacado;
	}
	public void setAtacado(String atacado) {
		this.atacado = atacado;
	}
	public String getTelemarketing() {
		return telemarketing;
	}
	public void setTelemarketing(String telemarketing) {
		this.telemarketing = telemarketing;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getVarejo() {
		return varejo;
	}
	public void setVarejo(String varejo) {
		this.varejo = varejo;
	}	
	public String getSeqfornecedor() {
		return seqfornecedor;
	}
	public void setSeqfornecedor(String seqfornecedor) {
		this.seqfornecedor = seqfornecedor;
	}
	
	public String getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getDescFamilia() {
		return descFamilia;
	}
	public void setDescFamilia(String descFamilia) {
		this.descFamilia = descFamilia;
	}
	public String getNomeRespPreench() {
		return nomeRespPreench;
	}
	public void setNomeRespPreench(String nomeRespPreench) {
		this.nomeRespPreench = nomeRespPreench;
	}
	public String getEmailSolicitante() {
		return emailSolicitante;
	}
	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}
	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}
	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}
	
	
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}
	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}
	public String getNomeRespPreencimento() {
		return nomeRespPreencimento;
	}
	public void setNomeRespPreencimento(String nomeRespPreencimento) {
		this.nomeRespPreencimento = nomeRespPreencimento;
	}
	public String getEmailRespPreencimento() {
		return emailRespPreencimento;
	}
	public void setEmailRespPreencimento(String emailRespPreencimento) {
		this.emailRespPreencimento = emailRespPreencimento;
	}
	public String getTelefoneRespPreencimento() {
		return telefoneRespPreencimento;
	}
	public void setTelefoneRespPreencimento(String telefoneRespPreencimento) {
		this.telefoneRespPreencimento = telefoneRespPreencimento;
	}
	public String getNomeAprovadorComercial() {
		return nomeAprovadorComercial;
	}
	public void setNomeAprovadorComercial(String nomeAprovadorComercial) {
		this.nomeAprovadorComercial = nomeAprovadorComercial;
	}
	public String getEmailAprovadorComercial() {
		return emailAprovadorComercial;
	}
	public void setEmailAprovadorComercial(String emailAprovadorComercial) {
		this.emailAprovadorComercial = emailAprovadorComercial;
	}
	public String getTelefoneAprovadorComercial() {
		return telefoneAprovadorComercial;
	}
	public void setTelefoneAprovadorComercial(String telefoneAprovadorComercial) {
		this.telefoneAprovadorComercial = telefoneAprovadorComercial;
	}
	public String getNomeComprador() {
		return nomeComprador;
	}
	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}
	public String getEmailComprador() {
		return emailComprador;
	}
	public void setEmailComprador(String emailComprador) {
		this.emailComprador = emailComprador;
	}
	public String getTelefoneComprador() {
		return telefoneComprador;
	}
	public void setTelefoneComprador(String telefoneComprador) {
		this.telefoneComprador = telefoneComprador;
	}
	public String getEmailsFiscal() {
		return emailsFiscal;
	}
	public void setEmailsFiscal(String emailsFiscal) {
		this.emailsFiscal = emailsFiscal;
	}
	
	public String getIsDadosFiscaisCompleto() {
		return IsDadosFiscaisCompleto;
	}
	public void setIsDadosFiscaisCompleto(String isDadosFiscaisCompleto) {
		IsDadosFiscaisCompleto = isDadosFiscaisCompleto;
	}
	public String getIdentificador() {
		return identificador;
	}
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}
	public long getIdComprador() {
		return idComprador;
	}
	public void setIdComprador(long idComprador) {
		this.idComprador = idComprador;
	}
	
	public String getEmailsAprovadoNovosCadastro() {
		return emailsAprovadoNovosCadastro;
	}
	public void setEmailsAprovadoNovosCadastro(String emailsAprovadoNovosCadastro) {
		this.emailsAprovadoNovosCadastro = emailsAprovadoNovosCadastro;
	}
	public String getEmailsPrecificacao() {
		return emailsPrecificacao;
	}
	public void setEmailsPrecificacao(String emailsPrecificacao) {
		this.emailsPrecificacao = emailsPrecificacao;
	}
	public String getEmailsLogistica() {
		return emailsLogistica;
	}
	public void setEmailsLogistica(String emailsLogistica) {
		this.emailsLogistica = emailsLogistica;
	}
	public String getEmailsCadastro() {
		return emailsCadastro;
	}
	public void setEmailsCadastro(String emailsCadastro) {
		this.emailsCadastro = emailsCadastro;
	}
	public BigDecimal getCustoBase() {
		return custoBase;
	}
	public void setCustoBase(BigDecimal custoBase) {
		this.custoBase = custoBase;
	}
	public BigDecimal getDesconto() {
		return desconto;
	}
	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}
	public BigDecimal getDespAcessorios() {
		return despAcessorios;
	}
	public void setDespAcessorios(BigDecimal despAcessorios) {
		this.despAcessorios = despAcessorios;
	}
	public BigDecimal getCustoBruto() {
		return custoBruto;
	}
	public void setCustoBruto(BigDecimal custoBruto) {
		this.custoBruto = custoBruto;
	}
	
	@Override
	public String toString() {
		return "FormularioProduto [seqfornecedor=" + seqfornecedor
				+ ", nomeFornecedor=" + nomeFornecedor + ", idFamilia="
				+ idFamilia + ", descFamilia=" + descFamilia
				+ ", descCompleta=" + descCompleta + ", codProduto="
				+ codProduto + ", descReduzida=" + descReduzida
				+ ", paisOrigem=" + paisOrigem + ", simplesNacional="
				+ simplesNacional + ", uploadfile="
				+ Arrays.toString(uploadfile) + ", origemProduto="
				+ origemProduto + ", quantidadeUnidade=" + quantidadeUnidade
				+ ", embalagemUnidade=" + embalagemUnidade + ", eanUnidade="
				+ eanUnidade + ", pesoLiqUnidade=" + pesoLiqUnidade
				+ ", pesoBrutoUnidade=" + pesoBrutoUnidade
				+ ", comprimentoUnidade=" + comprimentoUnidade
				+ ", alturaUnidade=" + alturaUnidade + ", larguraUnidade="
				+ larguraUnidade + ", quantidadeDisplay=" + quantidadeDisplay
				+ ", embalagemDisplay=" + embalagemDisplay + ", eanDisplay="
				+ eanDisplay + ", pesoLiqDisplay=" + pesoLiqDisplay
				+ ", pesoBrutoDisplay=" + pesoBrutoDisplay
				+ ", comprimentoDisplay=" + comprimentoDisplay
				+ ", alturaDisplay=" + alturaDisplay + ", larguraDisplay="
				+ larguraDisplay + ", possuiEmbalagem=" + possuiEmbalagem
				+ ", embalagemEmbalagem=" + embalagemEmbalagem
				+ ", eanEmbalagem=" + eanEmbalagem + ", quantidadeEmbalagem="
				+ quantidadeEmbalagem + ", pesoBrutoEmbalagem="
				+ pesoBrutoEmbalagem + ", comprimentoEmbalagem="
				+ comprimentoEmbalagem + ", alturaEmbalagem=" + alturaEmbalagem
				+ ", larguraEmbalagem=" + larguraEmbalagem + ", formaEntrega="
				+ formaEntrega + ", produtoPaletizado=" + produtoPaletizado
				+ ", quantidadeLastroPaletizado=" + quantidadeLastroPaletizado
				+ ", totalPalletPaletizado=" + totalPalletPaletizado
				+ ", pesoTotalPaletizado=" + pesoTotalPaletizado
				+ ", comprimentoPaletizado=" + comprimentoPaletizado
				+ ", alturaPaletizado=" + alturaPaletizado
				+ ", larguraPaletizado=" + larguraPaletizado
				+ ", quantidadeAlturaPaletizado=" + quantidadeAlturaPaletizado
				+ ", perecivel=" + perecivel + ", pesoVariavel=" + pesoVariavel
				+ ", sazonal=" + sazonal + ", cMaximaEstocagem="
				+ cMaximaEstocagem + ", cMinimaEstocagem=" + cMinimaEstocagem
				+ ", validadeEstocagem=" + validadeEstocagem
				+ ", empilhamentoEstocagem=" + empilhamentoEstocagem
				+ ", quantidadePorcao=" + quantidadePorcao + ", valorCalorico="
				+ valorCalorico + ", proteina=" + proteina
				+ ", gorduraSaturada=" + gorduraSaturada + ", fibraAlimentar="
				+ fibraAlimentar + ", sodio=" + sodio + ", carboidrato="
				+ carboidrato + ", gorduraTotal=" + gorduraTotal
				+ ", gorduraTrans=" + gorduraTrans + ", unidadePorcao="
				+ unidadePorcao + ", cnm=" + cnm + ", descricaoNcm="
				+ descricaoNcm + ", aliquotaIpi=" + aliquotaIpi
				+ ", aliquotaCofins=" + aliquotaCofins + ", aliquotaPis="
				+ aliquotaPis + ", pauta=" + pauta + ", ivaSt=" + ivaSt
				+ ", incentivoFiscal=" + incentivoFiscal
				+ ", incentivoFiscalQual=" + incentivoFiscalQual
				+ ", fundamentacao=" + fundamentacao + ", tributacaoIcms="
				+ tributacaoIcms + ", alicotaIcms=" + alicotaIcms
				+ ", reducaoIcms=" + reducaoIcms + ", caseId=" + caseId
				+ ", produtoPara=" + produtoPara + ", comprador=" + comprador
				+ ", categoria=" + categoria + ", divisao=" + divisao
				+ ", atacado=" + atacado + ", telemarketing=" + telemarketing
				+ ", grupo=" + grupo + ", varejo=" + varejo
				+ ", nomeRespPreench=" + nomeRespPreench
				+ ", emailSolicitante=" + emailSolicitante
				+ ", telefoneSolicitante=" + telefoneSolicitante
				+ ", nomeRespPreencimento=" + nomeRespPreencimento
				+ ", emailRespPreencimento=" + emailRespPreencimento
				+ ", telefoneRespPreencimento=" + telefoneRespPreencimento
				+ ", nomeAprovadorComercial=" + nomeAprovadorComercial
				+ ", emailAprovadorComercial=" + emailAprovadorComercial
				+ ", telefoneAprovadorComercial=" + telefoneAprovadorComercial
				+ ", idComprador=" + idComprador + ", nomeComprador="
				+ nomeComprador + ", emailComprador=" + emailComprador
				+ ", telefoneComprador=" + telefoneComprador
				+ ", IsDadosFiscaisCompleto=" + IsDadosFiscaisCompleto
				+ ", emailsFiscal=" + emailsFiscal + ", identificador="
				+ identificador + ", emailsAprovadoNovosCadastro="
				+ emailsAprovadoNovosCadastro + ", emailsPrecificacao="
				+ emailsPrecificacao + ", emailsLogistica=" + emailsLogistica
				+ ", emailsCadastro=" + emailsCadastro + ", custoBase="
				+ custoBase + ", desconto=" + desconto + ", despAcessorios="
				+ despAcessorios + ", custoBruto=" + custoBruto
				+ ", historicoProdutos=" + historicoProdutos
				+ ", aprovacaoGerenciaComercial=" + aprovacaoGerenciaComercial
				+ "]";
	}


	
}
