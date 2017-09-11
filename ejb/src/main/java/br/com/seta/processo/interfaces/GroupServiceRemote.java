package br.com.seta.processo.interfaces;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface GroupServiceRemote {
   public abstract List<String> findEmails(String groupName) throws Exception;
}
