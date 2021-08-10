package io.github.profile.dao.impl;

import java.util.Objects;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import io.github.profile.dao.AbstractGenericDAO;
import io.github.profile.dao.ClienteDao;
import io.github.profile.dao.exeption.DAOException;
import io.github.profile.model.Cliente;

@Stateless
public class ClienteDaoImpl extends AbstractGenericDAO<Cliente, Long> implements ClienteDao {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	protected Class<Cliente> getEntityClass() {
		return Cliente.class;
	}

	@Override
	public Cliente buscarCliente(String cpfCliente) throws DAOException {
		
		final StringBuilder jpql = new StringBuilder();
			
		jpql.append("select c from Cliente c where 1 = 1 ");
		
		if(!Objects.isNull(cpfCliente)) {
			
			jpql.append("and c.cpf = :cpfCliente "); 
		}
		
		jpql.append("order by c.nome "); 
		try {
			
			final Query query = this.em.createQuery(jpql.toString());
			
			if(!Objects.isNull(cpfCliente)) {
				
				query.setParameter("cpfCliente", cpfCliente); 
			}
			return (Cliente) query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		} catch (final PersistenceException e) {
			throw new DAOException(e);
		}
	}
	
}

