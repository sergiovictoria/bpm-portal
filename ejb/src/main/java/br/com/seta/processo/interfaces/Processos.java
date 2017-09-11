package br.com.seta.processo.interfaces;

import java.util.List;

import br.com.seta.processo.dto.Suprimento;



public interface Processos {
   public abstract void save(Object object);
   public abstract void save(List<Object> objects);
   public abstract void save(Object object, Class<?> type);
   public abstract List<Suprimento> getSuprimentos(java.util.Date dataInicio, java.util.Date dataFinal);
}
