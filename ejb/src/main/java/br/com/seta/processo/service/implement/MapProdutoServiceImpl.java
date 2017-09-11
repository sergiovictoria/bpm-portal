package br.com.seta.processo.service.implement;

import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.bean.GePessoas;
import br.com.seta.processo.bean.MapEmbalagens;
import br.com.seta.processo.bean.MapProdCodigos;
import br.com.seta.processo.bean.MapProdutos;
import br.com.seta.processo.bean.MapFamilias;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.entity.GePessoaEntity;
import br.com.seta.processo.entity.MapEmbalagemEntity;
import br.com.seta.processo.entity.MapFamembalagemId;
import br.com.seta.processo.entity.MapProdcodigoEntity;
import br.com.seta.processo.entity.MapProdcodigoId;
import br.com.seta.processo.entity.MapProdutoEntity;
import br.com.seta.processo.entity.MapProdutoId;
import br.com.seta.processo.exceptions.ParseException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.parse.ParseNormalizacaoString;
import br.com.seta.processo.parse.ParseToUpperCase;
import br.com.seta.processo.service.interfaces.MapProdutoService;

@Stateless(name="MapProdutoService")
@Local(MapProdutoService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
public class MapProdutoServiceImpl implements MapProdutoService {

	@Inject private Logger logger;
	@Inject private MapProdutos mapProdutos;
	@Inject private MapProdCodigos mapProdCodigos;
	@Inject private MapEmbalagens mapEmbalagens;
	@Inject private MapFamilias mapFamilias;
	@Inject private GePessoas gePessoas;

	private ParseToUpperCase<FormularioProduto> parseUpperCase = new ParseToUpperCase<FormularioProduto>();
	private ParseNormalizacaoString<FormularioProduto> parseNormalizacao = new ParseNormalizacaoString<FormularioProduto>();
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB Produto Serviço");
	}

	@Override
	public String create(FormularioProduto formularioProduto) {
		
		try{
			parseUpperCase.parse(formularioProduto);
			parseNormalizacao.parse(formularioProduto);
		}catch(ParseException e){
			throw new RuntimeException(e);
		}
		
		MapProdutoEntity produtoEntity = new MapProdutoEntity();
		MapProdutoId mapProdutoId = new MapProdutoId();
		mapProdutoId.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
		produtoEntity.setComplemento(formularioProduto.getDescCompleta());
		produtoEntity.setDesccompleta(formularioProduto.getDescCompleta());
		produtoEntity.setDescgenerica(formularioProduto.getDescReduzida());
		produtoEntity.setDescreduzida(formularioProduto.getDescReduzida());
		produtoEntity.setIndprecozerobalanca("N");
		produtoEntity.setUsuarioinclusao("BPM-SETA");
		produtoEntity.setIndprocfabricacao("S");
		produtoEntity.setIndreplicacao("S");
		produtoEntity.setDtahorinclusao(new java.util.Date());
		produtoEntity.setIndemitecodprodfiscalnfe("N");
		produtoEntity.setProdutoID(mapProdutoId);
		MapProdutoEntity createdProduto = mapProdutos.create(produtoEntity);
		String seqproduto = createdProduto.getSeqproduto().toString();
		saveCodigoProduto(formularioProduto,seqproduto);
		
		if (formularioProduto.getCnm()!=null) {
			try {
				BigDecimal txtNcm = new BigDecimal(formularioProduto.getCnm());
				mapFamilias.updateNcm(new BigDecimal(formularioProduto.getIdFamilia()),txtNcm);
			}catch (Exception e){
			}
		}
		
		return seqproduto;
	}


	public void saveCodigoProduto(FormularioProduto formularioProduto, String seqproduto) {
		MapProdcodigoEntity prodcodigoEntity;
		MapProdcodigoId mapProdutoId;
		GePessoaEntity gePessoaEntity = gePessoas.buscaGePessoaPorFornecedorByID(new BigDecimal(formularioProduto.getSeqfornecedor()));
		String nrocgccpf =  gePessoaEntity.getNrocgccpf().toString();
		Integer nrocgccpfFinal = new Integer("0");
		
		if (nrocgccpf.length()>=8) {
			nrocgccpf = nrocgccpf.substring(0,8);
			nrocgccpfFinal = new Integer(nrocgccpf); 
		}else {
			nrocgccpfFinal = new Integer(nrocgccpf);
		}
		

		try {


			if ( (formularioProduto.getQuantidadeUnidade()!=null) || (formularioProduto.getQuantidadeUnidade().equals(""))) {
				
				if (!mapEmbalagens.IsExisteEmbalagem(new BigDecimal(formularioProduto.getIdFamilia()), new BigDecimal(formularioProduto.getQuantidadeUnidade()))) {

					MapEmbalagemEntity emb = new MapEmbalagemEntity();
					MapFamembalagemId famembalagemId = new MapFamembalagemId();

					famembalagemId.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeUnidade()));
					famembalagemId.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					
					emb.setEmbalagem(formularioProduto.getEmbalagemUnidade());
					emb.setAltura(new BigDecimal(formularioProduto.getAlturaUnidade() != null ? formularioProduto.getAlturaUnidade() : "0" ));
					emb.setProfundidade(new BigDecimal(formularioProduto.getComprimentoUnidade() != null ? formularioProduto.getComprimentoUnidade() : "0" ));
					emb.setLargura(new BigDecimal(formularioProduto.getLarguraUnidade() != null ? formularioProduto.getLarguraUnidade() : "0" ));
					emb.setPesobruto(new BigDecimal(formularioProduto.getPesoBrutoUnidade() != null ? formularioProduto.getPesoBrutoUnidade() : "0" ));
					emb.setPesoliquido(new BigDecimal(formularioProduto.getPesoLiqUnidade() != null ? formularioProduto.getPesoLiqUnidade() : "0" ));
					emb.setNrobaseexportacao(0L);
					emb.setStatus("A");
					
					emb.setId(famembalagemId);
					mapEmbalagens.create(emb);

				}
				
				String codacesso = formularioProduto.getEanUnidade();
				if (!mapProdCodigos.findMapprod_Cod(nrocgccpfFinal,codacesso,"E")) {
					prodcodigoEntity  = new MapProdcodigoEntity();
					mapProdutoId = new MapProdcodigoId();
					
					mapProdutoId.setCodacesso(formularioProduto.getEanUnidade());
					mapProdutoId.setTipcodigo("E");
					mapProdutoId.setCgcfornec(nrocgccpfFinal);
					
					prodcodigoEntity.setSeqproduto(new BigDecimal(seqproduto));
					prodcodigoEntity.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					prodcodigoEntity.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeUnidade()));
					prodcodigoEntity.setId(mapProdutoId);
					mapProdCodigos.create(prodcodigoEntity);
				}
				
			}

			if ( (formularioProduto.getQuantidadeDisplay()!=null) || (formularioProduto.getQuantidadeDisplay().equals(""))) {
				if (!mapEmbalagens.IsExisteEmbalagem(new BigDecimal(formularioProduto.getIdFamilia()), new BigDecimal(formularioProduto.getQuantidadeDisplay()))) {

					MapEmbalagemEntity emb = new MapEmbalagemEntity();
					MapFamembalagemId famembalagemId = new MapFamembalagemId();

					famembalagemId.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					famembalagemId.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeDisplay() != null ? formularioProduto.getQuantidadeDisplay() : "0" ));
					
					emb.setEmbalagem(formularioProduto.getEmbalagemDisplay() != null ? formularioProduto.getEmbalagemDisplay() : "0" );
					emb.setAltura(new BigDecimal(formularioProduto.getAlturaDisplay() != null ? formularioProduto.getAlturaDisplay() : "0" ));
					emb.setProfundidade(new BigDecimal(formularioProduto.getComprimentoDisplay() != null ? formularioProduto.getComprimentoDisplay() : "0" ));
					emb.setLargura(new BigDecimal(formularioProduto.getLarguraDisplay() != null ? formularioProduto.getLarguraDisplay() : "0" ));
					emb.setPesobruto(new BigDecimal(formularioProduto.getPesoBrutoDisplay() != null ? formularioProduto.getPesoBrutoDisplay() : "0" ));
					emb.setPesoliquido(new BigDecimal(formularioProduto.getPesoLiqDisplay() != null ? formularioProduto.getPesoLiqDisplay() : "0" ));
					emb.setStatus("A");
					
					emb.setId(famembalagemId);
					mapEmbalagens.create(emb);

				}

				String codacesso = formularioProduto.getEanDisplay();
				if (!mapProdCodigos.findMapprod_Cod(nrocgccpfFinal,codacesso,"E")) {
					prodcodigoEntity  = new MapProdcodigoEntity();
					mapProdutoId = new MapProdcodigoId();
					
					mapProdutoId.setCodacesso(formularioProduto.getEanDisplay());
					mapProdutoId.setTipcodigo("E");
					mapProdutoId.setCgcfornec(nrocgccpfFinal);
					
					prodcodigoEntity.setSeqproduto(new BigDecimal(seqproduto));
					prodcodigoEntity.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					prodcodigoEntity.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeDisplay()));
					prodcodigoEntity.setId(mapProdutoId);
					mapProdCodigos.create(prodcodigoEntity);
				}
			}

			if ( (formularioProduto.getQuantidadeEmbalagem()!=null) || (formularioProduto.getQuantidadeEmbalagem().equals(""))) {
				
				if (!mapEmbalagens.IsExisteEmbalagem(new BigDecimal(formularioProduto.getIdFamilia()), new BigDecimal(formularioProduto.getQuantidadeEmbalagem()))) {

					MapEmbalagemEntity emb = new MapEmbalagemEntity();
					MapFamembalagemId famembalagemId = new MapFamembalagemId();

					famembalagemId.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					famembalagemId.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeEmbalagem() != null ? formularioProduto.getQuantidadeEmbalagem() : "0" ));
					
					emb.setEmbalagem(formularioProduto.getEmbalagemEmbalagem() != null ? formularioProduto.getEmbalagemEmbalagem() : "0" );
					emb.setAltura(new BigDecimal(formularioProduto.getAlturaEmbalagem() != null ? formularioProduto.getAlturaEmbalagem() : "0" ));
					emb.setProfundidade(new BigDecimal(formularioProduto.getComprimentoEmbalagem() != null ? formularioProduto.getComprimentoEmbalagem() : "0" ));
					emb.setLargura(new BigDecimal(formularioProduto.getLarguraEmbalagem() != null ? formularioProduto.getLarguraEmbalagem() : "0" ));
					emb.setPesobruto(new BigDecimal(formularioProduto.getPesoBrutoEmbalagem() != null ? formularioProduto.getPesoBrutoEmbalagem() : "0" ));
					emb.setPesoliquido(new BigDecimal("0"));
					emb.setStatus("A");
					emb.setId(famembalagemId);
					mapEmbalagens.create(emb);

				}
				
				String codacesso = formularioProduto.getEanEmbalagem();
				if (!mapProdCodigos.findMapprod_Cod(nrocgccpfFinal,codacesso,"D")) {
					prodcodigoEntity  = new MapProdcodigoEntity();
					mapProdutoId = new MapProdcodigoId();
					
					mapProdutoId.setCodacesso(formularioProduto.getEanEmbalagem());
					mapProdutoId.setTipcodigo("D");
					mapProdutoId.setCgcfornec(nrocgccpfFinal);
					
					prodcodigoEntity.setSeqproduto(new BigDecimal(seqproduto));
					prodcodigoEntity.setSeqfamilia(new BigDecimal(formularioProduto.getIdFamilia()));
					prodcodigoEntity.setQtdembalagem(new BigDecimal(formularioProduto.getQuantidadeEmbalagem()));
					prodcodigoEntity.setId(mapProdutoId);
					mapProdCodigos.create(prodcodigoEntity);
				}
				
				
			}


		}catch (Exception e ) {
		}
	}



	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB Produto Serviço");
	}


}
