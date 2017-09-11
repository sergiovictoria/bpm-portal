package br.com.seta.processo.validacoes;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExtensaoValidator implements ConstraintValidator<Extensao, String>{

	private boolean extensoesSaoValidas;
	private String[] extensoes;
	private boolean aceitaNull;
	
	@Override
	public void initialize(Extensao annotation) {
		this.extensoesSaoValidas = annotation.saoValidas();
		this.extensoes = annotation.extensoes();
		this.aceitaNull = annotation.aceitaNull();
	}

	@Override
	public boolean isValid(String extensao, ConstraintValidatorContext context) {
		if(extensao == null){
			return this.aceitaNull;
		}
		
		boolean extensaoEncontrada = false;
		
		for(String ext : extensoes){
			if(extensao.equals(ext)){
				extensaoEncontrada = true;
				break;
			}
		}		
		
		if(extensoesSaoValidas){
			return extensaoEncontrada;
		}else{
			return !extensaoEncontrada;
		}
	}
	
}
