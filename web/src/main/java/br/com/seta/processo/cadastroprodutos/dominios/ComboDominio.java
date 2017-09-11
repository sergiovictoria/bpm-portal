package br.com.seta.processo.cadastroprodutos.dominios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComboDominio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<String> unidadesPorcao;
	private List<String> origensProduto;
	private List<String> formasEntrega;
	private List<String> aliquostaIcms;
	private List<String> reducoes;
	private List<String> tributacoesIcms;
	private List<String> produtoPara;
	private List<String> classificacaoComercial;
	private List<String> divisao;
	
	public ComboDominio() {
		unidadesPorcao = new ArrayList<String>();
		unidadesPorcao.add("GR");
		unidadesPorcao.add("ML");
		unidadesPorcao.add("UN");
		
		origensProduto = new ArrayList<String>();
		origensProduto.add("01-Estrangeira – Importação direta, exceto a indicada no código 6");
		origensProduto.add("02-Estrangeira – Adquirida no mercado interno, exceto a indicada no código 7");
		origensProduto.add("03-Nacional, mercadoria ou bem com conteúdo de importação superior à 40% e inferior ou igual à 70%");
		origensProduto.add("04-Nacional, cuja produção tenha sido feita em conformidade com os processos produtivos básicos (PPB) de que tratam o Decreto Lei nºs 8.248/1991, 8.387/1991, 10.176/2001");
		origensProduto.add("05-Nacional, mercadoria ou bem com conteúdo de importação inferior ou igual a 40%");
		origensProduto.add("06-Estrangeira – Importação direta, sem similar nacional, constante em lista de Resolução CAMEX e gás natural");
		origensProduto.add("07-Estrangeira – Adquirida no mercado interno, sem similar nacional, constante em lista de Resolução CAMEX e gás natural");
		origensProduto.add("08-Nacional, mercadoria ou bem com conteúdo de importação superior à 70%");
		
		formasEntrega = new ArrayList<String>();
		formasEntrega.add("CESTO");
		formasEntrega.add("CAIXA");
		formasEntrega.add("SACO");
		formasEntrega.add("FARDO");
		formasEntrega.add("EMBALADO");
		
		aliquostaIcms = new ArrayList<String>();
		aliquostaIcms.add("04%");
		aliquostaIcms.add("07%");
		aliquostaIcms.add("12%");
		aliquostaIcms.add("17%");
		aliquostaIcms.add("18%");
		aliquostaIcms.add("19%");
		aliquostaIcms.add("25%");
		
		reducoes = new ArrayList<String>();
		reducoes.add("0%");
		reducoes.add("11");
		reducoes.add("33%");
		reducoes.add("52%");
		reducoes.add("67%");
		reducoes.add("Integral");
		
		tributacoesIcms = new ArrayList<String>();
		tributacoesIcms.add("000-Tributada integralmente."); 
		tributacoesIcms.add("010-Tributada e com cobrança do ICMS por substituição tributária.");
		tributacoesIcms.add("020-Com redução de Base de Cálculo.");
		tributacoesIcms.add("030-Isenta ou não tributada e com cobrança do ICMS por substituição tributária.");
		tributacoesIcms.add("040-Isenta");
		tributacoesIcms.add("041-Não tributada");
		tributacoesIcms.add("050-Com suspensão");
		tributacoesIcms.add("051-Com diferimento");
		tributacoesIcms.add("060-ICMS cobrado anteriormente por substituição tributária");
		tributacoesIcms.add("070-Com redução da Base de Cálculo e cobrança do ICMS por substituição tributária");
		tributacoesIcms.add("090-Outras");
		
		produtoPara = new ArrayList<String>();
		produtoPara.add("INCLUSÃO");
		produtoPara.add("ALTERAÇÃO/ATUALIZAÇÃO");
		produtoPara.add("SUBSTITUIÇÃO");
		produtoPara.add("REATIVAÇÃO");
		produtoPara.add("INATIVAR/FORA DE LINHA");
		
		classificacaoComercial = new ArrayList<String>();
		classificacaoComercial.add("A");
		classificacaoComercial.add("B");
		classificacaoComercial.add("C");
		classificacaoComercial.add("D");
		classificacaoComercial.add("E");
		classificacaoComercial.add("F");
		classificacaoComercial.add("G");
		classificacaoComercial.add("H");
		classificacaoComercial.add("I");
		classificacaoComercial.add("J");
		classificacaoComercial.add("K");
		classificacaoComercial.add("X");
		
		divisao = new ArrayList<String>();
		divisao.add("ATACADO");
		divisao.add("NORTE");
		divisao.add("ATACADO E NORTE");
		
	}

	public List<String> getUnidadesPorcao() {
		return unidadesPorcao;
	}

	public List<String> getOrigensProduto() {
		return origensProduto;
	}

	public List<String> getFormasEntrega() {
		return formasEntrega;
	}

	public List<String> getAliquostaIcms() {
		return aliquostaIcms;
	}

	public List<String> getReducoes() {
		return reducoes;
	}

	public List<String> getTributacoesIcms() {
		return tributacoesIcms;
	}

	public List<String> getProdutoPara() {
		return produtoPara;
	}

	public List<String> getClassificacaoComercial() {
		return classificacaoComercial;
	}

	/**
	 * @return the divisao
	 */
	public final List<String> getDivisao() {
		return divisao;
	}
}
