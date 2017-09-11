package br.com.seta.processo.model;

import java.io.Serializable;

import org.apache.wicket.markup.html.WebPage;

/**
 * Representa um item da seção Serviços do Portal do BPM Seta
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class RotuloEWebPage implements Serializable{		
	private static final long serialVersionUID = 1L;
	
	private Class<? extends WebPage> webPage;
	private String rotulo;
	
	public RotuloEWebPage(){
		
	}
	
	public RotuloEWebPage(String rotulo, Class<? extends WebPage> webPage){
		this.rotulo = rotulo;
		this.webPage = webPage;			
	}

	public Class<? extends WebPage> getWebPage() {
		return webPage;
	}

	public void setWebPage(Class<? extends WebPage> webPage) {
		this.webPage = webPage;
	}

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}		
}
