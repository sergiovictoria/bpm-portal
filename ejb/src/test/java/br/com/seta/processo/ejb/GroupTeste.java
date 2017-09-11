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

import br.com.seta.processo.authorization.AcessoService;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.qualifier.ConsincoDatabase;
import br.com.seta.processo.resource.Resource;
import br.com.seta.processo.service.ExecuteRestAPI;


@RunWith(Arquillian.class)
public class GroupTeste {
	
	
	
	
	@Deployment
	public static Archive<?> createTestArchive(){
		
		PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
		File[] commonsLang = pom.resolve("org.apache.httpcomponents:httpclient","com.google.code.gson:gson", "commons-beanutils:commons-beanutils","org.bonitasoft.engine:bonita-client","org.apache.wicket:wicket-util","org.apache.poi:poi-ooxml","org.jopendocument:jOpenDocument").withTransitivity().asFile();

		return ShrinkWrap.create(WebArchive.class, "Insert.war")
				.addClasses(ConsincoDatabase.class,
						Resource.class, NaturezaDespesa.class)
						.addPackages(true, "br.com.seta") 
						.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
						.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
						.addAsLibraries(commonsLang);
	}
	
	
	@Inject
	private AcessoService  acessoService;
	
	@Inject
	private ExecuteRestAPI executeRestApi;
	
	
	@Test
	public void retriveTasksTest() throws Exception {
        User user = acessoService.loginAs("juliana.harumi","bpm");
		
		User retrunUser = executeRestApi.retriveUserProfessionalData(user);
		
		System.out.println(" retrunUser "+retrunUser.toString());
		
	}
	
	
	

}
