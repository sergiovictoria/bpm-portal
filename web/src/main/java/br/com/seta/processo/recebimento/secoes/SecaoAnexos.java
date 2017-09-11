package br.com.seta.processo.recebimento.secoes;

import java.util.Arrays;

import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.pagecomponentes.Anexos;
import br.com.seta.processo.provider.QueryAnexosProvider;

public class SecaoAnexos extends Panel {
	private static final long serialVersionUID = 1L;

	private Anexos anexos;
	private QueryAnexosProvider provider;
	
	private static final String QUERY_BUSCA  = "{'caseId' : {$in : <#ids>} }";
	
	public SecaoAnexos(String id, long... caseIds) {
		super(id);
		
		String query = QUERY_BUSCA.replaceFirst("<#ids>", toStringArray(caseIds));
		provider = new QueryAnexosProvider(query);
		anexos = new Anexos("anexos", provider);
		
		add(anexos);
	}
	
	public void setQuery(String query){
		provider.setQuery(query);
	}
	
	private String toStringArray(long[] valores){
		return Arrays.toString(valores);
		
		
//		String[] strArr = new String[valores.length];
//		
//		for(int i=0; i < strArr.length; i++)
//			strArr[i] = String.valueOf(valores[i]);		
//		
//		return strArr;
	}
	
}
