package br.com.seta.processo.servicos;

import java.text.SimpleDateFormat;

import br.com.seta.processo.dto.RegistroHistSolicitacaoPagto;
import br.com.seta.processo.dto.SolicitacaoPagamento;
import br.com.seta.processo.utils.ServicoUtils;

/**
 * Conjunto de métodos para a criação dos diferentes tipos de e-mails utilizados no processo de Solicitação de Pagamento
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class EmailsSolicitacaoPagamento {
	
	private static final String DATE_FORMAT = "dd/MM/yy";
	private static final String URL_IMG_FOOTER = "http://s32.postimg.org/dlb3nhtyt/email_footer.png";
	private static final String URL_IMG_HEADER = "http://s13.postimg.org/4jzk990l3/email_header.png";
	private static final String TAG_IMG = "<p><img src=\"[PATH_IMG]\"/></p>";
	private static final String DIV_CONTAINER_OPEN = "<div>";
	private static final String DIV_CONTAINER_CLOSE = "</div>";
	private static final String TAG_META = "<meta charset=\"UTF-8\" >";
	/*
	private static final String CSS = 
			"<style>"+ 
					"body{margin:0; padding:0;}"+
					".container{width: 599px; margin: auto;}"+
					"h3{text-align: center;}" +
					".conteudo{padding: 0 10px}"+
			"</style>";*/
	
	private static final String NOTIFICACAO_NOVA_SOLICITACAO_PAGAMENTO = 
		//"<h3 style=\"text-align: center;\">Detalhes da solicitação de pagamento</h3>" +
		"<p><b>Detalhes da solicitação de pagamento</b></p>" +
		"<div>" +
			"<p>" +
				"Solicitante: [NOME] <br>" +
				"E-mail: [EMAIL_SOLICITANTE] <br>" +
				"Telefone: [TELEFONE] <br>" +
				"Empresa: [LOJA] <br>" +
				"Nota de Despesa: [CATEGORIA] <br>" +
				"Favorecido: [FAVORECIDO] <br>" +
				"Valor: [VALOR] <br>" +
				"Vencimento: [VENCIMENTO] <br>" +
				"Mensagem: [MENSAGEM] <br>" +
			"</p>" +
			"<p>" +
				"A solicitação foi registrada com sucesso e está disponível na plataforma BPM SETA." +
			"</p>" +
		"</div>";
	
	private static final String NOTIFICACAO_APROVACAO_SOLICITACAO_PAGAMENTO = 
		//"<h3 style=\"text-align: center;\">Detalhes da solicitação de pagamento</h3>" +
		"<p><b>Detalhes da solicitação de pagamento</b></p>" +
		"<div>" +
			"<p >" +
				"Empresa: [LOJA] <br>" +
				"Nota de Despesa: [CATEGORIA] <br>" +
				"Favorecido: [FAVORECIDO] <br>" +
				"Valor: [VALOR] <br>" +
				"Mensagem: [MENSAGEM] <br>" +
			"</p>" +
			"<p >" +
				"A solicitação foi aprovada com sucesso e registrada na Consinco pelo ID Título: [SEQ_TITULO_GERADO]." +
			"</p>" +
		"</div>";
	
	private static final String NOTIFICACAO_REJEICAO_SOLICITACAO_PAGAMENTO = 
			//"<h3 style=\"text-align: center;\">Detalhes da solicitação de pagamento</h3>" +
			"<p><b>Detalhes da solicitação de pagamento</b></p>" +
			"<div>" +
				"<p >" + 
					"Empresa: [LOJA] <br>" +
					"Nota de Despesa: [CATEGORIA] <br>" +
					"Favorecido: [FAVORECIDO] <br>" +
					"Valor: [VALOR] <br>" +
					"Mensagem: [MENSAGEM] <br>" +
				"</p>" +
				"<p >" +
					"A solicitação foi rejeitada." +
				"</p>" +
				"<p >[COMENTARIOS]</p>" +
			"</div>";
	
	public static String criaEmailNovaSolicitacaoPagamento(SolicitacaoPagamento solicitacaoPagamento){
		
		String nome = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNomeSolicitante(), "");
		String emailSolicitante = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getEmailSolicitante(), "");
		String telefone = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getTelefoneSolicitante(), "");
		String loja = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNomeLoja(), "");
		String categoria = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNaturezaDespesa(), "");
		String favorecido = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getFornecedor(), "");
		String mensagem = solicitacaoPagamento.getMensagem() != null ? solicitacaoPagamento.getMensagem() : "";
		String valor = extraiValor(solicitacaoPagamento);		
		String vencimento = "";
		if(solicitacaoPagamento.getOrSolicitacaovenctos() != null
				&& solicitacaoPagamento.getOrSolicitacaovenctos().size() == 1
				&& solicitacaoPagamento.getOrSolicitacaovenctos().get(0).getDtavencimento() != null){
			vencimento = new SimpleDateFormat(DATE_FORMAT).format(solicitacaoPagamento.getOrSolicitacaovenctos().get(0).getDtavencimento());			
		}
		String corpoEmail = NOTIFICACAO_NOVA_SOLICITACAO_PAGAMENTO
				.replace("[NOME]", nome)
				.replace("[EMAIL_SOLICITANTE]", emailSolicitante)
				.replace("[TELEFONE]", telefone)
				.replace("[LOJA]", loja)
				.replace("[CATEGORIA]", categoria)
				.replace("[FAVORECIDO]", favorecido)
				.replace("[VALOR]", valor)
				.replace("[VENCIMENTO]", vencimento)
				.replace("[MENSAGEM]", mensagem);
		
		return criaEmail(corpoEmail);		
	}

	public static String criaEmailAprovacaoSolicitacaoPagamento(SolicitacaoPagamento solicitacaoPagamento){
		String loja = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNomeLoja(), "");
		String categoria = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNaturezaDespesa(), "");
		String favorecido = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getFornecedor(), "");
		String mensagem = solicitacaoPagamento.getMensagem() != null ? solicitacaoPagamento.getMensagem() : "";
		String valor = extraiValor(solicitacaoPagamento);
		Object seqNota = ServicoUtils.ehNulo(solicitacaoPagamento.getSeqNota(), "");
		
		String corpoEmail = NOTIFICACAO_APROVACAO_SOLICITACAO_PAGAMENTO
				.replace("[LOJA]", loja)
				.replace("[CATEGORIA]", categoria)
				.replace("[FAVORECIDO]", favorecido)
				.replace("[VALOR]", valor)				
				.replace("[MENSAGEM]", mensagem)
				.replace("[SEQ_TITULO_GERADO]", seqNota.toString());
		
		return criaEmail(corpoEmail);
	}
	
	public static String criaEmailRejeicaoSolicitacaoPagamento(SolicitacaoPagamento solicitacaoPagamento){
		String loja = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNomeLoja(), "");
		String categoria = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getNaturezaDespesa(), "");
		String favorecido = (String) ServicoUtils.ehNulo(solicitacaoPagamento.getFornecedor(), "");
		String mensagem = solicitacaoPagamento.getMensagem() != null ? solicitacaoPagamento.getMensagem() : "";
		String valor = extraiValor(solicitacaoPagamento);
		String comentarios = null;
		
		for (RegistroHistSolicitacaoPagto registro : solicitacaoPagamento.getHistorico()) {
			if (registro.getStatus().equals(SolicitacaoPagamento.STATUS_REJEITADO)) {
				comentarios = registro.getComentario();
				break;
			}
		}		
		if(comentarios == null) comentarios = "";
		
		String corpoEmail = NOTIFICACAO_REJEICAO_SOLICITACAO_PAGAMENTO
				.replace("[LOJA]", loja)
				.replace("[CATEGORIA]", categoria)
				.replace("[FAVORECIDO]", favorecido)
				.replace("[VALOR]", valor)				
				.replace("[MENSAGEM]", mensagem)
				.replace("[COMENTARIOS]", comentarios);
	
		return criaEmail(corpoEmail);
	}

	private static String extraiValor(SolicitacaoPagamento solicitacaoPagamento) {
		String valor = "";
		if(solicitacaoPagamento.getValor() != null){
			valor = ServicoUtils.formataValorMonetario(solicitacaoPagamento.getValor());
		}
		return valor;
	}
	
	private static String criaEmail(String corpoEmail) {
		return 	"<html>" +
				"<head>" +					
					TAG_META +
				"</head>" +
				"<body>" +
					"<!--[if mso]>" +
						"<style type=\"text/css\">" +
							"body{font-family: Arial, Verdana !important;}" +
						"</style>" +
					"<![endif]-->" +
					DIV_CONTAINER_OPEN +
					TAG_IMG.replace("[PATH_IMG]", URL_IMG_HEADER)+
					corpoEmail+
					TAG_IMG.replace("[PATH_IMG]", URL_IMG_FOOTER)+					
					DIV_CONTAINER_CLOSE +
				"</body>" +
				"</html>";		
	}

	public static void main(String[] args){
		SolicitacaoPagamento solicitacaoPagamento = new SolicitacaoPagamento();
		String email = EmailsSolicitacaoPagamento.criaEmailAprovacaoSolicitacaoPagamento(solicitacaoPagamento );
		System.out.println(email);
	}
	
}
