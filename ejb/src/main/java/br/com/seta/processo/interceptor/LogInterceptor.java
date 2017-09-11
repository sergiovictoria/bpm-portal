package br.com.seta.processo.interceptor;

import java.io.StringWriter;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.jboss.logging.Logger;

import br.com.seta.processo.exceptions.TransactionException;
import br.com.seta.processo.qualifier.Log;


@Interceptor
@Log
public class LogInterceptor {

	@Inject
	private Logger logger;



	@AroundInvoke
	public Object operation(InvocationContext context) throws Exception {
		Object object = null;
		logger.info("Executando "+context.getMethod().getName());
		try {
			object = context.proceed();
		}catch (Exception e) {
			throw new TransactionException("Ocorreu um erro na transação ",e);
		}
		logger.info("Executado  "+context.getMethod().getName());
		return object;
	}


	public String printObjectXml(Object object) {
		try { 
			JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());  
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(object, sw);
			return sw.toString();
		}catch (Exception  e) {
			e.printStackTrace();    
		}
		return null;
	}


}
