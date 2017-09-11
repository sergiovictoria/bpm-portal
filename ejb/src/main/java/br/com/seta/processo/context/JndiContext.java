package br.com.seta.processo.context;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.jboss.logging.Logger;

import br.com.seta.processo.utils.PropertiesEJBUtils;


public class JndiContext {

	private static final Logger logger = Logger.getLogger(JndiContext.class);

	public Context getContext( )  {

		String hostName  = null;
		Integer portName = null;
		
		try {
			hostName = PropertiesEJBUtils.getInstance().getValues("hostNameEJB");
			portName = Integer.parseInt(PropertiesEJBUtils.getInstance().getValues("portNameEJB"));
		}catch (Exception e ) {
			hostName  = "localhost";
			portName = 4447;
		}


		try {
			logger.info("O lookup esta configurado para o Host ["+hostName+" ]"+ " Porta [" +portName +"] ");
			Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL,"remote://"+hostName+":"+portName);
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "ejb");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "admin123.");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put("jboss.naming.client.ejb.context",true);
			jndiProperties.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED",false);
			jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS",false);
			jndiProperties.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT",false);
			jndiProperties.put("org.jboss.ejb.client.scoped.context",true);
			return new InitialContext(jndiProperties);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}



}
