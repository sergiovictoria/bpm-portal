package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contém o domínio SIM/NÃO de valores
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class SimNao {
	
	private static final List<String> VALORES;
	public static final String SIM = "SIM";
	public static final String NAO = "NÃO";
	
	static{
		ArrayList<String> valores = new ArrayList<String>();
		valores.add(SIM);
		valores.add(NAO);
		
		VALORES = Collections.unmodifiableList(valores);
	}
	
	public static List<String> getValores(){
		return VALORES;
	}
}
