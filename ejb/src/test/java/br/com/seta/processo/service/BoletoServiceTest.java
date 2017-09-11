package br.com.seta.processo.service;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.seta.processo.bean.BoletoSolicitacaoPagamentoService;
import br.com.seta.processo.dto.Documento;

@RunWith(Arquillian.class)
public class BoletoServiceTest {
	
	@Deployment
	public static Archive<?> createTestArchive(){
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ExecuteRestAPI.class)
				.addPackages(true, "br.com.seta.processo")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(files);
	}
	
	@Inject
	BoletoSolicitacaoPagamentoService boletoService;
	
	@Test
	public void testeIntegracaoMongoDB(){
		Documento boleto = new Documento();
		boleto.setCaseId(20L);
		byte[] bytes = "teste qualquer coisa".getBytes();
//		boleto.setBoleto(bytes);
		
		boletoService.save(boleto);
	}

}
