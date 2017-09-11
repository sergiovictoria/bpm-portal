package br.com.seta.processo.grafico;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class GraficoLinha extends Grafico{
	
	private String caption = "" ;
	private String subCaption = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private String numberPrefix = "";
	private int showBorder = 0;
	private int showShadow = 0;
	private String bgColor = "#ffffff";
	private String paletteColors = "#008ee4";
	private int showCanvasBorder = 0;
	private int showAxisLines = 0;
	private int showAlternateHGridColor = 0;
	private int divlineAlpha = 100;
	private int divlineThickness = 1;
	private int divLineIsDashed = 1;
	private int divLineDashLen = 1;
	private int divLineGapLen = 1;
	private int lineThickness = 3;
	private int flatScrollBars = 1;
	private int scrollheight = 10;
	private int numVisiblePlot = 12;
	private int showHoverEffect = 1;
	private int showValues = 1;
	private int exportEnabled = 1;
	
	public GraficoLinha(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue, String tituloX, String tituloY) {
		super(idDiv, datasource, indexLabel, indexValue);
		xAxisName = tituloX;
		yAxisName = tituloY;
	}
	
	public GraficoLinha(String idDiv, List<Object[]> datasource, int indexLabel, List<Integer> indexValues, String tituloX, String tituloY) {
		super(idDiv, datasource, indexLabel, indexValues);
		xAxisName = tituloX;
		yAxisName = tituloY;
	}

	@Override
	public String gerarGrafico() {
		String category = "";
		String data = "";
		
		for(Object[] row : getDatasource()){
			category += " { \"label\": \""+ row[getIndexLabel()] + "\" },";
			
			data += " 	  { ";
			
			if(getIndexValues() != null && getIndexValues().size() > 0){
				
				BigDecimal valor = new BigDecimal(0);
				
				for(Integer index : getIndexValues()){
					valor = valor.add(((BigDecimal)row[index]));
					
					count(row[index]);
				}
				
				data += "		\"value\": \"" + valor.longValue() + "\"";
				
			} else {
				data += "		\"value\": \"" + (row[getIndexValue()] instanceof BigDecimal ? ((BigDecimal)row[getIndexValue()]).longValue() : (BigInteger)row[getIndexValue()] ) + "\"";
				
				count(row[getIndexValue()]);
			}
			
		    data += adicionarDrillDown(row);
		    
			data += "  },";
		}
		
		String json = " FusionCharts.ready(function () {"
			    + " var grafico" + getIdDiv() + " = new FusionCharts({"
			    + "     type: 'scrollline2d',"
			    + "     renderAt: '" + getIdDiv() + "',"
			    + "     width: '" + getWidth() + "',"
			    + "     height: '" + getHeight() + "',"
			    + "     dataFormat: 'json',"
			    + "     dataSource: {"
				+ "			\"chart\" : {"
				+ "				\"caption\" : \"" + caption + "\","
				+ " 			\"subCaption\" : \"" + subCaption + "\","
				+ " 			\"xAxisName\" : \"" + xAxisName + "\","
				+ " 			\"yAxisName\" : \"" + yAxisName + "\","
				+ " 			\"numberPrefix\" : \"" + numberPrefix + "\","
				+ " 			\"showBorder\" : \"" + showBorder + "\","
				+ " 			\"showShadow\" : \"" + showShadow + "\","
				+ " 			\"bgColor\" : \"" + bgColor + "\","
				+ " 			\"paletteColors\" : \"" + paletteColors + "\","
				+ " 			\"showCanvasBorder\" : \"" + showCanvasBorder + "\","
				+ " 			\"showAxisLines\" : \"" + showAxisLines + "\","
				+ " 			\"showAlternateHGridColor\" : \"" + showAlternateHGridColor + "\","
				+ " 			\"divlineAlpha\" : \"" + divlineAlpha + "\","
				+ " 			\"divlineThickness\" : \"" + divlineThickness + "\","
				+ " 			\"divLineIsDashed\" : \"" + divLineIsDashed + "\","
				+ " 			\"divLineDashLen\" : \"" + divLineDashLen + "\","
				+ " 			\"divLineGapLen\" : \"" + divLineGapLen + "\","
				+ " 			\"lineThickness\" : \"" + lineThickness + "\","
				+ " 			\"flatScrollBars\" : \"" + flatScrollBars + "\","
				+ " 			\"scrollheight\" : \"" + scrollheight + "\","
				+ " 			\"numVisiblePlot\" : \"" + numVisiblePlot + "\","
				+ " 			\"showHoverEffect\" : \"" + showHoverEffect + "\","
				+ " 			\"showValues\" : \"" + showValues + "\","
				+ " 			\"exportEnabled\" : \"" + exportEnabled + "\""
				+ " },";
		
		json += montarAnotacoes();
		
		json += "         \"categories\": ["
			    + "             {"
			    + "                 \"category\": [";
			    
			json += category;    
			    
		    json += "                 ]"
			    + "             }"
			    + "         ],"
			    + "         \"dataset\": ["
			    + "             {"
			    + "                 \"data\": [";
			                        
		    json += data;    
			    
		    json += "                 ]"
			    + "             }"
			    + "         ]";
		    
		    
		    json += montarMedia();
		    
			json += "     }"
			    + " }).render();";
		
		    json += "});";
		
		return json;
	}

	public String getPaletteColors() {
		return paletteColors;
	}

	/**
	 * Ao deixar em branco o grafico gera com cores aleatorias. As cores são em Hexadeciaml usar #
	 * <br/>
	 * Usar ' , ' para adicionar mais de uma cor  
	 * @param paletteColors
	 */
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

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public int getShowShadow() {
		return showShadow;
	}

	public void setShowShadow(int showShadow) {
		this.showShadow = showShadow;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public int getShowCanvasBorder() {
		return showCanvasBorder;
	}

	public void setShowCanvasBorder(int showCanvasBorder) {
		this.showCanvasBorder = showCanvasBorder;
	}

	public int getShowAxisLines() {
		return showAxisLines;
	}

	public void setShowAxisLines(int showAxisLines) {
		this.showAxisLines = showAxisLines;
	}

	public int getShowAlternateHGridColor() {
		return showAlternateHGridColor;
	}

	public void setShowAlternateHGridColor(int showAlternateHGridColor) {
		this.showAlternateHGridColor = showAlternateHGridColor;
	}

	public int getDivlineAlpha() {
		return divlineAlpha;
	}

	public void setDivlineAlpha(int divlineAlpha) {
		this.divlineAlpha = divlineAlpha;
	}

	public int getDivlineThickness() {
		return divlineThickness;
	}

	public void setDivlineThickness(int divlineThickness) {
		this.divlineThickness = divlineThickness;
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

	public int getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
	}

	public int getFlatScrollBars() {
		return flatScrollBars;
	}

	public void setFlatScrollBars(int flatScrollBars) {
		this.flatScrollBars = flatScrollBars;
	}

	public int getScrollheight() {
		return scrollheight;
	}

	public void setScrollheight(int scrollheight) {
		this.scrollheight = scrollheight;
	}

	public int getNumVisiblePlot() {
		return numVisiblePlot;
	}

	public void setNumVisiblePlot(int numVisiblePlot) {
		this.numVisiblePlot = numVisiblePlot;
	}

	public int getShowHoverEffect() {
		return showHoverEffect;
	}

	public void setShowHoverEffect(int showHoverEffect) {
		this.showHoverEffect = showHoverEffect;
	}

	public int getShowValues() {
		return showValues;
	}

	public void setShowValues(int showValues) {
		this.showValues = showValues;
	}

	public int getExportEnabled() {
		return exportEnabled;
	}

	public void setExportEnabled(int exportEnabled) {
		this.exportEnabled = exportEnabled;
	}
	
	
	
}
