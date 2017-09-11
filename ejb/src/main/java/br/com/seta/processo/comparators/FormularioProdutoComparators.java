package br.com.seta.processo.comparators;

import java.util.Comparator;

import br.com.seta.processo.dto.FormularioProduto;

public class FormularioProdutoComparators extends AbstractBaseComparators {
	
	public static final Comparator<FormularioProduto> POR_ID_FORNECEDOR = new ComparaPorIdFornecedor();
	public static final Comparator<FormularioProduto> POR_ID_PRODUTO = new ComparaPorIdProduto();
	public static final Comparator<FormularioProduto> POR_DESC_PRODUTO = new ComparaPorDescProduto();
	public static final Comparator<FormularioProduto> POR_COD_NCM = new ComparaPorCodNCM();
	public static final Comparator<FormularioProduto> POR_DESC_NCM = new ComparaPorDescNCM();
	public static final Comparator<FormularioProduto> POR_EAN_UNIDADE = new ComparaPorEanUnidade();
	
	public static class ComparaPorIdFornecedor implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return compara(o1.getSeqfornecedor(), o2.getSeqfornecedor());
		}
		
	}
	
	public static class ComparaPorEanUnidade implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return compara(o1.getEanUnidade(), o2.getEanUnidade());
		}
		
	}
	
	public static class ComparaPorIdProduto implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return compara(o1.getCodProduto(), o2.getCodProduto());
		}
		
	}
	
	public static class ComparaPorDescProduto implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return comparaSemCaseSensitive(o1.getDescCompleta(), o2.getDescCompleta());
		}
		
	}
	
	public static class ComparaPorCodNCM implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return compara(o1.getCnm(), o2.getCnm());
		}
		
	}
	
	public static class ComparaPorDescNCM implements Comparator<FormularioProduto>{

		@Override
		public int compare(FormularioProduto o1, FormularioProduto o2) {
			return comparaSemCaseSensitive(o1.getDescricaoNcm(), o2.getDescricaoNcm());
		}
		
	}
	
}
