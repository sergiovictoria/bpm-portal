package br.com.seta.processo.validacoes;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida se uma extensão é valida ou não
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Target( { FIELD, METHOD, PARAMETER  })
@Retention(RUNTIME)
@Constraint(validatedBy=ExtensaoValidator.class)
@Documented
public @interface Extensao {	
	/**
	 * Array de Strings contendo as extensões (sem ponto final) a serem validadas.<br> 
	 * Ex: para <b>.jpg</b>, ultilizar <b>jpg</b>.
	 * @return
	 */
	String[] extensoes() default { };
	/**
	 * Define se as extensões são válidas (true) ou não (false)
	 * @return
	 */
	boolean saoValidas() default true;
	boolean aceitaNull() default true;
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
