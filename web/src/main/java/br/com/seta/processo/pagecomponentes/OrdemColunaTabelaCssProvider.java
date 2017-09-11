package br.com.seta.processo.pagecomponentes;

import org.apache.wicket.extensions.ajax.markup.html.repeater.data.sort.AjaxFallbackOrderByLink;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortState;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;

/**
 * Esta classe fornece um CSS para indicar a ordenação das colunas de uma tabela
 * 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@SuppressWarnings("rawtypes")
public class OrdemColunaTabelaCssProvider implements AjaxFallbackOrderByLink.ICssProvider {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public String getClassAttributeValue(ISortState state, Object sortProperty) {
		SortOrder sortOrder = state.getPropertySortOrder(sortProperty);
		
		switch(sortOrder){
			case ASCENDING: return "col-asc";
			case DESCENDING: return "col-desc";
			default: return "col-sem-ordem";
		}
	}
}



	
	
