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
 * Contém a regra para a validação dos valores de somatório das parcelas dos vencimentos (desdobramentos)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=SomaParcelasSolicitacaoPagtoIgualAoVlrTotalConstraintValidator.class)
@Documented
public @interface SomaParcelasSolicitacaoPagtoIgualAoVlrTotal {
	
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
