package br.com.seta.processo.bean;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.exceptions.DesdobramentoException;


@RunWith(Arquillian.class)

public class DadosFinanceiroFornecedorTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();

		return ShrinkWrap
				.create(WebArchive.class, "test.war")
//				.addPackages(true, "br.com.seta.processo")
//				.addPackages(true, "br.com.seta.soa")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml").addAsLibraries(files);
	}
	
	
	
	@Test
	public void InsertDadosFinanceiro() {
       	System.out.println(" ***************************************************************** ");
	}

}
