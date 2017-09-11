package br.com.seta.processo.service.gestor.acesso.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
public @interface AcessoModulo {
	String moduloResponsavel();
}
