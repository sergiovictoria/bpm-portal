package br.com.seta.processo.recebimento.guiacega;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;

import br.com.seta.processo.dto.Comentario;
import br.com.seta.processo.dto.OrReqitensdespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.Recebimento;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.utils.FormatUtils;

public class GuiaCega extends WebPage{

	private static final long serialVersionUID = 1L;	
	
	private static final String FORMATO_MONETARIO = "#,##0.00";
	private static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
	static{		
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
	}	
	private static final DecimalFormat monetario = new DecimalFormat(FORMATO_MONETARIO, symbols);
	
	private OrRequisicao requisicao;
	private SolicitacaoIntencaoCompra solicitacao;
	private Recebimento recebimento;
	
	public GuiaCega(OrRequisicao requisicao, SolicitacaoIntencaoCompra solicitacao, Recebimento recebimento){
		this.recebimento = recebimento;
		this.solicitacao = solicitacao;
		this.requisicao = requisicao;
		
		add(new Cabecalho(), new DadosSolicitacao(), new Valores(), new Itens(), new Validacao(), new Divergencias(), new AprovacaoRecebDivergente());
	}
	
	private class Cabecalho extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Label tipoDespesa, nroIntencao, requisicaoC5, dataInclusaoC5, dataCompraC5;
		
		public Cabecalho() {
			super("cabecalho");
			
			tipoDespesa = new Label("tipoDespesa", requisicao.getTipoDespesa());
			nroIntencao = new Label("nroIntencao", requisicao.getNumeroIntencaoSolicitacaoCompra());
			requisicaoC5 = new Label("requisicaoC5", requisicao.getNrorequisicao());
			dataInclusaoC5 = new Label("dataInclusaoC5", FormatUtils.formata(requisicao.getDtainclusao(), "dd/MM/yyyy"));
			dataCompraC5 = new Label("dataCompraC5", FormatUtils.formata(requisicao.getDtacompra(), "dd/MM/yyyy"));		
			
			add(tipoDespesa, nroIntencao, requisicaoC5, dataInclusaoC5, dataCompraC5);	
		}
		
	}
	
	private class DadosSolicitacao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		Label nomeSolicitante, fornecedor, naturezaDespesa, localEntrega, codModelo, empresa, cgo, dataSolicitacao;
		
		public DadosSolicitacao() {
			super("dadosSolicitacao");
			
			nomeSolicitante = new  Label("nomeSolicitante", solicitacao.getNomeRespPreenchimento()); 
			fornecedor = new  Label("fornecedor", requisicao.getNomeFornecedor() ); 
			naturezaDespesa = new  Label("naturezaDespesa", requisicao.getNaturezaDespesa()); 
			localEntrega = new  Label("localEntrega", requisicao.getLocalDeEntrega());
			codModelo = new  Label("codModelo", requisicao.getCodmodelo());
			empresa = new  Label("empresa", requisicao.getNomeEmpresa());
			cgo = new  Label("cgo", requisicao.getCgo());
			dataSolicitacao = new  Label("dataSolicitacao", FormatUtils.formata(requisicao.getDataSolicitacaoIntencao(), "dd/MM/yyyy"));
			
			add(nomeSolicitante, fornecedor, naturezaDespesa, localEntrega, codModelo, empresa, cgo, dataSolicitacao);
		}
		
	}
	
	private class Valores extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private Label valorTotal, valorDesconto, valorAcrescimo, valorLiquido;
		
		public Valores() {
			super("valores");
			
			valorTotal = new Label("valorTotal", monetario.format(requisicao.getValor()));
			valorDesconto = new Label("valorDesconto", monetario.format(requisicao.getVlrdescontos())); 
			valorAcrescimo = new Label("valorAcrescimo", monetario.format(requisicao.getVlracrescimos()) );
			valorLiquido = new Label("valorLiquido", monetario.format(requisicao.getVlrliqreq()));
			
			add(valorTotal, valorDesconto, valorAcrescimo, valorLiquido);
		}
		
	}
	
	private class Itens extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private ListView<OrReqitensdespesa> itensLista;
		
		public Itens() {
			super("itens");
			
			List<OrReqitensdespesa> itensDespesa = new ArrayList<OrReqitensdespesa>(requisicao.getOrReqitensdespesas());

			itensLista = new ListView<OrReqitensdespesa>("itensLista", itensDespesa) {
				private static final long serialVersionUID = 1L;

				private Label cod, descricao;
				
				@Override
				protected void populateItem(ListItem<OrReqitensdespesa> item) {					
					OrReqitensdespesa itemDespesa = (OrReqitensdespesa) item.getDefaultModelObject();
					cod = new Label("cod", itemDespesa.getCodproduto());
					descricao = new Label("descricao", itemDespesa.getDescricao());	
					item.add(cod, descricao);
				}
			};
			
			add(itensLista);
		}
		
	}
	
	private class Validacao extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Label box, nroNota, conferentes, descarregadores;
		
		public Validacao() {
			super("validacao");
			
			box = new Label("box", (String)FormatUtils.seNuloSubstitui(recebimento.getBox(), ""));
			nroNota = new Label("nroNota", (String)FormatUtils.seNuloSubstitui(recebimento.getNroNota(), ""));
			conferentes = new Label("conferentes", (String)FormatUtils.seNuloSubstitui(recebimento.getConferentes(), ""));
			descarregadores = new Label("descarregadores", (String)FormatUtils.seNuloSubstitui(recebimento.getDescarregadores(), ""));			
			
			add(box, nroNota, conferentes, descarregadores);
		}		
		
	}
	
	private class Divergencias extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Label possuiDivergencias, categoria, descricao;
		
		public Divergencias() {
			super("divergencias");
			
			possuiDivergencias = new Label("possuiDivergencias", recebimento.getIsDivergencia());
			categoria = new Label("categoria", (String) FormatUtils.seNuloSubstitui(recebimento.getTipoDivergencia(), ""));
			descricao = new Label("descricao", (String) FormatUtils.seNuloSubstitui(recebimento.getMotivoOuJustificativaDivergencia(), ""));
			
			add(possuiDivergencias, categoria, descricao);
		}
	}
	
	private class AprovacaoRecebDivergente extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;
		
		private Label solicitante, aprovador, comentarioAprovador;
		
		public AprovacaoRecebDivergente() {
			super("aprovacaoRecebDivergente");
			
			solicitante = new Label("solicitante", solicitacao.getNomeRespPreenchimento()); 
			aprovador = new Label("aprovador", ""); 
			comentarioAprovador = new Label("comentarioAprovador", "");
			
			add(solicitante, aprovador, comentarioAprovador);
			
			if(recebimento.getComentariosAprovRecebimentoDivergente().size() > 0){
				Comentario comentario = recebimento.getComentariosAprovRecebimentoDivergente().get(0);
				aprovador.setDefaultModelObject(comentario.getUsuario().getNomeCompleto());
				comentarioAprovador.setDefaultModelObject(comentario.getTexto());
			}		
			
		}
		
		@Override
		protected void onConfigure() {
			super.onConfigure();
			
			if(recebimento.getComentariosAprovRecebimentoDivergente().size() == 0){
				this.setVisible(false);
			}
		}
		
	}

}
