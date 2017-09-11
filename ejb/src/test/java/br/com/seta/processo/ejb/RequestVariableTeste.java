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
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.seta.processo.authorization.AcessoService;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.IntencaoCompra;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.qualifier.ConsincoDatabase;
import br.com.seta.processo.resource.Resource;
import br.com.seta.processo.service.ExecuteRestAPI;



@RunWith(Arquillian.class)
public class RequestVariableTeste {
	
		
	
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
	
	
	@Inject
	private AcessoService  acessoService;
	
	@Inject
	private ExecuteRestAPI executeRestApi;
	
	
	@Test
	public void retriveTasksTest() throws Exception {
		
		
		User user = acessoService.loginAs("sergio.victoria","bpm");
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuu    "+user.getIdUser());
		List<HumanTask> taskList = executeRestApi.retriveTaskList(user, "");
		
		
		HumanTask task = null;
		for (HumanTask dto : taskList) {
			System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  "+dto.getId());
			task = (HumanTask) dto;
		}
		
		
		
//		OrRequisicao orRequisicao = new OrRequisicao();
//		orRequisicao.setObservacao("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
//		executeRestApi.executeAssignUpdateVariableTask(user, task, orRequisicao, "requisicao");
		
		
//		
//		
		
		
//		
		IntencaoCompra intencaoCompra = (IntencaoCompra) executeRestApi.retriveVariableTask(user, task, IntencaoCompra.class, "intensaoCompra");
		System.out.println("orRequisicao     "+intencaoCompra.getAreaSolicitante());
		
		
	}
	
	

}
