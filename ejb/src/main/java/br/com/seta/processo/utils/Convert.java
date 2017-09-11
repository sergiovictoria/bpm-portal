package br.com.seta.processo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Qualifier;

import org.hibernate.collection.internal.PersistentBag;

@SuppressWarnings({ "unchecked" })
public class Convert<T> {
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
	public @interface ConverterAtributo {
		public enum ObjetoTipo {
			DTO_ENTITY,
			LIST,
			SET;
		}
		
		ObjetoTipo objetoTipo() default ObjetoTipo.DTO_ENTITY;
	}
	
	@Qualifier
	@Retention(RetentionPolicy.RUNTIME)
	@Target({ElementType.TYPE})
	public @interface ClasseDestino {
		 Class<?> classeDestino();
	}

	/**
	 * Converte DTO em Entidade ou Entidade em DTO <br/>
	 * As propriedades devem ser sempre do mesmo <b>TIPO</b> e ter o mesmo
	 * <b>NOME</b> <br/>
	 * Exemplo: <br/>
	 * <b>ORIGEM:</b> private String nome; <b>DESTINO:</b> private String nome;
	 * <br/>
	 * <li>convertOriginToDestiny(entidade, dto)</li>
	 * <li>convertOriginToDestiny(dto, entidade)</li>
	 * <li>convertOriginToDestiny(entidade, entidade)</li>
	 * <li>convertOriginToDestiny(dto, dto)</li>
	 * <li>convertOriginToDestiny(abacaxi, pera)</li>
	 * <br/> <br/>
	 * <b>===========================================</b>
	 * <br/> <br/>
	 * Para a conversão é necessario utilizar as interfaces @ClasseDestino que é usado na declaração da classe onde deve-se apontar a classe destino 
	 * e a @ConverterAtributo que deve ser usado nos atributos que são objetos(Dtos, Entidades, List, etc...) caso devam ser convertidas.
	 * 
	 * @param T
	 *            origin uma Entidade ou Dto
	 * @param T
	 *            destiny uma Entidade ou Dto
	 * @return T destiny preenchido
	 * 
	 * @author joao
	 * @throws Exception 
	 */
	
	public static <T> T convertOriginToDestiny(T origin, T destiny, boolean segundoNivel) throws Exception {

		Field originFields[] = origin.getClass().getDeclaredFields();
		Field destinyFields[] = destiny.getClass().getDeclaredFields();

		for (Field destinyField : destinyFields) {

			if (!destinyField.getName().equals("serialVersionUID")) {

				for (Field originField : originFields) {
					if (!originField.getName().equals("serialVersionUID") && destinyField.getName().equals(originField.getName())) {

						destinyField.setAccessible(true);
						originField.setAccessible(true);

						try {

							if (destinyField.getType().equals(originField.getType()) && !originField.isAnnotationPresent(ConverterAtributo.class)) {
								
								destinyField.set(destiny, originField.get(origin));
								
								break;
							} else {
								if (originField.isAnnotationPresent(ConverterAtributo.class)) {
									ConverterAtributo annotation = originField.getAnnotation(ConverterAtributo.class);

									if (originField.get(origin) != null && !(originField.get(origin) instanceof PersistentBag) && !segundoNivel) {

										T newDestiny = null;
										
										switch (annotation.objetoTipo()) {
										case LIST:
											List<T> listOrigemList = (List<T>) originField.get(origin);
											List<T> listDestinoList = new ArrayList<T>();

											for (T t : listOrigemList) {
												
												//CONVERTE PARA UMA NOVA CLASSE GENERICA
												T newOrigin = t;
												
												newDestiny = getNovoDestino(destiny, destinyField, newDestiny, newOrigin);
												// CHAMO RECURSIVAMENTE PARA
												// PREENCHER OBJETOS INTERNOS
												convertOriginToDestiny(t, newDestiny, true);

												listDestinoList.add(newDestiny);
											}

											// SETO O OBJETO INSTANCIADO NO
											// OBJETO PAI
											if (listDestinoList.size() > 0)
												destinyField.set(destiny, listDestinoList);
											break;
										case SET:
											// ############################
											// NÃO FOI TESTADO
											// ############################
											List<T> listOrigem = (List<T>) originField.get(origin);
											List<T> listDestino = new ArrayList<T>();

											for (T t : listOrigem) {
												//CONVERTE PARA UMA NOVA CLASSE GENERICA
												T newOrigin = t;
												
												newDestiny = getNovoDestino(destiny, destinyField, newDestiny, newOrigin);
												// CHAMO RECURSIVAMENTE PARA
												// PREENCHER OBJETOS INTERNOS
												convertOriginToDestiny(t, newDestiny, true);

												listDestino.add(newDestiny);
											}

											// SETO O OBJETO INSTANCIADO NO
											// OBJETO PAI
											if (listDestino.size() > 0) {
												Set<T> set = new HashSet<T>(listDestino);

												destinyField.set(destiny, set);
											}
											break;
										default:											
											
											//PEGA O OBJETO ORIGEM DO CAMPO
											Object obj = originField.get(origin);
											
											//CONVERTE PARA UMA NOVA CLASSE GENERICA
											T newOrigin = (T) obj;
											
											newDestiny = getNovoDestino(destiny, destinyField, newDestiny, newOrigin);
											
											convertOriginToDestiny(newOrigin, newDestiny, true);
											// SETO O OBJETO INSTANCIADO NO
											// OBJETO PAI
											destinyField.set(destiny, newDestiny);

											break;
										}
									}

									break;
								}
							}

						} catch (IllegalArgumentException | IllegalAccessException | ClassNotFoundException | InstantiationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}

		return destiny;
	}



	private static <T> T getNovoDestino(T destiny, Field destinyField, T newDestiny,
			T newOrigin) throws IllegalAccessException, ClassNotFoundException,
			InstantiationException, Exception {
		//VALLIDA SE O DESTINO ESTA NULL
		if (destinyField.get(destiny) == null) {
			
			//VE SE A ORIGEM POSSUI A ANOTAÇÃO QUE POSSUI A CLASSE DESTINO
			if(newOrigin.getClass().isAnnotationPresent(ClasseDestino.class)){
				ClasseDestino classDestiny = newOrigin.getClass().getAnnotation(ClasseDestino.class);
				
				//CRIA UMA NOVA CLASSE DESTINO COM O QUE ESTA NA ANOTAÇÃO
				Class<T> classT = (Class<T>) Class.forName(classDestiny.classeDestino().getCanonicalName());
				
				//INSTANCIA UM NOVO DESTINO
				newDestiny = classT.newInstance();
			} else {
				throw new Exception("O objeto origem "+newOrigin.getClass().getName()+" não possui a anotação para apontar quem é o destino ou está definido como LAZY na objeto pai.");
			}
			
		} else {
			newDestiny = (T) destinyField.get(destiny);
		}
		return newDestiny;
	}



	@SuppressWarnings("hiding")
	public static <T> T convertOriginToDestiny(T origin, T destiny) throws Exception {
		return convertOriginToDestiny(origin, destiny, false);
	}

}
