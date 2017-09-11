package br.com.seta.processo.grafico;


public class GraficoTanque extends Grafico{
	
	private String theme = "fint";
	private String caption = "";
	private String subcaption = "";
	private int lowerLimit = 0;
	private int upperLimit = 200;
	private String lowerLimitDisplay = "Vazio";
	private int upperLimitDisplay = 200;
	private String numberSuffix = "";
	private int showValue = 1;
	private String chartBottomMargin = "25";
	private String cylFillHoverColor = "#0099fd";
	private String cylFillHoverAlpha = "85";
	private int exportEnabled = 1;
	
	private long value = 0;

	public GraficoTanque(String idDiv, long value) {
		super(idDiv);
		this.value = value;
	}
	
	
	@Override
	public String gerarGrafico() {
		String json = " FusionCharts.ready(function () {"
				+ " var grafico" + getIdDiv() + " = new FusionCharts({"
				+ " type : 'cylinder',"
				+ " renderAt: '" + getIdDiv() + "',"
				+ " width : '" + getWidth() + "',"
				+ " height : '" + getHeight() + "',"
				+ " dataFormat: 'json',"
				+ " dataSource: {"
				+ " 	\"chart\" : { "
				+ " 		\"theme\": \"" + theme + "\","
				+ " 		\"caption\": \"" + caption + "\","
				+ " 		\"subcaption\": \"" + subcaption + "\","
				+ " 		\"lowerLimit\": \"" + lowerLimit + "\","
				+ " 		\"upperLimit\": \"" + upperLimit + "\","
				+ " 		\"lowerLimitDisplay\": \"" + lowerLimitDisplay + "\","
				+ " 		\"upperLimitDisplay\": \"" + upperLimitDisplay + "\","
				+ " 		\"numberSuffix\": \"" + numberSuffix + "\","
				+ " 		\"showValue\": \"" + showValue + "\","
				+ " 		\"chartBottomMargin\": \"" + chartBottomMargin + "\","
				+ " 		\"cylFillHoverColor\": \"" + cylFillHoverColor + "\","
				+ " 		\"cylFillHoverAlpha\": \"" + cylFillHoverAlpha + "\","
				+ " 		\"alignCaptionWithCanvas\": \"" + getAlignCaptionWithCanvas() + "\","
				+ " 		\"captionHorizontalPadding\": \"" + getCaptionHorizontalPadding() + "\","
				+ " 		\"captionOnTop\": \"" + getCaptionOnTop() + "\","
				+ " 		\"captionAlignment\": \"" + getCaptionAlignment() + "\","
				+ " 		\"exportEnabled\" : \"" + exportEnabled + "\""
				+ " },";
		
		json += montarAnotacoes();
		
		json += " 	\"value\": \"" + value + "\"";
		
		
		
		json += "      "
				+ "   }"
			    + " }).render();";
		
		
		json += " });";
		
		
		return json;
	}
	
	

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getSubcaption() {
		return subcaption;
	}

	public void setSubcaption(String subcaption) {
		this.subcaption = subcaption;
	}

	public int getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(int lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public int getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}

	public String getLowerLimitDisplay() {
		return lowerLimitDisplay;
	}

	public void setLowerLimitDisplay(String lowerLimitDisplay) {
		this.lowerLimitDisplay = lowerLimitDisplay;
	}

	public int getUpperLimitDisplay() {
		return upperLimitDisplay;
	}

	public void setUpperLimitDisplay(int upperLimitDisplay) {
		this.upperLimitDisplay = upperLimitDisplay;
	}

	public String getNumberSuffix() {
		return numberSuffix;
	}

	public void setNumberSuffix(String numberSuffix) {
		this.numberSuffix = numberSuffix;
	}

	public int getShowValue() {
		return showValue;
	}

	public void setShowValue(int showValue) {
		this.showValue = showValue;
	}

	public String getChartBottomMargin() {
		return chartBottomMargin;
	}

	public void setChartBottomMargin(String chartBottomMargin) {
		this.chartBottomMargin = chartBottomMargin;
	}

	public String getCylFillHoverColor() {
		return cylFillHoverColor;
	}

	public void setCylFillHoverColor(String cylFillHoverColor) {
		this.cylFillHoverColor = cylFillHoverColor;
	}

	public String getCylFillHoverAlpha() {
		return cylFillHoverAlpha;
	}

	public void setCylFillHoverAlpha(String cylFillHoverAlpha) {
		this.cylFillHoverAlpha = cylFillHoverAlpha;
	}

	public int getExportEnabled() {
		return exportEnabled;
	}

	public void setExportEnabled(int exportEnabled) {
		this.exportEnabled = exportEnabled;
	}

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}
	
}
