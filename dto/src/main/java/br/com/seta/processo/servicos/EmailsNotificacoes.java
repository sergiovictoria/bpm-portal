package br.com.seta.processo.servicos;

import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_CADASTRO_FORNECEDOR_VM;
import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_CADASTRO_PRODUTO_VM;
import static br.com.seta.processo.constant.ConstantesNotificacoes._NOTIFICACAO_ENCODE;

import java.io.IOException;
import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.jboss.logging.Logger;

import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.dto.FormularioProduto;
import br.com.seta.processo.dto.TipoPessoa;
import br.com.seta.processo.utils.FormatUtils;
import br.com.seta.processo.utils.PropertiesUtils;



public final class EmailsNotificacoes { 


	private static final  String _TEMPLETES_FORNECEDOR;
	private static final  String _TEMPLETES_PRODUTO;
	private static EmailsNotificacoes emailsNotificacoes;

	private static Logger logger = Logger.getLogger(EmailsNotificacoes.class);	
	
	static {
		_TEMPLETES_FORNECEDOR = PropertiesUtils.getInstance().getValues("templates_fornecedor");
	}
	
	static {
		_TEMPLETES_PRODUTO = PropertiesUtils.getInstance().getValues("templates_produto");
	}
		
	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsNotificacoes build (){
		if (emailsNotificacoes == null){
			logger.info("  **** Criando Serviço Singleton Notificações *** ");
			emailsNotificacoes = new EmailsNotificacoes();
		}
		return emailsNotificacoes;
	}
	
	
	
	public String NotificacaoCadastroFornecedor(br.com.seta.processo.dto.FormularioFornecedor formularioFornecedor) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_FORNECEDOR);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CADASTRO_FORNECEDOR_VM,_NOTIFICACAO_ENCODE);
		if (formularioFornecedor.getTipoPessoa().equals(TipoPessoa.JURIDICA) ){
		    context.put("nomeFornecedor",formularioFornecedor.getRazaoSocialFornedor());
		    context.put("cnpjCpf",formularioFornecedor.getRazaoSocialFornedor());
		}else {
		    context.put("nomeFornecedor",formularioFornecedor.getNome());
		    context.put("cnpjCpf",formularioFornecedor.getCpfFisica());			
		}
		context.put("numeroControle",formularioFornecedor.getCaseId());
		context.put("formatUtils",FormatUtils.class);
		StringWriter writer = new StringWriter();
		template.merge( context, writer );
		return writer.toString();
	}


	public String NotificacaoCadastroProduto(br.com.seta.processo.dto.FormularioProduto formularioProduto) throws IOException {
		VelocityEngine velocityEngine  = new VelocityEngine();
		VelocityContext context = new VelocityContext();
		velocityEngine.setProperty("resource.loader", "file");
		velocityEngine.setProperty( "file.resource.loader.path",_TEMPLETES_PRODUTO);
		Template template  = velocityEngine.getTemplate(_NOTIFICACAO_CADASTRO_PRODUTO_VM,_NOTIFICACAO_ENCODE);
		context.put("descricao",formularioProduto.getDescCompleta());
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
		System.out.println(new EmailsNotificacoes().build().NotificacaoCadastroProduto(formularioProduto));
	}
}
