package br.com.seta.processo.comparators;

import java.io.Serializable;
import java.util.Comparator;

import br.com.seta.processo.relatorios.DadosTempoAtendimento;

public class DadosTempoAtendimentoComparators extends AbstractBaseComparators {
	
	public static final Comparator<DadosTempoAtendimento> POR_CASE_ID = new ComparaPorCaseId();
	public static final Comparator<DadosTempoAtendimento> POR_INICIO = new ComparaPorInicio();
	public static final Comparator<DadosTempoAtendimento> POR_TERMINO = new ComparaPorTermino();
	public static final Comparator<DadosTempoAtendimento> POR_USUARIO_INICIADOR = new ComparaPorUsuarioIniciador();
	public static final Comparator<DadosTempoAtendimento> POR_DURACAO = new ComparaPorDuracao();
	
	public static class ComparaPorCaseId implements Comparator<DadosTempoAtendimento>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosTempoAtendimento obj1, DadosTempoAtendimento obj2) {
			return compara(obj1.getCaseId(), obj2.getCaseId());
		}
		
	}
	
	public static class ComparaPorInicio implements Comparator<DadosTempoAtendimento>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosTempoAtendimento obj1, DadosTempoAtendimento obj2) {
			return compara(obj1.getCriacaoInstancia(), obj2.getCriacaoInstancia());
		}
		
	}
	
	public static class ComparaPorTermino implements Comparator<DadosTempoAtendimento>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosTempoAtendimento obj1, DadosTempoAtendimento obj2) {
			return compara(obj1.getFimInstancia(), obj2.getFimInstancia());
		}
		
	}
	
	public static class ComparaPorUsuarioIniciador implements Comparator<DadosTempoAtendimento>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosTempoAtendimento obj1, DadosTempoAtendimento obj2) {
			return comparaSemCaseSensitive(obj1.getUsuarioaIniciador(), obj2.getUsuarioaIniciador());
		}
		
	}
	
	public static class ComparaPorDuracao implements Comparator<DadosTempoAtendimento>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosTempoAtendimento obj1, DadosTempoAtendimento obj2) {
			return compara(obj1.getDuracao(), obj2.getDuracao());
		}
		
	}

}
