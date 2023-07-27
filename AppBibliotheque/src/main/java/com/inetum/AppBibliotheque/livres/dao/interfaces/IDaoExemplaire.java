package com.inetum.AppBibliotheque.livres.dao.interfaces;
import com.inetum.AppBibliotheque.dao.interfaces.IDaoGeneric;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;

public interface IDaoExemplaire extends IDaoGeneric<Exemplaire, Long>{
	
	boolean IsExemplaireDispo();

}
