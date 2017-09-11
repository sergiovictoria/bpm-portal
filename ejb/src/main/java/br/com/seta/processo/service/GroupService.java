package br.com.seta.processo.service;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import org.bonitasoft.engine.api.IdentityAPI;
import org.bonitasoft.engine.api.TenantAPIAccessor;
import org.bonitasoft.engine.exception.BonitaException;
import org.bonitasoft.engine.identity.GroupCreator;
import org.bonitasoft.engine.identity.GroupCriterion;
import org.bonitasoft.engine.identity.GroupSearchDescriptor;
import org.bonitasoft.engine.identity.GroupUpdater;
import org.bonitasoft.engine.identity.UserWithContactData;
import org.bonitasoft.engine.search.Order;
import org.bonitasoft.engine.search.SearchFilterOperation;
import org.bonitasoft.engine.search.SearchOptions;
import org.bonitasoft.engine.search.SearchOptionsBuilder;
import org.bonitasoft.engine.search.SearchResult;
import org.bonitasoft.engine.search.impl.SearchFilter;
import org.bonitasoft.engine.session.APISession;
import org.jboss.logging.Logger;

import com.google.gson.reflect.TypeToken;

import br.com.seta.processo.constant.MemberType;
import br.com.seta.processo.dto.Actor;
import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.Professional;
import br.com.seta.processo.dto.TaskProcess;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dto.UserGroup;
import br.com.seta.processo.exceptions.GenericHttpStatusException;
import br.com.seta.processo.exceptions.HttpStatus401Exception;
import br.com.seta.processo.exceptions.HttpStatus404Exception;
import br.com.seta.processo.exceptions.RemoteException;
import br.com.seta.processo.interceptor.LogInterceptor;


@Stateless(name = "GroupService")
@LocalBean
@Interceptors({LogInterceptor.class})
public class GroupService extends BonitaService {

	@Inject private Logger logger;
	@Inject private UserService userService;



	private User user = new User();

	@PostConstruct
	public void init() {
		this.user.setUserName("install");
		this.user.setPassword("install");
		logger.info("Acessando EJB - Grupos");
	}


	private static final String actorMemberApiUrl = "/bonita/API/bpm/actorMember";
	private static final String userApiUrl = "/bonita/API/identity/user";
	private static final String groupApiUrl = "/bonita/API/identity/group";

	public List<Group> listaGruposPorAtor(User user, TaskProcess processo, Actor ator, MemberType memberType) throws BonitaException {
		String url = getApiActorMemberUrl();
		url = url + "?c=100&d=user_id&f=process_id%3D" +
				processo.getId() + 
				"&f=actor_id%3D" + 
				ator.getId() +
				"&f=member_type%3D" +
				memberType.name() +
				"&p=0";

		List<Group> groups = executeGetRequestForGroupList(user, url);
		return groups;
	}

	public List<User> listaUsuariosPorGrupo(User user, Group grupo) throws BonitaException {

		List<User> usuariosRecuperados = new ArrayList<User>();

		//- Busca Usuarios no Grupo
		String url = getApiUserUrl();		
		retrieveUserGroup(user, grupo, url);
		usuariosRecuperados.addAll(retrieveUserGroup(user, grupo, url));

		//- Busca Usuarios no SubGrupo
		List<Group> usersSubGroup = listaSubgruposPorGrupos(user, grupo);
		for (Group group : usersSubGroup) {
			usuariosRecuperados.addAll(retrieveUserGroup(user, group, url));
		}

		return usuariosRecuperados;
	}

	private List<User> retrieveUserGroup(User user, Group grupo, String url) throws BonitaException {
		url = url + 
				"?p=0&c=100&o=lastname%20ASC&f=enabled%3dtrue&f=group_id%3d" +
				grupo.getId();
		List<User> usersGroup = executeGetRequestForUserGroupList(user, url);
		if(usersGroup.isEmpty()) {
			url = url + 
					"?p=0&c=100&o=lastname%20ASC&f=enabled%3dtrue&f=group_id%3d" +
					grupo.getGroup_id();
			usersGroup = executeGetRequestForUserGroupList(user, url);
		}
		return usersGroup;
	}

	private List<Group> listaSubgruposPorGrupos(User user, Group grupo) throws BonitaException {
		String url = getApiGroupUrl();
		url = url +
				"?p=0&c=100&f=parent_path%3d" +
				grupo.getParent_path();
		List<Group> groups = executeGetRequestForGroupList(user, url);
		return groups;
	}


	private List<User> executeGetRequestForUserGroupList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listUserGroupType = new TypeToken<List<User>>() {}.getType();
			List<User> usersGroup = getGson().fromJson(responseBody, listUserGroupType);
			return usersGroup;
		} catch (HttpStatus401Exception | 
				HttpStatus404Exception | 
				GenericHttpStatusException | 
				IOException e) {
			throw new BonitaException(e);
		}
	}

	private List<Group> executeGetRequestForGroupList(User user, String processUri) throws BonitaException {
		try {
			String responseBody = executeGetRequest(user, processUri);
			Type listGroupType = new TypeToken<List<Group>>() {}.getType();
			List<Group> groups = getGson().fromJson(responseBody, listGroupType);
			return groups;
		} catch (HttpStatus401Exception | 
				HttpStatus404Exception | 
				GenericHttpStatusException | 
				IOException e) {
			throw new BonitaException(e);
		}
	}

	private String getApiActorMemberUrl() {
		return getBonitaURI() + actorMemberApiUrl;
	}

	private String getApiUserUrl() {
		return getBonitaURI() + userApiUrl;
	}

	private String getApiGroupUrl() {
		return getBonitaURI() + groupApiUrl;
	}


	public List<org.bonitasoft.engine.identity.Group> findGroups(int startIndex, int maxResults, GroupCriterion groupcriterion) throws BonitaException {
		logger.info(" Chamando serviço  findGroups...  ");
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		grupos = identityAPI.getGroups(startIndex, maxResults , groupcriterion);
		doTenantLogout(session);
		return grupos;

	}
	
	public List<org.bonitasoft.engine.identity.Group> findGroupsByGroupDescriptor(int startIndex, int maxResults, String groupSearchDescriptor, Serializable criteria) throws BonitaException {
		logger.info(" Chamando serviço  findGroupsByName...  ");
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		List<SearchFilter> filters = new ArrayList<SearchFilter>();
		filters.add(new SearchFilter(groupSearchDescriptor, SearchFilterOperation.EQUALS, criteria));
		SearchOptions srco = new SearchOptionsBuilder(startIndex, maxResults).setFilters(filters).done();
		grupos = identityAPI.searchGroups(srco).getResult();
		
		doTenantLogout(session);
		return grupos;

	}


	/*****
	 * @author Sérgio da Victória
	 * @param  Nome do Grupo 
	 * @return Grupo selecionado - Achado
	 * @throws BonitaException
	 */
	public org.bonitasoft.engine.identity.Group findOneGroup(String groupName) throws BonitaException {
		logger.info(" Chamando serviço  findOneGroup...  ");
		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}
	
	public org.bonitasoft.engine.identity.Group findOneGroupById(long id) throws BonitaException {
		logger.info(" Chamando serviço  findOneGroup...  ");
		org.bonitasoft.engine.identity.Group group = null; 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.ID, id).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		group = searchResultGroup.getResult().get(0);
		doTenantLogout(session);
		return group;

	}


	/******
	 *  Faz consulta de todos os grupos existentes.
	 * 
	 * @author Sérgio da Victória
	 * @return Retorno Lista de Grupos 
	 * @throws BonitaException
	 */
	public List<UserGroup> findGroupS( ) throws BonitaException {
		logger.info(" Procurando por todos os Grupos ! ");

		List<UserGroup> userGroups = new ArrayList<UserGroup>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		SearchOptions searchOptions =  new SearchOptionsBuilder(0,10000).sort(GroupSearchDescriptor.NAME, Order.ASC ).done(); 
		SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
		for (org.bonitasoft.engine.identity.Group dto: searchResultGroup.getResult()) {
			if (dto.getName()!=null) {
				UserGroup group = new UserGroup();
				group.setId(dto.getId());
				group.setFirstname(dto.getName());
				group.setUserName(dto.getDisplayName());
				userGroups.add(group);
			}
		}

		doTenantLogout(session);
		return userGroups;
	}

	/*****
	 * @author Sérgio da Victória
	 * @return Retorna map de grupo com usuários
	 * @throws BonitaException
	 */
	public Map<String, List<User>> findGroupsWithUserMapS( ) throws BonitaException {
		Map<String, List<User>> map = new HashMap<String, List<User>>();
		List<UserGroup> goGroups = findGroupS( );
		for (UserGroup dto : goGroups) {
			List<User> findUserS = userService.findUserS(dto.getId());
			List<User> users = new ArrayList<User>();
			for (User user : findUserS) {
				users.add(user);
			}
			map.put(dto.getFirstname(), users);
		}
		return map;
	}


	public List<org.bonitasoft.engine.identity.User> findUserForGroups(long idGrupo, int startIndex, int maxResults) throws BonitaException {
		logger.info(" Chamando serviço  findUserForGroups...  ");
		List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		users = identityAPI.getUsersInGroup(idGrupo,startIndex,maxResults,org.bonitasoft.engine.identity.UserCriterion.FIRST_NAME_ASC);

		doTenantLogout(session);
		return users;

	}

	
	
	public List<org.bonitasoft.engine.identity.User> findUserForGroups(String groupName, int startIndex, int maxResults) throws BonitaException {
		logger.info(" Chamando serviço  findUserForGroups...  ");
		List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		org.bonitasoft.engine.identity.Group group = findOneGroup(groupName);
		users = identityAPI.getUsersInGroup(group.getId(),startIndex,maxResults,org.bonitasoft.engine.identity.UserCriterion.FIRST_NAME_ASC);

		doTenantLogout(session);
		return users;

	}
		
	
	public List<UserWithContactData> findWithContactDataS(List<org.bonitasoft.engine.identity.User> userSearch) throws BonitaException {
		logger.info(" Chamando serviço  findWithContactDataS...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		return findWithContactDataS(identityAPI, userSearch);
	}


	private List<UserWithContactData> findWithContactDataS(IdentityAPI identityAPI , List<org.bonitasoft.engine.identity.User> userSearch) throws BonitaException {
		logger.info(" Chamando serviço  findWithContactDataS...  ");
		List<UserWithContactData> contactDatas = new ArrayList<UserWithContactData>();
		for (org.bonitasoft.engine.identity.User dto : userSearch) {
			contactDatas.add(identityAPI.getUserWithProfessionalDetails(dto.getId()));	
		}
		return contactDatas;

	}

	/****
	 * Faz a busca do usuário pelo nome retornando informações do manager email, nome do manager, idManager 
	 * @author Sérgio da Victória
	 * @param userName Nome do Usuário
	 * @return Faz a busca do usuário pelo nome retornando informações do manager email, nome do manager, idManager
	 * @throws BonitaException
	 */
	public User findUserWithManager(Long idUser) {
		logger.info(" Procurando usuaŕio com manager...");
		Professional professional = new Professional();
		User user = new User();
		String nameManager = "";
		String emailManager= "";

		try {

			APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
			IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
			org.bonitasoft.engine.identity.User dto = identityAPI.getUser(idUser);
			Long idManager = dto.getManagerUserId();


			if (idManager!=null) {
				try {
					org.bonitasoft.engine.identity.User userManger =  identityAPI.getUser(idManager);
					UserWithContactData userWithContactData = identityAPI.getUserWithProfessionalDetails(idManager);
					if (userWithContactData.getContactData().getEmail()!=null) {
						emailManager = userWithContactData.getContactData().getEmail();
					}else{
						emailManager= "atendimento.csc@setaatacadista.com.br";
					}
					if (userManger.getUserName()!=null) {
						nameManager  = userManger.getUserName(); 
					}else {
						nameManager  = "Usuário sem Gerente Hierarquio";	
					} 
				}catch(Exception e) {
					emailManager = null;
				}
			}else {
				emailManager ="atendimento.csc@setaatacadista.com.br";
			}

			user.setEmailManager(emailManager);
			user.setIdManager(idManager);
			user.setNameManager(nameManager);
			user.setFirstname(dto.getFirstName());
			user.setLastname(user.getLastname());
			UserWithContactData userWithContactData = identityAPI.getUserWithProfessionalDetails(idUser);
			professional.setEmail(userWithContactData.getContactData().getEmail());
			professional.setPhone_number(userWithContactData.getContactData().getPhoneNumber());
			user.setProfessional_data(professional);

		}catch (Exception e ) {
			logger.error("Ocorreu um erro ao buscar perfil de usuário e aprovador Hierarquio!");
		}

		return user;
	}

	/*****
	 * @author Sérgio da Victória
	 * @param groupName Nome do Grupo
	 * @return Emails do Grupo
	 * @throws Exception
	 */
	public List<String> findEmails(String groupName) throws Exception {

		logger.info(" Chamando serviço  findEmails  ");
		List<String> emails = new ArrayList<String>();

		try {
			APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
			SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, groupName).done(); 
			IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
			SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
			org.bonitasoft.engine.identity.Group group;

			if (searchResultGroup.getResult().size()>0) {
				group = searchResultGroup.getResult().get(0);
			}else {
				emails.add("Grupo não possui emails");
				doTenantLogout(session);
				return emails;
			}

			List<org.bonitasoft.engine.identity.User> users = new ArrayList<org.bonitasoft.engine.identity.User>(); 
			users = identityAPI.getUsersInGroup(group.getId(),0,1000,org.bonitasoft.engine.identity.UserCriterion.FIRST_NAME_ASC);

			for (org.bonitasoft.engine.identity.User dto : users) {
				UserWithContactData withContactData = identityAPI.getUserWithProfessionalDetails(dto.getId());
				if (withContactData.getContactData().getEmail() != null ) {
					emails.add(withContactData.getContactData().getEmail().toString());	
				}
			}
			doTenantLogout(session);
		} catch (Exception e) {
			throw new RemoteException("Ocorreu um erro ao tentar acesssar Bonita API "+e);
		}

		return emails;

	}

	/****
	 * @author Sérgio da Victória
	 * @param nameGroup Nome do Grupo
	 * @return Numero numero do Grupo
	 */
	public Long findGrupName(String nameGroup) {

		try {
			
			APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
			SearchOptions searchOptions =  new SearchOptionsBuilder(0,1000).filter(GroupSearchDescriptor.NAME, nameGroup).done(); 
			IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
			SearchResult<org.bonitasoft.engine.identity.Group> searchResultGroup =  identityAPI.searchGroups(searchOptions);
			org.bonitasoft.engine.identity.Group group;

			if (searchResultGroup.getResult().size()>0) {
				group = searchResultGroup.getResult().get(0);
				doTenantLogout(session);
				return group.getId();
			}else {
				doTenantLogout(session);
				return 0L;
			}
		}catch(Exception e) {
			return 0L;
		}

	}

	public static void main(String[] args) throws BonitaException {
		//		GroupService groupService = new GroupService();
		//		List<org.bonitasoft.engine.identity.Group> fin = groupService.findGroupS();
		//		for (org.bonitasoft.engine.identity.Group dto : fin) {
		//              System.out.println(" dto "+dto.getName()+"  "+dto.getId());
		//		}

	}

	public long createGroup(Group group) throws BonitaException {
		logger.info(" Chamando serviço  createGroup...  ");
		List<org.bonitasoft.engine.identity.Group> grupos = new ArrayList<org.bonitasoft.engine.identity.Group>(); 
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		GroupCreator gc = new GroupCreator(group.getName());
		gc.setDescription(group.getDescription());
		gc.setDisplayName(group.getDisplayName());
		gc.setParentPath(group.getParent_path());
		//createGroup(String name, String parentPath) - parentPath - the parent path of the group (null means no parent)
		org.bonitasoft.engine.identity.Group grp = identityAPI.createGroup(gc);
		
		doTenantLogout(session);
		return grp.getId();
	}
	
	public void updateGroup(Group group) throws BonitaException {
		logger.info(" Chamando serviço  updateGroup...  ");
		APISession session = doTenantLogin(this.user.getUserName(),this.user.getPassword());
		IdentityAPI identityAPI = TenantAPIAccessor.getIdentityAPI(session);
		
		GroupUpdater gc = new GroupUpdater();
		gc.updateName(group.getName());
		gc.updateDescription(group.getDescription());
		gc.updateDisplayName(group.getDisplayName());
		gc.updateParentPath(group.getParent_path());
		//createGroup(String name, String parentPath) - parentPath - the parent path of the group (null means no parent)
		org.bonitasoft.engine.identity.Group grp = identityAPI.updateGroup(group.getId(), gc);
		
		doTenantLogout(session);
	}

}
