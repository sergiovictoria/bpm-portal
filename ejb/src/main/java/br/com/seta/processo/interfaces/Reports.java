package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.Report;
import br.com.seta.processo.type.TypeDoc;
import br.com.seta.processo.type.TypePage;



public interface Reports {
	public abstract Report generateReport(List<?> cols, Class<?> myClass, TypeDoc typeDoc, String fileName, String title, String subTitle, TypePage typePage);
	public abstract Report generateReport(Object object, Class<?> myClass, TypeDoc typeDoc, String fileName, String title, String subTitle, TypePage typePage);
	public abstract Report generateIndicator(List<?> cols, Class<?> myClass, TypeDoc typeDoc, String fileName, String title, String subTitle, TypePage typePage);
	public abstract Report generateIndicator(Object object, Class<?> myClass, TypeDoc typeDoc, String fileName, String title, String subTitle, TypePage typePage);
}	

