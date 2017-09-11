package br.com.seta.processo.grafico;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

/**
 * Classe abstrada com funções e atributos basicos para os graficos
 * @author João Cesar Fernandes
 * @version 0.0.6
 */
public abstract class Grafico {
	
	private String width = "100%";
	private String height = "350";
	private String idDiv = "";
	
	private List<Object[]> datasource;
	private int indexLabel;
	private List<Integer> indexLabels;
	private int indexValue;
	private List<Integer> indexValues;
	
	private boolean exibirMedia = false;
	private HashMap<String, Integer> linhaMedia;
	private HashMap<String, List<Integer>> linhaMediaVariasColunas;
	
	private boolean exibirTotalizador = false;
	private String textoTotalizador = "";
	private int posicao = 3;
	private int totalizadorWidth = 200;
	private int totalizadorHeight = 20;
	private int totalizadorFontSize = 12;
	
	private boolean drillDown = false;
	private int linkTipo = 0;
	private String urlDetino;
	private HashMap<String, Integer> urlParametro;
	
	private BigInteger totalInteger = new BigInteger("0");
	private BigDecimal totalDecimal = new BigDecimal(0);
	
	private int alignCaptionWithCanvas = 1;
	private int captionHorizontalPadding = 2;
	private int captionOnTop = 1;
    private String captionAlignment = CAPTION_ALIGNMENT_CENTER;
	
	private boolean exibirDetalhe = false;
	private int posicaoDetalhe = POSICAO_INFERIOR_DIREITO;
	private String link = "";
	
    public Grafico(String idDiv){
    	super();
		this.idDiv = idDiv;
    }
	
	public Grafico(String idDiv, List<Object[]> datasource,  int indexLabel, int indexValue) {
		super();
		this.idDiv = idDiv;
		this.datasource = datasource;
		this.indexLabel = indexLabel;
		this.indexValue = indexValue;
	}
	
	public Grafico(String idDiv, List<Object[]> datasource, int indexLabel,  List<Integer> indexValues) {
		super();
		this.idDiv = idDiv;
		this.datasource = datasource;
		this.indexLabel = indexLabel;
		this.indexValues = indexValues;
	}
	
	public Grafico(String idDiv, List<Object[]> datasource,  List<Integer> indexLabels, int indexValue) {
		super();
		this.idDiv = idDiv;
		this.datasource = datasource;
		this.indexLabels = indexLabels;
		this.indexValue = indexValue;
	}
	
	public Grafico(String idDiv, List<Object[]> datasource,  List<Integer> indexLabels, List<Integer> indexValues) {
		super();
		this.idDiv = idDiv;
		this.datasource = datasource;
		this.indexLabels = indexLabels;
		this.indexValues = indexValues;
	}

	public Grafico(String idDiv, List<Object[]> datasource,  List<Integer> indexValues) {
		super();
		this.idDiv = idDiv;
		this.datasource = datasource;
		this.indexValues = indexValues;
	}
		
	
	/**
	 * Cria uma tarja azul sobre o grafico mostrando o total de registros
	 * @param total
	 * @return json
	 */
	public void exibirTotalizador(boolean exibirTotalizador, String textoTotalizador, int posicao){
		this.exibirTotalizador = exibirTotalizador;
		this.textoTotalizador = textoTotalizador;
		this.posicao = posicao;
	}
	
	/**
	 * Cria uma tarja azul sobre o grafico mostrando o total de registros
	 * @param total
	 * @return json
	 */
	public void exibirTotalizador(boolean exibirTotalizador, String textoTotalizador, int posicao, int totalizadorWidth, int totalizadorHeight){
		this.exibirTotalizador = exibirTotalizador;
		this.textoTotalizador = textoTotalizador;
		this.posicao = posicao;
		this.totalizadorWidth = totalizadorWidth;
		this.totalizadorHeight = totalizadorHeight;
	}
	
	protected String montarTotalizador(BigInteger total) {
		String json = "";
		if(exibirTotalizador) {
			json = " 		\"annotations\": {"
					+ "                 \"origw\": \"450\","
				    + "                 \"origh\": \"300\","
				    + "                 \"autoscale\": \"1\", "
				    + "                 \"showBelow\": \"0\","
				    + "                 \"groups\": ["
				    + "                     {"
				    + "                         \"id\": \"arcs\","
				    + "                         \"items\": [  "                          
				    + "                             {"
				    + "                                 \"id\": \"store-cs-bg\","                                
				    + "                                 \"type\": \"rectangle\",";
			
			switch (posicao) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartEndX - " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartStartY + " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartStartX + " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartStartY + " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartEndX - " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartEndY - " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartStartX + " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartEndY - " + totalizadorHeight + "\",";
					break;
			}
			
			json +=	"                                 \"fillcolor\": \"#0075c2\""
				    + "                             },"
				    + "                             {"
				    + "                                 \"id\": \"state-cs-text\","
				    + "                                 \"type\": \"Text\","
				    + "                                 \"color\": \"#ffffff\","
				    + "                                 \"label\": \"" + textoTotalizador + " " + total + "\","
				    + "                                 \"fontSize\": \"" + totalizadorFontSize + "em\","
				    + "                                 \"align\": \"left\",";
			
			switch (posicao) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (totalizadorWidth - 5) + "\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (totalizadorWidth - 5) + "\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
			}
			
			json += "                             }"
				    + "                         ]"
				    + "                     } "                                                          
				    + "                 ]"
				    + "             },";
		}
		return json;
	}
	
	//TODO montarTotalizador()
	protected String montarTotalizador() {
		String json = "";
		if(exibirTotalizador) {
			
			if(totalDecimal.longValue() > 0) {
				totalInteger = totalInteger.add(totalDecimal.toBigInteger());
			}
			
			json = "	{"
				    + "		\"id\": \"store-cs-bg\","                                
				    + "		\"type\": \"rectangle\",";
			
			switch (posicao) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartEndX - " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartStartY + " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartStartX + " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartStartY + " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartEndX - " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartEndY - " + totalizadorHeight + "\",";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartStartX + " + totalizadorWidth + "\","
						    + " \"toy\": \"$chartEndY - " + totalizadorHeight + "\",";
					break;
			}
			
			json +=	"                                 \"fillcolor\": \"#0075c2\""
				    + "                             },"
				    + "                             {"
				    + "                                 \"id\": \"state-cs-text\","
				    + "                                 \"type\": \"Text\","
				    + "                                 \"color\": \"#ffffff\","
				    + "                                 \"label\": \"" + textoTotalizador + " " + totalInteger + "\","
				    + "                                 \"fontSize\": \"" + totalizadorFontSize + "\","
				    + "                                 \"align\": \"left\",";
			
			switch (posicao) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (totalizadorWidth - 5) + "\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (totalizadorWidth - 5) + "\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
			}
			
			json += " },";
		}
		return json;
	}
	
	protected void count(Object column) {
		if(isExibirTotalizador()) {
			if (column instanceof BigInteger)
				totalInteger =  totalInteger.add((BigInteger)column);
			else 
				totalDecimal = totalDecimal.add((BigDecimal)column);
		}
	}
	
	
	
	/**
	 * Cria uma linha sobre o grafico que exibe a media de um determinado index do datasource
	 * <br/>
	 * <b>linhaMedia</b> - HaspMap<String, Integer> que define a linha
	 * <ul>
	 * <li>String - Definir a cor da linha em Hexadecimal e com #</li>
	 * <li>Integer - Coluna do data source que sera feita a media</li>
	 * </ul>
	 * @param linhaMedia
	 * @return
	 */
	public void exibirLinhaMedia(HashMap<String, Integer> linhaMedia){
		this.linhaMedia = linhaMedia;
	}
	
	/**
	 * Cria uma linha sobre o grafico que exibe a media da soma de varios index do datasource
	 * <br/>
	 * <b>linhaMedia</b> - HaspMap<String, List<Integer>> que define a linha
	 * <ul>
	 * <li>String - Definir a cor da linha em Hexadecimal e com #</li>
	 * <li>List<Integer> - Colunas do data source que serão somados e feita a media</li>
	 * </ul>
	 * @param linhaMedia
	 * @return
	 */
	public void exibirLinhaMediaSoma(HashMap<String, List<Integer>> linhaMediaVariasColunas){
		this.linhaMediaVariasColunas = linhaMediaVariasColunas;
	}
	
	//TODO montarMedia()
	protected String montarMedia(){
		
		if((linhaMedia!= null && linhaMedia.size() > 0) 
				|| (linhaMediaVariasColunas!= null && linhaMediaVariasColunas.size() > 0)){
			
			String json = ","
					+ "			\"trendlines\": ["
				    + "				{"
				    + "					\"line\": [";
			
			BigDecimal totalMedia = new BigDecimal(0);
			
			if(linhaMedia!= null && linhaMedia.size() > 0) {
				for(Entry<String, Integer> linha: linhaMedia.entrySet()){
					for(Object[] row : datasource) {
						BigDecimal temp = new BigDecimal(0);
						try { temp = (BigDecimal)row[linha.getValue()]; } catch(Exception e) { }
						
						totalMedia = totalMedia.add(temp);
					}
					
					if(totalMedia.intValue() > 0)
						totalMedia = totalMedia.divide(new BigDecimal(datasource.size()), 2, RoundingMode.UP);
					
					json += "	{"
						    + "		\"startvalue\": \"" + totalMedia + "\","
						    + "		\"color\": \"" + linha.getKey() + "\","
						    + "		\"valueOnRight\": \"1\","
						    + "		\"displayvalue\": \"Média - " + totalMedia + "\","
						    + "	},";
				}
			}
			
			if(linhaMediaVariasColunas!= null && linhaMediaVariasColunas.size() > 0) {
				for(Entry<String, List<Integer>> linha: linhaMediaVariasColunas.entrySet()){
					for(Object[] row : datasource) {
						BigDecimal temp = new BigDecimal(0);
						
						for(Integer index : linha.getValue()) {
							temp = new BigDecimal(0);
							
							try { temp = (BigDecimal)row[index]; } catch(Exception e) { }
							
							totalMedia = totalMedia.add(temp);
						}
					}
					
					if(totalMedia.intValue() > 0)
						totalMedia = totalMedia.divide(new BigDecimal(datasource.size()), 2, RoundingMode.UP);
					
					json += "	{"
						    + "		\"startvalue\": \"" + totalMedia + "\","
						    + "		\"color\": \"" + linha.getKey() + "\","
						    + "		\"valueOnRight\": \"1\","
						    + "		\"displayvalue\": \"Média - " + totalMedia + "\","
						    + "	},";
				}
			}
			
		    json += "					]"
				    + "				}"
				    + "			]";
			
			return json;
		}
		
		return "";
	}
	
	
	
	
	
	
	/**
	 * Preenche os atributos que serão usados para criar o link para o drill-down.
	 * <br/>
	 * <b>urlDestino</b> - url da tela destino ou nome da função javascript <b>SEM</b> ()
	 * <ul>
	 * <li>Quando a url possuir parametros estaticos deve-se passar já com a urlDestino, usar a urlParametro para valores que serão buscados no datasource</li>
	 * </ul>
	 * <br/>
	 * <b>urlParametro</b> - adiciona na urlDestino um parametro e o valor respectivo do datasource.
	 * <ul>
	 * <li> Sendo String = parametro, Integer = index da coluna que devera ser passado. </li>
	 * <li> Sendo que para funções javascript os parametros devem ser adicionados na ordem que estão na função.</li>
	 * </ul>
	 * @param urlDetino
	 * @param linkTipo
	 * @param urlParametro
	 */
	public void configurarDrillDown(boolean drillDown, String urlDetino, int linkTipo, HashMap<String, Integer> urlParametro){
		this.drillDown = drillDown;
		this.urlDetino = urlDetino;
		this.linkTipo = linkTipo;
		this.urlParametro = urlParametro;
	}
	//TODO adicionarDrillDown()
	protected String adicionarDrillDown(Object[] row){
		if(drillDown){
			String link = "";
			
			switch (linkTipo) {
				case LINK_TIPO_JAVASCRIPT:
					if(urlParametro != null && urlParametro.size() > 0){
						link += "  ,\"link\": \"JavaScript:" + urlDetino + "(";
						
						for(Entry<String, Integer> valor : urlParametro.entrySet()){
							if(row[valor.getValue()] instanceof String)
								link += "'" + row[valor.getValue()] + "',";
							else
								link += row[valor.getValue()] + ",";
						}
						
						if(link.length() > 0 && link.endsWith(","))
							link = link.substring(0, link.length() - 1);
						
						link += ")";
					}
					else
						link += "  ,\"link\": \"JavaScript:" + urlDetino + "()";
					
					link += "\"";
					
					break;
				
				case LINK_TIPO_URL:
					link += "	,\"link\": \"" + urlDetino;
					
					for(Entry<String, Integer> valor : urlParametro.entrySet()){
						link += "&" + valor.getKey() + "=" + row[valor.getValue()];
					}
					
					link += "\"";
					
					break;
				case LINK_TIPO_NENHUM:
				default:
					break;
			}
			
			return link;
		}
		return "";
	}
	
	
	
	
	
	/**
	 * A posição DEFAULT é a POSICAO_INFERIOR_DIREITO, NÃO utilizar o default do static
	 * @param exibirDetalhe
	 * @param posicao
	 * @param url Url da tela que vai ser dado o detalhe, os parametros devem vir juntos com a url
	 */
	public void exibirBotaoDetalhe(boolean exibirDetalhe, int posicao, String url){
		this.exibirDetalhe = exibirDetalhe;
		this.posicaoDetalhe = posicao;
		this.link = url;
	}
	
	//TODO montarBotaoDetalhe()
	protected String montarBotaoDetalhe() {
		String json = "";
		if(exibirDetalhe) {
			int width = 52;
			int height = 20;
			this.link = "www.terra.com.br";
			
			json = "	{"
				    + "		\"id\": \"store-cs-bg\","                                
				    + "		\"type\": \"rectangle\",";
			
			switch (posicaoDetalhe) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartEndX - " + width + "\","
						    + " \"toy\": \"$chartStartY + " + height + "\",";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartStartY\","
						    + " \"tox\": \"$chartStartX + " + width + "\","
						    + " \"toy\": \"$chartStartY + " + height + "\",";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\" : \"$chartEndX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartEndX - " + width + "\","
						    + " \"toy\": \"$chartEndY - " + height + "\",";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\" : \"$chartStartX\","
						    + " \"y\": \"$chartEndY\","
						    + " \"tox\": \"$chartStartX + " + width + "\","
						    + " \"toy\": \"$chartEndY - " + height + "\",";
					break;
			}
			
			json +=	"                                 \"fillcolor\": \"#dc9843\""
				    + "                             },"
				    + "                             {"
				    + "                                 \"id\": \"detalheLabel\","
				    + "                                 \"type\": \"Text\","
				    + "                                 \"color\": \"#ffffff\","
				    + "                                 \"label\": \"Detalhe\","
				    + "									\"link\" : \"" + link + "\","
				    + "                                 \"fontSize\": \"" + totalizadorFontSize + "\","
				    + "                                 \"align\": \"left\",";
			
			switch (posicaoDetalhe) {
				case POSICAO_SUPERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (width - 5) + "\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_SUPERIOR_ESQUERDO:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartStartY + 10\"";
					break;
					
				case POSICAO_INFERIOR_DIREITO:
					json += " \"x\": \"$chartEndX - " + (width - 5) + "\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
					
				case POSICAO_INFERIOR_ESQUERDO:
				default:
					json += " \"x\": \"$chartStartX + 5\","
						    + " \"y\": \"$chartEndY - 10\"";
					break;
			}
			
			json += " },";
		}
		return json;
		
	}
	
	/**
	 * Usar esse metodo para montar as anotações
	 * pois ele monta corretamente o totalizador e o botão detalhe.
	 * <br/>
	 * Todos os outros labels devem ser montados aqui tambem
	 * @return json
	 */
	//TODO montarAnotacoes()
	protected String  montarAnotacoes(){
		String json = "";
		
		if(exibirDetalhe || exibirTotalizador) {
			json = " 		\"annotations\": {"
					+ "                 \"origw\": \"450\","
				    + "                 \"origh\": \"300\","
				    + "                 \"autoscale\": \"1\", "
				    + "                 \"showBelow\": \"0\","
				    + "                 \"groups\": ["
				    + "                     {"
				    + "                         \"id\": \"arcs\","
				    + "                         \"items\": [";
		}
		
		json += montarTotalizador();
		
		json += montarBotaoDetalhe();
		
		if(exibirDetalhe || exibirTotalizador)
			json += "                         ]"
				    + "                     } "                                                          
				    + "                 ]"
				    + "             },";
		
		return json;
	}
	
	
	
	public abstract String gerarGrafico();
	
	
	
	public static final int POSICAO_SUPERIOR_DIREITO = 0;
	public static final int POSICAO_SUPERIOR_ESQUERDO = 1;
	public static final int POSICAO_INFERIOR_DIREITO = 2;
	public static final int POSICAO_INFERIOR_ESQUERDO = 3;
	public static final int POSICAO_DEFAULT = 3;
	
	public static final int LINK_TIPO_NENHUM = 0;
	public static final int LINK_TIPO_JAVASCRIPT = 1;
	public static final int LINK_TIPO_URL = 2;
	
	public static final String CAPTION_ALIGNMENT_CENTER = "center";
	public static final String CAPTION_ALIGNMENT_RIGHT = "right";
	public static final String CAPTION_ALIGNMENT_LEFT = "left";

	public boolean isExibirTotalizador() {
		return exibirTotalizador;
	}

	public void setExibirTotalizador(boolean exibirTotalizador) {
		this.exibirTotalizador = exibirTotalizador;
	}

	public String getTextoTotalizador() {
		return textoTotalizador;
	}

	public void setTextoTotalizador(String textoTotalizador) {
		this.textoTotalizador = textoTotalizador;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public int getTotalizadorWidth() {
		return totalizadorWidth;
	}

	public void setTotalizadorWidth(int totalizadorWidth) {
		this.totalizadorWidth = totalizadorWidth;
	}

	public int getTotalizadorHeight() {
		return totalizadorHeight;
	}

	public void setTotalizadorHeight(int totalizadorHeight) {
		this.totalizadorHeight = totalizadorHeight;
	}

	public int getTotalizadorFontSize() {
		return totalizadorFontSize;
	}

	public void setTotalizadorFontSize(int totalizadorFontSize) {
		this.totalizadorFontSize = totalizadorFontSize;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	public String getIdDiv() {
		return idDiv;
	}

	public void setIdDiv(String idDiv) {
		this.idDiv = idDiv;
	}

	public int getIndexLabel() {
		return indexLabel;
	}

	public void setIndexLabel(int indexLabel) {
		this.indexLabel = indexLabel;
	}

	public int getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(int indexValue) {
		this.indexValue = indexValue;
	}

	public List<Object[]> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<Object[]> datasource) {
		this.datasource = datasource;
	}

	public boolean isExibirMedia() {
		return exibirMedia;
	}

	public void setExibirMedia(boolean exibirMedia) {
		this.exibirMedia = exibirMedia;
	}

	public List<Integer> getIndexLabels() {
		return indexLabels;
	}

	public void setIndexLabels(List<Integer> indexLabels) {
		this.indexLabels = indexLabels;
	}

	public List<Integer> getIndexValues() {
		return indexValues;
	}

	public void setIndexValues(List<Integer> indexValues) {
		this.indexValues = indexValues;
	}

	public int getAlignCaptionWithCanvas() {
		return alignCaptionWithCanvas;
	}

	public void setAlignCaptionWithCanvas(int alignCaptionWithCanvas) {
		this.alignCaptionWithCanvas = alignCaptionWithCanvas;
	}

	public int getCaptionHorizontalPadding() {
		return captionHorizontalPadding;
	}

	public void setCaptionHorizontalPadding(int captionHorizontalPadding) {
		this.captionHorizontalPadding = captionHorizontalPadding;
	}

	public int getCaptionOnTop() {
		return captionOnTop;
	}

	public void setCaptionOnTop(int captionOnTop) {
		this.captionOnTop = captionOnTop;
	}

	public String getCaptionAlignment() {
		return captionAlignment;
	}

	public void setCaptionAlignment(String captionAlignment) {
		this.captionAlignment = captionAlignment;
	}
	
}
