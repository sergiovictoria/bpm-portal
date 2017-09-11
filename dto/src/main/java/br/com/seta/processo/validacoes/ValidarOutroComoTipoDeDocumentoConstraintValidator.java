package br.com.seta.processo.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.Recebimento;

public class ValidarOutroComoTipoDeDocumentoConstraintValidator implements ConstraintValidator<ValidarOutroComoTipoDeDocumento, Recebimento>{

	@Override
	public void initialize(ValidarOutroComoTipoDeDocumento constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(Recebimento recebimento, ConstraintValidatorContext context) {
		String tipoDocumento = recebimento.getTipoDocumento();
		
		if(tipoDocumento != null && tipoDocumento.equals("Outro")){
			String descricao = recebimento.getDescTipoDocumento();
			if(descricao == null || descricao.trim().isEmpty())
				return false;
			
			return true;
		}
		
		
		return true;
	}

}
