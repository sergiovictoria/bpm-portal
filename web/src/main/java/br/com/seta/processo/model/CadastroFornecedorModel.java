package br.com.seta.processo.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.http.client.ClientProtocolException;
import org.apache.wicket.Session;
import org.apache.wicket.cdi.CdiContainer;
import org.apache.wicket.util.io.IClusterable;
import org.hibernate.validator.constraints.NotBlank;

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Professional;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dto.UserGroup;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatusException;
import br.com.seta.processo.service.ExecuteRestAPI;

public class CadastroFornecedorModel implements IClusterable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ExecuteRestAPI executeRestAPI;
	
	private String nomeSolicitante;
	private String nomeRespPreench;
	private String emailSolicitante;
	private String telefoneSolicitante;
	private String area;
	private List<String> solicitantes = new ArrayList<String>(0);
	private String solicitantesSelected;
	@NotBlank(message="A descrição é obrigatória")
	private String descricao;
	private transient User user;
	private transient Professional professional;
	
	
		
	public CadastroFornecedorModel() throws HttpStatus401Exception, ClientProtocolException, IOException, HttpStatusException {
		CdiContainer.get().getNonContextualManager().inject(this);
		User userSearch = (User) Session.get().getAttribute("user");
		this.user = executeRestAPI.retriveUserProfessionalData(userSearch);
		Group group = executeRestAPI.retriveGroupName(userSearch, "Comercial");
		List<UserGroup>  userGroups =  executeRestAPI.retriveUserForGroupList(userSearch, 0, 1000, group.getId());
		for (UserGroup userGroup : userGroups) {
			this.solicitantes.add(userGroup.getUserName());
		}
		this.area = "Comercial";
		this.solicitantesSelected = this.solicitantes.get(0); 
		this.professional = this.user.getProfessional_data();
		this.nomeRespPreench = this.user.getFirstname()+" "+this.user.getLastname();
		this.emailSolicitante = this.professional.getEmail();
		this.telefoneSolicitante = this.professional.getPhone_number();
	}



	public String getNomeSolicitante() {
		return nomeSolicitante;
	}


	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}


	public String getNomeRespPreench() {
		return nomeRespPreench;
	}


	public void setNomeRespPreench(String nomeRespPreench) {
		this.nomeRespPreench = nomeRespPreench;
	}


	public String getEmailSolicitante() {
		return emailSolicitante;
	}


	public void setEmailSolicitante(String emailSolicitante) {
		this.emailSolicitante = emailSolicitante;
	}


	public String getTelefoneSolicitante() {
		return telefoneSolicitante;
	}


	public void setTelefoneSolicitante(String telefoneSolicitante) {
		this.telefoneSolicitante = telefoneSolicitante;
	}


	public String getArea() {
		return area;
	}


	public void setArea(String area) {
		this.area = area;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Professional getProfessional() {
		return professional;
	}


	public void setProfessional(Professional professional) {
		this.professional = professional;
	}



	public List<String> getSolicitantes() {
		return solicitantes;
	}



	public void setSolicitantes(List<String> solicitantes) {
		this.solicitantes = solicitantes;
	}



	public String getSolicitantesSelected() {
		return solicitantesSelected;
	}



	public void setSolicitantesSelected(String solicitantesSelected) {
		this.solicitantesSelected = solicitantesSelected;
	}



	public String getDescricao() {
		return descricao;
	}



	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
		
	

	

	


}
