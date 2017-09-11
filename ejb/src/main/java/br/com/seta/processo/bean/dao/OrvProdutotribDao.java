package br.com.seta.processo.bean.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.OrvProdutotribEntity;

@Stateless(name="OrvProdutotribDao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class OrvProdutotribDao extends GenericJpaDaoConSinco<OrvProdutotribEntity> {
	
	private static final long serialVersionUID = 1L;
	
	private static final String QUERY_BUSCA_ORV_PRODUTO_TRIB = "SELECT * FROM ORV_PRODUTOTRIB "
			+ "WHERE NROEMPRESA = ?1 AND  SEQFORNECEDOR = ?2";
	
	public OrvProdutotribEntity busca(Integer nroEmpresa, Integer seqFornecedor){
		return findOneFromNativeQuery(QUERY_BUSCA_ORV_PRODUTO_TRIB, nroEmpresa, seqFornecedor);
	}

}
