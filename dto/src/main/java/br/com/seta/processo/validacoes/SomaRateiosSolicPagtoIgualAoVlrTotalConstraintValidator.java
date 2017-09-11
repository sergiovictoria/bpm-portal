package br.com.seta.processo.validacoes;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.SolicitacaoPagamento;

public class SomaRateiosSolicPagtoIgualAoVlrTotalConstraintValidator
		implements
		ConstraintValidator<SomaRateiosSolicPagtoIgualAoVlrTotal, SolicitacaoPagamento> {

	private boolean validaListaRateiosVazia;

	@Override
	public void initialize(SomaRateiosSolicPagtoIgualAoVlrTotal constraintAnnotation) {
		this.validaListaRateiosVazia = constraintAnnotation.verificaListaRateiosVazia();
		
	}

	@Override
	public boolean isValid(SolicitacaoPagamento solicitacao, ConstraintValidatorContext context) {
		BigDecimal valorSolicitacao = solicitacao.getValor();
		
		if(valorSolicitacao == null) return true;
		
		List<OrReqplanilhalancto> rateios = solicitacao.getOrReqplanilhalanctos();		
		if( (rateios == null || rateios.isEmpty())  && !validaListaRateiosVazia) 
			return true;		
		
		BigDecimal valorTotalRateios = calculaValorTotalRateios(rateios);		
		
		return valorSolicitacao.equals(valorTotalRateios);
	}

	private BigDecimal calculaValorTotalRateios(List<OrReqplanilhalancto> rateios) {
		BigDecimal vlrTotalRateios = BigDecimal.ZERO;
		for(OrReqplanilhalancto rateio : rateios){
			BigDecimal vlrlancamento = rateio.getVlrlancamento();
			vlrTotalRateios = vlrTotalRateios.add(vlrlancamento);
		}
		
		return vlrTotalRateios;
	}

}
