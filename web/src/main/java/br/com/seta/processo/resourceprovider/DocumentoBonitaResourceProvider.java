package br.com.seta.processo.resourceprovider;

import java.io.IOException;
import java.io.OutputStream;

import javax.inject.Inject;

import org.apache.http.ParseException;
import org.apache.wicket.Session;
import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.request.resource.AbstractResource;

import br.com.seta.processo.dto.Documento;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.ExecuteRestAPI;

/**
 * Classe que prover acesso aos documentos (br.com.seta.processo.dto.Documento) armazenados no Bonita BPM
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DocumentoBonitaResourceProvider extends AbstractResource {
	private static final long serialVersionUID = 1L;

	private long taskId;
	private String nomeVariavelDocumento;
	
	@Inject
	ExecuteRestAPI restApi;
	
	public DocumentoBonitaResourceProvider(long taskId, String nomeVariavelDocumento) {
		super();
		this.taskId = taskId;
		this.nomeVariavelDocumento = nomeVariavelDocumento;
		
		CdiContainer.get().getNonContextualManager().inject(this);
	}
	
	public void setTaskId(long taskId){
		this.taskId = taskId;
	}
	
	public void setNomeVariavelDocumento(String nomeVariavel){
		this.nomeVariavelDocumento = nomeVariavel;
	}
	
	@Override
	protected ResourceResponse newResourceResponse(Attributes attributes) {
		User user = (User) Session.get().getAttribute("user");
		
		try {
			Documento documento = (Documento) restApi.retriveVariableTask(user, taskId, Documento.class, nomeVariavelDocumento);
			ResourceResponse resourceResponse = criaResourceResponse(documento);
			return resourceResponse;
		} catch (ParseException | InstantiationException
				| IllegalAccessException | IOException | HttpStatusException e) {
			throw new WicketRuntimeException("Ocorreu um erro ao efetuar o download do documento");
		}				
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
