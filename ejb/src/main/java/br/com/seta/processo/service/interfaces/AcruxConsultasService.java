package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.seta.processo.dto.MapNcmDto;

@Local
public interface AcruxConsultasService {
     public abstract List<String> findEmbalagem();
     
     public abstract List<String> findOrigemProduto();
     
     public abstract List<MapNcmDto> findNcm(long first, long count, String ncm);
     
     public abstract Long sizeNcm(String ncm);
}
