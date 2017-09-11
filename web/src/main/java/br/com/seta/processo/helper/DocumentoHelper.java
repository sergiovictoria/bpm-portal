package br.com.seta.processo.helper;

import java.util.StringTokenizer;

import org.apache.wicket.markup.html.form.upload.FileUpload;

import br.com.seta.processo.dto.Documento;

/**
 * Helper responsável por criar o objeto Documento que será persistido no Bonita BPM
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class DocumentoHelper {

	public static Documento criaDocumento(FileUpload fileUpload, long caseId){		
		byte[] conteudo = fileUpload.getBytes();
		String nome = fileUpload.getClientFileName();
		String contentType = fileUpload.getContentType();
		String tipo = getTipoArquivo(fileUpload.getContentType());
		long tamanho = fileUpload.getSize();
		
		Documento documento = new Documento(caseId, conteudo, nome, contentType, tipo, tamanho);
		return documento;
	}
	
	public static Documento criaDocumento(FileUpload fileUpload, String descricao){
		Documento documento = criaDocumento(fileUpload, 0);
		documento.setDescricao(descricao);
		return documento;
	}
	
	public static Documento criaDocumento(FileUpload fileUpload, long caseId, String descricao){
		Documento documento = criaDocumento(fileUpload, caseId);
		documento.setDescricao(descricao);
		return documento;
	}
	
	public static Documento criaDocumento(FileUpload fileUpload){		
		return criaDocumento(fileUpload, 0);		
	}
	
	/**
	 * Preenche um documento pré existente com os valores do fileUpload
	 * 
	 * @param fileUpload
	 * @param caseId
	 * @param documento
	 */
	public static void preencheDocumento(FileUpload fileUpload, long caseId, Documento documento){
		if(fileUpload == null) return;
		
		byte[] conteudo = fileUpload.getBytes();
		String nome = fileUpload.getClientFileName();
		String contentType = fileUpload.getContentType();
		String tipo = getTipoArquivo(fileUpload.getContentType());
		long tamanho = fileUpload.getSize();
		
		documento.setConteudo(conteudo);
		documento.setNome(nome);
		documento.setContentType(contentType);
		documento.setTipo(tipo);
		documento.setTamanho(tamanho);
	}
	
	/**
	 * Preenche um documento pré existente com os valores do fileUpload
	 * 
	 * @param fileUpload
	 * @param documento
	 */
	public static void preencheDocumento(FileUpload fileUpload, Documento documento){
		if(fileUpload == null) return;
		
		byte[] conteudo = fileUpload.getBytes();
		String nome = fileUpload.getClientFileName();
		String contentType = fileUpload.getContentType();
		String tipo = getTipoArquivo(fileUpload.getContentType());
		long tamanho = fileUpload.getSize();
		
		documento.setConteudo(conteudo);
		documento.setNome(nome);
		documento.setContentType(contentType);
		documento.setTipo(tipo);
		documento.setTamanho(tamanho);
	}
	
	private static String getTipoArquivo(String contentType){
		if(contentType == null) return "";
		
		StringTokenizer tokenizer = new StringTokenizer(contentType, "/");
		
		if(tokenizer.countTokens() == 2){
			tokenizer.nextToken();
			return tokenizer.nextToken();
		}
		
		return "";
	}
}
