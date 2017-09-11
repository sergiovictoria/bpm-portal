package br.com.seta.processo.grafico;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * 
 * @author Joao C. Fernandes
 *
 */
public class GraficoBarraVerticalAgrupado extends Grafico {
	
	private String caption = "";
	private String subCaption = "";
	private String xAxisName = "";
	private String yAxisName = "";
	private String numberPrefix = "";
	private String paletteColors = "";
	private String bgColor = "#ffffff";
	private int showBorder = 0;
	private int showCanvasBorder = 0;
	private int usePlotGradientColor = 0;
	private int plotBorderAlpha = 10;
	private int showHoverEffect = 1;
	private String valueFontColor = "#000";
	private int rotateValues = 1;
	private int placeValuesInside = 0;
	private String divlineColor = "#999999";
	private int divLineIsDashed = 1;
	private int divLineDashLen = 1;
	private int divLineGapLen = 1;
	private String canvasBgColor = "#ffffff";
	private int captionFontSize = 14;
	private int subcaptionFontSize = 14;
	private int subcaptionFontBold = 0;
	private String legendBgColor = "#CCCCCC";
	private int legendBgAlpha = 20;
	private String legendBorderColor = "#666666";
	private int legendBorderThickness = 1;
	private int legendBorderAlpha = 40;
	private int legendShadow = 1;
	private int exportEnabled = 1;
	private int showLegend = 1;
	
	private String[] seriesname;
	private Integer indexGroup;
	
	
	public GraficoBarraVerticalAgrupado(String idDiv, List<Object[]> datasource, int indexLabel, List<Integer> indexValues, String[] nomeAgrupamentos,  String xTitulo, String yTitulo) {
		super(idDiv, datasource, indexLabel, indexValues);
		this.xAxisName = xTitulo;
		this.yAxisName = yTitulo;
		this.seriesname = nomeAgrupamentos;
	}
	
	/**
	* NO DATA SOURCE DEVE POSSUIR A CALUNA QUE SERA AGRUPADA</BR>
	*  EXEMPLO</BR>
	*  INDEX 0 | INDEX 1 | INDEX 2</BR>
	* -------- + ------- + -------</BR>
	*  GRUPO A | DEPT. 1 | 20</BR>
	*  GRUPO A | DEPT. 2 | 10</BR>
	*  GRUPO A | DEPT. 3 | 2</BR>
	*  GRUPO B | DEPT. 1 | 4</BR>
	*  GRUPO B | DEPT. 3 | 23</BR>
	* </BR>
	* AQUI NO CASO A IDEIA É AGRUPAR PELO INDEX 0, PORTANTO FICARA DOIS GRUPOS (GRUPO A E GRUPO B) POSSUINDO AS BARRAS NO GRAFICO MONTADAS PELO INDEX 1</BR>
	* <B>GRUPO A</B> (DEPT. 1, DEPT. 2, DEPT. 3)</BR>
	* <B>GRUPO B</B> (DEPT. 1, DEPT. 3)
	*/	
	public GraficoBarraVerticalAgrupado(String idDiv, List<Object[]> datasource, Integer indexGroup, int indexLabel, int indexValue, String xTitulo, String yTitulo) {
		super(idDiv, datasource, indexLabel, indexValue);
		this.xAxisName = xTitulo;
		this.yAxisName = yTitulo;
		this.indexGroup = indexGroup;
	}

	@Override
	public String gerarGrafico() {
		String data = "";
		String category = "";
		
		for(Object[] row : getDatasource()){
			category += " { \"label\": \""+ row[getIndexLabel()] + "\" },";
		}
		
		if(getIndexValues() != null && getIndexValues().size() > 0) {
			for(int index = 0; index < getIndexValues().size(); index ++) {
				
				data += " 	  { "
						+ " \"seriesname\": \"" + seriesname[index] + "\","
						+ " \"data\": [";
				
				for(Object[] row : getDatasource()){
					data += "	{	\"value\": \"" + row[getIndexValues().get(index)] + "\" },";
					
					count(row[index]);
				}
				
				data += " ]";
				data += "  },";
			}
		} else if(indexGroup != null) {
			//NO DATA SOURCE DEVE POSSUIR A CALUNA QUE SERA AGRUPADA
			// EXEMPLO
			// INDEX 0 | INDEX 1 | INDEX 2
			//-------- + ------- + -------
			// GRUPO A | DEPT. 1 | 20
			// GRUPO A | DEPT. 2 | 10
			// GRUPO A | DEPT. 3 | 2
			// GRUPO B | DEPT. 1 | 4
			// GRUPO B | DEPT. 3 | 23
			//
			//AQUI NO CASO A IDEIA É AGRUPAR PELO INDEX 0, PORTANTO O HASHMAP FICARIA COM 2 LINHAS CONTENDO O (GRUPO A) E O (GRUPO B) COMO KEYS E O OS VALUE SERÃO A LIST<OBJECT[]> 
			
			setShowLegend(0);
			
			LinkedHashMap<String, List<Object[]>> datasourceAgrupado = new LinkedHashMap<String, List<Object[]>>();
			
			for(Object[] group : getDatasource()){
				
				if(!datasourceAgrupado.containsKey(group[indexGroup])){
					
					List<Object[]> values = new ArrayList<Object[]>();
					
					for(Object[] row : getDatasource()){
						if(row[indexGroup].equals(group[indexGroup]))
							values.add(row);
					}
					
					datasourceAgrupado.put(group[indexGroup].toString(), values);
				}
					
			}
			
			//CARREGA O NOME DOS GRUPOS E
			//PEGA A LISTA Q POSSUI MAIS REGISTROS E GUARDA UMA LINHA DA MESMA
			category = "";
			int size = 0;
			Object[] linhaBranco = null;
			for(Entry<String, List<Object[]>> group : datasourceAgrupado.entrySet()) {
				category += " { \"label\": \""+ group.getKey() + "\" },";
				
				if(group.getValue().size() > size){
					size = group.getValue().size();
				}
			}
			
			linhaBranco = new Object[size];
			
			//PREENCHE AS LISTAS COM MENOS REGISTROS COM A LINHA GUARDADA
			for(Entry<String, List<Object[]>> group : datasourceAgrupado.entrySet()) {
				if(group.getValue().size() < size) {
					int qtd = size - group.getValue().size();
					for(int index = 0; index < qtd; index ++)
						group.getValue().add(linhaBranco);
				}
			}
			
			//MONTA O TRECHO DO DATA
			for(int index = 0; index < size; index ++){
				
				data += " 	  { "
						+ " \"data\": [";
				
				//VARRE PRIMEIRO OS GRUPOS
				for(Entry<String, List<Object[]>> group : datasourceAgrupado.entrySet()) {
					
					//VERIFICA SE A LINHA NÃO É A EM BRANCO, SE FOR SETA O VALUE E O DISPLAY EM BRANCO, POIS TODOS OS (DATA) DEVEM POSSUI A MESMA QUANTIDADE DE COLUNAS
					if(group.getValue().get(index) != null && group.getValue().get(index)[0] != null){
						data += "	{"
								+ "		\"value\": \"" + group.getValue().get(index)[getIndexValue()] + "\",  "
								+ "		\"displayValue\": \"" + group.getValue().get(index)[getIndexLabel()] + " - "+ group.getValue().get(index)[getIndexValue()] + "\"";
						
						data += adicionarDrillDown(group.getValue().get(index));
						
						data += " },";
						
						count(group.getValue().get(index)[getIndexValue()]);
					} else {
						//O LINK NÃO É OBRIGATORIO, PORTANTO NÃO É NECESSARIO ADICIONAR AQUI 
						data += "	{	"
								+ "		\"value\": \"\","
								+ "		\"displayValue\": \"\""
								+ " },";
					}
					
				
				}
				
				data += " ]";
				data += "  },";
				
			}
			
		}
		
		String json = "FusionCharts.ready(function () {"
				+ " var grafico" + getIdDiv() + " = new FusionCharts({"
				+ " type: 'mscolumn3d',"
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
				+ " 		\"valueFontColor\" : \"" + valueFontColor + "\","
				+ " 		\"numberPrefix\" : \"" + numberPrefix + "\","
				+ " 		\"captionFontSize\" : \"" + captionFontSize + "\","
				+ " 		\"subcaptionFontSize\" : \"" + subcaptionFontSize + "\","
				+ " 		\"subcaptionFontBold\" : \"" + subcaptionFontBold + "\","
				+ " 		\"placeValuesInside\" : \"" + placeValuesInside + "\","
				+ " 		\"rotateValues\" : \"" + rotateValues + "\","
				+ " 		\"paletteColors\" : \"" + paletteColors + "\","
				+ " 		\"bgColor\" : \"" + bgColor + "\","
				+ " 		\"showBorder\" : \"" + showBorder + "\","
				+ " 		\"divlineColor\" : \"" + divlineColor + "\","
				+ " 		\"divLineIsDashed\" : \"" + divLineIsDashed + "\","
				+ " 		\"showCanvasBorder\" : \"" + showCanvasBorder + "\","
				+ " 		\"divLineDashLen\" : \"" + divLineDashLen + "\","
				+ " 		\"divLineGapLen\" : \"" + divLineGapLen + "\","
				+ " 		\"canvasBgColor\" : \"" + canvasBgColor + "\","
				+ " 		\"legendBgColor\" : \"" + legendBgColor + "\","
				+ " 		\"legendBgAlpha\" : \"" + legendBgAlpha + "\","
				+ " 		\"legendBorderColor\" : \"" + legendBorderColor + "\","
				+ " 		\"legendBorderThickness\" : \"" + legendBorderThickness + "\","
				+ " 		\"legendBorderAlpha\" : \"" + legendBorderAlpha + "\","
				+ " 		\"legendShadow\" : \"" + legendShadow + "\","
				+ " 		\"usePlotGradientColor\" : \"" + usePlotGradientColor + "\","
				+ " 		\"plotBorderAlpha\" : \"" + plotBorderAlpha + "\","
				+ " 		\"showHoverEffect\" : \"" + showHoverEffect + "\","
				+ "			\"showLegend\" : \"" + showLegend + "\","
				+ " 		\"exportEnabled\" : \"" + exportEnabled + "\" "
				+ "		},";
		
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

	public String[] getSeriesname() {
		return seriesname;
	}

	public void setSeriesname(String[] seriesname) {
		this.seriesname = seriesname;
	}

	public String getNumberPrefix() {
		return numberPrefix;
	}

	public void setNumberPrefix(String numberPrefix) {
		this.numberPrefix = numberPrefix;
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

	public int getShowBorder() {
		return showBorder;
	}

	public void setShowBorder(int showBorder) {
		this.showBorder = showBorder;
	}

	public int getShowCanvasBorder() {
		return showCanvasBorder;
	}

	public void setShowCanvasBorder(int showCanvasBorder) {
		this.showCanvasBorder = showCanvasBorder;
	}

	public int getUsePlotGradientColor() {
		return usePlotGradientColor;
	}

	public void setUsePlotGradientColor(int usePlotGradientColor) {
		this.usePlotGradientColor = usePlotGradientColor;
	}

	public int getPlotBorderAlpha() {
		return plotBorderAlpha;
	}

	public void setPlotBorderAlpha(int plotBorderAlpha) {
		this.plotBorderAlpha = plotBorderAlpha;
	}

	public int getShowHoverEffect() {
		return showHoverEffect;
	}

	public void setShowHoverEffect(int showHoverEffect) {
		this.showHoverEffect = showHoverEffect;
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

	public int getShowLegend() {
		return showLegend;
	}

	public void setShowLegend(int showLegend) {
		this.showLegend = showLegend;
	}
	
	
}
