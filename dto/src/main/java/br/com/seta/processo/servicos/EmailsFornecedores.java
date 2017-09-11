package br.com.seta.processo.servicos;


import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_AGUARDANDO_APROVACAO;
import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_APROVADO;
import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_ENCODE;
import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_REJEICAO;
import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_AGUARDANDO_CORRECAO;
import static br.com.seta.processo.constant.ConstantesFornecedores._NOTIFICACAO_AGUARDANDO_AVALIAR;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.HistoricoFornecedor;
import br.com.seta.processo.utils.FormatUtils;
import br.com.seta.processo.utils.PropertiesUtils;



public final class EmailsFornecedores { 


	private static final  String _TEMPLETES_FORNECEDOR;
	private static EmailsFornecedores emailsFornecedores;

	private static Logger logger = Logger.getLogger(EmailsFornecedores.class);	

	static {
		_TEMPLETES_FORNECEDOR = PropertiesUtils.getInstance().getValues("templates_fornecedor");
	}
		
	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsFornecedores build (){
		if (emailsFornecedores == null){
			logger.info("  **** Criando Serviço Singleton Email de Fornecedores *** ");
			emailsFornecedores = new EmailsFornecedores();
		}
		return emailsFornecedores;
	}
	
	
	public String NotificacaoCadastroFornecedorRejeicao(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		List<HistoricoFornecedor> historicoFornecedors = formularioFornecedor.getHistoricoFornecedores();
		Collections.sort(historicoFornecedors, Collections.reverseOrder(HistoricoFornecedor.Comparators.DATA));
		String consideracao = (String) historicoFornecedors.get(0).getComentario();
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_REJEICAO,_NOTIFICACAO_ENCODE);
		context.put("formularioFornecedor",formularioFornecedor);
		context.put("consideracao",consideracao);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}

	
	public String NotificacaoCadastroFornecedorAprovado(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_APROVADO,_NOTIFICACAO_ENCODE);
		context.put("formularioFornecedor",formularioFornecedor);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	
	public String NotificacaoCadastroAguardandoAprovacao(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_AGUARDANDO_APROVACAO,_NOTIFICACAO_ENCODE);
		context.put("formularioFornecedor",formularioFornecedor);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	
	
	public String NotificacaoCadastroAguardandoCorrecao(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_AGUARDANDO_CORRECAO,_NOTIFICACAO_ENCODE);
		context.put("formularioFornecedor",formularioFornecedor);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	
	
	public String NotificacaoCadastroAguardandoAvaliar(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_AGUARDANDO_AVALIAR,_NOTIFICACAO_ENCODE);
		context.put("formularioFornecedor",formularioFornecedor);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	


}
