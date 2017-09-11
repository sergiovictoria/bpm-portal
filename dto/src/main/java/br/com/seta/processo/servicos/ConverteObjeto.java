package br.com.seta.processo.servicos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;

import com.mongodb.DBObject;

import br.com.seta.processo.dto.RegistroHistSolicitacaoPagto;
import br.com.seta.processo.dto.SolicitacaoPagamento;

public class ConverteObjeto {
	
	public Map<String, Object> converte(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Map<String, Object> objMapeado = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		Method[] methods = obj.getClass().getMethods();
		
		List<Method> getters = listaGettersReferentesACampos(methods, fields);
		
		for(Method m : getters){
			Object retornoMetodo = m.invoke(obj);
			String nomeDoCampo = nomeDoCampo(m);
			
			if(retornoMetodo == null){
				objMapeado.put(nomeDoCampo, null);
			}
			else if(ehListOuSet(retornoMetodo.getClass()) || ehArray(retornoMetodo)){
				List arr; 
				List arrString = new ArrayList();
				if(ehListOuSet(retornoMetodo)){
					arr = new ArrayList((Collection)retornoMetodo);
				}else{
					arr = Arrays.asList((Object[]) retornoMetodo);
				}
				
				for(Object o : arr){
					if(ehStringOuPrimitivoOuWrapper(o)){
						arrString.add(o.toString());
					}else{
						arrString.add(converte(o));
					}
				}
				
				objMapeado.put(nomeDoCampo, arrString);
			}else if(ehStringOuPrimitivoOuWrapper(retornoMetodo)){
				objMapeado.put(nomeDoCampo, retornoMetodo.toString());
			}else{
				objMapeado.put(nomeDoCampo, converte(retornoMetodo));
			}
		}
		
		return objMapeado;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private boolean ehListOuSet(Object obj){
		Class clazz = obj.getClass();
		return clazz.isAssignableFrom(Set.class) || clazz.isAssignableFrom(List.class);
	}
	
	@SuppressWarnings({ "rawtypes" })
	private boolean ehArray(Object obj){
		Class clazz = obj.getClass();
		return clazz.isArray();
	}
	
	private boolean isObjectId(Object obj){
		return obj.getClass().equals(ObjectId.class);
	}
	

	
	@SuppressWarnings({ "rawtypes", "unused" })
	private boolean ehStringOuPrimitivoOuWrapper(Object obj){
		Class clazz = obj.getClass();
		return ehPrimitivo(obj) || ehString(obj) || ehWrapper(obj);
	}
	
	@SuppressWarnings("rawtypes")
	private boolean  ehPrimitivo(Object obj) {
		Class clazz = obj.getClass();
		return clazz.isPrimitive();
	}

	@SuppressWarnings("rawtypes")
	private boolean ehString(Object obj) {
		Class clazz = obj.getClass();
		return String.class == clazz;
	}

	@SuppressWarnings("rawtypes")
	private boolean ehWrapper(Object obj) {
		Class clazz = obj.getClass();
		return Character.class == clazz || Boolean.class == clazz || clazz.isAssignableFrom(Number.class);
	}
	
	private List<Method> listaGettersReferentesACampos(Method[] metodos, Field[] campos){
		List<Method> gettersFiltrados = new ArrayList<Method>();
		List<Method> getters = listaGetters(metodos);
		
		for(Method m : getters){
			for(Field f : campos){
				if(nomeDoCampo(m).equals(f.getName()))
					gettersFiltrados.add(m);				
			}
		}	
		
		return gettersFiltrados;
	}
	
	private String nomeDoCampo(Method m){
		String nomeMetodo = m.getName();
		nomeMetodo = nomeMetodo.replaceFirst("get", "");
		return desCapitaliza(nomeMetodo);
	}
	
	
	private String desCapitaliza(String nomeMetodo) {
		if(nomeMetodo.length() == 0){
			return "";
		}
		
		return nomeMetodo.substring(0, 1).toLowerCase() + nomeMetodo.substring(1);
	}


	private List<Method> listaGetters(Method[] metodos){
		List<Method> getters = new ArrayList<Method>();
		
		for(Method m : metodos){
			if(ehGetter(m)){
				getters.add(m);
			}
		}
		
		return getters;
	}
	
	private boolean ehGetter(Method method) {
		if (!method.getName().startsWith("get"))
			return false;
		if (method.getParameterTypes().length != 0)
			return false;
		if (void.class.equals(method.getReturnType()))
			return false;

		return true;
	}
}
