package br.com.seta.processo.service.implement;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.seta.processo.dto.MapNcmDto;
import br.com.seta.processo.repository.AcruxConsultasRepository;
import br.com.seta.processo.service.interfaces.AcruxConsultasService;

@Stateless
@Local(AcruxConsultasService.class)
public class AcruxConsultasServiceImpl implements AcruxConsultasService {

    @Inject private AcruxConsultasRepository repository;

	@Override
	public List<String> findEmbalagem() {
		return repository.findEmbalagem();
	}
	
	@Override
	public List<String> findOrigemProduto() {
		return repository.findOrigemProduto();
	}

	@Override
	public List<MapNcmDto> findNcm(long first, long count, String ncm) {
		return repository.findNcm(first, count, ncm);
	}

	@Override
	public Long sizeNcm(String ncm) {
		return repository.sizeNcm(ncm);
	}

}
