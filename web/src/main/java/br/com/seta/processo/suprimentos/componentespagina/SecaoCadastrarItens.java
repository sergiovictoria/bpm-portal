package br.com.seta.processo.suprimentos.componentespagina;

import java.math.BigDecimal;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;

import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.provider.CadastrarItensProvider;

public class SecaoCadastrarItens extends Panel {
	private static final long serialVersionUID = 1L;
	
	private WebMarkupContainer tabelaCadastrarItensSelecionados;
	private DataView<Contrato> cadastrarItensSelecionados;
	private TextField<String> codproduto;
	private TextField<String> descricao;
	private TextField<Integer> cfop;
	private TextField<BigDecimal> quantidade;
	private TextField<Object> vlritem;
	private Label vlrTotalItem;
	private CheckBox checkboxCadastrarItens;
	private CadastrarItensProvider cadastrarItensProvider;
	
	public SecaoCadastrarItens(String id) {
		super(id);
		setOutputMarkupId(true);
		
		cadastrarItensProvider = new CadastrarItensProvider();
		cadastrarItensSelecionados = new DataView<Contrato>("cadastrarItensSelecionados", cadastrarItensProvider) {
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(final Item<Contrato> item) {
				final Contrato contrato = (Contrato) item.getDefaultModelObject();
				item.setOutputMarkupId(true);
				item.add(new Label("codigoItemLbl", contrato.getNumeroContrato()));
			}
		};
		cadastrarItensSelecionados.setOutputMarkupId(true);
		tabelaCadastrarItensSelecionados.setOutputMarkupId(true);
		
		tabelaCadastrarItensSelecionados.add(cadastrarItensSelecionados);
		tabelaCadastrarItensSelecionados.add(codproduto);
		tabelaCadastrarItensSelecionados.add(descricao);
		tabelaCadastrarItensSelecionados.add(cfop);
		tabelaCadastrarItensSelecionados.add(quantidade);
		tabelaCadastrarItensSelecionados.add(vlritem);
		tabelaCadastrarItensSelecionados.add(vlrTotalItem);
		tabelaCadastrarItensSelecionados.add(checkboxCadastrarItens);
		
		add(cadastrarItensSelecionados);
		add(tabelaCadastrarItensSelecionados);
	}

}
