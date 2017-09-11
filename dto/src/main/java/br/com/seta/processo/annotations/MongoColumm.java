package br.com.seta.processo.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.seta.processo.classTypes.Classes;

@Target ({ElementType.FIELD, ElementType.METHOD})
@Retention (RetentionPolicy.RUNTIME)

public @interface MongoColumm {
	public String property()  default "";
	public String title()     default "Title";
	public String list()      default  "";
	public Classes colClass() default Classes.STRING;
}
