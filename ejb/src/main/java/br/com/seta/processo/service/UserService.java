package br.com.seta.processo.service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.ProfileAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.ContactDataCreator;
import org.bonitasoft.engine.identity.ContactDataUpdater;
import org.bonitasoft.engine.identity.UserCreator;
import org.bonitasoft.engine.identity.UserSearchDescriptor;
import org.bonitasoft.engine.identity.UserUpdater;
import org.bonitasoft.engine.identity.UserWithContactData;
import org.bonitasoft.engine.profile.ProfileMember;
import org.bonitasoft.engine.profile.ProfileMemberCreator;
import org.bonitasoft.engine.search.SearchFilterOperation;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.search.impl.SearchFilter;
import org.bonitasoft.engine.session.APISession;
import org.jboss.logging.Logger;

import br.com.seta.processo.constant.MemberType;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.interceptor.LogInterceptor;

import com.google.gson.reflect.TypeToken;

@Stateless(name = "UserService")
@LocalBean
@Interceptors({LogInterceptor.class})
public class UserService extends BonitaService {

	private static final String actorMemberApiUrl = "/bonita/API/bpm/actorMember";

	@Inject
	private Logger logger;

	private User user = new User();


	@PostConstruct
	public void init() {
		this.user.setUserName("install");
		this.user.setPassword("install");
		logger.info("Acessando EJB - Grupos");
	}


	public UserService() {
		this.user.setUserName("install");
		this.user.setPassword("install");
	}

	public List<User> listaUsuariosPorAtor(User user, TaskProcess processo, Actor ator, MemberType memberType) throws BonitaException {
		String url = getApiActorMemberUrl();
		url = url + "?c=100&d=user_id&f=process_id%3D" +
				processo.getId() + 
				"&f=actor_id%3D" + 
				ator.getId() +
				"&f=member_type%3D" +
				memberType.name() +
				"&p=0";

		List<User> usuarios = executeGetRequestForUserList(user, url);
		return usuarios;
	}

	private List<User> executeGetRequestForUserList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listUserType = new TypeToken<List<User>>() {}.getType();
			List<User> usuarios = getGson().fromJson(responseBody, listUserType);
			return usuarios;
		} catch (HttpStatus401Exception | 
				HttpStatus404Exception | 
				GenericHttpStatusException | 
				IOException e) {
			throw new BonitaException(e);
		}
	}
	
	
	
	
	
	
	


	/*****
	 *  
	 *  Busca uma lista de Usuários atravez do id do grupo, trazendo informações detalhadas do Usuário
	 * 
	 * @author Sérgio da Victória
	 * @param idGroup
	 * @return Retorno uma lita de usuários
	 * @throws BonitaException 
	 * 
	 */
	public List<br.com.seta.processo.dto.User> findUserS(long groupID) throws BonitaException {

		logger.info(" Buscando lista de Usuários  ");
		List<br.com.seta.processo.dto.User> userList = new ArrayList<br.com.seta.processo.dto.User>();
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 1000);
		builder.filter(UserSearchDescriptor.GROUP_ID, groupID);
		SearchResult<org.bonitasoft.engine.identity.User> userResults = identityAPI.searchUsers(builder.done());
		List<org.bonitasoft.engine.identity.User> users = userResults.getResult();

		for (org.bonitasoft.engine.identity.User dto : users) {
			br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
			addUser.setFirstname(dto.getFirstName());
			addUser.setLastname(dto.getLastName());
			addUser.setIdUser(dto.getId());
			addUser.setGroupID(groupID);
			addUser.setUserName(dto.getUserName());
			UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
			if(withContactData.getContactData() != null){
				if (withContactData.getContactData().getEmail() != null ) {
					addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
				}
				if (withContactData.getContactData().getPhoneNumber()!=null) {
					addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
				}
			}			
			userList.add(addUser);
		}

		doTenantLogout(session);
		return userList;

	}
	
	public List<br.com.seta.processo.dto.User> findUsersByMembership(long groupID, long roleID) throws BonitaException {

		logger.info(" Buscando lista de Usuários  ");
		List<br.com.seta.processo.dto.User> userList = new ArrayList<br.com.seta.processo.dto.User>();
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 1000);
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		
		filters.add(new SearchFilter(UserSearchDescriptor.GROUP_ID, SearchFilterOperation.EQUALS, groupID));
		filters.add(new SearchFilter(UserSearchDescriptor.ROLE_ID, SearchFilterOperation.EQUALS, roleID));
		builder.setFilters(filters);
		SearchResult<org.bonitasoft.engine.identity.User> userResults = identityAPI.searchUsers(builder.done());
		List<org.bonitasoft.engine.identity.User> users = userResults.getResult();

		for (org.bonitasoft.engine.identity.User dto : users) {
			br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
			addUser.setFirstname(dto.getFirstName());
			addUser.setLastname(dto.getLastName());
			addUser.setIdUser(dto.getId());
			addUser.setGroupID(groupID);
			addUser.setUserName(dto.getUserName());
			UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
			if (withContactData.getContactData().getEmail() != null ) {
				addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
			}
			if (withContactData.getContactData().getPhoneNumber()!=null) {
				addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
			}
			userList.add(addUser);
		}

		doTenantLogout(session);
		return userList;

	}

	public List<br.com.seta.processo.dto.User> findUsersByFirstName(String name) throws BonitaException {

		logger.info(" Buscando lista de Usuários  ");
		List<br.com.seta.processo.dto.User> userList = new ArrayList<br.com.seta.processo.dto.User>();
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 1000);
		builder.filter(UserSearchDescriptor.FIRST_NAME, name);
		SearchResult<org.bonitasoft.engine.identity.User> userResults = identityAPI.searchUsers(builder.done());
		List<org.bonitasoft.engine.identity.User> users = userResults.getResult();

		for (org.bonitasoft.engine.identity.User dto : users) {
			br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
			addUser.setFirstname(dto.getFirstName());
			addUser.setLastname(dto.getLastName());
			addUser.setIdUser(dto.getId());
			addUser.setUserName(dto.getUserName());
			UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
			if (withContactData.getContactData().getEmail() != null ) {
				addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
			}
			if (withContactData.getContactData().getPhoneNumber()!=null) {
				addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
			}
			userList.add(addUser);
		}

		doTenantLogout(session);
		return userList;

	}

	public List<br.com.seta.processo.dto.User> findUsersByFirstAndLastName(String firstName, String lastName) throws BonitaException {

		logger.info(" Buscando lista de Usuários  ");
		List<br.com.seta.processo.dto.User> userList = new ArrayList<br.com.seta.processo.dto.User>();
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 10000);
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		
		filters.add(new SearchFilter(UserSearchDescriptor.FIRST_NAME, SearchFilterOperation.EQUALS, firstName));
		filters.add(new SearchFilter(UserSearchDescriptor.LAST_NAME, SearchFilterOperation.EQUALS, lastName));
		builder.setFilters(filters);
		SearchResult<org.bonitasoft.engine.identity.User> userResults = identityAPI.searchUsers(builder.done());
		List<org.bonitasoft.engine.identity.User> users = userResults.getResult();

		for (org.bonitasoft.engine.identity.User dto : users) {
			br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
			addUser.setFirstname(dto.getFirstName());
			addUser.setLastname(dto.getLastName());
			addUser.setIdUser(dto.getId());
			addUser.setUserName(dto.getUserName());
			UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
			if (withContactData != null && withContactData.getContactData() != null && withContactData.getContactData().getEmail() != null ) {
				addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
			}
			if (withContactData != null && withContactData.getContactData() != null && withContactData.getContactData().getPhoneNumber()!=null) {
				addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
			}
			userList.add(addUser);
		}

		doTenantLogout(session);
		return userList;

	}
	
	public org.bonitasoft.engine.identity.User findUserById(long idUser) throws BonitaException {
		logger.info(" Chamando serviço  findUserById...  ");
		org.bonitasoft.engine.identity.User user = null;
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,10000).filter(UserSearchDescriptor.ID, idUser).done();
		SearchResult<org.bonitasoft.engine.identity.User> searchResult = identityAPI.searchUsers(searchOptions);
		user = searchResult.getResult().get(0);
		doTenantLogout(session);
		return user;
	}
	
	
	public br.com.seta.processo.dto.User findUserById(String userName) throws BonitaException {
		logger.info(" Chamando serviço  findUserById...  ");
		org.bonitasoft.engine.identity.User user = null;
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,10000).filter(UserSearchDescriptor.USER_NAME, userName).done();
		SearchResult<org.bonitasoft.engine.identity.User> searchResult = identityAPI.searchUsers(searchOptions);
		user = searchResult.getResult().get(0);
		
		br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
		addUser.setFirstname(user.getFirstName());
		addUser.setLastname(user.getLastName());
		addUser.setIdUser(user.getId());
		addUser.setUserName(user.getUserName());
		UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(user.getId());
		if (withContactData != null && withContactData.getContactData() != null && withContactData.getContactData().getEmail() != null ) {
			addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
		}
		if (withContactData != null && withContactData.getContactData() != null && withContactData.getContactData().getPhoneNumber()!=null) {
			addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
		}
		doTenantLogout(session);
		return addUser;
	}
	
	
	/*****
	 *  
	 *  Busca uma lista de Usuários atravez do id do grupo, trazendo informações detalhadas do Usuário
	 * 
	 * @author Sérgio da Victória
	 * @return Retorno uma lita de usuários
	 * @throws BonitaException 
	 * 
	 */
	public List<br.com.seta.processo.dto.User> findUserS( ) throws BonitaException {

		logger.info(" Buscando lista de Usuários  ");
		List<br.com.seta.processo.dto.User> userList = new ArrayList<br.com.seta.processo.dto.User>();
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		final SearchOptionsBuilder builder = new SearchOptionsBuilder(0, 1000);
		SearchResult<org.bonitasoft.engine.identity.User> userResults = identityAPI.searchUsers(builder.done());
		List<org.bonitasoft.engine.identity.User> users = userResults.getResult();

		for (org.bonitasoft.engine.identity.User dto : users) {
			br.com.seta.processo.dto.User addUser = new br.com.seta.processo.dto.User();
			addUser.setFirstname(dto.getFirstName());
			addUser.setLastname(dto.getLastName());
			addUser.setIdUser(dto.getId());
			addUser.setGroupID(dto.getManagerUserId());
		
			UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
			if (withContactData != null && withContactData.getContactData()!= null && withContactData.getContactData().getEmail() != null ) {
				addUser.setPersonnal_data_email(withContactData.getContactData().getEmail().toString());
			}
			if (withContactData != null && withContactData.getContactData()!= null && withContactData.getContactData().getPhoneNumber()!=null) {
				addUser.setPersonnal_data_phone_number(withContactData.getContactData().getPhoneNumber());
			}
			userList.add(addUser);
		}

		doTenantLogout(session);
		return userList;

	}

	public static void main(String[] args) throws BonitaException {
		UserService g = new UserService();
		List<User> users = g.findUserS(33);
		for (User dto : users) {
			System.out.println(" dto "+dto.getNomeCompleto()+"  "+dto.getGroupID());
		}

	}

	private String getApiActorMemberUrl() {
		return getBonitaURI() + actorMemberApiUrl;
	}

	public long createUser(User user) throws BonitaException {
		logger.info(" Chamando serviço  createUser...  ");
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		//UserCreator(String name, String password)
		UserCreator uc = new UserCreator(user.getUserName(), user.getPassword());
		
		uc.setFirstName(user.getFirstname());
		uc.setLastName(user.getLastname());
		uc.setEnabled(true);
		
		if(user.getManager_id() != null)
			uc.setManagerUserId(Long.parseLong(user.getManager_id()));
		
		if((user.getPersonnal_data_email() != null && !user.getPersonnal_data_email().isEmpty()) 
				|| (user.getProfessional_data() != null && user.getProfessional_data().getEmail() != null && !user.getProfessional_data().getEmail().isEmpty())) 
		{
			ContactDataCreator cdc = new ContactDataCreator();
			
			if(user.getPersonnal_data_email() != null && !user.getPersonnal_data_email().isEmpty())
				cdc.setEmail(user.getPersonnal_data_email());
			if(user.getProfessional_data() != null && user.getProfessional_data().getEmail() != null && !user.getProfessional_data().getEmail().isEmpty())
				cdc.setEmail(user.getProfessional_data().getEmail());
			
			uc.setProfessionalContactData(cdc);
			uc.setPersonalContactData(cdc);
		}
		
		org.bonitasoft.engine.identity.User usr = identityAPI.createUser(uc);
		
		doTenantLogout(session);
		return usr.getId();
	}
		
	public void updateUser(User user) throws BonitaException {
		logger.info(" Chamando serviço  updateUser...  ");
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		//UserCreator(String name, String password)
		UserUpdater uc = new UserUpdater();
		
		uc.setUserName(user.getUserName());
		uc.setPassword(user.getPassword());
		uc.setFirstName(user.getFirstname());
		uc.setLastName(user.getLastname());
		uc.setEnabled(true);
		
		if(user.getManager_id() != null)
			uc.setManagerId(Long.parseLong(user.getManager_id()));
		
		if((user.getPersonnal_data_email() != null && !user.getPersonnal_data_email().isEmpty()) 
				|| (user.getProfessional_data() != null && user.getProfessional_data().getEmail() != null && !user.getProfessional_data().getEmail().isEmpty())) 
		{
			ContactDataUpdater cdc = new ContactDataUpdater();
			
			if(user.getPersonnal_data_email() != null && !user.getPersonnal_data_email().isEmpty())
				cdc.setEmail(user.getPersonnal_data_email());
			if(user.getProfessional_data() != null && user.getProfessional_data().getEmail() != null && !user.getProfessional_data().getEmail().isEmpty())
				cdc.setEmail(user.getProfessional_data().getEmail());
			
			uc.setProfessionalContactData(cdc);
			uc.setPersonalContactData(cdc);
		}
		
		org.bonitasoft.engine.identity.User usr = identityAPI.updateUser(user.getId(), uc);
		doTenantLogout(session);
	}
	
	
	public long inserProfile(long userId, long profileId) throws BonitaException{
		logger.info(" Chamando serviço  inserProfile...  ");
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		
		ProfileMemberCreator pmc = new ProfileMemberCreator(profileId);
		pmc.setUserId(userId);
		
		ProfileAPI profileAPI = TenantAPIAccessor.getProfileAPI(session);
		ProfileMember pm = profileAPI.createProfileMember(pmc);
		
		doTenantLogout(session);
		return pm.getId();
	}
	
	public void updateProfileUser(long profileUserId, long userId, long profileId) throws BonitaException{
		logger.info(" Chamando serviço  updateProfileUser...  ");
		APISession session = doTenantLogin(this.user.getUserName(), this.user.getPassword());
		
		ProfileMemberCreator pmc = new ProfileMemberCreator(profileId);
		pmc.setUserId(userId);
		
		ProfileAPI profileAPI = TenantAPIAccessor.getProfileAPI(session);
		profileAPI.deleteProfileMember(profileId);
		profileAPI.createProfileMember(pmc);
		
		doTenantLogout(session);
	}

}
