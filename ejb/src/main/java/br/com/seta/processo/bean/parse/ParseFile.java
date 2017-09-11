package br.com.seta.processo.bean.parse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.jboss.logging.Logger;

import br.com.seta.processo.dominios.TipoPessoa;
import br.com.seta.processo.dto.FormularioFornecedor;
import br.com.seta.processo.exceptions.ParseException;
import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.parse.ExecuteParse;
import br.com.seta.processo.parse.factory.ParseODSFactory;
import br.com.seta.processo.parse.factory.ParseXLSFactory;


@Stateless(name="ParseFile")
@TransactionManagement(TransactionManagementType.CONTAINER)
@Interceptors({LogInterceptor.class})
@LocalBean

public class ParseFile {

	@Inject
    Logger logger;
	
	
	@PostConstruct
	public void init() {
		logger.info("Acessando EJB - Parse de aquivos XSL OU ODS");
	}

	public FormularioFornecedor parse(File file) throws IOException, ParseException {
		
		FormularioFornecedor ff;
		String typeFile = ParseFile.identifyFileTypeUsingFilesProbeContentType(file);
		
		if  (typeFile.equals("application/vnd.oasis.opendocument.spreadsheet")) {
			ff =  new ExecuteParse(new ParseODSFactory(), file).getFormularioFonecedor(); 
		}else if (typeFile.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			ff = new ExecuteParse(new ParseXLSFactory(), file).getFormularioFonecedor();
		}else if (typeFile.equals("application/vnd.ms-excel")) {
			ff = new ExecuteParse(new ParseXLSFactory(), file).getFormularioFonecedor();
		}else {
			throw new ParseException("Este tipo arquivo, não é valido para leitura !");
		}
		
		if(ff.getCpfFisica() == null || ff.getCpfFisica().trim().isEmpty()){
			ff.setTipoPessoa(TipoPessoa.JURIDICA);
		}else{
			ff.setTipoPessoa(TipoPessoa.FISICA);
		}
		
		return ff;
	}


	public static String identifyFileTypeUsingFilesProbeContentType(final File file)	{
		String fileType = "Undetermined";
		try	{
			fileType = Files.probeContentType(file.toPath());
		} catch (IOException ioException) {

		}
		return fileType;
	}

	
	public static void main(String[] args) throws IOException, ParseException {
		new ParseFile().parse(new File ("/home/sergio/app/F-06 Cadastro de Fornecedor.xlsx"));
	}
	
	
	@PreDestroy
	public void destroy() {
		logger.info("Destruindo EJB - Parse de aquivos XSL OU ODS");
	}



}
