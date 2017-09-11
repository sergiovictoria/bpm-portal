package br.com.seta.processo.provider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import br.com.seta.processo.consultas.cadastrofornecedores.ConsultaCadastroFornecedor;
import br.com.seta.processo.consultas.produtos.ConsultaProdutos;
import br.com.seta.processo.consultas.suprimentos.ConsultaIntencoesCompra;
import br.com.seta.processo.gestaofilas.GestaoFilas;
import br.com.seta.processo.model.RotuloEWebPage;
import br.com.seta.processo.solicitacaopagamento.ConsultaSolicitacaoPagamento;

/**
 * 
 * @author Hromenique Cezniowscki Leite Batista
 *
 */
public class ConsultasProvider implements  IDataProvider<RotuloEWebPage>{

	private static final long serialVersionUID = 1L;

	private List<RotuloEWebPage> servicos;
	
	public ConsultasProvider() {
		this.servicos = recuperaServicos();
	}
	
	@Override
	public Iterator<? extends RotuloEWebPage> iterator(long first, long count) {
		return this.servicos.iterator();		
	}

	@Override
	public long size() {
		return this.servicos.size();
	}

	@Override
	public IModel<RotuloEWebPage> model(final RotuloEWebPage servico) {
		return new LoadableDetachableModel<RotuloEWebPage>() {
			private static final long serialVersionUID = 1L;
			@Override
			protected RotuloEWebPage load() {
				return servico;
			}
		};
	}

	@Override
	public void detach() {
		
	}
	
	private List<RotuloEWebPage> recuperaServicos(){
		RotuloEWebPage servico = new RotuloEWebPage("Solicitação de Pagamento", ConsultaSolicitacaoPagamento.class);
		RotuloEWebPage filas = new RotuloEWebPage("Gestão de Filas", GestaoFilas.class);
		RotuloEWebPage consultaCadastroProduto = new RotuloEWebPage("Consulta Cadastro de Produto", ConsultaProdutos.class);
		RotuloEWebPage consultaCadastroFornecedor = new RotuloEWebPage("Consulta Cadastro de Fornecedor", ConsultaCadastroFornecedor.class);
		RotuloEWebPage consultaIntencaoCompra = new RotuloEWebPage("Consulta Intenções de Compra", ConsultaIntencoesCompra.class);
		
		ArrayList<RotuloEWebPage> servicos = new ArrayList<RotuloEWebPage>();
		servicos.add(servico);
		servicos.add(filas);
		servicos.add(consultaCadastroProduto);
		servicos.add(consultaCadastroFornecedor);
		servicos.add(consultaIntencaoCompra);
		
		return servicos;
	}
}
