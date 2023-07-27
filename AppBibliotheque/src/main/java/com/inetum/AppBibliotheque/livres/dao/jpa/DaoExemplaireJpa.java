package com.inetum.AppBibliotheque.livres.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;

@Repository //pour cette classe de DAO soit prise en charge par Spring
@Transactional //pour demander commit/rollback automatiques

public class DaoExemplaireJpa extends DaoGenericJpa<Exemplaire, Long> implements IDaoExemplaire {
	@PersistenceContext
	private EntityManager entityManager;

	public DaoExemplaireJpa() {
		super(Exemplaire.class);
	}

	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}


	@Override
	public boolean IsExemplaireDispo() {
		// TODO Auto-generated method stub
		return false;
	}

}