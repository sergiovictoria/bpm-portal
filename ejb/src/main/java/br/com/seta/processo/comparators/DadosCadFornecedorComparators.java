package br.com.seta.processo.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

import br.com.seta.processo.comparators.FormularioFornecedorComparators;
import br.com.seta.processo.consultas.DadosCadFornecedor;
import br.com.seta.processo.dto.FormularioFornecedor;

public class DadosCadFornecedorComparators implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Comparator<DadosCadFornecedor> POR_CASE_ID = new ComparaPorCaseId();
	
	public static final Comparator<DadosCadFornecedor> POR_COD_CONSINCO = new ComparaPorCodConsinco();
	
	public static final Comparator<DadosCadFornecedor> POR_DATA = new ComparaPorData();
	
	public static final Comparator<DadosCadFornecedor> POR_RAZAO_SOCIAL = new ComparaPorRazaoSocial();
	
	public static final Comparator<DadosCadFornecedor> POR_NOME = new ComparaPorNome();
	
	public static final Comparator<DadosCadFornecedor> POR_CNPJ = new ComparaPorCnpj();
	
	public static final Comparator<DadosCadFornecedor> POR_CPF = new ComparaPorCpf();
	
	public static final Comparator<DadosCadFornecedor> POR_COMPRADOR = new ComparaPorComprador();
	
	public static final Comparator<DadosCadFornecedor> POR_STATUS = new ComparaPorStatus();
	
	/**
	 * Compara os dados de FormularioFornecedor de dois DadosRelCadFornecedor
	 * 
	 * @param dados1
	 * @param dados2
	 * @param comparator Objeto que implementa a interface Comparator<FormularioFornecedor> que possui o critério de comparação para o tipo FormularioFornecedor
	 * @return
	 */
	private static int comparaFormulariosFornecedorDe(DadosCadFornecedor dados1, DadosCadFornecedor dados2, Comparator<FormularioFornecedor> comparator){
		FormularioFornecedor f1 = dados1.getFormularioFornecedor();
		FormularioFornecedor f2 = dados2.getFormularioFornecedor();
		
		int valor = verificaValores(f1, f2);
		if(valor != 0)
			return valor;
		
		return comparator.compare(f1, f2);
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

	private static final class ComparaPorStatus implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			String status1 = dados1.getStatus();
			String status2 = dados2.getStatus();
			
			int valor = verificaValores(status1, status2);
			
			if(valor == 0)
				return valor;
			
			return status1.compareToIgnoreCase(status2);
		}
	}

	private static final class ComparaPorComprador implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_COMPRADOR);
		}
	}

	private static final class ComparaPorCpf implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_CPF);
		}
	}

	private static final class ComparaPorCnpj implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_CNPJ);
		}
	}

	private static final class ComparaPorNome implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_NOME);
		}
	}

	private static final class ComparaPorRazaoSocial implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_RAZAO_SOCIAL);
		}
	}

	private static final class ComparaPorData implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;
	
		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			Date inicio1 = dados1.getInicio();
			Date inicio2 = dados2.getInicio();
			
			int valor = verificaValores(inicio1, inicio2);
			if(valor != 0)
				return valor;
			
			return inicio1.compareTo(inicio2);
		}
	}

	private static final class ComparaPorCodConsinco implements Comparator<DadosCadFornecedor>, Serializable {	
		private static final long serialVersionUID = 1L;
	
		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {			
			return comparaFormulariosFornecedorDe(dados1, dados2, FormularioFornecedorComparators.POR_COD_CONSINCO);
		}
	}

	private static final class ComparaPorCaseId implements Comparator<DadosCadFornecedor>, Serializable {
		private static final long serialVersionUID = 1L;
	
		@Override
		public int compare(DadosCadFornecedor dados1, DadosCadFornecedor dados2) {
			Long caseId1 = dados1.getCaseId();
			Long caseId2 = dados2.getCaseId();
			
			int valor = verificaValores(caseId1, caseId2);
			
			if(valor != 0)
				return valor;
			
			
			return caseId1.compareTo(caseId2);
		}
	}

}
