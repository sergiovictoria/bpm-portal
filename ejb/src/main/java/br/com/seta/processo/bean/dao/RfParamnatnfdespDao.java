package br.com.seta.processo.bean.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.RfParamnatnfdespEntity;

/**
 * DAO para entidade RfParamnatnfdesp (tabela RF_PARAMNATNFDESP da CONSINCO)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
@Stateless(name="RfParamnatnfdespDao")
@LocalBean
public class RfParamnatnfdespDao extends GenericJpaDaoConSinco<RfParamnatnfdespEntity> {

	private static final long serialVersionUID = 1L;
	
	private static final String QUERY_BUSCA_RF_PARAMNATNFDESP = "SELECT * FROM RF_PARAMNATNFDESP WHERE NROEMPRESA = ?1 AND CODHISTORICO = ?2";
	
	public RfParamnatnfdespEntity busca(Integer nroEmpresa, Integer codHistorico){
		List<RfParamnatnfdespEntity> resultados = findFromNativeQuery(QUERY_BUSCA_RF_PARAMNATNFDESP, nroEmpresa, codHistorico);
		if(resultados.isEmpty()){
			return null;
		}
		return resultados.get(0);
	}

}
