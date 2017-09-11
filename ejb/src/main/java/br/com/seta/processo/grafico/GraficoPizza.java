package br.com.seta.processo.grafico;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class GraficoPizza extends Grafico {
	private String caption = "";
	private String subCaption = "";
	private String bgColor = "#ffffff";
	private int showBorder = 0;
	private int use3DLighting = 0;
	private int showShadow = 0;
	private int enableSmartLabels = 0;
	private int startingAngle = 0;
	private int showPercentValues = 1;
	private int showPercentInTooltip = 0;
	private int decimals = 1;
	private int captionFontSize = 14;
	private int subcaptionFontSize = 14;
	private int subcaptionFontBold = 0;
	private String toolTipColor = "#ffffff";
	private int toolTipBorderThickness = 0;
	private String toolTipBgColor = "#000000";
	private int toolTipBgAlpha = 80;
	private int toolTipBorderRadius = 2;
	private int toolTipPadding = 5;
	private int showHoverEffect = 1;
	private int showLegend = 1;
	private int legendItemFontSize = 10;
	private String legendItemFontColor = "'#666666'";
	private int showValues = 1;
	private int showLabels = 0;
	private String legendBgColor = "#CCCCCC";
	private int legendBgAlpha = 20;
	private String legendBorderColor = "#666666";
	private int legendBorderThickness = 1;
	private int legendBorderAlpha = 40;
	private int legendShadow = 1;
	private int exportEnabled = 1;
	private String paletteColors = "";
	
	private String[] nomeAgrupamentos;
	
	public GraficoPizza(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue){
		super(idDiv, datasource, indexLabel, indexValue);
	}
	
	/**
	 * Os labels são substituidos pelo nomeAgrupamento, e os valores são somados atravez do indexValues
	 * <br/>
	 * Os valores devem estar na mesma seguencia.
	 * <br/>
	 * <b>nomeAgrupamentos - 'Fruta'</b>
	 *  / <b>indexValues - 1 </b>
	 * <br/>
	 * <b>Portanto tanto o nomeAgrupamento e o indexValues devem possuir a mesma quantidade de index, e na ordem em que o index represente aquele nomeAgrupamento</b>
	 * @param idDiv
	 * @param datasource
	 * @param indexValues
	 * @param nomeAgrupamentos
	 */
	public GraficoPizza(String idDiv, List<Object[]> datasource, List<Integer> indexValues, String[] nomeAgrupamentos){
		super(idDiv, datasource, indexValues);
		this.nomeAgrupamentos = nomeAgrupamentos;
	}
	
	@Override
	public String gerarGrafico() {	
		String dados = "";
		
		if(nomeAgrupamentos != null && nomeAgrupamentos.length > 0){
			
			for(int index = 0; index < nomeAgrupamentos.length; index ++){
				dados += " {"
						+ "		\"label\": \"" + nomeAgrupamentos[index] + "\",";
				
				BigInteger totalValue = new BigInteger("0");
				
				for(Object[] row : getDatasource()) {
					
					if(row[index] instanceof BigInteger)
						totalValue = totalValue.add((BigInteger)row[index]);
					else
						totalValue = totalValue.add(((BigDecimal)row[index]).toBigInteger());
					
					count(row[index]);
				}
				
				dados += "		\"value\": \"" + totalValue + "\"";
				
				dados += " },";
				
				totalValue = new BigInteger("0");				
			}
			
		} else {
			for(Object[] row : getDatasource()) {
				
				dados += " {"
						+ "		\"label\": \"" + row[getIndexLabel()] + "\","
						+ "		\"value\": \"" + row[getIndexValue()] + "\""
						+ "},";
							
				count(row[getIndexValue()]);
			}
		}
		
		String json = " FusionCharts.ready(function () {"
				+ " var grafico" + getIdDiv() + " = new FusionCharts({"
				+ " type : 'pie3d',"
				+ " renderAt: '" + getIdDiv() + "',"
				+ " width : '" + getWidth() + "',"
				+ " height : '" + getHeight() + "',"
				+ " dataFormat: 'json',"
				+ " dataSource: {"
				+ " 	\"chart\" : { "
				+ " 		\"caption\" : \"" + caption + "\","
				+ " 		\"subCaption\" : \"" + subCaption + "\","
				+ " 		\"bgColor\" : \"" + bgColor + "\","
				+ " 		\"showBorder\" : \"" + showBorder + "\","
				+ " 		\"use3DLighting\" : \"" + use3DLighting + "\","
				+ " 		\"showShadow\" : \"" + showShadow + "\","
				+ " 		\"enableSmartLabels\" : \"" + enableSmartLabels + "\","
				+ " 		\"startingAngle\" : \"" + startingAngle + "\","
				+ " 		\"showPercentValues\" : \"" + showPercentValues + "\","
				+ " 		\"showPercentInTooltip\" : \"" + showPercentInTooltip + "\","
				+ " 		\"decimals\" : \"" + decimals + "\","
				+ " 		\"captionFontSize\" : \"" + captionFontSize + "\","
				+ " 		\"subcaptionFontSize\" : \"" + subcaptionFontSize + "\","
				+ " 		\"subcaptionFontBold\" : \"" + subcaptionFontBold + "\","
				+ " 		\"toolTipColor\" : \"" + toolTipColor + "\","
				+ " 		\"toolTipBorderThickness\" : \"" + toolTipBorderThickness + "\","
				+ " 		\"toolTipBgColor\" : \"" + toolTipBgColor + "\","
				+ " 		\"toolTipBgAlpha\" : \"" + toolTipBgAlpha + "\","
				+ " 		\"toolTipBorderRadius\" : \"" + toolTipBorderRadius + "\","
				+ " 		\"toolTipPadding\" : \"" + toolTipPadding + "\","
				+ " 		\"showHoverEffect\" : \"" + showHoverEffect + "\","
				+ " 		\"showLegend\" : \"" + showLegend + "\","
				+ " 		\"legendItemFontSize\" : '" + legendItemFontSize + "',"
				+ " 		\"legendItemFontColor\" : " + legendItemFontColor + ","
				+ " 		\"showValues\" : \"" + showValues + "\","
				+ " 		\"showLabels\" : \"" + showLabels + "\","
				+ " 		\"legendBgColor\" : \"" + legendBgColor + "\","
				+ " 		\"legendBgAlpha\" : \"" + legendBgAlpha + "\","
				+ " 		\"legendBorderColor\" : \"" + legendBorderColor + "\","
				+ " 		\"legendBorderThickness\" : \"" + legendBorderThickness + "\","
				+ " 		\"legendBorderAlpha\" : '" + legendBorderAlpha + "',"
				+ " 		\"legendShadow\" : '" + legendShadow + "',"
				+ " 		\"paletteColors\" : '" + paletteColors + "',"
				+ " 		\"alignCaptionWithCanvas\": \"" + getAlignCaptionWithCanvas() + "\","
				+ " 		\"captionHorizontalPadding\": \"" + getCaptionHorizontalPadding() + "\","
				+ " 		\"captionOnTop\": \"" + getCaptionOnTop() + "\","
				+ " 		\"captionAlignment\": \"" + getCaptionAlignment() + "\","
				+ " 		\"exportEnabled\" : \"" + exportEnabled + "\""
				+ " },";
		
		json += montarAnotacoes();
		
		json += " 	\"data\": [";
		
		json += dados;
		
		json += "      ]"
				+ "   }"
			    + " }).render();";
		
		
		json += " });";
		
		
		return json;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public int getUse3DLighting() {
		return use3DLighting;
	}

	public void setUse3DLighting(int use3dLighting) {
		use3DLighting = use3dLighting;
	}

	public int getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(int showShadow) {
		this.showShadow = showShadow;
	}

	public int getEnableSmartLabels() {
		return enableSmartLabels;
	}

	public void setEnableSmartLabels(int enableSmartLabels) {
		this.enableSmartLabels = enableSmartLabels;
	}

	public int getStartingAngle() {
		return startingAngle;
	}

	public void setStartingAngle(int startingAngle) {
		this.startingAngle = startingAngle;
	}

	public int getShowPercentValues() {
		return showPercentValues;
	}

	public void setShowPercentValues(int showPercentValues) {
		this.showPercentValues = showPercentValues;
	}

	public int getShowPercentInTooltip() {
		return showPercentInTooltip;
	}

	public void setShowPercentInTooltip(int showPercentInTooltip) {
		this.showPercentInTooltip = showPercentInTooltip;
	}

	public int getDecimals() {
		return decimals;
	}

	public void setDecimals(int decimals) {
		this.decimals = decimals;
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

	public int getShowValues() {
		return showValues;
	}

	public void setShowValues(int showValues) {
		this.showValues = showValues;
	}

	public int getShowLabels() {
		return showLabels;
	}

	public void setShowLabels(int showLabels) {
		this.showLabels = showLabels;
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

	public int getExportEnabled() {
		return exportEnabled;
	}

	public void setExportEnabled(int exportEnabled) {
		this.exportEnabled = exportEnabled;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	public void setPaletteColors(String paletteColors) {
		this.paletteColors = paletteColors;
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
}
