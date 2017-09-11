package br.com.seta.processo.helper;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * Classe que contém uma série de métodos (helpers) para criação de componentes do Wicket
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class WicketHelper {	
	
	/**
	 * Cria um objeto wicket do tipo RadioGroup
	 * 
	 * @param id Um id que será utilizado para a geração dos ids dos radios. Os ids dos radios terão o formato id_[ÍNDICE DO RADIO]
	 * @param valores lista de valores para os radios
	 * @return Um objeto do tipo RadioGroup
	 */
	public static <T extends Serializable> RadioGroup<T> criaRadioGroup(String id, List<T> valores){
		return criaRadioGroup(id, null, valores);
	}
	 
	public static <T extends Serializable> RadioGroup<T> criaRadioGroup(String id, IModel<T> model, List<T> valores){
		RadioGroup<T> radioGroup;
		
		if(model != null){
			radioGroup = new RadioGroup<T>(id, model);
		}else{
			radioGroup = new RadioGroup<T>(id);
		}
		
		int count = 0;		
		Iterator<T> iterator = valores.iterator();
		while(iterator.hasNext()){
			String idRadio = id + "_" + count;
			count++;			
			radioGroup.add(new Radio<T>(idRadio, new Model<T>(iterator.next())));
		}
		
		return radioGroup;
	}
}
