package com.inetum.AppBibliotheque.livres.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoExemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;

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
	public List<Exemplaire> getDisponibilite(EtatLivre etat) {
		return entityManager
				.createNamedQuery("Exemplaire.getDisponibilite",Exemplaire.class)
				.setParameter(1,etat)
				.getResultList();
	}


}