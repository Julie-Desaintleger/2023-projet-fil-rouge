package com.inetum.AppBibliotheque.livres.dao.interfaces;
import java.util.List;

import com.inetum.AppBibliotheque.dao.interfaces.IDaoGeneric;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire.EtatLivre;

public interface IDaoExemplaire extends IDaoGeneric<Exemplaire, Long>{
	
	List<Exemplaire> getDisponibilite(EtatLivre etat);

}
