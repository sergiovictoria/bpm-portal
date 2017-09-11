package br.com.seta.processo.validacoes;

import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SePreenchidoVerificaTambemValidator implements
		ConstraintValidator<SePreenchidoVerificaTambem, Object> {

	private String nomeCampo;
	private String[] nomeCamposAVerificar;

	@Override
	public void initialize(SePreenchidoVerificaTambem annotation) {
		this.nomeCampo = annotation.campo();
		this.nomeCamposAVerificar = annotation.camposAVerificar();
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		Class<? extends Object> clazz = obj.getClass();

		try {
			Field campo = getField(clazz, nomeCampo);

			if (nuloOuNaoPreenchido(obj, campo)) {
				return true;
			}

			for (String nomeCampoAVerificar : nomeCamposAVerificar) {
				Field campoAVerificar = getField(clazz, nomeCampoAVerificar);
				if (nuloOuNaoPreenchido(obj, campoAVerificar)) {
					return false;
				}
				
			}

			return true;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Field getField(Class<? extends Object> clazz, String nomeCampo)
			throws NoSuchFieldException, SecurityException {
		Field campo = clazz.getDeclaredField(nomeCampo);
		campo.setAccessible(true);

		return campo;
	}

	private boolean nuloOuNaoPreenchido(Object obj, Field campo) throws IllegalArgumentException, IllegalAccessException {
		Class<?> clazzDoCampo = campo.getType();
		Object valor = campo.get(obj);
		
		if (clazzDoCampo.equals(String.class)) {
			String strValor = (String) valor;
			if (strValor == null || strValor.trim().isEmpty())
				return true;

			return false;
		} else {
			if (valor == null) {
				return true;
			}
			return false;
		}
	}

}
