package br.com.seta.processo.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.ItensContrato;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.ServicoContratado;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name="VerificaContratosIntes")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class VerificaContratosIntes {


	@Inject private Logger logger;
	@Inject private TransacaoMongo transacaoMongo;  
	



	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Verifica Contratos ");
	}

	public List<ItensContrato> saveContratosPendentes(OrRequisicao orRequisicao, Long instancia) {
		java.util.Date data =  new java.util.Date();
		List<ItensContrato> itensComContrato = new ArrayList<ItensContrato>();
		List<ItensContrato> itensSemContrato = new ArrayList<ItensContrato>();	
		
		List<OrReqitensdespesa> orReqitensdespesas = new ArrayList<OrReqitensdespesa>(orRequisicao.getOrReqitensdespesas());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("statusContrato", 4L);
		parameters.put("codigoFornecedor", Long.parseLong(String.valueOf(orRequisicao.getSeqpessoa())));
		
		List<Contrato> contratos = (List<Contrato>) transacaoMongo.find(parameters, Contrato.class);

		if (contratos.size()> 0) {
			for (Contrato dto : contratos) {
				if (dto.getDataFimContrato().compareTo(data) >= 0) {
					for (ServicoContratado servicoContratado : dto.getServicoContratado()) {
						ItensContrato itens = new ItensContrato();
						itens.setCodigoItem(servicoContratado.getCodigoItem());
						itens.setDataInicioContrato(dto.getDataInicioContrato());
						itens.setDataFimContrato(dto.getDataFimContrato());
						itens.setDescricao(servicoContratado.getDescricaoItem());
						itens.setNumeroContrato(dto.getNumeroContrato());
						itensComContrato.add(itens);
					}
				}else {
					for (OrReqitensdespesa itensVerifica : orReqitensdespesas) {
						ItensContrato itens = new ItensContrato();
						itens.setCodigoItem(Long.valueOf(itensVerifica.getCodproduto()));
						itens.setDataInicioContrato(dto.getDataInicioContrato());
						itens.setDataFimContrato(dto.getDataFimContrato());
						itens.setDescricao(itensVerifica.getDescricao());
						itens.setNumeroContrato(dto.getNumeroContrato());
						itens.setVlr(itensVerifica.getVlrtotal().longValue());
						itens.setQuantidade(itensVerifica.getQuantidade().longValue());
						itens.setCfop(itensVerifica.getCfop());
						itensSemContrato.add(itens);
					}
				}
			}
		}else {
			for (OrReqitensdespesa itensVerifica : orReqitensdespesas) {
				ItensContrato itens = new ItensContrato();
				itens.setDescricao(itensVerifica.getDescricao());
				itensSemContrato.add(itens);
			}
		}

		if(itensSemContrato.size()>0) {
			Contrato contrato = new Contrato();
			contrato.setNomeFornecedor(orRequisicao.getNomeFornecedor());
			contrato.setNumeroInstanciaOrigem(instancia);
			contrato.setStatusContrato(1L);
			contrato.setCodigoFornecedor(orRequisicao.getSeqpessoa());
			Set<ServicoContratado> servicos = new HashSet<ServicoContratado>();
			for (ItensContrato i: itensSemContrato) {
				ServicoContratado c = new ServicoContratado();
				c.setNumeroContrato(instancia);
				c.setDescricaoItem(i.getDescricao());
				servicos.add(c);
			}

			contrato.getServicoContratado().addAll(servicos);
			transacaoMongo.save(contrato, Contrato.class);
		}

		return itensSemContrato.size()>0 ?itensSemContrato :null;

	}


	public List<ItensContrato> itensSemCadastro(OrRequisicao orRequisicao) {
		List<ItensContrato> itensSemCadastro = new ArrayList<ItensContrato>();

		List<OrReqitensdespesa> orReqitensdespesas = new ArrayList<OrReqitensdespesa>(orRequisicao.getOrReqitensdespesas());	
		for (OrReqitensdespesa itensVerifica : orReqitensdespesas) {
			String codigo = itensVerifica.getCodproduto();
			if (codigo==null) {
				ItensContrato itens = new ItensContrato();
				itens.setDescricao(itensVerifica.getDescricao());
				itens.setVlr(itensVerifica.getVlrtotal().longValue());
				itens.setQuantidade(itensVerifica.getQuantidade().longValue());
				itens.setCfop(itensVerifica.getCfop());
				itensSemCadastro.add(itens);
			}
		}
		return itensSemCadastro.size()>0 ?itensSemCadastro :null;
	}


	public List<Contrato> findContratoVigenteS(Long seqfornecedor) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("codigoFornecedo",seqfornecedor);
		List<Contrato> contratos = (List<Contrato>) transacaoMongo.find(parameters, Contrato.class);
		return contratos;
	}



	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Verifica Contratos ");
	}

}
