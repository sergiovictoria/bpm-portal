package br.com.seta.processo.helper;

import java.util.HashSet;
import java.util.Set;

import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.FontePagadora;
import br.com.seta.processo.entity.GeEmpresaEntity;

public class FontePagadoraHelper {
	
	public static FontePagadora criaFontePagadora(GeEmpresaEntity empresa){
		FontePagadora fontePagadora = new FontePagadora();		
		
		int nroEmpresa = empresa.getNroempresa();
		String nomeReduzido = empresa.getNomereduzido();
		String razaoSocial = empresa.getRazaosocial();
		long nroCGC = empresa.getNrocgc();
		byte digitoCGC = empresa.getDigcgc();
		boolean habilitado = false;
		Set<CategoriaNaturezaDespesa> naturezasDespesa = new HashSet<CategoriaNaturezaDespesa>();
		
		fontePagadora.setNroEmpresa(nroEmpresa);
		fontePagadora.setNomeReduzido(nomeReduzido);
		fontePagadora.setRazaoSocial(razaoSocial);
		fontePagadora.setNroCGC(nroCGC);
		fontePagadora.setDigitoCGC(digitoCGC);
		fontePagadora.setHabilitado(habilitado);
		fontePagadora.setNaturezasDespesa(naturezasDespesa);
		
		return fontePagadora;
	}

}
