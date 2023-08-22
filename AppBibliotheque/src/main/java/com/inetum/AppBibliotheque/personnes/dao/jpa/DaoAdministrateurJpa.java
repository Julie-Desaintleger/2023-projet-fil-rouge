package com.inetum.AppBibliotheque.personnes.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.personnes.dao.interfaces.IDaoAdministrateur;
import com.inetum.AppBibliotheque.personnes.entities.Administrateur;

@Repository // pour cette classe de DAO soit prise en charge par Spring
@Transactional // pour demander commit/rollback automatiques
public class DaoAdministrateurJpa extends DaoGenericJpa<Administrateur, Long>
			implements IDaoAdministrateur {

	@PersistenceContext
	private EntityManager entityManager;

	public DaoAdministrateurJpa() {
		super(Administrateur.class);
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Administrateur searchById(Long numero) {
		return entityManager.find(Administrateur.class, numero);
	}

	@Override
	public List<Administrateur> searchAll() {
		return entityManager.createNamedQuery("Administrateur.findAll", Administrateur.class)
					.getResultList();
	}

	@Override
	public Administrateur insert(Administrateur admin) {
		entityManager.persist(admin);// .numero n'est normalement plus null si auto-incr
		return admin; // on retourne l'objet modifié (avec .numero non null)
	}

	@Override
	public void saveOrUpdate(Administrateur admin) {
		entityManager.merge(admin);
	}

	@Override
	public void deleteById(Long numero) {
		Administrateur personneAsupprimer = entityManager.find(Administrateur.class, numero);
		entityManager.remove(personneAsupprimer);
	}

}
