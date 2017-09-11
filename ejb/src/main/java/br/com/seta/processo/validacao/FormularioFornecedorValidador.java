package br.com.seta.processo.validacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;

import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaFisicaCheck;
import br.com.seta.processo.validacoesCheck.FornecedorPessoaJuridicaCheck;

/**
 * @author Hromenique Cezniowscki Leite Batista
 */

@Stateless(name="FormularioFornecedorValidador")
@LocalBean
public class FormularioFornecedorValidador {	
	
	private Validator validator;
	
	@PostConstruct
	public void init(){
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}	
	
	public List<String> validate(FormularioFornecedor ff){
		List<String> mensagensErro = new ArrayList<String>();		
		
		if(isPessoaJuridica(ff)){
			mensagensErro = getListaMensagensErro(validator.validate(ff, FornecedorPessoaJuridicaCheck.class, Default.class));
		}else{
			mensagensErro = getListaMensagensErro(validator.validate(ff, FornecedorPessoaFisicaCheck.class, Default.class));
		}
		
		System.out.println("-------------------------ERROS DE VALIDAÇÃO----------------------------------------");
		System.out.println(mensagensErro);
		
		return mensagensErro;
	}
	
	private List<String> getListaMensagensErro(Set<ConstraintViolation<FormularioFornecedor>> set){
		List<String> mensagensErro = new ArrayList<String>();
		
		Iterator<ConstraintViolation<FormularioFornecedor>> iterator = set.iterator();
		while(iterator.hasNext()){
			String mensagem = iterator.next().getMessage();
			mensagensErro.add(mensagem);
		}
		
		return mensagensErro;
	}
	
	private boolean isPessoaFisica(FormularioFornecedor ff){
		if(ff.getCpfFisica() != null && !ff.getCpfFisica().isEmpty())
			return true;
		
		return false;
	}
	
	private boolean isPessoaJuridica(FormularioFornecedor ff){
		if(ff.getCpnjJuridico() != null && !ff.getCpnjJuridico().isEmpty())
			return true;
		
		return false;
	}
}
