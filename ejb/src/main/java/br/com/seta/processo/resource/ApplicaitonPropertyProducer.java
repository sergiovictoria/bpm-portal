package br.com.seta.processo.resource;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

import br.com.seta.processo.bean.PropertyFileResolver;
import br.com.seta.processo.qualifier.ApplicationProperty;

public class ApplicaitonPropertyProducer {

    @Inject
    private PropertyFileResolver fileResolver;

    @Produces
    @ApplicationProperty(name = "")
    public String getPropertyAsString(InjectionPoint injectionPoint) {
        String propertyName = injectionPoint.getAnnotated().getAnnotation(ApplicationProperty.class).name();
        String value = fileResolver.getProperty(propertyName);
        if (value == null || propertyName.trim().length() == 0) {
            throw new IllegalArgumentException("Propriedade não econtrada" + value);
        }
        return value;
    }

    @Produces
    @ApplicationProperty(name="")
    public Integer getPropertyAsInteger(InjectionPoint injectionPoint) {
        String value = getPropertyAsString(injectionPoint);
        return value == null ? null : Integer.valueOf(value);
    }
}