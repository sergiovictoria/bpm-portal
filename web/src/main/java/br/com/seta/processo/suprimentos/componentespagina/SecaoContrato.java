//package br.com.seta.processo.suprimentos.componentespagina;
//
//import org.apache.wicket.markup.html.WebMarkupContainer;
//import org.apache.wicket.markup.html.basic.Label;
//import org.apache.wicket.markup.html.panel.Panel;
//import org.apache.wicket.markup.repeater.Item;
//import org.apache.wicket.markup.repeater.data.DataView;
//
//import br.com.seta.processo.dto.Contrato;
//import br.com.seta.processo.provider.ContratosAnterioresProvider;
//import br.com.seta.processo.provider.ItemSemContratoProvider;
//
//public class SecaoContrato extends Panel {
//	private static final long serialVersionUID = 1L;
//	
//	private Label dadosFornecedorLbl;
//	
//	private WebMarkupContainer tabelaItemSemContrato;
//	private DataView<Contrato> itensSemContrato;
//	private Label codigoItemLbl;
//	private ItemSemContratoProvider itemSemContratoProvider;
//
//	private WebMarkupContainer tabelaContratosAnteriores;
//	private DataView<Contrato> contratosAnteriores;
//	private Label nomeFornecedorContratoAnteriorLbl;
//	private Label inicioContratoAnteriorLbl;
//	private Label fimContratoAnteriorLbl;
//	private ContratosAnterioresProvider contratoAnteriorProvider;
//	
//	
//	public SecaoContrato(String id) {
//		super(id);
//		setOutputMarkupId(true);
//		
//		itemSemContratoProvider = new ItemSemContratoProvider();
//		itensSemContrato = new DataView<Contrato>("itensSemContrato", itemSemContratoProvider) {
//			private static final long serialVersionUID = 1L;
//			@Override
//			protected void populateItem(final Item<Contrato> item) {
//				final Contrato contrato = (Contrato) item.getDefaultModelObject();
//				item.setOutputMarkupId(true);
//				item.add(new Label("codigoItemLbl", contrato.getNumeroContrato()));
//			}
//		};
//		itensSemContrato.setOutputMarkupId(true);
//		tabelaItemSemContrato.setOutputMarkupId(true);
//		tabelaItemSemContrato.add(itensSemContrato);
//		tabelaItemSemContrato.add(codigoItemLbl);
//		
//		contratoAnteriorProvider = new ContratosAnterioresProvider();
//		contratosAnteriores = new DataView<Contrato>("contratosAnteriores", contratoAnteriorProvider) {
//			private static final long serialVersionUID = 1L;
//			@Override
//			protected void populateItem(final Item<Contrato> item) {
//				final Contrato contrato = (Contrato) item.getDefaultModelObject();
//				item.setOutputMarkupId(true);
//				item.add(new Label("nomeFornecedorContratoAnteriorLbl", contrato.getNomeFornecedor()));
//				item.add(new Label("inicioContratoAnteriorLbl", contrato.getNomeFornecedor()));
//				item.add(new Label("fimContratoAnteriorLbl", contrato.getNomeFornecedor()));
//			}
//		};
//		contratosAnteriores.setOutputMarkupId(true);
//		tabelaContratosAnteriores.setOutputMarkupId(true);
//		tabelaContratosAnteriores.add(contratosAnteriores);
//		tabelaContratosAnteriores.add(nomeFornecedorContratoAnteriorLbl);
//		tabelaContratosAnteriores.add(inicioContratoAnteriorLbl);
//		tabelaContratosAnteriores.add(fimContratoAnteriorLbl);
//		
//		add(dadosFornecedorLbl);
//		add(tabelaItemSemContrato);
//		add(tabelaContratosAnteriores);
//	}
//
//}
