package br.com.seta.processo.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * Entidade que mantém as configurações do Processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Entity(noClassnameStored=true)
public class ConfiguracaoSolicitacaoPagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private ObjectId id;
	
	/**
	 * Mantém o endereço de e-mail utilizado para notificar a área de Aprovação de Solicitação de Pagamento
	 */
	@NotEmpty(message="O e-mail é obrigatório")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message="E-mail em formato inválido")
	private String emailNotificacao = "";

	public String getEmailNotificacao() {
		return emailNotificacao;
	}

	public void setEmailNotificacao(String emailNotificacao) {
		this.emailNotificacao = emailNotificacao;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
}
