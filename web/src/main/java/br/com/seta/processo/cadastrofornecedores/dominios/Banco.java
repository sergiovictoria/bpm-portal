package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Banco {
	
	private static final List<String> BANCOS;
	
	static{
		ArrayList<String> bancos = new ArrayList<String>();
		bancos.add("341 ITAÚ UNIBANCO S.A.");
		bancos.add("237 BANCO BRADESCO S.A.");
		bancos.add("1 BANCO DO BRASIL S.A.");
		bancos.add("33 BANCO SANTANDER (BRASIL) S.A.");
		bancos.add("104 CAIXA ECONÔMICA FEDERAL");
		bancos.add("399 HSBC BANK BRASIL S.A. - BANCO MÚLTIPLO");
		bancos.add("422 BANCO SAFRA S.A.");
		bancos.add("0 BANCO BANKPAR S.A.");
		bancos.add("3 BANCO DA AMAZÔNIA S.A.");
		bancos.add("4 BANCO DO NORDESTE DO BRASIL S.A.");
		bancos.add("12 BANCO STANDARD DE INVESTIMENTOS S.A.");
		bancos.add("14 NATIXIS BRASIL S.A. BANCO MÚLTIPLO");
		bancos.add("19 BANCO AZTECA DO BRASIL S.A.");
		bancos.add("21 BANESTES S.A. BANCO DO ESTADO DO ESPÍRITO SANTO");
		bancos.add("24 BANCO DE PERNAMBUCO S.A. - BANDEPE");
		bancos.add("25 BANCO ALFA S.A.");
		bancos.add("29 BANCO BANERJ S.A.");
		bancos.add("31 BANCO BEG S.A.");
		bancos.add("36 BANCO BRADESCO BBI S.A.");
		bancos.add("37 BANCO DO ESTADO DO PARÁ S.A.");
		bancos.add("39 BANCO DO ESTADO DO PIAUÍ S.A. - BEP");
		bancos.add("40 BANCO CARGILL S.A.");
		bancos.add("41 BANCO DO ESTADO DO RIO GRANDE DO SUL S.A.");
		bancos.add("44 BANCO BVA S.A.");
		bancos.add("45 BANCO OPPORTUNITY S.A.");
		bancos.add("47 BANCO DO ESTADO DE SERGIPE S.A.");
		bancos.add("62 HIPERCARD BANCO MÚLTIPLO S.A.");
		bancos.add("63 BANCO IBI S.A. BANCO MÚLTIPLO");
		bancos.add("64 GOLDMAN SACHS DO BRASIL BANCO MÚLTIPLO S.A.");
		bancos.add("65 BANCO BRACCE S.A.");
		bancos.add("66 BANCO MORGAN STANLEY S.A.");
		bancos.add("69 BPN BRASIL BANCO MÚLTIPLO S.A.");
		bancos.add("70 BRB - BANCO DE BRASÍLIA S.A.");
		bancos.add("72 BANCO RURAL MAIS S.A.");
		bancos.add("73 BB BANCO POPULAR DO BRASIL S.A.");
		bancos.add("74 BANCO J. SAFRA S.A.");
		bancos.add("75 BANCO CR2 S.A.");
		bancos.add("76 BANCO KDB S.A.");
		bancos.add("78 BES INVESTIMENTO DO BRASIL S.A.-BANCO DE INVESTIMENTO");
		bancos.add("79 JBS BANCO S.A.");
		bancos.add("84 UNICRED NORTE DO PARANÁ");
		bancos.add("96 BANCO BM&F DE SERVIÇOS DE LIQUIDAÇÃO E CUSTÓDIA S.A");
		bancos.add("107 BANCO BBM S.A.");
		bancos.add("168 HSBC FINANCE (BRASIL) S.A. - BANCO MÚLTIPLO");
		bancos.add("184 BANCO ITAÚ BBA S.A.");
		bancos.add("204 BANCO BRADESCO CARTÕES S.A.");
		bancos.add("208 BANCO BTG PACTUAL S.A.");
		bancos.add("212 BANCO MATONE S.A.");
		bancos.add("213 BANCO ARBI S.A.");
		bancos.add("214 BANCO DIBENS S.A.");
		bancos.add("215 BANCO COMERCIAL E DE INVESTIMENTO SUDAMERIS S.A.");
		bancos.add("217 BANCO JOHN DEERE S.A.");
		bancos.add("218 BANCO BONSUCESSO S.A.");
		bancos.add("222 BANCO CREDIT AGRICOLE BRASIL S.A.");
		bancos.add("224 BANCO FIBRA S.A.");
		bancos.add("225 BANCO BRASCAN S.A.");
		bancos.add("229 BANCO CRUZEIRO DO SUL S.A.");
		bancos.add("230 UNICARD BANCO MÚLTIPLO S.A.");
		bancos.add("233 BANCO GE CAPITAL S.A.");
		bancos.add("241 BANCO CLÁSSICO S.A.");
		bancos.add("243 BANCO MÁXIMA S.A.");
		bancos.add("246 BANCO ABC BRASIL S.A.");
		bancos.add("248 BANCO BOAVISTA INTERATLÂNTICO S.A.");
		bancos.add("249 BANCO INVESTCRED UNIBANCO S.A.");
		bancos.add("250 BANCO SCHAHIN S.A.");
		bancos.add("254 PARANÁ BANCO S.A.");
		bancos.add("263 BANCO CACIQUE S.A.");
		bancos.add("265 BANCO FATOR S.A.");
		bancos.add("266 BANCO CÉDULA S.A.");
		bancos.add("300 BANCO DE LA NACION ARGENTINA");
		bancos.add("318 BANCO BMG S.A.");
		bancos.add("320 BANCO INDUSTRIAL E COMERCIAL S.A.");
		bancos.add("356 BANCO REAL S.A.");
		bancos.add("366 BANCO SOCIÉTÉ GÉNÉRALE BRASIL S.A.");
		bancos.add("370 BANCO WESTLB DO BRASIL S.A.");
		bancos.add("376 BANCO J. P. MORGAN S.A.");
		bancos.add("389 BANCO MERCANTIL DO BRASIL S.A.");
		bancos.add("394 BANCO BRADESCO FINANCIAMENTOS S.A.");
		bancos.add("409 UNIBANCO - UNIÃO DE BANCOS BRASILEIROS S.A.");
		bancos.add("412 BANCO CAPITAL S.A.");
		bancos.add("453 BANCO RURAL S.A.");
		bancos.add("456 BANCO DE TOKYO-MITSUBISHI UFJ BRASIL S.A.");
		bancos.add("464 BANCO SUMITOMO MITSUI BRASILEIRO S.A.");
		bancos.add("473 BANCO CAIXA GERAL - BRASIL S.A.");
		bancos.add("477 CITIBANK N.A.");
		bancos.add("479 BANCO ITAÚBANK S.A");
		bancos.add("487 DEUTSCHE BANK S.A. - BANCO ALEMÃO");
		bancos.add("488 JPMORGAN CHASE BANK");
		bancos.add("492 ING BANK N.V.");
		bancos.add("494 BANCO DE LA REPUBLICA ORIENTAL DEL URUGUAY");
		bancos.add("495 BANCO DE LA PROVINCIA DE BUENOS AIRES");
		bancos.add("505 BANCO CREDIT SUISSE (BRASIL) S.A.");
		bancos.add("600 BANCO LUSO BRASILEIRO S.A.");
		bancos.add("604 BANCO INDUSTRIAL DO BRASIL S.A.");
		bancos.add("610 BANCO VR S.A.");
		bancos.add("611 BANCO PAULISTA S.A.");
		bancos.add("612 BANCO GUANABARA S.A.");
		bancos.add("613 BANCO PECÚNIA S.A.");
		bancos.add("623 BANCO PANAMERICANO S.A.");
		bancos.add("626 BANCO FICSA S.A.");
		bancos.add("630 BANCO INTERCAP S.A.");
		bancos.add("633 BANCO RENDIMENTO S.A.");
		bancos.add("634 BANCO TRIÂNGULO S.A.");
		bancos.add("637 BANCO SOFISA S.A.");
		bancos.add("638 BANCO PROSPER S.A.");
		bancos.add("641 BANCO ALVORADA S.A.");
		bancos.add("643 BANCO PINE S.A.");
		bancos.add("652 ITAÚ UNIBANCO HOLDING S.A.");
		bancos.add("653 BANCO INDUSVAL S.A.");
		bancos.add("654 BANCO A.J.RENNER S.A.");
		bancos.add("655 BANCO VOTORANTIM S.A.");
		bancos.add("707 BANCO DAYCOVAL S.A.");
		bancos.add("719 BANIF-BANCO INTERNACIONAL DO FUNCHAL (BRASIL)S.A.");
		bancos.add("721 BANCO CREDIBEL S.A.");
		bancos.add("724 BANCO PORTO SEGURO S.A.");
		bancos.add("734 BANCO GERDAU S.A.");
		bancos.add("735 BANCO POTTENCIAL S.A.");
		bancos.add("738 BANCO MORADA S.A.");
		bancos.add("739 BANCO BGN S.A.");
		bancos.add("740 BANCO BARCLAYS S.A.");
		bancos.add("741 BANCO RIBEIRÃO PRETO S.A.");
		bancos.add("743 BANCO SEMEAR S.A.");
		bancos.add("744 BANKBOSTON N.A.");
		bancos.add("745 BANCO CITIBANK S.A.");
		bancos.add("746 BANCO MODAL S.A.");
		bancos.add("747 BANCO RABOBANK INTERNATIONAL BRASIL S.A.");
		bancos.add("748 BANCO COOPERATIVO SICREDI S.A.");
		bancos.add("749 BANCO SIMPLES S.A.");
		bancos.add("751 DRESDNER BANK BRASIL S.A. - BANCO MÚLTIPLO");
		bancos.add("752 BANCO BNP PARIBAS BRASIL S.A.");
		bancos.add("753 NBC BANK BRASIL S.A. - BANCO MÚLTIPLO");
		bancos.add("755 BANK OF AMERICA MERRILL LYNCH BANCO MÚLTIPLO S.A.");
		bancos.add("756 BANCO COOPERATIVO DO BRASIL S.A. - BANCOOB");
		bancos.add("757 BANCO KEB DO BRASIL S.A.");
				
		BANCOS = Collections.unmodifiableList(bancos);
	}
	
	/**
	 * Lista imutável contendo o domínio dos bancos do Processo de Cadastro de Fornecedor
	 * @return
	 */
	public static List<String> getBancos(){
		return BANCOS;
	}
}
