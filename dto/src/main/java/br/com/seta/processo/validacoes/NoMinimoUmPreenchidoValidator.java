package br.com.seta.processo.validacoes;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class NoMinimoUmPreenchidoValidator implements ConstraintValidator<NoMinimoUmPreenchido, Object> {

	private String[] nomeCampos;
	
	@Override
	public void initialize(NoMinimoUmPreenchido annotation) {
		this.nomeCampos = annotation.value();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Class<? extends Object> clazz = obj.getClass();

		for (String nomeCampo : nomeCampos) {

			try {
				Field campo = clazz.getDeclaredField(nomeCampo);
				campo.setAccessible(true);
				Object valor = campo.get(obj);
				Class<?> clazzDoCampo = campo.getType();
				
				if(clazzDoCampo.equals(String.class)){
					String strValor = (String) valor;
					if(strValor != null && !strValor.trim().isEmpty())
						return true;
				}else{
					if (valor != null)
						return true;
				}				

			} catch (NoSuchFieldException | SecurityException
					| IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}

		return false;
	}

}
