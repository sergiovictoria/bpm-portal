package br.com.seta.processo.grafico;

import java.util.List;

/**
 * 
 * Source: <a href="http://jsfiddle.net/fusioncharts/gAbDL/">Exemplo Gráfico </a>
 *
 * @author Augusto Souza
 */

public class GraficoBarraVerticalLinha extends Grafico{
	
	private int showValues = 0;
	private String caption = "";
	private String numberprefix = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private int showBorder = 0;
	private String paletteColors = "#0075c2,#1aaf5d,#f2c500";
	private String bgColor = "#ffffff";
	private String canvasBgColor = "#ffffff";
	private int captionFontSize = 14;
	private int subcaptionFontSize = 14;
	private int subcaptionFontBold = 0;
	private String divLineColor = "#999999";
	private int divLineIsDashed = 1;
	private int divLineDashLen = 1;
	private int divLineGapLen = 1;
	private String toolTipColor = "#ffffff";
	private int toolTipBorderThickness = 0;
	private String toolTipBgColor = "#ffffff";
	private int toolTipBgAlpha = 80;
	private int toolTipBorderRadius = 2;
	private int toolTipPadding = 5;
	private String legendBgColor = "#ffffff";
	private int legendBorderAlpha = 0;
	private int legendShadow = 0;
	private int legendItemFontSize = 10;
	private String legendItemFontColor = "#666666";
	private String[] seriesname;

	public GraficoBarraVerticalLinha(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue, String tituloX, String tituloY, String[] serie) {
		super(idDiv, datasource, indexLabel, indexValue);
		this.xAxisName = tituloX;
		this.yAxisName = tituloY;
		this.seriesname = serie; 
	}
	
	@Override
	public String gerarGrafico(){
		/*String data = "";
		String category = "";
		
		for(Object[] row : getDatasource()){
			category += " {"
					  + " \"label\": \""+ row[getIndexLabel()] 
					+ "\" },";
		}
		
		for(int index = 0; index < getIndexValue(); index ++) {
			
			data += " 	  { "
					+ " \"seriesname\": \"" + seriesname[index] + "\","
					+ " \"data\": [";
			
			for(Object[] row : getDatasource()){
				data += "	{	\"value\": \"" + row[index] + "\" },";
				
				count(row[index]);
			}
			
			data += " ]";
			data += "  },";
		}
		
		String json = " FusionCharts.ready(function () {"
			    + " var grafico" + getIdDiv() + " = new FusionCharts({"
			    + "     type: 'stackedColumn3DLine',"
			    + "     renderAt: 	'" + getIdDiv()  + "',"
			    + "     width:		'" + getWidth()  + "',"
			    + "     height:		'" + getHeight() + "',"
			    + "     dataFormat: 'json',"
			    + "     dataSource: {"
				+ "			\"chart\" : {"
			    + "				\"showvalues\" 				: \"" 	+ getShowValues() 		+ "\","	
				+ "				\"caption\" 				: \"" 	+ caption 				+ "\","
				+ "				\"numberprefix\"			: \"" 	+ numberprefix 			+ "\","
				+ " 			\"xAxisName\" 				: \"" 	+ xAxisName 			+ "\","
				+ " 			\"yAxisName\" 				: \"" 	+ yAxisName 			+ "\","
				+ " 			\"showBorder\" 				: \"" 	+ showBorder 			+ "\","
				+ " 			\"paletteColors\" 			: \"" 	+ paletteColors 		+ "\","
				+ " 			\"bgColor\" 				: \"" 	+ bgColor 				+ "\","
				+ "				\"canvasBgColor\"			: \"" 	+ canvasBgColor 		+ "\","
				+ "				\"captionFontSize\" 		: \""	+ captionFontSize 		+ "\","
				+ "				\"subcaptionFontSize\"		: \""	+ subcaptionFontSize 	+ "\","
				+ "				\"subcaptionFontBold\"		: \""  	+ subcaptionFontBold 	+ "\","
				+ "				\"divLineColor\" 			: \"" 	+ divLineColor		 	+ "\","
				+ "				\"divLineIsDashed\" 		: \"" 	+ divLineIsDashed	 	+ "\","
				+ "				\"divLineDashLen\" 			: \"" 	+ divLineDashLen	 	+ "\","
				+ "				\"divLineGapLen\" 			: \"" 	+ divLineGapLen 	 	+ "\","
				+ "				\"toolTipColor\" 			: \"" 	+ toolTipColor 		 	+ "\","
				+ "				\"toolTipBorderThickness\" 	: \"" 	+ toolTipBorderThickness+ "\","
				+ "				\"toolTipBgColor\" 			: \"" 	+ toolTipBgColor 		+ "\","
				+ "				\"toolTipBgAlpha\" 			: \"" 	+ toolTipBgAlpha 		+ "\","
				+ "				\"toolTipBorderRadius\"		: \""	+ toolTipBorderRadius	+ "\","		
				+ "				\"toolTipPadding\"			: \""	+ toolTipPadding		+ "\","					
				+ "				\"legendBgColor\" 			: \"" 	+ legendBgColor  		+ "\","
				+ "				\"legendBorderAlpha\" 		: \"" 	+ legendBorderAlpha 	+ "\","
				+ "				\"legendShadow\" 			: \"" 	+ legendShadow 			+ "\","
				+ "				\"legendItemFontSize\" 		: \"" 	+ legendItemFontSize  	+ "\","
				+ "				\"legendItemFontColor\" 	: \"" 	+ legendItemFontColor 	+ "\","
				+ " },";
		
		json += montarAnotacoes();
		
		json += "         \"categories\": ["
			    + "             {"
			    + "                 \"category\": [";
			    
		json += category;    
		    
	    json += "                   ]"
		    + "             }"
		    + "         ],"
		    + "         \"dataset\": [";
		                        
	    json += data;    
	    
		json += " 		]"
			+ "     }"
		    + " }).render();";
	
	    json += "});";
		
		return json;*/
		return "";
	}

	public int getShowValues() {
		return showValues;
	}

	public void setShowValues(int showValues) {
		this.showValues = showValues;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getNumberprefix() {
		return numberprefix;
	}

	public void setNumberprefix(String numberprefix) {
		this.numberprefix = numberprefix;
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

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	public void setPaletteColors(String paletteColors) {
		this.paletteColors = paletteColors;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public String getCanvasBgColor() {
		return canvasBgColor;
	}

	public void setCanvasBgColor(String canvasBgColor) {
		this.canvasBgColor = canvasBgColor;
	}

	public int getCaptionFontSize() {
		return captionFontSize;
	}

	public void setCaptionFontSize(int captionFontSize) {
		this.captionFontSize = captionFontSize;
	}

	public int getSubcaptionFontSize() {
		return subcaptionFontSize;
	}

	public void setSubcaptionFontSize(int subcaptionFontSize) {
		this.subcaptionFontSize = subcaptionFontSize;
	}

	public int getSubcaptionFontBold() {
		return subcaptionFontBold;
	}

	public void setSubcaptionFontBold(int subcaptionFontBold) {
		this.subcaptionFontBold = subcaptionFontBold;
	}

	public String getDivLineColor() {
		return divLineColor;
	}

	public void setDivLineColor(String divLineColor) {
		this.divLineColor = divLineColor;
	}

	public int getDivLineIsDashed() {
		return divLineIsDashed;
	}

	public void setDivLineIsDashed(int divLineIsDashed) {
		this.divLineIsDashed = divLineIsDashed;
	}

	public int getDivLineDashLen() {
		return divLineDashLen;
	}

	public void setDivLineDashLen(int divLineDashLen) {
		this.divLineDashLen = divLineDashLen;
	}

	public int getDivLineGapLen() {
		return divLineGapLen;
	}

	public void setDivLineGapLen(int divLineGapLen) {
		this.divLineGapLen = divLineGapLen;
	}

	public String getToolTipColor() {
		return toolTipColor;
	}

	public void setToolTipColor(String toolTipColor) {
		this.toolTipColor = toolTipColor;
	}

	public int getToolTipBorderThickness() {
		return toolTipBorderThickness;
	}

	public void setToolTipBorderThickness(int toolTipBorderThickness) {
		this.toolTipBorderThickness = toolTipBorderThickness;
	}

	public String getToolTipBgColor() {
		return toolTipBgColor;
	}

	public void setToolTipBgColor(String toolTipBgColor) {
		this.toolTipBgColor = toolTipBgColor;
	}

	public int getToolTipBgAlpha() {
		return toolTipBgAlpha;
	}

	public void setToolTipBgAlpha(int toolTipBgAlpha) {
		this.toolTipBgAlpha = toolTipBgAlpha;
	}

	public int getToolTipBorderRadius() {
		return toolTipBorderRadius;
	}

	public void setToolTipBorderRadius(int toolTipBorderRadius) {
		this.toolTipBorderRadius = toolTipBorderRadius;
	}

	public int getToolTipPadding() {
		return toolTipPadding;
	}

	public void setToolTipPadding(int toolTipPadding) {
		this.toolTipPadding = toolTipPadding;
	}

	public String getLegendBgColor() {
		return legendBgColor;
	}

	public void setLegendBgColor(String legendBgColor) {
		this.legendBgColor = legendBgColor;
	}

	public int getLegendBorderAlpha() {
		return legendBorderAlpha;
	}

	public void setLegendBorderAlpha(int legendBorderAlpha) {
		this.legendBorderAlpha = legendBorderAlpha;
	}

	public int getLegendShadow() {
		return legendShadow;
	}

	public void setLegendShadow(int legendShadow) {
		this.legendShadow = legendShadow;
	}

	public int getLegendItemFontSize() {
		return legendItemFontSize;
	}

	public void setLegendItemFontSize(int legendItemFontSize) {
		this.legendItemFontSize = legendItemFontSize;
	}

	public String getLegendItemFontColor() {
		return legendItemFontColor;
	}

	public void setLegendItemFontColor(String legendItemFontColor) {
		this.legendItemFontColor = legendItemFontColor;
	}

}
