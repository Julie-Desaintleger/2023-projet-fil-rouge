package com.inetum.AppBibliotheque.converter;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDto;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx;
import com.inetum.AppBibliotheque.emprunts.dto.EmprunterDtoEx2;
import com.inetum.AppBibliotheque.emprunts.entities.Emprunter;
import com.inetum.AppBibliotheque.livres.dto.DomaineDto;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDto;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx;
import com.inetum.AppBibliotheque.livres.dto.ExemplaireDtoEx2;
import com.inetum.AppBibliotheque.livres.dto.LivreDto;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx;
import com.inetum.AppBibliotheque.livres.dto.LivreDtoEx2;
import com.inetum.AppBibliotheque.livres.entities.Exemplaire;
import com.inetum.AppBibliotheque.livres.entities.Livre;
import com.inetum.AppBibliotheque.personnes.dto.AdministrateurDto;
import com.inetum.AppBibliotheque.personnes.dto.LecteurDto;
import com.inetum.AppBibliotheque.personnes.dto.PersonneDto;
import com.inetum.AppBibliotheque.personnes.entities.Administrateur;
import com.inetum.AppBibliotheque.personnes.entities.Lecteur;
import com.inetum.AppBibliotheque.personnes.entities.Personne;

public class DtoConverter {

	// LIVRES-------------------------------------
	public /* static */ LivreDto LivreToLivreDto(Livre entity) {
		return new LivreDto(entity.getIdLivre(), entity.getTitre(), entity.getAuteur(), entity.getEditeur());
	}

	public /* static */ List<LivreDto> LivreToLivreDto(List<Livre> entityList) {
		return entityList.stream().map((entity) -> LivreToLivreDto(entity)).toList();
	}

	public /* static */ LivreDtoEx2 LivreToLivreDtoEx2(Livre entity) {
		LivreDtoEx2 livreDto = new LivreDtoEx2();
		if (entity != null) {
			BeanUtils.copyProperties(entity, livreDto);
			livreDto.setDomaine(GenericConverter.map(entity.getDomaine(), DomaineDto.class));
			return livreDto;
		}else {
			return null;
		}
	}

	public /* static */ List<LivreDtoEx2> LivreToLivreDtoEx2(List<Livre> entityList) {
		return entityList.stream().map((entity) -> LivreToLivreDtoEx2(entity)).toList();
	}

	public /* static */ LivreDtoEx LivreToLivreDtoEx(Livre entity) {
		Long idDom = entity.getDomaine() != null ? entity.getDomaine().getId() : null;
		LivreDtoEx LivreDto = new LivreDtoEx();
		BeanUtils.copyProperties(entity, LivreDto); // compact/écriture concise mais pas rapide
		LivreDto.setIdDomaine(idDom);
		return LivreDto;
	}

	public /* static */ List<LivreDtoEx> LivreToLivreDtoEx(List<Livre> entityList) {
		return entityList.stream().map((entity) -> LivreToLivreDtoEx(entity)).toList();
	}

	// EXEMPLAIRES

	public /* static */ ExemplaireDto exemplaireToExemplaireDto(Exemplaire entity) {
		return new ExemplaireDto(entity.getIdExemp(), entity.getEtat().toString(), entity.getIsDisponibilite(),
				entity.getLabel());
	}

	public /* static */ List<ExemplaireDto> exemplaireToExemplaireDto(List<Exemplaire> entityList) {
		return entityList.stream().map((entity) -> exemplaireToExemplaireDto(entity)).toList();
	}

	public /* static */ ExemplaireDtoEx2 exemplaireToExemplaireDtoEx2(Exemplaire entity) {
		ExemplaireDtoEx2 exemplaireDto = new ExemplaireDtoEx2();
		BeanUtils.copyProperties(entity, exemplaireDto);
		exemplaireDto.setLivre(GenericConverter.map(entity.getLivre(), LivreDto.class));
		exemplaireDto.setEtat(entity.getEtat().toString());
		return exemplaireDto;
	}

	public /* static */ List<ExemplaireDtoEx2> exemplaireToExemplaireDtoEx2(List<Exemplaire> entityList) {
		return entityList.stream().map((entity) -> exemplaireToExemplaireDtoEx2(entity)).toList();
	}

	public /* static */ ExemplaireDtoEx exemplaireToExemplaireDtoEx(Exemplaire entity) {
		Long idLivre = entity.getLivre() != null ? entity.getLivre().getIdLivre() : null;
		ExemplaireDtoEx exemplaireDto = new ExemplaireDtoEx();
		BeanUtils.copyProperties(entity, exemplaireDto); // compact/écriture concise mais pas rapide
		exemplaireDto.setIdLiv(idLivre);
		exemplaireDto.setEtat(entity.getEtat().toString());
		return exemplaireDto;
	}

	public /* static */ List<ExemplaireDtoEx> exemplaireToExemplaireDtoEx(List<Exemplaire> entityList) {
		return entityList.stream().map((entity) -> exemplaireToExemplaireDtoEx(entity)).toList();
	}

	// EMPRUNTS

	public /* static */ List<EmprunterDto> emprunterToEmprunterDto(List<Emprunter> entityList) {
		return entityList.stream().map((entity) -> emprunterToEmprunterDto(entity)).toList();
	}

	public /* static */ EmprunterDto emprunterToEmprunterDto(Emprunter entity) {
		return new EmprunterDto(entity.getId(), entity.getDateDebut(), entity.getDateFin(),
				entity.getType().toString());
	}

	public /* static */ EmprunterDtoEx2 emprunterToEmprunterDtoEx2(Emprunter entity) {
		EmprunterDtoEx2 emprunterDto = new EmprunterDtoEx2();
		BeanUtils.copyProperties(entity, emprunterDto);
		emprunterDto.setExemplaire(GenericConverter.map(entity.getExemplaireEmprunte(), ExemplaireDto.class));
		emprunterDto.setLecteur(GenericConverter.map(entity.getEmprunteur(), LecteurDto.class));
		emprunterDto.setType(entity.getType().toString());
		return emprunterDto;
	}
	
	public /* static */ List<EmprunterDtoEx2> emprunterToEmprunterDtoEx2(List<Emprunter> entityList) {
		return entityList.stream().map((entity) -> emprunterToEmprunterDtoEx2(entity)).toList();
	}

	public /* static */ EmprunterDtoEx emprunterToEmprunterDtoEx(Emprunter entity) {
		Long idExemp = entity.getExemplaireEmprunte() != null ? entity.getExemplaireEmprunte().getIdExemp() : null;
		Long idLect = entity.getEmprunteur() != null ? entity.getEmprunteur().getIdPersonne() : null;
		EmprunterDtoEx emprunterDto = new EmprunterDtoEx();
		BeanUtils.copyProperties(entity, emprunterDto); // compact/écriture concise mais pas rapide
		emprunterDto.setIdExemp(idExemp);
		emprunterDto.setIdLecteur(idLect);
		emprunterDto.setType(entity.getType().toString());
		return emprunterDto;
	}

	public /* static */ List<EmprunterDtoEx> emprunterToEmprunterDtoEx(List<Emprunter> entityList) {
		return entityList.stream().map((entity) -> emprunterToEmprunterDtoEx(entity)).toList();
	}

	// Personnes -------------------------------------

	// Personne
	public PersonneDto personneToPersonneDto(Personne entity) {
		return new PersonneDto(entity.getIdPersonne(), entity.getPrenom(), entity.getNom(), entity.getEmail(),
				entity.getTelephone(), entity.getAdresse());
	}

	public Personne personneDtoToPersonne(PersonneDto dto) {
		return new Personne(dto.getIdPersonne(), dto.getPrenom(), dto.getNom(), dto.getEmail(), dto.getTelephone(),
				dto.getAdresse());
	}

	public List<PersonneDto> personneToPersonneDto(List<Personne> entityList) {
		return entityList.stream().map(e -> personneToPersonneDto(e)).toList();
	}

	// Lecteur
	public LecteurDto lecteurToLecteurDto(Lecteur entity) {
		return new LecteurDto(entity.getIdPersonne(), entity.getPrenom(), entity.getNom(), entity.getEmail(),
				entity.getTelephone(), entity.getAdresse());
	}

	public Lecteur lecteurDtoToLecteur(LecteurDto dto) {
		return new Lecteur(dto.getIdPersonne(), dto.getPrenom(), dto.getNom(), dto.getEmail(), dto.getTelephone(),
				dto.getAdresse());
	}

	public List<LecteurDto> lecteurToLecteurDto(List<Lecteur> entityList) {
		return entityList.stream().map(e -> lecteurToLecteurDto(e)).toList();
	}

	// Administrateur

	public AdministrateurDto administrateurToAdministrateurDto(Administrateur entity) {
		return new AdministrateurDto(entity.getIdPersonne(), entity.getPrenom(), entity.getNom(),
					entity.getEmail(), entity.getTelephone(), entity.getAdresse(), entity.getUsername(),
					entity.getPassword());
	}

	public Administrateur administrateurDtoToAdministrateur(AdministrateurDto dto) {
		return new Administrateur(dto.getIdPersonne(), dto.getPrenom(), dto.getNom(), dto.getEmail(),
					dto.getTelephone(), dto.getAdresse(), dto.getUsername(), dto.getPassword());
	}

	public List<AdministrateurDto> administrateurToAdministrateurDto(List<Administrateur> entityList) {
		return entityList.stream().map(e -> administrateurToAdministrateurDto(e)).toList();
	}

}
