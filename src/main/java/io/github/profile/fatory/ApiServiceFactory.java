package io.github.profile.fatory;

import javax.naming.NamingException;

public class ApiServiceFactory extends AbstractServiceFactory {

	private static final String JNDI_PROJECT_NAME = "Cliente";
	
	private static ApiServiceFactory instance;
	
	protected ApiServiceFactory() throws NamingException {
		super();
	}
	
	public static ApiServiceFactory getInstance() throws NamingException {
		synchronized (ApiServiceFactory.class) {
			if (instance == null)
				instance = new ApiServiceFactory();
		}
		
		return instance;
	}
	
	@Override
	protected String getJndiProjectName() {
		return JNDI_PROJECT_NAME;
	}

}
