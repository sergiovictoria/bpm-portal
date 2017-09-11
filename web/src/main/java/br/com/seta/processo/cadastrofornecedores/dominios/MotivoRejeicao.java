package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio contendo os motivos de rejeição de um cadastro de fornecedor
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class MotivoRejeicao {
	
	private final static List<String> MOTIVOS_REJEICAO;
	
	static{
		ArrayList<String> motivosRejeicao = new ArrayList<String>();
		motivosRejeicao.add("Não autorizado pela Direção Comercial / Sem interesse");
		motivosRejeicao.add("Não autorizado pela Direção Comercial / Falta informações p/ Negociação");
		motivosRejeicao.add("Não autorizado pela Direção Comercial e/ou Comprador / Excesso de Mix");
		motivosRejeicao.add("Informações cadastrais incompletas ou inconsistentes");
		motivosRejeicao.add("Outros");
		
		MOTIVOS_REJEICAO = Collections.unmodifiableList(motivosRejeicao);
	}
	
	/**
	 * 
	 * @return Uma lista imutável contendo os motivos de rejeição
	 */
	public static List<String> getMotivosRejeicao(){
		return MOTIVOS_REJEICAO;
	}

}
