package br.com.seta.processo.parse;

import java.lang.reflect.Field;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Trasfere os valores de um objeto para outro
 * 
 * @author Hromenique Cezniowscki Leite Batista *
 *
 */

@LocalBean
@Stateless(name = "TransferObjectValues")
public class TransferObjectValues {
	
	/**
	 * Trasnfere os valores de um objeto para para outro. 
	 * Para este procedimento, é necessário que os nomes e tipos dos campos do objeto de origem e destino sejam iguais.
	 * 
	 * @param sourceObj Objeto origem
	 * @param destinationObj Objeto de destino
	 * @return retorna o mesmo objeto de destino (referência)
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 */
	public Object transferValues(Object sourceObj, Object destinationObj) throws IllegalArgumentException, SecurityException{		
		Field[] fields = sourceObj.getClass().getDeclaredFields();		
		
		for(Field field : fields){
			try {
				field.setAccessible(true);
				Object value = field.get(sourceObj);
				Field destinationField = destinationObj.getClass().getDeclaredField(field.getName());
				destinationField.setAccessible(true);
				destinationField.set(destinationObj, value);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				//caso não exista o campo ou seja um campo de acesso ilegal ou final, não fazer nada
			}			
		}
		
		return destinationObj;
	}
	/*
	public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException, SecurityException{
		FormularioFonecedor ff = new FormularioFonecedor();
		ff.setBairro("Jardim São José");
		ff.setAprovacaoGerenciaComercial(new AprovacaoGerenciaComercial("jkasddjl", null, "jlfdsalkjfljfdsl"));
		
		FormularioFonecedorTransfer ft = new FormularioFonecedorTransfer();
		
		ParseExtractValues<FormularioFonecedor, FormularioFonecedorTransfer> parser = new ParseExtractValues<FormularioFonecedor, FormularioFonecedorTransfer>();
		parser.extractValues(ff, ft);
		
		System.out.println(ft);
	}*/
}
