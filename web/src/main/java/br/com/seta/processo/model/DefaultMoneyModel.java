package br.com.seta.processo.model;

import java.math.BigDecimal;
import java.util.Locale;

import org.apache.wicket.model.IModel;

public class DefaultMoneyModel implements IModel<Object>  {

	private static final long serialVersionUID = 1L;
	private static final Locale locale = new Locale( "pt", "BR" );
	@SuppressWarnings("rawtypes")
	private IModel mModel;


	@SuppressWarnings("rawtypes")
	public DefaultMoneyModel(IModel mModel) {
		this.mModel = mModel;
	}

	@Override
	public void detach() {
		mModel.detach();
	}

	@Override
	public String getObject() {
		BigDecimal value = (BigDecimal) mModel.getObject();
		return BigDecimalToMoneySessionFormat.format(value,getSessionLocale(),null);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void setObject(Object object) {
		BigDecimal value = null;
		
		if(object != null){
			value = BigDecimalParser.parse((String)object, getSessionLocale());
		}else{
			value = BigDecimal.ZERO;
		}		
		
		mModel.setObject(value);
	}


	private Locale getSessionLocale() {
		return locale;
	}




}
