package br.com.seta.processo.dto;


import java.math.BigDecimal;
import java.util.Date;


public class Produto implements java.io.Serializable {
	

	private static final long serialVersionUID = 1L;
	
	private BigDecimal seqpessoa;
	private BigDecimal seqproduto;
	private BigDecimal seqfamilia;
	private String complemento;
	private String desccompleta;
	private String descreduzida;
	private String reffabricante;
	private String especificdetalhada;
	private String desccomposicao;
	private Short pzovalidadedia;
	private String indprocfabricacao;
	private BigDecimal qtdfabricadalote;
	private BigDecimal percacrespreco;
	private BigDecimal fatorconversao;
	private Date dtahorinclusao;
	private String usuarioinclusao;
	private Date dtahoralteracao;
	private String usuarioalteracao;
	private String indreplicacao;
	private String indgeroureplicacao;
	private String indprecozerobalanca;
	private Short pzovalidademes;
	private String nroregminsaude;
	private String codprodfiscal;
	private String descgenerica;
	private BigDecimal propqtdprodutobase;
	private Short nrodiasadverrecbto;
	private BigDecimal percaltprecrelac;
	private Short nroitemfixo;
	private String geralivrocprod;
	private String indusanfdespesa;
	private BigDecimal seqbulario;
	private BigDecimal seqprodutotmp;
	private String indemitecodprodfiscalnfe;
	private BigDecimal percdiasadverrecbto;
	private Integer codigoanp;
	private BigDecimal codigoif;
	private String indintegraecommerce;
	private BigDecimal qtdlimitepromocecommerce;
	private String youtubecodeecommerce;
	private Date datahorintegracaoecommerce;
	private String indres3166;
	private Date dtahoralteracargapdv;
	private String impdatavalidadebalanc;
	private String impdataembbalanc;
	private BigDecimal seqinfnutricprod;
	private String indreplicainfofornec;
	private String tituloecommerce;
	private String descecommerce;
	private String palavrachaveecommerce;
	private String urlecommerce;
	private BigDecimal percgasnatural;
	private String indemiteetqhort;
	private String indcontrolatemperatura;
	private String indpartcotatac;
	private BigDecimal percacresccustorelac;
	private BigDecimal percacresccustorelacvig;
	private BigDecimal qtdmultiplovdaecommerce;
	private String persimiliarecommerce;
	private Date datahorintegracaoecommerceestq;
	private Short pzovalidlote;
	private BigDecimal seqprodrelaclote;
	private BigDecimal aliqadjud;
	
	public Produto() {
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((aliqadjud == null) ? 0 : aliqadjud.hashCode());
		result = prime * result
				+ ((codigoanp == null) ? 0 : codigoanp.hashCode());
		result = prime * result
				+ ((codigoif == null) ? 0 : codigoif.hashCode());
		result = prime * result
				+ ((codprodfiscal == null) ? 0 : codprodfiscal.hashCode());
		result = prime * result
				+ ((complemento == null) ? 0 : complemento.hashCode());
		result = prime
				* result
				+ ((datahorintegracaoecommerce == null) ? 0
						: datahorintegracaoecommerce.hashCode());
		result = prime
				* result
				+ ((datahorintegracaoecommerceestq == null) ? 0
						: datahorintegracaoecommerceestq.hashCode());
		result = prime * result
				+ ((desccompleta == null) ? 0 : desccompleta.hashCode());
		result = prime * result
				+ ((desccomposicao == null) ? 0 : desccomposicao.hashCode());
		result = prime * result
				+ ((descecommerce == null) ? 0 : descecommerce.hashCode());
		result = prime * result
				+ ((descgenerica == null) ? 0 : descgenerica.hashCode());
		result = prime * result
				+ ((descreduzida == null) ? 0 : descreduzida.hashCode());
		result = prime * result
				+ ((dtahoralteracao == null) ? 0 : dtahoralteracao.hashCode());
		result = prime
				* result
				+ ((dtahoralteracargapdv == null) ? 0 : dtahoralteracargapdv
						.hashCode());
		result = prime * result
				+ ((dtahorinclusao == null) ? 0 : dtahorinclusao.hashCode());
		result = prime
				* result
				+ ((especificdetalhada == null) ? 0 : especificdetalhada
						.hashCode());
		result = prime * result
				+ ((fatorconversao == null) ? 0 : fatorconversao.hashCode());
		result = prime * result
				+ ((geralivrocprod == null) ? 0 : geralivrocprod.hashCode());
		result = prime
				* result
				+ ((impdataembbalanc == null) ? 0 : impdataembbalanc.hashCode());
		result = prime
				* result
				+ ((impdatavalidadebalanc == null) ? 0 : impdatavalidadebalanc
						.hashCode());
		result = prime
				* result
				+ ((indcontrolatemperatura == null) ? 0
						: indcontrolatemperatura.hashCode());
		result = prime
				* result
				+ ((indemitecodprodfiscalnfe == null) ? 0
						: indemitecodprodfiscalnfe.hashCode());
		result = prime * result
				+ ((indemiteetqhort == null) ? 0 : indemiteetqhort.hashCode());
		result = prime
				* result
				+ ((indgeroureplicacao == null) ? 0 : indgeroureplicacao
						.hashCode());
		result = prime
				* result
				+ ((indintegraecommerce == null) ? 0 : indintegraecommerce
						.hashCode());
		result = prime * result
				+ ((indpartcotatac == null) ? 0 : indpartcotatac.hashCode());
		result = prime
				* result
				+ ((indprecozerobalanca == null) ? 0 : indprecozerobalanca
						.hashCode());
		result = prime
				* result
				+ ((indprocfabricacao == null) ? 0 : indprocfabricacao
						.hashCode());
		result = prime * result
				+ ((indreplicacao == null) ? 0 : indreplicacao.hashCode());
		result = prime
				* result
				+ ((indreplicainfofornec == null) ? 0 : indreplicainfofornec
						.hashCode());
		result = prime * result
				+ ((indres3166 == null) ? 0 : indres3166.hashCode());
		result = prime * result
				+ ((indusanfdespesa == null) ? 0 : indusanfdespesa.hashCode());
		result = prime
				* result
				+ ((nrodiasadverrecbto == null) ? 0 : nrodiasadverrecbto
						.hashCode());
		result = prime * result
				+ ((nroitemfixo == null) ? 0 : nroitemfixo.hashCode());
		result = prime * result
				+ ((nroregminsaude == null) ? 0 : nroregminsaude.hashCode());
		result = prime
				* result
				+ ((palavrachaveecommerce == null) ? 0 : palavrachaveecommerce
						.hashCode());
		result = prime
				* result
				+ ((percacresccustorelac == null) ? 0 : percacresccustorelac
						.hashCode());
		result = prime
				* result
				+ ((percacresccustorelacvig == null) ? 0
						: percacresccustorelacvig.hashCode());
		result = prime * result
				+ ((percacrespreco == null) ? 0 : percacrespreco.hashCode());
		result = prime
				* result
				+ ((percaltprecrelac == null) ? 0 : percaltprecrelac.hashCode());
		result = prime
				* result
				+ ((percdiasadverrecbto == null) ? 0 : percdiasadverrecbto
						.hashCode());
		result = prime * result
				+ ((percgasnatural == null) ? 0 : percgasnatural.hashCode());
		result = prime
				* result
				+ ((persimiliarecommerce == null) ? 0 : persimiliarecommerce
						.hashCode());
		result = prime
				* result
				+ ((propqtdprodutobase == null) ? 0 : propqtdprodutobase
						.hashCode());
		result = prime * result
				+ ((pzovalidadedia == null) ? 0 : pzovalidadedia.hashCode());
		result = prime * result
				+ ((pzovalidademes == null) ? 0 : pzovalidademes.hashCode());
		result = prime * result
				+ ((pzovalidlote == null) ? 0 : pzovalidlote.hashCode());
		result = prime
				* result
				+ ((qtdfabricadalote == null) ? 0 : qtdfabricadalote.hashCode());
		result = prime
				* result
				+ ((qtdlimitepromocecommerce == null) ? 0
						: qtdlimitepromocecommerce.hashCode());
		result = prime
				* result
				+ ((qtdmultiplovdaecommerce == null) ? 0
						: qtdmultiplovdaecommerce.hashCode());
		result = prime * result
				+ ((reffabricante == null) ? 0 : reffabricante.hashCode());
		result = prime * result
				+ ((seqbulario == null) ? 0 : seqbulario.hashCode());
		result = prime * result
				+ ((seqfamilia == null) ? 0 : seqfamilia.hashCode());
		result = prime
				* result
				+ ((seqinfnutricprod == null) ? 0 : seqinfnutricprod.hashCode());
		result = prime
				* result
				+ ((seqprodrelaclote == null) ? 0 : seqprodrelaclote.hashCode());
		result = prime * result
				+ ((seqproduto == null) ? 0 : seqproduto.hashCode());
		result = prime * result
				+ ((seqprodutotmp == null) ? 0 : seqprodutotmp.hashCode());
		result = prime * result
				+ ((tituloecommerce == null) ? 0 : tituloecommerce.hashCode());
		result = prime * result
				+ ((urlecommerce == null) ? 0 : urlecommerce.hashCode());
		result = prime
				* result
				+ ((usuarioalteracao == null) ? 0 : usuarioalteracao.hashCode());
		result = prime * result
				+ ((usuarioinclusao == null) ? 0 : usuarioinclusao.hashCode());
		result = prime
				* result
				+ ((youtubecodeecommerce == null) ? 0 : youtubecodeecommerce
						.hashCode());
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
		Produto other = (Produto) obj;
		if (aliqadjud == null) {
			if (other.aliqadjud != null)
				return false;
		} else if (!aliqadjud.equals(other.aliqadjud))
			return false;
		if (codigoanp == null) {
			if (other.codigoanp != null)
				return false;
		} else if (!codigoanp.equals(other.codigoanp))
			return false;
		if (codigoif == null) {
			if (other.codigoif != null)
				return false;
		} else if (!codigoif.equals(other.codigoif))
			return false;
		if (codprodfiscal == null) {
			if (other.codprodfiscal != null)
				return false;
		} else if (!codprodfiscal.equals(other.codprodfiscal))
			return false;
		if (complemento == null) {
			if (other.complemento != null)
				return false;
		} else if (!complemento.equals(other.complemento))
			return false;
		if (datahorintegracaoecommerce == null) {
			if (other.datahorintegracaoecommerce != null)
				return false;
		} else if (!datahorintegracaoecommerce
				.equals(other.datahorintegracaoecommerce))
			return false;
		if (datahorintegracaoecommerceestq == null) {
			if (other.datahorintegracaoecommerceestq != null)
				return false;
		} else if (!datahorintegracaoecommerceestq
				.equals(other.datahorintegracaoecommerceestq))
			return false;
		if (desccompleta == null) {
			if (other.desccompleta != null)
				return false;
		} else if (!desccompleta.equals(other.desccompleta))
			return false;
		if (desccomposicao == null) {
			if (other.desccomposicao != null)
				return false;
		} else if (!desccomposicao.equals(other.desccomposicao))
			return false;
		if (descecommerce == null) {
			if (other.descecommerce != null)
				return false;
		} else if (!descecommerce.equals(other.descecommerce))
			return false;
		if (descgenerica == null) {
			if (other.descgenerica != null)
				return false;
		} else if (!descgenerica.equals(other.descgenerica))
			return false;
		if (descreduzida == null) {
			if (other.descreduzida != null)
				return false;
		} else if (!descreduzida.equals(other.descreduzida))
			return false;
		if (dtahoralteracao == null) {
			if (other.dtahoralteracao != null)
				return false;
		} else if (!dtahoralteracao.equals(other.dtahoralteracao))
			return false;
		if (dtahoralteracargapdv == null) {
			if (other.dtahoralteracargapdv != null)
				return false;
		} else if (!dtahoralteracargapdv.equals(other.dtahoralteracargapdv))
			return false;
		if (dtahorinclusao == null) {
			if (other.dtahorinclusao != null)
				return false;
		} else if (!dtahorinclusao.equals(other.dtahorinclusao))
			return false;
		if (especificdetalhada == null) {
			if (other.especificdetalhada != null)
				return false;
		} else if (!especificdetalhada.equals(other.especificdetalhada))
			return false;
		if (fatorconversao == null) {
			if (other.fatorconversao != null)
				return false;
		} else if (!fatorconversao.equals(other.fatorconversao))
			return false;
		if (geralivrocprod == null) {
			if (other.geralivrocprod != null)
				return false;
		} else if (!geralivrocprod.equals(other.geralivrocprod))
			return false;
		if (impdataembbalanc == null) {
			if (other.impdataembbalanc != null)
				return false;
		} else if (!impdataembbalanc.equals(other.impdataembbalanc))
			return false;
		if (impdatavalidadebalanc == null) {
			if (other.impdatavalidadebalanc != null)
				return false;
		} else if (!impdatavalidadebalanc.equals(other.impdatavalidadebalanc))
			return false;
		if (indcontrolatemperatura == null) {
			if (other.indcontrolatemperatura != null)
				return false;
		} else if (!indcontrolatemperatura.equals(other.indcontrolatemperatura))
			return false;
		if (indemitecodprodfiscalnfe == null) {
			if (other.indemitecodprodfiscalnfe != null)
				return false;
		} else if (!indemitecodprodfiscalnfe
				.equals(other.indemitecodprodfiscalnfe))
			return false;
		if (indemiteetqhort == null) {
			if (other.indemiteetqhort != null)
				return false;
		} else if (!indemiteetqhort.equals(other.indemiteetqhort))
			return false;
		if (indgeroureplicacao == null) {
			if (other.indgeroureplicacao != null)
				return false;
		} else if (!indgeroureplicacao.equals(other.indgeroureplicacao))
			return false;
		if (indintegraecommerce == null) {
			if (other.indintegraecommerce != null)
				return false;
		} else if (!indintegraecommerce.equals(other.indintegraecommerce))
			return false;
		if (indpartcotatac == null) {
			if (other.indpartcotatac != null)
				return false;
		} else if (!indpartcotatac.equals(other.indpartcotatac))
			return false;
		if (indprecozerobalanca == null) {
			if (other.indprecozerobalanca != null)
				return false;
		} else if (!indprecozerobalanca.equals(other.indprecozerobalanca))
			return false;
		if (indprocfabricacao == null) {
			if (other.indprocfabricacao != null)
				return false;
		} else if (!indprocfabricacao.equals(other.indprocfabricacao))
			return false;
		if (indreplicacao == null) {
			if (other.indreplicacao != null)
				return false;
		} else if (!indreplicacao.equals(other.indreplicacao))
			return false;
		if (indreplicainfofornec == null) {
			if (other.indreplicainfofornec != null)
				return false;
		} else if (!indreplicainfofornec.equals(other.indreplicainfofornec))
			return false;
		if (indres3166 == null) {
			if (other.indres3166 != null)
				return false;
		} else if (!indres3166.equals(other.indres3166))
			return false;
		if (indusanfdespesa == null) {
			if (other.indusanfdespesa != null)
				return false;
		} else if (!indusanfdespesa.equals(other.indusanfdespesa))
			return false;
		if (nrodiasadverrecbto == null) {
			if (other.nrodiasadverrecbto != null)
				return false;
		} else if (!nrodiasadverrecbto.equals(other.nrodiasadverrecbto))
			return false;
		if (nroitemfixo == null) {
			if (other.nroitemfixo != null)
				return false;
		} else if (!nroitemfixo.equals(other.nroitemfixo))
			return false;
		if (nroregminsaude == null) {
			if (other.nroregminsaude != null)
				return false;
		} else if (!nroregminsaude.equals(other.nroregminsaude))
			return false;
		if (palavrachaveecommerce == null) {
			if (other.palavrachaveecommerce != null)
				return false;
		} else if (!palavrachaveecommerce.equals(other.palavrachaveecommerce))
			return false;
		if (percacresccustorelac == null) {
			if (other.percacresccustorelac != null)
				return false;
		} else if (!percacresccustorelac.equals(other.percacresccustorelac))
			return false;
		if (percacresccustorelacvig == null) {
			if (other.percacresccustorelacvig != null)
				return false;
		} else if (!percacresccustorelacvig
				.equals(other.percacresccustorelacvig))
			return false;
		if (percacrespreco == null) {
			if (other.percacrespreco != null)
				return false;
		} else if (!percacrespreco.equals(other.percacrespreco))
			return false;
		if (percaltprecrelac == null) {
			if (other.percaltprecrelac != null)
				return false;
		} else if (!percaltprecrelac.equals(other.percaltprecrelac))
			return false;
		if (percdiasadverrecbto == null) {
			if (other.percdiasadverrecbto != null)
				return false;
		} else if (!percdiasadverrecbto.equals(other.percdiasadverrecbto))
			return false;
		if (percgasnatural == null) {
			if (other.percgasnatural != null)
				return false;
		} else if (!percgasnatural.equals(other.percgasnatural))
			return false;
		if (persimiliarecommerce == null) {
			if (other.persimiliarecommerce != null)
				return false;
		} else if (!persimiliarecommerce.equals(other.persimiliarecommerce))
			return false;
		if (propqtdprodutobase == null) {
			if (other.propqtdprodutobase != null)
				return false;
		} else if (!propqtdprodutobase.equals(other.propqtdprodutobase))
			return false;
		if (pzovalidadedia == null) {
			if (other.pzovalidadedia != null)
				return false;
		} else if (!pzovalidadedia.equals(other.pzovalidadedia))
			return false;
		if (pzovalidademes == null) {
			if (other.pzovalidademes != null)
				return false;
		} else if (!pzovalidademes.equals(other.pzovalidademes))
			return false;
		if (pzovalidlote == null) {
			if (other.pzovalidlote != null)
				return false;
		} else if (!pzovalidlote.equals(other.pzovalidlote))
			return false;
		if (qtdfabricadalote == null) {
			if (other.qtdfabricadalote != null)
				return false;
		} else if (!qtdfabricadalote.equals(other.qtdfabricadalote))
			return false;
		if (qtdlimitepromocecommerce == null) {
			if (other.qtdlimitepromocecommerce != null)
				return false;
		} else if (!qtdlimitepromocecommerce
				.equals(other.qtdlimitepromocecommerce))
			return false;
		if (qtdmultiplovdaecommerce == null) {
			if (other.qtdmultiplovdaecommerce != null)
				return false;
		} else if (!qtdmultiplovdaecommerce
				.equals(other.qtdmultiplovdaecommerce))
			return false;
		if (reffabricante == null) {
			if (other.reffabricante != null)
				return false;
		} else if (!reffabricante.equals(other.reffabricante))
			return false;
		if (seqbulario == null) {
			if (other.seqbulario != null)
				return false;
		} else if (!seqbulario.equals(other.seqbulario))
			return false;
		if (seqfamilia == null) {
			if (other.seqfamilia != null)
				return false;
		} else if (!seqfamilia.equals(other.seqfamilia))
			return false;
		if (seqinfnutricprod == null) {
			if (other.seqinfnutricprod != null)
				return false;
		} else if (!seqinfnutricprod.equals(other.seqinfnutricprod))
			return false;
		if (seqprodrelaclote == null) {
			if (other.seqprodrelaclote != null)
				return false;
		} else if (!seqprodrelaclote.equals(other.seqprodrelaclote))
			return false;
		if (seqproduto == null) {
			if (other.seqproduto != null)
				return false;
		} else if (!seqproduto.equals(other.seqproduto))
			return false;
		if (seqprodutotmp == null) {
			if (other.seqprodutotmp != null)
				return false;
		} else if (!seqprodutotmp.equals(other.seqprodutotmp))
			return false;
		if (tituloecommerce == null) {
			if (other.tituloecommerce != null)
				return false;
		} else if (!tituloecommerce.equals(other.tituloecommerce))
			return false;
		if (urlecommerce == null) {
			if (other.urlecommerce != null)
				return false;
		} else if (!urlecommerce.equals(other.urlecommerce))
			return false;
		if (usuarioalteracao == null) {
			if (other.usuarioalteracao != null)
				return false;
		} else if (!usuarioalteracao.equals(other.usuarioalteracao))
			return false;
		if (usuarioinclusao == null) {
			if (other.usuarioinclusao != null)
				return false;
		} else if (!usuarioinclusao.equals(other.usuarioinclusao))
			return false;
		if (youtubecodeecommerce == null) {
			if (other.youtubecodeecommerce != null)
				return false;
		} else if (!youtubecodeecommerce.equals(other.youtubecodeecommerce))
			return false;
		return true;
	}



	public Produto(BigDecimal seqproduto, BigDecimal seqfamilia,
			String complemento, String desccompleta, String descreduzida,
			String reffabricante, String especificdetalhada,
			String desccomposicao, Short pzovalidadedia,
			String indprocfabricacao, BigDecimal qtdfabricadalote,
			BigDecimal percacrespreco, BigDecimal fatorconversao,
			Date dtahorinclusao, String usuarioinclusao, Date dtahoralteracao,
			String usuarioalteracao, String indreplicacao,
			String indgeroureplicacao, String indprecozerobalanca,
			Short pzovalidademes, String nroregminsaude, String codprodfiscal,
			String descgenerica, BigDecimal propqtdprodutobase,
			Short nrodiasadverrecbto, BigDecimal percaltprecrelac,
			Short nroitemfixo, String geralivrocprod, String indusanfdespesa,
			BigDecimal seqbulario, BigDecimal seqprodutotmp,
			String indemitecodprodfiscalnfe, BigDecimal percdiasadverrecbto,
			Integer codigoanp, BigDecimal codigoif, String indintegraecommerce,
			BigDecimal qtdlimitepromocecommerce, String youtubecodeecommerce,
			Date datahorintegracaoecommerce, String indres3166,
			Date dtahoralteracargapdv, String impdatavalidadebalanc,
			String impdataembbalanc, BigDecimal seqinfnutricprod,
			String indreplicainfofornec, String tituloecommerce,
			String descecommerce, String palavrachaveecommerce,
			String urlecommerce, BigDecimal percgasnatural,
			String indemiteetqhort, String indcontrolatemperatura,
			String indpartcotatac, BigDecimal percacresccustorelac,
			BigDecimal percacresccustorelacvig,
			BigDecimal qtdmultiplovdaecommerce, String persimiliarecommerce,
			Date datahorintegracaoecommerceestq, Short pzovalidlote,
			BigDecimal seqprodrelaclote, BigDecimal aliqadjud) {
		super();
		this.seqproduto = seqproduto;
		this.seqfamilia = seqfamilia;
		this.complemento = complemento;
		this.desccompleta = desccompleta;
		this.descreduzida = descreduzida;
		this.reffabricante = reffabricante;
		this.especificdetalhada = especificdetalhada;
		this.desccomposicao = desccomposicao;
		this.pzovalidadedia = pzovalidadedia;
		this.indprocfabricacao = indprocfabricacao;
		this.qtdfabricadalote = qtdfabricadalote;
		this.percacrespreco = percacrespreco;
		this.fatorconversao = fatorconversao;
		this.dtahorinclusao = dtahorinclusao;
		this.usuarioinclusao = usuarioinclusao;
		this.dtahoralteracao = dtahoralteracao;
		this.usuarioalteracao = usuarioalteracao;
		this.indreplicacao = indreplicacao;
		this.indgeroureplicacao = indgeroureplicacao;
		this.indprecozerobalanca = indprecozerobalanca;
		this.pzovalidademes = pzovalidademes;
		this.nroregminsaude = nroregminsaude;
		this.codprodfiscal = codprodfiscal;
		this.descgenerica = descgenerica;
		this.propqtdprodutobase = propqtdprodutobase;
		this.nrodiasadverrecbto = nrodiasadverrecbto;
		this.percaltprecrelac = percaltprecrelac;
		this.nroitemfixo = nroitemfixo;
		this.geralivrocprod = geralivrocprod;
		this.indusanfdespesa = indusanfdespesa;
		this.seqbulario = seqbulario;
		this.seqprodutotmp = seqprodutotmp;
		this.indemitecodprodfiscalnfe = indemitecodprodfiscalnfe;
		this.percdiasadverrecbto = percdiasadverrecbto;
		this.codigoanp = codigoanp;
		this.codigoif = codigoif;
		this.indintegraecommerce = indintegraecommerce;
		this.qtdlimitepromocecommerce = qtdlimitepromocecommerce;
		this.youtubecodeecommerce = youtubecodeecommerce;
		this.datahorintegracaoecommerce = datahorintegracaoecommerce;
		this.indres3166 = indres3166;
		this.dtahoralteracargapdv = dtahoralteracargapdv;
		this.impdatavalidadebalanc = impdatavalidadebalanc;
		this.impdataembbalanc = impdataembbalanc;
		this.seqinfnutricprod = seqinfnutricprod;
		this.indreplicainfofornec = indreplicainfofornec;
		this.tituloecommerce = tituloecommerce;
		this.descecommerce = descecommerce;
		this.palavrachaveecommerce = palavrachaveecommerce;
		this.urlecommerce = urlecommerce;
		this.percgasnatural = percgasnatural;
		this.indemiteetqhort = indemiteetqhort;
		this.indcontrolatemperatura = indcontrolatemperatura;
		this.indpartcotatac = indpartcotatac;
		this.percacresccustorelac = percacresccustorelac;
		this.percacresccustorelacvig = percacresccustorelacvig;
		this.qtdmultiplovdaecommerce = qtdmultiplovdaecommerce;
		this.persimiliarecommerce = persimiliarecommerce;
		this.datahorintegracaoecommerceestq = datahorintegracaoecommerceestq;
		this.pzovalidlote = pzovalidlote;
		this.seqprodrelaclote = seqprodrelaclote;
		this.aliqadjud = aliqadjud;
	}



	public BigDecimal getSeqproduto() {
		return seqproduto;
	}



	public void setSeqproduto(BigDecimal seqproduto) {
		this.seqproduto = seqproduto;
	}



	public BigDecimal getSeqfamilia() {
		return seqfamilia;
	}



	public void setSeqfamilia(BigDecimal seqfamilia) {
		this.seqfamilia = seqfamilia;
	}



	public String getComplemento() {
		return complemento;
	}



	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}



	public String getDesccompleta() {
		return desccompleta;
	}



	public void setDesccompleta(String desccompleta) {
		this.desccompleta = desccompleta;
	}



	public String getDescreduzida() {
		return descreduzida;
	}



	public void setDescreduzida(String descreduzida) {
		this.descreduzida = descreduzida;
	}



	public String getReffabricante() {
		return reffabricante;
	}



	public void setReffabricante(String reffabricante) {
		this.reffabricante = reffabricante;
	}



	public String getEspecificdetalhada() {
		return especificdetalhada;
	}



	public void setEspecificdetalhada(String especificdetalhada) {
		this.especificdetalhada = especificdetalhada;
	}



	public String getDesccomposicao() {
		return desccomposicao;
	}



	public void setDesccomposicao(String desccomposicao) {
		this.desccomposicao = desccomposicao;
	}



	public Short getPzovalidadedia() {
		return pzovalidadedia;
	}



	public void setPzovalidadedia(Short pzovalidadedia) {
		this.pzovalidadedia = pzovalidadedia;
	}



	public String getIndprocfabricacao() {
		return indprocfabricacao;
	}



	public void setIndprocfabricacao(String indprocfabricacao) {
		this.indprocfabricacao = indprocfabricacao;
	}



	public BigDecimal getQtdfabricadalote() {
		return qtdfabricadalote;
	}



	public void setQtdfabricadalote(BigDecimal qtdfabricadalote) {
		this.qtdfabricadalote = qtdfabricadalote;
	}



	public BigDecimal getPercacrespreco() {
		return percacrespreco;
	}



	public void setPercacrespreco(BigDecimal percacrespreco) {
		this.percacrespreco = percacrespreco;
	}



	public BigDecimal getFatorconversao() {
		return fatorconversao;
	}



	public void setFatorconversao(BigDecimal fatorconversao) {
		this.fatorconversao = fatorconversao;
	}



	public Date getDtahorinclusao() {
		return dtahorinclusao;
	}



	public void setDtahorinclusao(Date dtahorinclusao) {
		this.dtahorinclusao = dtahorinclusao;
	}



	public String getUsuarioinclusao() {
		return usuarioinclusao;
	}



	public void setUsuarioinclusao(String usuarioinclusao) {
		this.usuarioinclusao = usuarioinclusao;
	}



	public Date getDtahoralteracao() {
		return dtahoralteracao;
	}



	public void setDtahoralteracao(Date dtahoralteracao) {
		this.dtahoralteracao = dtahoralteracao;
	}



	public String getUsuarioalteracao() {
		return usuarioalteracao;
	}



	public void setUsuarioalteracao(String usuarioalteracao) {
		this.usuarioalteracao = usuarioalteracao;
	}



	public String getIndreplicacao() {
		return indreplicacao;
	}



	public void setIndreplicacao(String indreplicacao) {
		this.indreplicacao = indreplicacao;
	}



	public String getIndgeroureplicacao() {
		return indgeroureplicacao;
	}



	public void setIndgeroureplicacao(String indgeroureplicacao) {
		this.indgeroureplicacao = indgeroureplicacao;
	}



	public String getIndprecozerobalanca() {
		return indprecozerobalanca;
	}



	public void setIndprecozerobalanca(String indprecozerobalanca) {
		this.indprecozerobalanca = indprecozerobalanca;
	}



	public Short getPzovalidademes() {
		return pzovalidademes;
	}



	public void setPzovalidademes(Short pzovalidademes) {
		this.pzovalidademes = pzovalidademes;
	}



	public String getNroregminsaude() {
		return nroregminsaude;
	}



	public void setNroregminsaude(String nroregminsaude) {
		this.nroregminsaude = nroregminsaude;
	}



	public String getCodprodfiscal() {
		return codprodfiscal;
	}



	public void setCodprodfiscal(String codprodfiscal) {
		this.codprodfiscal = codprodfiscal;
	}



	public String getDescgenerica() {
		return descgenerica;
	}



	public void setDescgenerica(String descgenerica) {
		this.descgenerica = descgenerica;
	}



	public BigDecimal getPropqtdprodutobase() {
		return propqtdprodutobase;
	}



	public void setPropqtdprodutobase(BigDecimal propqtdprodutobase) {
		this.propqtdprodutobase = propqtdprodutobase;
	}



	public Short getNrodiasadverrecbto() {
		return nrodiasadverrecbto;
	}



	public void setNrodiasadverrecbto(Short nrodiasadverrecbto) {
		this.nrodiasadverrecbto = nrodiasadverrecbto;
	}



	public BigDecimal getPercaltprecrelac() {
		return percaltprecrelac;
	}



	public void setPercaltprecrelac(BigDecimal percaltprecrelac) {
		this.percaltprecrelac = percaltprecrelac;
	}



	public Short getNroitemfixo() {
		return nroitemfixo;
	}



	public void setNroitemfixo(Short nroitemfixo) {
		this.nroitemfixo = nroitemfixo;
	}



	public String getGeralivrocprod() {
		return geralivrocprod;
	}



	public void setGeralivrocprod(String geralivrocprod) {
		this.geralivrocprod = geralivrocprod;
	}



	public String getIndusanfdespesa() {
		return indusanfdespesa;
	}



	public void setIndusanfdespesa(String indusanfdespesa) {
		this.indusanfdespesa = indusanfdespesa;
	}



	public BigDecimal getSeqbulario() {
		return seqbulario;
	}



	public void setSeqbulario(BigDecimal seqbulario) {
		this.seqbulario = seqbulario;
	}



	public BigDecimal getSeqprodutotmp() {
		return seqprodutotmp;
	}



	public void setSeqprodutotmp(BigDecimal seqprodutotmp) {
		this.seqprodutotmp = seqprodutotmp;
	}



	public String getIndemitecodprodfiscalnfe() {
		return indemitecodprodfiscalnfe;
	}



	public void setIndemitecodprodfiscalnfe(String indemitecodprodfiscalnfe) {
		this.indemitecodprodfiscalnfe = indemitecodprodfiscalnfe;
	}



	public BigDecimal getPercdiasadverrecbto() {
		return percdiasadverrecbto;
	}



	public void setPercdiasadverrecbto(BigDecimal percdiasadverrecbto) {
		this.percdiasadverrecbto = percdiasadverrecbto;
	}



	public Integer getCodigoanp() {
		return codigoanp;
	}



	public void setCodigoanp(Integer codigoanp) {
		this.codigoanp = codigoanp;
	}



	public BigDecimal getCodigoif() {
		return codigoif;
	}



	public void setCodigoif(BigDecimal codigoif) {
		this.codigoif = codigoif;
	}



	public String getIndintegraecommerce() {
		return indintegraecommerce;
	}



	public void setIndintegraecommerce(String indintegraecommerce) {
		this.indintegraecommerce = indintegraecommerce;
	}



	public BigDecimal getQtdlimitepromocecommerce() {
		return qtdlimitepromocecommerce;
	}



	public void setQtdlimitepromocecommerce(BigDecimal qtdlimitepromocecommerce) {
		this.qtdlimitepromocecommerce = qtdlimitepromocecommerce;
	}



	public String getYoutubecodeecommerce() {
		return youtubecodeecommerce;
	}



	public void setYoutubecodeecommerce(String youtubecodeecommerce) {
		this.youtubecodeecommerce = youtubecodeecommerce;
	}



	public Date getDatahorintegracaoecommerce() {
		return datahorintegracaoecommerce;
	}



	public void setDatahorintegracaoecommerce(Date datahorintegracaoecommerce) {
		this.datahorintegracaoecommerce = datahorintegracaoecommerce;
	}



	public String getIndres3166() {
		return indres3166;
	}



	public void setIndres3166(String indres3166) {
		this.indres3166 = indres3166;
	}



	public Date getDtahoralteracargapdv() {
		return dtahoralteracargapdv;
	}



	public void setDtahoralteracargapdv(Date dtahoralteracargapdv) {
		this.dtahoralteracargapdv = dtahoralteracargapdv;
	}



	public String getImpdatavalidadebalanc() {
		return impdatavalidadebalanc;
	}



	public void setImpdatavalidadebalanc(String impdatavalidadebalanc) {
		this.impdatavalidadebalanc = impdatavalidadebalanc;
	}



	public String getImpdataembbalanc() {
		return impdataembbalanc;
	}



	public void setImpdataembbalanc(String impdataembbalanc) {
		this.impdataembbalanc = impdataembbalanc;
	}



	public BigDecimal getSeqinfnutricprod() {
		return seqinfnutricprod;
	}



	public void setSeqinfnutricprod(BigDecimal seqinfnutricprod) {
		this.seqinfnutricprod = seqinfnutricprod;
	}



	public String getIndreplicainfofornec() {
		return indreplicainfofornec;
	}



	public void setIndreplicainfofornec(String indreplicainfofornec) {
		this.indreplicainfofornec = indreplicainfofornec;
	}



	public String getTituloecommerce() {
		return tituloecommerce;
	}



	public void setTituloecommerce(String tituloecommerce) {
		this.tituloecommerce = tituloecommerce;
	}



	public String getDescecommerce() {
		return descecommerce;
	}



	public void setDescecommerce(String descecommerce) {
		this.descecommerce = descecommerce;
	}



	public String getPalavrachaveecommerce() {
		return palavrachaveecommerce;
	}



	public void setPalavrachaveecommerce(String palavrachaveecommerce) {
		this.palavrachaveecommerce = palavrachaveecommerce;
	}



	public String getUrlecommerce() {
		return urlecommerce;
	}



	public void setUrlecommerce(String urlecommerce) {
		this.urlecommerce = urlecommerce;
	}



	public BigDecimal getPercgasnatural() {
		return percgasnatural;
	}



	public void setPercgasnatural(BigDecimal percgasnatural) {
		this.percgasnatural = percgasnatural;
	}



	public String getIndemiteetqhort() {
		return indemiteetqhort;
	}



	public void setIndemiteetqhort(String indemiteetqhort) {
		this.indemiteetqhort = indemiteetqhort;
	}



	public String getIndcontrolatemperatura() {
		return indcontrolatemperatura;
	}



	public void setIndcontrolatemperatura(String indcontrolatemperatura) {
		this.indcontrolatemperatura = indcontrolatemperatura;
	}



	public String getIndpartcotatac() {
		return indpartcotatac;
	}



	public void setIndpartcotatac(String indpartcotatac) {
		this.indpartcotatac = indpartcotatac;
	}



	public BigDecimal getPercacresccustorelac() {
		return percacresccustorelac;
	}



	public void setPercacresccustorelac(BigDecimal percacresccustorelac) {
		this.percacresccustorelac = percacresccustorelac;
	}



	public BigDecimal getPercacresccustorelacvig() {
		return percacresccustorelacvig;
	}



	public void setPercacresccustorelacvig(BigDecimal percacresccustorelacvig) {
		this.percacresccustorelacvig = percacresccustorelacvig;
	}



	public BigDecimal getQtdmultiplovdaecommerce() {
		return qtdmultiplovdaecommerce;
	}



	public void setQtdmultiplovdaecommerce(BigDecimal qtdmultiplovdaecommerce) {
		this.qtdmultiplovdaecommerce = qtdmultiplovdaecommerce;
	}



	public String getPersimiliarecommerce() {
		return persimiliarecommerce;
	}



	public void setPersimiliarecommerce(String persimiliarecommerce) {
		this.persimiliarecommerce = persimiliarecommerce;
	}



	public Date getDatahorintegracaoecommerceestq() {
		return datahorintegracaoecommerceestq;
	}



	public void setDatahorintegracaoecommerceestq(
			Date datahorintegracaoecommerceestq) {
		this.datahorintegracaoecommerceestq = datahorintegracaoecommerceestq;
	}



	public Short getPzovalidlote() {
		return pzovalidlote;
	}



	public void setPzovalidlote(Short pzovalidlote) {
		this.pzovalidlote = pzovalidlote;
	}



	public BigDecimal getSeqprodrelaclote() {
		return seqprodrelaclote;
	}



	public void setSeqprodrelaclote(BigDecimal seqprodrelaclote) {
		this.seqprodrelaclote = seqprodrelaclote;
	}



	public BigDecimal getAliqadjud() {
		return aliqadjud;
	}



	public void setAliqadjud(BigDecimal aliqadjud) {
		this.aliqadjud = aliqadjud;
	}



	public BigDecimal getSeqpessoa() {
		return seqpessoa;
	}



	public void setSeqpessoa(BigDecimal seqpessoa) {
		this.seqpessoa = seqpessoa;
	}
	
	
	


}
