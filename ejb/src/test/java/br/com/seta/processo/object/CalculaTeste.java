//package br.com.seta.processo.object;
//
//import java.io.File;
//import java.math.BigDecimal;
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.resource.ResourceException;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.Archive;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//import org.jboss.shrinkwrap.resolver.api.maven.Maven;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import br.com.seta.processo.bean.Cfops;
//import br.com.seta.processo.bean.Desdobras;
//import br.com.seta.processo.bean.Teste;
//import br.com.seta.processo.dto.Valor;
//import br.com.seta.processo.exceptions.DesdobramentoException;
//import br.com.seta.processo.service.ExecuteRestAPI;
//import br.com.seta.soa.dto.Cfop;
//
//
//
//@RunWith(Arquillian.class)
//public class CalculaTeste {
//
//
//	@Deployment
//	public static Archive<?> createTestArchive(){
//		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
//
//		return ShrinkWrap.create(WebArchive.class, "test.war")
//				.addClasses(ExecuteRestAPI.class)
//				.addPackages(true, "br.com.seta.processo")
//				.addPackages(true, "br.com.seta.soa")
//				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
//				.addAsResource("META-INF/persistence.xml","META-INF/persistence.xml")
//				.addAsLibraries(files);
//	}	
//
//	@Inject
//	private Desdobras desdobras; 
//	
//	@Inject
//	private Cfops cfopsService;
//	
//	@Inject
//	private Teste teste;
//
//
//	@Test
//	public void calc() throws DesdobramentoException, ResourceException {
//		
//		teste.load();
//
//
////		List<Valor> valors = desdobras.parse("10",new java.util.Date() ,new BigDecimal("100.00"));
////		for (Valor valor : valors) {
////			System.out.println(" /////////////////// ");
////			System.out.println("  "+valor.getParcela());
////			System.out.println("  "+valor.getDataDesdobramento());
////			System.out.println("  "+valor.getValorTotal());
////			System.out.println("  "+valor.getValor());
////			System.out.println("  "+valor.getLinha());
////			System.out.println(" /////////////////// ");
////		}
//
//	}
//
//
//	public static void main(String[] args) throws DesdobramentoException, ResourceException {
//		new CalculaTeste().calc();
//	}
//
//}
