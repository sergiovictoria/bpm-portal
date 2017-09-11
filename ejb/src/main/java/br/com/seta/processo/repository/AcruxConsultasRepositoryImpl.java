package br.com.seta.processo.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.Query;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.MapNcmDto;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(AcruxConsultasRepository.class)
public class AcruxConsultasRepositoryImpl extends GenericJpaDaoConSinco<String> implements AcruxConsultasRepository {

     private static final long serialVersionUID = 1L;


	@Override
	public List<String> findEmbalagem() {
		String sql = "select a.lista from max_atributofixo a"
					+ " where a.tipatributofixo = 'EMBALAGEM' order by a.lista";
	
		Query query = getEntityManager().createNativeQuery(sql);
		
		List<String> list = query.getResultList();
		
	    return list;
	}
	
	@Override
	public List<String> findOrigemProduto(){
		String sql = "select CONCAT(a.lista, CONCAT(' - ', a.descricao)) from max_atributofixo a where a.tipatributofixo like '%ORIGEMMERCADORIA%' order by a.lista";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		List<String> list = query.getResultList();
		
		return list;
	}

	@Override
	public List<MapNcmDto> findNcm(long first, long count, String ncm) {
		String sql = "SELECT A.CODNCM, A.DESCREDUZIDA FROM MAP_NCM A WHERE 1=1";
		
		if(ncm != null && !ncm.isEmpty())
			sql += " AND A.CODNCM || A.DESCREDUZIDA LIKE :DESCREDUZIDA";
		
		Query query = getEntityManager().createNativeQuery(sql).setFirstResult((int)first).setMaxResults((int)count);
		
		if(ncm != null && !ncm.isEmpty())
			query.setParameter("DESCREDUZIDA", "%" + ncm + "%");
		
		List<Object[]> list = query.getResultList();
		
		List<MapNcmDto> ncms = new ArrayList<MapNcmDto>();
		
		for(Object[] obj : list){
			MapNcmDto dto = new MapNcmDto();
			
			try { dto.setCodncm( (String) obj[0]);} catch (Exception e) {}
			try { dto.setDescricao( (String) obj[1]);} catch (Exception e) {}
			
			ncms.add(dto);
		}
		
		return ncms;
	}

	@Override
	public Long sizeNcm(String ncm) {
		String sql = "SELECT count(*) FROM MAP_NCM A WHERE 1=1";
		
		if(ncm != null && !ncm.isEmpty())
			sql += " AND A.CODNCM || A.DESCREDUZIDA LIKE :DESCREDUZIDA";
		
		Query query = getEntityManager().createNativeQuery(sql);
		
		if(ncm != null && !ncm.isEmpty())
			query.setParameter("DESCREDUZIDA", "%" + ncm + "%");
		
		Long size = ((BigDecimal) query.getSingleResult()).longValue();
		
		
		return size;
	}

}
