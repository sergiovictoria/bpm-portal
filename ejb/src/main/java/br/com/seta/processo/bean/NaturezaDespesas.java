package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.NaturezaDespesa;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.OderProvider;



@Stateless(name="NaturezaDespesas")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class NaturezaDespesas extends GenericJpaDaoConSinco<NaturezaDespesas> {


	@Inject
	private Logger logger;

	private static final long serialVersionUID = 1L;

	private static final String _NATUREZA_CENTRO_FILTRO_DET = "SELECT HIS.CODHISTORICO || ' - ' ||HIS.DESCRICAO  AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
            "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
            "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA ORDER BY HIS.CODHISTORICO";
	
	
	private static final String _NATUREZA_DESC_ASC = "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                     "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                     "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA ORDER BY HIS.DESCRICAO ASC";
	
	private static final String _NATUREZA_DESC_DES = "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                     "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                     "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA ORDER BY HIS.DESCRICAO DESC";
	
	
	private static final String _NATUREZA_COD_ASC = "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                     "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                     "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA ORDER BY HIS.CODHISTORICO ASC";

    private static final String _NATUREZA_COD_DES =  "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                     "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                     "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA ORDER BY HIS.CODHISTORICO DESC";
     
     
 	private static final String _NATUREZA_DESC_ASC_LIKE = "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                          "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA AND (HIS.DESCRICAO LIKE :historico OR TO_CHAR(HIS.CODHISTORICO) = :codHistorico) ORDER BY HIS.DESCRICAO ASC";

    private static final String _NATUREZA_DESC_DES_LIKE = "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                          "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA AND HIS.DESCRICAO LIKE :historico ORDER BY HIS.DESCRICAO DESC";


    private static final String _NATUREZA_COD_ASC_LIKE =  "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                          "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA AND HIS.DESCRICAO LIKE :historico ORDER BY HIS.CODHISTORICO ASC";

    private static final String _NATUREZA_COD_DES_LIKE =  "SELECT HIS.DESCRICAO AS HISTORICO,  EMP.NROEMPRESA NUMERO_EMPRESA, DECODE(HIS.NROPLANILHA,NULL, HIS.CODPLANILHA, HIS.NROPLANILHA) AS DESCRICAO_NATUREZA , NVL(PAR.EXIGEITENSNOTA,'N') AS EXISTE_ITENS_NOTA, "+ 
                                                          "HIS.CODHISTORICO AS CODHISTORICO, PAR.CGO AS CGC, PAR.CODESPECIE AS CODESPECIE, PAR.MODELONF AS CODMODELO, MOD.DESCRICAO AS DESCRICAO_MODELO, PAR.ESPECIENF AS ESPECIE_NF, EMP.MATRIZ AS EMPRESA_MATRIZ, PAR.CGO AS CGO FROM CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA AND HIS.DESCRICAO LIKE :historico ORDER BY HIS.CODHISTORICO DESC";
     
     
     
	private static final String _NATUREZA_COUNT        =  "SELECT COUNT(*) FROM  CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA";
	
	private static final String _NATUREZA_COUNT_LIKE   =  "SELECT COUNT(*) FROM  CT_HISTORICO HIS, RF_PARAMNATNFDESP PAR, GE_MODELODOC MOD, GE_EMPRESA EMP WHERE HIS.CODHISTORICO = PAR.CODHISTORICO "+
                                                          "AND HIS.NROEMPRESA = PAR.NROEMPRESA AND MOD.CODMODELO = PAR.MODELONF AND PAR.STATUS = 'A' AND EMP.NROEMPRESA = :nroempresa AND EMP.MATRIZ = HIS.NROEMPRESA AND (HIS.DESCRICAO like :historico OR TO_CHAR(HIS.CODHISTORICO) = :codHistorico)";
	
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - NaturezaDespesa");
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<NaturezaDespesa> getNaturezaDespesaS(BigDecimal nroempresa, String oder,long first, long count) {

		
		Query query = null;
		
		if (oder.equals(OderProvider.NatrurezaAscDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_DESC_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.NatrurezaDesDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_DESC_DES).setFirstResult((int)first).setMaxResults((int)count);
			
		}else if (oder.equals(OderProvider.NatrurezaAscCodigo.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_COD_ASC).setFirstResult((int)first).setMaxResults((int)count);
			
		}else {
			query = getEntityManager().createNativeQuery(_NATUREZA_COD_DES).setFirstResult((int)first).setMaxResults((int)count);
		}
		
		query.setParameter("nroempresa", nroempresa);
		
		List<NaturezaDespesa> naturezaDespesas = (List <NaturezaDespesa>) getResultListMap(query, NaturezaDespesa.class);
		return naturezaDespesas;
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<NaturezaDespesa> getNaturezaDespesaSLike(BigDecimal nroempresa, String oder, String historico, long first, long count) {

		Query query = null;
		
		if (oder.equals(OderProvider.NatrurezaAscDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_DESC_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);

			
		}else if (oder.equals(OderProvider.NatrurezaDesDescricao.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_DESC_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);

			
		}else if (oder.equals(OderProvider.NatrurezaAscCodigo.getValue())) {
			query = getEntityManager().createNativeQuery(_NATUREZA_COD_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}else {
			query = getEntityManager().createNativeQuery(_NATUREZA_COD_DES_LIKE).setFirstResult((int)first).setMaxResults((int)count);

		}
		
		query.setParameter("historico", "%"+historico.toUpperCase()+"%");
		query.setParameter("nroempresa", nroempresa);
		
		List<NaturezaDespesa> naturezaDespesas = (List <NaturezaDespesa>) getResultListMap(query, NaturezaDespesa.class);
		return naturezaDespesas;
		

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long size(BigDecimal nroempresa) {
		Query query = getEntityManager().createNativeQuery(_NATUREZA_COUNT);
		query.setParameter("nroempresa", nroempresa);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public long sizeLike(BigDecimal nroempresa, String historico) {
		Query query = getEntityManager().createNativeQuery(_NATUREZA_COUNT_LIKE);
		query.setParameter("historico", "%"+historico.toUpperCase()+"%");
		query.setParameter("nroempresa", nroempresa);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}

	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<NaturezaDespesa> getNaturezaDespesaFiltroItemID(NaturezaDespesa naturezaDespesa) {
		Query query = getEntityManager().createNativeQuery(_NATUREZA_CENTRO_FILTRO_DET);
		query.setParameter("nroempresa", naturezaDespesa.getNumero_empresa());
		List<NaturezaDespesa> naturezaDespesas = getResultListMap(query, NaturezaDespesa.class);
		return naturezaDespesas;
	}
	
	/****
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca Natureza
	 * @param first Primeiro Registro
	 * @param count Numero de Registros
	 * @return Rertorna uma lista com Natureza de Despesas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<NaturezaDespesa> findNaturezaDespesaS(BigDecimal nroempresa, long first, long count) {
		Query query = getEntityManager().createNativeQuery(_NATUREZA_DESC_ASC).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("nroempresa", nroempresa);
		List<NaturezaDespesa> naturezaDespesas = getResultListMap(query, NaturezaDespesa.class);
		return naturezaDespesas;
	}
	
	/***
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca
	 * @param filtro Filtro para Busca no Histórico
	 * @param first Primeiro Registro
	 * @param count Quantidade de Registro
	 * @return Rertorna uma lista com Natureza de Despesas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<NaturezaDespesa> findNaturezaDespesaS(BigDecimal nroempresa,String filtro, long first, long count) {
		if(filtro == null) filtro = "";
		
		Query query = getEntityManager().createNativeQuery(_NATUREZA_DESC_ASC_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("historico",  "%" + filtro.toUpperCase() + "%");
		query.setParameter("codHistorico", filtro);
		List<NaturezaDespesa> naturezaDespesas = getResultListMap(query, NaturezaDespesa.class);
		
		return naturezaDespesas;
	}
	
	
	/***
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca
	 * @return Rertorna Quantidade de Registro Size
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findNaturezaDespesaSize(BigDecimal nroempresa) {
		Query query = getEntityManager().createNativeQuery(_NATUREZA_COUNT);
		query.setParameter("nroempresa", nroempresa);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	/***
	 * @author Sérgio da Victória
	 * @param nroempresa Numero da Empresa para Busca
	 * @param filtro Filtro para Busca no Histórico
	 * @return Rertorna Quantidade de Registro Size
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findNaturezaDespesaSize(BigDecimal nroempresa, String filtro) {
		if(filtro == null) filtro = "";
		
		Query query = getEntityManager().createNativeQuery(_NATUREZA_COUNT_LIKE);
		query.setParameter("nroempresa", nroempresa);
		query.setParameter("historico", "%" + filtro.toUpperCase() + "%");
		query.setParameter("codHistorico", filtro);
		
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
		
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - NaturezaDespesa");
	}




}

