package br.com.seta.processo.provider;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.hornetq.api.core.TransportConfiguration;
import org.hornetq.api.jms.HornetQJMSClient;
import org.hornetq.api.jms.JMSFactoryType;
import org.hornetq.core.remoting.impl.netty.NettyConnectorFactory;
import org.hornetq.core.remoting.impl.netty.TransportConstants;
import org.jboss.logging.Logger;

import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.utils.PropertiesEJBUtils;


@Stateless(name="EnviaFornecedor")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean
public class EnviaFornecedor {

	@Inject
	private Logger logger;
	
	private String hostName;
	private String portName;
	private String hostUser;
	private String hostPassword;
	
	private Queue queue = HornetQJMSClient.createQueue("Consincos");

	@PostConstruct
	public void init() {
		
		logger.info(" ============ Enviando Mensagem ao Portal Fornecedor ============");
		this.hostName = PropertiesEJBUtils.getInstance().getValues("hostNameHornet");
		this.portName = PropertiesEJBUtils.getInstance().getValues("portNameHornet");
		this.hostUser = PropertiesEJBUtils.getInstance().getValues("hornetUserDestinnation");
		this.hostPassword = PropertiesEJBUtils.getInstance().getValues("hornetPasswordDestination");
		
	}

	private Map<String, Object> connectionParams = new HashMap<String, Object>();
	
	public void env(Map<String, String> map) {
		
		connectionParams.put(TransportConstants.PORT_PROP_NAME, this.portName);
		connectionParams.put(TransportConstants.HOST_PROP_NAME, this.hostName);
		
		TransportConfiguration transportConfiguration = new TransportConfiguration(NettyConnectorFactory.class.getName(), connectionParams);
		ConnectionFactory factory = (ConnectionFactory) HornetQJMSClient.createConnectionFactoryWithoutHA(JMSFactoryType.CF, transportConfiguration);
		
		Connection connection;

		try {
			
			connection = factory.createConnection(this.hostUser, this.hostPassword);
			Session session = connection.createSession(false,QueueSession.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			ObjectMessage objectMessage = session.createObjectMessage((Serializable)map);
			messageProducer.send(objectMessage);
			connection.close();
			session.close();
			logger.info(" ============ Mensagem Enviada ao Portal Fornecedor ============");
			
		}catch (Exception e ) {
			e.printStackTrace();
			logger.info(" ============ Erro ao Enviar Mensagem ao Portal Fornecedor ============");
		}
		
	}


	@PreDestroy
	public void destroy() {
		logger.info(" Destruindo Envio de Mensagens ");
	}


}
