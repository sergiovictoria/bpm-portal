package br.com.seta.processo.validacao;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import br.com.seta.processo.exceptions.ValidacaoBeanException;

/**
 * Valida um bean (classe java) baseando-se nas anotações Bean Validation
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="ValidadorBean")
@LocalBean
public class ValidadorBean {
	
	private Validator validator;

	@PostConstruct
	public void init(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}	
	
	/**
	 * Valida um objeto baseando-se na especificação Bean Validation
	 * 
	 * @param obj Objeto a ser validado
	 * @param groups 
	 * @throws ValidacaoBeanException Exceção lançada caso o objeto não respeite alguma validação
	 */
	public void valida(Object obj, Class<?> groups) throws ValidacaoBeanException{		
		Set<ConstraintViolation<Object>> constraintViolations;
		if(groups == null){
			constraintViolations = validator.validate(obj, Default.class);
		}else{
			constraintViolations = validator.validate(obj, groups);
		}		 
		
		if(!constraintViolations.isEmpty()){
			throw new ValidacaoBeanException(constraintViolations);
		}
	}
	
	public void validaComGroups(Object obj, Class<?>... groups) throws ValidacaoBeanException{
		Set<ConstraintViolation<Object>> constraintViolations;
		if(groups == null){
			constraintViolations = validator.validate(obj, Default.class);
		}else{
			constraintViolations = validator.validate(obj, groups);
		}		 
		
		if(!constraintViolations.isEmpty()){
			throw new ValidacaoBeanException(constraintViolations);
		}
	}
	
	/**
	 * Valida um objeto baseando-se na especificação Bean Validation
	 * 
	 * @param objects
	 * @throws ValidacaoBeanException
	 */
	public void valida(Object... objects) throws ValidacaoBeanException{
		Set<ConstraintViolation<Object>> constraintViolations = new HashSet<ConstraintViolation<Object>>();
		
		for(Object obj : objects){
			Set<ConstraintViolation<Object>> violations = validator.validate(obj, Default.class);
			if(!violations.isEmpty()){
				constraintViolations.addAll(violations);
			}
		}
		
		if(!constraintViolations.isEmpty()){
			throw new ValidacaoBeanException(constraintViolations);
		}
	}
	
	/**
	 * Valida um objeto baseando-se na especificação Bean Validation
	 * 
	 * @param obj
	 * @throws ValidacaoBeanException
	 */
	public void valida(Object obj) throws ValidacaoBeanException{
		valida(obj, null);
	}

}
