package br.com.seta.processo.parse;

import java.lang.reflect.Field;

import br.com.seta.processo.dto.FormularioFornecedor;

public class ParseUpperCase {
	
	public FormularioFornecedor parse(FormularioFornecedor ff) throws IllegalArgumentException, IllegalAccessException{
		
		Field[] fields = ff.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.getType().isAssignableFrom(String.class)){
				field.setAccessible(true);
				String valor = (String)field.get(ff);
				if(valor != null) 
					field.set(ff, valor.toUpperCase());
			}
		}
		
		return ff;
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException{
		ParseUpperCase parse = new ParseUpperCase();
		FormularioFornecedor ff = new FormularioFornecedor();
		ff.setBairro("jd são josé");
		ff.setCidade("suzano");
		ff.setCpnjJuridico("123454");
		
		FormularioFornecedor parse2 = parse.parse(ff);
		System.out.println(parse2);
	}
}
