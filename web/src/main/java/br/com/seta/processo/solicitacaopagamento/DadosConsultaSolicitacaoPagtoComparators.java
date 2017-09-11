package br.com.seta.processo.solicitacaopagamento;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Conjunto de comparadores para a classe DadosConsultaSolicitacaoPagto
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DadosConsultaSolicitacaoPagtoComparators {
	
	public final static Comparator<DadosConsultaSolicitacaoPagto> POR_ID = new ComparaPorId();
	public static final Comparator<DadosConsultaSolicitacaoPagto> POR_FORNECEDOR = new ComparaPorFornecedor();
	public static final Comparator<DadosConsultaSolicitacaoPagto> POR_NOTA_DESPESA = new ComparaPorNotaDespesa();
	public static final Comparator<DadosConsultaSolicitacaoPagto> POR_VALOR = new ComparaPorValor();
	public static final Comparator<DadosConsultaSolicitacaoPagto> POR_STATUS = new ComparaPorStatus();
	public static final Comparator<DadosConsultaSolicitacaoPagto> POR_SOLICITANTE = new ComparaPorSolicitante();

	private static final class ComparaPorSolicitante implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			String fornecedor1 = sp1.getFornecedor();
			String fornecedor2 = sp2.getFornecedor();
			
			int valor = verificaValores(fornecedor1, fornecedor2);
			if(valor != 0) 
				return valor;
			
			
			return fornecedor1.compareToIgnoreCase(fornecedor2);
		}
	}

	private static final class ComparaPorStatus implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			String status1 = sp1.getStatus();
			String status2 = sp2.getStatus();
			
			int valor = verificaValores(status1, status2);
			if(valor != 0) return valor;
			
			return status1.compareTo(status2);
		}
	}

	private static final class ComparaPorValor implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			BigDecimal valor1 = sp1.getValor();
			BigDecimal valor2 = sp2.getValor();
			
			int valor = verificaValores(valor1, valor2);
			if(valor != 0) return valor;
			
			return valor1.compareTo(valor2);
		}
	}

	private static final class ComparaPorNotaDespesa implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			Long nroNota1 = sp1.getNroNota();
			Long nroNota2 = sp2.getNroNota();			
			int valor = verificaValores(nroNota1, nroNota2);
			if(valor != 0) return valor;
			
			return nroNota1.compareTo(nroNota2);
		}
	}

	private static final class ComparaPorFornecedor implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			String fornecedor1 = sp1.getFornecedor();
			String fornecedor2 = sp2.getFornecedor();
			int valor = verificaValores(fornecedor1, fornecedor2);
			if(valor != 0) return valor;
			
			return fornecedor1.compareToIgnoreCase(fornecedor2);
		}
	}

	private static final class ComparaPorId implements
			Comparator<DadosConsultaSolicitacaoPagto>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosConsultaSolicitacaoPagto sp1, DadosConsultaSolicitacaoPagto sp2) {
			Long caseId1 = sp1.getCaseId();
			Long caseId2 = sp2.getCaseId();		
			int valor = verificaValores(caseId1, caseId2);
			if(valor != 0) return valor;
			
			return caseId1.compareTo(caseId2);
		}
	}

	private static int verificaValores(Object valor1, Object valor2){
		if(saoNulos(valor1, valor2)){
			if(ehNulo(valor1)){
				return -1;
			}else{
				return 1;
			}
		}
		return 0;
	}
	
	private static boolean saoNulos(Object valor1, Object valor2){
		if(valor1 == null || valor2 == null){
			return true;
		}
		return false;
	}
	
	private static boolean ehNulo(Object valor){
		return valor == null ? true : false;
	}

}
