package io.github.profile.dao;

import java.util.Collection;

import javax.ejb.Local;

import io.github.profile.dao.exeption.DAOException;
import io.github.profile.model.Cliente;

@Local
public interface ClienteDao extends GenericDAO<Cliente, Long> {

	public Collection<Cliente> buscarCliente(String cpf) throws DAOException; 
}
