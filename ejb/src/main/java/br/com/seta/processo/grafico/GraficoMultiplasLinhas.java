package br.com.seta.processo.grafico;

import java.util.ArrayList;
import java.util.List;

public class GraficoMultiplasLinhas extends Grafico{
	
	private String caption = "" ;
	private String subCaption = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private String numberPrefix = "";
	private int showBorder = 0;
	private int showShadow = 0;
	private String bgColor = "#ffffff";
	private String paletteColors = "";
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
	private int legendBgAlpha = 20;
	private String legendBorderColor = "#666666";
	private int legendBorderThickness = 1;
	private int legendBorderAlpha = 40;
	private int legendShadow = 1;
	
	private String[] seriesname;
	private Integer indexSeriesName = null;
	
	/**
	 * Construtora que recebe um datasource tratado sendo o label as linhas, seriesname as colunas e o value o index expecifico daquela coluna.
	 * @param idDiv
	 * @param datasource
	 * @param indexLabel
	 * @param indexValues
	 * @param nomeAgrupamentos
	 * @param tituloX
	 * @param tituloY
	 */
	public GraficoMultiplasLinhas(String idDiv, List<Object[]> datasource, int indexLabel, List<Integer> indexValues, String[] nomeAgrupamentos, String tituloX, String tituloY) {
		super(idDiv, datasource, indexLabel, indexValues);
		xAxisName = tituloX;
		yAxisName = tituloY;
		this.seriesname = nomeAgrupamentos;
	}
	
	/**
	 * Essa contrutora recebe como datasource um select pegando o value/label e seriesname em colunas e repetidos por seriesname.
	 * @param idDiv
	 * @param datasource
	 * @param indexLabel Nome dos ponto no eixo X
	 * @param indexValue Valores do eixo Y
	 * @param indexSeriesName objeto a ser apresentado, ficara na legenda tambem
	 * @param tituloX
	 * @param tituloY
	 */
	public GraficoMultiplasLinhas(String idDiv, List<Object[]> datasource, int indexLabel, int indexValue, int indexSeriesName, String tituloX, String tituloY) {
		super(idDiv, datasource, indexLabel, indexValue);
		xAxisName = tituloX;
		yAxisName = tituloY;
		this.indexSeriesName = indexSeriesName;
	}

	@Override
	public String gerarGrafico() {
		String category = "";
		String data = "";
		
		//USADO PARA A PRIMEIRA CONSTRUTORA
		if(getIndexValues() != null && seriesname != null && indexSeriesName == null) {
			for(Object[] row : getDatasource()){
				category += " { \"label\": \""+ row[getIndexLabel()] + "\" },";
			}
			
			for(int index = 0; index < getIndexValues().size(); index ++) {
				
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
		} else { //USASADO NA SEGUNDA CONTRUTORA
			
			List<Object> lstCategory = new ArrayList<Object>();
			List<Object> lstSeriesname = new ArrayList<Object>();
			
			//PRIMEIRO SEPARO AS CATEGORIAS(LABELS) DO GRAFICO. ELAS PODEM ESTAR REPETIDAS. 
			//POIS TAMBEM SERAM FILTRADAS RETIRANDO ASSIM AS REPETIDAS
			//FILTRO OS SERIESNAME DO DATASOURCE, LEMBRANDO QUE PODE ESTAR REPETIDO TAMBEM
			//A IDEIA AQUI É MANTER A ORDEM DAS INFORMAÇÕES DO DATASOURCE
			for(Object[] row : getDatasource()) {
				if(!lstCategory.contains(row[getIndexLabel()]))
					lstCategory.add(row[getIndexLabel()]);
				
				if(!lstSeriesname.contains(row[indexSeriesName]))
					lstSeriesname.add(row[indexSeriesName]);
			}
			
			//PREENCHO A CATEGORY PARA SER USADO NA MONTAGEM DO JSON ABAIXO
			for(Object obj : lstCategory)
				category += " { \"label\": \""+ obj.toString() + "\" },";
			
			
			//MONTA A STRING DATA QUE SERA JUNTA O SERIESNAME E OS VALUES PARA SER USADO NO JSON ABAIXO
			for(Object seriesname : lstSeriesname) {
				
				data += " 	  { "
						+ " \"seriesname\": \"" + seriesname.toString() + "\","
						+ " \"data\": [";
				
				for(Object[] row : getDatasource()){
					if(row[indexSeriesName].toString().equals(seriesname.toString())){
						data += "	{	\"value\": \"" + row[getIndexValue()] + "\" },";
						
						count(row[getIndexValue()]);
					}
				}
				
				data += " ]";
				data += "  },";
			}
			
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
				
				+ " 			\"legendBgAlpha\" : \"" + legendBgAlpha + "\","
				+ " 			\"legendBorderColor\" : \"" + legendBorderColor + "\","
				+ " 			\"legendBorderThickness\" : \"" + legendBorderThickness + "\","
				+ " 			\"legendBorderAlpha\" : \"" + legendBorderAlpha + "\","
				+ " 			\"legendShadow\" : \"" + legendShadow + "\","
				
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
			    + "         \"dataset\": [";
			                        
		    json += data;    
		    
			json += " 		]"
				+ "     }"
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

	public String[] getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String[] seriesname) {
		this.seriesname = seriesname;
	}
	
	
}
