package com.inetum.AppBibliotheque.personnes.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoLecteur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;

@Repository // pour cette classe de DAO soit prise en charge par Spring
@Transactional // pour demander commit/rollback automatiques
public class DaoLecteurJpa extends DaoGenericJpa<Lecteur, Long> implements IDaoLecteur {

	@PersistenceContext
	private EntityManager entityManager;

	public DaoLecteurJpa() {
		super(Lecteur.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Lecteur findById(Long numero) {
		return entityManager.find(Lecteur.class, numero);
	}

	@Override
	public List<Lecteur> findAll() {
		return entityManager.createNamedQuery("Lecteur.findAll", Lecteur.class).getResultList();
	}

	@Override
	public Lecteur insert(Lecteur lecteur) {
		entityManager.persist(lecteur);// .numero n'est normalement plus null si auto-incr
		return lecteur; // on retourne l'objet modifié (avec .numero non null)
	}

	@Override
	public void update(Lecteur lecteur) {
		entityManager.merge(lecteur);
	}

	@Override
	public void deleteById(Long numero) {
		Lecteur lecteurAsupprimer = entityManager.find(Lecteur.class, numero);
		entityManager.remove(lecteurAsupprimer);
	}

}
