package br.com.seta.processo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
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

import br.com.seta.processo.authorization.AcessoService;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;

@RunWith(Arquillian.class)
public class RetriveCompletedTaskListByAssignedDateTest {
	
	@Deployment
	public static Archive<?> createTestArchive(){
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ExecuteRestAPI.class)
				.addPackages(true, "br.com.seta.processo")
				.addPackages(true, "br.com.seta.soa")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(files);
	}
	
	private User user;
	
	@Inject
	private AcessoService acessoService;
	
	@Inject
	private ExecuteRestAPI executeRestAPI;
	
	@Before
	public void executeLogin(){
		user = acessoService.loginAs("sergio.victoria", "bpm");
	}
	
	@Test
	public void retriveCompletedTaskListByAssignedDateTest() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		System.out.println("********************** retriveCompletedTaskListByAssignedDate *********************************");
		List<HumanTask> completedHumanTasks = executeRestAPI.retriveCompletedTaskListByAssignedDate(user,0,300,"igual",new java.util.Date(),new java.util.Date());
		for (HumanTask humanTask : completedHumanTasks) {
               System.out.println(" humanTask ===>>> "+humanTask.toString() );			
		}
//		List<HumanTask> completedHumanTasks = executeRestAPI.retriveCompletedTaskList(user, 0, 10, "financeiras");
//		Iterator<HumanTask> iterator = completedHumanTasks.iterator();
//		System.out.println("Quantidade: " + completedHumanTasks.size());
//		while(iterator.hasNext()) {
//			System.out.println(iterator.next());
//		}
	}
}
