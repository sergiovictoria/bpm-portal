package br.com.seta.processo.validacoes;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class NotEqualsValidator implements ConstraintValidator<NotEquals, Object> {

	private String campo1;
	private String campo2;
	
	@Override
	public void initialize(NotEquals annotation) {
		this.campo1 = annotation.primeiroCampo();
		this.campo2 = annotation.segundoCampo();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Class<? extends Object> classeAlvo = obj.getClass();		
		
		try {
			Field field1 = classeAlvo.getDeclaredField(campo1);
			Field field2 = classeAlvo.getDeclaredField(campo2);
			
			field1.setAccessible(true);
			field2.setAccessible(true);
			
			Object valor1 = field1.get(obj);
			Object valor2 = field2.get(obj);
			
			if(valor1 == null || valor2 == null)
				return true;
			
			return !valor1.equals(valor2);
			
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}		
		
	}

}
