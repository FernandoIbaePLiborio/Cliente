package io.github.profile.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.github.profile.dao.exeption.DAOException;

public abstract class AbstractGenericDAO<E, K> extends Object implements GenericDAO<E, K> {
	
	public E create(E entity) throws DAOException {
		try {
			getEntityManager().persist(entity);
			return entity;
		} catch (Exception ex) {
			throw new DAOException(ex);
		}
	}

	public Collection<E> create(Collection<E> entities) throws DAOException {
		for (Iterator<E> it = entities.iterator(); it.hasNext();) {
			create(it.next());
		}

		return entities;
	}

	public E update(E entity) throws DAOException {
		try {
			return (E) getEntityManager().merge(entity);
		} catch (Exception ex) {

			throw new DAOException(ex);
		}
	}

	public void remove(E entity) throws DAOException {
		try {
			getEntityManager().remove(entity);
		} catch (Exception ex) {
			throw new DAOException(ex);
		}
	}

	public void remove(Collection<E> entities) throws DAOException {
		for (Iterator<E> it = entities.iterator(); it.hasNext();) {
			E entity = (E) it.next();
			remove(entity);
		}
	}

	public void delete(K primariKey) throws DAOException {
		try {
			Object localObject = getEntityManager().find(getEntityClass(), primariKey);
			getEntityManager().remove(localObject);
		} catch (Exception ex) {
			throw new DAOException(ex);
		}
	}

	public E findById(K primariKey) throws DAOException {
		try {
			return (E) getEntityManager().find(getEntityClass(), primariKey, LockModeType.NONE);
		} catch (Exception ex) {
			throw new DAOException(ex);
		}
	}

	public List<E> find(List<K> primariKeys) throws DAOException {
		List<E> retorno = new ArrayList<E>();
		try {
			for (K id : primariKeys) {
				E e = (E) findById(id);
				if (e != null) {
					retorno.add(e);
				}
			}
			return retorno;
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<E> all() throws DAOException {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<E> cq = cb.createQuery(getEntityClass());
		cq.select(cq.from(getEntityClass()));

		return getEntityManager().createQuery(cq).getResultList();
	}

	public List<E> findForNamedQuery(String namedQuery, Map<String, Object> parameters) throws DAOException {
		List<E> result = new ArrayList<E>();

		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	public E findSingleResultForNamedQuery(String namedQuery, Map<String, Object> parameters) throws DAOException {
		E result = null;

		try {
			Query query = getEntityManager().createNamedQuery(namedQuery);

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(query, parameters);
			}

			result = (E) query.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	public List<E> findForQuery(String jpaQuery, Map<String, Object> parameters) throws DAOException {
		List<E> result = new ArrayList<E>();

		try {
			TypedQuery typedQuery = getEntityManager().createQuery(jpaQuery, getEntityClass());

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(typedQuery, parameters);
			}
			result = typedQuery.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	public E findSingleResultForQuery(String jpaQuery, Map<String, Object> parameters) throws DAOException {
		E result = null;

		try {
			TypedQuery typedQuery = getEntityManager().createQuery(jpaQuery, getEntityClass());

			if (parameters != null && !parameters.isEmpty()) {
				populateQueryParameters(typedQuery, parameters);
			}

			result = (E) typedQuery.getSingleResult();
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	private void populateQueryParameters(Query query, Map<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter((String) entry.getKey(), entry.getValue());
		}
	}

	public Long count() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		Root<E> rt = cq.from(getEntityClass());
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		TypedQuery typedQuery = getEntityManager().createQuery(cq);
		return (Long) typedQuery.getSingleResult();
	}

	public List<E> findByExample(E example) throws DAOException {
		List<E> results = new ArrayList<E>();
		try {
			Query query = QueryToFindByExample(example);
			results = query.getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return results;
	}

	private Query QueryToFindByExample(E example) throws IllegalArgumentException, IllegalAccessException {
		String AND_CLAUSE = " AND ";

		StringBuffer queryByExample = (new StringBuffer("select A from ")).append(example.getClass().getSimpleName())
				.append(" A where ");
		Field[] fields = example.getClass().getDeclaredFields();

		for (Field current : fields) {
			current.setAccessible(Boolean.TRUE.booleanValue());
			if (!Modifier.isStatic(current.getModifiers()) && current.get(example) != null) {
				queryByExample = queryByExample.append(" A.").append(current.getName()).append(" = ").append(":")
						.append(current.getName()).append(" AND ");
			}
		}

		if (queryByExample.substring(queryByExample.length() - " AND ".length()).equalsIgnoreCase(" AND ")) {
			queryByExample = queryByExample.delete(queryByExample.length() - " AND ".length() + 1,
					queryByExample.length());
		}

		Query query = getEntityManager().createQuery(queryByExample.toString());

		for (Field current : fields) {
			if (current != null) {
				current.setAccessible(Boolean.TRUE.booleanValue());
				if (!Modifier.isStatic(current.getModifiers()) && current.get(example) != null) {
					query.setParameter(current.getName(), current.get(example));
				}
			}
		}

		return query;
	}

	protected abstract Class<E> getEntityClass();
}