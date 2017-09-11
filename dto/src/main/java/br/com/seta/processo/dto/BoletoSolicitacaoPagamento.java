package br.com.seta.processo.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.seta.processo.validacoes.Extensao;

public class BoletoSolicitacaoPagamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Documento boleto;
	
	public BoletoSolicitacaoPagamento(Documento documento){
		this.boleto = documento;
	}
	
	public Documento getBoleto(){
		return this.boleto;
	}
	
	@NotEmpty(message="O anexo é obrigatório")
	public byte[] getConteudo() {
		if(boleto == null)
			return null;
		
		return boleto.getConteudo();
	}	
	
	@Extensao(extensoes={"pdf", "jpeg", "jpeg", "gif", "png"}, 
			saoValidas=true, message="Apenas os seguintes formatos são aceitos: png, jpg/jpeg, gif e pdf")
	public String getTipo() {
		if(boleto == null)
			return null;
		
		return boleto.getTipo();
	}
}
