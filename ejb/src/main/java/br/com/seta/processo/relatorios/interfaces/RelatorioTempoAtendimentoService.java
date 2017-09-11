package br.com.seta.processo.relatorios.interfaces;

import java.util.Date;
import java.util.List;

import br.com.seta.processo.relatorios.DadosTempoAtendimento;

public interface RelatorioTempoAtendimentoService {

	public abstract List<DadosTempoAtendimento> lista(Long caseId,
			String nomeProcesso, Date inicio, Date fim,
			Long idUsuarioIniciador, Integer quantRegistros, Integer pagina);

}