package com.inetum.AppBibliotheque.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import com.inetum.AppBibliotheque.dao.interfaces.IDaoGeneric;

@Transactional
public abstract class DaoGenericJpa<E, PK> implements IDaoGeneric<E, PK> {

	private Class<E> entityClass;

	public DaoGenericJpa(Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	public abstract EntityManager getEntityManager();

	@Override
	public E searchById(PK id) {
		return getEntityManager().find(entityClass, id);
	}

	@Override
	public List<E> searchAll() {
		return getEntityManager().createQuery("FROM " + entityClass.getSimpleName(), entityClass)
				.getResultList();
	}

	@Override
	public E insert(E e) {
		getEntityManager().persist(e);
		return e;
	}

	@Override
	public void saveOrUpdate(E e) {
		getEntityManager().merge(e);
	}

	@Override
	public void deleteById(PK id) {
		E e = getEntityManager().find(entityClass, id);
		getEntityManager().remove(e);
	}

}
