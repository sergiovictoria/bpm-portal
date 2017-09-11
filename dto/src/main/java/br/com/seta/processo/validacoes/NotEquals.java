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
 * Valida se dois campos são diferentes ou não. Os dois campos serão comparados utilizando o método equals() e devem ser do mesmo tipo.
 * Caso um ou os dois campos sejam nulos (null), não serão validados.
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=NotEqualsValidator.class)
@Documented
public @interface NotEquals {
	/**
	 * O nome do primeiro campo
	 * @return
	 */
	String primeiroCampo();
	/**
	 * O nome do segundo campo
	 * @return
	 */
	String segundoCampo();
	String message() default "";
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    
    @Target({TYPE, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface 
    List{
    	NotEquals[] value();
    }
}
