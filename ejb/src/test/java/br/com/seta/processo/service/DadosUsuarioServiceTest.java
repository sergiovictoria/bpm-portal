package br.com.seta.processo.service;

import java.io.File;

import javax.inject.Inject;

import org.bonitasoft.engine.exception.BonitaException;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.seta.processo.dto.User;

@RunWith(Arquillian.class)
public class DadosUsuarioServiceTest {
	
	private User user;
	
	@Inject
	private DadosUsuarioService usuarioService;
	
	@Deployment
	public static Archive<?> createTestArchive(){
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(DadosUsuarioService.class)
				.addPackages(true, "br.com.seta.processo")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(files);
	}
	
	@Before
	public void executeLogin(){
		user = new User();
		user.setUserName("hromenique.batista");
		user.setPassword("bpm");
		user.setIdUser(50L);
	}
	
	@Test
	public void alteraEmailTest() throws BonitaException{
		String novoEmail = "fulano@gmail.com";
		usuarioService.alteraEmail(user, novoEmail);
		System.out.println("Finalizado teste de troca de email");
	}	
}
