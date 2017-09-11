package br.com.seta.processo.validacoes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.comparators.OrSolicitacaovenctoComparators;
import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.dto.SolicitacaoPagamento;

public class VencMaiorQueDtEmissaoDaSolicitacaoPagtoConstraintValidator implements ConstraintValidator<VencimentoMaiorQueDataEmissaoDaSolicitacaoPagamento, SolicitacaoPagamento> {

	@Override
	public void initialize(VencimentoMaiorQueDataEmissaoDaSolicitacaoPagamento constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(SolicitacaoPagamento solicitacao, ConstraintValidatorContext context) {
		Date dataEmissao = solicitacao.getDataEmissao();
		OrSolicitacaovencto primeiroVencimento = primeiroVencimento(solicitacao.getOrSolicitacaovenctos());
		
		if(dataEmissao == null || primeiroVencimento == null) //Não é necessário a validação
			return true;
		
		
		return ehMenorOuIgual(dataEmissao, primeiroVencimento);
	}

	private boolean ehMenorOuIgual(Date dataEmissao, OrSolicitacaovencto primeiroVencimento) {
		return dataEmissao.compareTo(primeiroVencimento.getDtavencimento()) <= 0;
	}
	
	
	private OrSolicitacaovencto primeiroVencimento(List<OrSolicitacaovencto> vencimentos){
		if(vencimentos == null || vencimentos.isEmpty())
			return null;
		
		ArrayList<OrSolicitacaovencto> vencimentosOrdenados = new ArrayList<OrSolicitacaovencto>(vencimentos);
		Collections.sort(vencimentosOrdenados, OrSolicitacaovenctoComparators.POR_DATA_VENCIMENTO);
		
		return vencimentosOrdenados.get(0);
	}

}
