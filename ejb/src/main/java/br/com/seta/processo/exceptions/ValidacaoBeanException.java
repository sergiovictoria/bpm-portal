package br.com.seta.processo.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

/**
 * Exceção de validação de um bean (Bean Validation)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ValidacaoBeanException extends Exception {
	private static final long serialVersionUID = 1L;

	private Set<ConstraintViolation<Object>> constraintViolations;
	private List<String> messages;
	
	public ValidacaoBeanException(){
		
	}
	
	public ValidacaoBeanException(List<String> messages){
		this.messages = messages;
	}
	
	public ValidacaoBeanException(String message, Set<ConstraintViolation<Object>> constraintViolations) {
		super(message);
		this.constraintViolations = constraintViolations;
		this.messages = extractConstraintMessages(constraintViolations);
	}

	public ValidacaoBeanException(Set<ConstraintViolation<Object>> constraintViolations) {
		this.constraintViolations = constraintViolations;
		this.messages = extractConstraintMessages(constraintViolations);
	}
	
	public Set<ConstraintViolation<Object>> getConstraintViolations() {
		return constraintViolations;
	}

	public void setConstraintViolations(
			Set<ConstraintViolation<Object>> constraintViolations) {
		this.constraintViolations = constraintViolations;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	private List<String> extractConstraintMessages(Set<ConstraintViolation<Object>> constraintViolations){
		List<String> messages = new ArrayList<String>();
		
		for(ConstraintViolation<Object> constraint : constraintViolations){
			messages.add(constraint.getMessage());
		}
		
		return messages;
	}
}
