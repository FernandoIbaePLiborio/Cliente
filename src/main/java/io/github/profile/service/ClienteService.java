package io.github.profile.service;

import java.util.Collection;

import javax.ejb.Local;

import io.github.profile.dao.exeption.BusinessException;
import io.github.profile.model.Cliente;

@Local
public interface ClienteService {

	public String JNDI_NAME = "ejb/ClienteServiceImpl";
			
	public void salvar(final Cliente cliente) throws BusinessException;
	
	public void delete(final Long id) throws BusinessException;

	public Collection<Cliente> pesquisar() throws BusinessException;

	public Cliente buscarCliente(String cpf) throws BusinessException;

}
