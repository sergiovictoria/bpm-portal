package br.com.seta.processo.comparators;

import java.io.Serializable;
import java.util.Comparator;

import br.com.seta.processo.dto.Historico;

/**
 * Agrupa os comparadores para a classe <b>br.com.seta.processo.dto.Historico</b>
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class HistoricoComparators extends AbstractBaseComparators{
	
	public static final Comparator<Historico> POR_STATUS = new ComparaPorStatus();
	public static final Comparator<Historico> POR_NOME = new ComparaPorNome();
	public static final Comparator<Historico> POR_DATA = new ComparaPorData();
	public static final Comparator<Historico> POR_MOTIVO = new ComparaPorMotivo();	

	public static class ComparaPorStatus implements Comparator<Historico>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Historico h1, Historico h2) {
			return comparaSemCaseSensitive(h1.getStatus(), h2.getStatus());
		}
		
	}
	
	public static class ComparaPorNome implements Comparator<Historico>, Serializable{
		private static final long serialVersionUID = 1L;
		
		@Override
		public int compare(Historico h1, Historico h2) {
			return comparaSemCaseSensitive(h1.getNome(), h2.getNome());
		}
		
	}
	
	public static class ComparaPorData implements Comparator<Historico>, Serializable{
		private static final long serialVersionUID = 1L;
		
		public int compare(Historico h1, Historico h2) {
			return compara(h1.getData(), h2.getData());
		}
		
	}
	
	public static class ComparaPorMotivo implements Comparator<Historico>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Historico h1, Historico h2) {
			return compara(h1.getMotivo(), h2.getMotivo());
		}
		
	}
	
	
}
