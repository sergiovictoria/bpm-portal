package br.com.seta.processo.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import br.com.seta.processo.dto.HumanTask;

@RunWith(Parameterized.class)
public class FiltroDataHumanTaskTest {
	
	private static HumanTask ht;
	private int tipoFiltro;
	private static Date dataIgual;
	private static Date dataMaior;
	private static Date dataMenor;
	
	public FiltroDataHumanTaskTest(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
	@Parameters
	public static Collection<Object[]> getParametros() {
		Object[][] params = new Object[][] {
				{ new Integer(FiltroDataHumanTask.POR_DATA_ATRIBUICAO) },
				{ new Integer(FiltroDataHumanTask.POR_DATA_CONCLUSAO) } };

		return Arrays.asList(params);
	}
	
	@Before
	public void iniciaHumanTask(){
		ht = new HumanTask();
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 2, 15, 10, 15, 10);
		ht.setAssigned_date(calendar.getTime());
		ht.setArchivedDate(calendar.getTime());
		
		dataIgual = calendar.getTime();
		
		calendar.set(Calendar.MONTH, 3);
		dataMaior = calendar.getTime();
		
		calendar.set(Calendar.MONTH, 1);
		dataMenor = calendar.getTime();
	}
	
	@Test
	public void igualTest(){
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.IGUAL, dataIgual ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.IGUAL, dataMaior ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.IGUAL, dataMenor ,true).apply(ht));
	}
	
	@Test
	public void diferenteTest(){
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.DIFERENTE, dataIgual ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.DIFERENTE, dataMaior ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.DIFERENTE, dataMenor ,true).apply(ht));
	}
	
	@Test
	public void maiorTest(){
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR, dataIgual ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR, dataMaior ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR, dataMenor ,true).apply(ht));
	}
	
	@Test
	public void maiorOuIgualATest(){
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR_IGUAL, dataIgual ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR_IGUAL, dataMaior ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MAIOR_IGUAL, dataMenor ,true).apply(ht));
	}
	
	@Test
	public void menorTest(){		
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR, dataIgual ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR, dataMaior ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR, dataMenor ,true).apply(ht));
	}
	
	@Test
	public void menorOuIgualATest(){
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR_IGUAL, dataIgual ,true).apply(ht));
		assertTrue(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR_IGUAL, dataMaior ,true).apply(ht));
		assertFalse(new FiltroDataHumanTask(tipoFiltro, OperadorFiltro.MENOR_IGUAL, dataMenor ,true).apply(ht));
	}
	
}
