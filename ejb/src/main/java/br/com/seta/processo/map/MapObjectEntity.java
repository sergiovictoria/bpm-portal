package br.com.seta.processo.map;

import java.beans.PropertyDescriptor;
import java.util.Calendar;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrReqplanilhalancto;
import br.com.seta.processo.dto.OrRequisicaovencto;
import br.com.seta.processo.entity.OrReqitensdespesaEntity;
import br.com.seta.processo.entity.OrReqitensdespesaId;
import br.com.seta.processo.entity.OrReqplanilhalanctoEntity;
import br.com.seta.processo.entity.OrReqplanilhalanctoId;
import br.com.seta.processo.entity.OrRequisicaovenctoEntity;
import br.com.seta.processo.entity.OrRequisicaovenctoId;



public final class MapObjectEntity {
	
	
	
	public static OrReqitensdespesaEntity mapIntensDespesasEntity(OrReqitensdespesa orReqitensdespesa, Long seqrequisicao) {
		OrReqitensdespesaEntity orReqitensdespesaEntity = new OrReqitensdespesaEntity();
		orReqitensdespesaEntity.setCfop(orReqitensdespesa.getCfop());
		orReqitensdespesaEntity.setCodproduto(orReqitensdespesa.getCodproduto());
		orReqitensdespesaEntity.setComplemento(orReqitensdespesa.getComplemento());
		orReqitensdespesaEntity.setDescricao(orReqitensdespesa.getDescricao());
		orReqitensdespesaEntity.setNroempresa(orReqitensdespesa.getNroempresa());
		orReqitensdespesaEntity.setNroempresaorc(orReqitensdespesa.getNroempresaorc());
		orReqitensdespesaEntity.setQuantidade(orReqitensdespesa.getQuantidade());
		orReqitensdespesaEntity.setServico(orReqitensdespesa.getServico());
		orReqitensdespesaEntity.setUnidade(orReqitensdespesa.getUnidade());
		orReqitensdespesaEntity.setUnidadepadrao(orReqitensdespesa.getUnidadepadrao());
		orReqitensdespesaEntity.setVeiculo(orReqitensdespesa.getVeiculo());
		orReqitensdespesaEntity.setVersaoprod(orReqitensdespesa.getVersaoprod());
		orReqitensdespesaEntity.setVlracrescimos(orReqitensdespesa.getVlracrescimos());
		orReqitensdespesaEntity.setVlrdesconto(orReqitensdespesa.getVlrdesconto());
		orReqitensdespesaEntity.setVlritem(orReqitensdespesa.getVlritem());
		orReqitensdespesaEntity.setVlrliqdesp(orReqitensdespesa.getVlrliqdesp());
		orReqitensdespesaEntity.setVlrtotal(orReqitensdespesa.getVlrtotal());
		orReqitensdespesaEntity.setCodtributacao(orReqitensdespesa.getCodtributacao());
		OrReqitensdespesaId orReqitensdespesaId = new OrReqitensdespesaId();
		orReqitensdespesaId.setNroitem(orReqitensdespesa.getNroitem());
		orReqitensdespesaId.setSeqrequisicao(seqrequisicao);
		orReqitensdespesaEntity.setId(orReqitensdespesaId);
		return orReqitensdespesaEntity;
	}

	
	public static OrRequisicaovenctoEntity mapVenctoEntity(OrRequisicaovencto orRequisicaovencto, Long seqrequisicao) {
		OrRequisicaovenctoEntity orRequisicaovenctoEntity = new OrRequisicaovenctoEntity();
		orRequisicaovenctoEntity.setDtaprogramada(orRequisicaovencto.getDtaprogramada());
		orRequisicaovenctoEntity.setDtavencimento(orRequisicaovencto.getDtavencimento());
		orRequisicaovenctoEntity.setVlracrescimo(orRequisicaovencto.getVlracrescimo());
		orRequisicaovenctoEntity.setVlrdesconto(orRequisicaovencto.getVlrdesconto());
		orRequisicaovenctoEntity.setVlrliquido(orRequisicaovencto.getVlrliquido());
		orRequisicaovenctoEntity.setVlroutoperdesc(orRequisicaovencto.getVlroutoperdesc());
		orRequisicaovenctoEntity.setVlrtotal(orRequisicaovencto.getVlrtotal());
		OrRequisicaovenctoId orRequisicaovenctoId = new OrRequisicaovenctoId();
		orRequisicaovenctoId.setNroparcela(orRequisicaovencto.getNroparcela());
		orRequisicaovenctoId.setSeqrequisicao(seqrequisicao);
		orRequisicaovenctoEntity.setId(orRequisicaovenctoId);
		return orRequisicaovenctoEntity;
	}
	
	
	public static OrReqplanilhalanctoEntity mapPlanilhaEntity(OrReqplanilhalancto orReqplanilhalancto, Long seqrequisicao) {
		OrReqplanilhalanctoEntity orReqplanilhalanctoEntity = new OrReqplanilhalanctoEntity();
		orReqplanilhalanctoEntity.setCentrocustocr(orReqplanilhalancto.getCentrocustocr());
		orReqplanilhalanctoEntity.setCentrocustodb(orReqplanilhalancto.getCentrocustodb());
		orReqplanilhalanctoEntity.setCodentidadecr(orReqplanilhalancto.getCodentidadecr());
		orReqplanilhalanctoEntity.setContadebito(orReqplanilhalancto.getContadebito());
		orReqplanilhalanctoEntity.setContacredito(orReqplanilhalancto.getContacredito());
		orReqplanilhalanctoEntity.setDtacontabiliza(orReqplanilhalancto.getDtacontabiliza());
		orReqplanilhalanctoEntity.setFilial(orReqplanilhalancto.getFilial());
		orReqplanilhalanctoEntity.setHistorico(orReqplanilhalancto.getHistorico());
		orReqplanilhalanctoEntity.setNrodocumento(orReqplanilhalancto.getNrodocumento());
		orReqplanilhalanctoEntity.setNroempresa(orReqplanilhalancto.getNroempresa());
		orReqplanilhalanctoEntity.setNroplanilha(orReqplanilhalancto.getNroplanilha());
		orReqplanilhalanctoEntity.setPercrateio(orReqplanilhalancto.getPercrateio());
		orReqplanilhalanctoEntity.setSituacao(orReqplanilhalancto.getSituacao());
		orReqplanilhalanctoEntity.setTablink(orReqplanilhalancto.getTablink());
		orReqplanilhalanctoEntity.setTipocontab(orReqplanilhalancto.getTipocontab());
		orReqplanilhalanctoEntity.setTipoentidadecr(orReqplanilhalancto.getTipoentidadecr());
		orReqplanilhalanctoEntity.setTipoentidadedb(orReqplanilhalancto.getTipoentidadedb());
		orReqplanilhalanctoEntity.setVlrlancamento(orReqplanilhalancto.getVlrlancamento());
		OrReqplanilhalanctoId orReqplanilhalanctoId  = new OrReqplanilhalanctoId();
		orReqplanilhalanctoId.setSeqrequisicao(seqrequisicao);
		orReqplanilhalanctoId.setNrolinha(orReqplanilhalancto.getNrolinha());
		orReqplanilhalanctoEntity.setId(orReqplanilhalanctoId);
		return orReqplanilhalanctoEntity;
	}

	
		
	public static OrReqitensdespesa mapIntesDto(OrReqitensdespesaEntity reqitensdespesaEntity) {
		OrReqitensdespesa orReqitensdespesa = new OrReqitensdespesa();
		orReqitensdespesa.setCfop(reqitensdespesaEntity.getCfop());
		orReqitensdespesa.setCodproduto(reqitensdespesaEntity.getCodproduto());
		orReqitensdespesa.setComplemento(reqitensdespesaEntity.getComplemento());
		orReqitensdespesa.setDescricao(reqitensdespesaEntity.getDescricao());
		orReqitensdespesa.setNroempresa(reqitensdespesaEntity.getNroempresa());
		orReqitensdespesa.setNroempresaorc(reqitensdespesaEntity.getNroempresaorc());
		orReqitensdespesa.setQuantidade(reqitensdespesaEntity.getQuantidade());
		orReqitensdespesa.setServico(reqitensdespesaEntity.getServico());
		orReqitensdespesa.setUnidade(reqitensdespesaEntity.getUnidade());
		orReqitensdespesa.setUnidadepadrao(reqitensdespesaEntity.getUnidadepadrao());
		orReqitensdespesa.setVeiculo(reqitensdespesaEntity.getVeiculo());
		orReqitensdespesa.setVersaoprod(reqitensdespesaEntity.getVersaoprod());
		orReqitensdespesa.setVlracrescimos(reqitensdespesaEntity.getVlracrescimos());
		orReqitensdespesa.setVlrdesconto(reqitensdespesaEntity.getVlrdesconto());
		orReqitensdespesa.setVlritem(reqitensdespesaEntity.getVlritem());
		orReqitensdespesa.setVlrliqdesp(reqitensdespesaEntity.getVlrliqdesp());
		orReqitensdespesa.setVlrtotal(reqitensdespesaEntity.getVlrtotal());
		orReqitensdespesa.setCodtributacao(reqitensdespesaEntity.getCodtributacao());
		OrReqitensdespesaId orReqitensdespesaId = reqitensdespesaEntity.getId();
		orReqitensdespesa.setNroitem(orReqitensdespesaId.getNroitem());
		orReqitensdespesa.setSeqrequisicao(orReqitensdespesaId.getSeqrequisicao());
		orReqitensdespesa.setNroempresa(reqitensdespesaEntity.getNroempresa());
		return orReqitensdespesa;
	}
	

	
	public static OrRequisicaovencto mapVenctoDto(OrRequisicaovenctoEntity orRequisicaovenctoEntity) {
		OrRequisicaovencto orRequisicaovencto = new OrRequisicaovencto();
		orRequisicaovencto.setDtaprogramada(formatMongoDate(orRequisicaovenctoEntity.getDtaprogramada()));
		orRequisicaovencto.setDtavencimento(formatMongoDate(orRequisicaovenctoEntity.getDtavencimento()));
		orRequisicaovencto.setVlracrescimo(orRequisicaovenctoEntity.getVlracrescimo());
		orRequisicaovencto.setVlrdesconto(orRequisicaovenctoEntity.getVlrdesconto());
		orRequisicaovencto.setVlrliquido(orRequisicaovenctoEntity.getVlrliquido());
		orRequisicaovencto.setVlroutoperdesc(orRequisicaovenctoEntity.getVlroutoperdesc());
		orRequisicaovencto.setVlrtotal(orRequisicaovenctoEntity.getVlrtotal());
		OrRequisicaovenctoId orRequisicaovenctoId = orRequisicaovenctoEntity.getId();
		orRequisicaovencto.setNroparcela(orRequisicaovenctoId.getNroparcela());
		orRequisicaovencto.setSeqrequisicao(orRequisicaovenctoId.getSeqrequisicao());
		orRequisicaovenctoEntity.setId(orRequisicaovenctoId);
		return orRequisicaovencto;
	}
	
	
	public static OrReqplanilhalancto mapPlanilhaDto(OrReqplanilhalanctoEntity orReqplanilhalanctoEntity) {
		OrReqplanilhalancto orReqplanilhalancto = new OrReqplanilhalancto();
		orReqplanilhalancto.setCentrocustocr(orReqplanilhalanctoEntity.getCentrocustocr());
		orReqplanilhalancto.setCentrocustodb(orReqplanilhalanctoEntity.getCentrocustodb());
		orReqplanilhalancto.setCodentidadecr(orReqplanilhalanctoEntity.getCodentidadecr());
		orReqplanilhalancto.setContadebito(orReqplanilhalanctoEntity.getContadebito());
		orReqplanilhalancto.setContacredito(orReqplanilhalanctoEntity.getContacredito());
		orReqplanilhalancto.setDtacontabiliza(formatMongoDate(orReqplanilhalanctoEntity.getDtacontabiliza()));
		orReqplanilhalancto.setFilial(orReqplanilhalanctoEntity.getFilial());
		orReqplanilhalancto.setHistorico(orReqplanilhalanctoEntity.getHistorico());
		orReqplanilhalancto.setNrodocumento(orReqplanilhalanctoEntity.getNrodocumento());
		orReqplanilhalancto.setNroempresa(orReqplanilhalanctoEntity.getNroempresa());
		orReqplanilhalancto.setNroplanilha(orReqplanilhalanctoEntity.getNroplanilha());
		orReqplanilhalancto.setPercrateio(orReqplanilhalanctoEntity.getPercrateio());
		orReqplanilhalancto.setSituacao(orReqplanilhalanctoEntity.getSituacao());
		orReqplanilhalancto.setTablink(orReqplanilhalanctoEntity.getTablink());
		orReqplanilhalancto.setTipocontab(orReqplanilhalanctoEntity.getTipocontab());
		orReqplanilhalancto.setTipoentidadecr(orReqplanilhalanctoEntity.getTipoentidadecr());
		orReqplanilhalancto.setTipoentidadedb(orReqplanilhalanctoEntity.getTipoentidadedb());
		orReqplanilhalancto.setVlrlancamento(orReqplanilhalanctoEntity.getVlrlancamento());
		OrReqplanilhalanctoId orReqplanilhalanctoId  = orReqplanilhalanctoEntity.getId();
		orReqplanilhalancto.setSeqrequisicao(orReqplanilhalanctoId.getSeqrequisicao());
		orReqplanilhalancto.setNrolinha(orReqplanilhalanctoId.getNrolinha());
		orReqplanilhalancto.setNroempresa(orReqplanilhalanctoEntity.getNroempresa());
		return orReqplanilhalancto;
	}
	
	
	public static Object copyObject(Object dest, Object orig) {
		PropertyDescriptor [] destDesc = PropertyUtils.getPropertyDescriptors (dest);
		try {
			for (int i=0; i <destDesc.length; i++) {
				Class<?> destType = destDesc[i].getPropertyType();
				Class<?> origType = PropertyUtils.getPropertyType(orig, destDesc[i].getName());
				if (destType!=null && destType.equals(origType) && !destType.equals(Class.class)) {
					if (!Collection.class.isAssignableFrom(origType)) {
						Object value = PropertyUtils.getProperty (orig, destDesc[i].getName());
						if ( (value!=null) && (value instanceof java.util.Date)) {
							value = formatMongoDate(value);
						}
						PropertyUtils.setProperty (dest, destDesc[i].getName(),value);
					}
				}
			}
		}catch (Exception e ) {
			e.printStackTrace();
		}
		return dest;
	}
	
	
	
	public static java.util.Date formatMongoDate(Object value)  {
		if (value==null) {
		   return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime((java.util.Date)value);
		calendar.add(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
		calendar.add(Calendar.MINUTE, Calendar.MINUTE);
		calendar.add(Calendar.SECOND, Calendar.SECOND);
		return calendar.getTime();
	}
	
}
