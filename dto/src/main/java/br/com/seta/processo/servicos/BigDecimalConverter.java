package br.com.seta.processo.servicos;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;
import org.mongodb.morphia.mapping.MappingException;

import com.mongodb.DBObject;

public class BigDecimalConverter extends TypeConverter implements SimpleValueConverter {
    
    public BigDecimalConverter() {
        super(BigDecimal.class);
    }
 
    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        BigDecimal bigDecimalValue = (BigDecimal) value;
        
        return bigDecimalValue.doubleValue();        
    }
 
    @Override
    @SuppressWarnings("rawtypes")
    public Object decode(Class targetClass, Object fromDBObject, MappedField field) throws MappingException {
    	if(targetClass.equals(BigDecimal.class)){        	
        	return new BigDecimal(fromDBObject.toString());
        }
    	
    	DBObject dbo = (DBObject) fromDBObject;
        if (dbo == null) {
            return null;
        }
 
        BigDecimal bigDecimal = null;
 
        Long unscaled = (Long) dbo.get("unscaled");
        Integer scale = (Integer) dbo.get("scale");
 
        if (unscaled != null && scale != null) {
            bigDecimal = new BigDecimal(new BigInteger(unscaled.toString()), scale);
        }
 
        return bigDecimal;
    }
 
}