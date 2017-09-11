package br.com.seta.processo.service.implement;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.relatorios.DadosTempoAtendimento;
import br.com.seta.processo.relatorios.ProcessoEmAberto;
import br.com.seta.processo.relatorios.interfaces.RelatorioProcessosEmAberto;
import br.com.seta.processo.relatorios.interfaces.RelatorioTempoAtendimentoService;
import br.com.seta.processo.report.ReportModel;
import br.com.seta.processo.report.ReportUtil;
import br.com.seta.processo.report.TypeReportEnum;
import br.com.seta.processo.service.interfaces.RelatorioService;
import br.com.seta.processo.utils.WrapperUtil;
import br.com.seta.processo.utils.WrapperUtil.FormatacaoTipo;


@Stateless(name="RelatorioService")
@Local(RelatorioService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})

public class RelatorioServiceImpl implements RelatorioService {
	
	public static final String PATH_REPORT = "/WEB-INF/report/";
	public static final String PATH_LOGO = "/resources/assets/images/seta/LogoSeta.png";
	public static final String PDF = ".pdf";
	public static final String XLS = ".xls";

	public static final String RELATORIO_ATIVIDADES_EM_ABERTO = "/atividades-em-aberto_v1";
	private static final String RELATORIO_ATIVIDADES_EM_ABERTO_JASPER_PDF = "atividades-em-aberto_v1.jasper";
	
	public static final String RELATORIO_TEMPO_DE_ATENDIMENTO = "/tempo-de-atendimento_v1";
	private static final String RELATORIO_TEMPO_DE_ATENDIMENTO_JASPER_PDF = "tempo-de-atendimento_v1.jasper";
	
	@Inject private RelatorioProcessosEmAberto relatorioProcessoEmAbertoBean;
	@Inject private RelatorioTempoAtendimentoService relatorioTempoAtendimentoBean;
	
	@Override
	public File reportAtividadesEmAberto(TypeReportEnum tipo, String path, Long caseId, Date dataDe,
			Date dataAte, Actor ator, User usuario, String processo,
			String estado) {
		
		HashMap<String, Object> reportModel = new HashMap<String, Object>();
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		String nomeAtor = ator != null ? ator.getDisplayName() : null;
		Long idUsuario = null;
		String nomeUsuario = null;
		if(usuario != null){
			idUsuario = usuario.getId();
			nomeUsuario = usuario.getFirstname() + " " + usuario.getLastname();
		}
		
		String fileName  = RELATORIO_ATIVIDADES_EM_ABERTO + WrapperUtil.format(FormatacaoTipo.DATA, new java.util.Date());
		
		parametros.put("REPORT_JASPER", path + PATH_REPORT + RELATORIO_ATIVIDADES_EM_ABERTO_JASPER_PDF);
		parametros.put("SUBREPORT_DIR", path + PATH_REPORT);
		parametros.put("LOGOTIPO", ReportUtil.getLogo(path + PATH_LOGO));
		parametros.put("LOGOTIPO", ReportUtil.getLogo(path + PATH_LOGO));
		parametros.put("REPORT_NAME", fileName);
		
		//CAMPOS DO RELATORIO
		reportModel.put("caseIdFiltro", caseId);
		reportModel.put("estadoFiltro", estado);
		reportModel.put("processoFiltro", processo);
		reportModel.put("usuarioFiltro", nomeUsuario);
		reportModel.put("laneFiltro", nomeAtor);
		reportModel.put("dataInicialFiltro", WrapperUtil.format(FormatacaoTipo.DATA, dataDe));
		reportModel.put("dataFinalFiltro", WrapperUtil.format(FormatacaoTipo.DATA, dataAte));
		reportModel.put("dataHoraImpressao", WrapperUtil.format(FormatacaoTipo.DATA_HORA, new Date()));
		
		//SUB REPORT
		List<ReportModel> atividades = new ArrayList<ReportModel>();
		
		List<ProcessoEmAberto> result = relatorioProcessoEmAbertoBean.busca(caseId, 
															processo, 
															estado, 
															nomeAtor, 
															idUsuario, 
															dataDe, 
															dataAte);
		
		if(tipo.equals(TypeReportEnum.XLS)){
			ReportModel row = new ReportModel(tipo);
			row.getModel().put("caseId", "Case ID");
			row.getModel().put("processo", "Processo");
			row.getModel().put("idAtividade", "Atividade ID");
			row.getModel().put("atividade", "Atividade");
			row.getModel().put("status", "Status");
			row.getModel().put("dataInstancia", "Data da Instâcia");
			row.getModel().put("dataInicioAtividade", "Data de Inicio");
			row.getModel().put("dataFimAtividade", "Data Fim");
			row.getModel().put("duracaoAtividade", "Duração");
			row.getModel().put("lane", "Grupo");
			row.getModel().put("usuario", "Usuário");
			
			parametros.put("REPORT_NAME", RELATORIO_ATIVIDADES_EM_ABERTO.replace("/", ""));
			
			atividades.add(row);
			
			parametros.put("REPORT_NAME", RELATORIO_ATIVIDADES_EM_ABERTO.replace("/", ""));
		}
		
		for(ProcessoEmAberto proc : result) {
			ReportModel row = new ReportModel(tipo);
			row.getModel().put("caseId", proc.getCaseId());
			row.getModel().put("processo", proc.getNomeProcesso());
			row.getModel().put("idAtividade", proc.getIdAtividade());
			row.getModel().put("atividade", proc.getNomeAtividade());
			row.getModel().put("status", proc.getEstadoAtividade());
			row.getModel().put("dataInstancia", WrapperUtil.format(FormatacaoTipo.DATA_HORA, proc.getInicioInstancia()));
			row.getModel().put("dataInicioAtividade", WrapperUtil.format(FormatacaoTipo.DATA_HORA, proc.getInicioAtividade()));
			row.getModel().put("dataFimAtividade", WrapperUtil.format(FormatacaoTipo.DATA_HORA, proc.getFimAtividade()));
			row.getModel().put("duracaoAtividade", proc.getDuracao());
			row.getModel().put("lane", proc.getAtor());
			row.getModel().put("usuario", proc.getExecutadaPor());
			
			atividades.add(row);
		}
		
		parametros.put("cabecalho", reportModel);
		
		File file = null;
		
		try {
			file = ReportUtil.export(atividades, parametros, tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}

	
	
	@Override
	public File reportTempoAtendimento(TypeReportEnum tipo, String path, Long caseId, Date dataDe, Date dataAte, User usuario, String processo) {
		HashMap<String, Object> reportModel = new HashMap<String, Object>();
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		Long idUsuario = null;
		String nomeUsuario = null;
		
		if(usuario != null){
			idUsuario = usuario.getId();
			nomeUsuario = usuario.getFirstname() + " " + usuario.getLastname();
		}
		
		String fileName  = RELATORIO_TEMPO_DE_ATENDIMENTO + WrapperUtil.format(FormatacaoTipo.DATA, new java.util.Date());
		
		parametros.put("REPORT_JASPER", path + PATH_REPORT + RELATORIO_TEMPO_DE_ATENDIMENTO_JASPER_PDF);
		parametros.put("SUBREPORT_DIR", path + PATH_REPORT);
		parametros.put("LOGOTIPO", ReportUtil.getLogo(path + PATH_LOGO));
		parametros.put("LOGOTIPO", ReportUtil.getLogo(path + PATH_LOGO));
		parametros.put("REPORT_NAME", fileName);
		
		//CAMPOS DO RELATORIO
		reportModel.put("caseIdFiltro", caseId);
		reportModel.put("processoFiltro", processo);
		reportModel.put("usuarioFiltro", nomeUsuario);
		reportModel.put("dataInicialFiltro", WrapperUtil.format(FormatacaoTipo.DATA, dataDe));
		reportModel.put("dataFinalFiltro", WrapperUtil.format(FormatacaoTipo.DATA, dataAte));
		
		//SUB REPORT
		List<ReportModel> atividades = new ArrayList<ReportModel>();
		
		List<DadosTempoAtendimento> result = relatorioTempoAtendimentoBean.lista(caseId, 
															processo, 
															dataDe, 
															dataAte,
															idUsuario, 
															null, 
															null);
		
		//CRIA O HEADER CASO FOR GERAR XLS
		if(tipo.equals(TypeReportEnum.XLS)){
			ReportModel row = new ReportModel(tipo);
			
			row.getModel().put("caseId", "Case ID");
			row.getModel().put("processo", "Processo");
			row.getModel().put("dataHoraCriacaoInstancia", "Criação Instância");
			row.getModel().put("ultimoStatus", "Último Status");
			row.getModel().put("usuarioIniciador", "Usuário Iniciador");
			row.getModel().put("duracao", "Duração");
			
			atividades.add(row);
			
			parametros.put("REPORT_NAME", RELATORIO_TEMPO_DE_ATENDIMENTO.replace("/", ""));
		}
		
		for(DadosTempoAtendimento proc : result) {
			ReportModel row = new ReportModel(tipo);
			row.getModel().put("caseId", proc.getCaseId());
			row.getModel().put("processo", proc.getProcesso());
			row.getModel().put("dataHoraCriacaoInstancia", WrapperUtil.format(FormatacaoTipo.DATA_HORA, proc.getCriacaoInstancia()));
			row.getModel().put("ultimoStatus", WrapperUtil.format(FormatacaoTipo.DATA_HORA, proc.getFimInstancia()));
			row.getModel().put("usuarioIniciador", proc.getUsuarioaIniciador());
			row.getModel().put("duracao", proc.getDuracao());
			
			atividades.add(row);
		}
		
		parametros.put("cabecalho", reportModel);
		
		File file = null;
		
		try {
			file = ReportUtil.export(atividades, parametros, tipo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return file;
	}

    
}
