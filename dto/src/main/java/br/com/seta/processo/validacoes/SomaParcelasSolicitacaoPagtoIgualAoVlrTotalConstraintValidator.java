package br.com.seta.processo.validacoes;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.dto.SolicitacaoPagamento;

public class SomaParcelasSolicitacaoPagtoIgualAoVlrTotalConstraintValidator 
	implements ConstraintValidator<SomaParcelasSolicitacaoPagtoIgualAoVlrTotal, SolicitacaoPagamento> {

	@Override
	public void initialize(SomaParcelasSolicitacaoPagtoIgualAoVlrTotal constraintAnnotation) {
		
	}

	@Override
	public boolean isValid(SolicitacaoPagamento solicitacao, ConstraintValidatorContext context) {
		List<OrSolicitacaovencto> parcelas = solicitacao.getOrSolicitacaovenctos();
		
		if(parcelas == null || parcelas.isEmpty())
			return false;
		
		BigDecimal valorTotalDasParcelas = calculaValorTotalDasParcelas(parcelas);
		
		return vlrTotalParcelasIgualAoVlrTotalSolicitacaoPgto(solicitacao, valorTotalDasParcelas);
	}

	private boolean vlrTotalParcelasIgualAoVlrTotalSolicitacaoPgto(SolicitacaoPagamento solicitacao,
			BigDecimal valorTotalDasParcelas) {
		return solicitacao.getValor().equals(valorTotalDasParcelas);
	}

	private BigDecimal calculaValorTotalDasParcelas(List<OrSolicitacaovencto> parcelas) {
		BigDecimal vlrTotalParcelas = BigDecimal.ZERO;
		for(OrSolicitacaovencto parcela : parcelas){
			BigDecimal vlrParcela = parcela.getVlrtotal();
			vlrTotalParcelas = vlrTotalParcelas.add(vlrParcela);
		}
		
		return vlrTotalParcelas;
	}

}
