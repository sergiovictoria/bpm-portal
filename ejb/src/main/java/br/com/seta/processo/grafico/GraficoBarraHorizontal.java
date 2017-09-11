package br.com.seta.processo.grafico;

import java.util.List;

public class GraficoBarraHorizontal extends Grafico {

	private String caption = "";
	private String subCaption = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private String numberPrefix = ""; 
	private int alignCaptionWithCanvas = 0;
	private int canvasBgAlpha = 0;
	private int exportEnabled = 1;
	
	
	public GraficoBarraHorizontal(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue, String xTitulo, String yTitulo) {
		super(idDiv, datasource, indexLabel, indexValue);
		this.xAxisName = xTitulo;
		this.yAxisName = yTitulo;
	}

	@Override
	public String gerarGrafico() {
		String dados = "";
		
		for(Object[] row : getDatasource()) {
			dados += " {"
					+ "		\"label\": \"" + row[getIndexLabel()] + "\","
					+ "		\"value\": \"" + row[getIndexValue()] + "\"";
					
			dados += adicionarDrillDown(row);
					
			dados += "},";
			
			count(row[getIndexValue()]);
		}
		
		String json = " FusionCharts.ready(function () {"
				    + " var grafico" + getIdDiv() + " = new FusionCharts({"
				    + "     type: 'bar3d',"
				    + "     renderAt: '" + getIdDiv() + "',"
				    + "     width: '" + getWidth() + "',"
				    + "     height: '" + getHeight() + "',"
				    + "     dataFormat: 'json',"
				    + "     dataSource: {"
					+ " \"chart\" : {"
					+ "		 \"caption\" : \"" + caption + "\","
					+ " 	\"subCaption\" : \"" + subCaption + "\","
					+ " 	\"xAxisName\" : \"" + xAxisName + "\","
					+ " 	\"yAxisName\" : \"" + yAxisName + "\","
					+ " 	\"numberPrefix\" : \"" + numberPrefix + "\","
					+ " 	\"alignCaptionWithCanvas\" : \"" + alignCaptionWithCanvas + "\","
					+ " 	\"canvasBgAlpha\" : \"" + canvasBgAlpha + "\","
					+ " 	\"alignCaptionWithCanvas\": \"" + getAlignCaptionWithCanvas() + "\","
					+ " 	\"captionHorizontalPadding\": \"" + getCaptionHorizontalPadding() + "\","
					+ " 	\"captionOnTop\": \"" + getCaptionOnTop() + "\","
					+ " 	\"captionAlignment\": \"" + getCaptionAlignment() + "\","
					+ " 	\"exportEnabled\" : \"" + exportEnabled + "\""
					+ " },";
		
		json += montarAnotacoes();
		
		json += " \"data\": [";
		
		json += dados;
		
		json += "      ]}"
			    + " }).render();";
		
		json += " });";
		
		return json;
	}
	

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubCaption() {
		return subCaption;
	}

	public void setSubCaption(String subCaption) {
		this.subCaption = subCaption;
	}

	public String getxAxisName() {
		return xAxisName;
	}

	public void setxAxisName(String xAxisName) {
		this.xAxisName = xAxisName;
	}

	public String getyAxisName() {
		return yAxisName;
	}

	public void setyAxisName(String yAxisName) {
		this.yAxisName = yAxisName;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
	}

	public int getAlignCaptionWithCanvas() {
		return alignCaptionWithCanvas;
	}

	public void setAlignCaptionWithCanvas(int alignCaptionWithCanvas) {
		this.alignCaptionWithCanvas = alignCaptionWithCanvas;
	}

	public int getCanvasBgAlpha() {
		return canvasBgAlpha;
	}

	public void setCanvasBgAlpha(int canvasBgAlpha) {
		this.canvasBgAlpha = canvasBgAlpha;
	}

	public int getExportEnabled() {
		return exportEnabled;
	}

	public void setExportEnabled(int exportEnabled) {
		this.exportEnabled = exportEnabled;
	}
	
}
