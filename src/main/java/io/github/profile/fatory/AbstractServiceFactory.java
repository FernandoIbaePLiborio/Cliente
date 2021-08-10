package io.github.profile.fatory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public abstract class AbstractServiceFactory implements ServiceFactory {

	private final Context context;

	protected AbstractServiceFactory() throws NamingException {

		this.context = new InitialContext();
	}

	protected abstract String getJndiProjectName();

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getService(final String jndiName) throws IllegalArgumentException, NamingException {

		return (T) this.context.lookup(String.format("java:app/%s/%s", this.getJndiProjectName(), jndiName));
	}

}