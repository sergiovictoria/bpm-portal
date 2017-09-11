package br.com.seta.processo.bean.dao;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.mongodb.morphia.Datastore;

import br.com.seta.processo.entity.FontePagadora;
import br.com.seta.processo.transaction.ManagerMongo;

/**
 * Classe responsável pelo acesso e operações com a base de dados do MongoDB para entidade Fonte Pagadora
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="FontesPagadoras")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class FontesPagadorasDao {
	
	private static final String NOME_REDUZIDO = "nomeReduzido";
	private ManagerMongo<FontePagadora> managerMongo;
	private Datastore datastore;	
	
	@PostConstruct
	public void init(){
		managerMongo = new ManagerMongo<FontePagadora>(FontePagadora.class); 
		this.datastore = managerMongo.getDatastore();
	}
	
	/**
	 * Retorna todas as fontes pagadoras
	 * 
	 * @return Uma lista contendo as fontes pagadoras
	 */
	public List<FontePagadora> buscaFontesPagadoras(){		
		return managerMongo.findAll();
	}
	
	public List<FontePagadora> buscaFontesPagadorasPorNomeReduzido(String nomeReduzido){
		return datastore.createQuery(FontePagadora.class).field(NOME_REDUZIDO).contains(nomeReduzido).asList();
	}
	
	public FontePagadora buscaFontePagadora(int nroEmpresa){
		return (FontePagadora) managerMongo.findOne("nroEmpresa", nroEmpresa);		
	}
	
	/**
	 * Remove todas as fontes pagadoras
	 */
	public void removeTodasFontesPagadoras(){
		datastore.delete(datastore.createQuery(FontePagadora.class));
	}
	
	/**
	 * Adiciona ou atualiza uma fonte pagadora
	 * 
	 * @param fontePagadora 
	 */
	public void salva(FontePagadora fontePagadora){
		datastore.save(fontePagadora);
	}
	
	/**
	 * Adiciona fontes pagadoras
	 * 
	 * @param fontesPagadoras
	 */
	public void adicionaFontesPagadoras(Iterable<FontePagadora> fontesPagadoras){
		datastore.save(fontesPagadoras);
	}
	
	/**
	 * Atualiza as fontes pagadoras. Primeiro todas as fontes pagadoras são excluídas e depois novas fontes pagadoras são adiconadas
	 * 
	 * @param fontesPagadoras
	 */
	public void atualizaFontesPagadoras(Iterable<FontePagadora> fontesPagadoras){
		removeTodasFontesPagadoras();
		adicionaFontesPagadoras(fontesPagadoras);
	}
}
