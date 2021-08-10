package io.github.profile.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;

import io.github.profile.dao.exeption.DAOException;

public interface GenericDAO<E, K> {

	EntityManager getEntityManager();

	E create(E paramE) throws DAOException;

	Collection<E> create(Collection<E> paramCollection) throws DAOException;

	E update(E paramE) throws DAOException;

	void delete(K paramK) throws DAOException;

	void remove(E paramE) throws DAOException;

	E findById(K paramK) throws DAOException;

	Collection<E> all() throws DAOException;

	void remove(Collection<E> paramCollection) throws DAOException;

	List<E> find(List<K> paramList) throws DAOException;

	Collection<E> findForNamedQuery(String paramString, Map<String, Object> paramMap) throws DAOException;

	E findSingleResultForNamedQuery(String paramString, Map<String, Object> paramMap) throws DAOException;

	Collection<E> findForQuery(String paramString, Map<String, Object> paramMap) throws DAOException;

	E findSingleResultForQuery(String paramString, Map<String, Object> paramMap) throws DAOException;

	List<E> findByExample(E paramE) throws DAOException;
}
