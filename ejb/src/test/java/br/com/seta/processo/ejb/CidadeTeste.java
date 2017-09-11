package br.com.seta.processo.ejb;

import java.io.File;

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

import br.com.seta.processo.bean.Cidades;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.entity.GeCidadeEntity;
import br.com.seta.processo.qualifier.ConsincoDatabase;
import br.com.seta.processo.resource.Resource;



@RunWith(Arquillian.class)
public class CidadeTeste {
	
	
	@Inject
	private Cidades cidades;
		
	
	@Deployment
	public static Archive<?> createTestArchive(){
		
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
		File[] commonsLang = pom.resolve("org.jopendocument:jOpenDocument").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "Insert.war")
				.addClasses(ConsincoDatabase.class,
						Resource.class, NaturezaDespesa.class)
						.addPackages(true, "br.com.seta") 
						.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
						.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
						.addAsLibraries(commonsLang);
	}
	
	
	
	

	@Test
	public void cidadeTest() throws Exception {
		GeCidadeEntity cidadeEntity = new GeCidadeEntity();
		cidadeEntity = 	cidades.getGeCidade("SÃO BERNARDO DO CAMPO","SP");
		System.out.println(" Cidade  ======>>>>> "+cidadeEntity);
	}
	
	

}
