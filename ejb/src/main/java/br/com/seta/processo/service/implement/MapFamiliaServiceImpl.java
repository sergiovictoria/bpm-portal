package br.com.seta.processo.service.implement;

import java.math.BigDecimal;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.bean.MapFamfornecs;
import br.com.seta.processo.bean.MapFamilias;
import br.com.seta.processo.dto.MafFornecedor;
import br.com.seta.processo.dto.MapEmbalagem;
import br.com.seta.processo.dto.MapFamilia;
import br.com.seta.processo.entity.MapEmbalagemEntity;
import br.com.seta.processo.entity.MapFamembalagemId;
import br.com.seta.processo.entity.MapFamfornecEntity;
import br.com.seta.processo.entity.MapFamfornecId;
import br.com.seta.processo.entity.MapFamiliaEntity;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.service.interfaces.MapFamiliaService;
import br.com.seta.processo.utils.Convert;


@Stateless(name = "MapFamiliaService")
@Local(MapFamiliaService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
public class MapFamiliaServiceImpl implements MapFamiliaService {

    @Inject private MapFamilias familiaBean;
    @Inject private MapFamfornecs famfornecBean;
    @Inject private MapEmbalagens embalagemBean;
    @Inject private Logger logger;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public boolean save(MapFamilia dto) {
		MapFamiliaEntity familiaEntity = new MapFamiliaEntity();
		
		if(dto.isPesavelchk())
			familiaEntity.setPesavel("S");
		else
			familiaEntity.setPesavel("N");
		
		if(dto.isBebidaAlcoolica())
			familiaEntity.setIndbebidaalcoolica("S");
		else
			familiaEntity.setIndbebidaalcoolica("N");
		
		if(dto.isVasilhame())
			familiaEntity.setIndvasilhame("S");
		else
			familiaEntity.setIndvasilhame("N");
		
		if(dto.isPermiteDecimais())
			familiaEntity.setPmtdecimal("S");
		else
			familiaEntity.setPmtdecimal("N");
		
		if(dto.isPermiteMultiplicacao())
			familiaEntity.setPmtmultiplicacao("S");
		else
			familiaEntity.setPmtmultiplicacao("N");
		
		BigDecimal zero = new BigDecimal("0");
		
		familiaEntity.setFamilia(dto.getFamilia().toUpperCase());
		familiaEntity.setIndiceformbaseipi(zero);
		familiaEntity.setAliquotaipi(zero);
		
		//CRIA A FAMILIA
		familiaEntity = familiaBean.create(familiaEntity);
		
		//GRAVA OS FORNECEDORES SELECIONADOS NA FAMILIA
		for (MafFornecedor fornecedor : dto.getFornecedores()) {
			//GERO O ID
			MapFamfornecId id = new MapFamfornecId();
			id.setSeqfamilia(familiaEntity.getSeqfamilia());
			id.setSeqfornecedor(fornecedor.getSeqfornecedor());
			
			//SETO A RELAÇÃO FAMILIA/FORNECEDOR
			MapFamfornecEntity famfornec = new MapFamfornecEntity();
			famfornec.setId(id);
			famfornec.setMapFamiliaEntity(familiaEntity);
			
			if(fornecedor.isPrincicalchk())
				famfornec.setPrincipal("S");
			else
				famfornec.setPrincipal("N");
			
			famfornecBean.create(famfornec);
		}
		
		//GRAVA AS EMBALAGENS SETADAS EM UNIDADE/DISPLAY E MASTER
		//AQUI NÃO É FEITO NENHUMA VERIFICAÇÃO DE EMBALAGEM, POR SER UMA NOVA FAMILIA LOGO NÃO POSSUI EMBALAGENS PARA CONFLITAR
		for (MapEmbalagem embalagem : dto.getEmbalagens()) {
			//CRIA ID EMBALAGEM/FAMILIA
			MapFamembalagemId id = new MapFamembalagemId();
			id.setSeqfamilia(familiaEntity.getSeqfamilia());
			id.setQtdembalagem(embalagem.getQtdembalagem());
			
			MapEmbalagemEntity embalagemEntity = new MapEmbalagemEntity();
			
			//CONVERTE AS INFORMAÇÕES DO DTO PARA A ENTIDADE
			try {
				Convert.convertOriginToDestiny(embalagem, embalagemEntity);
			} catch (Exception e) {
				logger.error("ERRO AO CONVERTER A EMBALAGEM DTO EM EMBALAGEM ENTITY DA FAMILIA = " + familiaEntity.getSeqfamilia());
			}
			
			embalagemEntity.setId(id);
			embalagemEntity.setStatus("A");
			
			embalagemBean.create(embalagemEntity);
		}
		
		dto.setSeqfamilia(familiaEntity.getSeqfamilia());
		
		return true;
		
	}


}
