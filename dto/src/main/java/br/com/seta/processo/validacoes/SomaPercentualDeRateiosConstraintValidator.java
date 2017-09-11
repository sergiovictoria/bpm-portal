package br.com.seta.processo.validacoes;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicao;

public class SomaPercentualDeRateiosConstraintValidator implements ConstraintValidator<SomaPercentualDeRateios, OrRequisicao> {

	private boolean validaListaRateiosVazia;
	private BigDecimal percentualMax;
	
	@Override
	public void initialize(SomaPercentualDeRateios constraintAnnotation) {
		this.validaListaRateiosVazia = constraintAnnotation.verificaListaRateiosVazia();
		this.percentualMax = new BigDecimal(constraintAnnotation.percentualMax());
	}

	@Override
	public boolean isValid(OrRequisicao requisicao, ConstraintValidatorContext context) {
		Set<OrReqplanilhalancto> lanctos = requisicao.getOrReqplanilhalanctos();
		
		if(lanctos.isEmpty() && !validaListaRateiosVazia) return true;
		
		BigDecimal percentualTotal = BigDecimal.ZERO;
		
		for(OrReqplanilhalancto lancto : lanctos){
			BigDecimal percentual = lancto.getPercrateio();
			percentualTotal = percentualTotal.add(percentual);
		}
		
		return percentualTotal.compareTo(percentualMax) == 0;
	}

}
