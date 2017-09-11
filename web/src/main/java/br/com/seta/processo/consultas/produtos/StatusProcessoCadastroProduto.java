package br.com.seta.processo.consultas.produtos;

import java.util.ArrayList;
import java.util.List;

public class StatusProcessoCadastroProduto {
	
	public static List<String> listaStatus(){
		List<String> status = new ArrayList<String>();
		status.add("Aprovado");
		status.add("Rejeitado");
		
		return status;
	}
}
