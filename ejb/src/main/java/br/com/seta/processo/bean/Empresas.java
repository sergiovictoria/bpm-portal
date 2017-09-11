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
import br.com.seta.processo.dto.Empresa;
import br.com.seta.processo.dto.EmpresaMatriz;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="Empresas")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class Empresas extends GenericJpaDaoConSinco<Empresa> {
	

	@Inject
    private Logger logger;
	

	private static final long serialVersionUID = 1L;
	private static final String _FIND_EMPRESA_ALL_MATRIZ     =  "SELECT p.nroempresaorc, M.NROEMPRESA, M.RAZAOSOCIAL, M.FANTASIA, M.ENDERECO, M.BAIRRO, M.CEP, M.CIDADE, M.UF, M.NROCGC FROM MAX_EMPRESA M, or_parametro P WHERE 1=1 AND P.NROEMPRESA = M.NROEMPRESA ORDER BY M.FANTASIA";
	private static final String _FIND_EMPRESA_ALL_MATRIZ_ID  =  "SELECT p.nroempresaorc, M.NROEMPRESA, M.RAZAOSOCIAL, M.FANTASIA, M.ENDERECO, M.BAIRRO, M.CEP, M.CIDADE, M.UF, M.NROCGC FROM MAX_EMPRESA M, or_parametro P WHERE 1=1 AND P.NROEMPRESA = M.NROEMPRESA AND M.NROEMPRESA= :nroempresa ";
		
	private static final String _FIND_EMPRESA_ALL            =  "SELECT M.RAZAOSOCIAL, M.FANTASIA, M.NROEMPRESA, M.ENDERECO, M.BAIRRO, M.CEP, M.CIDADE, M.UF, M.NROCGC, M.DIGCGC FROM MAX_EMPRESA M ORDER BY M.FANTASIA";
	private static final String _FIND_EMPRESA_ALL_LIKE       =  "SELECT M.RAZAOSOCIAL, M.FANTASIA, M.NROEMPRESA, M.ENDERECO, M.BAIRRO, M.CEP, M.CIDADE, M.UF, M.NROCGC, M.DIGCGC FROM MAX_EMPRESA M WHERE M.FANTASIA LIKE :fantasia OR (TO_CHAR(M.NROCGC) || TO_CHAR(M.DIGCGC)) = :cnpj  ORDER BY M.FANTASIA";
	
	private static final String _FIND_EMPRESA_ALL_SIZE       =  "SELECT COUNT(*) FROM MAX_EMPRESA";
	private static final String _FIND_EMPRESA_ALL_SIZE_LIKE  =  "SELECT COUNT(*) FROM MAX_EMPRESA M WHERE M.FANTASIA LIKE :fantasia OR (TO_CHAR(M.NROCGC) || TO_CHAR(M.DIGCGC)) = :cnpj";
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Empresa");
	}
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Empresa> getEmpresaS() {
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL);
		List<Empresa> results = (List <Empresa>) getResultList(query,Empresa.class);
		return results;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<EmpresaMatriz> getEmpresaMatriz() {
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL_MATRIZ);
		List<EmpresaMatriz> empresaMatrizs = (List <EmpresaMatriz>) getResultList(query,EmpresaMatriz.class);
		return empresaMatrizs;
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EmpresaMatriz getEmpresaMatrizID(EmpresaMatriz empresaMatriz) {
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL_MATRIZ_ID);
		query.setParameter("nroempresa",empresaMatriz.getNroempresa());	
		List<EmpresaMatriz> empresaMatrizs = getResultList(query,EmpresaMatriz.class);
		return (EmpresaMatriz) (empresaMatrizs.size() > 0 ? empresaMatrizs.get(0) : null);
	}
	
	
	/*****
	 * @author Sérgio da Victória
	 * @param first  Primeiro Registro 
	 * @param count Quantidade de Registro 
	 * @return Lista de Empresas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Empresa> findEmpresaS(long first, long count) {
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL).setFirstResult((int)first).setMaxResults((int)count);
		List<Empresa> empresas = getResultList(query,Empresa.class);
		return empresas;
	}
	
	
	/*****
	 * @author Sérgio da Victória 
	 * @param first  Primeiro Registro 
	 * @param count Quantidade de Registro 
	 * @param razaosocial Fitro descricação razão social  like
	 * @return Lista de Empresas
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public List<Empresa> findEmpresaS(long first, long count, String filtro) {
		if(filtro == null) filtro = "";
		
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL_LIKE).setFirstResult((int)first).setMaxResults((int)count);
		query.setParameter("fantasia", "%"+filtro.toUpperCase()+"%");
		query.setParameter("cnpj", filtro);
		
		return getResultList(query, Empresa.class);
	}	
	
	/*****
	 * @author Sérgio da Victória 
	 * @return Quantidade de Registros Empresas Size
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findEmpresaSize( ) {
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL_SIZE);
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	
	
	/*****
	 * @author Sérgio da Victória 
	 * @param razaosocial Filtero descricação razão social  like
	 * @return Quantidade de Registros Empresas Size
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Long findEmpresaSize(String filtro) {
		if(filtro == null)
			filtro = "";
		
		Query query = getEntityManager().createNativeQuery(_FIND_EMPRESA_ALL_SIZE_LIKE);
		query.setParameter("fantasia", "%"+filtro.toUpperCase()+"%");
		query.setParameter("cnpj", filtro);
		
		return ((BigDecimal) query.getSingleResult()).longValue();
	}
	


	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public EmpresaMatriz getEmpresaMatrizIDS() {
		return null;
	}

	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Empresa");
	}


}
