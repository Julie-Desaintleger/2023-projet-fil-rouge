package com.inetum.AppBibliotheque.livres.dao.interfaces;

import java.util.List;

import com.inetum.AppBibliotheque.dao.interfaces.IDaoGeneric;
import com.inetum.AppBibliotheque.livres.entities.Livre;

public interface IDaoLivre extends IDaoGeneric<Livre, Long>{
	
	List<Livre> findLivreByTitre(String titre);

}
