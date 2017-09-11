package br.com.seta.processo.comparators;

import java.io.Serializable;
import java.util.Comparator;

import br.com.seta.processo.dto.InstanciaProcesso;

public class InstanciaProcessoComparators extends AbstractBaseComparators {
	
	public static final Comparator<InstanciaProcesso> POR_CASE_ID = new ComparaPorCaseId();
	public static final Comparator<InstanciaProcesso> POR_PROCESSO = new ComparaPorProcesso();
	public static final Comparator<InstanciaProcesso> POR_STATUS = new ComparaPorStatus();
	public static final Comparator<InstanciaProcesso> POR_DATA_TERMINO = new ComparaPorDataTermino();
	
	public static class ComparaPorCaseId implements Comparator<InstanciaProcesso>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(InstanciaProcesso o1, InstanciaProcesso o2) {
			return compara(o1.getCaseId(), o2.getCaseId());
		}
		
	}
	
	public static class ComparaPorProcesso implements Comparator<InstanciaProcesso>, Serializable{
		private static final long serialVersionUID = 1L;		
		
		@Override
		public int compare(InstanciaProcesso o1, InstanciaProcesso o2) {
			return compara(o1.getNomeProcesso(), o2.getNomeProcesso());
		}
		
	}
	
	public static class ComparaPorStatus implements Comparator<InstanciaProcesso>, Serializable{
		private static final long serialVersionUID = 1L;
		
		@Override
		public int compare(InstanciaProcesso o1, InstanciaProcesso o2) {
			return compara(o1.getStatus(), o2.getStatus());
		}
		
	}
	
	public static class ComparaPorDataTermino implements Comparator<InstanciaProcesso>, Serializable{
		private static final long serialVersionUID = 1L;
		
		@Override
		public int compare(InstanciaProcesso o1, InstanciaProcesso o2) {
			return compara(o1.getFim(), o2.getFim());
		}
		
	}

}
