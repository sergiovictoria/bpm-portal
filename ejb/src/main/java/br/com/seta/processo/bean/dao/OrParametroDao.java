package br.com.seta.processo.bean.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.OrParametroEntity;

@Stateless(name="OrParametroDao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class OrParametroDao extends GenericJpaDaoConSinco<OrParametroEntity> {

	private static final long serialVersionUID = 1L;
	
	private static final String QUERY_BUSCA_OR_PARAMETRO = "SELECT * FROM OR_PARAMETRO WHERE NROEMPRESA = ?1";
	
	public OrParametroEntity busca(Integer nroEmpresa){
		return findOneFromNativeQuery(QUERY_BUSCA_OR_PARAMETRO, nroEmpresa);
	}

}
