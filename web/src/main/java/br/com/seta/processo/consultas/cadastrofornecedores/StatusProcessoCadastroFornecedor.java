package br.com.seta.processo.consultas.cadastrofornecedores;

import java.util.ArrayList;
import java.util.List;

public class StatusProcessoCadastroFornecedor {
	
	public static List<String> listaStatus(){
		List<String> status = new ArrayList<String>();
		status.add("Aprovado");
		status.add("Rejeitado");
		
		return status;
	}
}
