package br.com.seta.processo.service.interfaces;

import java.util.List;

import javax.ejb.Local;

import br.com.seta.processo.dto.MapFamilia;

@Local
public interface MapFamiliaService {

	public abstract boolean save(MapFamilia dto);
}
