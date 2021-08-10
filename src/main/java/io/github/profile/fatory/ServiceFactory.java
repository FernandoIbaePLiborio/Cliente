package io.github.profile.fatory;

import javax.naming.NamingException;

public interface ServiceFactory {

	public <T> T getService(final String jndiName) throws IllegalArgumentException, NamingException;

}