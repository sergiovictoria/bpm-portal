package br.com.seta.processo.bean.dao;

import java.math.BigDecimal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;

import br.com.seta.processo.dao.GenericJpaDaoConSinco;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrSolicitacaovencto;
import br.com.seta.processo.entity.OrNfdespesaEntity;
import br.com.seta.processo.entity.OrNfplanilhalanctoEntity;
import br.com.seta.processo.interceptor.LogInterceptor;

@Stateless(name="OrNfplanilhalanctoDao")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class OrNfplanilhalanctoDao extends GenericJpaDaoConSinco<OrNfplanilhalanctoEntity> {	
	private static final long serialVersionUID = 1L;
	
	private static final String SQL_INSERT_PLANILHA = "insert into OR_NFPLANILHALANCTO"
			+ " (OR_NFPLANILHALANCTO.SEQNOTA,"//1
			+ " OR_NFPLANILHALANCTO.NROPLANILHA,"//2
			+ " OR_NFPLANILHALANCTO.NROLINHA,"//3
			+ " OR_NFPLANILHALANCTO.NROEMPRESA,"//4
			+ " OR_NFPLANILHALANCTO.FILIAL,"//5
			+ " OR_NFPLANILHALANCTO.CONTADEBITO,"//6
			+ " OR_NFPLANILHALANCTO.TIPOENTIDADEDB,"//7
			+ " OR_NFPLANILHALANCTO.CODENTIDADEDB,"//8
			+ " OR_NFPLANILHALANCTO.CENTROCUSTODB,"//9
			+ " OR_NFPLANILHALANCTO.CONTACREDITO,"//10
			+ " OR_NFPLANILHALANCTO.TIPOENTIDADECR,"//11
			+ " OR_NFPLANILHALANCTO.CODENTIDADECR,"//12
			+ " OR_NFPLANILHALANCTO.CENTROCUSTOCR,"//13
			+ " OR_NFPLANILHALANCTO.HISTORICO,"//14
			+ " OR_NFPLANILHALANCTO.HISTORICOORIGINAL,"//15
			+ " OR_NFPLANILHALANCTO.VLRLANCAMENTO,"//16
			+ " OR_NFPLANILHALANCTO.DTACONTABILIZA,"//17
			+ " OR_NFPLANILHALANCTO.TABLINK,"//18
			+ " OR_NFPLANILHALANCTO.NRODOCUMENTO,"//19
			+ " OR_NFPLANILHALANCTO.SITUACAO,"//20
			+ " OR_NFPLANILHALANCTO.TIPOCONTAB,"//21
			+ " OR_NFPLANILHALANCTO.PERCRATEIO,"//22
			+ " OR_NFPLANILHALANCTO.INDORIGEMREQUISICAO"//23
			+ " )"
			+ " SELECT ?1," //SEQNOTA 
			+ " P.NROPLANILHA,"        
			+ " P.NROLINHA,"                
			+ " P.NROEMPRESA,"     
			+ " P.NROEMPRESA,"
			+ " P.CONTADEBITO,"        
			+ " P.TIPOENTIDADEDB,"        
			+ " P.CODENTIDADEDB,"       
			+ " P.CENTROCUSTODB,"        
			+ " P.CONTACREDITO,"        
			+ " P.TIPOENTIDADECR,"        
			+ " P.CODENTIDADECR,"        
			+ " P.CENTROCUSTOCR,"       
			+ " P.HISTORICO,"
			+ " P.HISTORICO,"        
			+ " ?2,"//VALOR        
			+ " null,"       
			+ " null,"       
			+ " null,"        
			+ " null,"        
			+ " null,"        
			+ " null,"        
			+ " null"
			+ " FROM CT_PLANILHALINHA P, CTV_PLANOCONTA DB, CTV_PLANOCONTA CR" 
			+ " WHERE P.CONTADEBITO = DB.CONTA(+)"    
			+ " AND P.NROEMPRESA = DB.NROEMPRESA(+)"    
			+ " AND P.CONTACREDITO = CR.CONTA(+)"    
			+ " AND P.NROEMPRESA = CR.NROEMPRESA(+)"    
			+ " AND P.NROPLANILHA = ?3"   
			+ " AND P.NROEMPRESA = ?4"
			+ " AND P.NROLINHA = ?5";
	
	private static final String SQL_INSERT_OR_NFPLANILHALANCTO = 
			"insert into or_nfplanilhalancto " +
					"SELECT ?1, " + //OR_NFDESPESA.SEQNOTA
					"       P.NROLINHA, "+
					"       P.NROPLANILHA, "+
					"       P.NROEMPRESA, "+
					"       P.NROEMPRESA, "+
					"       P.CONTADEBITO, "+
					"       P.TIPOENTIDADEDB, "+
					"       P.CODENTIDADEDB, "+
					"       P.CENTROCUSTODB, "+
					"       P.CONTACREDITO, "+
					"       P.TIPOENTIDADECR, "+
					"       P.CODENTIDADECR, "+
					"       P.CENTROCUSTOCR, "+
					"       P.HISTORICO, "+
					"       0, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null, "+
					"       null "+
					      "FROM CT_PLANILHALINHA P, CTV_PLANOCONTA DB, CTV_PLANOCONTA CR "+
					      "WHERE P.CONTADEBITO = DB.CONTA(+) "+
					"   AND P.NROEMPRESA = DB.NROEMPRESA(+) "+
					"   AND P.CONTACREDITO = CR.CONTA(+) "+
					"   AND P.NROEMPRESA = CR.NROEMPRESA(+) "+
					"   AND P.NROPLANILHA = ("+
					"                           SELECT A.NROPLANILHA "+
					"                            FROM CT_HISTORICO A, RF_PARAMNATNFDESP B "+
					"                            WHERE A.CODHISTORICO = B.CODHISTORICO "+
					"                            AND A.NROEMPRESA = B.NROEMPRESA "+
					"                            AND A.CODHISTORICO = ?2 " + //NATUREZA DE DESPESA( OR_NFDESPESA.CODHISTORICO)
					"                            AND A.NROEMPRESA = ?3 " + //EMPRESA DE LANÇAMENTO DA DESPESA( OR_NFDESPESA.NROEMPRESA)
					"                            AND ROWNUM <= 1 "+
					"                        )"+
					"   AND P.NROEMPRESA = ?3 "+ //EMPRESA DE LANÇAMENTO DA DESPESA ( OR_NFDESPESA.NROEMPRESA )
					  "ORDER BY P.NROLINHA";
	
	private static final String SQL_ATUALIZAR_VALOR_CONTABILIZACAO = 
			"UPDATE or_nfplanilhalancto " +
					"SET "+
					"    VLRLANCAMENTO = ?1, " + //OR_NFDESPESA.VALOR
					"    PERCRATEIO = '100' " + //FIXO 100
					"WHERE SEQNOTA = ?2 " + //OR_NFDESPESA.SEQNOTA
					"AND ROWNUM <= 1"; //ATUALIZAR APENAS A PRIMEIRA LINHA
	
	
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insereNovoRegistro(Long seqNota, Integer codHistorico, Integer nroEmpresa){
		executeNativeQuery(SQL_INSERT_OR_NFPLANILHALANCTO, seqNota, codHistorico, nroEmpresa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void insereNovoRegistroSolicitacaoPagamento(Long seqNota, OrReqplanilhalancto lcto) {
		executeNativeQuery(SQL_INSERT_PLANILHA, seqNota,
												lcto.getVlrlancamento(),
												lcto.getNroplanilha(),
												lcto.getNroempresa(),
												lcto.getNrolinha());
	}
	
	public void insereNovoRegistro(OrNfdespesaEntity nfDespesa){
		Long seqNota = nfDespesa.getSeqnota();
		Integer codHistorico = nfDespesa.getCodhistorico().intValue();
		Integer nroEmpresa = new Integer(nfDespesa.getNroempresa());
		insereNovoRegistro(seqNota, codHistorico, nroEmpresa);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void atualizaValorContabilizacao(BigDecimal valor, Long seqNota){
		executeNativeQuery(SQL_ATUALIZAR_VALOR_CONTABILIZACAO, valor, seqNota);		
	}

}
