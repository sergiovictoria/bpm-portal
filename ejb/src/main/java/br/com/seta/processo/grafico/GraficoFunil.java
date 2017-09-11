package br.com.seta.processo.grafico;

import java.util.List;

public class GraficoFunil extends Grafico {
	
	private String caption = ""; 
	private String subCaption = "";
	private String valueFontColor = "#000000";
	private int valueFontSize = 10;
	private String baseFont = "Helvetica Neue,Arial";
	private int captionFontSize= 14;
	private int subcaptionFontSize= 14;
	private int subcaptionFontBold= 0;
	private int showLabels= 1;
	private int showShadow= 0;
	private String divlineColor= "#999999";      
	private int divLineIsDashed= 1;
	private int divlineThickness= 1;
	private int divLineDashLen= 1;
	private int divLineGapLen= 1;
	private String canvasBgColor= "#ffffff";
	private int exportEnabled = 1;
	private String legendBgColor = "#CCCCCC";
	private int legendBgAlpha = 20;
	private String legendBorderColor = "#666666";
	private int legendBorderThickness = 1;
	private int legendBorderAlpha = 40;
	private int legendShadow = 1;
	private int showHoverEffect = 1;
	private int showLegend = 1;
	private int legendItemFontSize = 10;
	private String legendItemFontColor = "'#666666'";
	
	private Integer indexIdRegistro;
	private int linkTipo = 0;
	private String paletteColors = "";
	private boolean showDisplayValues = false;
	
	public GraficoFunil(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue) {
		super(idDiv, datasource, indexLabel, indexValue);
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
				+ " type: 'funnel',"
				+ " renderAt: '" + getIdDiv() + "',"
				+ " width: '" + getWidth() + "',"
				+ " height: '" + getHeight() + "',"
				+ " dataFormat: 'json',"
				+ " dataSource: {" 
				+ " 	\"chart\" : {"
				+ "			\"caption\" : \"" + caption + "\","
				+ " 		\"subCaption\" : \"" + subCaption + "\","
				+ "			\"paletteColors\" : \""+ paletteColors + "\","
				+ " 		\"valueFontColor\" : \"" + valueFontColor + "\","
				+ "			\"valueFontSize\" : \"" + valueFontSize + "\","
				+ " 		\"baseFont\" : \"" + baseFont + "\","
				+ " 		\"captionFontSize\" : \"" + captionFontSize + "\","
				+ " 		\"subcaptionFontSize\" : \"" + subcaptionFontSize + "\","
				+ " 		\"subcaptionFontBold\" : \"" + subcaptionFontBold + "\","
				+ " 		\"showLabels\" : \"" + showLabels + "\","
				+ " 		\"showShadow\" : \"" + showShadow + "\","
				+ " 		\"divlineColor\" : \"" + divlineColor + "\","
				+ " 		\"divLineIsDashed\" : \"" + divLineIsDashed + "\","
				+ " 		\"divlineThickness\" : \"" + divlineThickness + "\","
				+ " 		\"divLineDashLen\" : \"" + divLineDashLen + "\","
				+ " 		\"divLineGapLen\" : \"" + divLineGapLen + "\","
				+ " 		\"legendBgColor\" : \"" + legendBgColor + "\","
				+ " 		\"legendBgAlpha\" : \"" + legendBgAlpha + "\","
				+ " 		\"legendBorderColor\" : \"" + legendBorderColor + "\","
				+ " 		\"legendBorderThickness\" : \"" + legendBorderThickness + "\","
				+ " 		\"legendBorderAlpha\" : '" + legendBorderAlpha + "',"
				+ " 		\"legendShadow\" : '" + legendShadow + "',"
				+ " 		\"legendItemFontSize\" : '" + legendItemFontSize + "',"
				+ " 		\"legendItemFontColor\" : " + legendItemFontColor + ","
				+ "			\"showLegend\" : \"" + showLegend + "\","
				+ " 		\"canvasBgColor\" : \"" + canvasBgColor + "\""
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

	public int getShowLabels() {
		return showLabels;
	}

	public int getValueFontSize() {
		return valueFontSize;
	}

	public void setValueFontSize(int valueFontSize) {
		this.valueFontSize = valueFontSize;
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

	public String getLegendBgColor() {
		return legendBgColor;
	}

	public void setLegendBgColor(String legendBgColor) {
		this.legendBgColor = legendBgColor;
	}

	public int getLegendBgAlpha() {
		return legendBgAlpha;
	}

	public void setLegendBgAlpha(int legendBgAlpha) {
		this.legendBgAlpha = legendBgAlpha;
	}

	public String getLegendBorderColor() {
		return legendBorderColor;
	}

	public void setLegendBorderColor(String legendBorderColor) {
		this.legendBorderColor = legendBorderColor;
	}

	public int getLegendBorderThickness() {
		return legendBorderThickness;
	}

	public void setLegendBorderThickness(int legendBorderThickness) {
		this.legendBorderThickness = legendBorderThickness;
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

	public int getShowHoverEffect() {
		return showHoverEffect;
	}

	public void setShowHoverEffect(int showHoverEffect) {
		this.showHoverEffect = showHoverEffect;
	}

	public int getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(int showLegend) {
		this.showLegend = showLegend;
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
