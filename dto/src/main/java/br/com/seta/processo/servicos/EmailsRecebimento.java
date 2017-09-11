package br.com.seta.processo.servicos;

import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_APROVADO_DIVERGENTE;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_CHEKIN_FILE_VM;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_DIVERGENCIA_TOTAL;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_ENCODE;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_INTEGRADA_PAGTO;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_REJEICAO_FILE_VM;
import static br.com.seta.processo.constant.ConstantesRecebimento._NOTIFICACAO_RECEBIMENTO_CANCELADA;


import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.utils.FormatUtils;
import br.com.seta.processo.utils.PropertiesUtils;



public final class EmailsRecebimento { 


	private static final  String _RECEBIMENTO_TEMPLATES = "/home/sergio/app/EAP-6.4-Bpm/templates/recebimento";
	private static EmailsRecebimento emailsRecebimento;

	private static Logger logger = Logger.getLogger(EmailsRecebimento.class);	

	
	public void init() {
		logger.info("Acessando EJB - Templetes de Emails");
	}
	
		
	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsRecebimento build (){
		if (emailsRecebimento == null){
			logger.info("  **** Criando Serviço Singleton EmailsRecebimento *** ");
			emailsRecebimento = new EmailsRecebimento();
		}
		return emailsRecebimento;
	}
	
	
	
	public String recebimentoNotificacaoCancelada(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_RECEBIMENTO_CANCELADA,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}


	public String recebimentoNotificacaoRejeicao(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_REJEICAO_FILE_VM,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}


	public String recebimentoNotificacaoCheckin(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CHEKIN_FILE_VM,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}

	public String recebimentoDivergenciaTotal(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_DIVERGENCIA_TOTAL,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}

	public String recebimentoIntegradaPagamento(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_INTEGRADA_PAGTO,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}


	public String recebimentoAprovadoDivergencia(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_APROVADO_DIVERGENTE,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}


	public String recebimentoAprovadoDivergenciaTotal(SolicitacaoIntencaoCompra solicitacaoIntencaoCompra, OrRequisicao orRequisicao) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_RECEBIMENTO_TEMPLATES);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_DIVERGENCIA_TOTAL,_NOTIFICACAO_ENCODE);
		context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
		context.put("orrequisicao",orRequisicao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}

	public static void main(String[] args) throws IOException {
		SolicitacaoIntencaoCompra solicitacaoIntencaoCompra = new SolicitacaoIntencaoCompra();
		OrRequisicao orRequisicao = new OrRequisicao();
		System.out.println(new EmailsRecebimento().build().recebimentoNotificacaoRejeicao(solicitacaoIntencaoCompra, orRequisicao));
	}
}
