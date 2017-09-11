package br.com.seta.processo.validacoes;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.OrRequisicaovencto;

public class SomaVencimentosConstraintValidator implements ConstraintValidator<SomaVencimentos, OrRequisicao> {

	@Override
	public void initialize(SomaVencimentos constraintAnnotation) {		
		
	}

	@Override
	public boolean isValid(OrRequisicao requisicao, ConstraintValidatorContext context) {
		BigDecimal valorLiquidoRequisicao = requisicao.getVlrliqreq() == null ? BigDecimal.ZERO : requisicao.getVlrliqreq();
		BigDecimal somaVlrLiquidoDoVencimentos = calculaSomaVlrLiquidoDosVencimentos(requisicao.getOrRequisicaovenctos());		
		BigDecimal valorFrete = requisicao.getVlrFrete() == null ? BigDecimal.ZERO : requisicao.getVlrFrete();
		
		return valorLiquidoRequisicao.add(valorFrete).equals(somaVlrLiquidoDoVencimentos);
	}
	
	private BigDecimal calculaSomaVlrLiquidoDosVencimentos(Set<OrRequisicaovencto> vencimentos){
		BigDecimal total = BigDecimal.ZERO;
		for(OrRequisicaovencto vencto : vencimentos){
			BigDecimal valorLiquido = vencto.getVlrliquido();
			total = total.add(valorLiquido);
		}
		
		return total;
	}

}
