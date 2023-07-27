package com.inetum.AppBibliotheque.livres.dao.interfaces;
import java.util.List;

import com.inetum.AppBibliotheque.dao.interfaces.IDaoGeneric;
import com.inetum.AppBibliotheque.livres.entities.Domaine;

public interface IDaoDomaine extends IDaoGeneric<Domaine, Long>{

	List<Domaine> findDomaineByNom(String nom);
	
	

}
