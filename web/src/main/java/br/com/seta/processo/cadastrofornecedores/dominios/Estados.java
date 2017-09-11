package br.com.seta.processo.cadastrofornecedores.dominios;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio contendo os estados e siglas das UFs
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class Estados {
	
	private static final List<String> UFS;
	private static final List<String> ESTADOS;
	
	static{
		ArrayList<String> ufs = new ArrayList<String>();
		ufs.add("ACRE/AC");  
		ufs.add("ALAGOAS/AL");  
		ufs.add("AMAZONAS/AM");  
		ufs.add("AMAPÁ/AP");  
		ufs.add("BAHIA/BA");  
		ufs.add("CEARÁ/CE");  
		ufs.add("DISTRITO FEDERAL/DF");  
		ufs.add("ESPIRITO SANTO/ES");  
		ufs.add("GOIAS/GO");  
		ufs.add("MARANHÃO/MA");  
		ufs.add("MINAS GERAIS/MG");  
		ufs.add("MATO GROSSO SUL/MS");  
		ufs.add("MATO GROSSO/MT");  
		ufs.add("PARÁ/PA");  
		ufs.add("PARAIBA/PB");  
		ufs.add("PERNANBUCO/PE");  
		ufs.add("PIAUI/PI");  
		ufs.add("PARANA/PR");  
		ufs.add("RIO DE JANEIRO/RJ");  
		ufs.add("RIO GRANDE DO NORTE/RN");  
		ufs.add("RONDÔNIA/RO");  
		ufs.add("RORAIMA/RR");  
		ufs.add("RIO GRANDE DO SUL/RS");  
		ufs.add("SANTA CATARINA/SC");  
		ufs.add("SERGIPE/SE");  
		ufs.add("SÃO PAULO/SP");  
		ufs.add("TOCANTINS/TO");  
		UFS = Collections.unmodifiableList(ufs);
		
		ArrayList<String> estados = new ArrayList<String>();
		estados.add("Acre");  
		estados.add("Alagoas");  
		estados.add("Amazonas");  
		estados.add("Amapá");  
		estados.add("Bahia");  
		estados.add("Ceará");  
		estados.add("Distrito Federal");  
		estados.add("Espirito Santo");  
		estados.add("Goias");  
		estados.add("Maranhão");  
		estados.add("Minas Gerais");  
		estados.add("Mato Grosso Sul");  
		estados.add("Mato Grosso");  
		estados.add("Pará");  
		estados.add("Paraiba");  
		estados.add("Pernanbuco");  
		estados.add("Piaui");  
		estados.add("Parana");  
		estados.add("Rio de Janeiro");  
		estados.add("Rio Grande do Norte");  
		estados.add("Rondônia");  
		estados.add("Roraima");  
		estados.add("Rio Grande do Sul");  
		estados.add("Santa Catarina");  
		estados.add("Sergipe");  
		estados.add("São Paulo");  
		estados.add("Tocantins");  
		
		ESTADOS = Collections.unmodifiableList(estados);
	}
	
	public static List<String> getUFs(){
		return UFS;
	}
	
	public static List<String> getEstados(){
		return ESTADOS;
	}

}
