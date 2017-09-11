package br.com.seta.processo.suprimentos;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.bonitasoft.engine.exception.BonitaException;

import br.com.seta.processo.dto.OrRequisicao;
import br.com.seta.processo.dto.SolicitacaoIntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.model.SolicitacaoCompraModel;
import br.com.seta.processo.page.BonitaPage;
import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_COMPRA;
import static br.com.seta.processo.constant.VariaveisSolicitacaoIntencaoCompra.SOLICITACAO_INTENCAO_DTO;;

public abstract class SolicitacaoIntencaoCompraTemplate extends BonitaPage {

	private static final long serialVersionUID = 1L; 

	private Form<Void> form;
	private SolicitacaoContainer solicitacaoContainer;
	private SolicitacaoIntencaoCompra solicitacao;
	private OrRequisicao requisicao;
	private SolicitacaoCompraModel model = new SolicitacaoCompraModel();

	public SolicitacaoIntencaoCompraTemplate(PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException	{
		super(pageParameters);
		
		try{
			solicitacao = (SolicitacaoIntencaoCompra) retriveProcessVariable(SOLICITACAO_INTENCAO_COMPRA, SolicitacaoIntencaoCompra.class, new SolicitacaoIntencaoCompra());
			requisicao = (OrRequisicao) retriveProcessVariable(SOLICITACAO_INTENCAO_DTO, OrRequisicao.class, new OrRequisicao());
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
		this.form = new Form<Void>("formSolicitacao");
		this.solicitacaoContainer = new SolicitacaoContainer("solicitacaoContainer", solicitacao);
		this.form.add(this.solicitacaoContainer);
		add(this.form);
	}

	public SolicitacaoIntencaoCompraTemplate(SolicitacaoIntencaoCompra solicitacao, PageParameters pageParameters) throws HttpStatus401Exception, HttpStatus404Exception, ClientProtocolException, GenericHttpStatusException, IOException, BonitaException{
		this(pageParameters);
		this.solicitacao = solicitacao;
	}

	protected SolicitacaoIntencaoCompra getSolicitacao(){
		return this.solicitacao;
	}

	protected SolicitacaoCompraModel getSolicitacaoModel(){
		return this.model;
	}


	protected void addSecaoBotes(Panel panel){
		form.add(panel);
	}

	protected void addSecaoHistorico(Panel panel){
		form.add(panel);
	}

	protected SolicitacaoContainer getSolicitacaoContainer(){
		return this.solicitacaoContainer;
	}

	private class SolicitacaoContainer extends WebMarkupContainer {

		private static final long serialVersionUID = 1L;

		private TextField<String> nomeRespPreenchimento, emailRespPreenchimento, telRespPreenchimento, emailSolicitante, foneSolicitante;
		private DropDownChoice<String> diretoria, gerencia, comprador;
		DropDownChoice<String> areaSolicitante;
		DropDownChoice<User> nomeSolicitante;
		private RadioGroup<String> tipoDespesa;
		private TextArea<String> descricaoCompra;


		IModel<List<String>> mUserGroup = new AbstractReadOnlyModel<List<String>>(){
			private static final long serialVersionUID = 1L;
			@Override
			public List<String> getObject() {
				return new ArrayList<>(model.getListGroupsUsers().keySet());
			}
		};


		IModel<List<User>> mUser = new AbstractReadOnlyModel<List<User>>(){
			private static final long serialVersionUID = 1L;
			@Override
			public List<User> getObject() {
				List<User> usList = model.getListGroupsUsers().get(model.getGroupSelected());
				if (usList==null) {
					usList = Collections.emptyList();
				}
				return usList;
			}
		};


		public SolicitacaoContainer(String id, SolicitacaoIntencaoCompra solicitacao) {
			super(id, new CompoundPropertyModel<SolicitacaoIntencaoCompra>(solicitacao));

			nomeRespPreenchimento = new TextField<String>("nomeRespPreenchimento",new PropertyModel<String>(model, "nomeRespPreenchimento"));
			emailRespPreenchimento = new TextField<String>("emailRespPreenchimento",new PropertyModel<String>(model, "emailRespPreenchimento"));
			telRespPreenchimento = new TextField<String>("telRespPreenchimento",new PropertyModel<String>(model, "telRespPreenchimento"));

			areaSolicitante = new DropDownChoice<>( "areaSolicitante", new PropertyModel<String>(model, "groupSelected"), mUserGroup);
			nomeSolicitante = new DropDownChoice<User>("nomeSolicitante",new PropertyModel<User>(model, "userSelected"), mUser, new ChoiceRenderer<User>("userName"));
			areaSolicitante.setOutputMarkupId(true);

			areaSolicitante.add(new AjaxFormComponentUpdatingBehavior("change"){
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target){
					target.add(nomeSolicitante);
				}
			});

			nomeSolicitante.add(new AjaxFormComponentUpdatingBehavior("change"){
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target){
					try {
						model.infoSolicitante();
					} catch (Exception e) {
					}
					target.add(emailSolicitante);
					target.add(foneSolicitante);
				}
			});
			

			diretoria = new DropDownChoice<String>("diretoria", new PropertyModel<String>(model,"diretoriaSelected"),model.getDiretoria());
			gerencia = new DropDownChoice<String>("gerencia", new PropertyModel<String>(model, "gerenciaSelected"),model.getGerencia());
			tipoDespesa = criaRadioGroup("tipoDespesa", new PropertyModel<String>(model,"tipoDespesaSelected") , model.getTipoDespesa());
			descricaoCompra = new TextArea<String>("descricaoCompra",new PropertyModel<String>(model, "descricaoCompra"));
			emailSolicitante = new TextField<String>("emailSolicitante",new PropertyModel<String>(model, "emailSolicitante"));
			foneSolicitante = new TextField<String>("foneSolicitante",new PropertyModel<String>(model, "foneSolicitante"));
			comprador = new DropDownChoice<String>("comprador", new PropertyModel<String>(model, "compradorSelected"),model.getComprador());
			emailSolicitante.setOutputMarkupId(true);
			foneSolicitante.setOutputMarkupId(true);

			add(nomeRespPreenchimento, emailRespPreenchimento, telRespPreenchimento, nomeSolicitante, areaSolicitante, 
					diretoria, gerencia, comprador, tipoDespesa, emailSolicitante, foneSolicitante, descricaoCompra);
		}

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

	

}
