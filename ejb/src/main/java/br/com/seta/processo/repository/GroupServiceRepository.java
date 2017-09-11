package br.com.seta.processo.repository;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;

import br.com.seta.processo.interceptor.LogInterceptor;
import br.com.seta.processo.interfaces.GroupServiceRemote;
import br.com.seta.processo.service.FindGroups;


@TransactionManagement(TransactionManagementType.CONTAINER)
@Remote(GroupServiceRemote.class)
@Interceptors({LogInterceptor.class})
@Stateless(name="GroupServiceRepository")

public class GroupServiceRepository implements GroupServiceRemote {

	@Inject private FindGroups findGroups;
	
	@Override
	public List<String> findEmails(String groupName) throws Exception {
		return findGroups.findEmails(groupName);
	}

}
