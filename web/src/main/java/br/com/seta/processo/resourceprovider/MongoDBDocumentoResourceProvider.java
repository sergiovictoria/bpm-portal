package br.com.seta.processo.resourceprovider;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.request.resource.AbstractResource;

import br.com.seta.processo.bean.dao.interfaces.DocumentoDao;
import br.com.seta.processo.dto.Documento;

/**
 * Classe que prover acesso aos documentos (br.com.seta.processo.dto.Documento) armazenados no MongoDB na Collections de Documentos
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class MongoDBDocumentoResourceProvider extends AbstractResource {
	private static final long serialVersionUID = 1L;

	private String id;
	
	@Inject
	private DocumentoDao dao;
	
	public MongoDBDocumentoResourceProvider(String id) {
		super();
		this.id = id;
		CdiContainer.get().getNonContextualManager().inject(this);
	}	
	
	@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		Documento documento = dao.buscaDocumento(id);
		ResourceResponse resourceResponse = criaResourceResponse(documento);
		return resourceResponse;			
	}
	
	private ResourceResponse criaResourceResponse(Documento documento){
		WriteCallback writer = criaWriteCallback(documento);
		ResourceResponse resourceResponse = new ResourceResponse();
		resourceResponse.setContentType(documento.getContentType());
		resourceResponse.setFileName(documento.getNome());
		resourceResponse.setContentLength(documento.getTamanho());
		resourceResponse.setWriteCallback(writer);
		
		return resourceResponse;
	}
	
	private WriteCallback criaWriteCallback(final Documento documento){
		WriteCallback writer = new WriteCallback() {
			
			@Override
			public void writeData(Attributes attributes) throws IOException {
				byte[] conteudo = documento.getConteudo();
				OutputStream os = attributes.getResponse().getOutputStream();
				os.write(conteudo);
				os.flush();
				os.close();
			}
		};
		
		return writer;
	}
}
