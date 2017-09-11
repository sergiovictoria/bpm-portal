package br.com.seta.processo.service.interfaces;

import java.io.File;
import java.util.Date;

import javax.ejb.Local;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.report.TypeReportEnum;


@Local
public interface RelatorioService {

	public abstract File reportAtividadesEmAberto(TypeReportEnum tipo, String path, Long caseId, Date dataDe, Date dataAte, Actor ator, User idUsuario, String processo, String estado);
	
	public abstract File reportTempoAtendimento(TypeReportEnum tipo, String path, Long caseId, Date dataDe, Date dataAte, User usuario, String processo);
	
}
