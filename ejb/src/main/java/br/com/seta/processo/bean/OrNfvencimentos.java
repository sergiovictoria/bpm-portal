package br.com.seta.processo.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.Query;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.entity.OrNfvencimento;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name="OrNfvencimentos")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class OrNfvencimentos extends GenericJpaDaoConSinco<OrNfvencimento> {	
	private static final long serialVersionUID = 1L;
	
	
	private static final String SQL_INSERT_NFVENCIMENTO = "INSERT INTO OR_NFVENCIMENTO"
			+ " (OR_NFVENCIMENTO.SEQNOTA,"
			+ " OR_NFVENCIMENTO.NROEMPRESA,"
			+ " OR_NFVENCIMENTO.CONTA,"
			+ " OR_NFVENCIMENTO.DTAVENCTO,"
			+ " OR_NFVENCIMENTO.VALOR,"
			+ " OR_NFVENCIMENTO.DTAPROGRAMADA,"
			+ " OR_NFVENCIMENTO.VLRDESCONTOS,"
			+ " OR_NFVENCIMENTO.VLROUTRASOPDESC,"
			+ " OR_NFVENCIMENTO.VLRACRESCIMOS,"
			+ " OR_NFVENCIMENTO.VLRIMPOSTOS,"
			+ " OR_NFVENCIMENTO.VALORLIQ,"
			+ " OR_NFVENCIMENTO.USUALTERACAO,"
			+ " OR_NFVENCIMENTO.DTAALTERACAO,"
			+ " OR_NFVENCIMENTO.NROPARCELA,"
			+ " OR_NFVENCIMENTO.OBSERVACAO,"
			+ " OR_NFVENCIMENTO.SEQDEPOSITARIO,"
			+ " OR_NFVENCIMENTO.QTDPARCELAS"
			+ " )"
			+ " VALUES"
			+ " (?1,"//SEQNOTA,
			+ " ?2," //NROEMPRESA,
			+ " ?3," //CONTA,
			+ " ?4," //DTAVENCTO,
			+ " ?5," //VALORTOTALPARCELA, 
			+ " ?6," //DTAPROGRAMADA,
			+ " ?7," //VLRDESCONTOS,
			+ " ?8," //VLROUTRASOPDESC,
			+ " ?9," //VLRACRESCIMOS,
			+ " ?10,"//VLRIMPOSTOS,
			+ " ?11,"//VALORLIQPARCELA = VALORTOTALPARCELA,
			+ " ?12,"//USUALTERACAO,
			+ " ?13,"//+ " trunc(sysdate),"//DTAALTERACAO = SYSDATE,
			+ " ?14,"//NROPARCELA,
			+ " ?15,"//OBSERVACAO,
			+ " ?16,"//SEQDEPOSITARIO,
			+ " ?17" //QTDPARCELAS
			+ " )";
	
	public static final String SELECT_DEPOSITARIO = "SELECT NVL(B.SEQDEPOSITARIO, 0)" // NVL(B.SEQDEPOSITARIO, 0) ||' - ' || D.DESCRICAO AS DEPOSITARIO
			+ " FROM RF_PARAMNATNFDESP A, FI_ESPECIE B, RF_PARAMNATNFDESP_ESPECIE C , FI_DEPOSITARIO D"
			+ "  WHERE A.CODHISTORICO = :codnatureza" //CODNATUREZADESPESA
			+ "    AND A.CODHISTORICO = C.CODHISTORICO"
			+ "    AND B.CODESPECIE = C.CODESPECIE"
			+ "    AND A.NROEMPRESA = C.MATRIZ"
			+ "    AND B.NROEMPRESAMAE = C.NROEMPRESAMAE"
			+ "	   AND C.NROEMPRESAMAE IN (SELECT GE.MATRIZ FROM GE_EMPRESA GE WHERE GE.NROEMPRESA = :nroempresa)"
			+ "    AND D.SEQDEPOSITARIO = B.SEQDEPOSITARIO";
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public String findDepositario(Integer nrempresa, Integer codnaturezadespesa) {
		
		Query query = getEntityManager().createNativeQuery(SELECT_DEPOSITARIO);
		
		query.setParameter("codnatureza", codnaturezadespesa);
		query.setParameter("nroempresa", nrempresa);
		
		List<Object> obj =   query.getResultList();
		
		if(obj == null || (obj != null && obj.size() == 0))
			return "";
		else 
			return  obj.get(0).toString();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insereNovoRegistroSolicitacaoPagamento(Long seqNota, Integer nroempresa, Long contadebito, Integer codnaturezadespesa, OrSolicitacaovencto dcto) {
		
		executeNativeQuery(SQL_INSERT_NFVENCIMENTO, seqNota, nroempresa, contadebito, dcto.getDtavencimento(), dcto.getVlrtotal(), dcto.getDtaprogramada(), dcto.getVlrdesconto(),
				dcto.getVlroutoperdesc(), dcto.getVlracrescimo(), new BigDecimal(0), dcto.getVlrtotal(), "Bpm-Seta", new Date(), dcto.getNroparcela(), null, 
				new BigDecimal(findDepositario(nroempresa, codnaturezadespesa)), dcto.getQtdparcela());
		
	}

}
