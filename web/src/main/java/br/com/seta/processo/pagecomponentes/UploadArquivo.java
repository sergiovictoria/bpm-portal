package br.com.seta.processo.pagecomponentes;

import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.panel.Panel;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.helper.DocumentoHelper;

/**
 * Renderiza um componente de anexo de arquivo
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class UploadArquivo extends Panel {
	
	private static final long serialVersionUID = 1L;

	private FileUploadField fileUploadField;
	
	public UploadArquivo(String id) {
		super(id);
		
		fileUploadField = new FileUploadField("fileUploadField");
		
		add(fileUploadField);
	}
	
	public FileUploadField getFileUploadField(){
		return fileUploadField;
	}
	
	public FileUpload getFileUpload(){
		return  fileUploadField.getFileUpload();
	}
	
	public Documento getAnexo(){		
		if(!existeArquivoAnexado()) 
			return null;
		
		return DocumentoHelper.criaDocumento(getFileUpload());
	}
	
	public Documento getAnexo(long caseId, String descricao){		
		if(!existeArquivoAnexado()) 
			return null;
		
		return DocumentoHelper.criaDocumento(getFileUpload(), caseId, descricao);
	}
	
	public boolean existeArquivoAnexado(){
		if(getFileUpload() == null)
			return false;
		
		return true;
	}

}
