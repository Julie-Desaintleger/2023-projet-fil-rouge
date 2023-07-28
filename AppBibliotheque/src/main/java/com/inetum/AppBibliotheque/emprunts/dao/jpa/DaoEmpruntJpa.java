package com.inetum.AppBibliotheque.emprunts.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.emprunts.dao.interfaces.IDaoEmprunt;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;

@Repository // pour cette classe de DAO soit prise en charge par Spring
@Transactional // pour demander commit/rollback automatiques
public class DaoEmpruntJpa extends DaoGenericJpa<Emprunter, Long> implements IDaoEmprunt {

	@PersistenceContext
	private EntityManager entityManager;

	public DaoEmpruntJpa() {
		super(Emprunter.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Emprunter findById(Long numero) {
		return entityManager.find(Emprunter.class, numero);
	}

	@Override
	public List<Emprunter> findAll() {
		return entityManager.createNamedQuery("Emprunter.findAll", Emprunter.class).getResultList();
	}

	@Override
	public Emprunter insert(Emprunter emprunt) {
		entityManager.persist(emprunt);// .numero n'est normalement plus null si auto-incr
		return emprunt; // on retourne l'objet modifié (avec .numero non null)
	}

	@Override
	public void update(Emprunter emprunt) {
		entityManager.merge(emprunt);
	}

	@Override
	public void deleteById(Long numero) {
		Emprunter empruntAsupprimer = entityManager.find(Emprunter.class, numero);
		entityManager.remove(empruntAsupprimer);
	}
}
