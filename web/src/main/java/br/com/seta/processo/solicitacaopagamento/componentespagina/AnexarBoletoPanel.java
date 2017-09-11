package br.com.seta.processo.solicitacaopagamento.componentespagina;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.dto.BoletoSolicitacaoPagamento;
import br.com.seta.processo.pagecomponentes.UploadArquivo;

/**
 * 
 * Representa a seção para anexo de um novo boleto/documento para pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class AnexarBoletoPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private UploadArquivo uploadArquivo;
	
	public AnexarBoletoPanel(String id) {
		super(id);
		
		this.uploadArquivo = new UploadArquivo("uploadArquivo");		
		add(uploadArquivo);
	}
	
	public BoletoSolicitacaoPagamento getBoletoSolicitacaoPagamento(){
		return new BoletoSolicitacaoPagamento(uploadArquivo.getAnexo());
	}	
	
	public FileUpload getFileUpload(){
		return this.uploadArquivo.getFileUpload();
	}

}
