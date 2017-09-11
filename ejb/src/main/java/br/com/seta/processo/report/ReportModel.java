package br.com.seta.processo.report;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ReportModel {
	
	private Map<String, Object> model;
	
	public ReportModel (TypeReportEnum tipo) {
		if(tipo.equals(TypeReportEnum.XLS))
			model = new LinkedHashMap<String,Object>();
		else
			model = new HashMap<String, Object>();
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(HashMap<String, Object> model) {
		this.model = model;
	}

}
