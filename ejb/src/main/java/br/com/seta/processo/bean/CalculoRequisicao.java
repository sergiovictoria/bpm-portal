package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.exceptions.CalculoException;
import br.com.seta.processo.exceptions.DesdobramentoException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.WrapperUtils;


@Stateless(name="CalculoRequisicao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class CalculoRequisicao {

	@Inject
	private Logger logger;

	private static final String _SEPARATOR = "/";
	private static final String _MESSAGE   = "OK";

	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Calculos ");
	}

    
	public String parseCalcular(OrRequisicao orRequisicao) throws CalculoException {

		if ( (orRequisicao.getValor().equals(0)) || (orRequisicao.getValor()==null) ) {
			throw new CalculoException("Requisição sem valores !");
		}

		BigDecimal vlrRequsicao  = orRequisicao.getValor();
		BigDecimal vlrdescontos  = orRequisicao.getVlrdescontos();
		BigDecimal vlracrescimos = orRequisicao.getVlracrescimos();
		
		
		BigDecimal vlrRequsicaoDescontoItens  = BigDecimal.ZERO;
		BigDecimal vlrRequsicaoAcrescimoItens = BigDecimal.ZERO;
		BigDecimal vlrRequsicaoTotalItens     = BigDecimal.ZERO;
		BigDecimal vlrLancamentos             = BigDecimal.ZERO;
		
		BigDecimal vlrVencimentosDesconto  = BigDecimal.ZERO;
		BigDecimal vlrVencimentosAcrescimo = BigDecimal.ZERO;
		BigDecimal vlrVencimentosValor     = BigDecimal.ZERO;	
		
		
		/*** Verificando itens de Despesas ***/
		for (OrReqitensdespesa orReqitensdespesa : orRequisicao.getOrReqitensdespesas() ) {
			
			if (orReqitensdespesa.getVlrdesconto() != null) {
				vlrRequsicaoDescontoItens = vlrRequsicaoDescontoItens.add(orReqitensdespesa.getVlrdesconto());
			}
			
			if (orReqitensdespesa.getVlracrescimos() != null) {
				vlrRequsicaoAcrescimoItens = vlrRequsicaoAcrescimoItens	.add(orReqitensdespesa.getVlracrescimos());
			}
			
			if (orReqitensdespesa.getVlrtotal() != null) {
				vlrRequsicaoTotalItens = vlrRequsicaoTotalItens	.add(orReqitensdespesa.getVlrtotal());
			}
			
		}

		
		/*** Verificando vencimentos valores ***/
		for (OrRequisicaovencto orRequisicaovencto : orRequisicao.getOrRequisicaovenctos() ) {
			
			if (orRequisicaovencto.getVlrtotal() != null) {
				vlrVencimentosValor = vlrVencimentosValor.add(orRequisicaovencto.getVlrtotal());
			}
			
			if (orRequisicaovencto.getVlrdesconto() != null) {
				vlrVencimentosDesconto = vlrVencimentosDesconto.add(orRequisicaovencto.getVlrdesconto());
			}
			
			if (orRequisicaovencto.getVlracrescimo() != null) {
				vlrVencimentosAcrescimo = vlrVencimentosAcrescimo.add(orRequisicaovencto.getVlracrescimo());
			}
			
		}		
		
		/**** Verifica Quantidade dos Itens ****/
		
		for (OrReqitensdespesa orReqitensdespesa : orRequisicao.getOrReqitensdespesas() ) {
			if (orReqitensdespesa.getQuantidade()==null) throw new CalculoException("Quantidade de produtos sem valores [ "+orReqitensdespesa.getDescricao()+ " ]");
			if (orReqitensdespesa.getQuantidade().equals(0) || orReqitensdespesa.getQuantidade()==null) throw new CalculoException("Quantidade de produtos sem valores [ "+orReqitensdespesa.getDescricao()+ " ]"); 
		}
		
	
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosValor.doubleValue()==0) {
			throw new CalculoException("Vencimentos sem valores !");
		}
		
		
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosValor.doubleValue() < vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é maior que valor dos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlrVencimentosValor.doubleValue()) + " ] !");
		}
	
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosValor.doubleValue() > vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é menor que valor dos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlrVencimentosValor.doubleValue()) + " ] !");
		}
		
		
		
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosDesconto.doubleValue() > vlrdescontos.doubleValue()) {
			throw new CalculoException("Valor desconto na requisição [ " + WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue())+ " ]  é maior que valor desconto nos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue()) + " ] !");
		}
	
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosDesconto.doubleValue() < vlrdescontos.doubleValue()) {
			throw new CalculoException("Valor desconto na requisição [ " + WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue())+ " ]  é menor que valor desconto nos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue()) + " ] !");
		}
		
		
		
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosDesconto.doubleValue() > vlracrescimos.doubleValue()) {
			throw new CalculoException("Valor acréscimo na requisição [ " + WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue())+ " ]  é menor que valor acréscimo nos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue()) + " ] !");
		}
	
		/**** Verifica valores vencimentos ****/
		if (vlrVencimentosDesconto.doubleValue() < vlracrescimos.doubleValue()) {
			throw new CalculoException("Valor acréscimo na requisição [ " + WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue())+ " ]  é maior que valor acréscimo nos vencimentos [ "+ WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue()) + " ] !");
		}
		
		

		/*** Verificando valor de lançamentos ***/
		for (OrReqplanilhalancto orReqplanilhalancto : orRequisicao.getOrReqplanilhalanctos()) {
			if (orReqplanilhalancto.getVlrlancamento() != null) {
				vlrLancamentos = vlrLancamentos.add(orReqplanilhalancto.getVlrlancamento());
			}
		}

		if (vlrLancamentos.doubleValue() == 0) {
			throw new CalculoException("Planilha de lançamento sem valores !");
		}

		/**** Verifica valores dos Laçamentos ****/
		if (vlrLancamentos.doubleValue() < vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é menor que valor dos Lançamentos [ "+ WrapperUtils.formatarFloatBr(vlrLancamentos.doubleValue()) + " ] !");
		}

		/**** Verifica valores dos Laçamentos ****/
		if (vlrLancamentos.doubleValue() > vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é maior que valor dos Lançamentos [ "+ WrapperUtils.formatarFloatBr(vlrLancamentos.doubleValue()) + " ] !");
		}

		
		/**** Verifica valores dos Itens ****/
		if (vlrRequsicaoTotalItens.doubleValue() == 0) {
			throw new CalculoException("Itens não possui valores !");
		}
				
		/**** Verifica valores dos Itens ****/
		if (vlrRequsicaoTotalItens.doubleValue() > vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é menor que valor dos itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoTotalItens.doubleValue()) + " ] !");
		}

		/**** Verifica valores dos Itens ****/
		if (vlrRequsicaoTotalItens.doubleValue() < vlrRequsicao.doubleValue()) {
			throw new CalculoException("Valor da requisição [ " + WrapperUtils.formatarFloatBr(vlrRequsicao.doubleValue())+ " ]  é maior que valor dos itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoTotalItens.doubleValue()) + " ] !");
		}


		/**** Verifica valores Descontos ****/
		if ( (vlrRequsicaoDescontoItens.doubleValue() > 0) || (vlrdescontos.doubleValue() > 0) ) {

			if (vlrdescontos.doubleValue() > vlrRequsicaoDescontoItens.doubleValue()) {
				throw new CalculoException("Valor desconto na requisição [ "+ WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue())+ " ] é maior que valor de desconto itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoDescontoItens.doubleValue()) + " ] ! ");
			}

			if (vlrdescontos.doubleValue() < vlrRequsicaoDescontoItens.doubleValue()) {
				throw new CalculoException("Valor desconto na requisição [ "+WrapperUtils.formatarFloatBr(vlrdescontos.doubleValue())+ " ] é menor que valor de desconto itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoDescontoItens.doubleValue()) + " ] ! ");
			}

		}

		/**** Verifica valores Acrescimo ****/
		if ((vlrRequsicaoAcrescimoItens.doubleValue() > 0)	|| (vlracrescimos.doubleValue() > 0)) {

			if (vlracrescimos.doubleValue() > vlrRequsicaoAcrescimoItens.doubleValue()) {
				throw new CalculoException("Valor acréscimo na requisição [ "+ WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue())+ " ] é maior que valor de acréscimo nos itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoAcrescimoItens.doubleValue()) + " ] !");
			}


			if (vlracrescimos.doubleValue() < vlrRequsicaoAcrescimoItens.doubleValue()) {
				throw new CalculoException("Valor acréscimo na requisição [ "+ WrapperUtils.formatarFloatBr(vlracrescimos.doubleValue())+ " ] é menor que valor de acréscimo nos itens [ "+ WrapperUtils.formatarFloatBr(vlrRequsicaoAcrescimoItens.doubleValue()) + " ] !");
			}

		}

		return 	_MESSAGE;
	}
	
	public void calculaValorTotalEVencimentos(OrRequisicao requisicao) throws DesdobramentoException{
		calculaValorTotalRequisicao(requisicao);
		calculaVencimentos(requisicao);
	}
	
	public void calculaVencimentos(OrRequisicao requisicao) throws DesdobramentoException{
		Date dataRequsicao = new Date();
		BigDecimal valorTotal = isNull(requisicao.getValor(), BigDecimal.ZERO);
		BigDecimal valorDesconto = isNull(requisicao.getVlrdescontos(), BigDecimal.ZERO);
		BigDecimal valorAcrescimo = isNull(requisicao.getVlracrescimos(), BigDecimal.ZERO);
		BigDecimal valorFrete = isNull(requisicao.getVlrFrete(), BigDecimal.ZERO);
		valorAcrescimo = valorAcrescimo.add(valorFrete);
		
		List<OrRequisicaovencto> vencs = calculaVencimentos(requisicao.getPrazopagto(), dataRequsicao, valorTotal, valorDesconto, valorAcrescimo);
		
		HashSet<OrRequisicaovencto> vencimentos = new HashSet<OrRequisicaovencto>(vencs);
		
		requisicao.setOrRequisicaovenctos(vencimentos);
	}

	public List<OrRequisicaovencto> calculaVencimentos(String parcelas, Date dataRequsicao, BigDecimal valorTotal, BigDecimal valorDesconto, BigDecimal valorAcrescimo) throws DesdobramentoException {

		List<OrRequisicaovencto> orRequisicaovenctos = new ArrayList<OrRequisicaovencto>(0);
				
		if ((parcelas == null) && (dataRequsicao == null) && (valorTotal == null) ) {
			throw new DesdobramentoException("Parcelas, data ou valor estão nulos!");
		}		
		
		StringTokenizer stk = new StringTokenizer(parcelas, _SEPARATOR);
		String  par = ((Integer)stk.countTokens()).toString();
		Short count = 1;			
		BigDecimal parcelasDiv = new BigDecimal(par);

		if(valorTotal == null) return orRequisicaovenctos;			
		if(valorDesconto == null) valorDesconto = BigDecimal.ZERO;
		if(valorAcrescimo == null) valorAcrescimo = BigDecimal.ZERO;			
			
		
		BigDecimal valorTotalParcela, valorDescParcela, valorAcrescParcela;			
		
		// 1-Cálculo das parcelas
		valorTotalParcela = divide(valorTotal, parcelasDiv, RoundingMode.DOWN);			
		valorDescParcela = divide(valorDesconto, parcelasDiv, RoundingMode.DOWN);
		valorAcrescParcela = divide(valorAcrescimo, parcelasDiv, RoundingMode.DOWN);			
		
		// 2-Criação das parcelas (vencimentos)
		while (stk.hasMoreElements()) {				
			Integer days = Integer.parseInt(stk.nextElement().toString());

			OrRequisicaovencto orRequisicaovencto = new OrRequisicaovencto();
			orRequisicaovencto.setVlrtotal(valorTotalParcela);
			orRequisicaovencto.setVlracrescimo(valorAcrescParcela);
			orRequisicaovencto.setVlrdesconto(valorDescParcela);
			orRequisicaovencto.setDtavencimento(WrapperUtils.addDays(dataRequsicao, days));
			orRequisicaovencto.setDtaprogramada(WrapperUtils.addDays(dataRequsicao, days));				
			orRequisicaovencto.setNroparcela(count++);
			orRequisicaovencto.setVlroutoperdesc(BigDecimal.ZERO);
			orRequisicaovenctos.add(orRequisicaovencto);
		}
		
		// 3-Cálculo das diferenças entre as parcelas e o seu valor total
		BigDecimal diferencaVlrTotal = calculaDiferencaoEntreParcelasEValor(valorTotal, parcelasDiv, valorTotalParcela);
		BigDecimal diferencaVlrDesconto = calculaDiferencaoEntreParcelasEValor(valorDesconto, parcelasDiv, valorDescParcela);
		BigDecimal diferencaVlrAcrescimo = calculaDiferencaoEntreParcelasEValor(valorAcrescimo, parcelasDiv, valorAcrescParcela);
		
		// 4-A diferença é adicionada na última parcela
		int ultimoIndice = orRequisicaovenctos.size() - 1;
		OrRequisicaovencto ultimaParcela = orRequisicaovenctos.get(ultimoIndice);

		BigDecimal acrescimo = valorAcrescParcela.add(diferencaVlrAcrescimo);
		BigDecimal desconto = valorDescParcela.add(diferencaVlrDesconto);
		BigDecimal total = valorTotalParcela.add(diferencaVlrTotal);
		
		ultimaParcela.setVlracrescimo(acrescimo);
		ultimaParcela.setVlrdesconto(desconto);
		ultimaParcela.setVlrtotal(total);		


		return orRequisicaovenctos;
	}


	private BigDecimal calculaDiferencaoEntreParcelasEValor(BigDecimal valor, BigDecimal quantParcelas, BigDecimal valorParcela) {
		BigDecimal vlrParcelasXquantParcelas = valorParcela.multiply(quantParcelas);
		return valor.subtract(vlrParcelasXquantParcelas);
	}

	/**
	 *  
	 * Efetua a divisão entre dois números, podendo aplicar alguma tipo de tratamento para dízimas periódicas
	 * 
	 * @param valor Valor  a ser dividido
	 * @param divisor Divisor
	 * @param arrendondamento Um tipo de RoundingMode para ser aplicados para os casos de mais de duas casas decimais
	 * @return O resultado da divisão 
	 * 
	 */
	private BigDecimal divide(BigDecimal valor, BigDecimal divisor, RoundingMode arrendondamento) {
		return valor.divide(divisor, 2, arrendondamento);
	}
	
	/**
	 * Calcula o valor total da requisição
	 * @param requisicao
	 */
	public void calculaValorTotalRequisicao(OrRequisicao requisicao){
		Set<OrReqitensdespesa> orReqitensdespesas = requisicao.getOrReqitensdespesas();
		
		//Cálculo dos itens e valor total
		BigDecimal vlrTotalRequisicao = BigDecimal.ZERO;
		for(OrReqitensdespesa item : orReqitensdespesas){
			BigDecimal quantidade = item.getQuantidade() == null ? BigDecimal.ZERO : item.getQuantidade();
			BigDecimal vlritem = item.getVlritem();
			BigDecimal vlrTotalItem = quantidade.multiply(vlritem);
			item.setVlrtotal(vlrTotalItem);
			
			vlrTotalRequisicao = vlrTotalRequisicao.add(vlrTotalItem);
		}
		
		BigDecimal vlrDescontos = requisicao.getVlrdescontos();
		BigDecimal vlrAcrescimos = requisicao.getVlracrescimos();
		BigDecimal vlrLiquido = vlrTotalRequisicao.add(vlrAcrescimos).subtract(vlrDescontos);
		
		requisicao.setValor(vlrTotalRequisicao);
		requisicao.setVlrliqreq(vlrLiquido);
	}	
	
	/**
	 * Calcula o valor de um lançamento de Rateio de Centro de Custo
	 * 
	 * @param lancto
	 * @param vlrLiquido
	 */
	public void calculaValorRateio(OrReqplanilhalancto lancto, BigDecimal vlrLiquido){
		BigDecimal _100 = new BigDecimal(100);
		BigDecimal percentual = lancto.getPercrateio() == null ? BigDecimal.ZERO : lancto.getPercrateio();
		
		BigDecimal percentualRateio = percentual.divide(_100);
		BigDecimal vlrLancamento = vlrLiquido.multiply(percentualRateio);
		
		lancto.setVlrlancamento(vlrLancamento);
	}
	
	public void calculaValorRateio(Collection<OrReqplanilhalancto> lanctos, BigDecimal vlrLiquido){
		for(OrReqplanilhalancto lancto : lanctos)
			calculaValorRateio(lancto, vlrLiquido);
	}
	
	public void calculaValorRateio(OrRequisicao requisicao){
		calculaValorRateio(requisicao.getOrReqplanilhalanctos(), requisicao.getVlrliqreq());
	}
	
	public <T> T isNull(T value, T replace){
		if(value == null)
			return replace;
		
		return value;
	}

	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Calculos");
	}


	public static void main(String[] args) throws DesdobramentoException {
		CalculoRequisicao dob = new CalculoRequisicao();
		List<OrRequisicaovencto> orRequisicaovenctos = dob.calculaVencimentos("10/20/30",new java.util.Date() ,new BigDecimal("1000.00"), new BigDecimal("100.00"), new BigDecimal("50.00"));
		for (OrRequisicaovencto dto : orRequisicaovenctos) {
			System.out.println(" Quantidade Parcelas "+dto.getNroparcela()+"\n");
			System.out.println(" Vencimento          "+dto.getDtavencimento());
			System.out.println(" Programada          "+dto.getDtaprogramada());
			System.out.println(" Desconto            "+dto.getVlrdesconto());
			System.out.println(" Acescimo            "+dto.getVlracrescimo());
			System.out.println(" Liquido             "+dto.getVlrliquido());
			System.out.println(" Outras Despesas     "+dto.getVlroutoperdesc());
			System.out.println(" Valor Parcela       "+dto.getVlrtotal()+"\n");
		}
	}

}

