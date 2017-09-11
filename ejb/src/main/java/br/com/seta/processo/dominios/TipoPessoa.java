package br.com.seta.processo.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TipoPessoa {

	public static final String JURIDICA = "JURÍDICA";
	public static final String FISICA = "FÍSICA";
	private static final List<String> TIPOS_FORNECEDOR;

	static {
		ArrayList<String> tiposPessoa = new ArrayList<String>();
		tiposPessoa.add(FISICA);
		tiposPessoa.add(JURIDICA);
		TIPOS_FORNECEDOR = Collections.unmodifiableList(tiposPessoa);
	}

	/**
	 * @return Lista imutável contendo os tipos de pessoa do cadastro de
	 *         fornecedor (fisica/juridica)
	 * 
	 */
	public static List<String> getValores() {
		return TIPOS_FORNECEDOR;
	}

}
