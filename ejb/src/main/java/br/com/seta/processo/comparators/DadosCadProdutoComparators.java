package br.com.seta.processo.comparators;

import java.io.Serializable;
import java.util.Comparator;

import br.com.seta.processo.consultas.DadosCadastroProduto;
import br.com.seta.processo.dto.FormularioProduto;

public class DadosCadProdutoComparators extends AbstractBaseComparators {
	
	public static final Comparator<DadosCadastroProduto> POR_CASE_ID = new ComparaPorCaseId();
	public static final Comparator<DadosCadastroProduto> POR_DATA = new ComparaPorData();
	public static final Comparator<DadosCadastroProduto> POR_ID_FORNECEDOR = new ComparaPorIdFornecedor();
	public static final Comparator<DadosCadastroProduto> POR_EAN_UNIDADE = new ComparaPorEanUnidade();
	public static final Comparator<DadosCadastroProduto> POR_ID_PRODUTO = new ComparaPorIdProduto();
	public static final Comparator<DadosCadastroProduto> POR_DESC_PRODUTO = new ComparaPorDescProduto();
	public static final Comparator<DadosCadastroProduto> POR_COD_NCM = new ComparaPorCodNCM();
	public static final Comparator<DadosCadastroProduto> POR_DESC_NCM = new ComparaPorDescNCM();
	public static final Comparator<DadosCadastroProduto> POR_STATUS = new ComparaPorStatus();
	
	private static int compara(DadosCadastroProduto o1, DadosCadastroProduto o2, Comparator<FormularioProduto> comparator) {
		return compara(o1.getFormularioProduto(), o2.getFormularioProduto(), comparator);
	}
	
	public static class ComparaPorCaseId implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1.getCaseId(), o2.getCaseId());
		}
		
	}
	
	public static class ComparaPorData implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1.getInicio(), o2.getInicio());
		}
		
	}
	
	public static class ComparaPorIdFornecedor implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_ID_FORNECEDOR);
		}		
	}
	
	public static class ComparaPorEanUnidade implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_EAN_UNIDADE);
		}
		
	}
	
	public static class ComparaPorIdProduto implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_ID_PRODUTO);
		}
		
	}
	
	public static class ComparaPorDescProduto implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_DESC_PRODUTO);
		}
		
	}
	
	public static class ComparaPorCodNCM implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_COD_NCM);
		}
		
	}
	
	public static class ComparaPorDescNCM implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1, o2, FormularioProdutoComparators.POR_DESC_NCM);
		}
		
	}
	
	public static class ComparaPorStatus implements Comparator<DadosCadastroProduto>, Serializable{
		private static final long serialVersionUID = 1L;

		@Override
		public int compare(DadosCadastroProduto o1, DadosCadastroProduto o2) {
			return compara(o1.getStatus(), o2.getStatus());
		}
		
	}
}
