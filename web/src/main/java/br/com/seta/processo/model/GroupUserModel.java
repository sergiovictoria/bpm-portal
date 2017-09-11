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

import br.com.seta.processo.dto.Group;
import br.com.seta.processo.dto.User;
import br.com.seta.processo.dto.UserGroup;
import br.com.seta.processo.service.ExecuteRestAPI;


public class GroupUserModel implements IClusterable, Serializable {


	private static final long serialVersionUID = 1L;
	private final Map<String, List<String>> modelsMap = new HashMap<String, List<String>>();
	private transient User user = (User) Session.get().getAttribute("user");
	public static String defaultValue;

	@Inject
	private ExecuteRestAPI executeRestAPI;


	public GroupUserModel( ) {
		CdiContainer.get().getNonContextualManager().inject(this);
		List<Group> groups = new ArrayList<Group>();
		try {
			groups = executeRestAPI.retriveGroupList(user, 0,1000);
			defaultValue = groups.get(0).getName(); 
			for (Group group : groups) {
				List<String>  userGroups = new ArrayList<String>(0);	
				for (UserGroup dto : executeRestAPI.retriveUserForGroupList(user, 0,1000, group.getId())) {
					userGroups.add(dto.getUserName());
				}
				this.modelsMap.put(group.getName(),userGroups); 	
			}
		}catch (Exception e) {
		}
	}


	public Map<String, List<String>> getModelsMap() {
		return modelsMap;
	}
	
	

	
}

