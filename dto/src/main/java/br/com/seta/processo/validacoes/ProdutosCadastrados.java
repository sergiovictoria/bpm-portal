
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
 * Verifica se todos os produtos contidos na requisição estão cadastrados
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=ProdutosCadastradosValidator.class)
@Documented
public @interface ProdutosCadastrados {
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
