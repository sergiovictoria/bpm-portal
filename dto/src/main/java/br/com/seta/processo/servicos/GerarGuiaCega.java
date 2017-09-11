package br.com.seta.processo.servicos;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import br.com.seta.processo.utils.VelocityUtils;

public class GerarGuiaCega {
	
	private static final String PATH_GUIA_CEGA_FONTES = PropertiesDtoUtils.getInstance().getValues("template_guia_cega");
	private static final String TEMPLATE_GUIA_CEGA = "template_guia_cega.vm";
	//private static final String LOGO_SETA = "seta_logo.png";
	private static GerarGuiaCega gerador;
	
	private GerarGuiaCega(){
		
	}
	
	public static void getInstance(){
		if(gerador == null){
			gerador = new GerarGuiaCega();
		}
	}
	
	public String geraHtml(){
		Template template = getTemplate();
		VelocityContext velocityContext = new VelocityContext();
		
		/* context.put("solicitacaointencaocompra",solicitacaoIntencaoCompra);
			context.put("orrequisicao",orRequisicao);
			context.put("formatUtils",FormatUtils.class);
		 */
		
		StringWriter writer = new StringWriter();
		template.merge(velocityContext, writer);
		return writer.toString();
	}	

	public static Template getTemplate() {
		Template template = VelocityUtils.getTemplate(PATH_GUIA_CEGA_FONTES, TEMPLATE_GUIA_CEGA);
		return template;
	}

}
