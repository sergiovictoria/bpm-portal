package br.com.seta.processo.bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.interceptor.LogInterceptor;

@Singleton(name="PropertyFileResolver")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
public class PropertyFileResolver {

	@Inject
	private Logger logger;

    private Map<Object, Object> properties = new HashMap<>();

    @PostConstruct
    private void init() throws IOException {

        String propertyFile = System.getProperty("application.properties");
        File file = new File(propertyFile);
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            logger.error("Arquivo de propriedades não econtrado ", e);
        }

        HashMap<Object, Object> hashMap = new HashMap<>(properties);
        this.properties.putAll(hashMap);
    }

    public String getProperty(String key) {
        return (String) properties.get(key);
    }
    
    
}