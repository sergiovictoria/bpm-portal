package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.bean.dao.interfaces.ContratoDao;
import br.com.seta.processo.dto.Contrato;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.ServicoContratado;
import br.com.seta.processo.provider.ContratosAnterioresProvider;

public class SecaoContrato extends Panel {

	private static final long serialVersionUID = 1L;

	@Inject private ContratoDao contratoDao;

	private OrRequisicao requisicao;
	private Contrato contratoVigente;

	private TextField<String> fornecedor;
	private SecaoContratosAnteriores secaoContratosAnterioes;
	private SecaoItensSemContrato secaoItensSemContrato;

	public SecaoContrato(String id, OrRequisicao requisicao) {
		super(id);

		this.requisicao = requisicao;
		this.contratoVigente = contratoDao.buscaContratoVigente(requisicao.getSeqpessoa());

		fornecedor = new TextField<String>("fornecedor", new PropertyModel<String>(requisicao, "seqpessoa"));
		fornecedor.setEnabled(false);
		secaoContratosAnterioes = new SecaoContratosAnteriores("secaoContratosAnteriores");
		ArrayList<ServicoContratado> itensSContrato = null;
		if(this.contratoVigente!=null) {
			itensSContrato = new ArrayList<ServicoContratado>(contratoVigente.getServicoContratado());
		}
		secaoItensSemContrato = new SecaoItensSemContrato("secaoItensSemContrato", itensSContrato);

		add(fornecedor, secaoContratosAnterioes, secaoItensSemContrato);
	}

	private class SecaoItensSemContrato extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private ListView<ServicoContratado> itensSemContrato;

		public SecaoItensSemContrato(String id, List<ServicoContratado> itens) {
			super(id);

			itensSemContrato = new ListView<ServicoContratado>("itensSemContrato", itens) {
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<ServicoContratado> item) {
					ServicoContratado it = (ServicoContratado) item.getDefaultModelObject();

					Label descricaoItem = new Label("descricaoItem", it.getDescricaoItem());
					Label codItem = new Label("codItem", it.getCodigoItem());

					item.add(codItem, descricaoItem);
				}
			};

			add(itensSemContrato);
		}		
	}

	private class SecaoContratosAnteriores extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private ContratosAnterioresDV contratosAnteriores;
		private ContratosAnterioresProvider provider;

		public SecaoContratosAnteriores(String id) {
			super(id);

			provider = new ContratosAnterioresProvider(requisicao.getSeqpessoa());
			contratosAnteriores = new ContratosAnterioresDV("contratosAnteriores", provider);

			add(contratosAnteriores);
		}

	}

	private class ContratosAnterioresDV extends DataView<Contrato>{
		private static final long serialVersionUID = 1L;

		protected ContratosAnterioresDV(String id, IDataProvider<Contrato> dataProvider) {
			super(id, dataProvider);
		}

		@Override
		protected void populateItem(Item<Contrato> item) {
			Contrato contrato = (Contrato) item.getDefaultModelObject();

			Label fornecedor = new Label("contrato", contrato.getNomeFornecedor());
			Label inicioContrato = new Label("inicioContrato", contrato.getDataInicioContrato());
			Label fimContrato = new Label("fimContrato", contrato.getDataInicioContrato());

			item.add(fornecedor, inicioContrato, fimContrato);			
		}

	}

}
