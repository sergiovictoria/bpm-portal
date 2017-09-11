package br.com.seta.processo.provider;

import static br.com.seta.processo.constant.StatusCadastroProduto.SOLICITANTE_PRE_CADASTRO;
import static br.com.seta.processo.constant.VariaveisCadastroFornecedores.CADASTRO_FORNECEDOR;
import static br.com.seta.processo.constant.VariaveisCadastroFornecedores.CADASTRO_FORNECEDORES;
import static br.com.seta.processo.constant.VariaveisCadastroFornecedores.FORMULARIO_FORNECEDOR;
import static br.com.seta.processo.constant.VariaveisCadastroFornecedores.STATUS_CADASTRO_FORNECEDOR;
import static br.com.seta.processo.constant.VariaveisCadastroFornecedores.VERSAO_CADASTRO_FORNECEDOR;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.jboss.logging.Logger;

import br.com.seta.processo.dominios.TipoPessoa;
import br.com.seta.processo.dto.CadastroFornecedor;
import br.com.seta.processo.dto.EstadosCadastroFornecedores;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.HistoricoFornecedor;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.utils.PropertiesEJBUtils;

@MessageDriven(activationConfig = { @ActivationConfigProperty(
		propertyName = "destinationType", propertyValue = "javax.jms.Queue"), @ActivationConfigProperty (
				propertyName = "destination", propertyValue = "java:/queue/fornecedores"),
				@ActivationConfigProperty(propertyName = "transactionTimeout", propertyValue="3600"),
				@ActivationConfigProperty(propertyName = "maxMessages", propertyValue = "15"),
				@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "75") 
}, 
mappedName = "java:/queue/fornecedores", 
name = "Fornecedores")
public class FornecedoresProvider implements MessageListener {

	@Inject	private Logger logger;
	@Inject	private ExecuteRestAPI executeRestAPI;
	
	private String bpmUser;
	private String bpmPassword;

	public FornecedoresProvider() {
	}

	@PostConstruct
	public void init() {
		logger.info("Acessando MD  - Formulários ");
		this.bpmUser = PropertiesEJBUtils.getInstance().getValues("bpmUser");
		this.bpmPassword = PropertiesEJBUtils.getInstance().getValues("bpmPassword");
	}


	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void onMessage(Message message) {
		
		FormularioFornecedor formularioFornecedor = null;
		
		try {		
			ObjectMessage objMsg = (ObjectMessage)message;
			formularioFornecedor = (FormularioFornecedor) objMsg.getObject();
			adicionaItemAoHistorico(formularioFornecedor, "Usuário Web", "", "Enviado Pelo Portal", SOLICITANTE_PRE_CADASTRO);
			CadastroFornecedor cadastroFornecedor = new CadastroFornecedor();

			if (formularioFornecedor.getTipoPessoa().equals(TipoPessoa.JURIDICA)) {
				if(formularioFornecedor.getRazaoSocialReduzido()!=null) {
					cadastroFornecedor.setNomeRespPreench(formularioFornecedor.getRazaoSocialReduzido());
				}else {
					cadastroFornecedor.setNomeRespPreench(formularioFornecedor.getRazaoSocialFornedor());
				}
			}else {
				if(formularioFornecedor.getNomeReduzido()!=null) {
					cadastroFornecedor.setNomeRespPreench(formularioFornecedor.getNomeReduzido());
				}else {
					cadastroFornecedor.setNomeRespPreench(formularioFornecedor.getNome());
				}	   
			}
			
			cadastroFornecedor.setComentarioCadastro("Enviado pelo Portal Web");
			cadastroFornecedor.setDescricao("Pré-cadastro de Inclusão de Fornecedor");
			cadastroFornecedor.setEmailSolicitante(formularioFornecedor.getEmailContato());
			String telefoneComercial = formularioFornecedor.getDdFoneComercial() == null ? "" : formularioFornecedor.getDdFoneComercial() + " ";
			telefoneComercial = telefoneComercial + (formularioFornecedor.getFoneComercial() == null ? "" : formularioFornecedor.getFoneComercial());
			cadastroFornecedor.setTelefoneSolicitante(telefoneComercial);
			cadastroFornecedor.setArea("Portal");
			cadastroFornecedor.setNomeSolicitante("Inclusão de Fornecedor");
			cadastroFornecedor.setDataSolicitacao(new java.util.Date());
			cadastroFornecedor.setIdUsuario(formularioFornecedor.getIdUsuario());
			formularioFornecedor.setIdUsuario(formularioFornecedor.getIdUsuario());

			Map<String, Object> listVariablesSerializable = new HashMap<String, Object>(); 
			listVariablesSerializable.put(FORMULARIO_FORNECEDOR, formularioFornecedor);
			listVariablesSerializable.put(CADASTRO_FORNECEDOR, cadastroFornecedor);
			listVariablesSerializable.put(STATUS_CADASTRO_FORNECEDOR, EstadosCadastroFornecedores.EM_PRE_ANALISE);
			logger.info(" Fazendo chamada ao cadastro de fornecedor Usuário ["+this.bpmUser+"]  Password  [ "+this.bpmPassword+ " ]");
			executeRestAPI.execueteCaseWithVariable(this.bpmUser, this.bpmPassword, listVariablesSerializable, CADASTRO_FORNECEDORES, VERSAO_CADASTRO_FORNECEDOR);
			logger.info("Instância criada com sucesso do processo de Cadastro de Fornecedor criada com sucesso");
		} catch (Exception e) {
			logger.error("Ocorreu um erro no momento de iniciar uma nova instância do processo de Cadastro de Fornecedor", e);
			logger.error(formularioFornecedor);
		} 
	}


	private void adicionaItemAoHistorico(FormularioFornecedor formularioFornecedor, String nome, String motivo, String comentario, String status){
		HistoricoFornecedor historicoFornecedor = new HistoricoFornecedor();
		historicoFornecedor.setNome(nome);
		historicoFornecedor.setMotivo(motivo);
		historicoFornecedor.setComentario(comentario);
		historicoFornecedor.setStatus(status);
		historicoFornecedor.setData(new Date());
		if (formularioFornecedor.getHistoricoFornecedores()!=null) {
			formularioFornecedor.getHistoricoFornecedores().clear();
		}
		formularioFornecedor.getHistoricoFornecedores().add(historicoFornecedor);
	}


	@PreDestroy
	public void destroy() {
		logger.info("Destruindo MD  - Formulários");
	}

}
