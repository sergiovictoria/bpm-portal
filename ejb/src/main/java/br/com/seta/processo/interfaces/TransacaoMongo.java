package br.com.seta.processo.interfaces;

import java.util.Collection;
import java.util.Map;



public interface TransacaoMongo {
   public abstract void save(Object object, Class<?> type);
   public abstract void save(Collection<? extends Object> objects, Class<?> type);
   public abstract Collection<? extends Object> find(String key, Object value, Class<?> type);
   public abstract Object findOne(String key, Object value, Class<?> type);
   public abstract Object find(Class<?> type);
   public abstract void update(String key, Object value, Object object,  Class<?> type);
   public abstract void updateWithTMap(String key, Object value, Map<String,Object> opsMap,  Class<?> type);
   public abstract void updateWithTwoAttributes(String key, Object value, String keyOps, Object valueOps, Class<?> type);
   public abstract void remove(String key, Object value, Class<?> type);
}
