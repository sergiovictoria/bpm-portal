package br.com.seta.processo.grafico;

import java.util.List;

public class GraficoBarraVertical extends Grafico {
	
	private String caption = ""; 
	private String subCaption = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private int slantLabels = 0;
	private String valueFontColor = "#000000";
	private String baseFont = "Helvetica Neue,Arial";
	private int captionFontSize= 14;
	private int subcaptionFontSize= 14;
	private int subcaptionFontBold= 0;
	private int placeValuesInside= 0;
	private int rotateValues= 1;
	private int rotateLabels = 1;
	private int showLabels= 1;
	private int showShadow= 0;
	private String divlineColor= "#999999";      
	private int divLineIsDashed= 1;
	private int divlineThickness= 1;
	private int divLineDashLen= 1;
	private int divLineGapLen= 1;
	private String canvasBgColor= "#ffffff";
	private int exportEnabled = 1;
	private Integer indexIdRegistro;
	private int linkTipo = 0;
	private String paletteColors = "";
	private boolean showDisplayValues = false;
	
	public GraficoBarraVertical(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue, String xTitulo, String yTitulo) {
		super(idDiv, datasource, indexLabel, indexValue);
		this.xAxisName = xTitulo;
		this.yAxisName = yTitulo;
	}

	@Override
	public String gerarGrafico() {
		String dados = "";
		
		for (Object[] row : getDatasource()) {
			dados += " {"
						+ "  \"label\": \"" + row[getIndexLabel()] + "\", "
						+ "  \"value\": \"" + row[getIndexValue()] + "\"";
			
			if(showDisplayValues)
				dados += "  ,\"displayvalue\": \""+ row[getIndexLabel()] + " - " + row[getIndexValue()] + "\"";
						
			dados += adicionarDrillDown(row);
					
			dados += "}, ";
			
			count(row[getIndexValue()]);
		}
		
		String json = "FusionCharts.ready(function () {"
				+ " var grafico" + getIdDiv() + " = new FusionCharts({"
				+ " type: 'column3d',"
				+ " renderAt: '" + getIdDiv() + "',"
				+ " width: '" + getWidth() + "',"
				+ " height: '" + getHeight() + "',"
				+ " dataFormat: 'json',"
				+ " dataSource: {" 
				+ " 	\"chart\" : {"
				+ "			\"caption\" : \"" + caption + "\","
				+ " 		\"subCaption\" : \"" + subCaption + "\","
				+ " 		\"xAxisName\" : \"" + xAxisName + "\","
				+ " 		\"yAxisName\" : \"" + yAxisName + "\","
				+ " 		\"slantLabels\" : \"" + slantLabels + "\","
				+ "			\"paletteColors\" : \""+ paletteColors + "\","
				+ " 		\"valueFontColor\" : \"" + valueFontColor + "\","
				+ " 		\"baseFont\" : \"" + baseFont + "\","
				+ " 		\"captionFontSize\" : \"" + captionFontSize + "\","
				+ " 		\"subcaptionFontSize\" : \"" + subcaptionFontSize + "\","
				+ " 		\"subcaptionFontBold\" : \"" + subcaptionFontBold + "\","
				+ " 		\"placeValuesInside\" : \"" + placeValuesInside + "\","
				+ " 		\"rotateValues\" : \"" + rotateValues + "\","
				+ " 		\"rotateLabels\" : \"" + rotateLabels + "\","
				+ " 		\"showLabels\" : \"" + showLabels + "\","
				+ " 		\"showShadow\" : \"" + showShadow + "\","
				+ " 		\"divlineColor\" : \"" + divlineColor + "\","
				+ " 		\"divLineIsDashed\" : \"" + divLineIsDashed + "\","
				+ " 		\"divlineThickness\" : \"" + divlineThickness + "\","
				+ " 		\"divLineDashLen\" : \"" + divLineDashLen + "\","
				+ " 		\"divLineGapLen\" : \"" + divLineGapLen + "\","
				+ " 		\"canvasBgColor\" : \"" + canvasBgColor + "\","
				+ " 		\"exportEnabled\" : \"" + exportEnabled + "\" "
				+ "		},";
		
		json += montarAnotacoes();
				
		json += "		\"data\": [";
		
		json += dados;
		
		json += "			]"
				+ "        }"
				+ "    }).render();";
		
		json += "});";
		
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

	public String getValueFontColor() {
		return valueFontColor;
	}

	public void setValueFontColor(String valueFontColor) {
		this.valueFontColor = valueFontColor;
	}

	public String getBaseFont() {
		return baseFont;
	}

	public void setBaseFont(String baseFont) {
		this.baseFont = baseFont;
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

	public int getPlaceValuesInside() {
		return placeValuesInside;
	}

	public void setPlaceValuesInside(int placeValuesInside) {
		this.placeValuesInside = placeValuesInside;
	}

	public int getRotateValues() {
		return rotateValues;
	}

	public void setRotateValues(int rotateValues) {
		this.rotateValues = rotateValues;
	}

	public int getRotateLabels() {
		return rotateLabels;
	}

	public void setRotateLabels(int rotateLabels) {
		this.rotateLabels = rotateLabels;
	}

	public int getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(int showLabels) {
		this.showLabels = showLabels;
	}

	public int getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(int showShadow) {
		this.showShadow = showShadow;
	}

	public String getDivlineColor() {
		return divlineColor;
	}

	public void setDivlineColor(String divlineColor) {
		this.divlineColor = divlineColor;
	}

	public int getDivLineIsDashed() {
		return divLineIsDashed;
	}

	public void setDivLineIsDashed(int divLineIsDashed) {
		this.divLineIsDashed = divLineIsDashed;
	}

	public int getDivlineThickness() {
		return divlineThickness;
	}

	public void setDivlineThickness(int divlineThickness) {
		this.divlineThickness = divlineThickness;
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

	public String getCanvasBgColor() {
		return canvasBgColor;
	}

	public void setCanvasBgColor(String canvasBgColor) {
		this.canvasBgColor = canvasBgColor;
	}

	public int getExportEnabled() {
		return exportEnabled;
	}

	public void setExportEnabled(int exportEnabled) {
		this.exportEnabled = exportEnabled;
	}

	public Integer getIndexIdRegistro() {
		return indexIdRegistro;
	}

	public void setIndexIdRegistro(Integer indexIdRegistro) {
		this.indexIdRegistro = indexIdRegistro;
	}

	public int getLinkTipo() {
		return linkTipo;
	}

	public void setLinkTipo(int linkTipo) {
		this.linkTipo = linkTipo;
	}

	/**
	 * Exibe o displayValue do grafico, combinando o label - value
	 * @return
	 */
	public boolean isShowDisplayValues() {
		return showDisplayValues;
	}

	public void setShowDisplayValues(boolean showDisplayValues) {
		this.showDisplayValues = showDisplayValues;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	public void setPaletteColors(String paletteColors) {
		this.paletteColors = paletteColors;
	}

	public int getSlantLabels() {
		return slantLabels;
	}

	public void setSlantLabels(int slantLabels) {
		this.slantLabels = slantLabels;
	}
	
}
