package br.com.seta.processo.validacoes;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Verifica se no mínimo um dos campos é não nulo ou se está preenchido, para o caso de Strings
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=NoMinimoUmPreenchidoValidator.class)
@Documented
public @interface NoMinimoUmPreenchido {
	
	/**
	 * Um lista contendo os nomes do campos a serem validados
	 * @return
	 */
	String[] value() default {};
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
