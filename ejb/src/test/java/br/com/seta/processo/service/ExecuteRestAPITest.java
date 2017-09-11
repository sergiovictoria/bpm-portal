package br.com.seta.processo.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.ParseException;
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
import br.com.seta.processo.dto.Case;
import br.com.seta.processo.dto.Form;
import br.com.seta.processo.dto.HumanTask;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.TaskTimeline;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;

@RunWith(Arquillian.class)
public class ExecuteRestAPITest {
	
	@Deployment
	public static Archive<?> createTestArchive(){
		File[] files = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve().withTransitivity().asFile();
		
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addClasses(ExecuteRestAPI.class)
				.addPackages(true, "br.com.seta.processo")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				.addAsLibraries(files);
	}
	
	private User user;
	
	@Inject
	private AcessoService acessoService = new AcessoService();
	
	@Inject
	private ExecuteRestAPI executeRestAPI;
	
	@Before
	public void executeLogin(){
		user = acessoService.loginAs("hromenique.batista", "bpm");
	}
	
	@Test
	public void retriveAllProcessTest() throws Exception{
		System.out.println("**********************retriveAllProcessTest*********************************");
		List<TaskProcess> processList = executeRestAPI.retriveProcessList(user);
		for (TaskProcess taskProcess : processList) {
			System.out.println("****************************************************");
			System.out.println(taskProcess);
		}
	}
	
	@Test
	public void retriveProcessListTest() throws Exception {
		System.out.println("**********************retriveProcessListTest*********************************");
		List<TaskProcess> processList = executeRestAPI.retriveProcessList(user, 0, 10);
		for (TaskProcess taskProcess : processList) {
			System.out.println("****************************************************");
			System.out.println(taskProcess);
		}
	}
	
	@Test
	public void retriveProcessListSizeTest() throws Exception{
		System.out.println("**********************retriveProcessListSizeTest*********************************");
		int size = executeRestAPI.retriveProcessListSize(user);
		System.out.println("********************************************************");
		System.out.println("Process List Size equals " + size);
	}
	
	@Test
	public void retriveTasksListTest() throws HttpStatus401Exception, ParseException, IOException, HttpStatusException{
		List<HumanTask> taskList = executeRestAPI.retriveTaskList(user,0, 10, "");
		System.out.println(taskList);
		System.out.println("********************************************************");

		taskList = executeRestAPI.retriveTaskList(user , 11, 10, "");		
		System.out.println(taskList);
		System.out.println("********************************************************");

		taskList = executeRestAPI.retriveTaskList(user , 21, 10, "");		
		System.out.println(taskList);		
		System.out.println("********************************************************");

		taskList = executeRestAPI.retriveTaskList(user , 32, 10, "");		
		System.out.println(taskList);
		System.out.println("********************************************************");

	}
	
	@Test
	public void retriveTaskListSize() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		int size = executeRestAPI.retriveTaskListSize(user, "");
		System.out.println("********************************************************");
		System.out.println("Task List Size equals " + size);
	}
	
	@Test
	public void retriveHumanTasksTimelineTest() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		TaskTimeline HumanTasksTimeline = executeRestAPI.retriveHumanTasksTimeline(user, 6L, HumanTask.Comparators.REACHED_STATE_DATE_DESC);
		System.out.println(HumanTasksTimeline);
	}
	
	@Test
	public void retriveCaseTest() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		Case cs = executeRestAPI.retriveCase(6L, user);
		System.out.println("*************************Retrive Case Test***********************************");
		System.out.println("Case: " + cs);
	}
	
	@Test
	public void retriveTaskFormTest() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		Form form = executeRestAPI.retriveTaskForm(user, "8036173194842478729", "preencher_formulario");
		System.out.println("***************************** Retrive Task Form ***************");
		System.out.println(form);
	}
	
	public void retriveInstantiationForm() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException{
		Form form = executeRestAPI.retriveInstantiationForm(user, "8036173194842478729");
		System.out.println("***************** Retrive Instantiation Form **********************");
		System.out.println(form);
	}
	
}
