package ajc.formation.projet_factory.dto.response;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonView;

import ajc.formation.projet_factory.model.Formateur;
import ajc.formation.projet_factory.model.Gestionnaire;
import ajc.formation.projet_factory.model.Ordinateur;
import ajc.formation.projet_factory.model.Salle;
import ajc.formation.projet_factory.model.Stagiaire;
import ajc.formation.projet_factory.model.Technicien;

public class OrdinateurResponse {
	@JsonView(CustomJsonViews.Common.class)
	private Integer id;
	@JsonView(CustomJsonViews.Common.class)
	private String libelle;
	@JsonView(CustomJsonViews.Common.class)
	private String adresseMac;
	@JsonView(CustomJsonViews.Common.class)
	private String dateAchat;
	
	private Salle emplacement;

	@JsonView(CustomJsonViews.Common.class)
	private String status;

	private Formateur formateur;

	private Stagiaire stagiaire;

	private Gestionnaire gestionnaire;

	@JsonView(CustomJsonViews.OrdinateurWithTechnicien.class)
	private Technicien technicien;
	
	@JsonView(CustomJsonViews.Common.class)
	private String os;

	public OrdinateurResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public OrdinateurResponse(Ordinateur ordinateur) {
		BeanUtils.copyProperties(ordinateur, this);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAdresseMac() {
		return adresseMac;
	}

	public void setAdresseMac(String adresseMac) {
		this.adresseMac = adresseMac;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}

	public Salle getEmplacement() {
		return emplacement;
	}

	public void setEmplacement(Salle emplacement) {
		this.emplacement = emplacement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public Gestionnaire getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(Gestionnaire gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public Technicien getTechnicien() {
		return technicien;
	}

	public void setTechnicien(Technicien technicien) {
		this.technicien = technicien;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}
	
	
}
