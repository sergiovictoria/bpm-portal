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
 * Valida se a soma dos percentuais dos rateios não ultrapassa 100%
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=SomaPercentualDeRateiosConstraintValidator.class)
@Documented
public @interface SomaPercentualDeRateios {
	String message() default "";
	boolean verificaListaRateiosVazia() default false;
	int percentualMax() default 100;
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
