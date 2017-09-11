/**
 * 
 */
package br.com.seta.processo.servicos;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jboss.logging.Logger;

import br.com.seta.processo.dto.Historico;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;

/**
 * @author Sérgio da Victória
 *
 *   
 */

public final class EmailsSolicitacaoIntencaoCompra implements Serializable {

	private static final long serialVersionUID = 1L;

	private static String hostName;
	private static String portName;
	private static EmailsSolicitacaoIntencaoCompra emailsSolicitacaoIntencaoCompra;
	private static Logger logger = Logger.getLogger(EmailsSolicitacaoIntencaoCompra.class);
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static Locale ptBr = new Locale("pt", "BR");
	private static NumberFormat formatValue = NumberFormat.getCurrencyInstance(ptBr);

	private static final String _NOTIFICACAO_PROCESSO_SUPRIMENTOS = "Prezado(a), <p> Há uma nova intenção de compra Nº [NUMEROINTENCAOCOMPRA] da área [NOMEAREASOLICITANTE], pendente de aprovação.<p>"+
			"<a href=\"http://[HOST]:[PORT]/processo/\">Clique aqui para acessar</a><p><br/><br/>"+
			"<b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p><br/>"+
			"<img src=\"http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg\" height=\"45 width=\"15\"><p>";


	private static final String _APROVACAO_FUNCIONAL_APROVADA = "Prezado(a), <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] , foi aprovada.  <p>"+
			"Aprovador Funcional: [NOMEAPROVADORFUNCIONAL] <p> Histórico(s) [COMENTARIOAPROVFUNC] <p><b>Nome do Fornecedor: </b>"
			+"[NOMEFORNECEDOR] <p> <b> Itens: </b><p> [ITENS] <br/>"
			+"<b>Observação: Favor aguardar o recebimento do email de notificação da Abertura de Requisição para autorizar o fornecedor a entregar o produto / prestar o serviço. </b><p>"
			+"<a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p>"
			+"<br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"
			+"<br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";



	private static final String _APROVACAO_FUNCIONAL_REPROVADA = "rezado(a), <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA]"
			+", foi reprovada. <p> Aprovador Funcional:  [NOMEAPROVADORFUNCIONAL]  <p> Histórico(s) [COMENTARIOAPROVFUNC] <p>"
			+" <b>Nome do Fornecedor: </b>  [NOMEFORNECEDOR] <p> <b>Itens: </b> <p> [ITENS]"
			+" <br/><a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/>"
			+" <br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"
			+" <br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";


	private static final String _APROVACAO_HIERARQUIO_SOLICITACAO = "Prezado(a), <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] , foi aprovada pelo aprovador funcional [NOMEAPROVADORFUNCIONAL]"
			+" E foi enviada para aprovação hierarquica.<p> Histórico(s) [COMENTARIOAPROVFUNC] <p><b>Nome do Fornecedor: </b>"
			+"[NOMEFORNECEDOR] <p><b>Itens: </b> [ITENS]"
			+"Observação: Favor aguardar o recebimento do email de notificação da Abertura de Requisição para autorizar o fornecedor a entregar o produto / prestar o serviço. <br/><a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p><br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";



	private static final String _APROVACAO_ADD_ITENS = "<table border='1'><tr align='CENTER'><td> DESCRIÇÃO </td><td> QUANTIDADE </td><td> VALOR ITEM </td>"
			+"</tr><tr>";


	private static final String _APROVACAO_ADD_HISTORICO = "<table border='1'><tr align='CENTER'> <td>STATUS</td> <td>NOME</td> <td>DATA</td> <td>MOTIVO</td> <td>COMENTÁRIO</td>"
			+"</tr><tr>";

	private static final String _APROVADOR_HIERARQUIO_NOTIFICACAO = "Prezado(a), <p> Há uma nova intenção de compra Nº [NUMEROINTENCAOCOMPRA] da área [NOMEAREASOLICITANTE], pendente de aprovação.<p>"
			+ "<a href=\"http://[HOST]:[PORT]/processo/\">Clique aqui para acessar</a><p><br/>"
			+ "<br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"
			+ "<br/><img src=\"http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg\" height=\"45 width=\"15\"><p>";


	private static final String _APROVADOR_HIERARQUIO_REJEITADO = "Prezado(a), <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] , foi reprovada <p>"
			+"Aprovador Hierarquico:  [NOMEAPROVADORHIERARQUICO] <p> Comentário [COMENTARIOAPROVHIERARQ] <p>"
			+"<b>Nome do Fornecedor: </b>  [NOMEFORNECEDOR] <p><b>Itens: [ITENS] </b> <p> m <br/>"
			+"<a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p><br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";


	private static final String _APROVADOR_HIERARQUIO_APROVADO = "Prezado(a), <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] , foi aprovada pelo aprovador hierarquico . <p>"
			+"Aprovador Hierarquico: [NOMEAPROVADORHIERARQUICO] <p> Comentário  [COMENTARIOAPROVHIERARQ] <p>"
			+"<b>Nome do Fornecedor: </b>  [NOMEFORNECEDOR]  <p><b>Itens: [ITENS] </b> <p> m <br/>"
			+"<a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p><br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";


	private static final String _NECESSIDADE_CADASTRO = "Prezado(a), <p> uma nova intenção de compra Nº [NUMEROINTENCAOCOMPRA] da área [NOMEAREASOLICITANTE] , pendente de cadastro.<p>"+
			"<a href=\"http://[HOST]:[PORT]/processo/\">Clique aqui para acessar</a><p><br/><br/>"+
			"<b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p><br/>"+
			"<img src=\"http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg\" height=\"45 width=\"15\"><p>";


	private static final String _NOTIFICACAO_JURIDICO = "Prezado(a),<p> Segue em anexo o contrato da intenção de compra de serviço Nº [NUMEROINTENCAOCOMPRA] da área [NOMEAREASOLICITANTE], <p>"+
			"Responsável CSC- Juridico:[NOMERESPCSCJURIDICO] <p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"+
			"<br/><img src=\"http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg\" height=\"45 width=\"15\"><p>";


	private static final String _NOTIFICACAO_CANCELAMENTO_CSC_JURIDICO = "Prezado(a),<p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] ,foi cancelada. <p><br/>CSC- Juridico : [NOMERESPCSCJURIDICO] <p>"+
			"Comentário(s):  [COMENTARIOANEXOCONTRATO] <p><b>Nome do Fornecedor: </b> [NOMEFORNECEDOR] <p><b> Itens: </b>"+
			"[ITENS]<br/>Observação: Favor aguardar o recebimento do email de notificação da Abertura de Requisição para autorizar o fornecedor a entregar o produto / prestar o serviço.<p>"+
			"<a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"+
			"<br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";

	private static final String _SOLICITACAO_INFORMACAO_CADASTRO = "Prezado(a), <p> A área de cadastro solicitou informações para a finalização do cadastro.<p> "+
			"Por gentileza verifique. <p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA] <p> <b>Nome do Fornecedor: </b>[NOMEFORNECEDOR]<p>"+
			"<b>Itens: </b><p> [ITENS] <br/>Observação: Favor aguardar o recebimento do email de notificação da Abertura de Requisição para autorizar o fornecedor a entregar o produto / prestar o serviço.<p>"+
			"<a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"+
			"<br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";


	private static final String _SOLICITACAO_REQUISICAO_ABERTA = "Prezado(a), <p> Sua intenção de compra está aprovada e já foi aberta a requisição de compra para efetivar o pagamento do fornecedor após a entrega de produtos ou prestação dos serviços. <p>"+
			"<b>Favor seguir as seguintes instruções: </b><p><b>1- Comunicar ao fornecedor que a compra do produto / prestação do serviço está autorizada, e que pode ser efetuado o trâmite para entrega / prestação de serviço.</b><p>"+
			"<b>2- Solicitar ao fornecedor que, caso emita NFe (Nota Fiscal Eletrônica) que a mesma seja enviada para o email nfe.csc@setaatacadista.com.br </b><p>"+
			"<b>3- Solicitar ao fornecedor que inclua no campo de 'Observações ou Dados Adicionais' da Nota Fiscal o número da Intenção e da Requisição, que seguem abaixo: </b><p>"+
			"<br/><b>Número Intenção de Compra: </b> [NUMEROINTENCAOCOMPRA]<br/><b>Número Requisição aberta: </b> [REQUISICAOABERTA]<br/>"+
			"<b>Loja Faturamento:</b> [NOMEMPRESA] <br/><b>Local Entrega/Prestação de Serviço: </b> [LOCALENTREGALOJASELEC] <br/>"+
			"<b>Nome do Fornecedor: </b> [NOMEFORNECEDOR] <p><b>Itens: </b><p>[ITENS]<br/>"+
			"<b>Em caso de dúvidas quanto as instruções acima, favor entrar em contato pelo email: atendimento.csc@setaatacadista.com.br</b><p>"+
			"<br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem.</i></b><p><br/>";


	private static final String _SOLICITACAO_REQUISICAO_CANCELADA = "Prezado(a),<p> A intenção de compra Nº [NUMEROINTENCAOCOMPRA], foi cancelada. <p>"+
			"Responsável: [NOMERESPABERTURAREQUISICAO] <p> Comentário: [COMENTARIOEXIBIRRESUMOABERTURAREQ] <p><b>Nome do Fornecedor: </b>[NOMEFORNECEDOR] <p>"+
			"<b>Itens: </b><p>[ITENS]<br/><a href='http://[HOST]:[PORT]/processo/'>Clique aqui para acessar</a><p><br/><br/><b><i>Esta é uma resposta automática. Por favor, não responda a esta mensagem. </i></b> <p>"+
			"<br/><br/><img src='http://www.setaatacadista.com.br/suporte/upload/images/logo2.jpg' height='45 width='15'><p>";




	/*****
	 * 
	 * @return Singleton Object  
	 */

	public static EmailsSolicitacaoIntencaoCompra getInstance(){
		if (emailsSolicitacaoIntencaoCompra == null){
			logger.info("  **** Criando Serviço Singleton EmailsSolicitacaoIntencaoCompra *** ");
			emailsSolicitacaoIntencaoCompra = new EmailsSolicitacaoIntencaoCompra();
		}
		return emailsSolicitacaoIntencaoCompra;
	}


	public EmailsSolicitacaoIntencaoCompra() {
		hostName = PropertiesDtoUtils.getInstance().getValues("hostNameBPM");
		portName = PropertiesDtoUtils.getInstance().getValues("portNameBPM");
	}


	public static String solicitacaRequisicaoAberta(Long numerointencaocompra,String nomerespaberturarequisicao, List<Historico> historicos,
			String nomefornecedor, Set<OrReqitensdespesa> orReqitensdespesas, Long requisicaoaberta, String localentregalojaselec, String nomeEmpesa, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaRequisicaoAberta) [ "+email+ "]");



		String corpoEmail = _SOLICITACAO_REQUISICAO_ABERTA
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMERESPABERTURAREQUISICAO]",nomerespaberturarequisicao)
				.replace("[COMENTARIOEXIBIRRESUMOABERTURAREQ]",addHistoricos(historicos))
				.replace("[NOMEMPRESA]", nomeEmpesa) 
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[LOCALENTREGALOJASELEC]", localentregalojaselec)
				.replace("[REQUISICAOABERTA]",requisicaoaberta.toString())
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		return corpoEmail;
	}


	public static String solicitacaRequisicaoCancelada(Long numerointencaocompra, String nomerespaberturarequisicao,String nomefornecedor, 
			List<Historico> historicos, Set<OrReqitensdespesa> orReqitensdespesas, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaRequisicaoCancelada) [ "+email+ "]");

		String corpoEmail = _SOLICITACAO_REQUISICAO_CANCELADA
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[COMENTARIOEXIBIRRESUMOABERTURAREQ]",addHistoricos(historicos))
				.replace("[NOMERESPABERTURAREQUISICAO]",nomerespaberturarequisicao)
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		return corpoEmail;
	}


	public static String solicitacaInformacaoCadastro(java.lang.Long numerointencaocompra, 	String nomefornecedor,  Set<OrReqitensdespesa> orReqitensdespesas, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaInformacaoCadastro) [ "+email+ "]");

		String corpoEmail = _SOLICITACAO_INFORMACAO_CADASTRO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));		
		return corpoEmail;
	}

	public static String solicitacaAprovacaoCSCJuridicoCancelado(java.lang.Long numerointencaocompra, String nomerespcscjuridico,
			String nomefornecedor,  Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email ) {

		logger.info(" Enviando  Email (solicitacaAprovacaoCSCJuridicoCancelado) [ "+email+ "]");

		if (isVariavelNull(nomerespcscjuridico)) {
			nomerespcscjuridico = " ";
		}

		if (isVariavelNull(nomefornecedor)) {
			nomefornecedor = " ";
		}

		String corpoEmail = _NOTIFICACAO_CANCELAMENTO_CSC_JURIDICO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMERESPCSCJURIDICO]",nomerespcscjuridico)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[COMENTARIOANEXOCONTRATO]", addHistoricos(historicos))
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		return corpoEmail;
	}



	public static String enviaSolicitacaoIntencaoSolicitacaoJuridico(java.lang.Long numerointencaocompra, String nomeareasolicitante, String nomerespcscjuridico, String email) {

		logger.info(" Enviando  Email (enviaSolicitacaoIntencaoSolicitacaoJuridico) [ "+email+ "]");

		String corpoEmail = _NOTIFICACAO_JURIDICO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEAREASOLICITANTE]", nomeareasolicitante)
				.replace("[NOMERESPCSCJURIDICO]", nomerespcscjuridico);
		return corpoEmail;
	}

	public static String enviaSolicitacaoIntencaoSolicitacaoCadastro(java.lang.Long numerointencaocompra, String nomeareasolicitante, String email) {

		logger.info(" Enviando  Email (enviaSolicitacaoIntencaoSolicitacaoCadastro) [ "+email+ "]");

		String corpoEmail = _NECESSIDADE_CADASTRO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEAREASOLICITANTE]", nomeareasolicitante);
		return corpoEmail;
	}

	public static String enviaSolicitacaoIntencaoCompra(java.lang.Long numerointencaocompra, String nomeareasolicitante, String email) {

		logger.info(" Enviando  Email (enviaSolicitacaoIntencaoCompra) [ "+email+ "]");

		String corpoEmail = _NOTIFICACAO_PROCESSO_SUPRIMENTOS
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEAREASOLICITANTE]", nomeareasolicitante);
		return corpoEmail;
	}


	public static String enviaSolicitacaoIntencaoCompraHierarquia(java.lang.Long numerointencaocompra, String nomeareasolicitante, String email) {

		logger.info(" Enviando  Email (enviaSolicitacaoIntencaoCompraHierarquia) [ "+email+ "]");

		String corpoEmail = _APROVADOR_HIERARQUIO_NOTIFICACAO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[NOMEAREASOLICITANTE]", nomeareasolicitante);
		return corpoEmail;
	}


	public static String solicitacaAprovacaoFuncionalAprovada(java.lang.Long numerointencaocompra, String nomeaprovadorfuncional,
			String nomefornecedor,  Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaAprovacaoFuncionalAprovada) [ "+email+ "]");

		EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq());
		
		String corpoEmail = _APROVACAO_FUNCIONAL_APROVADA
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[COMENTARIOAPROVFUNC]", addHistoricos(historicos))
				.replace("[NOMEAPROVADORFUNCIONAL]", nomeaprovadorfuncional)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		
		return corpoEmail;
	}


	public static String solicitacaAprovacaoFuncionalReprovada(java.lang.Long numerointencaocompra, String nomeaprovadorfuncional,
			String nomefornecedor, Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaAprovacaoFuncionalReprovada) [ "+email+ "]");

		String corpoEmail = _APROVACAO_FUNCIONAL_REPROVADA
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[COMENTARIOAPROVFUNC]", addHistoricos(historicos))
				.replace("[NOMEAPROVADORFUNCIONAL]", nomeaprovadorfuncional)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
				.replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		return corpoEmail;
	}


	public static String solicitacaAprovacaoHierarquioAprovada(java.lang.Long numerointencaocompra, String nomeaprovadorhierarquico,
			String nomefornecedor, Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaAprovacaoHierarquioAprovada) [ "+email+ "]");

		String corpoEmail = _APROVADOR_HIERARQUIO_APROVADO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[COMENTARIOAPROVHIERARQ]", addHistoricos(historicos))
				.replace("[NOMEAPROVADORHIERARQUICO]", nomeaprovadorhierarquico)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
		        .replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		System.out.println(corpoEmail);
		return corpoEmail;
	}



	public static String solicitacaAprovacaoRejeitadaHierarquio(java.lang.Long numerointencaocompra, String nomeaprovadorhierarquico,
			String nomefornecedor, Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaAprovacaoRejeitadaHierarquio) [ "+email+ "]");

		String corpoEmail = _APROVADOR_HIERARQUIO_REJEITADO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[COMENTARIOAPROVHIERARQ]", addHistoricos(historicos))
				.replace("[NOMEAPROVADORHIERARQUICO]", nomeaprovadorhierarquico)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
		        .replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		System.out.println(corpoEmail);
		return corpoEmail;
	}



	public static String solicitacaAprovacaoAvisoHierarquio(java.lang.Long numerointencaocompra, String nomeaprovadorfuncional,
			String nomefornecedor, Set<OrReqitensdespesa> orReqitensdespesas, List<Historico> historicos, OrRequisicao orRequisicao, String email) {

		logger.info(" Enviando  Email (solicitacaAprovacaoRejeitadaHierarquio) [ "+email+ "]");

		String corpoEmail = _APROVACAO_HIERARQUIO_SOLICITACAO
				.replace("[HOST]", hostName)
				.replace("[PORT]", portName)
				.replace("[NUMEROINTENCAOCOMPRA]",numerointencaocompra.toString())
				.replace("[COMENTARIOAPROVFUNC]", addHistoricos(historicos))
				.replace("[NOMEAPROVADORFUNCIONAL]", nomeaprovadorfuncional)
				.replace("[NOMEFORNECEDOR]", nomefornecedor)
				.replace("[ITENS]",addItens(orReqitensdespesas))
		        .replace("[TOTAL]",EmailsSolicitacaoIntencaoCompra.formatValue.format(orRequisicao.getVlrliqreq()));
		System.out.println(corpoEmail);
		return corpoEmail;
	}

	public static String addHistoricos(List<br.com.seta.processo.dto.Historico> historicos) {

		String historico ="";

		if (historicos.size()>0) {
			for (Historico dto : historicos) {
				historico+="<tr><td>"+ dto.getStatus()+"</td><td>"+dto.getNome()+"</td><td>"+new SimpleDateFormat(DATE_FORMAT).format(dto.getData())+"</td><td>"+dto.getMotivo()+"</td><td>"+dto.getComentario()+"</td></tr>";
			}
			return _APROVACAO_ADD_HISTORICO +historico+"</td></table>";
		}else {
			return _APROVACAO_ADD_HISTORICO +historico+"</td></table>";
		}

	
	}


	public static String addItens(Set<OrReqitensdespesa> orReqitensdespesas) {

		String corpoEmail ="";
		String itens ="";

		if (orReqitensdespesas.size()>0) {
			for (OrReqitensdespesa dto : orReqitensdespesas) {
				itens+="<tr><td>"+ dto.getDescricao()+"</td><td>"+dto.getQuantidade()+"</td><td>"+EmailsSolicitacaoIntencaoCompra.formatValue.format(dto.getVlritem())+"</td></tr>";
			}
			corpoEmail = _APROVACAO_ADD_ITENS + itens + "<td colspan='3' align='right'><b>VALOR TOTAL </b> [TOTAL] </td></table>";
		}
		
		return corpoEmail;
		
	}

	public static boolean isVariavelNull(String s){
		return s == null;
	}


}
