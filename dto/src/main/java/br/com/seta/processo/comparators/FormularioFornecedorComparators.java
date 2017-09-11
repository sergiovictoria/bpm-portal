package br.com.seta.processo.comparators;

import java.util.Comparator;

import br.com.seta.processo.dto.FormularioFornecedor;

/**
 * Contém os comparadores para a classe FormularioFornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class FormularioFornecedorComparators {
	
	public static final Comparator<FormularioFornecedor> POR_COD_CONSINCO = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String codC51 = f1.getIdFornecedorC5();
			String codC52 = f2.getIdFornecedorC5();
			
			int valor = verificaValores(codC51, codC52);
			
			if(valor != 0) 
				return valor;
			
			return codC51.compareTo(codC52);
		}
	};
	
	public static final Comparator<FormularioFornecedor> POR_RAZAO_SOCIAL = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String rs1 = f1.getRazaoSocialFornedor();
			String rs2 = f2.getRazaoSocialFornedor();
			
			int valor = verificaValores(rs1, rs2);
			
			if(valor != 0) 
				return valor;
			
			return rs1.compareToIgnoreCase(rs2);
		}
	};
	
	public static final Comparator<FormularioFornecedor> POR_NOME = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String nome1 = f1.getNome();
			String nome2 = f2.getNome();
			
			int valor = verificaValores(nome1, nome2);
			
			if(valor != 0)
				return valor;
			
			return nome1.compareToIgnoreCase(nome2);
		}
	};
	
	public static final Comparator<FormularioFornecedor> POR_CNPJ = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String cnpj1 = f1.getCpnjJuridico();
			String cnpj2 = f2.getCpnjJuridico();
			
			int valor = verificaValores(cnpj1, cnpj2);
			
			if(valor != 0)
				return valor;
			
			return cnpj1.compareTo(cnpj2);
		}
	};
	
	public static final Comparator<FormularioFornecedor> POR_CPF = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String cpf1 = f1.getCpfFisica();
			String cpf2 = f2.getCpfFisica();
			
			int valor = verificaValores(cpf1, cpf2);
			
			if(valor != 0)
				return valor;
			
			return cpf1.compareTo(cpf2);
		}
	};
	
	public static final Comparator<FormularioFornecedor> POR_COMPRADOR = new Comparator<FormularioFornecedor>() {
		
		@Override
		public int compare(FormularioFornecedor f1, FormularioFornecedor f2) {
			String comprador1 = f1.getComprador();
			String comprador2 = f2.getComprador();
			
			int valor = verificaValores(comprador1, comprador2);
			
			if(valor != 0)
				return valor;
			
			return comprador1.compareTo(comprador2);
		}
	};
	
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
