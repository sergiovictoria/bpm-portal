package br.com.seta.processo.parse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Domínio contendo os estados e siglas das UFs
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ParseEstados {
	
	private static final List<String> UFS;
	
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

	}
	
	public static String findUF(String uf) {
		String retorno = "";
		for (String e : UFS) {
			if(e.substring(e.length()-2).contains(uf.substring(uf.length()-2).toUpperCase())) {
				int i = UFS.indexOf(e);
				retorno = UFS.get(i);
			}
		}
		return retorno;
	}

}
