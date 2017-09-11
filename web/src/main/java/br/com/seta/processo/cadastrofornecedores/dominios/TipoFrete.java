package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio contendo os tipos de frete do cadastro de fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class TipoFrete {
	
	private static final List<String> TIPOS_FRETE;
	
	static{
		ArrayList<String> tiposFrete = new ArrayList<String>();
		tiposFrete.add("CIF");
		tiposFrete.add("FOB");
		TIPOS_FRETE = Collections.unmodifiableList(tiposFrete);
	}
	
	/** 
	 * @return Lista imutável contendo os tipos de frete do cadastro de fornecedor
	 */
	public static List<String> getTiposFrete(){
		return TIPOS_FRETE;
	}

}
