package com.inetum.AppBibliotheque.livres.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoDomaine;
import com.inetum.AppBibliotheque.livres.entities.Domaine;

@Repository //pour cette classe de DAO soit prise en charge par Spring
@Transactional //pour demander commit/rollback automatiques

public class DaoDomaineJpa extends DaoGenericJpa<Domaine, Long> implements IDaoDomaine {
	@PersistenceContext
	private EntityManager entityManager;

	public DaoDomaineJpa() {
		super(Domaine.class);
	}
	@Override
	public List<Domaine> findDomaineByNom(String nom) {
		return entityManager
				.createNamedQuery("Domaine.findDomaineByNom",Domaine.class)
				.setParameter(1,nom)//pour valeur de ?1
				.getResultList();
	}
	
	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}


	

