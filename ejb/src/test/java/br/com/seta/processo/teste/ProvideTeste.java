package br.com.seta.processo.teste;

import java.io.File;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.qualifier.ConsincoDatabase;
import br.com.seta.processo.service.Atividades;


@RunWith(Arquillian.class)
public class ProvideTeste {
	
	
	
	@Deployment
	public static Archive<?> createTestArchive() {

		//		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
		//				.importRuntimeDependencies().resolve().withTransitivity().asFile();


		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
		File[] commonsLang = pom.resolve("org.apache.httpcomponents:httpclient","com.google.code.gson:gson").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ConsincoDatabase.class,
						Resource.class, NaturezaDespesa.class)
						.addPackages(true, "br.com.seta.processo")
//						.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
						.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
						.addAsLibraries(commonsLang);
		// Deploy our test datasource
		//.addAsWebInfResource("test-ds.xml", "test-ds.xml");
	}

	@Inject
	private Atividades atividades; 
	

	
	
	@Test
	public void testRegister() throws Exception {
//		atividades.getTextSearch("NSNS");
	}
	


}
