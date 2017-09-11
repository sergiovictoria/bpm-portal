package br.com.seta.processo.servicos;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.jboss.logging.Logger;

public final class EmailsContratos implements Serializable {
	
	

	private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");
    private static String hostName;
    private static String portName;
    private static EmailsContratos emailContratos;
	private static Logger logger = Logger.getLogger(EmailsContratos.class);
	
    
	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsContratos getInstance(){
		if (emailContratos == null){
			logger.info("  **** Criando Serviço Singleton EmailContratos *** ");
			emailContratos = new EmailsContratos();
		}
		return emailContratos;
	}

	
	public EmailsContratos() {
		hostName = PropertiesDtoUtils.getInstance().getValues("hostNameBPM");
		portName = PropertiesDtoUtils.getInstance().getValues("portNameBPM");
	}
    


	private static final String _MAILNOTIFICACAOJURIDICO = "Prezado(a), <p> Há intenções de compras pendentes de inclusão da data de vigência do contrato.<p> " +
			"Por gentileza verifique. <p>"+
			"<a href=\"http://[HOST]:[PORT]/processo\">Clique aqui para acessar</a>"+
			"<br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b><p>"+
			"<br/><img src=\"http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg height=\"45 width=\"15\"><p>";


	private static final String  _ENVIAINSTANCIAENCERRADA = " A instância Nº : [NROINSTANCIAJUR] foi encerrada com sucesso \n <p> [MENSAGEMFINAL] <p>";


	private static final String  _ENVIACONTRATOJURIDICO = "Prezado(a), <p> Segue em anexo o contrato dos fornecedores: <p> [NROINSTANCIAJUR]"+
			"<br/><b><i> Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"+
			"<br/>";

	private static final String _CARREGAMENSAGEMFINAL = "<table border='4'><tr align='CENTER'><td> NOME FORNECEDOR </td>" +
			"<td> DATA INICIO VIGÊNCIA </td><td> DATA FIM VIGÊNCIA </td> </tr><tr><th>[FORNECEDOR]</th><th>[DATAINICO]</th>"+
		    "<th>[DATAFIM]</tr></table><br/>";

	
	public static String criaEmailNotificacaoJuridico(String email) {
		logger.info("  Executando criaEmailNotificacaoJuridico [ "+email+" ]" );
		String corpoEmail = _MAILNOTIFICACAOJURIDICO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName);
		return corpoEmail;
	}


	public static String enviaIntanciaEncerradaJuridico(String nroinstanciajur, String mensagemfinal) {
		String corpoEmail = _ENVIAINSTANCIAENCERRADA
				.replace("[NROINSTANCIAJUR]", nroinstanciajur)
				.replace("[MENSAGEMFINAL]", mensagemfinal);
		return corpoEmail;
	}


	public static String enviaContratoJuridico(String nroinstanciajur, String mensagemfinal) {
		String corpoEmail = _ENVIACONTRATOJURIDICO
				.replace("[MENSAGEMFINAL]", mensagemfinal);
		return corpoEmail;
	}


	public static String CarregaMensagemFinal(Map<String,Object> map) {
      
		String dataInicio = SDF.format((java.util.Date)map.get("DATAINICO"));
		String dataFinal  = SDF.format((java.util.Date)map.get("DATAFIM"));
		String corpoEmail = _CARREGAMENSAGEMFINAL
				.replace("[FORNECEDOR]", (String) map.get("FORNECEDOR"))
				.replace("[DATAINICO]",dataInicio)
				.replace("[DATAFIM]",dataFinal);

		return corpoEmail;
	}



}
