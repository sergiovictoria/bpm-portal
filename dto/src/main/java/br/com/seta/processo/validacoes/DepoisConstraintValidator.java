package br.com.seta.processo.validacoes;

import java.lang.reflect.Method;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DepoisConstraintValidator implements ConstraintValidator<Depois, Object> {	
		
	private String getDate1;
	private String getDate2;
	private boolean datasIguaisValidas;

	@Override
	public void initialize(Depois constraintAnnotation) {		
		getDate1 = constraintAnnotation.getDate1();
		getDate2 = constraintAnnotation.getDate2();
		datasIguaisValidas = constraintAnnotation.datasIguaisValidas();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		Class<? extends Object> classeAlvo = object.getClass();
		Method metodo1;
		Method metodo2;
		try {
			
			metodo1 = classeAlvo.getMethod(getDate1, new Class[0]);
			metodo2 = classeAlvo.getMethod(getDate2, new Class[0]);
			Date data1 = (Date) metodo1.invoke(object);
			Date data2 = (Date) metodo2.invoke(object);
			
			if(data1 == null || data2 == null){
				return true;
			}
			
			if(datasIguaisValidas){
				return data1.after(data2) || data1.equals(data2);
			}
			
			return data1.after(data2);
			
		} catch (Throwable e) {			
			e.printStackTrace();
		}
		
		return false;
	}
}
