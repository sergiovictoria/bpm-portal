package br.com.seta.processo.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.seta.processo.classTypes.Classes;


@Target (ElementType.FIELD)
@Retention (RetentionPolicy.RUNTIME)


public @interface ReportColumn {
    public String property() default "";
    public String title() default "Title";
    public Classes colClass() default Classes.STRING;
    public boolean groupingCriteria() default false;
    public boolean sumable() default false;
}