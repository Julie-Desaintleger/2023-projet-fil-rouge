package com.inetum.AppBibliotheque.livres.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.inetum.AppBibliotheque.dao.jpa.DaoGenericJpa;
import com.inetum.AppBibliotheque.livres.dao.interfaces.IDaoLivre;
import com.inetum.AppBibliotheque.livres.entities.Livre;

@Repository //pour cette classe de DAO soit prise en charge par Spring
@Transactional //pour demander commit/rollback automatiques
public class DaoLivreJpa extends DaoGenericJpa<Livre, Long> implements IDaoLivre {
	@PersistenceContext
	private EntityManager entityManager;

	public DaoLivreJpa() {
		super(Livre.class);
	}

	@Override
	public List<Livre> findLivreByTitre(String titre) {
		return entityManager
				.createNamedQuery("Livre.findLivreByTitre",Livre.class)
				.setParameter(1,titre)//pour valeur de ?1
				.getResultList();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
