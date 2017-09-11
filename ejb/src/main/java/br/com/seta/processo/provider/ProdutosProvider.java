package br.com.seta.processo.provider;

import static br.com.seta.processo.constant.StatusCadastroProduto.SOLICITANTE_PRE_CADASTRO;
import static br.com.seta.processo.constant.VariaveisCadastroProduto.CADASTRO_PRODUTO;
import static br.com.seta.processo.constant.VariaveisCadastroProduto.CASE_CADASTRO_PRODUTO;
import static br.com.seta.processo.constant.VariaveisCadastroProduto.FORMULARIO_PRODUTO;
import static br.com.seta.processo.constant.VariaveisCadastroProduto.VERSAO_CADASTRO_PRODUTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.EstadosCadastroProdutos;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.HistoricoProduto;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.utils.PropertiesEJBUtils;


@MessageDriven(activationConfig = { @ActivationConfigProperty(
		propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty (
				propertyName = "destination", propertyValue = "java:/queue/produtos"),
				@ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue="3600"),
				@ActivationConfigProperty(propertyName = "maxMessages", propertyValue = "15"),
				@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "75") 
}, 
mappedName = "java:/queue/produtos", 
name = "ProdutosProvider")
public class ProdutosProvider implements MessageListener {

	@Inject
	private Logger logger;
	@Inject
	private ExecuteRestAPI executeRestAPI;
	private String bpmUser;
	private String bpmPassword;
	
	public ProdutosProvider() {
		this.bpmUser = PropertiesEJBUtils.getInstance().getValues("bpmUser");
		this.bpmPassword = PropertiesEJBUtils.getInstance().getValues("bpmPassword");
	}

	@PostConstruct
	public void init() {
		logger.info("Acessando MD  - Produtos ");
	}


	public void onMessage(Message message) {		
		try {
			ObjectMessage objMsg = (ObjectMessage)message;
			FormularioProduto formularioProduto = (FormularioProduto) objMsg.getObject();			

			CadastroProduto cadastroProduto = new CadastroProduto();
			cadastroProduto.setComentarioCadastro("Enviado pelo Portal Web");
			cadastroProduto.setDescricao("Pré-cadastro de Inclusão de Produto");
			cadastroProduto.setArea("Portal");
			cadastroProduto.setNomeSolicitante("Web - Produtos");
			cadastroProduto.setDataSolicitacao(new java.util.Date());
			cadastroProduto.setNomeRespPreench(formularioProduto.getNomeRespPreench());
			cadastroProduto.setEmailSolicitante(formularioProduto.getEmailSolicitante());
			cadastroProduto.setTelefoneSolicitante(formularioProduto.getTelefoneSolicitante());
			cadastroProduto.setIsPrecadastro(EstadosCadastroProdutos.EM_PRE_ANALISE);
			adicionaItemAoHistorico(formularioProduto, "Usuário Web", "", "Enviado Pelo Portal", SOLICITANTE_PRE_CADASTRO);
			
			Map<String, Object> listVariablesSerializable = new HashMap<String, Object>();
			listVariablesSerializable.put(FORMULARIO_PRODUTO, formularioProduto);
			listVariablesSerializable.put(CADASTRO_PRODUTO, cadastroProduto);
			
			executeRestAPI.execueteCaseWithVariable(this.bpmUser, this.bpmPassword, listVariablesSerializable, CASE_CADASTRO_PRODUTO,VERSAO_CADASTRO_PRODUTO);
			logger.info("Instância criada com sucesso do processo de Cadastro de Produto criada com sucesso");
		} catch (Exception e) {
			logger.error("Ocorreu um erro no momento de iniciar uma nova instância do processo de Cadastro de Produto", e);
		} 

	}
	
	private void adicionaItemAoHistorico(FormularioProduto formularioProduto, String nome, String motivo, String comentario, String status){
		HistoricoProduto historico = new HistoricoProduto();
		historico.setNome(nome);
		historico.setMotivo(motivo);
		historico.setComentario(comentario);
		historico.setStatus(status);
		historico.setData(new Date());
		if (formularioProduto.getHistoricoProdutos()!=null) {
			formularioProduto.getHistoricoProdutos().clear();
		}
		formularioProduto.getHistoricoProdutos().add(historico);
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo MD  - Produtos");
	}


}
