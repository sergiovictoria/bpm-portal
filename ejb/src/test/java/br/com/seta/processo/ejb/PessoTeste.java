package br.com.seta.processo.ejb;

import java.io.File;
import java.util.List;

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

import br.com.seta.processo.bean.TransacaoMongo;
import br.com.seta.processo.dto.Erros;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.parse.ParseODS;
import br.com.seta.processo.parse.ParseXLS;
import br.com.seta.processo.service.ExecuteRestAPI;



@RunWith(Arquillian.class)
public class PessoTeste {
	
	
	
	@Deployment
	public static Archive<?> createTestArchive(){
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ExecuteRestAPI.class)
				.addPackages(true, "br.com.seta.processo")
				.addPackages(true, "br.com.seta.soa")
				.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(files);
	}
	
	@Inject
	private ParseXLS parseXLS; 
	
	@Inject
	private ParseODS parseODS; 
	
	
	@Inject 
	private TransacaoMongo transacaoMongoLocal; 
	
	
	@Test
	public void retriveTasksTest() throws Exception {

		FormularioFornecedor formularioFornecedor = parseXLS.parseBuild(new File ("/home/sergio/Documents/Downloads/F-06 Cadastro de Fornecedor.xlsx"));
		System.out.println("formularioFonecedor "+formularioFornecedor.toString());
		List<Erros> erros2 = formularioFornecedor.getErros();
		for (Erros erros : erros2) {
			System.out.println(" Erros "+erros.toString());
		}
		
		transacaoMongoLocal.save(formularioFornecedor, FormularioFornecedor.class);
		
	}

}
