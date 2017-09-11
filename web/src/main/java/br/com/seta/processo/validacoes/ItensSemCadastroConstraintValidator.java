package br.com.seta.processo.validacoes;

import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.suprimentos.FormularioIntencaoCompraTemplate;

public class ItensSemCadastroConstraintValidator implements ConstraintValidator<ItensSemCadastro, FormularioIntencaoCompraTemplate> {

	@Override
	public void initialize(ItensSemCadastro constraintAnnotation) {		
		 
	}

	@Override
	public boolean isValid(FormularioIntencaoCompraTemplate formulario,
			ConstraintValidatorContext context) {
		
		OrRequisicao requisicao = formulario.getRequisicao();
		if(existeItensSemCadastro(requisicao.getOrReqitensdespesas())){
			if(formulario.getFormularioCadastroItens() == null)
				return false;
			
			return true;
		}		
		
		return true;
	}
	
	private boolean existeItensSemCadastro(Set<OrReqitensdespesa> itens){
		for(OrReqitensdespesa item : itens){
			String codproduto = item.getCodproduto();
			if(codproduto == null || codproduto.trim().isEmpty())
				return true;
		}
		
		return false;
	}

}
