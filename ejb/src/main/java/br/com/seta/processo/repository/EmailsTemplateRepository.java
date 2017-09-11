//package br.com.seta.processo.repository;
//
//
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_APROVADO_DIVERGENTE;
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_CHEKIN_FILE_VM;
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_DIVERGENCIA_TOTAL;
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_ENCODE;
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_INTEGRADA_PAGTO;
//import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_REJEICAO_FILE_VM;
//
//import java.io.IOException;
//import java.io.StringWriter;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
//import javax.ejb.Remote;
//import javax.ejb.Stateless;
//import javax.ejb.TransactionManagement;
//import javax.ejb.TransactionManagementType;
//import javax.inject.Inject;
//import javax.interceptor.Interceptors;
//
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;
//import org.jboss.logging.Logger;
//
//import br.com.seta.processo.dto.OrRequisicao;
//import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
//import br.com.seta.processo.interceptor.LogInterceptor;
//import br.com.seta.processo.interfaces.EmailTemplatesRemote;
//import br.com.seta.processo.utils.FormatUtils;
//import br.com.seta.processo.utils.PropertiesEJBUtils;
//
//@TransactionManagement(TransactionManagementType.CONTAINER)
//@Remote(EmailTemplatesRemote.class)
//@Interceptors({LogInterceptor.class})
//@Stateless(name="EmailsTemplateRepository")
//
//public class EmailsTemplateRepository implements EmailTemplatesRemote {
//	
//	private static final String _RECEBIMENTO_TEMPLATES =  PropertiesEJBUtils.getInstance().getValues("RECEBIMENTO_TEMPLATES");
//
//	@Inject	private Logger logger;
//	
//	@PostConstruct
//	public void init() {
//		logger.info("Acessando EJB - Templetes de Emails");
//	}
//
//	@Override
//	public String build(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra,OrRequisicao orRequisicao, String email) {
//		
//		String mails = "";	
//		if (email.equals("recebimentoIntegradaPagamento")) {
//			try {
//				mails = recebimentoIntegradaPagamento(solicitacaoIntencaoCompra,orRequisicao);
//			}catch (Exception e ) {
//			}
//		}
//		return mails;
//	}
//
//	
//	private String recebimentoNotificacaoRejeicao(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_REJEICAO_FILE_VM,_NOTIFICACAO_ENCODE);
//		context.put("idrequisicao",solicitacaoIntencaoCompra.getNumeroIntencaoSolicitacaoCompra());
//		context.put("nomefornecedor",orRequisicao.getNomeFornecedor());
//		context.put("historicos",solicitacaoIntencaoCompra.getHistorico());
//		context.put("orreqitensdespesas",orRequisicao.getOrReqitensdespesas());
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//
//	private String recebimentoNotificacaoCheckin(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CHEKIN_FILE_VM,_NOTIFICACAO_ENCODE);
//		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//	private String recebimentoDivergenciaTotal(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_DIVERGENCIA_TOTAL,_NOTIFICACAO_ENCODE);
//		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//	private String recebimentoIntegradaPagamento(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_INTEGRADA_PAGTO,_NOTIFICACAO_ENCODE);
//		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//
//	private String recebimentoAprovadoDivergencia(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_APROVADO_DIVERGENTE,_NOTIFICACAO_ENCODE);
//		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//
//	private String recebimentoAprovadoDivergenciaTotal(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
//		VelocityEngine velocityEngine  = new VelocityEngine();
//		VelocityContext context = new VelocityContext();
//		velocityEngine.setProperty("resource.loader", "file");
//		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
//		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_DIVERGENCIA_TOTAL,_NOTIFICACAO_ENCODE);
//		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
//		context.put("orrequisicao",orRequisicao);
//		context.put("formatUtils",FormatUtils.class);
//		StringWriter writer = new StringWriter();
//		template.merge( context, writer );
//		return writer.toString();
//	}
//
//	@PreDestroy
//	public void destroy() {
//		logger.info("Destruindo EJB - Templetes de Emails");
//	}
//
//
//
//	
//}
