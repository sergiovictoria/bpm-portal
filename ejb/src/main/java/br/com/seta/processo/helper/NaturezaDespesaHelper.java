package br.com.seta.processo.helper;

import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.CtvHistorico;

public class NaturezaDespesaHelper {
	
	public static CategoriaNaturezaDespesa criaNaturezaDespesa(CtvHistorico ctvHistorico){
		CategoriaNaturezaDespesa naturezaDespesa = new CategoriaNaturezaDespesa();
		
		int id = ctvHistorico.getCodhistorico().intValue();
		String descricao = ctvHistorico.getDescricao();
		String nroplanilha = ctvHistorico.getNroplanilha();
		boolean habilitado = false;
		boolean chaveAcesso = false;
		
		naturezaDespesa.setCodHistorico(id);		
		naturezaDespesa.setDescricao(descricao);
		naturezaDespesa.setNroplanilha(nroplanilha);
		naturezaDespesa.setHabilitado(habilitado);
		naturezaDespesa.setChaveAcesso(chaveAcesso);
		
		return naturezaDespesa;
	}
	
}
