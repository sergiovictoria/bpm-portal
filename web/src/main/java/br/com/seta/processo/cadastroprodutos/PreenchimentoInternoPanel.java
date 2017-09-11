package br.com.seta.processo.cadastroprodutos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;

import br.com.seta.processo.dto.CadastroProduto;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dto.UserGroup;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.helper.WicketHelper;
import br.com.seta.processo.service.ExecuteRestAPI;

public class PreenchimentoInternoPanel extends Panel {
	private static final long serialVersionUID = 1L;
	
	private static final String AREA_COMERCIAL = "Comercial";
	public static final Boolean BUSCAR_DADOS_RESP_PREENCHIMENTO_DO_USUARIO_DA_SESSAO = true;
	public static final Boolean NAO_BUSCAR_DADOS_RESP_PREENCHIMENTO_DO_USUARIO_DA_SESSAO = false;
	
	@Inject 
	private ExecuteRestAPI restApi;
	
	private TextField<String> nomeRespPreench;
	private TextField<String> emailSolicitante;
	private TextField<String> telefoneSolicitante;
	private TextField<String> area;
	private NomeSolicitanteField nomeSolicitante;
	private DateTextField dataSolicitacao;
	private TextArea<String> descricao;	
	private RadioGroup<Boolean> acordoComercial;
	private TextField<String> nroAcordo;
	
	public PreenchimentoInternoPanel(String id, CadastroProduto cadastroProduto, boolean buscaDadosRespPreenchimentoDoUsuarioDaSessao) {
		super(id);
		
		if(buscaDadosRespPreenchimentoDoUsuarioDaSessao){
			User userProfessionalData = getUserProfessionalData();		
			cadastroProduto.setArea(AREA_COMERCIAL);
			cadastroProduto.setIdRespPreench(getSessionUser().getIdUser());
			cadastroProduto.setNomeRespPreench(userProfessionalData.getFirstname() + " " + userProfessionalData.getLastname());
			cadastroProduto.setEmailSolicitante(userProfessionalData.getProfessional_data().getEmail());
			cadastroProduto.setTelefoneSolicitante(userProfessionalData.getProfessional_data().getPhone_number());
		}
		
		nomeRespPreench = new TextField<String>("nomeRespPreench", new PropertyModel<String>(cadastroProduto, "nomeRespPreench"));
		emailSolicitante = new TextField<String>("emailSolicitante", new PropertyModel<String>(cadastroProduto, "emailSolicitante"));
		telefoneSolicitante = new TextField<String>("telefoneSolicitante", new PropertyModel<String>(cadastroProduto, "telefoneSolicitante"));
		area = new TextField<String>("area", new PropertyModel<String>(cadastroProduto, "area"));
		dataSolicitacao = new DateTextField("dataSolicitacao", new PropertyModel<Date>(cadastroProduto, "dataSolicitacao"), "dd/MM/yyyy HH:mm");
		nomeSolicitante = new NomeSolicitanteField("nomeSolicitante", new PropertyModel<String>(cadastroProduto, "nomeSolicitante"), true);
		descricao = new TextArea<String>("descricao", new PropertyModel<String>(cadastroProduto, "descricao"));		
		acordoComercial = WicketHelper.criaRadioGroup("acordoComercial", new PropertyModel<Boolean>(cadastroProduto, "acordoComercial"),  Arrays.asList(true, false));
		nroAcordo = new TextField<String>("nroAcordo", new PropertyModel<String>(cadastroProduto, "nroAcordo"));
		
		add(nomeRespPreench, emailSolicitante, telefoneSolicitante, area, dataSolicitacao, nomeSolicitante, descricao, acordoComercial, nroAcordo);
		
		nomeRespPreench.setEnabled(false);
		emailSolicitante.setEnabled(false);
		telefoneSolicitante.setEnabled(false);
		dataSolicitacao.setEnabled(false);
		area.setEnabled(false);
	}
	
	@Override
	protected void onConfigure() {
		super.onConfigure();
		
		if(!isEnabled()){
			nomeSolicitante.habilitaSelecao(false);
		}else{
			nomeSolicitante.habilitaSelecao(true);
		}
	}	
	
	
	public PreenchimentoInternoPanel habilitaParaEdicao(boolean habilitado){
		this.setEnabled(habilitado);
		nomeSolicitante.habilitaSelecao(habilitado);
		
		return this;
	}
	
	private User getSessionUser(){
		return (User) Session.get().getAttribute("user");
	}
	
	private User getUserProfessionalData(){
		try {
			return restApi.retriveUserProfessionalData(getSessionUser());
		} catch (IOException | HttpStatusException e) {
			throw new RuntimeException(e);
		}
	}
	
	private List<String> getSolicitantes(){
		try{
			Group grupoComercial = restApi.retriveGroupName(getSessionUser(), AREA_COMERCIAL);
			List<UserGroup>  usuariosComercial =  restApi.retriveUserForGroupList(getSessionUser(), 0, 1000, grupoComercial.getId());
			return paraListaSolicitantes(usuariosComercial);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	private List<String> paraListaSolicitantes(List<UserGroup> usuariosComercial) {
		List<String> solicitantes = new ArrayList<String>(); 
		
		for(UserGroup u : usuariosComercial){
			solicitantes.add(u.getUserName());
		}
		
		return solicitantes;
	}
	
	private class NomeSolicitanteField extends WebMarkupContainer{
		private static final long serialVersionUID = 1L;

		private WebMarkupContainer bootstrapCustomSelect; 
		private DropDownChoice<String> nomeSolicitanteDDC;
		private TextField<String> nomeSolicitanteTF;
		private List<String> solicitantesCache;
		
		public NomeSolicitanteField(String id, IModel<String> model, boolean habilitadoParaSelecao) {
			super(id, model);
			
			nomeSolicitanteDDC = new DropDownChoice<String>("nomeSolicitanteDDC", model, new ArrayList<String>());
			nomeSolicitanteTF = new TextField<String>("nomeSolicitanteTF", model);
			bootstrapCustomSelect = new WebMarkupContainer("bootstrapCustomSelect");
			
			bootstrapCustomSelect.add(nomeSolicitanteDDC);
			add(bootstrapCustomSelect, nomeSolicitanteTF);			
		}

		private NomeSolicitanteField habilitaSelecao(boolean habilitadoParaSelecao) {
			if(habilitadoParaSelecao){
				if(solicitantesCache == null)
					solicitantesCache = getSolicitantes();
				
				nomeSolicitanteDDC.setChoices(solicitantesCache);
				bootstrapCustomSelect.setVisible(true);
				nomeSolicitanteTF.setVisible(false);
			}else{
				bootstrapCustomSelect.setVisible(false);
				nomeSolicitanteTF.setVisible(true);
			}
			
			return this;
		}		
		
	}
}
