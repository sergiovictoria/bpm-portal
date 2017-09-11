package br.com.seta.processo.validacoes;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;

public class ValorItensRequisicaoValidator implements ConstraintValidator<ItensRequisicao, OrRequisicao>{

	@Override
	public void initialize(ItensRequisicao constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(OrRequisicao requisicao, ConstraintValidatorContext context) {
		Set<OrReqitensdespesa> itens = requisicao.getOrReqitensdespesas();		
		if(itens == null) return true;
		
		boolean ehValido = true;
		context.disableDefaultConstraintViolation();
		for(OrReqitensdespesa item : itens){
			BigDecimal valorItem = item.getVlrtotal();
			
			if(valorItem == null || menorOuIgualAZero(valorItem)){
				String descricao = item.getDescricao();
				
				context
					.buildConstraintViolationWithTemplate("O produto " + descricao + " está com o valor inválido")
					.addConstraintViolation();
				
				ehValido = false;
			}
		}
		
		return ehValido;
	}

	private boolean menorOuIgualAZero(BigDecimal quantidade) {
		return quantidade.compareTo(BigDecimal.ZERO) <= 0;
	}	

}
