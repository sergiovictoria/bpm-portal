package br.com.seta.processo.validacoes;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=ValidarOutroComoTipoDeDocumentoConstraintValidator.class)
@Documented
public @interface ValidarOutroComoTipoDeDocumento {
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
