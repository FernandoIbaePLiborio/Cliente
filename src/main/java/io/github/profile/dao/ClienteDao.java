package io.github.profile.dao;

import javax.ejb.Local;

import io.github.profile.dao.exeption.DAOException;
import io.github.profile.model.Cliente;

@Local
public interface ClienteDao extends GenericDAO<Cliente, Long> {

	public Cliente buscarCliente(String cpf) throws DAOException; 
}
