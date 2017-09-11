package br.com.seta.processo.helper;

import org.bonitasoft.engine.identity.ContactData;
import org.bonitasoft.engine.identity.ContactDataUpdater;

import br.com.seta.processo.dtobonita.Contatos;

/**
 * Helper para conversão dos tipos do Bonita BPM referente a contatos do usuario para um objeto da classe br.com.seta.processo.dtobonita.Contatos
 * 
 * @author Hromenique Cezniowscki Leite Batista    
 *
 */
public class ContatosHelper {
	
	public Contatos criaContato(ContactData contactData){
		if(contactData == null) return null;
		
		Contatos contatos = new Contatos();
		contatos.setEndereco(contactData.getAddress());
		contatos.setCidade(contactData.getCity());
		contatos.setEstado(contactData.getState());
		contatos.setPais(contactData.getCountry());
		contatos.setCep(contactData.getZipCode());
		contatos.setEmail(contactData.getEmail());
		contatos.setTelefone(contactData.getPhoneNumber());
		contatos.setCelular(contactData.getMobileNumber());
		
		return contatos;
	}
	
	public ContactDataUpdater criaContactDataUpdater(Contatos contatos){
		if(contatos == null) return null;
		
		ContactDataUpdater updater = new ContactDataUpdater();
		updater.setAddress(contatos.getEndereco());
		updater.setCity(contatos.getCidade());
		updater.setState(contatos.getEstado());
		updater.setCity(contatos.getPais());
		updater.setZipCode(contatos.getCep());
		updater.setEmail(contatos.getEmail());
		updater.setPhoneNumber(contatos.getTelefone());
		updater.setMobileNumber(contatos.getCelular());
		
		return updater;
	}

}
