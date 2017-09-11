package br.com.seta.processo.validacoes;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Valida se entre dois campos do tipo Date de uma classe o primeiro é maior (depois) do segundo
 * 
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Target( { TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy=DepoisConstraintValidator.class)
@Documented
public @interface Depois {
	
	String message() default "";	
	/**
	 * Nome do método get que retorna a primeira data
	 * @return
	 */
	String getDate1();
	/**
	 * Nome do método get que retorna a segunda data
	 * @return
	 */
	String getDate2();
	/**
	 * Indica se duas datas iguais são válidas. Valor default é igual a true
	 * @return
	 */
	boolean datasIguaisValidas() default true;
	Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };

}
