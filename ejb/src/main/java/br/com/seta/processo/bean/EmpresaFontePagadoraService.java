package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.seta.processo.bean.dao.CtvHistoricoDao;
import br.com.seta.processo.bean.dao.FontesPagadorasDao;
import br.com.seta.processo.bean.dao.GeEmpresasDao;
import br.com.seta.processo.entity.CategoriaNaturezaDespesa;
import br.com.seta.processo.entity.CtvHistorico;
import br.com.seta.processo.entity.FontePagadora;
import br.com.seta.processo.entity.GeEmpresaEntity;
import br.com.seta.processo.helper.FontePagadoraHelper;
import br.com.seta.processo.helper.NaturezaDespesaHelper;

/**
 * Serviço responsável por mapear empredas como fontes pagadoras
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="EmpresaFontePagadoraService")
@LocalBean
//@Interceptors({LogInterceptor.class})
public class EmpresaFontePagadoraService {
	
	@Inject
	private GeEmpresasDao geEmpresasDao;	
	@Inject
	private FontesPagadorasDao fontesPagadorasDao;
	@Inject
	private CtvHistoricoDao ctvHistoricoDao;
	
	/**
	 * Busca uma fonte pagadora baseando-se no número da empresa
	 * 
	 * @param nroEmpresa 
	 * @return Uma determinada empresa ou null, caso a empresa não exista
	 */
	public FontePagadora buscaFontePagadora(int nroEmpresa){
		return fontesPagadorasDao.buscaFontePagadora(nroEmpresa);
	}
	
	/**
	 * Retorna uma lista contendo todas as empresas, sendo que as empresas que são fontes pagadoras são marcadas como habilitadas
	 * 
	 * @return Uma lista com as empresas
	 */
	public List<FontePagadora> listaFontesPagadoras(){
		List<GeEmpresaEntity> empresas = geEmpresasDao.buscaEmpresasAtivas();
		List<FontePagadora> fontesFromDB = fontesPagadorasDao.buscaFontesPagadoras();
		
		return mapearFontesPagadoras(empresas, fontesFromDB);
	}

	/**
	 * Retorna uma lista contendo todas as empresas, baseando-se em seu nome reduzido, sendo que as empresas que são fontes pagadoras são marcadas como habilitadas
	 * 
	 * @param nomeReduzido 
	 * @return
	 */
	public List<FontePagadora> listaFontesPagadoras(String nomeReduzido){
		if(nomeReduzido == null){
			nomeReduzido = "";
		}else{
			nomeReduzido = nomeReduzido.toUpperCase();
		}
		
		List<GeEmpresaEntity> empresas = geEmpresasDao.buscaEmpresasAtivasPorNomeReduzido("%"+nomeReduzido+"%");
		List<FontePagadora> fontesFromDB = fontesPagadorasDao.buscaFontesPagadorasPorNomeReduzido(nomeReduzido);
		
		return mapearFontesPagadoras(empresas, fontesFromDB);
	}
	
	public List<FontePagadora> listaFontesPagadorasHabilitadas(){		
		List<FontePagadora> fontesPagadorasHabilitadas = new ArrayList<FontePagadora>();		
		for(FontePagadora fontePagadora : listaFontesPagadoras()){
			if(fontePagadora.isHabilitado())
				fontesPagadorasHabilitadas.add(fontePagadora);						
		}
		
		return fontesPagadorasHabilitadas;
	}
	
	/**
	 * Busca uma determinada Natureza de Despesa
	 * 
	 * @param nroEmpresa
	 * @param codHistorico código da categoria de despesa 
	 * @return
	 */
	public CategoriaNaturezaDespesa buscaNaturezaDespesa(int nroEmpresa, Integer codHistorico){
		List<CtvHistorico> ctvHistoricos = ctvHistoricoDao.listaCtHistoricoPorEmpresa(nroEmpresa);		
		
		for(CtvHistorico historico : ctvHistoricos){
			if(historico.getCodhistorico().equals(codHistorico)){
				return NaturezaDespesaHelper.criaNaturezaDespesa(historico);
			}
		}
		
		return null;
	}
	
	public CategoriaNaturezaDespesa buscaNaturezaDespesa(FontePagadora fontePagadora, Integer codHistorico){
		return  buscaNaturezaDespesa(fontePagadora.getNroEmpresa(), codHistorico);
	}
	
	public List<CategoriaNaturezaDespesa> listaNaturezasDespesa(FontePagadora fontePagadora){
		if(fontePagadora == null){
			return new ArrayList<CategoriaNaturezaDespesa>();
		}
		
		List<CtvHistorico> ctvHistoricos = ctvHistoricoDao.listaCtHistoricoPorEmpresa(fontePagadora.getNroEmpresa());
		Set<CategoriaNaturezaDespesa> naturezasDespesaFromDB = fontePagadora.getNaturezasDespesa();
		
		return mapearNaturezasDespesa(ctvHistoricos, naturezasDespesaFromDB);
	}

	public List<CategoriaNaturezaDespesa> listaNaturezasDespesa(FontePagadora fontePagadora, String descricao){
		if(fontePagadora == null){
			return new ArrayList<CategoriaNaturezaDespesa>();
		}
		
		if(descricao == null){
			descricao = "";
		}else{
			descricao = descricao.toUpperCase();
		}
		
		List<CtvHistorico> ctvHistoricos = ctvHistoricoDao.listaCtHistoricoPorEmpresaEDescricao(fontePagadora.getNroEmpresa(), "%"+descricao+"%");
		Set<CategoriaNaturezaDespesa> naturezasDespesaFromDB = fontePagadora.getNaturezasDespesa();
		
		return mapearNaturezasDespesa(ctvHistoricos, naturezasDespesaFromDB);
	}
	
	/**
	 * Atualiza as fontes pagadoras no MongoDB
	 * 
	 * @param fontesPagadoras Empresas que serÃ£o marcadas como fontes pagadoras
	 */
	public void atualizaFontesPagadoras(Set<FontePagadora> fontesPagadoras){
		fontesPagadorasDao.atualizaFontesPagadoras(fontesPagadoras);
	}
	
	public void replicaFontePagadora(FontePagadora origem, FontePagadora destino){
		List<CtvHistorico> historicosDestino = ctvHistoricoDao.listaCtHistoricoPorEmpresa(destino.getNroEmpresa());
		Map<Integer, CategoriaNaturezaDespesa> mapNatDespesaOrigem = new HashMap<Integer, CategoriaNaturezaDespesa>();
		
		for(CategoriaNaturezaDespesa natDespesa : origem.getNaturezasDespesa()){
			mapNatDespesaOrigem.put(natDespesa.getCodHistorico(), natDespesa);
		}
		
		for(CtvHistorico historicoDestino : historicosDestino){
			CategoriaNaturezaDespesa naturezaDespesa = mapNatDespesaOrigem.get(historicoDestino.getCodhistorico());
			if(naturezaDespesa != null){
				destino.adicionaNaturezaDespesa(naturezaDespesa);
			}
		}
		
		fontesPagadorasDao.salva(destino);
	}

	private List<FontePagadora> mapearFontesPagadoras(List<GeEmpresaEntity> empresas, List<FontePagadora> fontesFromDB) {
		List<FontePagadora> results = new ArrayList<FontePagadora>();
		
		for(GeEmpresaEntity empresa : empresas){
			FontePagadora fontePagadora = FontePagadoraHelper.criaFontePagadora(empresa);
			results.add(fontePagadora);
		}
		
		Map<Integer, FontePagadora> fontesMap = new HashMap<Integer, FontePagadora>();
		for(FontePagadora fontePagadora : fontesFromDB){			 
			fontesMap.put(fontePagadora.getNroEmpresa(), fontePagadora);
		}
		
		for(FontePagadora fontePagadora : results){			
			FontePagadora fp = fontesMap.get(fontePagadora.getNroEmpresa());
			if(fp != null){
				fontePagadora.setHabilitado(true);
				fontePagadora.setNaturezasDespesa(fp.getNaturezasDespesa());
			}
		}
		
		return results;
	}

	/**
	 * Faz o de-para (CtHistorico/NaturezaDespesa) dos dados oriundos da CONSINCO para Natureza Despesa 
	 * 
	 * @param ctHistoricos dados orignados da Consincos
	 * @param naturezasDespesaFromDB dados oriundos da base do MongoDB
	 * @return
	 */
	private List<CategoriaNaturezaDespesa> mapearNaturezasDespesa(List<CtvHistorico> ctvHistoricos, Set<CategoriaNaturezaDespesa> naturezasDespesaFromDB) {
		List<CategoriaNaturezaDespesa> results = new ArrayList<CategoriaNaturezaDespesa>();
		
		for(CtvHistorico dto : ctvHistoricos){
			CategoriaNaturezaDespesa natDespesa = NaturezaDespesaHelper.criaNaturezaDespesa(dto);
			results.add(natDespesa);
		}
		
		if(naturezasDespesaFromDB != null && naturezasDespesaFromDB.size() > 0){		
			Map<Integer, CategoriaNaturezaDespesa> categoriasMap = new HashMap<Integer, CategoriaNaturezaDespesa>();
			for(CategoriaNaturezaDespesa natDesp : naturezasDespesaFromDB){
				categoriasMap.put(natDesp.getCodHistorico(), natDesp);
			}
			
			for(CategoriaNaturezaDespesa natDesp : results){
				if(categoriasMap.containsKey(natDesp.getCodHistorico())){
					natDesp.setHabilitado(true);
					natDesp.setChaveAcesso(categoriasMap.get(natDesp.getCodHistorico()).isChaveAcesso());
				}
			}
		}
			
		return results;
	}	
}
