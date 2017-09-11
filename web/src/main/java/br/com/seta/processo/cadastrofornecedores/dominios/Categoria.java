package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio contendo as categorias do Cadastro de Fornecedores
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Categoria {
	
	private static final List<String> CATEGORIAS;
	
	static{
		ArrayList<String> categorias = new ArrayList<String>();
		categorias.add("BAZAR");
		categorias.add("BEBIDAS ALCOOLICAS");
		categorias.add("BEBIDAS NÃO ALCOOLICAS");
		categorias.add("DESCARTAVEIS");
		categorias.add("HIGIENE SAUDE BELEZA");
		categorias.add("INSTITUCIONAL");
		categorias.add("LIMPEZA");
		categorias.add("MATINAIS");
		categorias.add("MERCEARIA ALTO GIRO");
		categorias.add("MERCEARIA DOCE");
		categorias.add("MERCEARIA SALGADA");
		categorias.add("PERECÍVEIS");
		
		CATEGORIAS = Collections.unmodifiableList(categorias);
	}
	
	/**
	 * 
	 * @return Lista imutável contendo as categorias do cadastro de fornecedores
	 */
	public static List<String> getCategorias(){
		return CATEGORIAS;
	}

}
