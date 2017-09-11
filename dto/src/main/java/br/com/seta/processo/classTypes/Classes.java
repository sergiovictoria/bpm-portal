package br.com.seta.processo.classTypes;


import java.awt.List;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;


public enum Classes {

	
	STRING (String.class.getName()),
    DATE (Date.class.getName()),
    INTEGER (Integer.class.getName()),
    FLOAT (Float.class.getName()),
    LONG (Long.class.getName()),
	BIGDECIMAL (BigDecimal.class.getName()),
	DOUBLE (Double.class.getName()),
	BIGINTEGER(BigInteger.class.getName()),
	SET(Set.class.getName()),
    LIST(List.class.getName());
	
	
    
    private final String className;
    
    public String className() {
        return this.className;
    }

    
    private Classes(String className) {
        this.className = className;
    }
}
