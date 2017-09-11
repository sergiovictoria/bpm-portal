package br.com.seta.processo.bean;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import br.com.seta.processo.exceptions.ValidacaoBeanException;

/**
 * Classe abstrata base para os serviços que deseja validar um bean
 * 
 * @author Hromenique Cezniowscki Leite Batista *

 */

@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)

public class AbstractValidateBeanService {
	
	private Validator validator;

	public AbstractValidateBeanService() {
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
}
