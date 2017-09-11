package br.com.seta.processo.comparators;

import java.util.Comparator;
import br.com.seta.processo.dto.OrSolicitacaovencto;

public class OrSolicitacaovenctoComparators{
	
	public static final Comparator<OrSolicitacaovencto> POR_DATA_VENCIMENTO = new ComparaPorDataVencimento();
	
	public static class ComparaPorDataVencimento implements Comparator<OrSolicitacaovencto>{

		@Override
		public int compare(OrSolicitacaovencto v1, OrSolicitacaovencto v2) {			
			return ComparatorUtils.compara(v1.getDtavencimento(), v2.getDtavencimento());			
		}
		
	}

}
