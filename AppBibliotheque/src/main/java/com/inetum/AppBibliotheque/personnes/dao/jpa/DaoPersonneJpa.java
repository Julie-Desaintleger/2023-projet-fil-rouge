package com.inetum.AppBibliotheque.personnes.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoPersonne;
import com.inetum.AppBibliotheque.personnes.entities.Personne;

@Repository // pour cette classe de DAO soit prise en charge par Spring
@Transactional // pour demander commit/rollback automatiques
public class DaoPersonneJpa extends DaoGenericJpa<Personne, Long> implements IDaoPersonne {

	@PersistenceContext
	private EntityManager entityManager;

	public DaoPersonneJpa() {
		super(Personne.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Personne searchById(Long numero) {
		return entityManager.find(Personne.class, numero);
	}

	@Override
	public List<Personne> searchAll() {
		return entityManager.createNamedQuery("Personne.findAll", Personne.class).getResultList();
	}

	@Override
	public Personne insert(Personne p) {
		entityManager.persist(p);// .numero n'est normalement plus null si auto-incr
		return p; // on retourne l'objet modifié (avec .numero non null)
	}

	@Override
	public void saveOrUpdate(Personne p) {
		entityManager.merge(p);
	}

	@Override
	public void deleteById(Long numero) {
		Personne personneAsupprimer = entityManager.find(Personne.class, numero);
		entityManager.remove(personneAsupprimer);
	}

}
