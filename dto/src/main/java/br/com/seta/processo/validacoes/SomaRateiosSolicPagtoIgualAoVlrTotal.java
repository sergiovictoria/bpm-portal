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
 * Valida se a soma dos rateios não é maior do que o valor total da solicitação de pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=SomaRateiosSolicPagtoIgualAoVlrTotalConstraintValidator.class)
@Documented
public @interface SomaRateiosSolicPagtoIgualAoVlrTotal {
	String message() default "";
	/**
	 * Define se uma lista de rateios vazio ou igual a null deve ser validada. O comportamento padrão é não validar.
	 * @return
	 */
	boolean verificaListaRateiosVazia() default false;
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
