package br.com.seta.processo.context;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import br.com.seta.processo.utils.PropertiesUtils;

public class JndiContext {
	
	private static Logger logger = Logger.getLogger(JndiContext.class);
	
	private String  hostNameEjb; 
	private Integer portNameEjb;
	
	
	public JndiContext() {
		try {
			hostNameEjb  = PropertiesUtils.getInstance().getValues("hostNameEjb");
			portNameEjb  = Integer.parseInt(PropertiesUtils.getInstance().getValues("portNameEjb"));
			logger.info(" Serviço configurado para acessar ["+hostNameEjb+ "] [ "+portNameEjb+ " ]");
		}catch (Exception e) {
			hostNameEjb  = "127.0.0.1";
			portNameEjb  = 4447;
			logger.info(" Serviço configurado para acessar ["+hostNameEjb+ "] [ "+portNameEjb+ " ]");
		}
     }
	
	
	public Context getContext( )  {
		try {
			Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL,"remote://"+hostNameEjb+":"+portNameEjb);
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "admin123.");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put("jboss.naming.client.ejb.context",true);
			jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED",false);
			jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",false);
			jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",false);
			jndiProperties.put("org.jboss.ejb.client.scoped.context",true);
			logger.info("Lookup feito com EJB ["+hostNameEjb+ "] [ "+portNameEjb+ " ]");
			return new InitialContext(jndiProperties);
		} catch (Exception e) {
			logger.error("Erro ao criar conexão com EJB  ["+hostNameEjb+ "]  [ "+portNameEjb+ " ]",e);
			throw new RuntimeException(e);
		}
	}

	

}