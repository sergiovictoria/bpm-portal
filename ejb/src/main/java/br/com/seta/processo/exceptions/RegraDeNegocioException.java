package br.com.seta.processo.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Exceção que representa que algum erro envolvendo uma ou mais regras de negócio
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class RegraDeNegocioException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	private List<String> mensagens = new ArrayList<String>();
	
	public RegraDeNegocioException(){		
	}
	
	public RegraDeNegocioException(String mensagemDeErro){
		super(mensagemDeErro);
	}
	
	public RegraDeNegocioException(String mensagemDeErro, List<String> mensagens) {
		super(mensagemDeErro);
		this.mensagens = mensagens;
	}
	
	public RegraDeNegocioException(List<String> mensagens){
		if(mensagens != null){
			this.mensagens = mensagens;
		}else{
			this.mensagens = new ArrayList<String>();
		}		
	}
	
	public void adicionaMensagem(String mensagem){
		this.mensagens.add(mensagem);
	}
	
	public List<String> getMensagens(){
		return this.mensagens;
	}

}
