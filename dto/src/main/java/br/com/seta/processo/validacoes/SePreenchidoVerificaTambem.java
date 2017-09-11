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
 * Caso um determinado campo é não nulo/preenchido (String), verifica os demais campos descritos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=SePreenchidoVerificaTambemValidator.class)
@Documented
public @interface SePreenchidoVerificaTambem {
	
	/**
	 * Campo que desencadeará as verificações dos demais campos caso esteja preenchido
	 * @return
	 */
	String campo();
	/**
	 * Campos a serem validados
	 * @return
	 */
	String[] camposAVerificar();
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface 
    List{
    	SePreenchidoVerificaTambem[] value();
    }

}
