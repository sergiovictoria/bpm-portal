package br.com.seta.processo.servicos;

import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_CADASTRO_REJEICAO_VM;
import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_ENCODE;
import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_CADASTRO_FISCAL_VM;
import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_CADASTRO_SUCESSO;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.utils.FormatUtils;
import br.com.seta.processo.utils.PropertiesUtils;



public final class EmailsProdutos { 


	private static final  String _TEMPLETES_PRODUTO;
	private static EmailsProdutos emailsprodutos;

	private static Logger logger = Logger.getLogger(EmailsProdutos.class);	

	static {
		_TEMPLETES_PRODUTO = PropertiesUtils.getInstance().getValues("templates_produto");
	}
		
	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsProdutos build (){
		if (emailsprodutos == null){
			logger.info("  **** Criando Serviço Singleton EmailsProdutos *** ");
			emailsprodutos = new EmailsProdutos();
		}
		return emailsprodutos;
	}
	
	
	public String NotificacaoCadastroProdutoRejeicao(br.com.seta.processo.dto.FormularioProduto formularioProduto) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_PRODUTO);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CADASTRO_REJEICAO_VM,_NOTIFICACAO_ENCODE);
		context.put("formularioProduto",formularioProduto);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	
	
	public String NotificacaoCadastroProdutoFiscal(br.com.seta.processo.dto.FormularioProduto formularioProduto) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_PRODUTO);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CADASTRO_FISCAL_VM,_NOTIFICACAO_ENCODE);
		context.put("formularioProduto",formularioProduto);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}
	
	
	public String NotificacaoCadastroProdutoSucesso(br.com.seta.processo.dto.FormularioProduto formularioProduto) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_PRODUTO);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CADASTRO_SUCESSO,_NOTIFICACAO_ENCODE);
		context.put("formularioProduto",formularioProduto);
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}



	public static void main(String[] args) throws IOException {
		FormularioFornecedor formularioFornecedor = new FormularioFornecedor(); 
		formularioFornecedor.setCaseId(17);
		FormularioProduto formularioProduto = new FormularioProduto();
		formularioProduto.setCaseId("13");
		System.out.println(new EmailsProdutos().build().NotificacaoCadastroProdutoRejeicao(formularioProduto));
	}
	
}
