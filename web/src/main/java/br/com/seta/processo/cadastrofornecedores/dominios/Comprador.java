package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dados de domínio dos compradores 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Comprador {
	
	private static final List<String> COMPRADORES;
	
	static{
		ArrayList<String> compradores = new ArrayList<String>();
		compradores.add("DIEGO");
		compradores.add("EDUARDO");
		compradores.add("ELIENE");
		compradores.add("ERIKA CORDEIRO");
		compradores.add("MARA");
		compradores.add("RENATA CARDOSO");
		compradores.add("RODRIGO");
		compradores.add("RODEMIR");
		compradores.add("NILEIDE");
		compradores.add("SIRLEIDE");
		compradores.add("TIAGO");
		compradores.add("WALLACE");
		compradores.add("WILTON");
		
		COMPRADORES = Collections.unmodifiableList(compradores);
	}
	
	/**	  
	 * @return Lista imutável contendo os nomes do compradores
	 */
	public static List<String> getCompradores(){
		return COMPRADORES;
	}
}
