package br.com.seta.processo.bean.dao;

import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.entity.CtvHistorico;
import br.com.seta.processo.entity.GeEmpresaEntity;

/**
 * DAO para entidade CtHistorico (CT_HISTORICO na CONSINCO)
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */


@Stateless(name="CtvHistoricoDao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@LocalBean
public class CtvHistoricoDao extends GenericJpaDaoConSinco<CtvHistorico> {
	
	private static final long serialVersionUID = 1L;
	
	
	public static final String QUERY_NATUREZAS_DESPESA_POR_EMPRESA = "SELECT CTV_HISTORICO.NROEMPRESA, CTV_HISTORICO.CODHISTORICO, CTV_HISTORICO.DESCRICAO  FROM CTV_HISTORICO, RFV_PARAMNATNFDESP "+
            "WHERE CTV_HISTORICO.CODHISTORICO = RFV_PARAMNATNFDESP.CODHISTORICO "+
            "AND CTV_HISTORICO.NROEMPRESA = RFV_PARAMNATNFDESP.NROEMPRESA "+
            "AND CTV_HISTORICO.NROEMPRESA = (SELECT A.MATRIZ FROM GE_EMPRESA A WHERE A.NROEMPRESA = :nroEmpresa ) "+
            "AND CTV_HISTORICO.CODHISTORICO IN (SELECT A.CODHISTORICO FROM RF_PARAMNATNFDESP A "+
            "WHERE A.NROEMPRESA = (SELECT NVL(B.NROEMPRESAHIST, B.NROEMPRESA) FROM ORV_NROEMPRESAPLANO B "+
            "WHERE B.NROEMPRESA =(SELECT A.MATRIZ FROM GE_EMPRESA A WHERE A.NROEMPRESA = :nroEmpresa ))) "+
            "AND RFV_PARAMNATNFDESP.STATUS = 'A' ORDER BY CTV_HISTORICO.CODHISTORICO ASC";
            
	
	public static final String QUERY_NATUREZAS_DESPESA_POR_EMPRESA_E_DESCRICAO ="SELECT CTV_HISTORICO.NROEMPRESA, CTV_HISTORICO.CODHISTORICO, CTV_HISTORICO.DESCRICAO FROM CTV_HISTORICO, RFV_PARAMNATNFDESP "+
            "WHERE CTV_HISTORICO.CODHISTORICO = RFV_PARAMNATNFDESP.CODHISTORICO "+
            "AND CTV_HISTORICO.NROEMPRESA = RFV_PARAMNATNFDESP.NROEMPRESA "+
            "AND CTV_HISTORICO.NROEMPRESA = (SELECT A.MATRIZ FROM GE_EMPRESA A WHERE A.NROEMPRESA = :nroEmpresa ) "+
            "AND CTV_HISTORICO.CODHISTORICO IN (SELECT A.CODHISTORICO FROM RF_PARAMNATNFDESP A "+
            "WHERE A.NROEMPRESA = (SELECT NVL(B.NROEMPRESAHIST, B.NROEMPRESA) FROM ORV_NROEMPRESAPLANO B "+
            "WHERE B.NROEMPRESA =(SELECT A.MATRIZ FROM GE_EMPRESA A WHERE A.NROEMPRESA = :nroEmpresa ))) "+
            "AND RFV_PARAMNATNFDESP.STATUS = 'A' AND CTV_HISTORICO.DESCRICAO LIKE :descricao ORDER BY CTV_HISTORICO.CODHISTORICO ASC ";
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CtvHistorico> listaCtHistoricoPorEmpresa(Integer nroEmpresa){
		Query query = getEntityManager().createNativeQuery(QUERY_NATUREZAS_DESPESA_POR_EMPRESA);
		query.setParameter("nroEmpresa", nroEmpresa);
		return getResultList(query,CtvHistorico.class);
	}

	public List<CtvHistorico> listaCtHistoricoPorEmpresa(GeEmpresaEntity empresa){
		return listaCtHistoricoPorEmpresa(empresa.getNroempresa());
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<CtvHistorico> listaCtHistoricoPorEmpresaEDescricao(Integer nroEmpresa, String descricao) {
		Query query = getEntityManager().createNativeQuery(QUERY_NATUREZAS_DESPESA_POR_EMPRESA_E_DESCRICAO);
		query.setParameter("nroEmpresa", nroEmpresa);
		query.setParameter("descricao",  "%"+descricao+"%");
		return getResultList(query,CtvHistorico.class);
	}

	public List<CtvHistorico> listaCtHistoricoPorEmpresaEDescricao(GeEmpresaEntity empresa, String descricao){
		return listaCtHistoricoPorEmpresaEDescricao(empresa.getNroempresa(), descricao);
	}
	
	
}
