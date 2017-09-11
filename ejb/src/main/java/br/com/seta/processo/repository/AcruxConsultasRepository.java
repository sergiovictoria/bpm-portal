package br.com.seta.processo.repository;

import java.util.List;

import javax.ejb.Local;

import br.com.seta.processo.dto.MapNcmDto;

@Local
public interface AcruxConsultasRepository {
	public abstract List<String> findEmbalagem();

	public abstract List<String> findOrigemProduto();

	public abstract List<MapNcmDto> findNcm(long first, long count, String ncm);

	public abstract Long sizeNcm(String ncm);
}
