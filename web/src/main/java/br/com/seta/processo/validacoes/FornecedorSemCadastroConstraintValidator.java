package br.com.seta.processo.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;

public class FornecedorSemCadastroConstraintValidator implements ConstraintValidator<FornecedorSemCadastro, FormularioIntencaoCompraTemplate> {

	@Override
	public void initialize(FornecedorSemCadastro constraintAnnotation) {		
		 
	}

	@Override
	public boolean isValid(FormularioIntencaoCompraTemplate formulario, ConstraintValidatorContext context) {

		OrRequisicao requisicao = formulario.getRequisicao();
		if ((requisicao.getNomeFornecedor() != null && !requisicao.getNomeFornecedor().trim().isEmpty()) && (requisicao.getSeqpessoa() == null || requisicao.getSeqpessoa().equals(0))) {
			if (formulario.getFormularioCadastroFornecedor() == null)
				return false;

			return true;
		}

		return true;

	}

}
