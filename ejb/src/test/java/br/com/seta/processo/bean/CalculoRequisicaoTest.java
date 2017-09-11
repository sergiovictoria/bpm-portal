package br.com.seta.processo.bean;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

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

import static org.junit.Assert.*;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.exceptions.DesdobramentoException;


@RunWith(Arquillian.class)
public class CalculoRequisicaoTest {
	
	@Deployment
	public static Archive<?> createTestArchive() {
		File[] files = Maven.resolver().loadPomFromFile("pom.xml")
				.importRuntimeDependencies().resolve().withTransitivity()
				.asFile();

		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				//.addClasses(ExecuteRestAPI.class)
				.addPackages(true, "br.com.seta.processo")
				.addPackages(true, "br.com.seta.soa")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml").addAsLibraries(files);
	}
	
	@Inject
	private CalculoRequisicao calculo;
	private String parcelas;
	private Date dataRequisicao;
	
	private BigDecimal valorTotalRedondo;
	private BigDecimal valorTotalQuebrado;
	
	private BigDecimal valorDescontoRedondo;
	private BigDecimal valorDescontoQuebrado;
	
	private BigDecimal valorAcrescimoRedondo;
	private BigDecimal valorAcrescimoQuebrado;
	
	@Before
	public void inicia(){
		calculo = new CalculoRequisicao();
		parcelas = "010/020/030";
		dataRequisicao = new Date();		
		
		valorTotalRedondo = new BigDecimal(999.00);
		valorTotalQuebrado = new BigDecimal(1000.00);
		
		valorDescontoRedondo = new BigDecimal(99.00);
		valorDescontoQuebrado = new BigDecimal(100.00);
		
		valorAcrescimoRedondo = new BigDecimal(48.00);
		valorAcrescimoQuebrado = new BigDecimal(50.00);
		
		
	}	
	
	@Test
	public void calculoRedondoTest() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalRedondo, valorDescontoRedondo, valorAcrescimoRedondo);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 3; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(new BigDecimal(16.0), vencimentos.get(i).getVlracrescimo());
			assertEquals(new BigDecimal(33.0), vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0 + 16.0 - 33.0), vencimentos.get(i).getVlrliquido());
		}		
	}
	
	@Test
	public void calculoComDescontoQuebrado() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalRedondo, valorDescontoQuebrado, valorAcrescimoRedondo);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 2; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(new BigDecimal(16.0), vencimentos.get(i).getVlracrescimo());
			assertEquals(new BigDecimal(33.0), vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0 + 16.0 - 33.0), vencimentos.get(i).getVlrliquido());
		}
		
		assertEquals(new BigDecimal(333.0), vencimentos.get(2).getVlrtotal());
		assertEquals(new BigDecimal(16.0), vencimentos.get(2).getVlracrescimo());
		assertEquals(new BigDecimal(34.0), vencimentos.get(2).getVlrdesconto());
		assertEquals(new BigDecimal(333.0 + 16.0 - 34.0), vencimentos.get(2).getVlrliquido());
	}
	
	@Test
	public void calculoComAcrescimoQuebrado() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalRedondo, valorDescontoRedondo, valorAcrescimoQuebrado);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 2; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(new BigDecimal(16.0), vencimentos.get(i).getVlracrescimo());
			assertEquals(new BigDecimal(33.0), vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0 + 16.0 - 33.0), vencimentos.get(i).getVlrliquido());
		}
		
		assertEquals(new BigDecimal(333.0), vencimentos.get(2).getVlrtotal());
		assertEquals(new BigDecimal(18.0), vencimentos.get(2).getVlracrescimo());
		assertEquals(new BigDecimal(33.0), vencimentos.get(2).getVlrdesconto());
		assertEquals(new BigDecimal(333.0 + 18.0 - 33.0), vencimentos.get(2).getVlrliquido());
	}
	
	@Test
	public void calculoComValorTotalQuebrado() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalQuebrado, valorDescontoRedondo, valorAcrescimoRedondo);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 2; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(new BigDecimal(16.0), vencimentos.get(i).getVlracrescimo());
			assertEquals(new BigDecimal(33.0), vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0 + 16.0 - 33.0), vencimentos.get(i).getVlrliquido());
		}
		
		assertEquals(new BigDecimal(334.0), vencimentos.get(2).getVlrtotal());
		assertEquals(new BigDecimal(16.0), vencimentos.get(2).getVlracrescimo());
		assertEquals(new BigDecimal(33.0), vencimentos.get(2).getVlrdesconto());
		assertEquals(new BigDecimal(334.0 + 16.0 - 33.0), vencimentos.get(2).getVlrliquido());
	}
	
	@Test
	public void calculoQuebrado() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalQuebrado, valorDescontoQuebrado, valorAcrescimoQuebrado);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 2; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(new BigDecimal(16.0), vencimentos.get(i).getVlracrescimo());
			assertEquals(new BigDecimal(33.0), vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0 + 16.0 - 33.0), vencimentos.get(i).getVlrliquido());
		}
		
		assertEquals(new BigDecimal(334.0), vencimentos.get(2).getVlrtotal());
		assertEquals(new BigDecimal(18.0), vencimentos.get(2).getVlracrescimo());
		assertEquals(new BigDecimal(34.0), vencimentos.get(2).getVlrdesconto());
		assertEquals(new BigDecimal(334.0 + 18.0 - 34.0), vencimentos.get(2).getVlrliquido());
	}
	
	@Test
	public void calculoSemDescontosEAcrescimos() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalRedondo, BigDecimal.ZERO, BigDecimal.ZERO);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 3; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(BigDecimal.ZERO, vencimentos.get(i).getVlracrescimo());
			assertEquals(BigDecimal.ZERO, vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrliquido());
		}
	}
	
	@Test
	public void calculoComNulls() throws DesdobramentoException{
		List<OrRequisicaovencto> vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, valorTotalRedondo, null, null);
		
		assertEquals("quantidade de itens da lista não conferem", vencimentos.size(), 3);
		for(int i=0; i < 3; i++){
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrtotal());
			assertEquals(BigDecimal.ZERO, vencimentos.get(i).getVlracrescimo());
			assertEquals(BigDecimal.ZERO, vencimentos.get(i).getVlrdesconto());
			assertEquals(new BigDecimal(333.0), vencimentos.get(i).getVlrliquido());
		}
		
		vencimentos = 
				calculo.calculaVencimentos(parcelas, dataRequisicao, null, null, null);
		assertEquals(0, vencimentos.size());
	}
}
