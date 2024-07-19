package ajc.formation.projet_factory.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordinateur")
public class Ordinateur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String libelle;
	
	@Column(name="adresse_mac")
	private String adresseMac;

	@Column(name="date_achat")
	private String dateAchat;
	
	@ManyToOne
	private Salle emplacement;
	
	private String status;
	
	@OneToOne
	private Formateur formateur;
	
	@OneToOne
	private Stagiaire stagiaire;
	
	@OneToOne
	private Gestionnaire gestionnaire;
	
	@OneToOne
	private Technicien technicien;
	
	private String os;
	
	public Ordinateur() {
	}

	public Ordinateur(Integer id, String libelle, String adresseMac, String dateAchat, Salle emplacement, String status,
			Formateur formateur, Stagiaire stagiaire, Gestionnaire gestionnaire, Technicien technicien, String os) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.adresseMac = adresseMac;
		this.dateAchat = dateAchat;
		this.emplacement = emplacement;
		this.status = status;
		this.formateur = formateur;
		this.stagiaire = stagiaire;
		this.gestionnaire = gestionnaire;
		this.technicien = technicien;
		this.os = os;
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

	public void setEmplacemeht(Salle emplacement) {
		this.emplacement = emplacement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
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

	public void setEmplacement(Salle emplacement) {
		this.emplacement = emplacement;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordinateur other = (Ordinateur) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
