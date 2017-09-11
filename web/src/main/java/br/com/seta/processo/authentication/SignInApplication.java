package br.com.seta.processo.authentication;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.component.IRequestableComponent;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.IResource;

import br.com.seta.processo.page.Atividades;


public final class SignInApplication extends ProcessoApplication {

    public SignInApplication() {
    }


    @Override
    public Class<? extends Page> getHomePage()   {
        return Atividades.class;
    }


    @Override
    public Session newSession(Request request, Response response)   {
        return new SignInSession(request);
    }


    @Override
    protected void init()    {
        super.init();

        getSecuritySettings().setAuthorizationStrategy(new IAuthorizationStrategy()  {
        	
            public boolean isActionAuthorized(Component component, Action action)     {
                return true;
            }

            public <T extends IRequestableComponent> boolean isInstantiationAuthorized(Class<T> componentClass)   {
                if (AuthenticatedWebPage.class.isAssignableFrom(componentClass)) {
                    if (((SignInSession)Session.get()).isSignedIn())  {
                        return true;
                    }
                    throw new RestartResponseAtInterceptPageException(SignIn.class);
                }
               return true;
            }


			public boolean isResourceAuthorized(IResource arg0,	PageParameters arg1) {
				return false;
			}  
			

        });
    }
}
