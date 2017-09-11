package br.com.seta.processo.suprimentos.componentespagina.abas;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.dominios.TipoDespesa;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoHistorico;

public class SecaoSolicitacao extends Panel {
	private static final long serialVersionUID = 1L;

	private SolicitacaoContainer solicitacaoContainer;
	private SolicitacaoIntencaoCompra solicitacao;
	
	public SecaoSolicitacao(String id, SolicitacaoIntencaoCompra solicitacao) {
		super(id);
		this.solicitacao = solicitacao;
		iniciaPagina();
	}
	
	private class SolicitacaoContainer extends WebMarkupContainer {

		private static final long serialVersionUID = 1L;

		private TextField<String> nomeRespPreenchimento, emailRespPreenchimento, telRespPreenchimento, emailSolicitante, foneSolicitante,
		diretoria, gerencia, comprador, areaSolicitante, nomeSolicitante;
		private RadioGroup<String> tipoDespesa;
		private TextArea<String> descricaoCompra;

		public SolicitacaoContainer(String id, SolicitacaoIntencaoCompra solicitacao) {
			super(id, new CompoundPropertyModel<SolicitacaoIntencaoCompra>(solicitacao));

			nomeRespPreenchimento = new TextField<String>("nomeRespPreenchimento",new PropertyModel<String>(solicitacao, "nomeRespPreenchimento"));
			emailRespPreenchimento = new TextField<String>("emailRespPreenchimento",new PropertyModel<String>(solicitacao, "emailRespPreenchimento"));
			telRespPreenchimento = new TextField<String>("telRespPreenchimento",new PropertyModel<String>(solicitacao, "telRespPreenchimento"));

			areaSolicitante = new TextField<String>( "areaSolicitante", new PropertyModel<String>(solicitacao, "areaSolicitante"));
			nomeSolicitante = new TextField<String>("nomeSolicitante",new PropertyModel<String>(solicitacao, "nomeSolicitante"));
			areaSolicitante.setOutputMarkupId(true);

			diretoria = new TextField<String>("diretoria", new PropertyModel<String>(solicitacao,"diretoria"));
			gerencia = new TextField<String>("gerencia", new PropertyModel<String>(solicitacao, "gerencia"));
			tipoDespesa = criaRadioGroup("tipoDespesa", new PropertyModel<String>(solicitacao,"tipoDespesa") , TipoDespesa.getValores());
			descricaoCompra = new TextArea<String>("descricaoCompra",new PropertyModel<String>(solicitacao, "descricaoCompra"));
			emailSolicitante = new TextField<String>("emailSolicitante",new PropertyModel<String>(solicitacao, "emailSolicitante"));
			foneSolicitante = new TextField<String>("foneSolicitante",new PropertyModel<String>(solicitacao, "foneSolicitante"));
			comprador = new TextField<String>("comprador", new PropertyModel<String>(solicitacao, "comprador"));			

			add(nomeRespPreenchimento, emailRespPreenchimento, telRespPreenchimento, nomeSolicitante, areaSolicitante, 
					diretoria, gerencia, comprador, tipoDespesa, emailSolicitante, foneSolicitante, descricaoCompra);
		}

	}

	private void iniciaPagina() {	
		this.solicitacaoContainer = new SolicitacaoContainer("solicitacaoContainer", this.solicitacao);
		add(this.solicitacaoContainer);
		addOrReplace(new SecaoHistorico("dadosAdicionais", solicitacao.getHistorico()));
		habilitaSolicitacaoContainer(false);
	}

	private <T extends Serializable> RadioGroup<T> criaRadioGroup(String id, IModel<T> model, List<T> valores){
		RadioGroup<T> radioGroup;

		if(model != null){
			radioGroup = new RadioGroup<T>(id, model);
		}else{
			radioGroup = new RadioGroup<T>(id);
		}

		int count = 0;		
		Iterator<T> iterator = valores.iterator();
		while(iterator.hasNext()){
			String idRadio = id + "_" + count;
			count++;			
			radioGroup.add(new Radio<T>(idRadio, new Model<T>(iterator.next())));
		}

		return radioGroup;
	}	
	
	public void habilitaSolicitacaoContainer(boolean habilitado){
		solicitacaoContainer.setEnabled(habilitado);
	}

}
