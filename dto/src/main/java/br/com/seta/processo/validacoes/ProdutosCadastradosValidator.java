package br.com.seta.processo.validacoes;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;

public class ProdutosCadastradosValidator implements ConstraintValidator<ProdutosCadastrados, OrRequisicao> {

	@Override
	public void initialize(ProdutosCadastrados constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(OrRequisicao requisicao, ConstraintValidatorContext context) {
		Set<OrReqitensdespesa> itens = requisicao.getOrReqitensdespesas();
		
		if(itens == null || itens.isEmpty()) return true;
		
		for(OrReqitensdespesa item : itens){
			if(item.getCodproduto() == null || item.getCodproduto().trim().isEmpty())
				return false;
		}
		
		return true;
	}

}
