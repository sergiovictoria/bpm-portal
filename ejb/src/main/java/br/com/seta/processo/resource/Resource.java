package br.com.seta.processo.resource;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.logging.Logger;

import br.com.seta.processo.qualifier.ConsincoDatabase;


public class Resource implements Serializable {


	private static final long serialVersionUID = 1L;

	@Produces
	@PersistenceContext(unitName="CONSINCO")
	@ConsincoDatabase
	private EntityManager entityManager;



	@Produces
	public Logger produceLog(InjectionPoint injectionPoint) {
		return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	}




}
