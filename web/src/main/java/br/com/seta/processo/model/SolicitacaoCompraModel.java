/***
 * 
 * @author Sérgio da Victória
 * 
 */
package br.com.seta.processo.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;
import org.jboss.logging.Logger;

import br.com.seta.processo.bean.Compradores;
import br.com.seta.processo.dominios.Diretoria;
import br.com.seta.processo.dominios.Gerencia;
import br.com.seta.processo.dominios.TipoDespesa;
import br.com.seta.processo.dto.Professional;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.service.ExecuteRestAPI;
import br.com.seta.processo.service.GroupService;



public class SolicitacaoCompraModel implements IClusterable, Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Map<String, List<User>> listGroupsUsers = new HashMap<String, List<User>>();
	private String nomeRespPreenchimento, emailRespPreenchimento, telRespPreenchimento, emailSolicitante, foneSolicitante, nomeSolicitante,  groupSelected, nomeSolicitanteSelected, compradorSelected, descricaoCompra, diretoriaSelected, gerenciaSelected, nomeCompleto, tipoDespesaSelected;
	private String nomeAprovadorHierarquio;
	private String emailAprovadorHierarquio;
	private Long idAprovadorHierarquio;
	private transient User user;
	private transient Professional professional;
	private List<String> comprador= new ArrayList<String>();
	private List<String> diretoria= new ArrayList<String>();
	private List<String> gerencia = new ArrayList<String>();
	private List<String> tipoDespesa = new ArrayList<String>();
	private User userSelected;
	private String grupoCheckInADM;
	

	private String mailCSCJuridicos;
	private String emailJuridicos;
	private String emailsAreaCadastros;
	private String emailsAutomacaos;
	private String emailsAprovadorSubstitutos;
	private Long idUsuarioLogado;
	private Long idGrupoCSC;
	private Long idGrupoCSCIntegracao;
	private Long idGrupoCSCJuridico;
	private Long idGrupoCSCValidacao;
	private Long idResponsavelPreenchimento;
	


	@Inject private GroupService groupService;
	@Inject private Compradores compradores;
	@Inject private ExecuteRestAPI executeRestAPI;
	private static Logger logger = Logger.getLogger(SolicitacaoCompraModel.class);

	public SolicitacaoCompraModel()  {

		CdiContainer.get().getNonContextualManager().inject(this);
		try {
			this.listGroupsUsers = groupService.findGroupsWithUserMapS();
			searchGroups();
			user = (User) Session.get().getAttribute("user");
			User userSearch = (User) Session.get().getAttribute("user");
			this.user = executeRestAPI.retriveUserProfessionalData(userSearch);
			this.nomeRespPreenchimento      = this.user.getFirstname()+" "+this.user.getLastname();
			this.idResponsavelPreenchimento = userSearch.getIdUser();
			this.idUsuarioLogado            = userSearch.getIdUser(); 

			this.professional = this.user.getProfessional_data();

			if (this.professional.getEmail()!=null) {
				this.emailRespPreenchimento = this.professional.getEmail();
			}

			if (this.professional.getPhone_number()!=null) {
				this.telRespPreenchimento = this.professional.getPhone_number();
			}

			this.gerencia    = Gerencia.getValores();
			this.diretoria   = Diretoria.getValores();
			this.tipoDespesa = TipoDespesa.getValores();
			this.comprador   = this.compradores.findCompradoreSWithName();
			findEmails();

		}catch (Exception e){
			logger.error(" E ao tentar montar model",e);
		}


		logger.info("Monta model solicitante com sucesso !");

	}

	public void infoSolicitante() {
		try {
			
			User user = groupService.findUserWithManager(this.userSelected.getIdUser());
			this.emailSolicitante = user.getProfessional_data().getEmail();
			this.foneSolicitante = user.getProfessional_data().getPhone_number();
			this.nomeAprovadorHierarquio = user.getNameManager();
			this.emailAprovadorHierarquio = user.getEmailManager();
			this.idAprovadorHierarquio = user.getIdManager();
			
		}catch (Exception e){
			logger.error("Erra ao tentar buscar informações do solicitante",e);
		}
		
	}
	

	public Map<String, List<User>> getListGroupsUsers() {
		return listGroupsUsers;
	}

	public void setListGroupsUsers(Map<String, List<User>> listGroupsUsers) {
		this.listGroupsUsers = listGroupsUsers;
	}

	public String getNomeRespPreenchimento() {
		return nomeRespPreenchimento;
	}
	public void setNomeRespPreenchimento(String nomeRespPreenchimento) {
		this.nomeRespPreenchimento = nomeRespPreenchimento;
	}
	public String getEmailRespPreenchimento() {
		return emailRespPreenchimento;
	}
	public void setEmailRespPreenchimento(String emailRespPreenchimento) {
		this.emailRespPreenchimento = emailRespPreenchimento;
	}
	public String getTelRespPreenchimento() {
		return telRespPreenchimento;
	}
	public void setTelRespPreenchimento(String telRespPreenchimento) {
		this.telRespPreenchimento = telRespPreenchimento;
	}
	public String getGroupSelected() {
		return groupSelected;
	}
	public void setGroupSelected(String groupSelected) {
		this.groupSelected = groupSelected;
	}

	public List<String> getComprador() {
		return comprador;
	}

	public void setComprador(List<String> comprador) {
		this.comprador = comprador;
	}

	public List<String> getDiretoria() {
		return diretoria;
	}

	public void setDiretoria(List<String> diretoria) {
		this.diretoria = diretoria;
	}

	public List<String> getGerencia() {
		return gerencia;
	}

	public void setGerencia(List<String> gerencia) {
		this.gerencia = gerencia;
	}

	public List<String> getTipoDespesa() {
		return tipoDespesa;
	}

	public void setTipoDespesa(List<String> tipoDespesa) {
		this.tipoDespesa = tipoDespesa;
	}


	public String getCompradorSelected() {
		return compradorSelected;
	}


	public void setCompradorSelected(String compradorSelected) {
		this.compradorSelected = compradorSelected;
	}


	public String getDescricaoCompra() {
		return descricaoCompra;
	}


	public void setDescricaoCompra(String descricaoCompra) {
		this.descricaoCompra = descricaoCompra;
	}


	public String getDiretoriaSelected() {
		return diretoriaSelected;
	}


	public void setDiretoriaSelected(String diretoriaSelected) {
		this.diretoriaSelected = diretoriaSelected;
	}


	public String getGerenciaSelected() {
		return gerenciaSelected;
	}


	public void setGerenciaSelected(String gerenciaSelected) {
		this.gerenciaSelected = gerenciaSelected;
	}


	public String getNomeCompleto() {
		return nomeCompleto;
	}


	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}


	public String getTipoDespesaSelected() {
		return tipoDespesaSelected;
	}


	public void setTipoDespesaSelected(String tipoDespesaSelected) {
		this.tipoDespesaSelected = tipoDespesaSelected;
	}


	public String getEmailSolicitante() {
		return emailSolicitante;
	}


	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}


	public String getFoneSolicitante() {
		return foneSolicitante;
	}


	public void setFoneSolicitante(String foneSolicitante) {
		this.foneSolicitante = foneSolicitante;
	}


	public String getNomeSolicitante() {
		return nomeSolicitante;
	}


	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getNomeAprovadorHierarquio() {
		return nomeAprovadorHierarquio;
	}

	public void setNomeAprovadorHierarquio(String nomeAprovadorHierarquio) {
		this.nomeAprovadorHierarquio = nomeAprovadorHierarquio;
	}

	public String getEmailAprovadorHierarquio() {
		return emailAprovadorHierarquio;
	}

	public void setEmailAprovadorHierarquio(String emailAprovadorHierarquio) {
		this.emailAprovadorHierarquio = emailAprovadorHierarquio;
	}

	public Long getIdAprovadorHierarquio() {
		return idAprovadorHierarquio;
	}

	public void setIdAprovadorHierarquio(Long idAprovadorHierarquio) {
		this.idAprovadorHierarquio = idAprovadorHierarquio;
	}
	
	
	public String getNomeSolicitanteSelected() {
		return nomeSolicitanteSelected;
	}

	public void setNomeSolicitanteSelected(String nomeSolicitanteSelected) {
		this.nomeSolicitanteSelected = nomeSolicitanteSelected;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMailCSCJuridicos() {
		return mailCSCJuridicos;
	}

	public void setMailCSCJuridicos(String mailCSCJuridicos) {
		this.mailCSCJuridicos = mailCSCJuridicos;
	}

	public String getEmailJuridicos() {
		return emailJuridicos;
	}

	public void setEmailJuridicos(String emailJuridicos) {
		this.emailJuridicos = emailJuridicos;
	}

	public String getEmailsAreaCadastros() {
		return emailsAreaCadastros;
	}

	public void setEmailsAreaCadastros(String emailsAreaCadastros) {
		this.emailsAreaCadastros = emailsAreaCadastros;
	}

	public String getEmailsAutomacaos() {
		return emailsAutomacaos;
	}

	public void setEmailsAutomacaos(String emailsAutomacaos) {
		this.emailsAutomacaos = emailsAutomacaos;
	}

	public String getEmailsAprovadorSubstitutos() {
		return emailsAprovadorSubstitutos;
	}

	public void setEmailsAprovadorSubstitutos(String emailsAprovadorSubstitutos) {
		this.emailsAprovadorSubstitutos = emailsAprovadorSubstitutos;
	}

	public Long getIdUsuarioLogado() {
		return idUsuarioLogado;
	}

	public void setIdUsuarioLogado(Long idUsuarioLogado) {
		this.idUsuarioLogado = idUsuarioLogado;
	}

	public User getUserSelected() {
		return userSelected;
	}

	public void setUserSelected(User userSelected) {
		this.userSelected = userSelected;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public Long getIdGrupoCSC() {
		return idGrupoCSC;
	}

	public void setIdGrupoCSC(Long idGrupoCSC) {
		this.idGrupoCSC = idGrupoCSC;
	}

	public Long getIdGrupoCSCIntegracao() {
		return idGrupoCSCIntegracao;
	}

	public void setIdGrupoCSCIntegracao(Long idGrupoCSCIntegracao) {
		this.idGrupoCSCIntegracao = idGrupoCSCIntegracao;
	}

	public Long getIdGrupoCSCJuridico() {
		return idGrupoCSCJuridico;
	}

	public void setIdGrupoCSCJuridico(Long idGrupoCSCJuridico) {
		this.idGrupoCSCJuridico = idGrupoCSCJuridico;
	}

	public Long getIdGrupoCSCValidacao() {
		return idGrupoCSCValidacao;
	}

	public void setIdGrupoCSCValidacao(Long idGrupoCSCValidacao) {
		this.idGrupoCSCValidacao = idGrupoCSCValidacao;
	}

	public GroupService getGroupService() {
		return groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	
	public String getGrupoCheckInADM() {
		return grupoCheckInADM;
	}

	public void setGrupoCheckInADM(String grupoCheckInADM) {
		this.grupoCheckInADM = grupoCheckInADM;
	}

	public Long getIdResponsavelPreenchimento() {
		return idResponsavelPreenchimento;
	}

	public void setIdResponsavelPreenchimento(Long idResponsavelPreenchimento) {
		this.idResponsavelPreenchimento = idResponsavelPreenchimento;
	}

	private void searchGroups() {
		this.idGrupoCSC = groupService.findGrupName("CSC");
		this.idGrupoCSCIntegracao = groupService.findGrupName("CSC Integração");
		this.idGrupoCSCJuridico = groupService.findGrupName("CSC - Juridico");
		this.idGrupoCSCValidacao = groupService.findGrupName("CSC Validação");
		this.grupoCheckInADM = this.idGrupoCSCValidacao+" - CSC Validação";
	}

	private void findEmails() {

		try {
			List<String> strings = groupService.findEmails("CSC - Juridico");
			String mails = "";

			for (Object dto : strings) {
				mails += (String) dto + ", ";
			}
			this.mailCSCJuridicos = mails;

			strings = groupService.findEmails("Juridico Contratos");
			mails = "";

			for (Object dto : strings) {
				mails += (String) dto + ", ";
			}
			this.emailJuridicos = mails;


			strings = groupService.findEmails("Cadastro");
			mails = "";

			for (Object dto : strings) {
				mails += (String) dto + ", ";
			}
			this.emailsAreaCadastros = mails;


			strings = groupService.findEmails("Processos - Automação");
			mails = "";

			for (Object dto : strings) {
				mails += (String) dto + ", ";
			}
			this.emailsAutomacaos = mails;


		}catch (Exception e ) {
			logger.error("Erro ao tentar buscar emails !",e);
		}
	}
	

	@Override
	public String toString() {
		return "SolicitacaoCompraModel [listGroupsUsers=" + listGroupsUsers
				+ ", nomeRespPreenchimento=" + nomeRespPreenchimento
				+ ", emailRespPreenchimento=" + emailRespPreenchimento
				+ ", telRespPreenchimento=" + telRespPreenchimento
				+ ", emailSolicitante=" + emailSolicitante
				+ ", foneSolicitante=" + foneSolicitante + ", nomeSolicitante="
				+ nomeSolicitante + ", groupSelected=" + groupSelected
				+ ", nomeSolicitanteSelected=" + nomeSolicitanteSelected
				+ ", compradorSelected=" + compradorSelected
				+ ", descricaoCompra=" + descricaoCompra
				+ ", diretoriaSelected=" + diretoriaSelected
				+ ", gerenciaSelected=" + gerenciaSelected + ", nomeCompleto="
				+ nomeCompleto + ", tipoDespesaSelected=" + tipoDespesaSelected
				+ ", nomeAprovadorHierarquio=" + nomeAprovadorHierarquio
				+ ", emailAprovadorHierarquio=" + emailAprovadorHierarquio
				+ ", idAprovadorHierarquio=" + idAprovadorHierarquio
				+ ", comprador=" + comprador + ", diretoria=" + diretoria
				+ ", gerencia=" + gerencia + ", tipoDespesa=" + tipoDespesa
				+ ", userSelected=" + userSelected + ", grupoCheckInADM="
				+ grupoCheckInADM + ", mailCSCJuridicos=" + mailCSCJuridicos
				+ ", emailJuridicos=" + emailJuridicos
				+ ", emailsAreaCadastros=" + emailsAreaCadastros
				+ ", emailsAutomacaos=" + emailsAutomacaos
				+ ", emailsAprovadorSubstitutos=" + emailsAprovadorSubstitutos
				+ ", idUsuarioLogado=" + idUsuarioLogado + ", idGrupoCSC="
				+ idGrupoCSC + ", idGrupoCSCIntegracao=" + idGrupoCSCIntegracao
				+ ", idGrupoCSCJuridico=" + idGrupoCSCJuridico
				+ ", idGrupoCSCValidacao=" + idGrupoCSCValidacao
				+ ", idResponsavelPreenchimento=" + idResponsavelPreenchimento
				+ ", groupService=" + groupService + ", compradores="
				+ compradores + ", executeRestAPI=" + executeRestAPI + "]";
	}

	

}
