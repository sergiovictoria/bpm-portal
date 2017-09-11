package br.com.seta.processo.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.Recebimento;

public class ValidaDivergenciasConstraintValidator implements ConstraintValidator<ValidaDivergencias, Recebimento>{

	@Override
	public void initialize(ValidaDivergencias annotation) {
		
	}

	@Override
	public boolean isValid(Recebimento recebimento, ConstraintValidatorContext context) {
		
		String possuiDivergencias = recebimento.getIsDivergencia();
		possuiDivergencias = possuiDivergencias == null ? "NÃO" : possuiDivergencias;
		
		if(possuiDivergencias.equals("SIM")){
			if(recebimento.getMotivoOuJustificativaDivergencia() == null || recebimento.getMotivoOuJustificativaDivergencia().trim().isEmpty()){
				return false;
			}
			
			if(recebimento.getTipoDivergencia() == null || recebimento.getTipoDivergencia().trim().isEmpty()){				
				return false;
			}
		}
		
		return true;
	}
	
	

}
