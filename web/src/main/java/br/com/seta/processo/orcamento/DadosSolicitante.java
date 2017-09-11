package br.com.seta.processo.orcamento;

import static br.com.seta.processo.model.GroupUserModel.defaultValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.dto.IntencaoCompra;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.model.DadosSolicitanteModel;
import br.com.seta.processo.model.GroupUserModel;
import br.com.seta.processo.page.InstanciacaoProcessoPage;

public class DadosSolicitante extends InstanciacaoProcessoPage {


	private static final long serialVersionUID = 1L;

	private	GroupUserModel groupUserModel = new GroupUserModel();
	private DadosSolicitanteModel dadosSolicitanteModel = new DadosSolicitanteModel();
	private final WebMarkupContainer DadosSolicitanteContainer = new WebMarkupContainer("DadosSolicitanteContainer");
	private IntencaoCompra intencaoCompra; 
	private static List<String> TIPO_REQUISICAO = Arrays.asList(new String[] { "Produto", "Serviço"});
	private static List<String> DIRETORIAS = Arrays.asList("Administrativo", "Comercial", "Operacional");
	private static List<String> GERENCIAS = Arrays.asList("Diretoria de Controller",
				"FC - Fiscal",
				"Diretoria Relações Financeiras",
				"Diretoria de TI",
				"Diretoria de RH Estratégico",
				"Jurídico",
				"Líderes empresariais - ADM",
				"Comitê Diretivo",
				"Diretoria Operacional",
				"LJ - Custo Geral de loja",
				"Diretoria Logística",
				"Líderes empresariais - OP",
				"Gerência comercial");

	private transient User user = (User) Session.get().getAttribute("user");

	public DadosSolicitante(PageParameters pageParameters)  {
		super(pageParameters);
		
		iniciaIntensaoCompra();
		add(new DadosSolicitanteForm("DadosSolicitanteForm",pageParameters));
		DadosSolicitanteContainer.setOutputMarkupId(true);
		setTituloPagina("Informações do Solicitante");			
	}

	public class DadosSolicitanteForm extends Form<Void> {

		private static final long serialVersionUID = 1L;
		
		final DropDownChoice<String> membros;
		final DropDownChoice<String> usuarios;
		final RadioChoice<String> type;
		private DropDownChoice<String> diretorias, gerencias;
		private AjaxButton proximo;
		private TextField<String> emailSolicitante;
		private TextField<String> telefoneSolicitante;
		private TextArea<String> descricaoCompra;
		
		@SuppressWarnings("unchecked")
		public DadosSolicitanteForm(String id, final PageParameters pageParameters) {
			super(id);

			IModel<List<String>> makeChoices = new AbstractReadOnlyModel<List<String>>() {
				private static final long serialVersionUID = 1L;
				@Override
				public List<String> getObject()	{
					return new ArrayList<>(groupUserModel.getModelsMap().keySet());
				}
			};

			IModel<List<String>> modelChoices = new AbstractReadOnlyModel<List<String>>() {
				private static final long serialVersionUID = 1L;
				@Override
				public List<String> getObject()      {
					List<String> models = groupUserModel.getModelsMap().get(defaultValue);
					if (models == null) {
						models = Collections.emptyList();
					}
					return models;
				}
			};
			
			type = new RadioChoice<String>("TipoRequisicao", new PropertyModel<String>(dadosSolicitanteModel, "tipoRequisicaoSelected"), TIPO_REQUISICAO ).setSuffix(" ");
			membros = new DropDownChoice<>("Membros", new PropertyModel<String>(groupUserModel, "defaultValue"), makeChoices);
			usuarios = (DropDownChoice<String>) new DropDownChoice<String>("Usuarios", new Model<String>(), modelChoices).setOutputMarkupId(true);
			gerencias = new DropDownChoice<String>("gerencias", new PropertyModel<String>(intencaoCompra, "gerencia"), GERENCIAS);
			diretorias = new DropDownChoice<String>("diretorias", new PropertyModel<String>(intencaoCompra, "diretoria"), DIRETORIAS);		
			descricaoCompra = new TextArea<String>("descricaoCompra", new PropertyModel<String>(intencaoCompra, "descricaoCompra"));
			emailSolicitante =  new TextField<String>("emailSolicitante", new PropertyModel<String>(intencaoCompra, "emailSolicitante"));
			telefoneSolicitante = new TextField<String>("telefoneSolicitante", new PropertyModel<String>(intencaoCompra, "foneSolicitante"));			
			proximo = new AjaxButton("Proximo", DadosSolicitanteForm.this) {
				private static final long serialVersionUID = 1L;
				protected void onSubmit(AjaxRequestTarget target, Form<?> form) {
					try {
						intencaoCompra.setAreaSolicitante(membros.getModelObject());
						intencaoCompra.setNomeSolicitante(usuarios.getModelObject());
						intencaoCompra.setTipoIntensao(type.getModelObject());						
						executeCaseWithVariable("intencaoCompra", intencaoCompra);
						redirecionaParaPaginaDeAtividades();						
					}catch (Exception e) {
						throw new RuntimeErrorException(new Error(e));
					}				
				}
			};
			
			membros.setNullValid(false);
			usuarios.setNullValid(false);
			gerencias.setNullValid(false);
			diretorias.setNullValid(false);

			Label Solicitante = new Label("Solicitante",user.getUserName());

			membros.add(new AjaxFormComponentUpdatingBehavior("change")  {
				private static final long serialVersionUID = 1L;
				@Override
				protected void onUpdate(AjaxRequestTarget target) {
					target.add(DadosSolicitanteContainer.get("Usuarios"));
				}
			});

			add(usuarios,membros,type,proximo,Solicitante,DadosSolicitanteContainer);
			DadosSolicitanteContainer.add(usuarios, membros, type, gerencias, diretorias, emailSolicitante, telefoneSolicitante, descricaoCompra, Solicitante);
		}
	}

	private void iniciaIntensaoCompra() {
		intencaoCompra = new IntencaoCompra();
		intencaoCompra.setDiretoria(DIRETORIAS.get(0));
		intencaoCompra.setGerencia(GERENCIAS.get(0));
	}
}	
