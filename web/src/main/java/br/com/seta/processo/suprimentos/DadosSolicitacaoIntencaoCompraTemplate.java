package br.com.seta.processo.suprimentos;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dominios.TipoDespesa;
import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.page.BonitaPage;
import br.com.seta.processo.suprimentos.componentespagina.secoes.SecaoHistorico;
import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA;
import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO;;

public abstract class DadosSolicitacaoIntencaoCompraTemplate extends BonitaPage {

	private static final long serialVersionUID = 1L;

	private Form<Void> form;
	private SolicitacaoContainer solicitacaoContainer;
	private SolicitacaoIntencaoCompra solicitacao;
	private OrRequisicao requisicao;

	private SecaoHistorico secaoHistorico;

	public DadosSolicitacaoIntencaoCompraTemplate(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException	{
		super(pageParameters);
		
		try{
			solicitacao = (SolicitacaoIntencaoCompra) retriveProcessVariable(SOLICITACAO_INTENCAO_COMPRA, SolicitacaoIntencaoCompra.class, new SolicitacaoIntencaoCompra());
			requisicao = (OrRequisicao) retriveProcessVariable(SOLICITACAO_INTENCAO_DTO, OrRequisicao.class, new OrRequisicao());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		iniciaPagina();
	}

	public DadosSolicitacaoIntencaoCompraTemplate(SolicitacaoIntencaoCompra solicitacao, PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException, BonitaException{
		super(pageParameters);
		this.solicitacao = solicitacao;
		iniciaPagina();
	}
	
	public DadosSolicitacaoIntencaoCompraTemplate exibeSecaoHistorico(boolean visivel){
		this.secaoHistorico.setVisible(visivel);
		return  this;
	}

	protected SolicitacaoIntencaoCompra getSolicitacao(){
		return this.solicitacao;
	}

	protected void addSecaoBotes(Panel panel){
		form.add(panel);
	}

	protected void addDadosAdicionais(Panel panel){
		form.addOrReplace(panel);
	}

	protected SolicitacaoContainer getSolicitacaoContainer(){
		return this.solicitacaoContainer;
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
		this.form = new Form<Void>("formSolicitacao");
		this.solicitacaoContainer = new SolicitacaoContainer("solicitacaoContainer", solicitacao);
		this.form.add(this.solicitacaoContainer);
		add(this.form);
		
		secaoHistorico = new SecaoHistorico("dadosAdicionais", getSolicitacao().getHistorico());
		addDadosAdicionais(secaoHistorico);
		habilitaSolicitacaoContainer(false);
		secaoHistorico.setVisible(false);
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

	public OrRequisicao getRequisicao() {
		return requisicao;
	}

	public void setRequisicao(OrRequisicao requisicao) {
		this.requisicao = requisicao;
	}

	public void setSolicitacao(SolicitacaoIntencaoCompra solicitacao) {
		this.solicitacao = solicitacao;
	}
	
	public void habilitaSolicitacaoContainer(boolean habilitado){
		solicitacaoContainer.setEnabled(habilitado);
	}

	

}
