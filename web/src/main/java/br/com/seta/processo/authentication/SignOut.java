package br.com.seta.processo.authentication;

import org.apache.wicket.request.mapper.parameter.PageParameters;

import br.com.seta.processo.page.ProcessoWebPage;




public class SignOut extends ProcessoWebPage {

	private static final long serialVersionUID = 1L;

	public SignOut(final PageParameters parameters)	{
		setResponsePage(SignIn.class);
		getSession().invalidate();
		getSession().clear();
	}
	

}
