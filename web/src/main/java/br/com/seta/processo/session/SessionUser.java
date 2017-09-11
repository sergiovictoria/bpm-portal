package br.com.seta.processo.session;

import br.com.seta.processo.dto.User;


public final class SessionUser {

	private static SessionUser sessionUser;
	private User user = new User();
	
	public static SessionUser getInstance(){
		if (sessionUser == null){
			sessionUser = new SessionUser();
		}
		return sessionUser;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	
	
	
}
