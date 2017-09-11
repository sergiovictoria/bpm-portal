package br.com.seta.processo.model;

import java.math.BigDecimal;

import org.apache.wicket.model.IModel;

/**
 * Model que para tratamento de BigDecimal iguais a zero.
 * 
 * @author Hromenique Cezniowscki Leite Batista
 */
public class DefaultZeroBigDecimalModel implements IModel<String> {
	private static final long serialVersionUID = 1L;

	private IModel<BigDecimal> model;

	@Override
	public void detach() {

	}

	public DefaultZeroBigDecimalModel(IModel<BigDecimal> model) {
		this.model = model;
	}

	@Override
	public String getObject() {
		return model.getObject().toPlainString();
	}

	@Override
	public void setObject(String object) {
		BigDecimal valor;

		if (object == null) {
			valor = BigDecimal.ZERO;
		} else {
			valor = BigDecimalParser.parse(object);
		}

		model.setObject(valor);

	}

}
