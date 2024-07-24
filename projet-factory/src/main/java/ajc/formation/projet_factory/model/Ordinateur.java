package ajc.formation.projet_factory.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private Salle salle;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@OneToOne(mappedBy = "ordinateur")
	private Formateur formateur;
	
	@OneToOne(mappedBy = "ordinateur")
	private Stagiaire stagiaire;
	
	@OneToOne(mappedBy = "ordinateur")
	private Gestionnaire gestionnaire;
	
	@OneToOne(mappedBy = "ordinateur")
	private Technicien technicien;
	
	private String os;
	
	public Ordinateur() {
	}

	public Ordinateur(Integer id, String libelle, String adresseMac, String dateAchat, Salle salle, Status status,
			Formateur formateur, Stagiaire stagiaire, Gestionnaire gestionnaire, Technicien technicien, String os) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.adresseMac = adresseMac;
		this.dateAchat = dateAchat;
		this.salle = salle;
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

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
