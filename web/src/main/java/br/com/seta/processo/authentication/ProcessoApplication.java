package br.com.seta.processo.authentication;

import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;

import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.core.request.handler.PageProvider;
import org.apache.wicket.core.request.handler.RenderPageRequestHandler;
import org.apache.wicket.core.util.file.WebApplicationPath;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.IRequestHandler;
import org.apache.wicket.request.cycle.AbstractRequestCycleListener;
import org.apache.wicket.request.cycle.RequestCycle;
import org.jboss.logging.Logger;

import br.com.seta.processo.cadastrofornecedores.CadastroFornecedores;
import br.com.seta.processo.cadastrofornecedores.SolicitarCadastroFornecedor;
import br.com.seta.processo.cadastroprodutos.CadastrarProduto;
import br.com.seta.processo.cadastroprodutos.EnviarCadastroConSinco;
import br.com.seta.processo.cadastroprodutos.SolicitaCadastroProduto;
import br.com.seta.processo.cadastroprodutos.VisualizaDadosProduto;
import br.com.seta.processo.consultas.cadastrofornecedores.ConsultaCadastroFornecedor;
import br.com.seta.processo.consultas.produtos.ConsultaProdutos;
import br.com.seta.processo.gestaofilas.DashboardGestaoFilas;
import br.com.seta.processo.gestaofilas.GestaoFilas;
import br.com.seta.processo.orcamento.DadosSolicitante;
import br.com.seta.processo.page.Atividades;
import br.com.seta.processo.page.ExceptionPage;
import br.com.seta.processo.page.ProcessoHome;
import br.com.seta.processo.page.Processos;
import br.com.seta.processo.recebimento.schedule.ScheduleContrato;
import br.com.seta.processo.recebimento.solicitacao.AprovarRecebimentoPedidoDivergente;
import br.com.seta.processo.recebimento.solicitacao.ClassificarImpostos;
import br.com.seta.processo.recebimento.solicitacao.ConsultaRequisicaoCompraProduto;
import br.com.seta.processo.recebimento.solicitacao.ConsultaRequisicaoCompraServico;
import br.com.seta.processo.recebimento.solicitacao.EscanearDocumentacaoRecebimento;
import br.com.seta.processo.recebimento.solicitacao.EscanearDocumentoServico;
import br.com.seta.processo.recebimento.solicitacao.IntegracaoParaPagamento;
import br.com.seta.processo.recebimento.solicitacao.MedirServico;
import br.com.seta.processo.recebimento.solicitacao.SimularGuiaCega;
import br.com.seta.processo.recebimento.solicitacao.SolicitarAutorizacaoRecebimento;
import br.com.seta.processo.recebimento.solicitacao.ValidarPrestacaoServico;
import br.com.seta.processo.recebimento.solicitacao.VerificarStatusDivergencia;
import br.com.seta.processo.relatorios.AtividadesEmAberto;
import br.com.seta.processo.relatorios.DemandasAtividadesGeralIndicadores;
import br.com.seta.processo.relatorios.DemandasAtividadesProcessosIndicadores;
import br.com.seta.processo.relatorios.SlaGeralIndicadores;
import br.com.seta.processo.relatorios.SlaParametros;
import br.com.seta.processo.relatorios.SlaProcessoIndicadores;
import br.com.seta.processo.relatorios.TempoAtendimento;
import br.com.seta.processo.solicitacaopagamento.AprovarSolicitacaoPagamento;
import br.com.seta.processo.solicitacaopagamento.CadastrarSolicitacaoPagamento;
import br.com.seta.processo.solicitacaopagamento.ConsultaSolicitacaoPagamento;
import br.com.seta.processo.solicitacaopagamento.ParametrizarSolicitacaoPagamento;
import br.com.seta.processo.suprimentos.ListaContratos;
import br.com.seta.processo.suprimentos.Recebimentos;
import br.com.seta.processo.suprimentos.paginasSolicitacao.AlterarIntencaoCompra;
import br.com.seta.processo.suprimentos.paginasSolicitacao.AnalisarStatusContrato;
import br.com.seta.processo.suprimentos.paginasSolicitacao.AprovacaoIntencaoCompra;
import br.com.seta.processo.suprimentos.paginasSolicitacao.AprovacaoIntencaoCompraHierarquico;
import br.com.seta.processo.suprimentos.paginasSolicitacao.CadastrarFornecedoresItens;
import br.com.seta.processo.suprimentos.paginasSolicitacao.ExibirResumoIntencao;
import br.com.seta.processo.suprimentos.paginasSolicitacao.PreencherFormularioIntencaoCompra;
import br.com.seta.processo.suprimentos.paginasSolicitacao.SolicitarIntencaoCompra;
import br.com.seta.processo.suprimentos.paginasSolicitacao.ValidarNaturezaDespesa;
import br.com.seta.processo.suprimentos.paginasSolicitacao.VerificarInformacoesParaCadastro;
import br.com.seta.processo.usuario.AlterarEmail;
import br.com.seta.processo.usuario.AlterarSenha;
import br.com.seta.processo.usuario.Perfil;
import br.com.seta.processo.usuario.PerfilUsuario;

public abstract class ProcessoApplication extends WebApplication {

	
	private static Logger logger = Logger.getLogger(ProcessoApplication.class);
	
	public ProcessoApplication() {
		super();
		this.getRequestCycleListeners().add(new AbstractRequestCycleListener() {  
			public IRequestHandler onException(RequestCycle cycle, Exception e) {  
				return new RenderPageRequestHandler(new PageProvider(new ExceptionPage()));
			}  
		});
	}

	
	protected void init() {
		
		logger.info("Iniciando context processos bpm ");
		
		super.init();
		getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "/"));
		getDebugSettings().setAjaxDebugModeEnabled(false);
		getDebugSettings().setDevelopmentUtilitiesEnabled(true);
		getApplicationSettings().setPageExpiredErrorPage(SignIn.class);
				
		this.mountPage("/Entrar/", SignIn.class);
		this.mountPage("/Principal/",ProcessoHome.class);
		this.mountPage("/Atividades", Atividades.class);
		this.mountPage("/Processo/", Processos.class);
		this.mountPage("/Erro/", ExceptionPage.class);
		//this.mountPage("/Teste", TestePage.class);
		
		/* Suprimentos */
		this.mountPage("/DadosSolicitante", DadosSolicitante.class);
		
		/* Cadastrar Fornecedor */
		this.mountPage("/CadastroFornecedores", CadastroFornecedores.class);
		this.mountPage("/SolicitarCadastroFornecedor", SolicitarCadastroFornecedor.class);
		
		/* Solicitar Pagamento */
		this.mountPage("/CadastrarSolicitacaoPagamento", CadastrarSolicitacaoPagamento.class);
		this.mountPage("/AprovarSolicitacaoPagamento", AprovarSolicitacaoPagamento.class);
		this.mountPage("/ParametrizarSolicitacaoPagamento", ParametrizarSolicitacaoPagamento.class);
		this.mountPage("/ConsultaSolicitacaoPagamento", ConsultaSolicitacaoPagamento.class);
		
		/* Cadastro de Produtos */
		this.mountPage("/SolicitaCadastroProduto", SolicitaCadastroProduto.class);
		this.mountPage("/VisualizaDadosProduto", VisualizaDadosProduto.class);
		this.mountPage("/EnviarCadastroConSinco", EnviarCadastroConSinco.class);
		this.mountPage("/CadastrarProduto", CadastrarProduto.class);
		
		/* Suprimentos */
		this.mountPage("/SolicitarIntencaoCompra", SolicitarIntencaoCompra.class);
		this.mountPage("/PreencherFormularioIntencaoCompra", PreencherFormularioIntencaoCompra.class);
		this.mountPage("/AprovacaoIntencaoCompra", AprovacaoIntencaoCompra.class);
		this.mountPage("/AprovacaoIntencaoCompraHierarquico", AprovacaoIntencaoCompraHierarquico.class);
		this.mountPage("/ValidarNaturezaDespesa", ValidarNaturezaDespesa.class);
		this.mountPage("/ExibirResumoIntencao", ExibirResumoIntencao.class);
		this.mountPage("/AlterarIntencaoCompra", AlterarIntencaoCompra.class);
		this.mountPage("/CadastrarFornecedoresItens", CadastrarFornecedoresItens.class);
		this.mountPage("/VerificarInformacoesParaCadastro", VerificarInformacoesParaCadastro.class);
		this.mountPage("/AnalisarStatusContrato", AnalisarStatusContrato.class);		
		
		/*Recebimento*/
		this.mountPage("/ConsultaRequisicaoCompraServico", ConsultaRequisicaoCompraServico.class);
		this.mountPage("/ConsultaRequisicaoCompraProduto", ConsultaRequisicaoCompraProduto.class);
		this.mountPage("/MedirServico", MedirServico.class);
		this.mountPage("/EscanearDocumentoServico", EscanearDocumentoServico.class);
		this.mountPage("/ClassificarImpostos", ClassificarImpostos.class);
		this.mountPage("/IntegracaoParaPagamento", IntegracaoParaPagamento.class);
		this.mountPage("/ValidarPrestacaoServico", ValidarPrestacaoServico.class);
		this.mountPage("/SimularGuiaCega", SimularGuiaCega.class);
		this.mountPage("/SolicitarAutorizacaoRecebimento", SolicitarAutorizacaoRecebimento.class);
		this.mountPage("/AprovarRecebimentoPedidoDivergente", AprovarRecebimentoPedidoDivergente.class);
		this.mountPage("/VerificarStatusDivergencia", VerificarStatusDivergencia.class);
		this.mountPage("/EscanearDocumentacaoRecebimento", EscanearDocumentacaoRecebimento.class);		
		
		/*  Dados Usuário */
		this.mountPage("/Perfil", Perfil.class);
		this.mountPage("/PerfilUsuario", PerfilUsuario.class);
		this.mountPage("/AlterarEmail", AlterarEmail.class);
		this.mountPage("/AlterarSenha", AlterarSenha.class);
		
		/* Relatórios */
		this.mountPage("/Indicadores/SlaGeral", SlaGeralIndicadores.class);
		this.mountPage("/Indicadores/SlaProcesso", SlaProcessoIndicadores.class);
		this.mountPage("/Indicadores/DemandasGerais", DemandasAtividadesGeralIndicadores.class);
		this.mountPage("/Indicadores/DemandasProcessos", DemandasAtividadesProcessosIndicadores.class);
		this.mountPage("/Relatorios/AtividadesEmAberto", AtividadesEmAberto.class);
		this.mountPage("/Parametrizacao/Sla", SlaParametros.class);
		this.mountPage("/Relatorios/TempoDeAtendimento", TempoAtendimento.class);
		
		/*Consultas*/
		this.mountPage("/Consulta/CadastroFornecedor", ConsultaCadastroFornecedor.class);
		this.mountPage("/Consulta/Produtos", ConsultaProdutos.class);
		
		/* Gestão de Filas */
		this.mountPage("/GestaoFilas", GestaoFilas.class);
		this.mountPage("/GestaoFilas/Dashboard", DashboardGestaoFilas.class);
		
		/* Suprimentos - Contratos de Serviços */
		this.mountPage("/ListaContratos", ListaContratos.class);
		
		/* Suprimentos - Inicia Recebimento */
		this.mountPage("/Recebimentos", Recebimentos.class);
		
		/* Recebimento  - Schedule Contrato */
		this.mountPage("/ScheduleContrato", ScheduleContrato.class);
				
		try {
			BeanManager manager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
			new CdiConfiguration(manager).configure(this);
		}catch (Exception e) {
			logger.error("Erro ao cria context cdi processos bpm ",e);
		}
		
	}
	
	
	public static String getContextPath(){
		String rootContext = "";
		try {
			ServletContext servletContext = WebApplication.get().getServletContext();
			if(servletContext!=null){
				rootContext = servletContext.getRealPath("");
			}else{
			}
		} catch (Exception e) {
			logger.error("Erro ao tentar criar context pagina processos bpm ",e);
		}
		return rootContext;
	}
	
	

	

}
