package br.com.seta.processo.bean.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.GeEmpresaEntity;

/**
 * 
 * Representa as operações sobre a entidade GE_EMPRESA da CONSINCO
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="GeEmpresasDao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class GeEmpresasDao extends GenericJpaDaoConSinco<GeEmpresaEntity> {
	private static final long serialVersionUID = 1L;	
	
	public GeEmpresaEntity buscaEmpresa(Integer nroEmpresa){
		List<GeEmpresaEntity> empresas = findFromNativeNamedQuery(GeEmpresaEntity.QUERY_EMPRESAS_ATIVAS_POR_NOMEREDUZIDO, nroEmpresa);
		
		if(empresas.isEmpty()) return null;
		
		return empresas.get(0);
	}
	
	public List<GeEmpresaEntity> buscaEmpresasAtivas(){
		return findFromNativeNamedQuery(GeEmpresaEntity.QUERY_EMPRESAS_ATIVAS);
	}	
	
	public List<GeEmpresaEntity> buscaEmpresasAtivasPorNomeReduzido(String nomeReduzido){
		return findFromNativeNamedQuery(GeEmpresaEntity.QUERY_EMPRESAS_ATIVAS_POR_NOMEREDUZIDO, nomeReduzido);
	}
}
