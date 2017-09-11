package br.com.seta.processo.pagecomponentes.secaoAbas;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxEventBehavior;

public class Aba implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private LinkAba link;
	private SecaoAba secao;
	
	public Aba(String idLink, String nomeAba, String idSecao, Component conteudo){
		this.link = new LinkAba(idLink, "#"+idSecao, nomeAba);
		this.secao = new SecaoAba(idSecao, conteudo);
	}
	
	public Aba(String idLink, String nomeAba, String idSecao, Component conteudo, boolean ativo){
		this.link = new LinkAba(idLink, "#"+idSecao, nomeAba, ativo);
		this.secao = new SecaoAba(idSecao, conteudo, ativo);
	}
	
	public Aba(String idLink, String nomeAba, String idSecao, Component conteudo, boolean ativo, AjaxEventBehavior event){
		this.link = new LinkAba(idLink, "#"+idSecao, nomeAba, ativo, event);
		this.secao = new SecaoAba(idSecao, conteudo, ativo);
	}
	
	public Aba(LinkAba link, SecaoAba secao){
		this.link = link;
		this.secao = secao;
	}

	public LinkAba getLink() {
		return link;
	}

	public SecaoAba getSecao() {
		return secao;
	}	

}
